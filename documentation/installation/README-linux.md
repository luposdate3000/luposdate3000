## Installation on Linux

You can copy paste execute the following script.
Make sure you read the following comments as they may provide useful information.

```bash
# The basic dependencies which can be installed via package manager apt.
# Yes, there are 2 versions of java ... because the dependencies dont like the wrong one.
# Sadly latex installs a complete gui for linux - even on server distributions.
apt install docker docker-compose docker.io g++ gnuplot gzip htop lcov make maven net-tools ntfs-3g openjdk-8-jdk openjdk-14-jdk unzip zip poppler-utils texlive texlive-latex-extra p7zip-full libncurses5

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

#kotlin
{
    # Compile from source to get a up to date version - not every version in their githup compiles, but the following should work.
    cd $dependencieshome
    git clone https://github.com/JetBrains/kotlin.git
    cd kotlin
    # you may check first if the head commit is working, otherwise the following commit worked for me
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
    # unused right now - intellij proposed their own scripting mechanism which seems to work right now.
    # I keep this here, in case someone needs the additional options provided by kscript.
    # Massive speed increase for each *.kts script, because the script only compiles once, and not on every use.
#    cd $dependencieshome
#    git clone https://github.com/holgerbrandl/kscript.git
#    cd kscript/
#    export JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64/"
#    ./gradlew assemble
#    ln -s $dependencieshome/kscript/build/libs/kscript /bin/kscript
}
#proguard
{
    # Currently unused ... but planned to be used to shrink and optimize the library.
#    cd $dependencieshome
#    git clone https://github.com/Guardsquare/proguard.git
#    cd proguard
#    gradle assemble
#    cd build/distributions
#    tar -xzf proguard-7.0.0.tar.gz
#    ln -s $dependencieshome/proguard/build/distributions/proguard-7.0.0/bin/proguard.sh /bin/proguard
}
#korio
{
    # Currently unused ... because of massive performance problems during communication.
    ${luposdate3000home}/compile-module-korio.main.kts
}
#intellij
{
    # Used to format the source code from the commandline - you may choose a more recent version.
    cd $dependencieshome
    wget https://download.jetbrains.com/idea/ideaIC-2020.1.2.tar.gz
    tar -xzf ideaIC-2020.1.2.tar.gz
    rm ideaIC-2020.1.2.tar.gz
    cd idea-IC-201.7846.76/bin
    ./idea.sh
    # intellij needs to be launched once to confirm basic settings.
    # During the above installations, the gui is installed anyway.
}

```
