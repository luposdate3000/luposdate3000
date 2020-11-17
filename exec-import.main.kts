#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_shared/commonMain/kotlin/lupos/s00misc/EOperatingSystem.kt")
@file:Import("src/luposdate3000_shared_inline/commonMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_shared_inline/jvmMain/kotlin/lupos/s00misc/Platform.kt")
@file:Import("src/luposdate3000_scripting/exec-import.kt")
@file:CompilerOptions("-Xmulti-platform")

execImport(args)
