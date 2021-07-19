# Installation of the dependencies

## Linux

```bash
apt install openjdk-8-jdk-headless unzip g++ python2 nodejs
git clone https://github.com/luposdate3000/luposdate3000.git

#Define the folder, where to download everything else.
dependencieshome=/opt

#bignum
{
    cd $dependencieshome
    git clone https://github.com/ionspin/kotlin-multiplatform-bignum.git
    cd kotlin-multiplatform-bignum/bignum
    #patch the buildfile to make it executable as JS in Browsers
    sed 's/.*it.compileKotlinTask.kotlinOptions.moduleKind = "commonjs"//g' -i build.gradle.kts
    sed 's/if.*primaryDevelopment.*{/if (true) {/g' -i build.gradle.kts
    sed 's/version.*=.*/version = "0.3.1-SNAPSHOT"/g' -i build.gradle.kts
    cd ..
    ./gradlew publishToMavenLocal
}
#kotlin
{
    cd $dependencieshome
    wget https://github.com/JetBrains/kotlin/releases/download/v1.5.10/kotlin-compiler-1.5.10.zip
    unzip kotlin-compiler-1.5.10.zip
    cd kotlinc/bin/
    echo "export PATH=$PATH:$(pwd)" >> ~/.bashrc
}

```


## Windows

### git-bash

Download and install git-bash from https://git-scm.com/download/win .

### java

Install any Java version of at least java 8
For Example:

* Download and unpack java 15 from (openjdk-15.0.1_windows-x64_bin.zip)[https://download.java.net/java/GA/jdk15.0.1/51f4f36ad4ef43e39d0dfdbaf6549e32/9/GPL/openjdk-15.0.1_windows-x64_bin.zip] .
* This download wants you to login (create an user) - there are workarounds in the internet, if you want to avoid creating credentials for that website.
  Download and install java 8 from (jdk-8u271-windows-x64.exe)[https://download.oracle.com/otn-pub/java/jdk/8u271-b09/61ae65e088624f5aaa0b1d2d801acb16/jdk-8u271-windows-x64.exe] .

restart gitbash, if it is already open

### kotlin-compiler

Download (kotlin-compiler-1.4.32.zip)[https://github.com/JetBrains/kotlin/releases/download/v1.5.10/kotlin-compiler-1.5.10.zip]
Extract the compiler to its final destination, for Example somewhere in the Programs-Folder
Add the compiler to the Path.
The compiler-path looks something like "C:/.../kotlinc/bin"

restart gitbash, if it is already open

### additional dependencies for the spa-client
https://nodejs.org/dist/v14.17.0/node-v14.17.0-x64.msi
https://www.python.org/ftp/python/2.7.18/python-2.7.18.amd64.msi

Add this to your path (modify them as needed)
"/c/Python27"
This directory must contain a "python2.exe". If there is no python2.exe, than copy paste the "python.exe" and rename it.

copy paste
C:\Program Files (x86)\Microsoft Visual Studio\2019\BuildTools\MSBuild\Current
to
C:\Program Files (x86)\Microsoft Visual Studio\2019\BuildTools\MSBuild\15.0
because of an bug in node-gyp, which uses the wrong path


### long-file-paths

enable windows long file paths (more than 260 chars)
This is required for working with the JS target due to how the compiler works.
open start-search
type "regedit"
open the suggested program
navigate to "HKEY_LOCAL_MACHINE\SYSTEM\ControlSet001\Control\FileSystem"
change the value of LongPathsEnabled to 1

you must reboot now to make that change effective

restart gitbash, if it is still open - this should be closed after the reboot

###

```gitbash
git clone https://github.com/luposdate3000/luposdate3000.git

#install bignum
{ 
    git clone https://github.com/ionspin/kotlin-multiplatform-bignum.git
    cd kotlin-multiplatform-bignum/bignum
    #patch the buildfile to make it executable as JS in Browsers
    sed 's/.*it.compileKotlinTask.kotlinOptions.moduleKind = "commonjs"//g' -i build.gradle.kts
    sed 's/if.*primaryDevelopment.*{/if (true) {/g' -i build.gradle.kts
    sed 's/version.*=.*/version = "0.3.1-SNAPSHOT"/g' -i build.gradle.kts
    cd ..
    ./gradlew publishToMavenLocal
}

```

### set some environment variables
* "LUPOS_RAM"
  specify the available memory in GB. Keep in mind, that the operating system (or other programs) may need some space too.
* "LUPOS_BUFFER_SIZE"
  used for internall buffers during importing data. Set this to at most 5% of your available RAM


# starting the webserver

First, move to the project directory.

To Compile the SPAClient on Windows use

```gitbash
./launcher.main.kts --setupIntellijIdea --target=JVM_JS
./gradlew build
./launcher.main.kts --setupSPAClient
# this will crash - maybe not during the compilation, but definetly during execution
# download the file
# https://github.com/nbrosowsky/tonejs-instruments/archive/8ec9f43d6f07fdeb15e684df5a6c7efa2c3eedf6.tar.gz
# yourself, and extract it to
# ./src/luposdate3000_spa_client/node_modules/tonejs-instruments#8ec9f43d6f07fdeb15e684df5a6c7efa2c3eedf6
./launcher.main.kts --setupSPAClient --dryMode=Enable
```

On linux the following is enough

```gitbash
./launcher.main.kts --setupIntellijIdea --target=JVM_JS
./gradlew build
./launcher.main.kts --setupSPAClient
```

To launch the database with the endpoint use

```gitbash
./launcher.main.kts --run
```


which will provide the Database on localhost:80/index.html
