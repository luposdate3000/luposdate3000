import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.util.concurrent.TimeUnit

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

class ChoosableOptionInternalScript(label: String, val action: () -> Unit, internalID: String, val beforeTemplate: Boolean) : ChooseableOption(label, "common" + internalID) {
    override fun toString() = "InternalScript($internalID)"
}

class ChooseableGroup(val name: String, val shortcut: String) : Comparable<ChooseableGroup> {
    override fun equals(other: Any?) = other is ChooseableGroup && name == other.name
    override fun hashCode() = name.hashCode()
    override operator fun compareTo(other: ChooseableGroup) = name.compareTo(other.name)
}

class PrecompileTemplate(val pkg: String, val sourceClass: String, val replacements: List<Pair<String, String>>)
class GenerateBuildFile(val args: Array<String>) {
    var autoGenerateAllChoosenOptionsList = mutableSetOf<ChooseableOption>()
    var autoGenerateAllNotChoosenOptionsList = mutableSetOf<ChooseableOption>()
    var autoGenerateAllChoicesString = ""
    var allChoicesString = ""
    var choicesCount = 0
    val myReadLineCache = mutableMapOf<String, String>()
    var newCommandString = java.io.PrintWriter(java.io.StringWriter())
    var additionalSources = mapOf<ChooseableOption, List<ChooseableOption>>()
    val allChoosenOptions = mutableSetOf<ChooseableOption>(ChooseableOptionDirectory("commonMain"), ChooseableOptionDirectory("commonConfig"))
    var platformPrefix = mapOf(
            "linuxX64" to listOf("common", "linuxX64", "native"),
            "macosX64" to listOf("common", "macosX64", "native"),
            "mingw64" to listOf("common"),
            "jvm" to listOf("common", "jvm")
    )

    init {
        for (a in args) {
            if (a.startsWith("--file=")) {
                val f = a.substring("--file=".length)
                java.io.File(f).forEachLine {
                    val b = it.split("->")
                    if (b.size == 2) {
                        myReadLineCache[b[0]] = b[1]
                    } else {
                        throw Exception("invalid input '$it'")
                    }
                }
                newCommandString = java.io.File(f).printWriter()
                break
            }
        }
    }

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
                    ChooseableOptionDirectory("BinaryTestSuite", "commonS00LaunchBinaryTestSuiteMain"),
                    ChooseableOptionDirectory("Benchmark", "commonS00LaunchBenchmarkMain"),
                    ChooseableOptionDirectory("BenchmarkJena", "commonS00LaunchBenchmarkJenaMain"),
                    ChooseableOptionDirectory("Import", "commonS00LaunchImportMain")
            ),
            ChooseableGroup("Sanity Checks", "Sanity") to listOf(
                    ChooseableOptionTypeAlias("On", "lupos.s00misc", listOf("SanityCheck" to "SanityCheckOn")),
                    ChooseableOptionTypeAlias("Off", "lupos.s00misc", listOf("SanityCheck" to "SanityCheckOff"))
            ),
            ChooseableGroup("Parallelisation Method", "Parallel") to listOf(
                    ChooseableOptionSymbolic("Threads", "commonS00ParallelThreadsMain")
            ),
            ChooseableGroup("Buffer Manager Type", "BufferManager") to listOf(
                    ChooseableOptionSymbolic("Heap", "commonS01HeapMain"),
            ),
            ChooseableGroup("Dictionary", "Dictionary") to listOf(
                    ChooseableOptionSymbolic("Inmemory", "commonS03DictionaryInmemoryMain")
            ),
            ChooseableGroup("Triple Store", "TripleStore") to listOf(
                    ChooseableOptionTypeAlias("BPlusTreePartition", "lupos.s05tripleStore", listOf("TripleStoreLocal" to "TripleStoreLocalBPlusTreePartition")),
                    ChooseableOptionTypeAlias("BPlusTree", "lupos.s05tripleStore", listOf("TripleStoreLocal" to "TripleStoreLocalBPlusTree")),
//                    ChooseableOptionTypeAlias("MapMapList", "lupos.s05tripleStore", listOf("TripleStoreLocal" to "TripleStoreLocalMapMapList")),
//                    ChooseableOptionTypeAlias("SingleList", "lupos.s05tripleStore", listOf("TripleStoreLocal" to "TripleStoreLocalSingleList"))
            ),
            ChooseableGroup("HttpEndpoint implementation", "Endpoint") to listOf(
                    ChooseableOptionDirectory("JavaNet", "jvmS16HttpEndpointJavaNetMain"),
//                    ChooseableOptionDirectory("Korio", "jvmS16HttpEndpointKorioMain"),
                    ChooseableOptionDirectory("None", "commonS16HttpEndpointNoneMain")
            ),
            ChooseableGroup("Include Jena Wrapper", "Jena") to listOf(
                    ChooseableOptionSymbolic("Off", "commonS00WrapperJenaOffMain"),
                    ChooseableOptionSymbolic("On", "jvmS00WrapperJenaOnMain")
            ),
