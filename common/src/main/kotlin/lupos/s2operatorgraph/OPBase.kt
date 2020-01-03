package lupos.s2operatorgraph

open class OPBase {

    open fun toString(indentation: String): String = "${indentation}${this::class.simpleName}\n"

    override fun toString(): String {
        return toString("")
    }

}