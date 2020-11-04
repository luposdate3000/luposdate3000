# luposdate3000

luposdate3000 is a Database which can process sparql-queries.

## Installation

You can copy paste execute the following script.
However, there are some commands (with comments), which will run until your disk-space is consumed.
Make sure you read the following comments.

```bash
# The basic dependencies which can be installed via package manager apt.
# Yes, there are 3 versions of java ... because the dependencies dont like the wrong one.
# Sadly latex installs a complete gui for linux - even on server distributions.
apt install docker docker-compose docker.io g++ gnuplot gzip htop lcov make maven net-tools ntfs-3g openjdk-8-jdk openjdk-11-jdk openjdk-14-jdk unzip zip poppler-utils texlive texlive-latex-extra p7zip-full libncurses5

# Unfortunately the ifis-git uses incorrect ssl such that thefollowing git option must be used.
git config --global http.sslVerify false
# Prevent repeatingly typing the password on commit.
git config --global credential.helper store
git clone https://sun01.pool.ifis.uni-luebeck.de/groppe/luposdate3000.git

#Lets safe the cloned directory path in a variable such that we can refer to this later in the documentation.
luposdate3000home=$(echo "$(pwd)/luposdate3000" | sed "s-luposdate3000.*-luposdate3000/-g")

#Define the folder, where to download everything else.
dependencieshome=/opt

#Now the components are installed one by one.

#gradle
{
    # Using the latest gradle version to enable all kotlin-features.
    cd $dependencieshome
    git clone https://github.com/gradle/gradle.git
    cd gradle
    export JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64/"
    ./gradlew install -Pgradle_installPath=/usr/local/gradle-source-build
}
#kotlin
{
    # Compile from source to get a up to date version - not every version in their githup compiles, but the following should work.
    cd $dependencieshome
    git clone https://github.com/JetBrains/kotlin.git
    cd kotlin
    git checkout 3a5ffe479eb43f53db55b33280cbdca72bf23dc8
    export JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64/"
    export JDK_16="/usr/lib/jvm/java-8-openjdk-amd64/"
    export JDK_17="/usr/lib/jvm/java-8-openjdk-amd64/"
    export JDK_18="/usr/lib/jvm/java-8-openjdk-amd64/"
    export JDK_9="/usr/lib/jvm/java-14-openjdk-amd64/"
    ./gradlew install
    ./gradlew dist
    ln -s $dependencieshome/kotlin/dist/kotlinc/bin/kotlinc /bin/kotlinc
    ln -s $dependencieshome/kotlin/dist/kotlinc/bin/kotlin /bin/kotlin
}
#kscript
{
    # Massive speed increase for each *.kts script, because the script only compiles once, and not on every use.
    cd $dependencieshome
    git clone https://github.com/holgerbrandl/kscript.git
    cd kscript/
    export JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64/"
    ./gradlew assemble
    ln -s $dependencieshome/kscript/build/libs/kscript /bin/kscript
}
#proguard
{
    # Currently unused ... but planned to be used to shrink and optimize the library.
    cd $dependencieshome
    git clone https://github.com/Guardsquare/proguard.git
    cd proguard
    gradle assemble
    cd build/distributions
    tar -xzf proguard-7.0.0.tar.gz
    ln -s $dependencieshome/proguard/build/distributions/proguard-7.0.0/bin/proguard.sh /bin/proguard
}
#korio
{
    # Currently unused ... because of massive performance problems during communication.
    ${luposdate3000home}/tool-build-korio.sh
}
#intellij
{
    # Used to format the source code from the commandline.
    cd $dependencieshome
    wget https://download.jetbrains.com/idea/ideaIC-2020.1.2.tar.gz
    tar -xzf ideaIC-2020.1.2.tar.gz
    rm ideaIC-2020.1.2.tar.gz
    cd idea-IC-201.7846.76/bin
    ./idea.sh
    # intellij needs to be launched once to confirm basic settings.
    # During the above installations, the gui is installed anyway.
}

#The following are the dependencies for the benchmarks.

#bsbm
{
    cd $dependencieshome
    wget https://ayera.dl.sourceforge.net/project/bsbmtools/bsbmtools/bsbmtools-0.2/bsbmtools-v0.2.zip
    unzip bsbmtools-v0.2.zip
    rm bsbmtools-v0.2.zip
    cd bsbmtools-0.2
    git init .
    git add .
    git commit -m a
    # The following line runs until your disk space is completely consumed.
    # Make sure to abort it if you have enough benchmark data.
    ${luposdate3000home}/exec-benchmark-generate-bsbm.sh
}
#sp2b
{
    cd $dependencieshome
    wget http://dbis.informatik.uni-freiburg.de/content/projects/SP2B/docs/sp2b-v1_00-full.tar.gz
    tar -xzf sp2b-v1_00-full.tar.gz
    rm sp2b-v1_00-full.tar.gz
    cd sp2b
    git init .
    git add .
    git commit -m a
    git apply ${luposdate3000home}/documentation/installation/sp2b.patch
    # The following line runs until your disk space is completely consumed.
    # Make sure to abort it if you have enough benchmark data.
    ${luposdate3000home}/exec-benchmark-generate-sp2b.sh
}
#btc2019
{
    cd /mnt/luposdate-testdata
    mkdir btc2019
    cd btc2019
    wget https://zenodo.org/record/2634588/files/btc2019-triples.nt.gz?download=1 btc2019-triples.nt.gz
    gunzip btc2019-triples.nt.gz
}
#btc2010
{
    cd /mnt/luposdate-testdata
    mkdir btc2010
    cd btc2010
    wget https://km.aifb.kit.edu/projects/btc-2010/000-CONTENTS
    # This one is about 600 gigabytes.
    # Make sure you have enough space available and a good enough internet connection.
    wget -i 000-CONTENTS
}
#yago2
{
    cd /mnt/luposdate-testdata
    mkdir yago2
    cd yago2
    wget https://yago-knowledge.org/data/yago2/yago-2.3.0-turtle.7z
    7z x yago-2.3.0-turtle.7z
    rm yago-2.3.0-turtle.7z statistics.txt
}
#barton
{
    cd /mnt/luposdate-testdata
    mkdir barton
    cd barton
    wget http://dslam.cs.umd.edu/data/barton/barton.mods.rdf.tar.gz
    tar -xzf barton.mods.rdf.tar.gz
    rm barton.mods.rdf.tar.gz
}
#virtuoso
{
    apt install autoconf automake libtool flex bison gperf gawk m4 make libssl-dev
    cd $dependencieshome
    git clone git://github.com/openlink/virtuoso-opensource.git --depth=1 virtuoso
    cd virtuoso
    ./autogen.sh
    CFLAGS="-O2 -m64" ./configure --prefix=$dependencieshome/virtuoso-dist
    make -j8
    make install
}
```

