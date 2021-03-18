/*
    Added by Rico

    receiveAnimation(..) is a method in the .js file of the visualization and is needed
    for sending the data between the operators to the visualization framework for
    the animation.

 */


package lupos.s09physicalOperators.singleinput

public actual class Visualisation actual constructor() {
    public actual fun sendData(parentUUID: Long, childUUID: Long,index: Int, string: String) {
        receiveAnimation(childUUID,parentUUID,index,string);
    }
}

public external fun receiveAnimation(childUUID: Long, parentUUID: Long,index:Int, string: String)
