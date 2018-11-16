import lupos.datastructures.b_plus_tree.*
import lupos.io.buffer.*
import lupos.misc.*

fun Byte.binary():String {
    var result = ""
    if(this < 0){
        result+="1"
    } else {
        result+="0"
    }
    if(this.bit6()){
        result+="1"
    } else {
        result+="0"
    }
    if(this.bit5()){
        result+="1"
    } else {
        result+="0"
    }
    if(this.bit4()){
        result+="1"
    } else {
        result+="0"
    }
    if(this.bit3()){
        result+="1"
    } else {
        result+="0"
    }
    if(this.bit2()){
        result+="1"
    } else {
        result+="0"
    }
    if(this.bit1()){
        result+="1"
    } else {
        result+="0"
    }
    if(this.bit0()){
        result+="1"
    } else {
        result+="0"
    }
    return result
}

fun main(args:Array<String>){
    val filename = "test"
    val page = bufferManager.getPage(filename, 0)
    var adr = 0L

    // testBPlusTree2()
    testBPlusTree5()
    // testBPlusTree6()
}