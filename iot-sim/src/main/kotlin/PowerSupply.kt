class PowerSupply(capacity: Double) {

    var consumed = 0.0
        private set;

    val isInfinite: Boolean = capacity < 0
    var actualCapacity: Double = if(isInfinite) Double.MAX_VALUE else capacity

    var drainageRate = 1.0


    fun hasPowerLeft() = isInfinite || actualCapacity >= 0.0

    fun decrease(){
        consumed += drainageRate
        if (isInfinite)
            actualCapacity -= drainageRate
    }

}