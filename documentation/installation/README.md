## Installation

You can copy paste execute the following script.
However, there are some commands (with comments), which will run until your disk-space is consumed.
Make sure you read the following comments.

```bash
# The basic dependencies which can be installed via package manager apt.
# Yes, there are 3 versions of java ... because the dependencies dont like the wrong one.
# Sadly latex installs a complete gui for linux - even on server distributions.
apt install docker docker-compose docker.io g++ gnuplot gzip htop lcov make maven net-tools ntfs-3g openjdk-8-jdk openjdk-11-jdk openjdk-14-jdk unzip zip poppler-utils texlive texlive-latex-extra p7zip-full libncurses5

# Unfortunately the ifis-git uses incorrect ssl such that the following git option must be used.
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
    # you may chech first if the head commit is working, otherwise the following commit worked for me
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
    ${luposdate3000home}/compile-module-korio.sh
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
