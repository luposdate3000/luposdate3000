package lupos.s02buildSyntaxTree.turtle
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.ParseError
import lupos.s02buildSyntaxTree.Token
import lupos.s02buildSyntaxTree.UnexpectedToken
import kotlin.jvm.JvmField
class TurtleParserWithDictionary(@JvmField val consume_triple: (Long, Long, Long) -> Unit, @JvmField val ltit: LookAheadTokenIterator) {
    // for storing the prefixes...
    private val prefixes = mutableMapOf<String, String>()
    // some constants used for typed literals
    private val xsd = "http://www.w3.org/2001/XMLSchema#"
    private val xsd_boolean = xsd + "boolean"
    private val xsd_integer = xsd + "integer"
    private val xsd_decimal = xsd + "decimal"
    private val xsd_double = xsd + "double"
    private val rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
    private val nil = rdf + "nil"
    val first: String = rdf + "first"
    private val rest = rdf + "rest"
    private val nil_iri = lupos.s02buildSyntaxTree.rdf.Dictionary.IRI(nil)
    private val first_iri = lupos.s02buildSyntaxTree.rdf.Dictionary.IRI(first)
    private val rest_iri = lupos.s02buildSyntaxTree.rdf.Dictionary.IRI(rest)
    private val type_iri = lupos.s02buildSyntaxTree.rdf.Dictionary.IRI(rdf + "type")
    fun turtleDoc() {
        var t1 = ltit.lookahead()
        while (t1.image == "@prefix" || t1.image == "@base" || t1.image == "PREFIX" || t1.image == "BASE" || t1 is IRI || t1 is PNAME_LN || t1 is PNAME_NS || t1 is BNODE || t1 is ANON_BNODE || t1.image == "(" || t1.image == "[") {
            statement()
            t1 = ltit.lookahead()
        }
        val token: Token = ltit.nextToken()
        if (token !is EOF) {
            throw UnexpectedToken(token, arrayOf("EOF"), ltit)
        }
    }
    private fun statement() {
        val token: Token
        val t2 = ltit.lookahead()
        when {
            t2.image == "@prefix" || t2.image == "@base" || t2.image == "PREFIX" || t2.image == "BASE" -> {
                directive()
            }
            t2 is IRI || t2 is PNAME_LN || t2 is PNAME_NS || t2 is BNODE || t2 is ANON_BNODE || t2.image == "(" || t2.image == "[" -> {
                triples()
                token = ltit.nextToken()
                if (token.image != ".") {
                    throw UnexpectedToken(token, arrayOf("."), ltit)
                }
            }
            else -> {
                throw UnexpectedToken(t2, arrayOf("@prefix", "@base", "PREFIX", "BASE", "IRI", "PNAME_LN", "PNAME_NS", "BNODE", "ANON_BNODE", "(", "["), ltit)
            }
        }
    }
    private fun directive() {
        var token: Token
        val t3 = ltit.lookahead()
        when (t3.image) {
            "@prefix" -> {
                prefixID()
            }
            "@base" -> {
                base()
            }
            "PREFIX" -> {
                sparqlPrefix()
            }
            "BASE" -> {
                sparqlBase()
            }
            else -> {
                throw UnexpectedToken(t3, arrayOf("@prefix", "@base", "PREFIX", "BASE"), ltit)
            }
        }
    }
    private fun prefixID() {
        var token: Token = ltit.nextToken()
        if (token.image != "@prefix") {
            throw UnexpectedToken(token, arrayOf("@prefix"), ltit)
        }
        token = ltit.nextToken()
        if (token !is PNAME_NS) {
            throw UnexpectedToken(token, arrayOf("PNAME_NS"), ltit)
        }
        val key = token.beforeColon
        token = ltit.nextToken()
        if (token !is IRI) {
            throw UnexpectedToken(token, arrayOf("IRI"), ltit)
        }
        prefixes[key] = token.content
        token = ltit.nextToken()
        if (token.image != ".") {
            throw UnexpectedToken(token, arrayOf("."), ltit)
        }
    }
    private fun base() {
        var token: Token = ltit.nextToken()
        if (token.image != "@base") {
            throw UnexpectedToken(token, arrayOf("@base"), ltit)
        }
        token = ltit.nextToken()
        if (token !is IRI) {
            throw UnexpectedToken(token, arrayOf("IRI"), ltit)
        }
        prefixes[""] = token.content
        token = ltit.nextToken()
        if (token.image != ".") {
            throw UnexpectedToken(token, arrayOf("."), ltit)
        }
    }
    private fun sparqlBase() {
        var token: Token = ltit.nextToken()
        if (token.image != "BASE") {
            throw UnexpectedToken(token, arrayOf("BASE"), ltit)
        }
        token = ltit.nextToken()
        if (token !is IRI) {
            throw UnexpectedToken(token, arrayOf("IRI"), ltit)
        }
        prefixes[""] = token.content
    }
    private fun sparqlPrefix() {
        var token: Token = ltit.nextToken()
        if (token.image != "PREFIX") {
            throw UnexpectedToken(token, arrayOf("PREFIX"), ltit)
        }
        token = ltit.nextToken()
        if (token !is PNAME_NS) {
            throw UnexpectedToken(token, arrayOf("PNAME_NS"), ltit)
        }
        val key = token.beforeColon
        token = ltit.nextToken()
        if (token !is IRI) {
            throw UnexpectedToken(token, arrayOf("IRI"), ltit)
        }
        prefixes[key] = token.content
    }
    private fun triples() {
        var token: Token
        val t5 = ltit.lookahead()
        when {
            t5 is IRI || t5 is PNAME_LN || t5 is PNAME_NS || t5 is BNODE || t5 is ANON_BNODE || t5.image == "(" -> {
                val s = subject()
                predicateObjectList(s)
            }
            t5.image == "[" -> {
                val s2 = blankNodePropertyList()
                val t4 = ltit.lookahead()
                if (t4 is IRI || t4 is PNAME_LN || t4 is PNAME_NS || t4.image == "A") {
                    predicateObjectList(s2)
                }
            }
            else -> {
                throw UnexpectedToken(t5, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "BNODE", "ANON_BNODE", "(", "["), ltit)
            }
        }
    }
    private fun predicateObjectList(s: Long) {
        var token: Token
        val p = verb()
        objectList(s, p)
        var t7 = ltit.lookahead()
        while (t7.image == ";") {
            token = ltit.nextToken()
            if (token.image != ";") {
                throw UnexpectedToken(token, arrayOf(";"), ltit)
            }
            val t6 = ltit.lookahead()
            if (t6 is IRI || t6 is PNAME_LN || t6 is PNAME_NS || t6.image == "A") {
                val p2 = verb()
                objectList(s, p2)
            }
            t7 = ltit.lookahead()
        }
    }
    private fun objectList(s: Long, p: Long) {
        var token: Token
        val o = triple_object()
        consume_triple(s, p, o)
        var t8 = ltit.lookahead()
        while (t8.image == ",") {
            token = ltit.nextToken()
            if (token.image != ",") {
                throw UnexpectedToken(token, arrayOf(","), ltit)
            }
            val o2 = triple_object()
            consume_triple(s, p, o2)
            t8 = ltit.lookahead()
        }
    }
    private fun verb(): Long {
        val token: Token
        val t9 = ltit.lookahead()
        when {
            t9 is IRI || t9 is PNAME_LN || t9 is PNAME_NS -> {
                return predicate()
            }
            t9.image == "A" -> {
                token = ltit.nextToken()
                if (token.image != "A") {
                    throw UnexpectedToken(token, arrayOf("A"), ltit)
                }
                if ((token as POSSIBLE_KEYWORD).original_image != "a") {
                    throw UnexpectedToken(token, arrayOf("a"), ltit)
                } else {
                    return type_iri
                }
            }
            else -> {
                throw UnexpectedToken(t9, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "A"), ltit)
            }
        }
    }
    private fun subject(): Long {
        var token: Token
        val result: Long
        val t10 = ltit.lookahead()
        result = when {
            t10 is IRI || t10 is PNAME_LN || t10 is PNAME_NS -> {
                iri()
            }
            t10 is BNODE || t10 is ANON_BNODE -> {
                BlankNode()
            }
            t10.image == "(" -> {
                collection()
            }
            else -> {
                throw UnexpectedToken(t10, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "BNODE", "ANON_BNODE", "("), ltit)
            }
        }
        return result
    }
    private fun predicate(): Long {
        var token: Token
        return iri()
    }
    private fun triple_object(): Long {
        var token: Token
        val result: Long
        val t11 = ltit.lookahead()
        when {
            t11 is IRI || t11 is PNAME_LN || t11 is PNAME_NS -> {
                result = iri()
            }
            t11 is BNODE || t11 is ANON_BNODE -> {
                result = BlankNode()
            }
            t11.image == "(" -> {
                result = collection()
            }
            t11.image == "[" -> {
                result = blankNodePropertyList()
            }
            t11 is STRING || t11 is INTEGER || t11 is DECIMAL || t11 is DOUBLE || t11.image == "true" || t11.image == "false" -> {
                result = literal()
            }
            else -> {
                throw UnexpectedToken(t11, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "BNODE", "ANON_BNODE", "(", "[", "STRING", "INTEGER", "DECIMAL", "DOUBLE", "true", "false"), ltit)
            }
        }
        return result
    }
    private fun literal(): Long {
        var token: Token
        val result: Long
        val t12 = ltit.lookahead()
        result = when {
            t12 is STRING -> {
                RDFLiteral()
            }
            t12 is INTEGER || t12 is DECIMAL || t12 is DOUBLE -> {
                NumericLiteral()
            }
            t12.image == "true" || t12.image == "false" -> {
                BooleanLiteral()
            }
            else -> {
                throw UnexpectedToken(t12, arrayOf("STRING", "INTEGER", "DECIMAL", "DOUBLE", "true", "false"), ltit)
            }
        }
        return result
    }
    private fun blankNodePropertyList(): Long {
        val result = lupos.s02buildSyntaxTree.rdf.Dictionary.BlankNode()
        var token: Token = ltit.nextToken()
        if (token.image != "[") {
            throw UnexpectedToken(token, arrayOf("["), ltit)
        }
        predicateObjectList(result)
        token = ltit.nextToken()
        if (token.image != "]") {
            throw UnexpectedToken(token, arrayOf("]"), ltit)
        }
        return result
    }
    private fun collection(): Long {
        var first = nil_iri
        var current = nil_iri
        var token: Token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        var t13 = ltit.lookahead()
        while (t13 is IRI || t13 is PNAME_LN || t13 is PNAME_NS || t13 is BNODE || t13 is ANON_BNODE || t13.image == "(" || t13.image == "[" || t13 is STRING || t13 is INTEGER || t13 is DECIMAL || t13 is DOUBLE || t13.image == "true" || t13.image == "false") {
            val next = lupos.s02buildSyntaxTree.rdf.Dictionary.BlankNode()
            if (current == nil_iri) {
                first = next
            } else {
                consume_triple(current, rest_iri, next)
            }
            current = next
            val o = triple_object()
            consume_triple(current, first_iri, o)
            t13 = ltit.lookahead()
        }
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        if (current != nil_iri) {
            consume_triple(current, rest_iri, nil_iri)
        }
        return first
    }
    private fun NumericLiteral(): Long {
        val token: Token
        when (val t14 = ltit.lookahead()) {
            is INTEGER -> {
                token = ltit.nextToken()
                if (token !is INTEGER) {
                    throw UnexpectedToken(token, arrayOf("INTEGER"), ltit)
                }
                return lupos.s02buildSyntaxTree.rdf.Dictionary.TypedLiteral(token.image, type = xsd_integer)
            }
            is DECIMAL -> {
                token = ltit.nextToken()
                if (token !is DECIMAL) {
                    throw UnexpectedToken(token, arrayOf("DECIMAL"), ltit)
                }
                return lupos.s02buildSyntaxTree.rdf.Dictionary.TypedLiteral(token.image, type = xsd_decimal)
            }
            is DOUBLE -> {
                token = ltit.nextToken()
                if (token !is DOUBLE) {
                    throw UnexpectedToken(token, arrayOf("DOUBLE"), ltit)
                }
                return lupos.s02buildSyntaxTree.rdf.Dictionary.TypedLiteral(token.image, type = xsd_double)
            }
            else -> {
                throw UnexpectedToken(t14, arrayOf("INTEGER", "DECIMAL", "DOUBLE"), ltit)
            }
        }
    }
    private fun RDFLiteral(): Long {
        var token: Token
        token = ltit.nextToken()
        if (token !is STRING) {
            throw UnexpectedToken(token, arrayOf("STRING"), ltit)
        }
        val content = token.content
        val delimiter = token.leftBrace
        val t16 = ltit.lookahead()
        if (t16 is LANGTAG || t16.image == "^^") {
            val t15 = ltit.lookahead()
            when {
                t15 is LANGTAG -> {
                    token = ltit.nextToken()
                    if (token !is LANGTAG) {
                        throw UnexpectedToken(token, arrayOf("LANGTAG"), ltit)
                    }
                    return lupos.s02buildSyntaxTree.rdf.Dictionary.LanguageTaggedLiteral(content, delimiter, token.language)
                }
                t15.image == "^^" -> {
                    token = ltit.nextToken()
                    if (token.image != "^^") {
                        throw UnexpectedToken(token, arrayOf("^^"), ltit)
                    }
                    val type_iri = iri_string()
                    return lupos.s02buildSyntaxTree.rdf.Dictionary.TypedLiteral(content, delimiter, type_iri)
                }
                else -> {
                    throw UnexpectedToken(t15, arrayOf("LANGTAG", "^^"), ltit)
                }
            }
        }
        return lupos.s02buildSyntaxTree.rdf.Dictionary.SimpleLiteral(content, delimiter)
    }
    private fun BooleanLiteral(): Long {
        val token: Token
        val t17 = ltit.lookahead()
        when (t17.image) {
            "true" -> {
                token = ltit.nextToken()
                if (token.image != "true") {
                    throw UnexpectedToken(token, arrayOf("true"), ltit)
                }
                if ((token as POSSIBLE_KEYWORD).original_image != "true") {
                    throw UnexpectedToken(token, arrayOf("true"), ltit)
                }; return lupos.s02buildSyntaxTree.rdf.Dictionary.TypedLiteral("true", type = xsd_boolean)
            }
            "false" -> {
                token = ltit.nextToken()
                if (token.image != "false") {
                    throw UnexpectedToken(token, arrayOf("false"), ltit)
                }
                if ((token as POSSIBLE_KEYWORD).original_image != "false") {
                    throw UnexpectedToken(token, arrayOf("false"), ltit)
                }; return lupos.s02buildSyntaxTree.rdf.Dictionary.TypedLiteral("false", type = xsd_boolean)
            }
            else -> {
                throw UnexpectedToken(t17, arrayOf("true", "false"), ltit)
            }
        }
    }
    fun iri(): Long {
        val token: Token
        val iri: String
        when (val t18 = ltit.lookahead()) {
            is IRI -> {
                token = ltit.nextToken()
                if (token !is IRI) {
                    throw UnexpectedToken(token, arrayOf("IRI"), ltit)
                }
                iri = token.content
            }
            is PNAME_LN, is PNAME_NS -> {
                iri = PrefixedName()
            }
            else -> {
                throw UnexpectedToken(t18, arrayOf("IRI", "PNAME_LN", "PNAME_NS"), ltit)
            }
        }
        // Do some kind of relative IRI detection and resolution to the base iri (if given)
        // This part is currently not perfect!
        if (iri.startsWith('/') || iri.startsWith('#')) {
            val base = prefixes[""]
            if (base != null) {
                return lupos.s02buildSyntaxTree.rdf.Dictionary.IRI(base + iri.substring(1))
            }
        }
        return lupos.s02buildSyntaxTree.rdf.Dictionary.IRI(iri)
    }
    private fun iri_string(): String {
        val token: Token
        val iri: String
        when (val t19 = ltit.lookahead()) {
            is IRI -> {
                token = ltit.nextToken()
                if (token !is IRI) {
                    throw UnexpectedToken(token, arrayOf("IRI"), ltit)
                }
                iri = token.content
            }
            is PNAME_LN, is PNAME_NS -> {
                iri = PrefixedName()
            }
            else -> {
                throw UnexpectedToken(t19, arrayOf("IRI", "PNAME_LN", "PNAME_NS"), ltit)
            }
        }
        // Do some kind of relative IRI detection and resolution to the base iri (if given)
        // This part is currently not perfect!
        if (iri.startsWith('/') || iri.startsWith('#')) {
            val base = prefixes[""]
            if (base != null) {
                return base + iri.substring(1)
            }
        }
        return iri
    }
    private fun PrefixedName(): String {
        val token: Token
        when (val t20 = ltit.lookahead()) {
            is PNAME_LN -> {
                token = ltit.nextToken()
                if (token !is PNAME_LN) {
                    throw UnexpectedToken(token, arrayOf("PNAME_LN"), ltit)
                }
                val key = token.beforeColon
                val result = prefixes[key]; if (result == null) throw ParseError("Prefix $key has not been defined", token, ltit); else return result + token.afterColon
            }
            is PNAME_NS -> {
                token = ltit.nextToken()
                if (token !is PNAME_NS) {
                    throw UnexpectedToken(token, arrayOf("PNAME_NS"), ltit)
                }
                val key = token.beforeColon
                val result = prefixes[key]; if (result == null) throw ParseError("Prefix $key has not been defined", token, ltit); else return result
            }
            else -> {
                throw UnexpectedToken(t20, arrayOf("PNAME_LN", "PNAME_NS"), ltit)
            }
        }
    }
    private fun BlankNode(): Long {
        val token: Token
        when (val t21 = ltit.lookahead()) {
            is BNODE -> {
                token = ltit.nextToken()
                if (token !is BNODE) {
                    throw UnexpectedToken(token, arrayOf("BNODE"), ltit)
                }
                return lupos.s02buildSyntaxTree.rdf.Dictionary.BlankNode(token.name)
            }
            is ANON_BNODE -> {
                token = ltit.nextToken()
                if (token !is ANON_BNODE) {
                    throw UnexpectedToken(token, arrayOf("ANON_BNODE"), ltit)
                }
                return lupos.s02buildSyntaxTree.rdf.Dictionary.BlankNode()
            }
            else -> {
                throw UnexpectedToken(t21, arrayOf("BNODE", "ANON_BNODE"), ltit)
            }
        }
    }
}
