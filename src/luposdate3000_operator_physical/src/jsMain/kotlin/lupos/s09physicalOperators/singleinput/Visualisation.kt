package lupos.operator_physical.singleinput

public actual class Visualisation actual constructor() {
    public actual fun sendData(parentUUID: Long, childUUID: Long, index: Int, string: String) {
        receiveAnimation(childUUID, parentUUID, index, string)
    }
}

public external fun receiveAnimation(childUUID: Long, parentUUID: Long, index: Int, string: String)
