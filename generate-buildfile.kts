#!/bin/kscript
import java.io.File

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

class ChooseableOptionConstantValue(val pkg: String, val variableName: String, val variableValue: String) : ChooseableOption(variableValue, "common" + pkg +"."+ variableName) {
    override fun toString() = "ConstantValue($internalID = variableValue)"
}

class ChooseableGroup(val name: String) : Comparable<ChooseableGroup> {
    override fun equals(other: Any?) = other is ChooseableGroup && name == other.name
    override fun hashCode() = name.hashCode()
    override operator fun compareTo(other: ChooseableGroup) = name.compareTo(other.name)
}

var allChoicesString = ""
var choicesCount = 0
fun presentChoice(group: ChooseableGroup, options: List<ChooseableOption>): ChooseableOption {
    when (options.size) {
        0 -> throw Exception("script error")
        1 -> return options[0]
        else -> {
            println("selecting ${group.name}: choose one of ${options.map { it.label }}")
            while (true) {
                val input = if (choicesCount < args.size) {
                    args[choicesCount++]
                } else
                    readLine()
                if (input != null) {
                    if (options.map { it.label }.contains(input)) {
                        for (o in options) {
                            if (o.label == input) {
                                allChoicesString += "_${o.label}"
                                return o
                            }
                        }
                        require(false)
                    }
                    try {
                        val i = input.toInt()
                        if (i < options.size) {
                            allChoicesString += "_${options[i].label}"
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
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Int", "VALUE" to "Int", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Int", "VNAME" to "Int", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Int", "VALUE" to "Long", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Int", "VNAME" to "Long", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Int", "VALUE" to "Double", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Int", "VNAME" to "Double", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Long", "VALUE" to "Int", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Long", "VNAME" to "Int", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Long", "VALUE" to "Long", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Long", "VNAME" to "Long", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Long", "VALUE" to "Double", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Long", "VNAME" to "Double", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Double", "VALUE" to "Int", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Double", "VNAME" to "Int", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Double", "VALUE" to "Long", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Double", "VNAME" to "Long", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Double", "VALUE" to "Double", "GDEF" to "", "GUSEKV" to "", "GUSEK" to "", "GUSEV" to "", "KNAME" to "Double", "VNAME" to "Double", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "GenericK",
                "VALUE" to "GenericV",
                "GDEF" to "<GenericK : Comparable<GenericK>, GenericV>",
                "GUSEKV" to "<GenericK, GenericV>",
                "GUSEK" to "<GenericK>",
                "GUSEV" to "<GenericV>",
                "KNAME" to "Generic",
                "VNAME" to "Generic",
                "IOSTART.*IOEND" to "throw Exception(\"not Implemented\")")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Int", "VALUE" to "Generic", "GDEF" to "<Generic>", "GUSEKV" to "<Generic>", "GUSEK" to "", "GUSEV" to "<Generic>", "KNAME" to "Int", "VNAME" to "Generic", "IOSTART.*IOEND" to "throw Exception(\"not Implemented\")")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBinaryTree", listOf("KEY" to "Long", "VALUE" to "Generic", "GDEF" to "<Generic>", "GUSEKV" to "<Generic>", "GUSEK" to "", "GUSEV" to "<Generic>", "KNAME" to "Long", "VNAME" to "Generic", "IOSTART.*IOEND" to "throw Exception(\"not Implemented\")")),
        PrecompileTemplate("lupos.s00misc", "MySetKEYBTree", listOf("KEY" to "Int", "GDEF" to "", "GUSE" to "", "ARRAYTYPE" to "IntArray", "ARRAYINITIALIZER" to "")),
        PrecompileTemplate("lupos.s00misc", "MySetKEYBTree", listOf("KEY" to "Long", "GDEF" to "", "GUSE" to "", "ARRAYTYPE" to "LongArray", "ARRAYINITIALIZER" to "")),
        PrecompileTemplate("lupos.s00misc", "MySetKEYBTree", listOf("KEY" to "Double", "GDEF" to "", "GUSE" to "", "ARRAYTYPE" to "DoubleArray", "ARRAYINITIALIZER" to "")),
        PrecompileTemplate("lupos.s00misc", "MySetKEYBTree", listOf("KEY" to "Generic", "GDEF" to "<Generic : Comparable<Generic>>", "GUSE" to "<Generic>", "ARRAYTYPE" to "Array<Any?>", "ARRAYINITIALIZER" to "{null}")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Int", "VALUE" to "Int", "KNAME" to "Int", "VNAME" to "Int", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "IntArray", "ARRAYVTYPE" to "IntArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Int", "VALUE" to "Long", "KNAME" to "Int", "VNAME" to "Long", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "IntArray", "ARRAYVTYPE" to "LongArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Int", "VALUE" to "Double", "KNAME" to "Int", "VNAME" to "Double", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "IntArray", "ARRAYVTYPE" to "DoubleArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Long", "VALUE" to "Int", "KNAME" to "Long", "VNAME" to "Int", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "LongArray", "ARRAYVTYPE" to "IntArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Long", "VALUE" to "Long", "KNAME" to "Long", "VNAME" to "Long", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "LongArray", "ARRAYVTYPE" to "LongArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Long", "VALUE" to "Double", "KNAME" to "Long", "VNAME" to "Double", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "LongArray", "ARRAYVTYPE" to "DoubleArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Double", "VALUE" to "Int", "KNAME" to "Double", "VNAME" to "Int", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "DoubleArray", "ARRAYVTYPE" to "IntArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Double", "VALUE" to "Long", "KNAME" to "Double", "VNAME" to "Long", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "DoubleArray", "ARRAYVTYPE" to "LongArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART" to "", "IOEND" to "")),
        PrecompileTemplate("lupos.s00misc", "MyMapKNAMEVNAMEBTree", listOf("KEY" to "Double", "VALUE" to "Double", "KNAME" to "Double", "VNAME" to "Double", "GDEF" to "", "GUSE" to "", "ARRAYKTYPE" to "DoubleArray", "ARRAYVTYPE" to "DoubleArray", "ARRAYKINITIALIZER" to "", "ARRAYVINITIALIZER" to "", "IOSTART" to "", "IOEND" to "")),
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
                "IOSTART.*IOEND" to "throw Exception(\"not Implemented\")"
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
                "IOSTART.*IOEND" to "throw Exception(\"not Implemented\")"
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
                "IOSTART.*IOEND" to "throw Exception(\"not Implemented\")"
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
                "IOSTART.*IOEND" to "throw Exception(\"not Implemented\")"
        ))
)

val options = mapOf<ChooseableGroup, List<ChooseableOption>>(
        ChooseableGroup("Launch Type") to listOf(
                ChooseableOptionDirectory("SparqlTestSuite", "commonS00LaunchSparqlTestSuiteMain"),
                ChooseableOptionDirectory("BinaryTests", "commonS00LaunchBinaryTestsMain"),
                ChooseableOptionDirectory("Endpoint", "commonS00LaunchEndpointMain"),
                ChooseableOptionDirectory("Benchmark", "commonS00LaunchBenchmarkMain"),
                ChooseableOptionDirectory("JavaFuzz", "jvmS00LaunchJavaFuzzMain"),
                ChooseableOptionDirectory("WarnkeFuzz", "jvmS00LaunchWarnkeFuzzMain")
        ),
        ChooseableGroup("Sanity Checks") to listOf(
                ChooseableOptionTypeAlias("On", "lupos.s00misc", listOf("SanityCheck" to "SanityCheckOn","CoroutinesHelperMutex" to "Lock")),
                ChooseableOptionTypeAlias("Off", "lupos.s00misc", listOf("SanityCheck" to "SanityCheckOff","CoroutinesHelperMutex" to "Lock"))
        ),
        ChooseableGroup("ResultFlow") to listOf(
                ChooseableOptionDirectory("Fast", "commonS00ResultFlowFastMain"),
                ChooseableOptionDirectory("Tests", "commonS00ResultFlowExecuteTestsMain")
        ),
        ChooseableGroup("Execution") to listOf(
                ChooseableOptionTypeAlias("Sequential", "lupos.s00misc", listOf("CoroutinesHelper" to "CoroutinesHelperSequential")),
                ChooseableOptionTypeAlias("Parallel", "lupos.s00misc", listOf("CoroutinesHelper" to "CoroutinesHelperParallel"))
        ),
        ChooseableGroup("Buffer Manager Type") to listOf(
                ChooseableOptionDirectory("Heap", "commonS01HeapMain"),
                ChooseableOptionDirectory("MemoryMapped", "jvmS01BufferMemoryMappedMain"),
                ChooseableOptionDirectory("MemoryMappedUnsafe", "jvmS01BufferMemoryMappedUnsafeMain"),
                ChooseableOptionDirectory("RandomAccess", "jvmS01BufferRandomAccessMain"),
                ChooseableOptionDirectory("Unsafe", "jvmS01BufferUnsafeMain")
        ),
        ChooseableGroup("Dictionary") to listOf(
                ChooseableOptionDirectory("ObjectMap", "commonS03DictionaryObjectMapMain"),
                ChooseableOptionDirectory("MultiMap", "commonS03DictionaryMultiMapMain")
        ),
        ChooseableGroup("Triple Store") to listOf(
                ChooseableOptionTypeAlias("MapMapList", "lupos.s05tripleStore", listOf("TripleStoreLocal" to "TripleStoreLocalMapMapList")),
                ChooseableOptionTypeAlias("SingleList", "lupos.s05tripleStore", listOf("TripleStoreLocal" to "TripleStoreLocalSingleList")),
                ChooseableOptionTypeAlias("BPlusTree", "lupos.s05tripleStore", listOf("TripleStoreLocal" to "TripleStoreLocalBPlusTree"))
        ),
        ChooseableGroup("P2P") to listOf(
                ChooseableOptionDirectory("Dummy", "commonS12DummyMain"),
                ChooseableOptionDirectory("Local", "commonS12LocalMain")
        ),
        ChooseableGroup("Server implementation") to listOf(
                ChooseableOptionDirectory("Korio", "jvmS14ServerKorioMain"),
                ChooseableOptionDirectory("None", "commonS14ServerNoneMain")
        ),
        ChooseableGroup("Client implementation") to listOf(
                ChooseableOptionDirectory("Korio", "jvmS14ClientKorioMain"),
                ChooseableOptionDirectory("None", "commonS14ClientNoneMain"),
                ChooseableOptionSymbolic("Ktor", "jvmS14ClientKtorTarget"),
                ChooseableOptionSymbolic("Ktor", "nativeS14ClientKtorTarget")
        ),
        ChooseableGroup("Triple Store Interface") to listOf(
                ChooseableOptionDirectory("Local", "commonS15LocalMain"),
                ChooseableOptionDirectory("Distributed", "commonS15DistributedMain")
        ),
        ChooseableGroup("Include Jena Wrapper") to listOf(
                ChooseableOptionDirectory("On", "jvmS00WrapperJenaOnMain"),
                ChooseableOptionDirectory("Off", "commonS00WrapperJenaOffMain")
        ),
        ChooseableGroup("Set Implementation") to listOf(
                ChooseableOptionTypeAlias("Bisection", "lupos.s00misc", listOf(
                        "MySetGeneric<T>" to "MySetGenericBinaryTree<T>",
                        "MySetLong" to "MySetLongBinaryTree",
                        "MySetInt" to "MySetIntBinaryTree",
                        "MySetDouble" to "MySetDoubleBinaryTree"
                )),
                ChooseableOptionTypeAlias("BTree", "lupos.s00misc", listOf(
                        "MySetGeneric<T>" to "MySetGenericBTree<T>",
                        "MySetLong" to "MySetLongBTree",
                        "MySetInt" to "MySetIntBTree",
                        "MySetDouble" to "MySetDoubleBTree"
                ))
        ),
        ChooseableGroup("Map Implementation") to listOf(
                ChooseableOptionTypeAlias("BTree", "lupos.s00misc", listOf(
                        "MyMapIntGeneric<T>" to "MyMapIntGenericBTree<T>",
                        "MyMapLongGeneric<T>" to "MyMapLongGenericBTree<T>",
                        "MyMapLongInt" to "MyMapLongIntBTree",
                        "MyMapIntInt" to "MyMapIntIntBTree",
                        "MyMapDoubleInt" to "MyMapDoubleIntBTree"
                )),
                ChooseableOptionTypeAlias("Bisection", "lupos.s00misc", listOf(
                        "MyMapIntGeneric<T>" to "MyMapIntGenericBinaryTree<T>",
                        "MyMapLongGeneric<T>" to "MyMapLongGenericBinaryTree<T>",
                        "MyMapLongInt" to "MyMapLongIntBinaryTree",
                        "MyMapIntInt" to "MyMapIntIntBinaryTree",
                        "MyMapDoubleInt" to "MyMapDoubleIntBinaryTree"
                )),
                ChooseableOptionTypeAlias("HashMap", "lupos.s00misc", listOf(
                        "MyMapIntGeneric<T>" to "MyMapIntGenericBinaryTree<T>",
                        "MyMapLongGeneric<T>" to "MyMapLongGenericBinaryTree<T>",
                        "MyMapLongInt" to "MyMapLongIntHash",
                        "MyMapIntInt" to "MyMapIntIntBinaryTree",
                        "MyMapDoubleInt" to "MyMapDoubleIntBinaryTree"
                ))
        ),
        ChooseableGroup("Iterator Debug verbosity") to listOf(
                ChooseableOptionTypeAlias("None", "lupos.s04logicalOperators.iterator", listOf("ColumnIteratorDebug" to "ColumnIteratorDebugFast")),
                ChooseableOptionTypeAlias("Count", "lupos.s04logicalOperators.iterator", listOf("ColumnIteratorDebug" to "ColumnIteratorDebugCount")),
                ChooseableOptionTypeAlias("Verbose", "lupos.s04logicalOperators.iterator", listOf("ColumnIteratorDebug" to "ColumnIteratorDebugVerbose"))
        ),
        ChooseableGroup("Default Result Format") to listOf(
                ChooseableOptionTypeAlias("XML", "lupos.s11outputResult", listOf("QueryResultToString" to "QueryResultToXMLString")),
                ChooseableOptionTypeAlias("Empty", "lupos.s11outputResult", listOf("QueryResultToString" to "QueryResultToEmptyString")),
                ChooseableOptionTypeAlias("EmptyWithDictionary", "lupos.s11outputResult", listOf("QueryResultToString" to "QueryResultToEmptyWithDictionaryString"))
        ),
        ChooseableGroup("PageSize in Bytes") to listOf(
                ChooseableOptionConstantValue("lupos.s05tripleStore.index_IDTriple", "PAGE_SIZE_IN_BYTES", "100"),
                ChooseableOptionConstantValue("lupos.s05tripleStore.index_IDTriple", "PAGE_SIZE_IN_BYTES", "8196")
        ),
        ChooseableGroup("ArrayList Block Capacity in Elements") to listOf(
                ChooseableOptionConstantValue("lupos.s00misc", "ARRAY_LIST_BLOCK_CAPACITY", "5"),
                ChooseableOptionConstantValue("lupos.s00misc", "ARRAY_LIST_BLOCK_CAPACITY", "1024")
        )
)
val conflicts = listOf(
        setOf("commonS12LocalMain", "commonS15DistributedMain"),
        setOf("commonS12LocalMain", "jvmS14ServerKorioMain"),
        setOf("commonS12LocalMain", "jvmS14ClientKorioMain"),
        setOf("commonS12LocalMain", "jvmS14ClientKtorTarget", "nativeS14ClientKtorTarget"),
        setOf("commonS00LaunchEndpointMain", "commonS00ResultFlowExecuteTestsMain"),
        setOf("commonS00LaunchSparqlTestSuiteMain", "commonS00ResultFlowExecuteTestsMain")
)
val platformPrefix = mapOf(
        "jvm" to listOf("common", "jvm"),
        "linuxX64" to listOf("common", "linuxX64", "native"),
        "macosX64" to listOf("common", "macosX64", "native"),
        "mingw64" to listOf("common")
)
val ktorVersion = presentChoice(ChooseableGroup("ktor-version"), listOf(ChooseableOption("1.3.1"))).label
val kotlinVersion = presentChoice(ChooseableGroup("kotlin-version"), listOf(ChooseableOption("1.3.70"), ChooseableOption("1.4.255-SNAPSHOT"))).label
val platform = presentChoice(ChooseableGroup("Platform"), platformPrefix.keys.toList().map { ChooseableOption(it) }).label

val additionalSources = mapOf(
/*if the key is choosen, automatically add all dependent things*/
        ChooseableOption("commonMain") to listOf(
                ChooseableOptionDependency("org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"),
                ChooseableOptionDependency("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"),
                ChooseableOptionDependency("com.benasher44:uuid:0.0.7"),
                ChooseableOptionDependency("com.soywiz.korlibs.krypto:krypto:1.9.1")
        ),
        ChooseableOption("commonS00ResultFlowExecuteTestsMain") to listOf(
                ChooseableOptionDirectory("commonTest")
        ),
        ChooseableOption("commonS00LaunchBinaryTestsMain") to listOf(
                ChooseableOptionDirectory("commonS00ResultFlowExecuteTestsMain")
        ),
        ChooseableOption("commonS01HeapMain") to listOf(
                ChooseableOptionDirectory("commonS01BufferMainmemoryMain")
        ),
        ChooseableOption("commonS14ClientKtorMain") to listOf(
                ChooseableOptionDependency("io.ktor:ktor-client-core:$ktorVersion"),
                ChooseableOptionDependency("io.ktor:ktor-client-cio:$ktorVersion"),
                ChooseableOptionDependency("io.ktor:ktor-client-logging:$ktorVersion"),
                ChooseableOptionDependency("org.slf4j:slf4j-nop:1.7.25")
        ),
        ChooseableOption("jvmMain") to listOf(
                ChooseableOptionDependency("com.soywiz.korlibs.klock:klock:1.7.0"),
                ChooseableOptionDependency("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion"),
                ChooseableOptionDependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"),
                ChooseableOptionDependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")
        ),
        ChooseableOption("jvmS00LaunchJavaFuzzMain") to listOf(
                ChooseableOptionDependency("dev.fuzzit.javafuzz:core:1.22"),
                ChooseableOptionDependency("org.jacoco:org.jacoco.agent:0.8.5:runtime"),
                ChooseableOptionDirectory("commonS00ResultFlowExecuteTestsMain")
        ),
        ChooseableOption("jvmS00WrapperJenaOnMain") to listOf(
                ChooseableOptionDependency("org.apache.jena:jena-core:3.14.0"),
                ChooseableOptionDependency("org.apache.jena:jena-arq:3.14.0")
        ),
        ChooseableOption("jvmS00LaunchWarnkeFuzzMain") to listOf(
                ChooseableOptionDirectory("commonS00ResultFlowExecuteTestsMain")
        ),
        ChooseableOption("jvmS01BufferMemoryMappedMain") to listOf(
                ChooseableOptionDirectory("commonS01BufferDiskbasedMain")
        ),
        ChooseableOption("jvmS01BufferMemoryMappedUnsafeMain") to listOf(
                ChooseableOptionDirectory("commonS01BufferDiskbasedMain"),
                ChooseableOptionDirectory("jvmS01BufferUnsafeHelperMain")
        ),
        ChooseableOption("jvmS01BufferUnsafeMain") to listOf(
                ChooseableOptionDirectory("commonS01BufferMainmemoryMain"),
                ChooseableOptionDirectory("jvmS01BufferUnsafeHelperMain")
        ),
        ChooseableOption("jvmS01BufferRandomAccessMain") to listOf(
                ChooseableOptionDirectory("commonS01BufferDiskbasedMain")
        ),
        ChooseableOption("jvmS14ClientKtorTarget") to listOf(
                ChooseableOptionDirectory("commonS14ClientKtorMain"),
                ChooseableOptionDependency("io.ktor:ktor-client-logging-jvm:$ktorVersion"),
                ChooseableOptionDependency("io.ktor:ktor-client-core-jvm:$ktorVersion")
        ),
        ChooseableOption("jvmS14ServerKorioMain") to listOf(
                ChooseableOptionDependency("com.soywiz.korlibs.korio:korio:1.9.9-SNAPSHOT")
        ),
        ChooseableOption("jvmS14ClientKorioMain") to listOf(
                ChooseableOptionDependency("com.soywiz.korlibs.korio:korio:1.9.9-SNAPSHOT")
        ),
        ChooseableOption("linuxX64Main") to listOf(
                ChooseableOptionDirectory("nativeMain"),
                ChooseableOptionDependency("com.soywiz.korlibs.klock:klock-linuxx64:1.8.7")
        ),
        ChooseableOption("macosX64Main") to listOf(
                ChooseableOptionDirectory("nativeMain"),
                ChooseableOptionDependency("com.soywiz.korlibs.klock:klock-macosx64:1.8.9")
        ),
        ChooseableOption("nativeMain") to listOf(
                ChooseableOptionDependency("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.3"),
                ChooseableOptionCInterop("dirent"),
                ChooseableOptionCInterop("stdio"),
                ChooseableOptionCInterop("unistd")
        ),
        ChooseableOption("nativeS14ClientKtorTarget") to listOf(
                ChooseableOptionDirectory("commonS14ClientKtorMain"),
                ChooseableOptionDependency("io.ktor:ktor-client-core-native:$ktorVersion"),
                ChooseableOptionDependency("io.ktor:ktor-client-logging-native:$ktorVersion")
        )
)
val allChoosenOptions = mutableSetOf<ChooseableOption>(ChooseableOptionDirectory("commonMain"), ChooseableOptionDirectory("commonConfig"))
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

println("result choices :: ")
for (option in allChoosenOptions.sorted())
    println(option)
allChoicesString = allChoicesString.replace("Main", "").replace("common", "")

File("build.gradle.kts").printWriter().use { out ->
    when (platform) {
        "jvm" -> {
            out.println("""import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget="14"
    kotlinOptions.freeCompilerArgs += "-Xno-param-assertions"
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
        maven("http://oss.sonatype.org/content/repositories/snapshots")
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
    application
}
application {
    mainClassName = "MainKt"
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
            for (option in allChoosenOptions.sorted())
                if (option is ChooseableOptionDirectory)
                    out.println("sourceSets[\"main\"].java.srcDir(\"src/${option.internalID}/kotlin\")")
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
                        out.println("    sourceSets[\"commonMain\"].kotlin.srcDir(\"src/${option.internalID}/kotlin\")")
                    else
                        out.println("    sourceSets[\"${platform}Main\"].kotlin.srcDir(\"src/${option.internalID}/kotlin\")")
                }
            out.println("""}""")
        }
    }
}
try {
    File("build.gradle.kts").copyTo(File("build/script${allChoicesString}.gradle.kts"))
} catch (e: FileAlreadyExistsException) {
}
File("src/commonConfig").deleteRecursively()
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
        fc.append("const val ${option.variableName} = ${option.variableValue}")
    }
}
for ((k, v) in configFilesContent) {
    File("src/commonConfig/kotlin/" + k.replace(".", "/")).mkdirs()
    File("src/commonConfig/kotlin/" + k.replace(".", "/") + "/Config.kt").printWriter().use { out ->
        out.print(v.toString())
    }
}

for (template in templates) {
    val sourceFile = File("src/commonTemplate/kotlin/" + template.pkg.replace(".", "/") + "/" + template.sourceClass + ".kt")
    var fileContent = sourceFile.readText()
    var targetClass = template.sourceClass
    for (replacement in template.replacements) {
        targetClass = targetClass.replace(replacement.first.toRegex(), replacement.second)
        fileContent = replacement.first.toRegex(RegexOption.DOT_MATCHES_ALL).replace(fileContent, replacement.second)
    }
    val targetFile = File("src/commonConfig/kotlin/" + template.pkg.replace(".", "/") + "/" + targetClass + ".kt")
    targetFile.printWriter().use {
        it.println("/* this File is autogenerated by generate-buildfile.kts */")
        it.println("/* DO NOT MODIFY DIRECTLY */")
        it.print(fileContent)
    }
}
