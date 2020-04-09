package lupos.s04logicalOperators

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s00misc.*
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.singleinput.*

abstract class OPBase(val query: Query, val operatorID: EOperatorID, val classname: String, val children: Array<OPBase>,val sortPriority:ESortPriority) {
    open suspend fun evaluate(): ColumnIteratorRow = throw Exception("not implemented $classname.evaluate")
    abstract fun cloneOP(): OPBase


val sortPriorityInternal=mutableListOf<String>()
 fun getSortPriority():List<String>{
when(sortPriority){
ESortPriority.ANY_PROVIDED_VARIABLE->{
return sortPriorityInternal
}
}
throw Exception("not implemented $sortPriority")
}
 fun canAddSortPriority(name:String):Boolean{
 when(sortPriority){
ESortPriority.ANY_PROVIDED_VARIABLE->{
return sortPriorityInternal.contains(name)||getProvidedVariableNames().contains(name)
}
}
throw Exception("not implemented $sortPriority")
}
 fun addSortPriority(name:String){
when(sortPriority){
ESortPriority.ANY_PROVIDED_VARIABLE->{
if(!sortPriorityInternal.contains(name)){
sortPriorityInternal.add(name)
}
}
}
throw Exception("not implemented $sortPriority")
}

    open fun applyPrefix(prefix: String, iri: String) {
        for (c in children) {
            c.applyPrefix(prefix, iri)
        }
    }

    open fun childrenToVerifyCount(): Int = children.size
    open fun updateChildren(i: Int, child: OPBase) {
        SanityCheck.check({ i < children.size })
        children[i] = child
    }

    open fun toString(indentation: String): String = "${indentation}${classNameToString(this)}\n"

    companion object {
        private val global_uuid = ThreadSafeUuid()
    }

    @JvmField
    val uuid: Long = global_uuid.next()

    override fun toString(): String = toXMLElement().toPrettyString()
    fun getRequiredVariableNamesRecoursive(): List<String> {
        val res = getRequiredVariableNames().toMutableList()
        for (c in children) {
            res += c.getRequiredVariableNamesRecoursive()
        }
        return res.distinct()
    }

    open fun getRequiredVariableNames(): List<String> {
        return mutableListOf()
    }

    open fun getProvidedVariableNames(): List<String> {
        val res = mutableListOf<String>()
        for (c in children) {
            res.addAll(c.getProvidedVariableNames())
        }
        return res.distinct()
    }

    open fun toSparqlQuery(): String {
        return "SELECT * WHERE{" + toSparql() + "}"
    }

    open fun toSparql(): String {
        throw Exception("not implemented $classname.toSparql()")
    }

    open fun toXMLElement(): XMLElement {
        val res = XMLElement(classname)
        res.addAttribute("uuid", "" + uuid)
        if (children.size > 0) {
            res.addContent(childrenToXML())
        }
        return res
    }

    fun childrenToXML(): XMLElement {
        val res = XMLElement("children")
        for (c in children) {
            res.addContent(c.toXMLElement())
        }
        return res
    }

    fun replaceVariableWithUndef(node: OPBase, name: String): OPBase {
        if (node is AOPVariable && node.name == name) {
            return AOPConstant(query, ResultSetDictionary.undefValue2)
        }
        for (i in 0 until node.children.size) {
            node.children[i] = replaceVariableWithUndef(node.children[i], name)
        }
        return node
    }

    fun syntaxVerifyAllVariableExistsAutocorrect() {
        for (name in getRequiredVariableNames()) {
            var found = false
            for (prov in getProvidedVariableNames()) {
                if (prov == name) {
                    found = true
                    break
                }
            }
            if (!found) {
                println("syntaxVerifyAllVariableExistsAutocorrect $classname")
                if (this is LOPBind || this is LOPFilter) {
                    children[1] = replaceVariableWithUndef(children[1], name)
                } else {
                    require(this is LOPGroup)
                    children[1] = replaceVariableWithUndef(children[1], name)
                    by.forEach {
                        if (it.name == name) {
                            throw Exception("group by undefined variable $name")
                        }
                    }
                }
            }
        }
    }

    open fun syntaxVerifyAllVariableExists(additionalProvided: List<String> = listOf(), autocorrect: Boolean = false) {
        for (i in 0 until childrenToVerifyCount()) {
            children[i].syntaxVerifyAllVariableExists(additionalProvided, autocorrect)
        }
        val res = (additionalProvided + getProvidedVariableNames()).containsAll(getRequiredVariableNames())
        if (!res) {
            if (autocorrect) {
                syntaxVerifyAllVariableExistsAutocorrect()
            } else {
                throw Exception("${classNameToString(this)} undefined Variable ${toXMLElement().toPrettyString()} ${additionalProvided} ${getProvidedVariableNames()} ${getRequiredVariableNames()}")
            }
        }
    }

    fun setChild(child: OPBase): OPBase {
        SanityCheck.check({ children.isNotEmpty() })
        this.children[0] = child
        return child
    }

    fun getLatestChild(): OPBase {
        if (children.isNotEmpty() && children[0].children.isNotEmpty()) {
            return children[0].getLatestChild()
        }
        return this
    }
}
