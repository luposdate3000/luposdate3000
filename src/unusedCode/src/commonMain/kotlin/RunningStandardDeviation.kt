import kotlin.jvm.JvmField
class RunningStandardDeviation {
    // https://www.johndcook.com/blog/standard_deviation/
    @JvmField
    var sum = 0.0
    @JvmField
    var n = 0
    @JvmField
    var oldM = 0.0
    @JvmField
    var newM = 0.0
    @JvmField
    var oldS = 0.0
    @JvmField
    var newS = 0.0
    internal inline fun clear() {
        n = 0
    }
    internal inline fun push(x: Double) {
        sum += x
        n++
        if (n == 1) {
            oldM = x
            newM = x
            oldS = 0.0
        } else {
            newM = oldM + (x - oldM) / n
            newS = oldS + (x - oldM) * (x - newM)
            oldM = newM
            oldS = newS
        }
    }
    internal inline fun mean(): Double {
        if (n > 0) {
            return newM
        } else {
            return 0.0
        }
    }
    internal inline fun variance(): Double {
        if (n > 1) {
            return newS / (n - 1)
        } else {
            return 0.0
        }
    }
    internal inline fun standardDeviation() = Math.sqrt(variance())
}
