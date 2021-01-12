
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
enum class SuspendMode {
    Enable, Disable
}
val regexEnableSuspend = "/\\*suspend\\*/ ".toRegex()
val regexDisableSuspend = "(^|[^a-zA-Z])suspend ".toRegex()
public fun applySuspendEnable() {
    Files.walk(Paths.get("src.generated")).forEach { it ->
        val tmp = it.toString()
        if (tmp.endsWith(".kt")) {
            applySuspend(tmp, SuspendMode.Enable)
        }
    }
}
public fun applySuspendDisable() {
    Files.walk(Paths.get("src.generated")).forEach { it ->
        val tmp = it.toString()
        if (tmp.endsWith(".kt")) {
            applySuspend(tmp, SuspendMode.Disable)
        }
    }
}
public fun applySuspend(f: String, suspendMode: SuspendMode) {
    val fileSource = File(f)
    val fileTarget = File(f + ".tmp")
    fileTarget.printWriter().use { out ->
        fileSource.bufferedReader().readLines().forEach {
            when (suspendMode) {
                SuspendMode.Enable -> {
                    out.println(
                        it //
                            .replace(regexDisableSuspend, "\$1suspend ") //
                            .replace(regexEnableSuspend, "suspend ")
                    )
                }
                SuspendMode.Disable -> {
                    out.println(
                        it //
                            .replace(regexDisableSuspend, "\$1") //
                            .replace(regexEnableSuspend, "")
                    )
                }
            }
        }
    }
    fileSource.delete()
    fileTarget.copyTo(fileSource)
    fileTarget.delete()
}
