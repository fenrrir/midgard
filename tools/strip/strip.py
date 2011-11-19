#!/usr/bin/env python
# -*- coding: UTF-8 -*-


import re
import os
from os.path import join, dirname, abspath
import pprint
import simplejson
from simplejson.decoder import JSONDecodeError


ROOT = abspath(join( dirname( __file__ ), "..", ".." ))

TARGET_DIRECTORY = join(ROOT, "build")
SRC_DIRECTORY = join(ROOT, "src")
RESOURCES_DIRECTORY = join(ROOT, "resources")
DEFAULTCONF = join(RESOURCES_DIRECTORY, "defaultConf.json")

COMPONENTS_REPOSITORY = join(RESOURCES_DIRECTORY, 
                            "midgard", 
                            "components", 
                            "components.json")


DEPENDENCIES = join(ROOT, "tools", "strip", "dependencies.json")



IMPORT_RE = re.compile("import\s+(midgard[\.\w+\.\w+]*);")
EXTENDS_RE = re.compile("extends\s+(\w+)[\s+{]")
INTERFACE_EXTENDS_RE = re.compile("extends\s+(\w+[\s*,\s*\w+]*)[\s+{]")
IMPLEMENTS_RE = re.compile("implements\s+(\w+[\s*,\s*\w+]*)[\s+{]")
CLASSES_RE = re.compile("(\w+)")



input_message = """
%(name)s is middleware required?
Type 1: for add interface
Type 2: for add %(name)s,%(proxy)s,%(impl)s
Type 3: for add %(name)s,%(proxy)s
Type 4: for add %(name)s,%(impl)s
Type 5: not add
input: """

def qualifiedname_to_file(clsname):
    return clsname.replace('.',os.path.sep) + ".class"

def file_to_qualifiedname(filename):
    return filename.replace(os.path.sep, ".")[:-len(".java")]

def classname_from_qualifiedname( qualifiedname ):
    return qualifiedname.split('.')[-1]

def qualifiedname_is_interface( name ):
    name = classname_from_qualifiedname( name )
    return classname_is_interface(name)

def classname_is_interface( name ):
    return True if name.startswith('I') else False


def javasrc_from_qualifiedname( name ):
    name = name.replace('.', os.path.sep) + ".java"
    filename = join(SRC_DIRECTORY, name)
    with file(filename) as f:
        return f.read()

def list_package_with_classnames( package ):
    dirname = package.replace(".", os.path.sep)
    dirpath = join( SRC_DIRECTORY, dirname)
    files = os.listdir( dirpath )
    files =  [ f for f in files if f.endswith('.java')]
    classes = [ f[:-len('.java')] for f in files ]
    return classes

def list_package_with_qualifiednames( package ):
    classes = list_package_with_classnames(package)
    qclasses = [ (package + '.' + c) for c in classes ]
    return qclasses

def packagename_from_qualifiedname( qname ):
    return ".".join( qname.split('.')[:-1] )

def file_to_classname(filename):
    return classname_from_qualifiedname( file_to_qualifiedname(filename) )

def get_baseclass_from_filecontent( content ):
    try:
        return EXTENDS_RE.findall(content)[0]
    except IndexError, error:
        return None

def get_baseclass_from_interface_filecontent( content ):
    try:
        ifaces = INTERFACE_EXTENDS_RE.findall(content)[0]
        ifaces = ifaces.replace(',','')
        return CLASSES_RE.findall(ifaces)
    except IndexError, error:
        return []


def get_implementedclasses_from_filecontent( content ):
    try:
        ifaces = IMPLEMENTS_RE.findall(content)[0]
        ifaces = ifaces.replace(',','')
        return CLASSES_RE.findall(ifaces)
    except IndexError, error:
        return []

def get_imports_from_filecontent( content ):
    return IMPORT_RE.findall( content )


def get_javaclass_from_file(filename):
    qualifiedname = file_to_qualifiedname(filename)
    classname = classname_from_qualifiedname( qualifiedname )

def load_dict_from_json(filename):
    try:
        with file(filename) as f:
            dct = simplejson.load(f)
    except (IOError, JSONDecodeError), error:
        print error
        dct = {}

    return dct



