package lupos.modulename
internal actual class _DateHelper {
    actual constructor() {}
    internal val time = js("new Date()")
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun year(): Int = time.getFullYear()
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun month(): Int = time.getMonth()
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun day(): Int = time.getDay()
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun hours(): Int = time.getHours()
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun minutes(): Int = time.getMinutes()
    @Suppress("NOTHING_TO_INLINE") internal actual inline fun seconds(): Int = time.getSeconds()
}
