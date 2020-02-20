package lupos.s14endpoint

import lupos.s00misc.EIndexPattern
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04ArithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.toASTNode
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPJoinNestedLoop
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPExpression
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPRename
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s09physicalOperators.singleinput.POPTemporaryStore
import lupos.s12p2p.POPServiceIRI
import lupos.s15tripleStoreDistributed.DistributedTripleStore


fun createAOPVariable(mapping: MutableMap<String, String>, name: String): AOPVariable {
    val n = mapping[name]
    if (n != null)
        return AOPVariable(n)
    return AOPVariable(name)
}

fun XMLElement.Companion.convertToOPBase(dictionary: ResultSetDictionary, transactionID: Long, node: XMLElement, mapping: MutableMap<String, String> = mutableMapOf<String, String>()): OPBase {
    return when (node.tag) {
        "POPSort" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            POPSort(dictionary, createAOPVariable(mapping, node.attributes["by"]!!), node.attributes["order"] == "ASC", child)
        }
        "POPRename" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            POPRename(dictionary, createAOPVariable(mapping, node.attributes["nameTo"]!!), createAOPVariable(mapping, node.attributes["nameFrom"]!!), child)
        }
        "POPProjection" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            val variables = mutableListOf<AOPVariable>()
            node["variables"]!!.childs.forEach {
                variables.add(createAOPVariable(mapping, it.attributes["name"]!!))
            }
            return POPProjection(dictionary, variables, child)
        }
        "POPMakeBooleanResult" -> POPMakeBooleanResult(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        "POPGroup" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            val by = mutableListOf<AOPVariable>()
            var bindings: POPBase = POPEmptyRow(dictionary)
            node["by"]!!.childs.forEach {
                by.add(createAOPVariable(mapping, it.attributes["name"]!!))
            }
            node["bindings"]!!.childs.forEach {
                bindings = POPBind(dictionary, createAOPVariable(mapping, it.attributes["name"]!!), POPExpression.fromXMLElement(dictionary, it.childs[0]), bindings)
            }
            if (bindings is POPEmptyRow)
                return POPGroup(dictionary, by, null, child)
            return POPGroup(dictionary, by, bindings as POPBind, child)
        }
        "POPFilter" -> POPFilter(dictionary, POPExpression.fromXMLElement(dictionary, node["children"]!!.childs[1]), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        "POPFilterExact" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            POPFilterExact(dictionary, createAOPVariable(mapping, node.attributes["name"]!!), node.attributes["value"]!!, child)
        }
        "POPBindUndefined" -> {
            val child = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            POPBindUndefined(dictionary, createAOPVariable(mapping, node.attributes["name"]!!), child)
        }
        "POPBind" -> {
            val child0 = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping)
            val child1 = convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping)
            POPBind(dictionary, createAOPVariable(mapping, node.attributes["name"]!!), child1 as POPExpression, child0)
        }
        "POPOffset" -> POPOffset(dictionary, node.attributes["offset"]!!.toInt(), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        "POPLimit" -> POPLimit(dictionary, node.attributes["limit"]!!.toInt(), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        "POPDistinct" -> POPDistinct(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        "POPValues" -> {
            val vars = mutableListOf<String>()
            val vals = mutableListOf<MutableMap<String, String>>()
            node["variables"]!!.childs.forEach {
                vars.add(it.attributes["name"]!!)
            }
            node["bindings"]!!.childs.forEach {
                val exp = mutableMapOf<String, String>()
                it.childs.forEach {
                    exp[it.attributes["name"]!!] = it.attributes["content"]!!
                }
                vals.add(exp)
            }
            return POPValues(dictionary, vars, vals)
        }
        "POPEmptyRow" -> POPEmptyRow(dictionary)
        "POPUnion" -> POPUnion(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping))
        "POPJoinNestedLoop" -> POPJoinNestedLoop(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
        "POPJoinHashMap" -> POPJoinHashMap(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[1], mapping), node.attributes["optional"]!!.toBoolean())
        "POPTemporaryStore" -> POPTemporaryStore(dictionary, convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        "POPExpression" -> POPExpression(dictionary, XMLElement.toASTNode(node.childs[0]))
        "TripleStoreIteratorLocal" -> {
            val res = DistributedTripleStore.getNamedGraph(node.attributes["name"]!!).getIterator(transactionID, dictionary, EIndexPattern.SPO)
            val olduuid = node.attributes["uuid"]
            mapping["#s" + olduuid] = "#s${res.uuid}"
            mapping["#p" + olduuid] = "#p${res.uuid}"
            mapping["#o" + olduuid] = "#o${res.uuid}"
            return res
        }
        "TripleStoreIteratorLocalFilter" -> {
            val sFilter = node.attributes["filterS"]
            val sName = node.attributes["nameS"]
            val pFilter = node.attributes["filterP"]
            val pName = node.attributes["nameP"]
            val oFilter = node.attributes["filterO"]
            val oName = node.attributes["nameO"]

            val sv = sFilter != null
            val pv = pFilter != null
            val ov = oFilter != null
            val s = if (sv)
                sFilter!!
            else
                sName!!
            val p = if (pv)
                pFilter!!
            else
                pName!!
            val o = if (ov)
                oFilter!!
            else
                oName!!

            val res = DistributedTripleStore.getNamedGraph(node.attributes["name"]!!).getIterator(transactionID, dictionary, s, p, o, sv, pv, ov, EIndexPattern.SPO)
            return res
        }
        "TripleStoreIteratorGlobalFilter" -> {
            val sFilter = node.attributes["filterS"]
            val sName = node.attributes["nameS"]
            val pFilter = node.attributes["filterP"]
            val pName = node.attributes["nameP"]
            val oFilter = node.attributes["filterO"]
            val oName = node.attributes["nameO"]

            val sv = sFilter != null
            val pv = pFilter != null
            val ov = oFilter != null
            val s = if (sv)
                sFilter!!
            else
                sName!!
            val p = if (pv)
                pFilter!!
            else
                pName!!
            val o = if (ov)
                oFilter!!
            else
                oName!!

            val res = DistributedTripleStore.getNamedGraph(node.attributes["name"]!!).getIterator(transactionID, dictionary, s, p, o, sv, pv, ov, EIndexPattern.SPO)
            return res
        }
        "TripleStoreIteratorGlobal" -> {
            val res = DistributedTripleStore.getNamedGraph(node.attributes["name"]!!).getIterator(transactionID, dictionary, EIndexPattern.SPO)
            val olduuid = node.attributes["uuid"]
            mapping["#s" + olduuid] = "#s${res.uuid}"
            mapping["#p" + olduuid] = "#p${res.uuid}"
            mapping["#o" + olduuid] = "#o${res.uuid}"
            return res
        }
        "POPServiceIRI" -> return POPServiceIRI(dictionary, transactionID, node.attributes["name"]!!, node.attributes["silent"]!!.toBoolean(), convertToOPBase(dictionary, transactionID, node["children"]!!.childs[0], mapping))
        else -> throw Exception("XMLElement.Companion.convertToOPBase unknown :: ${node.tag}")
    }
}
