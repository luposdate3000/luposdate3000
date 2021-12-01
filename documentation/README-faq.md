# FAQ

1. E: Error messages which contain the description "Redeclaration: ClassXYZ"  
   A: You have outdated files which contain old code. To solve this issue, first commit your changes, than clean your working-repository with `git clean -xdf`, finally rerun `./launcher.main.kts --setup`
     This should be fixed now.

3. E: After running `./launcher.main.kts --setup` there are the same errors as before executing it  
   A: To speed up the script, the kotlin-compiler is using caches. Unfortunately the caching does not consider the Files the script depends on. To work around this issue, the easiest method is to add an space to the `launcher.main.kts` File.

4. E: "platform clash of method signature"  
   A: If you have just merged with master, this may happen, if the index of intellij is outdated. The first option is to restart the indexing within intellij. Otherwise use the steps from 1.

5. E: gradle is executing only 1 Tasks  
   A: You skipped the step with `./launcher.main.kts` as described in the Readmes [usage](README-usage-compile.md).
      This should be fixed now.

6. E: java.io.FileNotFoundException: src/luposdate3000_shared/build.gradle.kts (Permission denied)  
   A: Please check, that you have the permission to read/write that File.  
   Does the parent directory exist?  
   Do you have permissions on the parent directory chain?  
   Do you have available space on your storage device? (remember, that a few percent are reserved for root)  
   Especially on Windows: Is there some Program, which has openend that File? Close that Program.  
   Do you own that file?  
   Does the file itself exist? Do you own it?

7. E: compilation fails because gradle daemon dissapears unecpected
   A: If it is killed due to out of memory, take a look at
   [compile with a few GB of RAM](README-compile-with-too-less-RAM.md)
