#!/bin/bash
dd if=/dev/zero of=/opt/tmpdata bs=4k count=1000000
mkdir /opt/tmpfs
mkfs -t ext3 /opt/tmpdata
mount -t ext3 -o loop /opt/tmpdata /opt/tmpfs