class JavaClass(object):

    def __init__(self, qualifiedname):
        self.qualifiedname = qualifiedname
        self.name = classname_from_qualifiedname( qualifiedname )
        self.package = packagename_from_qualifiedname( qualifiedname )
        self.src =  javasrc_from_qualifiedname( qualifiedname  )
        self.is_interface = True if self.name.startswith('I') else False
        self.provides = set()
        self.requires = set()
        self.cyclic_references = set()
        self.load()


    def __str__(self):
        return self.qualifiedname

    __repr__ = __str__

    def load(self):
        self.load_imports()
        self.fix_imports_with_package_elements()

        if not self.is_interface:
            self.load_baseclass()
            self.load_implemented_interfaces()
        else:
            self.load_interface_baseclasses()

        self.load_requires()
        self.load_provides()

    def load_imports(self):
        self.imports = get_imports_from_filecontent( self.src )

    def fix_imports_with_package_elements(self):
        need_package_class = []
        package_classes = list_package_with_classnames( self.package )
        package_classes.remove( self.name )


        for cls in package_classes:

            cls_in_use = " %s " % cls
            if cls_in_use in self.src:
                need_package_class.append(cls)

            cls_in_use = " %s{" % cls
            if cls_in_use in self.src:
                need_package_class.append(cls)

            cls_in_use = " %s," % cls
            if cls_in_use in self.src:
                need_package_class.append(cls)

            cls_in_use = " %s(" % cls
            if cls_in_use in self.src:
                need_package_class.append(cls)

        need_package_class = list(set( need_package_class ))
        qclasses = [ self.package + '.' + c for c in need_package_class ]

        self.imports.extend( qclasses )


    @property
    def proxy_name(self):
        return self.package + "." + self.name[1:]

    @property
    def implementation_name(self):
        return self.package + ".Default" + self.name[1:]

    def load_baseclass(self):
        baseclass = get_baseclass_from_filecontent( self.src )
        if baseclass:
            try:
                self.baseclass = [ qname for qname in self.imports\
                        if baseclass == classname_from_qualifiedname(qname)  ][0]
            except IndexError, error:
                self.baseclass = None
        else:
            self.baseclass = None

    def load_implemented_interfaces(self):
        clsnames = get_implementedclasses_from_filecontent( self.src )
        implemented_interfaces = []
        for qname in self.imports:
            for clsname in clsnames:
                if classname_from_qualifiedname(qname) == clsname:
                    implemented_interfaces.append( qname)


        self.implemented_interfaces = implemented_interfaces


    def load_interface_baseclasses(self):
        clsnames = get_baseclass_from_interface_filecontent( self.src )
        bases = []
        for qname in self.imports:
            for clsname in clsnames:
                if classname_from_qualifiedname(qname) == clsname:
                    bases.append( qname)


        self.baseclass = bases


    def load_requires(self):
        self.requires.update(self.imports)

    def load_provides(self):
        if not self.is_interface:
            self.provides.update(self.implemented_interfaces)
        else:
            self.provides.update(self.baseclass)

    @property
    def is_root(self):
        if self.is_interface:
            return not self.baseclass
        else:
            return not (self.baseclass or self.requires)


    def is_resolved(self, context, only_interfaces=False, cycles=None):
        count = 0

        requires = list(self.requires)

        if cycles:
            for a,b in cycles:
                if a is self:
                    self.cyclic_references.add(b)
                elif b is self:
                    self.cyclic_references.add(a)
                else:
                    pass

        for cyclic in self.cyclic_references:
            requires.remove(cyclic.qualifiedname)

        if only_interfaces:
            requires = [ r for r in requires if qualifiedname_is_interface( r ) ]

        for entity in context:
            if entity.qualifiedname in requires:
                count += 1


        return count == len(requires)


    def update_provides_from_context(self, context, only_interfaces=False):

        for interface in list(self.provides):
            iface = context[interface]
            self.provides.update( iface.provides )

        if not only_interfaces and self.baseclass:
            baseclass = context[self.baseclass]
            self.provides.update( baseclass.provides )


    def update_requires_from_context(self, context ):

        requires = list(self.requires)
        for required in requires:
            entity = context[required]
            self.requires.update( entity.requires )



