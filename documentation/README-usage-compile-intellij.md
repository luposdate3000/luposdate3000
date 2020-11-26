## Usage / Compile (advanced)

First close intellij, if it is already open.
Prepare the project for intellij using:


```bash
./tool-setup-idea.main.kts
```

This should be fast.

If you pull again from the git, than execute this setup-script again.

Download and install intellij from https://www.jetbrains.com/idea/download/

Now, you can open intellij.
Intellij on Windows warns you about not trusted git-ssl-certificates - you can accept these.
The Linux-version of intellij ignores this.

You can compile and run the database by navigating to the main functions, which are located within the src/luposdate3000_launch_*/src/jvmMain/kotlin/Main.kt files.
Substitute the "*" with the wanted target.
Within the IDE this works only for main functions, which dont require arguments.
