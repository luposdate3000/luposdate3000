```bash
#1 clone SIMORA
git clone --depth=1 ssh://git@sun04.pool.ifis.uni-luebeck.de:5522/warnke/simora.git
(
cd simora
#2 install simora into the local mvn store
./gradlew publishtomavenlocal
)
#3 clone luposdate3000
git clone --depth=1 ssh://git@sun04.pool.ifis.uni-luebeck.de:5522/warnke/luposdate3000.git
(
cd luposdate3000/src/luposdate3000_spa_client
#4 install npm dependencies
npm install
)



#A development mode
(
cd luposdate3000
#5A create gradle build files
./launcher.main.kts --setup --target=JS --releaseMode=Disable --dictionaryMode=InMemory
#6A compile kotlin
./gradlew jsBrowserDevelopmentWebpack
)
(
cd luposdate3000/src/luposdate3000_spa_client
#7A clear output folder
rm -rf dist
#8A configure development dependencies
sed "s#const luposdate3000.*#const luposdate3000 = require(\"../../luposdate3000_endpoint/build/developmentExecutable/luposdate3000_endpoint.js\")#g" -i src/endpointLuposdate3000Browser.js
#9A package the javascript code
npm run build:dev
)



#B production mode
(
cd luposdate3000
#5B create gradle build files
./launcher.main.kts --setup --target=JS --releaseMode=Enable --dictionaryMode=InMemory
#6B compile kotlin
./gradlew jsBrowserProductionWebpack
)
(
cd luposdate3000/src/luposdate3000_spa_client
#7B clear output folder
rm -rf dist
#8B configure production dependencies
sed "s#const luposdate3000.*#const luposdate3000 = require(\"../../luposdate3000_endpoint/build/distributions/luposdate3000_endpoint.js\")#g" -i src/endpointLuposdate3000Browser.js
#9B package the javascript code including minification, dead code elimination, removing unnecesary branches, removing comments, everything what is not needed for execution
npm run build:prod
)



(
cd luposdate3000/src/luposdate3000_spa_client
#10 host the webapp
python3 -m http.server 8890 --directory dist
)
```
