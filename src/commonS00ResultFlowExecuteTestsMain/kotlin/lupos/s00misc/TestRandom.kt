package lupos.s00misc

class TestRandom(val buffer: DynamicByteArray){
fun randomAvailable():Int{
return buffer.data.size/4
}
fun nextInt(maxValue:Int=Int.MAX_VALUE,increment:Boolean=true):Int{
val tmp = if (increment)
        buffer.getNextInt()
    else
        buffer.getInt(buffer.pos)
    if (tmp < 0)
        return (-tmp) % maxValue
    return tmp % maxValue
}
fun nextBoolean():Boolean{
return buffer.getNextInt()%2==0
}
}
