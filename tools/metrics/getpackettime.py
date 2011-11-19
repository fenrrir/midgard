#!/usr/bin/env python
# -*- coding: UTF-8 -*-

import sys

class Analyzer(object):

    MEMORY_STATE_CONTEXT = 1
    MEMORY_STATE_USED = 2
    MEMORY_STATE_END = 3


    def __init__(self, filename):
        self.filename = filename
        self.file = file(filename)
        self.measures = []

        self.middleware_initial_memory_state = 0
        self.app_initial_memory_state = 0
        self.memory_usage_state = 0

        self.middleware_initial_memory = 0
        self.app_initial_memory = 0
        self.memory_usage = 0


    def run(self):
        try:
            for line in self.file:
                self.process_line(line)

            self.show_statistics()

        finally:
            self.file.close()

    def process_line(self, line):
        self.get_packet_time(line)
        self.get_middleware_initial_memory(line)
        self.get_app_initial_memory(line)
        self.get_memory_usage(line)


    def show_statistics(self):
        print self.filename
        self.show_packet_time()
        self.show_middleware_initial_memory()
        self.show_app_initial_memory()
        self.show_memory_usage()



    def get_packet_time(self, line):
        if "PacketTime:" in line:
            measure = int(line.split()[-1])
            self.measures.append(measure)


    def get_middleware_initial_memory(self, line):
        if "Context=before boot" in line:
            self.middleware_initial_memory_state = self.MEMORY_STATE_CONTEXT
        if self.middleware_initial_memory_state == self.MEMORY_STATE_CONTEXT\
                and "Used memory=" in line:
            data = int( line.split()[-1].split('=')[-1] )
            self.middleware_initial_memory = data
            self.middleware_initial_memory_state = self.MEMORY_STATE_END
            
    def get_app_initial_memory(self, line):
        if "Context=before boot" in line:
            self.app_initial_memory_state = self.MEMORY_STATE_CONTEXT
        if self.app_initial_memory_state == self.MEMORY_STATE_CONTEXT\
                and "Used memory=" in line:
            data = int( line.split()[-1].split('=')[-1] )
            self.app_initial_memory = data
            self.app_initial_memory_state = self.MEMORY_STATE_END


    def get_memory_usage(self, line):
        if "Context=after life cycle" in line:
            self.memory_usage_state = self.MEMORY_STATE_CONTEXT
        if self.memory_usage_state == self.MEMORY_STATE_CONTEXT\
                and "Used memory=" in line:
            data = int( line.split()[-1].split('=')[-1] )
            self.memory_usage = data



    def show_packet_time(self):
        measures = self.measures
        print "\tpackets(n):",len(measures)
        print "\tavg packet time:",sum(measures)/len(measures),"ms"


    def show_middleware_initial_memory(self):
        print "\tmiddleware initial memory:",self.middleware_initial_memory/1000.0,"kb"

    def show_app_initial_memory(self):
        print "\tapp initial memory",self.app_initial_memory/1000.0,"kb"

    def show_memory_usage(self):
        print "\tmemory usage",self.memory_usage/1000.0,"kb"



def main(filenames):
    for filename in filenames:
        Analyzer(filename).run()


if __name__ == "__main__":
    main(sys.argv[1:])


