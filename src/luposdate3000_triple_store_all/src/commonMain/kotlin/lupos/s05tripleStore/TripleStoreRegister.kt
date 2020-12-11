package lupos.s05tripleStore
object TripleStoreRegister {
    fun generateRootKey(graphname: String, classname: String, params: String): String {
        return "R_${graphname}_${classname}_$params"
    }
    fun generateChildKey(classname: String, params: String): String {
        return "C_${classname}_$params"
    }
}
