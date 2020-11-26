## Usage / Compile (advanced)

To compile everything the first time you must perform a complete rebuild, which is done by using:

```bash
#./compile-module-korio.main.kts - the korio module is currently unused, and seems to be not compileable on windows
./compile-module-all.main.kts
```

This takes a lot of time (depending on the settings: on my computer up to 15 Minutes using a 4GHz CPU with NVME-SSD and lots of available RAM).
The final compiled jars, js and native-libs can be found in the folder build-cache/bin

Take a look into the file ./compile-module-all.main.kts.
If you change something, compile only the necessary module (and the ones depending on it) instead of everything.
If you only need the jars you can set "FastMode.JVM" - otherwise use "FastMode.Disable"

Compiling just a single Module for JVM only takes around 10 Seconds - which is much faster.
