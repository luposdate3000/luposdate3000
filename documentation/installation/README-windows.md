##Installation on Windows 10
My windows10 gui is in german, so I do not know the exact english labels in the gui.
Personally I prefer and therefore use linux.
If you have some hints to simplify/improve this README, let me know.
I am not sure if every step is required, but it seems like adding the programs to the path is not enough (there are errors when you try to execute them).
Instead copy paste them into the gitbash "/bin" and "/lib" folders works for me.

Download and install git from https://git-scm.com/download/win .
Download and unpack java 15 from https://download.java.net/java/GA/jdk15.0.1/51f4f36ad4ef43e39d0dfdbaf6549e32/9/GPL/openjdk-15.0.1_windows-x64_bin.zip .

This download wants you to login (create an user) - there are workarounds in the internet, if you want to avoid creating credentials for that website.
Download and install java 8 from https://download.oracle.com/otn-pub/java/jdk/8u271-b09/61ae65e088624f5aaa0b1d2d801acb16/jdk-8u271-windows-x64.exe .

Yes, there are 2 versions of java, because the dependencies dont like the "wrong" version .
Java8 seems to be available as installer only? I someone find a zipped version, which is downloadable without login let me know .
Remember, where you install each java, you will need that path later .

## add java to the path

open start-search
type "env"
open the suggested program
click on "environment-variables"
in the system variables select the row with the variable name "Path"
below the system variables click on "change"
click on "new"
add the path to your java installation - in my case it was "C:\Users\benja\luposdate\jdk-15.0.1\bin"
make sure your new java path is on top of that list, such that your wanted java version is used - this may break other programs which depend on another java version ...
click "ok" on every window (3 times)
restart gitbash, if it is already open

## inside of gitbash:

```gitbash
export myPathBackup=$PATH
git clone https://github.com/JetBrains/kotlin.git
cd kotlin
#you may test the head commit first, but this commit works for me
git checkout 67b262aa3435c3fd15e1224bfd6a50a0f008d2f3
#interestingly here the environment variables do effect the used jdk ...
export JAVA_HOME="C:\Program Files\Java\jdk1.8.0_271"
export JDK_16="C:\Program Files\Java\jdk1.8.0_271"
export JDK_17="C:\Program Files\Java\jdk1.8.0_271"
export JDK_18="C:\Program Files\Java\jdk1.8.0_271"
export JDK_9="C:\Users\benja\luposdate\jdk-15.0.1"
./gradlew install
./gradlew dist
export PATH="$myPathBackup"
```

##copy the kotlin binaries into the bin folder of your gitbash installation.
In my case that means copy the content of the folder "C:\Users\benja\luposdate\kotlin\dist\kotlinc\bin" into the folder "C:\Program Files\Git\usr\bin"
In my case that means copy the content of the folder "C:\Users\benja\luposdate\kotlin\dist\kotlinc\lib" into the folder "C:\Program Files\Git\usr\lib"
This cannot be done inside gitbash, because that folder is mounted as readonly.


## add kotlin compiler to the path
open start-search
type "env"
open the suggested program
click on "environment-variables"
in the system variables select the row with the variable name "Path"
below the system variables click on "change"
click on "new"
add the path to the kotlin-compiler The compiler is located in a subfolder of your cloned kotlin repository.
In my case it is "C:\Users\benja\luposdate\kotlin\dist\kotlinc\bin"
click "ok"
additionally create a new environment variable named "KOTLIN_HOME" and let it point to "C:\Users\benja\luposdate\kotlin\dist\kotlinc"
click "ok" on every window (2 times)

restart gitbash, if it is still open

## inside of gitbash:

```gitbash
# Unfortunately the ifis-git uses incorrect ssl such that the following git option must be used.
git config --global http.sslVerify false
# Prevent repeatingly typing the password on commit.
git config --global credential.helper store

git clone https://sun01.pool.ifis.uni-luebeck.de/groppe/luposdate3000.git
```
