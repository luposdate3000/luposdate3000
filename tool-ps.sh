#!/bin/bash
ps -aux | grep -vF " [" | grep -v " /usr" | grep -v " /lib"| grep -v " /sbin" | grep -v "postgres" | grep -v "avahi-daemon"
