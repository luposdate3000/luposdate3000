## Installation on Linux

You can copy paste execute the following script.
Make sure you read the following comments as they may provide useful information.

```bash
# if you are using the ifis gitlab-repository disable the ssl verification, because the ifis-git uses incorrect ssl.
git config --global http.sslVerify false
# Prevent repeatingly typing the password on commit.
git config --global credential.helper store
git clone https://github.com/luposdate3000/luposdate3000.git

apt install openjdk-8-jdk-headless unzip

#Define the folder, where to download everything else.
dependencieshome=/opt

#Now the components are installed one by one.

#bignum
{
    cd $dependencieshome
    git clone https://github.com/ionspin/kotlin-multiplatform-bignum.git
    cd kotlin-multiplatform-bignum/bignum
    #patch the buildfile to make it executable as JS in Browsers
    sed 's/.*it.compileKotlinTask.kotlinOptions.moduleKind = "commonjs"//g' -i build.gradle.kts
    sed 's/if.*primaryDevelopment.*{/if (true) {/g' -i build.gradle.kts
    sed 's/version.*=.*/version = "0.3.3-SNAPSHOT"/g' -i build.gradle.kts
    cd ..
    ./gradlew publishToMavenLocal
}
#kotlin
{
    cd $dependencieshome
    wget https://github.com/JetBrains/kotlin/releases/download/v1.5.21/kotlin-compiler-1.5.21.zip
    unzip kotlin-compiler-1.5.21.zip
    cd kotlinc/bin/
    echo "export PATH=$PATH:$(pwd)" >> ~/.bashrc
}
#kotlin (optional)
{
    cd $dependencieshome
    # the default settings use an precompiled-compiler from maven-repository such that this is not necessary

    apt install openjdk-16-jdk
    # not every commit in that repository is compileable. If the HEAD-commit does not work try another commit or another branch
    git clone https://github.com/JetBrains/kotlin.git --depth=1
    cd kotlin
    echo "export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/" >> ~/.bashrc
    echo "export JDK_16=/usr/lib/jvm/java-8-openjdk-amd64/" >> ~/.bashrc
    echo "export JDK_17=/usr/lib/jvm/java-8-openjdk-amd64/" >> ~/.bashrc
    echo "export JDK_18=/usr/lib/jvm/java-8-openjdk-amd64/" >> ~/.bashrc
    echo "export JDK_9=/usr/lib/jvm/java-16-openjdk-amd64/" >> ~/.bashrc
    ./gradlew install
    ./gradlew dist
    ln -s $dependencieshome/kotlin/dist/kotlinc/bin/kotlinc /bin/kotlinc
    ln -s $dependencieshome/kotlin/dist/kotlinc/bin/kotlin /bin/kotlin
}
#intellij (optional)
{
    cd $dependencieshome
    wget https://download-cf.jetbrains.com/idea/ideaIC-2021.1.1.tar.gz
    tar -xzf ideaIC-2021.1.1.tar.gz
    rm ideaIC-2021.1.1.tar.gz
    cd idea-IC-211.7142.45/bin/
    ./idea.sh
    # intellij needs to be launched once to confirm basic settings.
    # During the above installations, the gui is installed anyway.
}
#ktlint (optional)
{
    # used to format source code
    wget https://github.com/pinterest/ktlint/releases/download/0.41.0/ktlint
    chmod +x ktlint
    mv ktlint /usr/local/bin/
}
```

# usage

You can compile the database in multiple different ways.

The most important options are described in [readme usage](../README-usage-compile.md)

Running the VLDB'21 Demo:

The webserver can be compiled and started using these instructions [readme-sonification-demo](../README-usage-sonification-demo.md).
