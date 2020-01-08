package lupos.s2buildOperatorGraph

open class OPBase {

    open fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n"

    override fun toString(): String {
        return toString("")
    }
}
