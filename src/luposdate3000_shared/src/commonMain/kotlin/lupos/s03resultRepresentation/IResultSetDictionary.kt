package lupos.s03resultRepresentation
public interface IResultSetDictionary {
    public fun valueToGlobal(value: Int): Int
    public fun getValue(value: Int): ValueDefinition
    public fun createValue(value: String?): Int
    public fun createValue(value: ValueDefinition): Int
    public fun toBooleanOrError(value: Int): Int
    public fun getValue(
        value: Int,
        onBNode: (value: Int) -> Unit,
        onBoolean: (value: Boolean) -> Unit,
        onLanguageTaggedLiteral: (content: String, lang: String) -> Unit,
        onSimpleLiteral: (content: String) -> Unit,
        onTypedLiteral: (content: String, type: String) -> Unit,
        onDecimal: (value: String) -> Unit,
        onFloat: (value: Double) -> Unit,
        onDouble: (value: Double) -> Unit,
        onInteger: (value: String) -> Unit,
        onIri: (value: String) -> Unit,
        onError: () -> Unit,
        onUndefined: () -> Unit
    )
}
