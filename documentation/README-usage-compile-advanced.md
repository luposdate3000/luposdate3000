## Usage / Compile (advanced)

To compile everything the first time you need to setup the buildfiles first.
Here you can specify many options like ("target, inlineMode, releaseMode, compiler").

```bash
./launcher.main.kts --setupCommandline
```

afterwards you can compile it using gradle.

```bash
./gradlew build --parallel
```


If you want to build the SPAClient:
```bash
./launcher.main.kts --setupCommandline --target=JVM_JS
./gradlew build --parallel
./launcher.main.kts --setupSPAClient
./launcher.main.kts --run  --target=JVM --Endpoint_Launcher=Java_Sockets --processUrls=localhost:80
```
