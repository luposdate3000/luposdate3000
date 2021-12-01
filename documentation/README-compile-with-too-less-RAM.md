# compile of luposdate3000 if you dont have much RAM available
1. Increase memory of VM
2. Add some swap memory (this will be very very slow ...) look here for the necessary commands: [HowTo add swap-memory](https://linuxize.com/post/how-to-add-swap-space-on-ubuntu-18-04/) .
   Choose the swap space, such that you have at least 8GB available afterwards.
3. Close all unnecessary programs
4. Open the file gradle.properties :
4.a. verify that you set the correct amount of RAM at 2 locations in the number include the swap memory, and set this limit very close the maximum possible.
4.b. set org.gradle.parallel to false - parallel execution obviously requires more memory at the same time.
   Of course this would increase the compile time as well.
5. reboot - to make sure, that every unnecessary program is definitely gone.
6. use "./gradlew assemble" instead of "./gradlew build".
   This will skip all unit tests, because they require lots of memory (1GB for each used thread)
7. Pipe the output of gradlew to a file "./gradlew assemble > your-log-file-name", because this will decrease the memory needed by your console.
   As a side effect, you will see only the errors in the console, and everything else will be written to that file.
