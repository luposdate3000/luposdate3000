#!/bin/bash
mkdir log

commonS00ResultFlowGenerateTestsMain=0
commonS00ResultFlowExecuteTestsMain=0
commonS00ExecutionSequentialMain=0
commonS00TraceOnMain=0

for chooseS00ResultFlow in "commonS00ResultFlowGenerateTestsMain" "commonS00ResultFlowFastMain" "commonS00ResultFlowExecuteTestsMain"
do
for chooseS00Execution in "commonS00ExecutionSequentialMain" "commonS00ExecutionParallelMain"
do
for chooseS00Trace in "commonS00TraceOnMain" "commonS00TraceOffMain"
do
for chooseS03 in "commonS03DictionaryNoneMain" "commonS03DictionaryIntArrayMain"
do
for chooseS05 in "commonS05HashMapMain"
do
for chooseS12 in "jvmS12DummyMain" "commonS12LocalMain"
do
for chooseS14 in "jvmS14KorioMain" "commonS14NoneMain"
do
for chooseS15 in "commonS15LocalMain" "commonS15DistributedMain"
do
if [ "$chooseS00Execution" == "commonS00ExecutionParallelMain" ] && [ "$chooseS00Trace" == "commonS00TraceOnMain" ]
then
	continue
fi
if [ "$chooseS12" == "commonS12LocalMain" ]
then
if [ "$chooseS15" == "commonS15DistributedMain" ] || [ "$chooseS14" == "jvmS14KorioMain" ]
then
# if there is just one node, there is no distribution
        continue
fi
else
if [ "$chooseS03" == "commonS03DictionaryNoneMain" ]
then
# if multiple nodes exist, dictionaries are required
        continue
fi
fi
if [ "$chooseS00ResultFlow" == "commonS00ResultFlowGenerateTestsMain" ] && [ "$chooseS15" != "commonS15DistributedMain" ]
then
#debugging only work with that combination
        continue
fi
if [ "${chooseS00ResultFlow}" == "commonS00ResultFlowGenerateTestsMain" ]
then
#faster test-build
if [ "$commonS00ResultFlowGenerateTestsMain" == "1" ]
then
continue
fi
fi
if [ "${chooseS00ResultFlow}" == "commonS00ResultFlowExecuteTestsMain" ]
then
#faster test-build
if [ "$commonS00ResultFlowExecuteTestsMain" == "1" ]
then
continue
fi
fi
if [ "${chooseS00Execution}" == "commonS00ExecutionSequentialMain" ]
then
#faster test-build
if [ "$commonS00ExecutionSequentialMain" == "1" ]
then
continue
fi
fi
if [ "${chooseS00Trace}" == "commonS00TraceOnMain" ]
then
#faster test-build
if [ "$commonS00TraceOnMain" == "1" ]
then
continue
fi
fi
if [ "${chooseS00ResultFlow}" == "commonS00ResultFlowGenerateTestsMain" ]
then
#faster test-build
commonS00ResultFlowGenerateTestsMain=1
fi
if [ "${chooseS00ResultFlow}" == "commonS00ResultFlowExecuteTestsMain" ]
then
#faster test-build
commonS00ResultFlowExecuteTestsMain=1
fi
if [ "${chooseS00Execution}" == "commonS00ExecutionSequentialMain" ]
then
#faster test-build
commonS00ExecutionSequentialMain=1
fi
if [ "${chooseS00Trace}" == "commonS00TraceOnMain" ]
then
#faster test-build
commonS00TraceOnMain=1
fi
buildName="${chooseS00ResultFlow}-${chooseS00Execution}-${chooseS00Trace}-${chooseS03}-${chooseS05}-${chooseS12}-${chooseS14}-${chooseS15}.generated"
buildFile="build.gradle-${buildName}"
buildDir="buildJvm${buildName}"
buildCache="gradleJvm${buildName}"

cat >tmp <<EOF
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
    mainClassName = 'MainKt'
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
    main.kotlin.srcDirs += 'src/$chooseS00Execution/kotlin'
    main.kotlin.srcDirs += 'src/$chooseS00Trace/kotlin'
    main.kotlin.srcDirs += 'src/$chooseS00ResultFlow/kotlin'
    main.kotlin.srcDirs += 'src/$chooseS03/kotlin'
    main.kotlin.srcDirs += 'src/$chooseS05/kotlin'
    main.kotlin.srcDirs += 'src/$chooseS12/kotlin'
    main.kotlin.srcDirs += 'src/$chooseS14/kotlin'
    main.kotlin.srcDirs += 'src/$chooseS15/kotlin'
}
//test {
//    testLogging {
//        events "failed", "skipped", "passed"
//    }
//}
EOF
	echo "buildfile-diff"
	mv tmp $buildFile
(
(
	gradle --build-file="$buildFile" --project-cache-dir="$buildCache" build -x test
        cd $buildDir/distributions
        tar -xf luposdate3000.tar
	cat ./luposdate3000/bin/luposdate3000 | sed "s/lupos.TestKt/lupos.s14endpoint.P2PKt/g"> ./luposdate3000/bin/luposdate3000-p2p
	chmod +x ./luposdate3000/bin/luposdate3000-p2p
) > log/compile-$buildName.log 2>&1
)
done
done
done
done
done
done
done
done
wait
cat log/compile* | grep -v "ted performance impact of inlining" | grep -v "Name shadowed:"| grep -v "otlin/lupos/datastructures"| grep -v "kotlin/lupos/s02buildSyntaxTree/"| grep -v "kotlin/lupos/s01io/buffer/"| sort | uniq
grep -rni -e Success -e Fail log/compile*log
