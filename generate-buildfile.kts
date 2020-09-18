#!/bin/kscript
import java.util.concurrent.TimeUnit
import java.io.File
import java.lang.ProcessBuilder.Redirect

abstract class ChooseableOption(val label: String, val internalID: String) : Comparable<ChooseableOption> {
    companion object {
        val mapLabel = mutableMapOf<String, ChooseableOption>()
        val mapID = mutableMapOf<String, ChooseableOption>()
        operator fun invoke(internalID: String): ChooseableOption {
            var res = mapID[internalID]
            if (res != null) {
                return res
            }
            return ChooseableOptionSimple(internalID)
        }
    }

    init {
        mapLabel[label] = this
        mapID[internalID] = this
    }

    override fun equals(other: Any?) = other is ChooseableOption && internalID == other.internalID
    override fun hashCode() = internalID.hashCode()
    override operator fun compareTo(other: ChooseableOption): Int {
        var tmp = this::class.qualifiedName!!.compareTo(other::class.qualifiedName!!)
        if (tmp != 0)
            return tmp
        return internalID.compareTo(other.internalID)
    }
}

class ChooseableOptionSimple(label: String) : ChooseableOption(label, label) {
    override fun toString() = "Simple($internalID)"
}

class ChooseableOptionCInterop(directory: String) : ChooseableOption(directory, directory) {
    override fun toString() = "CInterop($internalID)"
}

class ChooseableOptionDependency(url: String) : ChooseableOption(url, url) {
    override fun toString() = "Dependency($internalID)"
}

class ChooseableOptionSymbolic(label: String, internalID: String) : ChooseableOption(label, internalID) {
    override fun toString() = "Symbolic($internalID)"
}

class ChooseableOptionDirectory(label: String, val directory: String) : ChooseableOption(label, directory) {
    constructor(directory: String) : this(directory, directory)

    override fun toString() = "Directory($internalID)"
}

class ChooseableOptionTypeAlias(label: String, val pkg: String, val aliasList: List<Pair<String, String>>) : ChooseableOption(label, "common" + pkg + aliasList) {
    override fun toString() = "TypeAlias($internalID)"
}

class ChooseableOptionConstantValue(val pkg: String, val variableName: String, val variableValue: String) : ChooseableOption(variableValue, "common" + pkg + "." + variableName + variableValue) {
    override fun toString() = "ConstantValue($internalID = $variableValue)"
}

class ChoosableOptionExternalScript(label: String, val scriptName: String, internalID: String, val beforeTemplate: Boolean) : ChooseableOption(label, "common" + internalID) {
    override fun toString() = "ExternalScript($scriptName)"
}

class ChooseableGroup(val name: String, val shortcut: String) : Comparable<ChooseableGroup> {
    override fun equals(other: Any?) = other is ChooseableGroup && name == other.name
    override fun hashCode() = name.hashCode()
    override operator fun compareTo(other: ChooseableGroup) = name.compareTo(other.name)
}

var newCommandString = "{"
var allChoicesString = ""
var choicesCount = 0
fun presentUserChoice(group: ChooseableGroup, options: List<ChooseableOption>): ChooseableOption {
    when (options.size) {
        0 -> throw Exception("script error")
        1 -> return options[0]
        else -> {
            println("selecting ${group.name}: choose one of ${options.map { it.label }}")
            while (true) {
                val input2 = readLine()
                if (input2 != null) {
                    val input: String
                    if (input2.startsWith("${group.shortcut}->")) {
                        input = input2.substring(group.shortcut.length + 2, input2.length)
                    } else {
                        input = input2
                    }
                    if (options.map { it.label }.contains(input)) {
                        for (o in options) {
                            if (o.label == input) {
                                allChoicesString += "_${o.label}"
                                newCommandString += "\n  echo \"${group.shortcut}->${o.label}\""
                                return o
                            }
                        }
                        require(false)
                    }
                    try {
                        val i = input.toInt()
                        if (i < options.size) {
                            allChoicesString += "_${options[i].label}"
                            newCommandString += "\n  echo \"${group.shortcut}->${options[i].label}\""
                            return options[i]
                        }
                    } catch (e: Throwable) {
                    }
                }
            }
        }
    }
}

class PrecompileTemplate(val pkg: String, val sourceClass: String, val replacements: List<Pair<String, String>>)

