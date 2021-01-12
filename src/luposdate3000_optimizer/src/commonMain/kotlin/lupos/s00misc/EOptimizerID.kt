package lupos.s00misc
internal class EOptimizerID public constructor (public val ordinal: Int){
    public override fun toString():String=throw Exception("toString not allowed")
    init{
        if(ordinal<0||ordinal>43)throw Exception("enum out of range")
    }
}
