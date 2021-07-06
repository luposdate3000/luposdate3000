package lupos.simulator_db

public class PreprocessingPackage(
    public val destinationAddresses: IntArray, // Richtung triple store
    public val operatorGraphParts: ByteArray,
    public val senderAddress: Int, // dies MUSS ein DB-node sein ... von wo kommt das paket
    public val queryID: Int, // die ist immer gleich für alles was zu einem "QueryPackage" gehört
) : IDatabasePackage

public class ChoosenOperatorPackage(
    // siehe #2
    public val destinationAddress: Int, // Richtung root-node
    public val senderAddress: Int,
    public val operators: IntArray, // zeigt an welche "operatorGraphParts" teile berechnet werden - dadurch ist schnell klar, welcher node was berechnet
    public val queryID: Int,
) : IDatabasePackage

public class ResultPackage(
    public val result: ByteArray, // die Nutzdaten ... zurzeit alles als ein Block, später besser bidirektionales streaming, wobei primär Richtung root-node gesendet wird.
    public val destinationAddress: Int, // Richtung root-node
    public val senderAddress: Int,
    public val queryID: Int,
    public val operatorID: Int, // damit der empfänger weiß, was für ein ergebnis dies ist ... kann ggf in "result" integriert werden
) : IDatabasePackage
