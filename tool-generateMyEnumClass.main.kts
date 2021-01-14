#!/usr/bin/env kotlin
import java.io.File

fun generate(enumName: String, packageName: String, modifier: String, fileName: String) {

    val mapping2 = mutableListOf<String>()
    var id = 0
    File(fileName + ".txt").forEachLine {
        mapping2.add(it)
        id++
    }
    val mapping = mapping2.sorted()
    File(fileName + ".kt").printWriter().use { out ->
        out.println("package $packageName")
        out.println("$modifier typealias $enumName = Int")
    }
    File(fileName + "Ext.kt").printWriter().use { out ->
        out.println("package $packageName")
        out.println("import kotlin.jvm.JvmField")
        out.println("$modifier object ${enumName}Ext {")
        for (i in 0 until mapping.size) {
            out.println("    $modifier const val ${mapping[i]}: $enumName = $i")
        }
        out.println("    $modifier const val values_size: Int = ${mapping.size}")
        out.println("    @JvmField $modifier val names: Array<String> = arrayOf(")
        for (i in 0 until mapping.size) {
            out.println("        \"${mapping[i]}\",")
        }
        out.println("    )")
        out.println("}")
    }
}
val generatingArgs = arrayOf(
    listOf("MyPrintWriterMode", "lupos.s00misc", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/MyPrintWriterMode"),
    listOf("BuiltInFunctions", "lupos.s02buildSyntaxTree.sparql1_1", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s02buildSyntaxTree/sparql1_1/BuiltInFunctions"),
    listOf("BinaryTestCaseOutputMode", "lupos.s00misc", "public", "src/luposdate3000_test/src/commonMain/kotlin/lupos/s00misc/BinaryTestCaseOutputMode"),
    listOf("Aggregation", "lupos.s02buildSyntaxTree.sparql1_1", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s02buildSyntaxTree/sparql1_1/Aggregation"),
    listOf("TripleStoreFeature", "lupos.s05tripleStore", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s05tripleStore/TripleStoreFeature"),
    listOf("IteratorBundleMode", "lupos.s04logicalOperators.iterator", "internal", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s04logicalOperators/iterator/IteratorBundleMode"),
    listOf("ESortPriority", "lupos.s00misc", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ESortPriority"),
    listOf("ETripleIndexType", "lupos.s00misc", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleIndexType"),
    listOf("EGraphRefType", "lupos.s00misc", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EGraphRefType"),
    listOf("EOperatorID", "lupos.s00misc", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatorID"),
    listOf("EModifyType", "lupos.s00misc", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EModifyType"),
    listOf("ETripleComponentType", "lupos.s00misc", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ETripleComponentType"),
    listOf("EGraphOperationType", "lupos.s00misc", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EGraphOperationType"),
    listOf("ESortType", "lupos.s00misc", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/ESortType"),
    listOf("EGroupMember", "", "lupos.s00misc", "public", "", "", "src/luposdate3000_optimizer/src/commonMain/kotlin/lupos/s00misc/EGroupMember"),
    listOf("EQueryResultToStream", "lupos.s11outputResult", "public", "src/luposdate3000_result_format/src/commonMain/kotlin/lupos/s11outputResult/EQueryResultToStream"),
    listOf("EPOPDebugMode", "lupos.s00misc", "public", "", "src/luposdate3000_operators/src/commonMain/kotlin/lupos/s00misc/EPOPDebugMode"),
    listOf("Turtle2ParserState", "lupos.s02buildSyntaxTree.turtle", "internal", "src/luposdate3000_parser/src/commonMain/kotlin/lupos/s02buildSyntaxTree/turtle/Turtle2ParserState"),
    listOf("EOptimizerID", "lupos.s00misc", "internal", "src/luposdate3000_optimizer/src/commonMain/kotlin/lupos/s00misc/EOptimizerID"),
    listOf("EOperatingSystem", "lupos.s00misc", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EOperatingSystem"),
    listOf("EIndexPattern", "lupos.s00misc", "public", "src/luposdate3000_shared/src/commonMain/kotlin/lupos/s00misc/EIndexPattern"),
)

for (args in generatingArgs) {
    generate(args[0], args[1], args[2], args[3])
}
