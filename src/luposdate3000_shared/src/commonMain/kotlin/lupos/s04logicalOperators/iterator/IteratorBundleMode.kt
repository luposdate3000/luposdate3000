package lupos.s04logicalOperators.iterator
internal class IteratorBundleMode public constructor (public val ordinal: Int){
    public override fun toString():String=throw Exception("toString not allowed")
    init{
        if(ordinal<0||ordinal>3)throw Exception("enum out of range")
    }
}
