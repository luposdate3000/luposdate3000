package dummyImpl

class Query(
    val operatorGraphParts: List<OperatorGraphPart>,
    val nextHops: IntArray, // für #2
    val parentAddress: Int,
) {
    val choosenOperators = mutableListOf<OperatorGraphPart>()
    val answeredByNextHop = BooleanArray(nextHops.size) { false } // wenn der nächste hop schon geantwortet hat, was er berechnen kann siehe #2, erst wenn alle geantwortet haben, startet die berechnung.
}