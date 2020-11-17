## Usage / Compile (advanced)

First close intellij, if it is already open.
Prepare the project for intellij using:


```bash
./tool-setup-idea.main.kts
```

This should be fast.

Download and install intellij from https://www.jetbrains.com/idea/download/

Now, you can open intellij.
Intellij on Windows warns you about not trusted git-ssl-certificates - you can accept these.
The Linux-version of intellij ignores this.

To compile and launch, click "Run Anything" in the top right corner.
Type gradle run - dont hit "enter" directly.
Now there shoule be a dropdown with multiple suggestions e.g. "src:luposdate3000_launch_binary_test_suite:run"
The module name tells you, what exactly is launched.
