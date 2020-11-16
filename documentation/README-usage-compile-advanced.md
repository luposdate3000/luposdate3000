## Usage / Compile (advanced)

First edit the file ./compile-module-all.sh and remove all "--idea" flags - this disables the intellij-IDEA friendly mode.

To compile everything the first time you must perform a complete rebuild, which is done by using:

```bash
./compile-module-korio.sh
./compile-module-all.sh
```

This takes a lot of time (on my computer 15 Minutes using a 4GHz CPU with NVME-SSD and lots of available RAM).
The final compiled jars, js and native-libs can be found in the folder build-cache/bin

Take a look into the file ./compile-module-all.sh.
If you change something, compile only the necessary module (and the ones depending on it) instead of everything.
If you only need the jars you can append "--fast" to the calls to "./generate-buildfile-module.kts".

Compiling just a single Module for JVM only takes around 10 Seconds - which is much faster.

