## Usage / Compile (advanced)

To compile everything the first time you must perform a complete rebuild, which is done by using:

```bash
./launcher.main.kts --compileAll
```

This takes a lot of time (depending on the settings: on my computer up to 15 Minutes using a 4GHz CPU with NVME-SSD and lots of available RAM).
The final compiled jars, js and native-libs can be found in the subfolders of [build-cache](build-cache)

Take a look into the function "onCompile" in the file [./launcher.main.kts](./launcher.main.kts)
If you change something, compile only the necessary module (and the ones depending on it) instead of everything.
If you only need the jars you can use "--fastMode=JVM" - otherwise use "--fastMode=Disable".

Compiling just a single Module for JVM only takes a few Seconds - which is much faster than multiple minutes for everything.
