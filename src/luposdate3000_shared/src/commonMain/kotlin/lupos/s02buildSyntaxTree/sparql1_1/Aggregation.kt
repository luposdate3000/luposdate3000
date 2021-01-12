package lupos.s02buildSyntaxTree.sparql1_1
public class Aggregation public constructor (public val ordinal: Int){
    public override fun toString():String=throw Exception("toString not allowed")
    init{
        if(ordinal<0||ordinal>7)throw Exception("enum out of range")
    }
}
