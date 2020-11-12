#!/bin/bash
cat > settings.gradle << EOF
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
rootProject.name = "Luposdate3000"
EOF
for f in src/lupos*
do
echo "include(\"$f\")" >> settings.gradle
done
