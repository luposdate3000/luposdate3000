package lupos.simulator_db.dummyImpl

public object Optimizer {
    public fun extractTripleStoreAddresses(l: List<OperatorGraphPart>): List<Int> {
        return listOf()
    }

    public fun optimize(s: String): OperatorGraph {
        return OperatorGraph()
    }

    public fun split(o: OperatorGraph): List<OperatorGraphPart> {
        return listOf()
    }

    public fun assignNodesToTripleStroceAccess(l: List<OperatorGraphPart>) {
// dies passiert zentralisiert, da die DB weiß, welche nodes es gibt, und wo was steht
    }
}
