package lupos.s00misc
import kotlin.jvm.JvmField
public data class SortHelper public constructor(@JvmField public val variableName: String, @JvmField public val sortType: ESortType) {
    override fun toString(): String = "$variableName.$sortType"
}
