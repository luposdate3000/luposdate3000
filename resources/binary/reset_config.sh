#!/bin/bash
ls resources/binary/ | grep -v "config" | sort -n | sed "s/$/=enabled/g" > resources/binary/config
