package lupos.s00misc

import lupos.s07physicalOperators.singleinput.POPSort
import lupos.s07physicalOperators.singleinput.POPRename
import lupos.s07physicalOperators.singleinput.POPProjection
import lupos.s07physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s07physicalOperators.singleinput.POPGroup
import lupos.s07physicalOperators.singleinput.POPFilter
import lupos.s07physicalOperators.singleinput.POPFilterExact
import lupos.s07physicalOperators.singleinput.POPBindUndefined
import lupos.s07physicalOperators.singleinput.POPBind
import lupos.s07physicalOperators.singleinput.modifiers.POPOffset
import lupos.s07physicalOperators.singleinput.modifiers.POPLimit
import lupos.s07physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s07physicalOperators.POPValues
import lupos.s07physicalOperators.POPExpression
import lupos.s07physicalOperators.POPEmptyRow
import lupos.s07physicalOperators.POPBase
import lupos.s07physicalOperators.multiinput.POPUnion
import lupos.s07physicalOperators.multiinput.POPJoinHashMap

fun XMLElement.Companion.convertToPOPBase(node: XMLElement): POPBase {
    return when (node.tag) {
        "POPSort" -> POPSort.fromXMLElement(node)
        "POPRename" -> POPRename.fromXMLElement(node)
        "POPProjection" -> POPProjection.fromXMLElement(node)
        "POPMakeBooleanResult" -> POPMakeBooleanResult.fromXMLElement(node)
        "POPGroup" -> POPGroup.fromXMLElement(node)
        "POPFilter" -> POPFilter.fromXMLElement(node)
        "POPFilterExact" -> POPFilterExact.fromXMLElement(node)
        "POPBindUndefined" -> POPBindUndefined.fromXMLElement(node)
        "POPBind" -> POPBind.fromXMLElement(node)
        "POPOffset" -> POPOffset.fromXMLElement(node)
        "POPLimit" -> POPLimit.fromXMLElement(node)
        "POPDistinct" -> POPDistinct.fromXMLElement(node)
        "POPValues" -> POPValues.fromXMLElement(node)
        "POPEmptyRow" -> POPEmptyRow.fromXMLElement(node)
        "POPUnion" -> POPUnion.fromXMLElement(node)
        "POPJoinHashMap" -> POPJoinHashMap.fromXMLElement(node)
        else -> throw Exception("unknown Physical Operator")
    }
}
