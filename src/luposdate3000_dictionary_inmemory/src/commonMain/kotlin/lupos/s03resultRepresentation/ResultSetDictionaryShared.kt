package lupos.s03resultRepresentation
@OptIn(ExperimentalUnsignedTypes::class)
public object ResultSetDictionaryShared {
    /*to most significant bit leads to signed errors because toInt sadly performs a whole reencoding of the int and stores it completely different*/
    internal const val mask1 = 0x40000000/*first 2 bit*/
    internal const val mask3 = 0x30000000/*first 4 bit*/
    internal const val mask6 = 0x3E000000/*first 7 bit*/
    internal const val filter3 = 0x0FFFFFFF
    internal const val filter6 = 0x01FFFFFF
    internal const val flaggedValueLocalBnode = 0x00000000/*first 4 bit*/ /*required to be 0 byResultSetDictionaryExt.booleanTrueValue*/
    internal const val flaggedValueLocalIri = 0x10000000/*first 4 bit*/
    internal const val flaggedValueLocalTyped = 0x20000000/*first 4 bit*/
    internal const val flaggedValueLocalInt = 0x30000000/*first 7 bit*/
    internal const val flaggedValueLocalDecimal = 0x34000000/*first 7 bit*/
    internal const val flaggedValueLocalDouble = 0x38000000/*first 7 bit*/
    internal const val flaggedValueLocalFloat = 0x3C000000/*first 7 bit*/
    internal const val flaggedValueLocalLangTagged = 0x3E000000/*first 7 bit*/
    internal const val flaggedValueGlobalBnode = 0x40000000/*first 4 bit*/
    internal const val flaggedValueGlobalIri = 0x50000000/*first 4 bit*/
    internal const val flaggedValueGlobalTyped = 0x60000000/*first 4 bit*/
    internal const val flaggedValueGlobalInt = 0x70000000/*first 7 bit*/
    internal const val flaggedValueGlobalDecimal = 0x74000000/*first 7 bit*/
    internal const val flaggedValueGlobalDouble = 0x78000000/*first 7 bit*/
    internal const val flaggedValueGlobalFloat = 0x7C000000/*first 7 bit*/
    internal const val flaggedValueGlobalLangTagged = 0x7E000000/*first 7 bit*/
    internal const val emptyString = ""
    public fun isGlobalBNode(value: Int): Boolean = (value and mask3) == flaggedValueGlobalBnode
}
