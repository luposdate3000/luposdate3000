#!/bin/kscript
import java.io.File

enum class InlineMode {
    Enable, Disable
}

var inlineMode = InlineMode.Enable

val regexEnableNoInline = "/\\*noinline\\*/".toRegex()
val regexEnableInline = "/\\*inline\\*/".toRegex()
val regexEnableCrossInline = "/\\*crossinline\\*/".toRegex()
val regexDisableNoInline = "(^|[^a-zA-Z])noinline ".toRegex()
val regexDisableInline = "(^|[^a-zA-Z])inline ".toRegex()
val regexDisableCrossInline = "(^|[^a-zA-Z])crossinline ".toRegex()

for (arg in args) {
    try {
        inlineMode = InlineMode.valueOf(arg)
    } catch (e: Throwable) {
        val fileSource = File(arg)
        val fileTarget = File(arg + ".tmp")
        fileTarget.printWriter().use { out ->
            fileSource.bufferedReader().readLines().forEach {
                when (inlineMode) {
                    InlineMode.Enable -> {
                        out.println(it.//
                        replace(regexDisableNoInline, "\$1noinline ").//
                        replace(regexDisableInline, "\$1inline ").//
                        replace(regexDisableCrossInline, "\$1crossinline ").//
                        replace(regexEnableNoInline, "noinline ").//
                        replace(regexEnableInline, "inline ").//
                        replace(regexEnableCrossInline, "crossinline "))
                    }
                    InlineMode.Disable -> {
                        out.println(it.//
                        replace(regexDisableNoInline, "\$1").//
                        replace(regexDisableInline, "\$1").//
                        replace(regexDisableCrossInline, "\$1").//
                        replace(regexEnableNoInline, "").//
                        replace(regexEnableInline, "").//
                        replace(regexEnableCrossInline, ""))
                    }
                }
            }
        }
        fileSource.delete()
        fileTarget.copyTo(fileSource)
        fileTarget.delete()
    }
}
