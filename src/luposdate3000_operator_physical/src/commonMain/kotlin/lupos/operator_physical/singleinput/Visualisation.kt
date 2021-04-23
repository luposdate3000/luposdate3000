package lupos.operator.physical.singleinput

public expect class Visualisation() {
    public fun sendData(parentUUID: Long, childUUID: Long, index: Int, string: String)
}