val templates = listOf(
        PrecompileTemplate("lupos.s00misc", "MyListVALUE", listOf("VALUE" to "Int", "GDEF" to "", "GUSE" to "", "ARRAYTYPE" to "IntArray", "ARRAYINITIALIZER" to "")),
        PrecompileTemplate("lupos.s00misc", "MyListVALUE", listOf("VALUE" to "Long", "GDEF" to "", "GUSE" to "", "ARRAYTYPE" to "LongArray", "ARRAYINITIALIZER" to "")),
        PrecompileTemplate("lupos.s00misc", "MyListVALUE", listOf("VALUE" to "Double", "GDEF" to "", "GUSE" to "", "ARRAYTYPE" to "DoubleArray", "ARRAYINITIALIZER" to "")),
        PrecompileTemplate("lupos.s00misc", "MyListVALUE", listOf("VALUE" to "Generic", "GDEF" to "<Generic>", "GUSE" to "<Generic>", "ARRAYTYPE" to "Array<Any?>", "ARRAYINITIALIZER" to "{null}")),
        PrecompileTemplate("lupos.s00misc", "MySetVALUEBinaryTree", listOf("VALUE" to "Int", "GDEF" to "", "GUSE" to "")),
        PrecompileTemplate("lupos.s00misc", "MySetVALUEBinaryTree", listOf("VALUE" to "Long", "GDEF" to "", "GUSE" to "")),
        PrecompileTemplate("lupos.s00misc", "MySetVALUEBinaryTree", listOf("VALUE" to "Double", "GDEF" to "", "GUSE" to "")),
        PrecompileTemplate("lupos.s00misc", "MySetVALUEBinaryTree", listOf("VALUE" to "Generic", "GDEF" to "<Generic : Comparable<Generic>>", "GUSE" to "<Generic>")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Int", "VALUE" to "Int", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Int", "VNAME" to "Int", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Int", "VALUE" to "Long", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Int", "VNAME" to "Long", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Int", "VALUE" to "Double", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Int", "VNAME" to "Double", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Long", "VALUE" to "Int", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Long", "VNAME" to "Int", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Long", "VALUE" to "Long", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Long", "VNAME" to "Long", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Long", "VALUE" to "Double", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Long", "VNAME" to "Double", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Double", "VALUE" to "Int", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Double", "VNAME" to "Int", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Double", "VALUE" to "Long", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Double", "VNAME" to "Long", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Double", "VALUE" to "Double", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Double", "VNAME" to "Double", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "GenericK",
                "VALUE" to "GenericV",
                "GDEF" to "<GenericK : Comparable<GenericK>, GenericV>",
                "GUSEKV" to "<GenericK, GenericV>",
                "GUSEK" to "<GenericK>",
                "GUSEV" to "<GenericV>",
                "KNAME" to "Generic",
                "VNAME" to "Generic",
                "IOSTART1.*IOEND1" to "throw FileIONotImplementedException()", "IOSTART2.*IOEND2" to "throw FileIONotImplementedException()")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Int", "VALUE" to "Generic", "GDEF" to "<Generic>", "GUSEKV" to "<Generic>", "GUSEK" to "", "GUSEV" to "<Generic>", "KNAME" to "Int", "VNAME" to "Generic", "IOSTART1.*IOEND1" to "throw FileIONotImplementedException()", "IOSTART2.*IOEND2" to "throw FileIONotImplementedException()")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Long", "VALUE" to "Generic", "GDEF" to "<Generic>", "GUSEKV" to "<Generic>", "GUSEK" to "", "GUSEV" to "<Generic>", "KNAME" to "Long", "VNAME" to "Generic", "IOSTART1.*IOEND1" to "throw FileIONotImplementedException()", "IOSTART2.*IOEND2" to "throw FileIONotImplementedException()")),
        PrecompileTemplate("lupos.s00misc", "MySetKEYBTree", listOf("KEY" to "Int", "GDEF" to "", "GUSE" to "", "ARRAYTYPE" to "IntArray", "ARRAYINITIALIZER" to "")),
        PrecompileTemplate("lupos.s00misc", "MySetKEYBTree", listOf("KEY" to "Long", "GDEF" to "", "GUSE" to "", "ARRAYTYPE" to "LongArray", "ARRAYINITIALIZER" to "")),
        PrecompileTemplate("lupos.s00misc", "MySetKEYBTree", listOf("KEY" to "Double", "GDEF" to "", "GUSE" to "", "ARRAYTYPE" to "DoubleArray", "ARRAYINITIALIZER" to "")),
        PrecompileTemplate("lupos.s00misc", "MySetKEYBTree", listOf("KEY" to "Generic", "GDEF" to "<Generic : Comparable<Generic>>", "GUSE" to "<Generic>", "ARRAYTYPE" to "Array<Any?>", "ARRAYINITIALIZER" to "{null}")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Int", "VALUE" to "Int", "KNAME" to "Int", "VNAME" to "Int", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "IntArray", "ARRAYVTYPE" to "IntArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Int", "VALUE" to "Long", "KNAME" to "Int", "VNAME" to "Long", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "IntArray", "ARRAYVTYPE" to "LongArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Int", "VALUE" to "Double", "KNAME" to "Int", "VNAME" to "Double", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "IntArray", "ARRAYVTYPE" to "DoubleArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Long", "VALUE" to "Int", "KNAME" to "Long", "VNAME" to "Int", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "LongArray", "ARRAYVTYPE" to "IntArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Long", "VALUE" to "Long", "KNAME" to "Long", "VNAME" to "Long", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "LongArray", "ARRAYVTYPE" to "LongArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Long", "VALUE" to "Double", "KNAME" to "Long", "VNAME" to "Double", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "LongArray", "ARRAYVTYPE" to "DoubleArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Double", "VALUE" to "Int", "KNAME" to "Double", "VNAME" to "Int", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "DoubleArray", "ARRAYVTYPE" to "IntArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Double", "VALUE" to "Long", "KNAME" to "Double", "VNAME" to "Long", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "DoubleArray", "ARRAYVTYPE" to "LongArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Double", "VALUE" to "Double", "KNAME" to "Double", "VNAME" to "Double", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "DoubleArray", "ARRAYVTYPE" to "DoubleArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART1" to "", "IOEND1" to "", "IOSTART2" to "", "IOEND2" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf(
                "KEY" to "Int",
                "VALUE" to "GenericV",
                "KNAME" to "Int",
                "VNAME" to "Generic",
                "GDEF" to "<GenericV>",
                "GUSE" to "<GenericV>",
                "ARRAYKTYPE" to "IntArray",
                "ARRAYVTYPE" to "Array<Any?>",
                "ARRAYKINITIALIZER" to "",
                "ARRAYVINITIALIZER" to "{null}",
                "IOSTART1.*IOEND1" to "throw FileIONotImplementedException()", "IOSTART2.*IOEND2" to "throw FileIONotImplementedException()"
        )),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf(
                "KEY" to "Long",
                "VALUE" to "GenericV",
                "KNAME" to "Long",
                "VNAME" to "Generic",
                "GDEF" to "<GenericV>",
                "GUSE" to "<GenericV>",
                "ARRAYKTYPE" to "LongArray",
                "ARRAYVTYPE" to "Array<Any?>",
                "ARRAYKINITIALIZER" to "",
                "ARRAYVINITIALIZER" to "{null}",
                "IOSTART1.*IOEND1" to "throw FileIONotImplementedException()", "IOSTART2.*IOEND2" to "throw FileIONotImplementedException()"
        )),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf(
                "KEY" to "Double",
                "VALUE" to "GenericV",
                "KNAME" to "Double",
                "VNAME" to "Generic",
                "GDEF" to "<GenericV>",
                "GUSE" to "<GenericV>",
                "ARRAYKTYPE" to "DoubleArray",
                "ARRAYVTYPE" to "Array<Any?>",
                "ARRAYKINITIALIZER" to "",
                "ARRAYVINITIALIZER" to "{null}",
                "IOSTART1.*IOEND1" to "throw FileIONotImplementedException()", "IOSTART2.*IOEND2" to "throw FileIONotImplementedException()"
        )),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf(
                "KEY" to "GenericK",
                "VALUE" to "GenericV",
                "KNAME" to "Generic",
                "VNAME" to "Generic",
                "GDEF" to "<GenericK : Comparable<GenericK>, GenericV>",
                "GUSE" to "<GenericK, GenericV>",
                "ARRAYKTYPE" to "Array<Any?>",
                "ARRAYVTYPE" to "Array<Any?>",
                "ARRAYKINITIALIZER" to "{null}",
                "ARRAYVINITIALIZER" to "{null}",
                "IOSTART1.*IOEND1" to "throw FileIONotImplementedException()", "IOSTART2.*IOEND2" to "throw FileIONotImplementedException()"
        ))
)

