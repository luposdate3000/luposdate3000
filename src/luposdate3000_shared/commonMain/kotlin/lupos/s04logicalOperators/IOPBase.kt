package lupos.s04logicalOperators
import lupos.s00misc.Partition
import lupos.s04logicalOperators.iterator.IteratorBundle
interface IOPBase{
fun getClassname():String
fun toSparql():String
suspend fun evaluate(parent: Partition): IteratorBundle
}
