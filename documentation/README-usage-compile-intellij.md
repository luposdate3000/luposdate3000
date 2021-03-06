## Usage / Compile (advanced)

Download and install intellij from [www.jetbrains.com](https://www.jetbrains.com/idea/download/)

First close intellij, if it is already open.

Move to the project directory, and prepare the project for intellij using:

```bash
./launcher.main.kts --setupIntellijIdea
```

You can append many options, which are shown by using the "--help" parameter
This should be fast.

If you pull from the git, than execute this setup-script again.
If you are adding new dependencies to a module, you need to execute this script again too.

Now, you can open intellij.
Intellij on Windows warns you about not trusted git-ssl-certificates - you can accept these.
The Linux-version of intellij ignores this - this seems to change in every version of the IDE.

You can compile and run the database by navigating to the main functions, which are located within the src/luposdate3000_launch_*/src/jvmMain/kotlin/Main.kt files.
Substitute the "*" with the wanted target.
Within the IDE this works only for main functions, which dont require arguments.
This only works, if the main function is in the jvmMain folder - not for main functions in the commonMain folders.
