#!/usr/bin/env kotlin
import java.io.File
// args[0] enum name
// args[1] package name
// args[2] modifier
// args[3] file base name

val mapping2 = mutableListOf<String>()
var id = 0
File(args[3] + ".txt").forEachLine {
    mapping2.add(it)
    id++
}
val mapping = mapping2.sorted()
File(args[3] + ".kt").printWriter().use { out ->
    out.println("package ${args[1]}")
    out.println("${args[2]} class ${args[0]} public constructor (public val ordinal: Int){")
    out.println("    public override fun toString():String=throw Exception(\"toString not allowed\")")
    out.println("    init{")
    out.println("        if(ordinal<0||ordinal>${mapping.size})throw Exception(\"enum out of range\")")
    out.println("    }")
    out.println("}")
}
File(args[3] + "Ext.kt").printWriter().use { out ->
    out.println("package ${args[1]}")
    out.println("import kotlin.jvm.JvmField")
    out.println("${args[2]} object ${args[0]}Ext {")
    for (i in 0 until mapping.size) {
        out.println("    ${args[2]}  val ${mapping[i]}: ${args[0]} = ${args[0]}($i)")
    }
    out.println("    ${args[2]} const val values_size: Int = ${mapping.size}")
    out.println("    @JvmField ${args[2]} val names: Array<String> = arrayOf(")
    for (i in 0 until mapping.size) {
        out.println("        \"${mapping[i]}\",")
    }
    out.println("    )")
    out.println("}")
}
/*
./tool-generateMyEnumClass.main.kts MyPrintWriterMode lupos.s00misc public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/MyPrintWriterMode
./tool-generateMyEnumClass.main.kts BuiltInFunctions lupos.s02buildSyntaxTree.sparql1_1 public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s02buildSyntaxTree/sparql1_1/BuiltInFunctions
./tool-generateMyEnumClass.main.kts BinaryTestCaseOutputMode lupos.s00misc public src/luposdate3000_test/src/commonMain/kotlin/lupos/s00misc/BinaryTestCaseOutputMode
./tool-generateMyEnumClass.main.kts Aggregation lupos.s02buildSyntaxTree.sparql1_1 public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s02buildSyntaxTree/sparql1_1/Aggregation
./tool-generateMyEnumClass.main.kts TripleStoreFeature lupos.s05tripleStore public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s05tripleStore/TripleStoreFeature
./tool-generateMyEnumClass.main.kts IteratorBundleMode lupos.s04logicalOperators.iterator internal src/luposdate3000_shared/src/commonMain/kotlin/lupos/s04logicalOperators/iterator/IteratorBundleMode
./tool-generateMyEnumClass.main.kts ESortPriority lupos.s00misc public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ESortPriority
./tool-generateMyEnumClass.main.kts ETripleIndexType lupos.s00misc public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleIndexType
./tool-generateMyEnumClass.main.kts EGraphRefType lupos.s00misc public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EGraphRefType
./tool-generateMyEnumClass.main.kts EOperatorID lupos.s00misc public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatorID
./tool-generateMyEnumClass.main.kts EModifyType lupos.s00misc public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EModifyType
./tool-generateMyEnumClass.main.kts ETripleComponentType lupos.s00misc public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleComponentType
./tool-generateMyEnumClass.main.kts EGraphOperationType lupos.s00misc public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EGraphOperationType
./tool-generateMyEnumClass.main.kts ESortType lupos.s00misc public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ESortType
./tool-generateMyEnumClass.main.kts EGroupMember  lupos.s00misc public   src/luposdate3000_optimizer/src/commonMain/kotlin/lupos/s00misc/EGroupMember
./tool-generateMyEnumClass.main.kts EQueryResultToStream lupos.s11outputResult public src/luposdate3000_result_format/src/commonMain/kotlin/lupos/s11outputResult/EQueryResultToStream
./tool-generateMyEnumClass.main.kts EPOPDebugMode lupos.s00misc public  src/luposdate3000_operators/src/commonMain/kotlin/lupos/s00misc/EPOPDebugMode
./tool-generateMyEnumClass.main.kts Turtle2ParserState lupos.s02buildSyntaxTree.turtle internal src/luposdate3000_parser/src/commonMain/kotlin/lupos/s02buildSyntaxTree/turtle/Turtle2ParserState
./tool-generateMyEnumClass.main.kts EOptimizerID lupos.s00misc internal src/luposdate3000_optimizer/src/commonMain/kotlin/lupos/s00misc/EOptimizerID
./tool-generateMyEnumClass.main.kts EOperatingSystem lupos.s00misc public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem
./tool-generateMyEnumClass.main.kts EIndexPattern lupos.s00misc public src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EIndexPattern
*/
