##Installation on Windows 10
My windows10 gui is in german, so I do not know the exact english labels in the gui.
Personally I prefer and therefore use linux.
If you have some hints to simplify/improve this README, let me know.


Download and install git-bash from (git-bash)[https://git-scm.com/download/win] .

# install java

Install any Java version of at least java 8

restart gitbash, if it is already open

# install kotlin

Download (kotlin-compiler-1.5.21.zip)[https://github.com/JetBrains/kotlin/releases/download/v1.5.21/kotlin-compiler-1.5.21.zip]
Extract the compiler to its final destination, for Example somewhere in the Programs-Folder
Add the compiler to the Path.
The compiler-path looks something like "C:/.../kotlinc/bin"

restart gitbash, if it is already open

# enable windows long file paths (more than 260 chars)
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
# if you are using the ifis gitlab-repository disable the ssl verification, because the ifis-git uses incorrect ssl.
git config --global http.sslVerify false
# work around windows filename length limitation
git config --system core.longpaths true
# if windows reports that you dont have the access rights, than add the key manually to the file
# Prevent repeatingly typing the password on commit.
git config --global credential.helper store

git clone https://github.com/luposdate3000/luposdate3000.git

# install bignum
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

# set some environment variables

* "LUPOS_HOME"
  only used, if the database is using a persistent buffer-manager. Point this to an Folder, where the database should store its data. Include a trailling backslash.
* "LUPOS_BUFFER_SIZE"
  used for internall buffers during importing data. Set this to at most 5% of your available RAM
* "LUPOS_REAL_WORLD_DATA_ROOT"
  point this to the base folder, where you have downloaded your real-world datasets. This is only required, if you are actually using those datasets.
* "LUPOS_RAM"
  specify the available memory in GB. Keep in mind, that the operating system (or other programs) may need some space too. This is only used in the commanline startup of the database.

# usage

You can compile the database in multiple different ways.

The most important options are described in [readme usage](../README-usage-compile.md)

Running the VLDB'21 Demo:

The webserver can be compiled and started using these instructions [readme-sonification-demo](../README-usage-sonification-demo.md).
