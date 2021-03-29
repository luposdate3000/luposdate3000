package lupos.s09physicalOperators.singleinput

public expect class Visualisation() {
    public fun sendData(parentUUID: Long, childUUID: Long, index: Int, string: String)

}
