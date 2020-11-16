#!/usr/bin/env kotlin
import java.io.File
import java.lang.ProcessBuilder.Redirect

val pb = ProcessBuilder("../gradlew", "publishToMavenLocal")
val e=pb.environment()
e["JAVA_HOME"] = "/usr/lib/jvm/java-8-openjdk-amd64"
pb.directory(File("korio"))
pb.redirectOutput(Redirect.INHERIT)
pb.redirectError(Redirect.INHERIT)
pb.start().waitFor()