val options = mapOf<ChooseableGroup, List<ChooseableOption>>(
        ChooseableGroup("Launch Type", "Launch") to listOf(
                ChooseableOptionDirectory("Endpoint", "commonS00LaunchEndpointMain"),
                ChooseableOptionDirectory("SparqlTestSuite", "commonS00LaunchSparqlTestSuiteMain"),
                ChooseableOptionDirectory("Benchmark", "commonS00LaunchBenchmarkMain"),
                ChooseableOptionDirectory("BenchmarkJena", "commonS00LaunchBenchmarkJenaMain"),
                ChooseableOptionDirectory("Import", "commonS00LaunchImportMain")
        ),
        ChooseableGroup("Sanity Checks", "Sanity") to listOf(
                ChooseableOptionTypeAlias("On", "lupos.s00misc", listOf("SanityCheck" to "SanityCheckOn", "CoroutinesHelperMutex" to "Lock")),
                ChooseableOptionTypeAlias("Off", "lupos.s00misc", listOf("SanityCheck" to "SanityCheckOff", "CoroutinesHelperMutex" to "Lock"))
        ),
        ChooseableGroup("Execution", "Execution") to listOf(
                ChooseableOptionTypeAlias("Sequential", "lupos.s00misc", listOf("CoroutinesHelper" to "CoroutinesHelperSequential")),
                ChooseableOptionTypeAlias("Parallel", "lupos.s00misc", listOf("CoroutinesHelper" to "CoroutinesHelperParallel"))
        ),
        ChooseableGroup("Buffer Manager Type", "BufferManager") to listOf(
                ChooseableOptionDirectory("Heap", "commonS01HeapMain"),
        ),
        ChooseableGroup("Dictionary", "Dictionary") to listOf(
                ChooseableOptionDirectory("MultiMap", "commonS03DictionaryMultiMapMain"),
                ChooseableOptionDirectory("ObjectMap", "commonS03DictionaryObjectMapMain"),
		ChooseableOptionDirectory("Small", "commonS03DictionarySmallMain")
        ),
        ChooseableGroup("Triple Store", "TripleStore") to listOf(
                ChooseableOptionTypeAlias("BPlusTreePartition", "lupos.s05tripleStore", listOf("TripleStoreLocal" to "TripleStoreLocalBPlusTreePartition")),
                ChooseableOptionTypeAlias("BPlusTree", "lupos.s05tripleStore", listOf("TripleStoreLocal" to "TripleStoreLocalBPlusTree")),
                ChooseableOptionTypeAlias("MapMapList", "lupos.s05tripleStore", listOf("TripleStoreLocal" to "TripleStoreLocalMapMapList")),
                ChooseableOptionTypeAlias("SingleList", "lupos.s05tripleStore", listOf("TripleStoreLocal" to "TripleStoreLocalSingleList"))
        ),
        ChooseableGroup("HttpEndpoint implementation", "Endpoint") to listOf(
                ChooseableOptionDirectory("JavaNet", "jvmS16HttpEndpointJavaNetMain"),
                ChooseableOptionDirectory("Korio", "jvmS16HttpEndpointKorioMain"),
                ChooseableOptionDirectory("None", "commonS16HttpEndpointNoneMain")
        ),
        ChooseableGroup("Include Jena Wrapper", "Jena") to listOf(
                ChooseableOptionTypeAlias("Off", "lupos.s00misc", listOf("JenaWrapper" to "JenaWrapperOff")),
                ChooseableOptionDirectory("On", "jvmS00WrapperJenaOnMain")
        ),
        ChooseableGroup("Set Implementation", "Set") to listOf(
                ChooseableOptionTypeAlias("BTree", "lupos.s00misc", listOf(
                        "MySetGeneric<T>" to "MySetGenericBTree<T>",
                        "MySetLong" to "MySetLongBTree",
                        "MySetInt" to "MySetIntBTree",
                        "MySetDouble" to "MySetDoubleBTree"
                )),
                ChooseableOptionTypeAlias("Bisection", "lupos.s00misc", listOf(
                        "MySetGeneric<T>" to "MySetGenericBinaryTree<T>",
                        "MySetLong" to "MySetLongBinaryTree",
                        "MySetInt" to "MySetIntBinaryTree",
                        "MySetDouble" to "MySetDoubleBinaryTree"
                ))
        ),
        ChooseableGroup("Map Implementation", "Map") to listOf(
                ChooseableOptionTypeAlias("BTree", "lupos.s00misc", listOf(
                        "MyMapGenericGeneric<K,V>" to "MyMapGenericGenericBTree<K,V>",
                        "MyMapIntGeneric<T>" to "MyMapIntGenericBTree<T>",
                        "MyMapLongGeneric<T>" to "MyMapLongGenericBTree<T>",
                        "MyMapLongInt" to "MyMapLongIntBTree",
                        "MyMapIntInt" to "MyMapIntIntBTree",
                        "MyMapDoubleInt" to "MyMapDoubleIntBTree"
                )),
                ChooseableOptionTypeAlias("Bisection", "lupos.s00misc", listOf(
                        "MyMapGenericGeneric<K,V>" to "MyMapIntGenericBinaryTree<K,V>",
                        "MyMapIntGeneric<T>" to "MyMapIntGenericBinaryTree<T>",
                        "MyMapLongGeneric<T>" to "MyMapLongGenericBinaryTree<T>",
                        "MyMapLongInt" to "MyMapLongIntBinaryTree",
                        "MyMapIntInt" to "MyMapIntIntBinaryTree",
                        "MyMapDoubleInt" to "MyMapDoubleIntBinaryTree"
                ))
        ),
        ChooseableGroup("Default Result Format", "OutputFormat") to listOf(
                ChooseableOptionTypeAlias("Empty", "lupos.s11outputResult", listOf("QueryResultToStream" to "QueryResultToEmptyStream")),
                ChooseableOptionTypeAlias("XML", "lupos.s11outputResult", listOf("QueryResultToStream" to "QueryResultToXMLStream")),
                ChooseableOptionTypeAlias("EmptyWithDictionary", "lupos.s11outputResult", listOf("QueryResultToStream" to "QueryResultToEmptyWithDictionaryStream"))
        ),
        ChooseableGroup("Enumerate Bnodes", "EnumerateBnodes") to listOf(
                ChooseableOptionConstantValue("lupos.s11outputResult", "PRETTY_BNODE_NAMES", "true"),
                ChooseableOptionConstantValue("lupos.s11outputResult", "PRETTY_BNODE_NAMES", "false"),
        ),
        ChooseableGroup("PageSize in Bytes", "Pagesize") to listOf(
                ChooseableOptionConstantValue("lupos.s00misc", "PAGE_SIZE_IN_BYTES", "128"),
                ChooseableOptionConstantValue("lupos.s00misc", "PAGE_SIZE_IN_BYTES", "256"),
                ChooseableOptionConstantValue("lupos.s00misc", "PAGE_SIZE_IN_BYTES", "512"),
                ChooseableOptionConstantValue("lupos.s00misc", "PAGE_SIZE_IN_BYTES", "1024"),
                ChooseableOptionConstantValue("lupos.s00misc", "PAGE_SIZE_IN_BYTES", "2048"),
                ChooseableOptionConstantValue("lupos.s00misc", "PAGE_SIZE_IN_BYTES", "4096"),
                ChooseableOptionConstantValue("lupos.s00misc", "PAGE_SIZE_IN_BYTES", "8196")
        ),
        ChooseableGroup("ArrayList Block Capacity in Elements", "BlockCapacity") to listOf(
                ChooseableOptionConstantValue("lupos.s00misc", "ARRAY_LIST_BLOCK_CAPACITY", "8"),
                ChooseableOptionConstantValue("lupos.s00misc", "ARRAY_LIST_BLOCK_CAPACITY", "16"),
                ChooseableOptionConstantValue("lupos.s00misc", "ARRAY_LIST_BLOCK_CAPACITY", "32"),
                ChooseableOptionConstantValue("lupos.s00misc", "ARRAY_LIST_BLOCK_CAPACITY", "64"),
                ChooseableOptionConstantValue("lupos.s00misc", "ARRAY_LIST_BLOCK_CAPACITY", "128"),
                ChooseableOptionConstantValue("lupos.s00misc", "ARRAY_LIST_BLOCK_CAPACITY", "256"),
                ChooseableOptionConstantValue("lupos.s00misc", "ARRAY_LIST_BLOCK_CAPACITY", "512"),
                ChooseableOptionConstantValue("lupos.s00misc", "ARRAY_LIST_BLOCK_CAPACITY", "1024"),
                ChooseableOptionConstantValue("lupos.s00misc", "ARRAY_LIST_BLOCK_CAPACITY", "1048576"),
                ChooseableOptionConstantValue("lupos.s00misc", "ARRAY_LIST_BLOCK_CAPACITY", "134217728")
        ),
        ChooseableGroup("BTree Branching Faktor", "BTreeBranching") to listOf(
                ChooseableOptionConstantValue("lupos.s00misc", "B_TREE_BRANCHING_FACTOR", "8"),
                ChooseableOptionConstantValue("lupos.s00misc", "B_TREE_BRANCHING_FACTOR", "16"),
                ChooseableOptionConstantValue("lupos.s00misc", "B_TREE_BRANCHING_FACTOR", "32"),
                ChooseableOptionConstantValue("lupos.s00misc", "B_TREE_BRANCHING_FACTOR", "64"),
                ChooseableOptionConstantValue("lupos.s00misc", "B_TREE_BRANCHING_FACTOR", "128"),
                ChooseableOptionConstantValue("lupos.s00misc", "B_TREE_BRANCHING_FACTOR", "256"),
                ChooseableOptionConstantValue("lupos.s00misc", "B_TREE_BRANCHING_FACTOR", "512")
        ),
        ChooseableGroup("Merge Sort Minimal Rows", "MergeSortRows") to listOf(
                ChooseableOptionConstantValue("lupos.s04logicalOperators.iterator", "MERGE_SORT_MIN_ROWS", "8"),
                ChooseableOptionConstantValue("lupos.s04logicalOperators.iterator", "MERGE_SORT_MIN_ROWS", "16"),
                ChooseableOptionConstantValue("lupos.s04logicalOperators.iterator", "MERGE_SORT_MIN_ROWS", "32"),
                ChooseableOptionConstantValue("lupos.s04logicalOperators.iterator", "MERGE_SORT_MIN_ROWS", "64"),
                ChooseableOptionConstantValue("lupos.s04logicalOperators.iterator", "MERGE_SORT_MIN_ROWS", "128"),
                ChooseableOptionConstantValue("lupos.s04logicalOperators.iterator", "MERGE_SORT_MIN_ROWS", "256"),
                ChooseableOptionConstantValue("lupos.s04logicalOperators.iterator", "MERGE_SORT_MIN_ROWS", "512")
        ),
        ChooseableGroup("Bulk-Import Triples per Block", "BulkImportBlockSize") to listOf(
                ChooseableOptionConstantValue("lupos.s05tripleStore", "BULK_IMPORT_BLOCK_SIZE", "8"),
                ChooseableOptionConstantValue("lupos.s05tripleStore", "BULK_IMPORT_BLOCK_SIZE", "1024"),
                ChooseableOptionConstantValue("lupos.s05tripleStore", "BULK_IMPORT_BLOCK_SIZE", "32768"),
                ChooseableOptionConstantValue("lupos.s05tripleStore", "BULK_IMPORT_BLOCK_SIZE", "1048576")
        ),
        ChooseableGroup("Replace small triple-store results during optimisation phase", "AdvancedOptimisation") to listOf(
                ChooseableOptionConstantValue("lupos.s08logicalOptimisation", "REPLACE_STORE_WITH_VALUES", "true"),
                ChooseableOptionConstantValue("lupos.s08logicalOptimisation", "REPLACE_STORE_WITH_VALUES", "false")
        ),
        ChooseableGroup("Code Coverage mode", "Coverage") to listOf(
                ChooseableOptionConstantValue("lupos.s00misc", "COVERAGE_MODE", "ECoverage.Disabled"),
                ChooseableOptionConstantValue("lupos.s00misc", "COVERAGE_MODE", "ECoverage.Count"),
                ChooseableOptionConstantValue("lupos.s00misc", "COVERAGE_MODE", "ECoverage.Verbose"),
                ChooseableOptionConstantValue("lupos.s00misc", "COVERAGE_MODE", "ECoverage.VeryVerbose")
        ),
        ChooseableGroup("Generate Code-Coverage-Code", "CoverageGenerate") to listOf(
                ChooseableOptionSymbolic("DontChange", "commonCoverageModeDontChange"),
                ChoosableOptionExternalScript("On", "./tool-coverage-enable.sh", "CoverageModeOn", true),
                ChoosableOptionExternalScript("Off", "./tool-coverage-disable.sh", "CoverageModeOff", true)
        ),
        ChooseableGroup("ServerCommunication implementation", "ServerCommunication") to listOf(
                ChooseableOptionDirectory("None", "commonS16ServerCommunicationNoneMain"),
                ChooseableOptionDirectory("Ktor", "jvmS16ServerCommunicationKtorMain"),
                ChooseableOptionDirectory("Sockets", "jvmS16ServerCommunicationSocketsMain")
        ),
        ChooseableGroup("ServerCommunication target packet size", "ServerCommunicationPacketSize") to listOf(
                ChooseableOptionConstantValue("lupos.s16network", "NETWORK_PACKET_SIZE", "8196")
        ),
        ChooseableGroup("ServerCommunication minimal triples per packet", "ServerCommunicationTriplesPerPacket") to listOf(
                ChooseableOptionConstantValue("lupos.s16network", "NETWORK_PACKET_MIN_TRIPLES", "128")
        ),
        ChooseableGroup("ServerCommunication default port", "ServerCommunicationPort") to listOf(
                ChooseableOptionConstantValue("lupos.s16network", "NETWORK_DEFAULT_PORT", "2323")
        ),
        ChooseableGroup("Max Triples During Extreme Testing", "MaxTriplesDuringTest") to listOf(
                ChooseableOptionConstantValue("lupos.s00misc", "MAX_TRIPLES_DURING_TEST", "400"),
                ChooseableOptionConstantValue("lupos.s00misc", "MAX_TRIPLES_DURING_TEST", "2000"),
                ChooseableOptionConstantValue("lupos.s00misc", "MAX_TRIPLES_DURING_TEST", "-1")
        ),
        ChooseableGroup("Use connection pool for server communication", "ConnectionPool") to listOf(
                ChooseableOptionTypeAlias("Off", "lupos.s16network", listOf("ServerCommunicationConnectionPool" to "ServerCommunicationConnectionPoolOff")),
                ChooseableOptionTypeAlias("On", "lupos.s16network", listOf("ServerCommunicationConnectionPool" to "ServerCommunicationConnectionPoolOn"))
        ),
        ChooseableGroup("Inline", "Inline") to listOf(
                ChooseableOptionSymbolic("DontChange", "commonInlineModeDontChange"),
                ChoosableOptionExternalScript("On", "./tool-inline-enable.sh", "InlineModeOn", false),
                ChoosableOptionExternalScript("Off", "./tool-inline-disable.sh", "InlineModeOff", false)
        ),
        ChooseableGroup("BigInteger Implementation", "BigInteger") to listOf(
                ChooseableOptionTypeAlias("jvmBigInteger", "lupos.s00misc", listOf("BigInteger" to "java.math.BigInteger"))
        ),
        ChooseableGroup("BigDecimal Implementation", "BigDecimal") to listOf(
                ChooseableOptionTypeAlias("jvmBigDecimal", "lupos.s00misc", listOf("BigDecimal" to "java.math.BigDecimal"))
        ),
        ChooseableGroup("MathContext Implementation", "BigDecimal") to listOf(
                ChooseableOptionTypeAlias("jvmMathContext", "lupos.s00misc", listOf("MathContext" to "java.math.MathContext"))
        ),
        ChooseableGroup("Use Partitions for parallel Join", "UsePartitions") to listOf(
                ChooseableOptionConstantValue("lupos.s10physicalOptimisation", "USE_PARTITIONS", "true"),
                ChooseableOptionConstantValue("lupos.s10physicalOptimisation", "USE_PARTITIONS", "false")
        ),
        ChooseableGroup("Iterator Debug mode", "IteratorDebug") to listOf(
                ChooseableOptionConstantValue("lupos.s09physicalOperators.singleinput", "ITERATOR_DEBUG_MODE", "EPOPDebugMode.NONE"),
                ChooseableOptionConstantValue("lupos.s09physicalOperators.singleinput", "ITERATOR_DEBUG_MODE", "EPOPDebugMode.DEBUG1"),
                ChooseableOptionConstantValue("lupos.s09physicalOperators.singleinput", "ITERATOR_DEBUG_MODE", "EPOPDebugMode.DEBUG2")
        ),
)


