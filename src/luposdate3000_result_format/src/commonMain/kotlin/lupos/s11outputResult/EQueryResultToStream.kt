package lupos.s11outputResult
public class EQueryResultToStream public constructor (public val ordinal: Int) {
    public override fun toString(): String = throw Exception("toString not allowed")
    init {
        if (ordinal <0 || ordinal> 6)throw Exception("enum out of range")
    }
}
