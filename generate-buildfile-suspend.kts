#!/bin/kscript
import java.io.File

enum class SuspendMode {
    Enable, Disable
}

var suspendMode = SuspendMode.Enable

val regexEnableSuspend = "/\\*suspend\\*/ ".toRegex()
val regexDisableSuspend = "(^|[^a-zA-Z])suspend ".toRegex()

for (arg in args) {
    try {
        suspendMode = SuspendMode.valueOf(arg)
    } catch (e: Throwable) {
        val fileSource = File(arg)
        val fileTarget = File(arg + ".tmp")
        fileTarget.printWriter().use { out ->
            fileSource.bufferedReader().readLines().forEach {
                when (suspendMode) {
                    SuspendMode.Enable -> {
                        out.println(it.//
                        replace(regexDisableSuspend, "\$1suspend ").//
                        replace(regexEnableSuspend, "suspend "))
                    }
                    SuspendMode.Disable -> {
                        out.println(it.//
                        replace(regexDisableSuspend, "\$1").//
                        replace(regexEnableSuspend, ""))
                    }
                }
            }
        }
        fileSource.delete()
        fileTarget.copyTo(fileSource)
        fileTarget.delete()
    }
}
