@file:kotlinx.cinterop.InteropStubs
@file:Suppress("UNUSED_VARIABLE", "UNUSED_EXPRESSION")
package platform.posix

import kotlin.native.SymbolName
import kotlinx.cinterop.*

// NOTE THIS FILE IS AUTO-GENERATED

fun posix_errno(): Int {
    return kniBridge0()
}

fun set_posix_errno(value: Int): Unit {
    return kniBridge1(value)
}

fun posix_h_errno(): Int {
    return kniBridge2()
}

fun set_posix_h_errno(value: Int): Unit {
    return kniBridge3(value)
}

fun posix_htons(x: Short): Short {
    return kniBridge4(x)
}

fun init_sockets(): Int {
    return kniBridge8()
}

fun deinit_sockets(): Unit {
    return kniBridge9()
}

@SymbolName("platform_posix_kniBridge0")
private external fun kniBridge0(): Int
@SymbolName("platform_posix_kniBridge1")
private external fun kniBridge1(p0: Int): Unit
@SymbolName("platform_posix_kniBridge2")
private external fun kniBridge2(): Int
@SymbolName("platform_posix_kniBridge3")
private external fun kniBridge3(p0: Int): Unit
@SymbolName("platform_posix_kniBridge4")
private external fun kniBridge4(p0: Short): Short
@SymbolName("platform_posix_kniBridge5")
private external fun kniBridge5(p0: NativePtr): Unit
@SymbolName("platform_posix_kniBridge6")
private external fun kniBridge6(p0: Int, p1: NativePtr): Unit
@SymbolName("platform_posix_kniBridge7")
private external fun kniBridge7(p0: Int, p1: NativePtr): Int
@SymbolName("platform_posix_kniBridge8")
private external fun kniBridge8(): Int
@SymbolName("platform_posix_kniBridge9")
private external fun kniBridge9(): Unit
