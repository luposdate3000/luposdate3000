package lupos.s00misc
internal actual class DateHelper {
    actual constructor() {}
    internal val time = js("new Date()")
    internal actual inline fun year(): Int = time.getFullYear()
    internal actual inline fun month(): Int = time.getMonth()
    internal actual inline fun day(): Int = time.getDay()
    internal actual inline fun hours(): Int = time.getHours()
    internal actual inline fun minutes(): Int = time.getMinutes()
    internal actual inline fun seconds(): Int = time.getSeconds()
}
