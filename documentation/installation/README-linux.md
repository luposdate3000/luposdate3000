## Installation on Linux

You can copy paste execute the following script.
Make sure you read the following comments as they may provide useful information.

```bash
# The basic dependencies which can be installed via package manager apt.
# Yes, there are 2 versions of java ... because the dependencies dont like the wrong one.
# Sadly latex installs a complete gui for linux - even on server distributions.
apt install docker docker-compose docker.io g++ gnuplot gzip htop lcov make maven net-tools ntfs-3g openjdk-8-jdk openjdk-14-jdk unzip zip poppler-utils texlive texlive-latex-extra p7zip-full libncurses5 texlive-font-utils raptor2-utils curl

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
#intellij ... alternatively build it from source
{
    cd $dependencieshome
    git clone --depth=1 https://github.com/JetBrains/intellij-community
    cd intellij-community
    export JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64/"
    ./getPlugins.sh
    ant build
}
#ktlint
{
    curl -sSLO https://github.com/pinterest/ktlint/releases/download/0.40.0/ktlint
    chmod a+x ktlint
    mv ktlint /usr/local/bin/
}
```