## Usage

To compile everything the first time you must perform a complete rebuild, which is done by using:

```bash
./compile-module-korio.sh
./compile-module-all.sh
```

This takes a lot of time (on my computer 20 Minutes using a 4GHz CPU with NVME-SSD and lots of available RAM).
The final compiled jars/js/native-libs are than in the folder build-cache/bin

Take a look into the file ./compile-module-all.sh.
If you change somethin, compile only the necessary module (and the ones depending on it) instead of everything.
If you only need the jars you can append "--fast" to the calls to "./generate-buildfile-module.kts".
If you change the interface of a module you can not use "--fast", because then the medatada is not generated correctly.

Compiling just a single Module for JVM only takes around 10 Seconds - which is much faster.

### Tests

To run all tests use the following:

```bash
exec-binary-test-suite-jvm.sh
```

These tests are executed repeatedly and therefore these should be compileable and executable.
Currently luposdate3000 is not finished - thats why there are lots of failing tests.
The test script writes a summary to the file resources/binary/config2.
The comparison to the file resources/binary/config should show if there are new tests working.
You can modify resources/binary/config to enable/disable specific tests.
The found errors are summarized in the file log/error.

luposdate3000 allows for many configuration options where completely independent code is used.

Currently the only tests are integration tests using complete sparql-queries at once.
To gain usefull insight, what breakes when, the object "lupos.s00misc.SanityCheck" provides assertion functions, which are included in debug-build, but not in release-build.

To add a new testcase::

```bash
inputdata=xyz/file.n3
sparql=xyz/file.sparql
targetdata=xyz/file.n3
outputfoldername=xyz
testname=xyz
mode=SELECT_QUERY_RESULT  # or mode=MODIFY_RESULT
java -Xmx60g -cp $(printf %s: $(pwd)/build-cache/bin-effective/*.jar) MainKt --generate "$inputdata" "$sparql" "$targetdata" "resources/binary/$outputfoldername" "$testname" "$mode"
echo "$outputfoldername=enabled" >> resources/binary/config
```

### Benchmarks

The benchmarks are updated soon ...

The script

```bash
exec-benchmark-luposdate3000-internal.sh
```

runs the sp2b and bsbm benchmarks and puts the results into the folder "benchmark_results".
Alternatively the script

```bash
exec-benchmark-luposdate3000-internal-paper.sh
```