class Stripper(object):

    def __init__(self):
        self.default_conf = load_dict_from_json(DEFAULTCONF)

        self.middleware_dependencies = load_dict_from_json(DEPENDENCIES)

        self.app_conf = self.open_app_conf_file()

        self.adaptations_repository =  self.open_adaptation_repository_file()
        print self.adaptations_repository

        self.classes = self.build_dependencies_dict()
        self.dont_strip = set()


    def open_app_conf_file(self):
        filename = RESOURCES_DIRECTORY + self.default_conf["AppRepositoryPath"]
        return load_dict_from_json(filename)

    def open_adaptation_repository_file(self):
        if "AdaptationProfileRepositoryPath" in self.default_conf:
            filename = RESOURCES_DIRECTORY + self.default_conf["AdaptationProfileRepositoryPath"]
            return load_dict_from_json(filename)
        return {}


    def run(self):
        self.load_apps_conf()
        self.load_adaptation_profile()
        self.load_midgard_deps()

        classes_to_remove = set(self.classes) - self.dont_strip

        for cls in classes_to_remove:
            self.remove_class(cls)


    def remove_class(self, name):
        fname = qualifiedname_to_file(name)
        fpath = join( TARGET_DIRECTORY, fname)
        print "vou remover",fpath,os.path.exists(fpath)
        os.remove(fpath)



    def load_apps_conf(self):
        for app in self.app_conf["apps"].values():
            self.dont_strip.add(  self.add_deps_of( app["class"] )) 

            for sensor_interface in app.get("sensors", []):
                self.add_deps_of(sensor_interface)


        for name in ["services", "tasks", "customEvents",
                     "adaptationProfiles", "webApplications", "dontStrip"]:

            for item in self.app_conf.get(name, []):
                self.add_deps_of(item)


    def load_adaptation_profile(self):
        for profile_name in self.adaptations_repository:
            profile = self.adaptations_repository[profile_name]
            print profile
            for action_name in profile['actions']:
                action = profile['actions'][action_name]
                call = action['call']
                try:
                    self.add_deps_of( call['param'] )

                    if call["command"] == "fireEvent":
                        self.add_deps_of( call['type'] )

                    self.add_deps_of( call['param2'] )
                except KeyError, error:
                    pass


    def load_midgard_deps(self):
        for component in self.middleware_dependencies:
            data = self.middleware_dependencies[component]
            self.dont_strip.update( data['requires'] )
            self.dont_strip.update( data['provides'] )
            self.dont_strip.add(component)

    def add_deps_of(self, name):
        try:
            self.dont_strip.add(name)
            self.dont_strip.update( self.get_dependencies_of(name) )
        except KeyError, error:
            pass

    def get_dependencies_of(self, name):
        cls =  self.classes[name]
        deps = set(cls.requires)

        if cls.is_interface:
            proxy = self.classes.get(cls.proxy_name, None)
            impl = self.classes.get(cls.implementation_name, None)
            if proxy:
                deps.add( cls.proxy_name )
                deps.update(proxy.requires)
            if impl:
                deps.add( cls.implementation_name )
                deps.update(impl.requires)

        return list(deps)

    def debug(self):
        pprint.pprint( self.dont_strip )


    def _get_implementations_of_interface(self, iface):
        return set( c.qualifiedname for c in self.classes.values()\
                if iface in c.provides and not c.is_interface )

    def generate_components_repository(self, fname=COMPONENTS_REPOSITORY):
        result = {}
        interfaces = [ i for i in self.classes.values() if i.is_interface ] 

        for iface in interfaces:
            dct = {}
            result[iface.qualifiedname] = dct

            proxyname = iface.proxy_name
            if proxyname in self.classes:
                dct['proxy'] = proxyname

            implname = iface.implementation_name

            impls = []
            dct['implementations'] = impls
            full_implementations =\
                    list(self._get_implementations_of_interface(iface.qualifiedname))

            if implname in self.classes:
                impls.append(implname)
                full_implementations.remove(implname)


            impls.extend( full_implementations )

        with file(fname, "w") as f:
            simplejson.dump( result, f)



    def generate_middleware_dependencies(self, filename=DEPENDENCIES):
        result = {}
        visited = set()
        rejecteds = set()
        allclasses = self.classes.values()
        interfaces = [ c for c in allclasses if c.is_interface ]
        classes = [ c for c in allclasses if not c.is_interface ]
        classes.sort(key=lambda x: x.qualifiedname)
        interfaces.sort(key=lambda x: x.qualifiedname)

        allclasses = interfaces + classes


        def visit(name):
            visited.add(name)
            visited.update( self.classes[name].requires  )
            visited.update( self.classes[name].provides  )

        for cls in allclasses:

            option = ""

            name = cls.qualifiedname
            if name in visited or name in rejecteds:
                continue

            while option not in ['1','2','3','4','5']:


                if cls.is_interface:
                    proxy = self.classes.get(cls.proxy_name, "not exist")
                    impl = self.classes.get(cls.implementation_name, "not exist")


                    option = raw_input(input_message % locals())
                    try:
                        if option == '1':
                            visit(name)
                        if option == '2':
                            visit(name)
                            visit(proxy.qualifiedname)
                            visit(impl.qualifiedname)
                        if option == '3':
                            visit(name)
                            visit(proxy.qualifiedname)
                        if option == '4':
                            visit(name)
                            visit(impl.qualifiedname)
                        if option == '5':
                            rejecteds.add(cls.proxy_name)
                            rejecteds.add(cls.implementation_name)
                    except AttributeError, error:
                        option = 'error'
                else:
                    option = raw_input("%(name)s is middleware required?[1=yes;[2,3,4,5]=no:" % locals())
                    if option == '1':
                        visit(name)

                

        for clsname in visited:
            cls = self.classes[clsname]
            result[cls.qualifiedname] = {
                    "requires" : list(cls.requires),
                    "provides" : list(cls.provides)
            }

        with file(filename, "w") as f:
            simplejson.dump( result, f)


    def _get_all_classes(self):
        all_classes = {}

        for dirpath, dirnames, filenames in os.walk(SRC_DIRECTORY):

            for f in filenames:
                fpath = join(dirpath, f)[len(SRC_DIRECTORY) + 1:]


                if not fpath.endswith(".java"):
                    continue

                classname = file_to_qualifiedname(fpath)

                if '$' in classname:
                    continue
                all_classes[classname] =  JavaClass(classname)

        return all_classes


    def _detect_cyclic_references(self, context):
        refs = []
        mark = []

        classes = context.values()
        iterator = list(classes)

        def mark_cycle(a,b):
            mark.append((a,b))
            mark.append((b,a))

        def verify_mark(a, b):
            return (a,b) in mark or (b,a) in mark


        for cls in iterator:
            for required in cls.requires:
                other = context[required]
                if cls.qualifiedname in other.requires and\
                        other.qualifiedname in cls.requires and\
                        not verify_mark(cls, other):
                    refs.append( (cls, other) )
                    mark_cycle(cls, other  )

