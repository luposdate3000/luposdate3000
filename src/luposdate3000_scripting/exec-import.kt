import lupos.s00misc.Platform
import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.nio.file.StandardOpenOption
import java.nio.file.Files
import java.nio.file.Paths
fun execImport(args:Array<String>){

File("log").mkdirs()
val jars = mutableListOf(
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Buffer_Manager_Inmemory-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Dictionary_Inmemory-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Endpoint-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Operators-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Parser-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Result_Format-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Shared-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Test-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Triple_Store_All-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Triple_Store_Id_Triple-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Optimizer_NoPartitions-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Endpoint_None-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Jena_Wrapper_Off-jvm.jar",
        "build-cache${Platform.getPathSeparator()}bin${Platform.getPathSeparator()}Luposdate3000_Launch_Import-jvm.jar",
)
val userHome = Platform.getUserHome()
for (f in Platform.findNamedFileInDirectory("${Platform.getUserHome()}${Platform.getPathSeparator()}.gradle${Platform.getPathSeparator()}caches${Platform.getPathSepara>
            jars.add(f)
}
for (f in Platform.findNamedFileInDirectory("${Platform.getUserHome()}${Platform.getPathSeparator()}.m2${Platform.getPathSeparator()}repository${Platform.getPathSepara>
jars.add(f)
}
var classpath = ""
for (jar in jars) {
    if (classpath == "") {
        classpath = jar
    } else {
        classpath = "$classpath:$jar"
    }
}
val cmd=mutableListOf("java", "-Xmx60g", "-cp", classpath, "MainKt")
cmd.addAll(args)
ProcessBuilder(cmd)
        .redirectOutput(Redirect.INHERIT)
        .redirectError(Redirect.INHERIT)
        .start()
        .waitFor()
}
