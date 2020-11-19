package lupos.s00misc

import lupos.s00misc.NotImplementedException

internal actual class DateHelper {
    actual constructor() {}

    internal val time = js("new Date()")
    actual inline fun year(): Int = time.getFullYear()
    actual inline fun month(): Int = time.getMonth()
    actual inline fun day(): Int = time.getDay()
    actual inline fun hours(): Int = time.getHours()
    actual inline fun minutes(): Int = time.getMinutes()
    actual inline fun seconds(): Int = time.getSeconds()
}
