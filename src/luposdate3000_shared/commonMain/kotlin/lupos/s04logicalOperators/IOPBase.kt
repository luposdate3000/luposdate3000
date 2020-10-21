package lupos.s04logicalOperators
import lupos.s00misc.Partition
import lupos.s00misc.SortHelper
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.iterator.IteratorBundle
interface IOPBase{
fun getClassname():String
fun toSparql():String
suspend fun evaluate(parent: Partition): IteratorBundle
  fun cloneOP(): IOPBase
   fun getPartitionCount(variable: String): Int
fun getRequiredVariableNamesRecoursive(): List<String> 
fun getRequiredVariableNames(): List<String>
    fun getProvidedVariableNames(): List<String> 
suspend fun toXMLElement(): XMLElement 
 fun getLatestChild(): IOPBase
fun getPossibleSortPriorities(): List<List<SortHelper>>
}

