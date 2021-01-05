package lupos.s05tripleStore
public object TripleStoreRegister {
    public fun generateRootKey(graphname: String, classname: String, params: String): String {
        return "R_${graphname}_${classname}_$params"
    }
    public fun generateChildKey(classname: String, params: String): String {
        return "C_${classname}_$params"
    }
}
