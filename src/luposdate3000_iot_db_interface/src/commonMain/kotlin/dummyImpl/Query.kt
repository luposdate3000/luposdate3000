package lupos.iot_db_interface.dummyImpl

public class Query(
    public val operatorGraphParts: List<OperatorGraphPart>,
    public val nextHops: IntArray, // für #2
    public val parentAddress: Int,
) {
    public val choosenOperators: MutableList<OperatorGraphPart> = mutableListOf<OperatorGraphPart>()
    public val answeredByNextHop: BooleanArray = BooleanArray(nextHops.size) { false } // wenn der nächste hop schon geantwortet hat, was er berechnen kann siehe #2, erst wenn alle geantwortet haben, startet die berechnung.
}
