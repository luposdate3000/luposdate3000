# FAQ

1. E: Error messages which contain the description "Redeclaration: ClassXYZ"  
   A: You have outdated files which contain old code. To solve this issue, first commit your changes, than clean your working-repository with "git clean -xdf", finally rerun "./launcher.main.kts --setupIntellijIdea"

2. E: The SPA-Client can not find its instruments - this yields File-not-found Exceptions in the Browser.  
   A: On windows the download of the instruments failes due an bug in "npm". Please read the documentation in [spaclient](README-SPAClient.md)

3. E: After running "./launcher.main.kts --setupIntellijIdea" there are the same errors as before executing it  
   A: To speed up the script, the kotlin-compiler is using caches. Unfortunately the caching does not consider the Files the script depends on. To work around this issue, the easiest method is to add an space to the launcher.main.kts File.

4. E: "platform clash of method signature"  
   A: If you have just merged with master, this may happen, if the index of intellij is outdated. The first option is to restart the indexing within intellij. Otherwise use the steps from 1.

5. E: gradle is executing only 1 Tasks  
   A: You skipped the step with "./launcher.main.kts" as described in the Readmes [spaclient](README-SPAClient.md)[advanced](README-usage-compile-advanced.md)[intellij](README-usage-compile-intellij.md).

6. E: java.io.FileNotFoundException: src/luposdate3000_shared/build.gradle.kts (Permission denied)  
   A: Please check, that you have the permission to read/write that File.  
   Does the parent directory exist?  
   Do you have permissions on the parent directory chain?  
   Do you have available space on your storage device? (remember, that a few percent are reserved for root)  
   Especially on Windows: Is there some Program, which has openend that File? Close that Program.  
   Do you own that file?  
   Does the file itself exist? Do you own it?
