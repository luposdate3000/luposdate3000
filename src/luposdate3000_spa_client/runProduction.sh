#!/bin/bash
(
cd ../..
./gradlew jsBrowserProductionWebpack --offline
)
rm -rf dist
sed "s#const luposdate3000.*#const luposdate3000 = require(\"../../luposdate3000_endpoint/build/distributions/luposdate3000_endpoint.js\")#g" -i src/endpointLuposdate3000Browser.js
npm run build:prod
python3 -m http.server 8890 --directory dist