var additionalSources = mapOf<ChooseableOption, List<ChooseableOption>>()
val allChoosenOptions = mutableSetOf<ChooseableOption>(ChooseableOptionDirectory("commonMain"), ChooseableOptionDirectory("commonConfig"))
fun resetAllChoosenOptions() {
    allChoosenOptions.clear()
    allChoosenOptions.add(ChooseableOptionDirectory("commonMain"))
    allChoosenOptions.add(ChooseableOptionDirectory("commonConfig"))
}

fun addAdditionalSources() {
    var changed = true
    while (changed) {
        changed = false
        for (option in allChoosenOptions) {
            val additionalSource = additionalSources[option]
            if (additionalSource != null)
                for (s in additionalSource)
                    if (!allChoosenOptions.contains(s)) {
                        allChoosenOptions.add(s)
                        changed = true
                    }
            if (changed)
                break
        }
    }
}

var platformPrefix = mapOf(
        "linuxX64" to listOf("common", "linuxX64", "native"),
        "macosX64" to listOf("common", "macosX64", "native"),
        "mingw64" to listOf("common"),
        "jvm" to listOf("common", "jvm")
)
/*--->>> autogenerating all possible build-files*/
var autoGenerateAllChoosenOptionsList = mutableSetOf<ChooseableOption>()
var autoGenerateAllNotChoosenOptionsList = mutableSetOf<ChooseableOption>()
var autoGenerateAllChoicesString = ""
fun presentAutoChoice(group: ChooseableGroup, options: List<ChooseableOption>): ChooseableOption {
    if (options.size == 1) {
        autoGenerateAllChoosenOptionsList.add(options[0])
        autoGenerateAllNotChoosenOptionsList.remove(options[0])
        return options[0]//only possibility
    } else {
        var argsFilter = false
        for (o in options) {
            if (args.contains(o.label) || args.contains(group.shortcut + "->" + o.label)) {
                argsFilter = true
            }
        }
        for (o in options) {
            if (!autoGenerateAllChoosenOptionsList.contains(o) && (!argsFilter || args.contains(o.label) || args.contains(group.shortcut + "->" + o.label))) {
                autoGenerateAllNotChoosenOptionsList.remove(o)
                autoGenerateAllChoosenOptionsList.add(o)
                for (o2 in options) {
                    if (!autoGenerateAllChoosenOptionsList.contains(o2) && (!argsFilter || args.contains(o2.label) || args.contains(group.shortcut + "->" + o2.label))) {
                        var flag = true
                        if (o2 is ChooseableOptionConstantValue) {
//numeric values should not be the only reason to compile more often
                            try {
                                o2.variableValue.toDouble()
                                flag = false
                            } catch (e: Throwable) {
                            }
                        }
                        if (flag) {
                            autoGenerateAllNotChoosenOptionsList.add(o2)
                        }
                    }
                }
                autoGenerateAllChoicesString += "\n  echo ${o.label}"
                return o//something new
            }
        }
        if (argsFilter) {
            for (o in options) {
                if (args.contains(o.label) || args.contains(group.shortcut + "->" + o.label)) {
                    autoGenerateAllChoicesString += "\n  echo ${o.label}"
                    return o//anything, since all were choosen at least once
                }
            }
        }
        autoGenerateAllChoicesString += "\n  echo ${options[0].label}"
        return options[0]//anything, since all were choosen at least once
    }
}

