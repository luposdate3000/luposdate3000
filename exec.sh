gradle clean
gradle build

cd build/distributions
tar -xf luposdate3000.tar
cd luposdate3000
cp -r ../../../resources .
./bin/luposdate3000
