package lupos.s03resultRepresentation

import kotlin.jvm.JvmField

internal object ResultSetDictionaryExt {
    const val booleanTrueValue = (0x00000000) /*lowest 5 values*/ /*required to be 0 for_ truth table loopups*/
    const val booleanFalseValue = (0x00000001) /*lowest 5 values*/ /*required to be 1 for_ truth table loopups*/
    const val errorValue = (0x00000002) /*lowest 5 values*/ /*required to be 2 for_ truth table loopups*/
    const val undefValue = (0x00000003) /*lowest 5 values*/
    const val nullValue = (0x00000004) /*lowest 5 values*/ /*symbol for no more results, previously 'null'*/

    @JvmField
    val booleanTrueValue2 = ValueBoolean(true)

    @JvmField
    val booleanFalseValue2 = ValueBoolean(false)

    @JvmField
    val errorValue2 = ValueError()

    @JvmField
    val undefValue2 = ValueUndef()
}