var done = false
var autogeneratemode = args.size > 0 && args[0] == "listAll"
while (!done) {
    resetAllChoosenOptions()
    var presentChoice: (ChooseableGroup, List<ChooseableOption>) -> ChooseableOption
    if (autogeneratemode) {
        presentChoice = ::presentAutoChoice
        done = autoGenerateAllNotChoosenOptionsList.size == 0 && autoGenerateAllChoicesString.length != 0
        autoGenerateAllChoicesString += "\n{"
    } else {
        presentChoice = ::presentUserChoice
        done = true
    }
/*<<<--- autogenerating all possible build-files*/

    val conflicts = listOf(
            setOf("commonCoverageModeOff", "commonlupos.s00misc.COVERAGE_MODEECoverage.Count", "commonlupos.s00misc.COVERAGE_MODEECoverage.Verbose")
    )
    val ktorVersion = presentChoice(ChooseableGroup("ktor-version", "KtorVersion"), listOf(ChooseableOption("1.3.2-1.4-M1-2"))).label
    val kotlinVersion = presentChoice(ChooseableGroup("kotlin-version", "KotlinVersion"), listOf(ChooseableOption("1.3.70"), ChooseableOption("1.4.255-SNAPSHOT"))).label
    val platform = presentChoice(ChooseableGroup("Platform", "Platform"), platformPrefix.keys.toList().map { ChooseableOption(it) }).label

    additionalSources = mapOf(
/*if the key is choosen, automatically add all dependent things*/

            ChooseableOption("jvmS16ServerCommunicationSocketsMain") to listOf(
                    ChooseableOptionDirectory("commonS16ServerCommunicationEnabledMain")
            ),
            ChooseableOption("commonMain") to listOf(
                    ChooseableOptionDependency("org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"),
                    ChooseableOptionDependency("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"),
                    ChooseableOptionDependency("com.benasher44:uuid:0.0.7"),
                    ChooseableOptionDependency("com.soywiz.korlibs.krypto:krypto:1.9.1")
            ),
            ChooseableOption("jvmMain") to listOf(
                    ChooseableOptionDependency("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"),
                    ChooseableOptionDependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"),
                    ChooseableOptionDependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3"),
                    ChooseableOptionDependency("org.slf4j:slf4j-simple:1.7.25")
            ),
            ChooseableOption("jvmS00WrapperJenaOnMain") to listOf(
                    ChooseableOptionDependency("org.apache.jena:jena-core:3.14.0"),
                    ChooseableOptionDependency("org.apache.jena:jena-arq:3.14.0")
            ),
            ChooseableOption("jvmS16ServerCommunicationKtorMain") to listOf(
                    ChooseableOptionDirectory("commonS16ServerCommunicationEnabledMain"),
                    ChooseableOptionDependency("io.ktor:ktor-network:$ktorVersion")
            ),
            ChooseableOption("jvmS16HttpEndpointKorioMain") to listOf(
                    ChooseableOptionDependency("com.soywiz.korlibs.korio:korio:1.9.9-SNAPSHOT")
            ),
            ChooseableOption("linuxX64Main") to listOf(
                    ChooseableOptionDirectory("nativeMain"),
            ),
            ChooseableOption("macosX64Main") to listOf(
                    ChooseableOptionDirectory("nativeMain"),
            ),
            ChooseableOption("nativeMain") to listOf(
                    ChooseableOptionDependency("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.3"),
                    ChooseableOptionCInterop("dirent"),
                    ChooseableOptionCInterop("stdio"),
                    ChooseableOptionCInterop("unistd")
            )
    )
    allChoosenOptions.add(ChooseableOptionDirectory("${platform}Main"))
    for ((k, choices) in options) {
        var alreadyChoosen = false
        for (choice in choices)
            if (allChoosenOptions.contains(choice)) {
                alreadyChoosen = true
                break
            }
        if (!alreadyChoosen) {
            val remainingChoices = mutableListOf<ChooseableOption>()
            for (choice in choices) {
                var ok = false
                for (prefix in platformPrefix[platform]!!)
                    if (choice.internalID.startsWith(prefix)) {
                        ok = true
                        break
                    }
                for (conflict in conflicts)
                    if (conflict.contains(choice.internalID))
                        for (option in allChoosenOptions)
                            if (conflict.contains(option.internalID)) {
                                ok = false
                            }
                if (ok)
                    remainingChoices.add(choice)
            }
            val choice = presentChoice(k, remainingChoices)
            allChoosenOptions.add(choice)
            addAdditionalSources()
        }
    }
    if (autogeneratemode) {
        autoGenerateAllChoicesString += "\n} | ./generate-buildfile.kts"
    } else {
        println("result choices :: ")
        for (option in allChoosenOptions.sorted())
            println(option.toString() + " :: " + option.internalID)
        allChoicesString = allChoicesString.replace("Main", "").replace("common", "")

        File("build.gradle.kts").printWriter().use { out ->
            when (platform) {
                "jvm" -> {

                    out.println("""import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
tasks.withType<KotlinCompile>().all {
    //kotlinOptions.jvmTarget = "14"
    kotlinOptions.jvmTarget = "1.8"
    //see /opt/kotlin/compiler/cli/cli-common/src/org/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments.kt
    //or kotlinc -X
    kotlinOptions.freeCompilerArgs += "-Xno-param-assertions"
    kotlinOptions.freeCompilerArgs += "-Xuse-ir"
    kotlinOptions.freeCompilerArgs += "-Xnew-inference"
    kotlinOptions.freeCompilerArgs += "-Xno-receiver-assertions"
    kotlinOptions.freeCompilerArgs += "-Xno-call-assertions"
}""")
                }
                else -> {
                    out.println("""import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
tasks.withType<KotlinCompile>().all {
    kotlinOptions.freeCompilerArgs += "-Xno-param-assertions"
}""")
                }
            }
            out.println("""buildscript {
    repositories {
        jcenter()
        google()
        mavenLocal()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-frontend-plugin:0.0.26")
        classpath("com.moowork.gradle:gradle-node-plugin:1.2.0")
    }
}""")
            when (platform) {
                "jvm" -> {
                    out.println("""plugins {
    id("org.jetbrains.kotlin.jvm") version "$kotlinVersion"
    id("com.github.johnrengelman.shadow") version "5.1.0"
    application
}
application {
    mainClassName = "MainKt"
}
tasks.withType<ShadowJar>() {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
}
repositories {
    jcenter()
    google()
    mavenLocal()
    mavenCentral()
    maven("http://dl.bintray.com/kotlin/kotlin-eap-1.2")
    maven("https://kotlin.bintray.com/kotlinx")
}
project.buildDir = file("build/build$allChoicesString")
dependencies {""")
                    for (option in allChoosenOptions.sorted())
                        if (option is ChooseableOptionDependency)
                            out.println("    implementation(\"${option.internalID}\")")
                    out.println("""}""")
                    for (option in allChoosenOptions.sorted()) {
                        if (option is ChooseableOptionDirectory) {
                            out.println("sourceSets[\"main\"].java.srcDir(\"src.generated/${option.internalID}/kotlin\")")
                        }
                    }
                }
                else -> {
                    out.println("""plugins {
    id("org.jetbrains.kotlin.multiplatform") version "$kotlinVersion"
}
repositories {
    jcenter()
    google()
    mavenLocal()
    mavenCentral()
    maven("http://dl.bintray.com/kotlin/kotlin-eap-1.2")
    maven("https://kotlin.bintray.com/kotlinx")
}
kotlin {
    project.buildDir = file("build/build$allChoicesString")
    ${platform}("${platform}") {
        val main by compilations.getting""")
                    for (option in allChoosenOptions.sorted()) {
                        if (option is ChooseableOptionCInterop)
                            out.println("        val ${option.internalID} by main.cinterops.creating")
                    }
                    out.println("""        binaries {
            executable()
        }
    }
    sourceSets["commonMain"].dependencies {""")
                    for (option in allChoosenOptions.sorted())
                        if (option is ChooseableOptionDependency)
                            out.println("        implementation(\"${option.internalID}\")")
                    out.println("""    }""")
                    for (option in allChoosenOptions.sorted())
                        if (option is ChooseableOptionDirectory) {
                            if (option.internalID.startsWith("common"))
                                out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"src.generated/${option.internalID}/kotlin\")")
                            else
                                out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"src.generated/${option.internalID}/kotlin\")")
                        }
                    out.println("""}""")
                }
            }
        }
        try {
            File("build.gradle.kts").copyTo(File("build/script${allChoicesString}.gradle.kts"))
        } catch (e: FileAlreadyExistsException) {
        }
        File("src.generated/commonConfig").deleteRecursively()
        val configFilesContent = mutableMapOf<String, StringBuilder>()
        for (option in allChoosenOptions) {
            //first all alias definitions
            if (option is ChooseableOptionTypeAlias) {
                var f = configFilesContent[option.pkg]
                if (f == null) {
                    f = StringBuilder()
                    f!!.append("/* this File is autogenerated by generate-buildfile.kts */\n")
                    f!!.append("/* DO NOT MODIFY DIRECTLY */\n")
                    f!!.append("package ${option.pkg}\n")
                    configFilesContent[option.pkg] = f!!
                }
                val fc = f!!
                for (alias in option.aliasList) {
                    fc.append("typealias ${alias.first} = ${alias.second}\n")
                }
            }
        }
        for (option in allChoosenOptions) {
            //than all constant definitions
            if (option is ChooseableOptionConstantValue) {
                var f = configFilesContent[option.pkg]
                if (f == null) {
                    f = StringBuilder()
                    f!!.append("/* this File is autogenerated by generate-buildfile.kts */\n")
                    f!!.append("/* DO NOT MODIFY DIRECTLY */\n")
                    f!!.append("package ${option.pkg}\n")
                    configFilesContent[option.pkg] = f!!
                }
                val fc = f!!
                if (option.variableValue.startsWith("\"")) {
                    fc.append("const val ${option.variableName} = ${option.variableValue}\n")
                } else {
                    try {
                        var x = option.variableValue.toDouble()
                        fc.append("const val ${option.variableName} = ${option.variableValue}\n")
                    } catch (e: Throwable) {
                        fc.append("val ${option.variableName} = ${option.variableValue}\n")
                    }
                }
            }
        }
        fun String.runCommand(workingDir: File? = null) {
            val process = ProcessBuilder(*split(" ").toTypedArray())
                    .directory(workingDir)
                    .redirectOutput(Redirect.INHERIT)
                    .redirectError(Redirect.INHERIT)
                    .start()
            if (!process.waitFor(60, TimeUnit.SECONDS)) {
                process.destroy()
                throw RuntimeException("execution timed out: $this")
            }
            if (process.exitValue() != 0) {
                throw RuntimeException("execution failed with code ${process.exitValue()}: $this")
            }
        }
