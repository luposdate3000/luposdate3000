package lupos.s11outputResult

import lupos.s00misc.*
import lupos.s03resultRepresentation.IResultSetDictionary
import lupos.s03resultRepresentation.ResultSetDictionaryExt
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBaseCompound
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId

enum class EQueryResultToStream{
DEFAULT_STREAM,
XML_STREAM,
EMPTY_STREAM ,
EMPTYDICTIONARY_STREAM,
MEMORY_TABLE   ,
XML_ELEMENT 
}
