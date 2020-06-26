package lupos.s03resultRepresentation
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueComparatorFast

class ValueComparatorFast() : Comparator<Value> {
    override fun compare(a: Value, b: Value): Int {
        return a.compareTo(b)
    }
}
