package lupos.simulator_iot

public class PowerSupply(capacity: Double) {

    public var consumed: Double = 0.0
        private set

    public val isInfinite: Boolean = capacity < 0
    public var actualCapacity: Double = if (isInfinite) Double.MAX_VALUE else capacity

    public var drainageRate: Double = 1.0

    public fun hasPowerLeft(): Boolean = isInfinite || actualCapacity >= 0.0

    public fun decrease() {
        consumed += drainageRate
        if (isInfinite) {
            actualCapacity -= drainageRate
        }
    }
}
