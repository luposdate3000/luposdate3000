git config --global http.sslVerify false
git config --global credential.helper store
git config --global user.name "Benjamin Warnke"
git config --global user.email "warnke@ifis.uni-luebeck.de"

apt install docker docker-compose docker.io g++ gnuplot gzip htop lcov make maven net-tools ntfs-3g openjdk-8-jdk openjdk-11-jdk openjdk-14-jdk unzip zip

git clone https://sun01.pool.ifis.uni-luebeck.de/groppe/luposdate3000.git
luposdate3000home=$(pwd)/luposdate3000home

#gradle
{
	cd /opt
	git clone https://github.com/gradle/gradle.git
	cd gradle
	export JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64/"
	./gradlew install -Pgradle_installPath=/usr/local/gradle-source-build
}
#kotlin
{
	cd /opt
	git clone https://github.com/JetBrains/kotlin.git
	git checkout build-1.4-M3-eap-48
	export JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64/"
	export JDK_16="/usr/lib/jvm/java-8-openjdk-amd64/"
	export JDK_17="/usr/lib/jvm/java-8-openjdk-amd64/"
	export JDK_18="/usr/lib/jvm/java-8-openjdk-amd64/"
	export JDK_9="/usr/lib/jvm/java-14-openjdk-amd64/"
	./gradlew install
	./gradlew dist
	ln -s /opt/kotlin/dist/kotlinc/bin/kotlinc /bin/kotlinc
	ln -s /opt/kotlin/dist/kotlinc/bin/kotlin /bin/kotlin
}
#kscript
{
	cd /opt
	git clone https://github.com/holgerbrandl/kscript.git
	cd kscript/
	export JAVA_HOME="/usr/lib/jvm/java-11-openjdk-amd64/"
	./gradlew assemble
	ln -s /opt/kscript/build/libs/kscript /bin/kscript
}
#proguard
{
	cd /opt
	git clone https://github.com/Guardsquare/proguard.git
	gradle assemble
	cd build/distributions
	tar -xzf proguard-7.0.0.tar.gz
	ln -s /opt/proguard/build/distributions/proguard-7.0.0/bin/proguard.sh /bin/proguard
}
#korio
{
	${luposdate3000home}/tool-build-korio.sh
}
#intellij
{
	cd /opt
	wget https://download.jetbrains.com/idea/ideaIC-2020.1.2.tar.gz
	tar -xzf ideaIC-2020.1.2.tar.gz
	rm ideaIC-2020.1.2.tar.gz
	cd idea-IC-201.7846.76/bin
	./idea.sh
	#### follow on-screen instructions for first time use
}
#bsbm
{
	cd /opt
	wget https://ayera.dl.sourceforge.net/project/bsbmtools/bsbmtools/bsbmtools-0.2/bsbmtools-v0.2.zip
	unzip bsbmtools-v0.2.zip
	rm bsbmtools-v0.2.zip
	cd bsbmtools-0.2
	git init .
	git add .
	git commit -m a
#	${luposdate3000home}/exec-benchmark-generate-bsbm.sh
}
#sp2b
{
	cd /opt
	wget http://dbis.informatik.uni-freiburg.de/content/projects/SP2B/docs/sp2b-v1_00-full.tar.gz
	tar -xzf sp2b-v1_00-full.tar.gz
	rm sp2b-v1_00-full.tar.gz
	cd sp2b
	git init .
	git add .
	git commit -m a
	git apply ${luposdate3000home}/documentation/installation/sp2b.patch
#	${luposdate3000home}/exec-benchmark-generate-sp2b.sh
}
#btc2019
{
	cd /mnt/luposdate-testdata
	mkdir btc2019
	cd btc2019
	wget https://zenodo.org/record/2634588/files/btc2019-triples.nt.gz?download=1 btc2019-triples.nt.gz
	gunzip btc2019-triples.nt.gz
}
#btc2010
{
	cd /mnt/luposdate-testdata
	mkdir btc2010
	cd btc2010
	wget https://km.aifb.kit.edu/projects/btc-2010/000-CONTENTS
	wget -i 000-CONTENTS
}
