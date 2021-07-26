## Usage / Compile

First, move to the project directory.

Than you need to setup the buildfiles.

```bash
./launcher.main.kts --setup --intellijMode=Disable --target=JVM_JS --releaseMode=Enable --dictionaryValueMode=Int
./gradlew build
```

To start the SPARQL endpoint and the webserver:

```bash
./launcher.main.kts --run
```

Now use a recent Browser to access localhost:80 .
To see the list of compatible Browsers, you may take a look at [caniuse.com](https://caniuse.com/?search=int64array) .
