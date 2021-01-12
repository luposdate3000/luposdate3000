
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
enum class InlineMode {
    Enable, Disable
}
val regexEnableNoInline = "/\\*noinline\\*/".toRegex()
val regexEnableInline = "/\\*inline\\*/".toRegex()
val regexEnableCrossInline = "/\\*crossinline\\*/".toRegex()
val regexDisableNoInline = "(^|[^a-zA-Z])noinline ".toRegex()
val regexDisableInline = "(^|[^a-zA-Z])inline ".toRegex()
val regexDisableCrossInline = "(^|[^a-zA-Z])crossinline ".toRegex()
public fun applyInlineEnable() {
    Files.walk(Paths.get("src.generated")).forEach { it ->
        val tmp = it.toString()
        if (tmp.endsWith(".kt")) {
            applyInline(tmp, InlineMode.Enable)
        }
    }
}
public fun applyInlineDisable() {
    Files.walk(Paths.get("src.generated")).forEach { it ->
        val tmp = it.toString()
        if (tmp.endsWith(".kt")) {
            applyInline(tmp, InlineMode.Disable)
        }
    }
}
public fun applyInline(f: String, inlineMode: InlineMode) {
    val fileSource = File(f)
    val fileTarget = File(f + ".tmp")
    fileTarget.printWriter().use { out ->
        fileSource.bufferedReader().readLines().forEach {
            when (inlineMode) {
                InlineMode.Enable -> {
                    out.println(
                        it //
                            .replace(regexDisableNoInline, "\$1noinline ") //
                            .replace(regexDisableInline, "\$1inline ") //
                            .replace(regexDisableCrossInline, "\$1crossinline ") //
                            .replace(regexEnableNoInline, "noinline ") //
                            .replace(regexEnableInline, "inline ") //
                            .replace(regexEnableCrossInline, "crossinline ")
                    )
                }
                InlineMode.Disable -> {
                    out.println(
                        it //
                            .replace(regexDisableNoInline, "\$1") //
                            .replace(regexDisableInline, "\$1") //
                            .replace(regexDisableCrossInline, "\$1") //
                            .replace(regexEnableNoInline, "") //
                            .replace(regexEnableInline, "") //
                            .replace(regexEnableCrossInline, "")
                    )
                }
            }
        }
    }
    fileSource.delete()
    fileTarget.copyTo(fileSource)
    fileTarget.delete()
}
