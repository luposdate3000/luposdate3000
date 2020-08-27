#!/bin/bash
ps -aux | grep -vF " [" | grep -v " /lib"| grep -v " /sbin" | grep -v "postgres" | grep -v "avahi-daemon" | grep -vw "gdm" | grep -v "/usr/sbin"
