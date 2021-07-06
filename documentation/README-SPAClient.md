#SPAClient

To Compile the SPAClient on Windows use

```gitbash
./launcher.main.kts --setupIntellijIdea --target=JVM_JS
./gradlew build
./launcher.main.kts --setupSPAClient
# dies wird crashen ... wenn nicht beim build, dann im Browser   
# die Datei
# https://github.com/nbrosowsky/tonejs-instruments/archive/8ec9f43d6f07fdeb15e684df5a6c7efa2c3eedf6.tar.gz
# selber herunterladen und nach 
# ./src/luposdate3000_spa_client/node_modules/tonejs-instruments#8ec9f43d6f07fdeb15e684df5a6c7efa2c3eedf6
# entpacken
./launcher.main.kts --setupSPAClient --dryMode=Enable
```

On linux the following is enough

```gitbash
./launcher.main.kts --setupIntellijIdea --target=JVM_JS
./gradlew build
./launcher.main.kts --setupSPAClient
```



To launch the database with the endpoint use

```gitbash
./launcher.main.kts --run
```


which will provide the Database on localhost:80/index.html