//copy to save location
        File("src.generated").deleteRecursively()
        for (option in allChoosenOptions) {
            if (option is ChooseableOptionDirectory && option.internalID != "commonConfig") {
                File("src/${option.internalID}").copyRecursively(File("src.generated/${option.internalID}"))
            }
        }
        File("src/commonTemplate").copyRecursively(File("src.generated/commonTemplate"))
//perform scripts "before template"
        for (option in allChoosenOptions) {
            if (option is ChoosableOptionExternalScript && option.beforeTemplate) {
                println("running script before ${option.scriptName}")
                option.scriptName.runCommand()
            }
        }
//create config files as defined by above configuration
        for ((k, v) in configFilesContent) {
            File("src.generated/commonConfig/kotlin/" + k.replace(".", "/")).mkdirs()
            File("src.generated/commonConfig/kotlin/" + k.replace(".", "/") + "/Config.kt").printWriter().use { out ->
                out.print(v.toString())
            }
        }
//expand the template files
        for (template in templates) {
            val sourceFile = File("src.generated/commonTemplate/kotlin/" + template.pkg.replace(".", "/") + "/" + template.sourceClass + ".kt")
            var fileContent = sourceFile.readText()
            var targetClass = template.sourceClass
            for (replacement in template.replacements) {
                targetClass = targetClass.replace(replacement.first.toRegex(), replacement.second)
                fileContent = replacement.first.toRegex(RegexOption.DOT_MATCHES_ALL).replace(fileContent, replacement.second)
            }
            val targetFile = File("src.generated/commonConfig/kotlin/" + template.pkg.replace(".", "/") + "/" + targetClass + ".kt")
            targetFile.printWriter().use {
                it.println("/* this File is autogenerated by generate-buildfile.kts */")
                it.println("/* DO NOT MODIFY DIRECTLY */")
                it.print(fileContent)
            }
        }
//perform scripts "after template"
        for (option in allChoosenOptions) {
            if (option is ChoosableOptionExternalScript && !option.beforeTemplate) {
                println("running script after ${option.scriptName}")
                option.scriptName.runCommand()
            }
        }
        println(newCommandString + "\n} | ./generate-buildfile.kts")
    }
}
println(autoGenerateAllChoicesString)
