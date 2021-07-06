# FAQ

1. E: Error messages which contain the description "Redeclaration: ClassXYZ"
   A: You have outdated files which contain old code. To solve this issue, first commit your changes, than clean your working-repository with "git clean -xdf", finally rerun "./launcher.main.kts --setupIntellijIdea"

2. E: The SPA-Client can not find its instruments - this yields File-not-found Exceptions in the Browser.
   A: On windows the download of the instruments failes due an bug in "npm". Please read the documentation in [spaclient](README-SPAClient.md)

3. E: After running "./launcher.main.kts --setupIntellijIdea" there are the same errors as before
   A: To speed up the script, the kotlin-compiler is using caches. Unfortunately the caching does not consider the Files the script depends on. To work around this issue, the easiest method is to add an space to the launcher.main.kts File.

4. E: "platform clash of method signature"
   A: If you have just merged with master, this may happen, if the index of intellij is outdated. The first option is to restart the indexing within intellij. Otherwise use the steps from 1.
