##Installation on Windows 10
My windows10 gui is in german, so I do not know the exact english labels in the gui.
Personally I prefer and therefore use linux.
If you have some hints to simplify/improve this README, let me know.

# install java

Download and install git-bash from https://git-scm.com/download/win .
Install any Java version of at least java 8
For Example:

* Download and unpack java 15 from (openjdk-15.0.1_windows-x64_bin.zip)[https://download.java.net/java/GA/jdk15.0.1/51f4f36ad4ef43e39d0dfdbaf6549e32/9/GPL/openjdk-15.0.1_windows-x64_bin.zip] .
* This download wants you to login (create an user) - there are workarounds in the internet, if you want to avoid creating credentials for that website.
  Download and install java 8 from (jdk-8u271-windows-x64.exe)[https://download.oracle.com/otn-pub/java/jdk/8u271-b09/61ae65e088624f5aaa0b1d2d801acb16/jdk-8u271-windows-x64.exe] .

restart gitbash, if it is already open

# install kotlin

Download (kotlin-compiler-1.4.32.zip)[https://github.com/JetBrains/kotlin/releases/download/v1.4.32/kotlin-compiler-1.4.32.zip]
Extract the compiler to its final destination, for Example somewhere in the Programs-Folder
Add the compiler to the Path.
The compiler-path looks something like "C:/.../kotlinc/bin"

restart gitbash, if it is already open

#enable windows long file paths (more than 260 chars)
This is required for working with the JS target due to how the compiler works.
open start-search
type "regedit"
open the suggested program
navigate to "HKEY_LOCAL_MACHINE\SYSTEM\ControlSet001\Control\FileSystem"
change the value of LongPathsEnabled to 1

you must reboot now to make that change effective

restart gitbash, if it is still open - this should be closed after the reboot

# inside of gitbash:

```gitbash
# Unfortunately the ifis-git uses incorrect ssl such that the following git option must be used.
git config --global http.sslVerify false
# work around windows filename length limitation
git config --system core.longpaths true
# if windows reports that you dont have the access rights, than add the key manually to the file
# Prevent repeatingly typing the password on commit.
git config --global credential.helper store

git clone https://sun01.pool.ifis.uni-luebeck.de/groppe/luposdate3000.git

#install bignum
{ 
    git clone https://github.com/ionspin/kotlin-multiplatform-bignum.git
    cd kotlin-multiplatform-bignum/bignum
    #patch the buildfile to make it executable as JS in Browsers
    sed 's/.*it.compileKotlinTask.kotlinOptions.moduleKind = "commonjs"//g' -i build.gradle.kts
    sed 's/if.*primaryDevelopment.*{/if (true) {/g' -i build.gradle.kts
    sed 's/^version .*/version = "0.3.1-SNAPSHOT"/g' -i build.gradle.kts
    cd ..
    gradle publishToMavenLocal
}
```

#set some environment variables

* "LUPOS_HOME"
  only used, if the database is using a persistent buffer-manager. Point this to an Folder, where the database should store its data. Include a trailling backslash.
* "LUPOS_BUFFER_SIZE"
  used for internall buffers during importing data. Set this to at most 5% of your available RAM
* "LUPOS_REAL_WORLD_DATA_ROOT"
  point this to the base folder, where you have downloaded your real-world datasets. This is only required, if you are actually using those datasets.
* "LUPOS_RAM"
  specify the available memory in GB. Keep in mind, that the operating system (or other programs) may need some space too. This is only used in the commanline startup of the database.