/*            ChooseableGroup("Set Implementation", "Set") to listOf(
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
*/
            ChooseableGroup("Default Result Format", "OutputFormat") to listOf(
                    ChooseableOptionTypeAlias("Empty", "lupos.s11outputResult", listOf("QueryResultToStream" to "QueryResultToEmptyStream")),
                    ChooseableOptionTypeAlias("XML", "lupos.s11outputResult", listOf("QueryResultToStream" to "QueryResultToXMLStream")),
                    ChooseableOptionTypeAlias("EmptyWithDictionary", "lupos.s11outputResult", listOf("QueryResultToStream" to "QueryResultToEmptyWithDictionaryStream"))
            ),
            ChooseableGroup("Enumerate Bnodes", "EnumerateBnodes") to listOf(
                    ChooseableOptionConstantValue("lupos.s11outputResult", "PRETTY_BNODE_NAMES", "true"),
                    ChooseableOptionConstantValue("lupos.s11outputResult", "PRETTY_BNODE_NAMES", "false"),
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
                    ChoosableOptionInternalScript("On", { applyCoverageEnable() }, "CoverageModeOn", true),
                    ChoosableOptionInternalScript("Off", { applyCoverageDisable() }, "CoverageModeOff", true)
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
                    ChoosableOptionInternalScript("On", { applyInlineEnable() }, "InlineModeOn", false),
                    ChoosableOptionInternalScript("Off", { applyInlineDisable() }, "InlineModeOff", false)
            ),
            ChooseableGroup("Use Partitions for parallel Join", "UsePartitions") to listOf(
                    ChooseableOptionConstantValue("lupos.s10physicalOptimisation", "USE_PARTITIONS", "true"),
                    ChooseableOptionConstantValue("lupos.s10physicalOptimisation", "USE_PARTITIONS", "false")
            ),
            ChooseableGroup("Iterator Debug mode", "IteratorDebug") to listOf(
                    ChooseableOptionConstantValue("lupos.s00misc", "ITERATOR_DEBUG_MODE", "EPOPDebugMode.NONE"),
                    ChooseableOptionConstantValue("lupos.s00misc", "ITERATOR_DEBUG_MODE", "EPOPDebugMode.DEBUG1"),
                    ChooseableOptionConstantValue("lupos.s00misc", "ITERATOR_DEBUG_MODE", "EPOPDebugMode.DEBUG2")
            ),
    )

    init {
        java.io.File("src/generateBuildfile/all-template").printWriter().use { out ->
            for ((grp, ops) in options) {
                for (o in ops) {
                    out.println(grp.shortcut + "->" + o.label)
                }
            }
        }
    }

    fun myReadLine(key: String): String? {
        while (true) {
            val tmp = myReadLineCache[key]
            if (tmp != null) {
                return tmp
            } else {
                val tmp2 = readLine()
                if (tmp2 != null) {
                    val a = tmp2.split("->")
                    if (a.size == 2) {
                        myReadLineCache[a[0]] = a[1]
                    } else if (a.size == 1) {
                        myReadLineCache[key] = a[0]
                        return a[0]
                    } else {
                        throw Exception("invalid input '$tmp2'")
                    }
                } else {
                    return null
                }
            }
        }
    }

    fun presentUserChoice(group: ChooseableGroup, options: List<ChooseableOption>): ChooseableOption {
        when (options.size) {
            0 -> throw Exception("script error")
            1 -> return options[0]
            else -> {
                println("selecting ${group.name}: choose one of ${options.map { it.label }}")
                while (true) {
                    val input2 = myReadLine(group.shortcut)
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
                                    newCommandString.println("${group.shortcut}->${o.label}")
                                    return o
                                }
                            }
                            require(false)
                        }
                        try {
                            val i = input.toInt()
                            if (i < options.size) {
                                allChoicesString += "_${options[i].label}"
                                newCommandString.println("${group.shortcut}->${options[i].label}")
                                return options[i]
                            }
                        } catch (e: Throwable) {
                        }
                    }
                }
            }
        }
    }

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

    /*--->>> autogenerating all possible build-files*/
    fun presentAutoChoice(group: ChooseableGroup, options: List<ChooseableOption>): ChooseableOption {
        if (options.size == 1) {
            autoGenerateAllChoosenOptionsList.add(options[0])
            autoGenerateAllNotChoosenOptionsList.remove(options[0])
            return options[0]//only possibility
        } else {
            for (o in options) {
                if (!autoGenerateAllChoosenOptionsList.contains(o)) {
                    autoGenerateAllNotChoosenOptionsList.remove(o)
                    autoGenerateAllChoosenOptionsList.add(o)
                    for (o2 in options) {
                        if (!autoGenerateAllChoosenOptionsList.contains(o2)) {
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
            autoGenerateAllChoicesString += "\n  echo ${options[0].label}"
            return options[0]//anything, since all were choosen at least once
        }
    }

    fun main() {
        var done = false
        var autogeneratemode = args.size > 0 && args[0] == "listAll"
        File("build-cache/bin-effective").deleteRecursively()
        File("build-cache/bin-effective").mkdirs()
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
                    ChooseableOption("commonS00ParallelThreadsMain") to listOf(
                            ChooseableOptionTypeAlias("commonS00ParallelThreadsMainTypes", "lupos.s00misc", listOf(
                                    "Parallel" to "ParallelThread",
                                    "ParallelJob" to "ParallelThreadJob",
                                    "ParallelCondition" to "ParallelThreadCondition",
                                    "ParallelQueue<T>" to "ParallelThreadQueue<T>",
                                    "MyLock" to "MyThreadLock",
                                    "MyReadWriteLock" to "MyThreadReadWriteLock",
                            )),
                            ChoosableOptionInternalScript("SuspendModeOff", { applySuspendDisable() }, "SuspendModeOff", false)
                    ),
                    ChooseableOption("jvmS16ServerCommunicationSocketsMain") to listOf(
                            ChooseableOptionDirectory("commonS16ServerCommunicationEnabledMain")
                    ),
                    ChooseableOptionSymbolic("Inmemory", "commonS03DictionaryInmemoryMain") to listOf(
                            ChooseableOptionDependency("luposdate3000:Luposdate3000_Dictionary_Inmemory:0.0.1"),
                    ),
                    ChooseableOptionSymbolic("Heap", "commonS01HeapMain") to listOf(
                            ChooseableOptionDependency("luposdate3000:Luposdate3000_Buffer_Manager_Inmemory:0.0.1"),
                    ),
                    ChooseableOption("commonMain") to listOf(
                            ChooseableOptionDependency("luposdate3000:Luposdate3000_Parser:0.0.1"),
                            ChooseableOptionDependency("luposdate3000:Luposdate3000_Triple_Store_Id_Triple:0.0.1"),
                            ChooseableOptionDependency("luposdate3000:Luposdate3000_Shared:0.0.1"),
                            ChooseableOptionDependency("luposdate3000:Luposdate3000_Operators:0.0.1"),
                            ChooseableOptionDependency("org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"),
                            ChooseableOptionDependency("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"),
                            ChooseableOptionDependency("com.benasher44:uuid:0.0.7"),
                            ChooseableOptionDependency("com.soywiz.korlibs.krypto:krypto:1.9.1")
                    ),
                    ChooseableOption("jvmMain") to listOf(
//                            ChooseableOptionDependency("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"),
                            ChooseableOptionDependency("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"),
                    ),
                    ChooseableOption("commonS00ParallelCoroutinesMain") to listOf(
                            ChooseableOptionDependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3"),
                    ),
                    ChooseableOptionSymbolic("Off", "commonS00WrapperJenaOffMain") to listOf(
                            ChooseableOptionDependency("luposdate3000:Luposdate3000_Jena_Wrapper_Off:0.0.1"),
                    ),
                    ChooseableOptionSymbolic("On", "jvmS00WrapperJenaOnMain") to listOf(
                            ChooseableOptionDependency("luposdate3000:Luposdate3000_Jena_Wrapper_On:0.0.1"),
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
                for (choice in choices) {
                    if (allChoosenOptions.contains(choice)) {
                        alreadyChoosen = true
                        break
                    }
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
                        for (conflict in conflicts) {
                            if (conflict.contains(choice.internalID)) {
                                for (option in allChoosenOptions) {
                                    if (conflict.contains(option.internalID)) {
                                        ok = false
                                    }
                                }
                            }
                        }
                        if (ok) {
                            remainingChoices.add(choice)
                        }
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
                for (option in allChoosenOptions.sorted()) {
                    println(option.toString() + " :: " + option.internalID)
                }
                allChoicesString = allChoicesString.replace("Main", "").replace("common", "")
                File("settings.gradle").printWriter().use { out ->
                    out.println("pluginManagement {")
                    out.println("    repositories {")
                    out.println("        mavenLocal()")
                    out.println("        gradlePluginPortal()")
                    out.println("    }")
                    out.println("}")
                    out.println("rootProject.name = \"luposdate3000\"")//maven-artifactID
                }
                File("build.gradle.kts").printWriter().use { out ->
                    when (platform) {
                        "jvm" -> {
                            out.println("import org.jetbrains.kotlin.gradle.tasks.KotlinCompile")
                            out.println("import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar")
                            out.println("tasks.withType<KotlinCompile>().all {")
                            out.println("    kotlinOptions.jvmTarget = \"1.8\"")//kotlinOptions.jvmTarget = \"14\"
                            //see /opt/kotlin/compiler/cli/cli-common/src/org/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments.kt
                            //or kotlinc -X
                            out.println("    kotlinOptions.freeCompilerArgs += \"-Xno-param-assertions\"")
                            out.println("    kotlinOptions.freeCompilerArgs += \"-Xuse-ir\"")
                            out.println("    kotlinOptions.freeCompilerArgs += \"-Xmulti-platform\"")
                            out.println("    kotlinOptions.freeCompilerArgs += \"-Xnew-inference\"")
                            out.println("    kotlinOptions.freeCompilerArgs += \"-Xno-receiver-assertions\"")
                            out.println("    kotlinOptions.freeCompilerArgs += \"-Xno-call-assertions\"")
                            out.println("}")
                        }
                        else -> {
                            out.println("import org.jetbrains.kotlin.gradle.tasks.KotlinCompile")
                            out.println("tasks.withType<KotlinCompile>().all {")
                            out.println("    kotlinOptions.freeCompilerArgs += \"-Xno-param-assertions\"")
                            out.println("}")
                        }
                    }
                    out.println("buildscript {")
                    out.println("    repositories {")
                    out.println("        jcenter()")
                    out.println("        google()")
                    out.println("        mavenLocal()")
                    out.println("        mavenCentral()")
                    out.println("        maven(\"https://plugins.gradle.org/m2/\")")
                    out.println("        maven(\"https://dl.bintray.com/kotlin/kotlin-eap\")")
                    out.println("    }")
                    out.println("    dependencies {")
                    out.println("        classpath(\"org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion\")")
                    out.println("        classpath(\"org.jetbrains.kotlin:kotlin-frontend-plugin:0.0.26\")")
                    out.println("        classpath(\"com.moowork.gradle:gradle-node-plugin:1.2.0\")")
                    out.println("    }")
                    out.println("}")
                    when (platform) {
                        "jvm" -> {
                            out.println("plugins {")
                            out.println("    id(\"org.jetbrains.kotlin.jvm\") version \"$kotlinVersion\"")
                            out.println("    id(\"com.github.johnrengelman.shadow\") version \"5.1.0\"")
                            out.println("    application")
                            out.println("}")
                            out.println("application {")
                            out.println("    mainClassName = \"MainKt\"")
                            out.println("}")
                            out.println("tasks.withType<ShadowJar>() {")
                            out.println("    manifest {")
                            out.println("        attributes[\"Main-Class\"] = \"MainKt\"")
                            out.println("    }")
                            out.println("}")
                            out.println("repositories {")
                            out.println("    jcenter()")
                            out.println("    google()")
                            out.println("    mavenLocal()")
                            out.println("    mavenCentral()")
                            out.println("    maven(\"http://dl.bintray.com/kotlin/kotlin-eap-1.2\")")
                            out.println("    maven(\"https://kotlin.bintray.com/kotlinx\")")
                            out.println("}")
                            out.println("project.buildDir = file(\"build/build$allChoicesString\")")
                            out.println("dependencies {")
                            for (option in allChoosenOptions.sorted()) {
                                if (option is ChooseableOptionDependency) {
                                    if (option.internalID.startsWith("luposdate3000")) {
                                        out.println("    compileOnly(\"${option.internalID}\")")
                                        val tmpName = option.internalID.split(":")[1]
                                        Files.createSymbolicLink(Paths.get("build-cache/bin-effective/${tmpName}-jvm.jar"), Paths.get("../bin/${tmpName}-jvm.jar"))
                                    } else {
                                        out.println("    implementation(\"${option.internalID}\")")
                                    }
                                }
                            }
                            out.println("}")
                            for (option in allChoosenOptions.sorted()) {
                                if (option is ChooseableOptionDirectory) {
                                    out.println("sourceSets[\"main\"].java.srcDir(\"src.generated/${option.internalID}/kotlin\")")
                                }
                            }
                        }
                        else -> {
                            out.println("plugins {")
                            out.println("    id(\"org.jetbrains.kotlin.multiplatform\") version \"$kotlinVersion\"")
                            out.println("}")
                            out.println("repositories {")
                            out.println("    jcenter()")
                            out.println("    google()")
                            out.println("    mavenLocal()")
                            out.println("    mavenCentral()")
                            out.println("    maven(\"http://dl.bintray.com/kotlin/kotlin-eap-1.2\")")
                            out.println("    maven(\"https://kotlin.bintray.com/kotlinx\")")
                            out.println("}")
                            out.println("kotlin {")
                            out.println("    project.buildDir = file(\"build/build$allChoicesString\")")
                            out.println("    ${platform}(\"${platform}\") {")
                            out.println("        val main by compilations.getting")
                            for (option in allChoosenOptions.sorted()) {
                                if (option is ChooseableOptionCInterop) {
                                    out.println("        val ${option.internalID} by main.cinterops.creating")
                                }
                            }
                            out.println("        binaries {")
                            out.println("            executable()")
                            out.println("        }")
                            out.println("    }")
                            out.println("    sourceSets[\" commonMain \"].dependencies {")
                            for (option in allChoosenOptions.sorted()) {
                                if (option is ChooseableOptionDependency) {
                                    out.println("        implementation(\"${option.internalID}\")")
                                }
                            }
                            out.println("    }")
                            for (option in allChoosenOptions.sorted()) {
                                if (option is ChooseableOptionDirectory) {
                                    if (option.internalID.startsWith("common")) {
                                        out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"src.generated/${option.internalID}/kotlin\")")
                                    } else {
                                        out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"src.generated/${option.internalID}/kotlin\")")
                                    }
                                }
                            }
                            out.println("}")
                        }
                    }
                }
                try {
                    File("build.gradle.kts").copyTo(File("build/script${allChoicesString}.gradle.kts"))
                } catch (e: FileAlreadyExistsException) {
                }
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
                            fc.append("internal typealias ${alias.first} = ${alias.second}\n")
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
                File("src.generated").mkdirs()
                File("src/luposdate3000_shared_inline").copyRecursively(File("src.generated"))
                for (option in allChoosenOptions) {
                    if (option is ChooseableOptionDirectory && option.internalID != "commonConfig") {
                        File("src/luposdate3000_core/${option.internalID}").copyRecursively(File("src.generated/${option.internalID}"))
                    }
                }
                try {
                    File("src/luposdate3000_core/commonTemplate").copyRecursively(File("src.generated/commonTemplate"))
                } catch (e: Throwable) {
                }
//perform scripts "before template"
                for (option in allChoosenOptions) {
                    if (option is ChoosableOptionExternalScript && option.beforeTemplate) {
                        println("running script before template ${option.scriptName}")
                        option.scriptName.runCommand()
                    } else if (option is ChoosableOptionInternalScript && option.beforeTemplate) {
                        println("running script before template ${option.internalID}")
                        option.action()
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
                    try {
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
                    } catch (e: Throwable) {
                    }
                }
//perform scripts "after template"
                for (option in allChoosenOptions) {
                    if (option is ChoosableOptionExternalScript && !option.beforeTemplate) {
                        println("running script after template ${option.scriptName}")
                        option.scriptName.runCommand()
                    } else if (option is ChoosableOptionInternalScript && !option.beforeTemplate) {
                        println("running script after template ${option.internalID}")
                        option.action()
                    }
                }
            }
        }
        newCommandString.flush()
        println(autoGenerateAllChoicesString)
    }
}
