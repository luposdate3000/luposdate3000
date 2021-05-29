class PreprocessingPackage(
    val destinationAddresses: IntArray, // Richtung triple store
    val operatorGraphParts: ByteArray,
    val senderAddress: Int, // dies MUSS ein DB-node sein ... von wo kommt das paket
    val queryID: Int, // die ist immer gleich für alles was zu einem "QueryPackage" gehört
): IDatabasePackage

class ChoosenOperatorPackage( // siehe #2
    val destinationAddress: Int, // Richtung root-node
    val senderAddress: Int,
    val operators: IntArray, // zeigt an welche "operatorGraphParts" teile berechnet werden - dadurch ist schnell klar, welcher node was berechnet
    val queryID: Int,
): IDatabasePackage

class ResultPackage(
    val result: ByteArray, // die Nutzdaten ... zurzeit alles als ein Block, später besser bidirektionales streaming, wobei primär Richtung root-node gesendet wird.
    val destinationAddress: Int, // Richtung root-node
    val senderAddress: Int,
    val queryID: Int,
    val operatorID: Int, // damit der empfänger weiß, was für ein ergebnis dies ist ... kann ggf in "result" integriert werden
): IDatabasePackage