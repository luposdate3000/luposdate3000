#!/bin/bash
cinterop -target linux -def posix.def -o platform.posix.klib
klib install platform.posix.klib
