#!/usr/bin/env python
# -*- coding: UTF-8 -*-

import os
import sys

NETBEANS_HEADER="""/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */"""


def get_header():
    dirname = os.path.dirname(__file__)
    headerfile = os.path.join(dirname, "header.txt")

    with file(headerfile) as f:
        content = f.read()

    return content


HEADER = get_header()

def add_license(filename):

    if not filename.endswith(".java"):
        return

    with file(filename) as f:
        content = f.read()

    if content.startswith(NETBEANS_HEADER):
        print filename
        content = content.split("\n")[4:] # remove header
        content = "\n".join(content)
        newcontent = HEADER + content

        y_or_n = raw_input("confirm %s ?" % filename)

        if y_or_n == "y":
            with file(filename, "w") as f:
                f.write(newcontent)



def main():

    for root, dirs, files in os.walk(sys.argv[1]):
        for filename in files:

            fullfilename = os.path.join(root, filename)
            add_license(fullfilename)


if __name__ == "__main__":
    main()
