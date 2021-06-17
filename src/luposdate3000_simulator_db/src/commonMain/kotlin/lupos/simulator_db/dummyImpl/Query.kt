package lupos.simulator_db.dummyImpl

public class Query(
    public val operatorGraphParts: List<OperatorGraphPart>,
    public val nextHops: IntArray,
    public val parentAddress: Int,
) {
    public val choosenOperators: MutableList<OperatorGraphPart> = mutableListOf()
    public val answeredByNextHop: BooleanArray = BooleanArray(nextHops.size) { false } // wenn der nächste hop schon geantwortet hat, was er berechnen kann siehe #2, erst wenn alle geantwortet haben, startet die berechnung.
}