may also be used, which executed different benchmarks.
You may want to change the targetfolder to something else to get a usefull history.
It is unlikely that your hardware matches the one used to create the current benchmark values.
The current git revision is part of the benchmark-result-filename, to be able to exactly compare which code-changes changes the performance.

### Configuration

You can configure the database as you want.

Take a look into "compile-module-all.sh"
There you can change the parameters for the indiviual modules.

The options "--nosuspend" or "--suspend" must be the same for EVERY module, because this changes method signatures in the binarys.

All configurable options can be found in the files src/*/configOptions
These csv files columns are: optionName,optionType(const val/val/typedef),variableType(Int,...),defaultValue

Additionally you can exchange some modules.
Currently these exchangeable Modules are:
* luposdate3000_endpoint_none or luposdate3000_endpoint_java_sockets
* luposdate3000_jena_wrapper_on or luposdate3000_jena_wrapper_off
* one of luposdate3000_launch*

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

### hints for performant kotlin code

Things you can change:

* "import java.xyz"<br/>
  Only use java-only code within src/jvm* and never within src/common.
  Because jvm related code will not compile on native.
* "import xyz.\*"<br/>
  Dont use the \* in imports, always include exactly what you want.
  Because otherwise the compilation is very very slow.
* "inline" function modifier<br/>
  The compiler copy paste the byte-code into the calling function.
  This modifier is applied recoursively - therefore the function byte-code may explode in size.
  Use this when the code is performance critical.
  There is a limit of 64kb byte-code per function - this is very easy to reach if you put inline everywhere.
  This modifier should NEVER be used during debugging, because it breaks the exception-stack-traces.
  if you have functional parameters combine it with "crossinline" to inline that parameter too.
* "internal" function/class modifier <br/>
  This hides the function/class from the api.
  This decreases the binary size.
  This increases the compile-speed, because there are no generated/exported functions
* function pointers<br/>
  Be careful.
  This creates (multiple) additional objects.
  If these are called, multiple indirect function calls are performed, which may be slow.
  Dont do this in performance-critical sections.
  The kotlin way to to this is a function with a "when(xyz)" where each case contains the code you otherwise would have put into different function pointers.
* coroutines<br/>
  If you have blocking code for example when you need a Lock, than recursively put the "suspend" keyword in front of every required function - the whole way up until "main" if necessary.
  Do the same for every function, which calles any of the functions you modify.
  Do NOT use "runBlocking" - because it does just that blocking.
  Use "runBlocking" only if you are really forced to do so by an external library.
* "suspend" function modifier<br/>
  The compiler performs a lot of changes to functions with this keyword.
  Try to avoid any function-local variable ... and put that as a instance-variable instead - because otherwise all these local variables are transformet into a temporary object by the kotlin compiler - which is bad.
  If a suspend function does not contain any suspend code, the compiler changes are minimal.
  Try to call other suspending functions as late as possible within a function, to reduce the number of variabes which must be stored by the compiler.
* debug-only-code<br/>
  use this:
  
  ```kotlin
    SanityCheck{
       debug-code here
    }
  ```
  
  than everything inside that section is removed completely from the release-build
* "override" function modifier<br/>
  Every function with this modifier can NOT be inlined, and is therefore limited in performance.
  Use it only, when there are different implementations at Runtime.
  If during runtime only one version is used, than use typealias, to select the effective class.
* "nullable" datatypes<br/>
  Introduce boxing if the datatype otherwise is a primitive such as Int.
  Especially in performance-critical code try to avoid it.
  If you already use a variable of an object type, this should not introduce additional overhead.
* "constant values"<br/>
  Primitive global constants should be defined like "const val x = 42"
  Strings are "primitives" in this context.
  Everything which is not primitive may at least use "val" instead of "var"
* "class-variables" and "instance-variables"<br/>
  Annotate all of these with "@JvmField" - otherwise the kotlin compiler generates useless getter and setter code.

Current limitations of the kotlin compiler:

* the jvm-target generates the "fastest" code
* at time of testing ... the native target is approximately 20 times slower
* native target calls freeze on EVERYTHING
  that means, that any global variable needs a full replication on every modification which is very very bad.
  this should change with upcoming kotlin-releases
* gradle has more kotlin related features than maven especially if not-java targets should be build
* If the program breaks and you dont think it should break, than clear the build folder and compile again.
  The incremental build sometimes just dont work - especially with enabled inlining.
* My experiments show that Threads are faster than Coroutines, because of the constantly allocated and released intermediate Objects, which are completely useless.
  Maybe this changes with updates to the kotlin-compiler.
  Currently: just use Threads.

## License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0)
