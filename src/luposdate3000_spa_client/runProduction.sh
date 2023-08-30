#!/bin/bash
(
cd ../..
./gradlew jsBrowserDevelopmentWebpack --offline
)
rm -rf dist
npm run build:dev
python3 -m http.server 8890 --directory dist
