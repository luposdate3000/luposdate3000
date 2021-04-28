#!/bin/bash
# This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
# Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, version 3.
#
# This program is distributed in the hope that it will be useful, but
# WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program. If not, see <http://www.gnu.org/licenses/>.
#./launcher.main.kts --run --mainClass=Endpoint --endpointMode=Java_Sockets --inlineMode=Enable --releaseMode=Enable --dryMode=Enable
rm config.pro
for f in $(./launcher.main.kts --run --mainClass=Endpoint --endpointMode=Java_Sockets --inlineMode=Enable --releaseMode=Enable --dryMode=Enable | tr ":" "\n" | tr " " "\n" | sort | grep "^build-cache.*\.jar$")
do
echo "-injars ./$f" >> config.pro
done
for f in $(./launcher.main.kts --run --mainClass=Endpoint --endpointMode=Java_Sockets --inlineMode=Enable --releaseMode=Enable --dryMode=Enable | tr ":" "\n" | tr " " "\n" | sort | grep "^/root.*\.jar$")
do
echo "-libraryjars $f" >> config.pro
done
echo "-outjars find-leak.jar" >> config.pro
echo '-libraryjars /usr/lib/jvm/java-1.11.0-openjdk-amd64/jmods/java.base.jmod(!**.jar;!module-info.class)' >>config.pro
cat <<EOT >> config.pro
-forceprocessing
-optimizationpasses 5
-allowaccessmodification
-dontobfuscate
-printmapping find-leak.mapping.txt
-printconfiguration find-leak.config.pro.generated
-printusage find-leak.usage.txt
-keep public class lupos.endpoint.LuposdateEndpoint{
    public String importIntermediateFiles(String);
}
-ignorewarnings
EOT
/usr/lib/jvm/java-1.11.0-openjdk-amd64/bin/java -jar /usr/share/java/proguard.jar @config.pro > find-leak.log 2>&1