#        for cls in context.values():
#            for other in context.values():
#                if cls.qualifiedname in other.requires and\
#                        other.qualifiedname in cls.requires and\
#                        not cls in mark:
#                    refs.append( (cls, other) )
#                    mark.append( cls )
#                    mark.append( other )


        return refs




    def build_dependencies_dict(self):

        all_classes = self._get_all_classes()
        cycles = self._detect_cyclic_references(all_classes)

        interfaces = [ i for i in all_classes.values() if i.is_interface  ]
        root_interfaces = [ i for i in interfaces if i.is_root  ]  
        resolved_interfaces = list(root_interfaces)
        
        pending_interfaces = [ i for i in interfaces if i not in resolved_interfaces  ]

        while pending_interfaces: 

            for i in list(pending_interfaces):

                if i.is_resolved( resolved_interfaces, only_interfaces=True ):
                    pending_interfaces.remove(i)
                    resolved_interfaces.append(i)
                    i.update_provides_from_context( all_classes, only_interfaces=True )


        only_classes = [ c for c in all_classes.values() if not c.is_interface  ]


        for cls in only_classes:
            cls.update_provides_from_context( all_classes )

        root_classes = [ c for c in only_classes if c.is_root  ]  

        resolved_classes = list(root_classes) + list(resolved_interfaces)
        pending_classes = [ c for c in only_classes if c not in resolved_classes  ]

        while pending_classes: 


            for c in list(pending_classes):

                if c.is_resolved( resolved_classes, cycles=cycles):
                    pending_classes.remove(c)
                    resolved_classes.append(c)
                    c.update_provides_from_context( all_classes, only_interfaces=False )
                    c.update_requires_from_context( all_classes )


        for i in interfaces:
            i.update_requires_from_context(all_classes)


        #FIX ER expression for accept dotted name interface
        defaultLightSensor = all_classes['midgard.sensors.light.DefaultLightSensor']
        defaultLightSensor.implemented_interfaces.append('midgard.sensors.light.ILightSensor')
        defaultLightSensor.load_provides()
        defaultLightSensor.update_provides_from_context(all_classes)


        return all_classes


if __name__ == "__main__":
    Stripper().run()
    #Stripper().generate_middleware_dependencies()
