## Installation on Linux

You can copy paste execute the following script.
Make sure you read the following comments as they may provide useful information.

```bash
# if you are using the ifis gitlab-repository disable the ssl verification, because the ifis-git uses self-signed ssl.
git config --global http.sslVerify false
# Prevent repeatingly typing the password on commit.
git config --global credential.helper store
git clone https://github.com/luposdate3000/luposdate3000.git

apt install openjdk-8-jdk-headless unzip

#Define the folder, where to download everything else.
dependencieshome=/opt

#Now the components are installed one by one.

#simora
{
    cd $dependencieshome
    git clone https://github.com/luposdate3000/SIMORA.git
    cd SIMORA
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

#if you have less than 8GB of memory  
Look at this guide [compile with a few GB of RAM](../README-compile-with-too-less-RAM.md)

# usage

You can compile the database in multiple different ways.

The most important options are described in [readme usage](../README-usage-compile.md)

Running the VLDB'21 Demo:

The webserver can be compiled and started using these instructions [readme-sonification-demo](../README-usage-sonification-demo.md).
