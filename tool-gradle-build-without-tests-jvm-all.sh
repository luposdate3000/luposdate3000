#!/bin/bash
mkdir log
for chooseS03 in "commonS03DictionaryNoneMain" "commonS03DictionaryQueryLocalLongMain"
do
for chooseS05 in "commonS05HashMapMain"
do
for chooseS12 in "commonS12LocalDummyMain"
do
for chooseS14 in "jvmS14KorioMain"
do

buildName="${chooseS03}-${chooseS05}-${chooseS12}-${chooseS14}"
buildFile="build.gradle-${buildName}.jvm.generated"
buildDir="buildJvm${buildName}"

cat >$buildFile <<EOF
project.buildDir="$buildDir"
buildscript {

    ext.kotlin_version = '1.3.41'

    repositories {
        mavenLocal()
        jcenter()
        mavenCentral()
        google()
        maven {
            url 'http://oss.sonatype.org/content/repositories/snapshots'
        }
        maven {
            url "https://plugins.gradle.org/m2/"
        }
        maven {
            url "https://dl.bintray.com/kotlin/kotlin-eap"
        }
    }

    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:\$kotlin_version"
        classpath "org.jetbrains.kotlin:kotlin-frontend-plugin:0.0.26"
        classpath "com.moowork.gradle:gradle-node-plugin:1.2.0"
    }
}
apply plugin: 'kotlin-platform-jvm'
apply plugin: 'application'

application {
    mainClassName = 'lupos.TestKt'
}

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
    google()
    maven {
        url 'http://dl.bintray.com/kotlin/kotlin-eap-1.2'
    }
}
tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).configureEach {
    kotlinOptions {
        freeCompilerArgs["-Xuse-experimental=kotlin.ExperimentalStdlibApi"]
    }
}
dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:\$kotlin_version"
    implementation "org.jetbrains.kotlin:kotlin-stdlib-common:\$kotlin_version"
    implementation "org.jetbrains.kotlin:kotlin-stdlib:\$kotlin_version"

implementation "com.benasher44:uuid:0.0.7"
    implementation "com.soywiz.korlibs.krypto:krypto:1.9.1"
    implementation "com.soywiz.korlibs.klock:klock:1.7.0"
    implementation "com.soywiz.korlibs.korio:korio:1.9.9-SNAPSHOT"

    implementation "org.jetbrains.kotlin:kotlin-reflect:\$kotlin_version"

    testImplementation "org.jetbrains.kotlin:kotlin-test-common:\$kotlin_version"
    testImplementation "org.jetbrains.kotlin:kotlin-test-annotations-common:\$kotlin_version"

    testImplementation "org.jetbrains.kotlin:kotlin-test-junit:\$kotlin_version"
    testImplementation "org.jetbrains.kotlin:kotlin-test:\$kotlin_version"
}

jar {
    manifest {
        attributes 'Main-Class': 'lupos/TestKt'
    }

    from {
        configurations.compile.collect {
            it.isDirectory() ? it : zipTree(it)
        }
    }
}
compileKotlin {
    kotlinOptions.freeCompilerArgs += [
            '-Xno-call-assertions',
            '-Xno-receiver-assertions',
            '-Xno-param-assertions'
    ]
}
sourceSets {
    main.kotlin.srcDirs += 'src/jvmMain/kotlin'
    main.kotlin.srcDirs += 'src/commonMain/kotlin'
    main.kotlin.srcDirs += 'src/commonMainBak/kotlin'
    main.kotlin.srcDirs += 'src/commonS01HeapMain'
    main.kotlin.srcDirs += 'src/commonS01BufferMainmemoryMain/kotlin'
    main.kotlin.srcDirs += 'src/$chooseS03/kotlin'
    main.kotlin.srcDirs += 'src/$chooseS05/kotlin'
    main.kotlin.srcDirs += 'src/$chooseS12/kotlin'
    main.kotlin.srcDirs += 'src/$chooseS14/kotlin'
}
//test {
//    testLogging {
//        events "failed", "skipped", "passed"
//    }
//}
EOF
(
(
	gradle --build-file="$buildFile" build -x test
        cd $buildDir/distributions
        tar -xf luposdate3000.tar
	cat ./luposdate3000/bin/luposdate3000 | sed "s/lupos.TestKt/lupos.s14endpoint.P2PKt/g"> ./luposdate3000/bin/luposdate3000-p2p
	chmod +x ./luposdate3000/bin/luposdate3000-p2p
) > log/compile-$buildName.log 2>&1
)&
done
done
done
done
wait

grep -rni -e Success -e Fail log/compile*log
