package lupos.s02buildSyntaxTree.sparql1_1
import kotlin.jvm.JvmField
public object BuiltInFunctionsExt {
    public  val ABS: BuiltInFunctions = BuiltInFunctions(0)
    public  val BNODE: BuiltInFunctions = BuiltInFunctions(1)
    public  val BOUND: BuiltInFunctions = BuiltInFunctions(2)
    public  val CEIL: BuiltInFunctions = BuiltInFunctions(3)
    public  val COALESCE: BuiltInFunctions = BuiltInFunctions(4)
    public  val CONCAT: BuiltInFunctions = BuiltInFunctions(5)
    public  val CONTAINS: BuiltInFunctions = BuiltInFunctions(6)
    public  val DATATYPE: BuiltInFunctions = BuiltInFunctions(7)
    public  val DAY: BuiltInFunctions = BuiltInFunctions(8)
    public  val ENCODE_FOR_URI: BuiltInFunctions = BuiltInFunctions(9)
    public  val Exists: BuiltInFunctions = BuiltInFunctions(10)
    public  val FLOOR: BuiltInFunctions = BuiltInFunctions(11)
    public  val HOURS: BuiltInFunctions = BuiltInFunctions(12)
    public  val IF: BuiltInFunctions = BuiltInFunctions(13)
    public  val IRI: BuiltInFunctions = BuiltInFunctions(14)
    public  val LANG: BuiltInFunctions = BuiltInFunctions(15)
    public  val LANGMATCHES: BuiltInFunctions = BuiltInFunctions(16)
    public  val LCASE: BuiltInFunctions = BuiltInFunctions(17)
    public  val MD5: BuiltInFunctions = BuiltInFunctions(18)
    public  val MINUTES: BuiltInFunctions = BuiltInFunctions(19)
    public  val MONTH: BuiltInFunctions = BuiltInFunctions(20)
    public  val NOW: BuiltInFunctions = BuiltInFunctions(21)
    public  val NotExists: BuiltInFunctions = BuiltInFunctions(22)
    public  val RAND: BuiltInFunctions = BuiltInFunctions(23)
    public  val ROUND: BuiltInFunctions = BuiltInFunctions(24)
    public  val RegexExpression: BuiltInFunctions = BuiltInFunctions(25)
    public  val SECONDS: BuiltInFunctions = BuiltInFunctions(26)
    public  val SHA1: BuiltInFunctions = BuiltInFunctions(27)
    public  val SHA256: BuiltInFunctions = BuiltInFunctions(28)
    public  val SHA384: BuiltInFunctions = BuiltInFunctions(29)
    public  val SHA512: BuiltInFunctions = BuiltInFunctions(30)
    public  val STR: BuiltInFunctions = BuiltInFunctions(31)
    public  val STRAFTER: BuiltInFunctions = BuiltInFunctions(32)
    public  val STRBEFORE: BuiltInFunctions = BuiltInFunctions(33)
    public  val STRDT: BuiltInFunctions = BuiltInFunctions(34)
    public  val STRENDS: BuiltInFunctions = BuiltInFunctions(35)
    public  val STRLANG: BuiltInFunctions = BuiltInFunctions(36)
    public  val STRLEN: BuiltInFunctions = BuiltInFunctions(37)
    public  val STRSTARTS: BuiltInFunctions = BuiltInFunctions(38)
    public  val STRUUID: BuiltInFunctions = BuiltInFunctions(39)
    public  val StrReplaceExpression: BuiltInFunctions = BuiltInFunctions(40)
    public  val SubstringExpression: BuiltInFunctions = BuiltInFunctions(41)
    public  val TIMEZONE: BuiltInFunctions = BuiltInFunctions(42)
    public  val TZ: BuiltInFunctions = BuiltInFunctions(43)
    public  val UCASE: BuiltInFunctions = BuiltInFunctions(44)
    public  val URI: BuiltInFunctions = BuiltInFunctions(45)
    public  val UUID: BuiltInFunctions = BuiltInFunctions(46)
    public  val YEAR: BuiltInFunctions = BuiltInFunctions(47)
    public  val isBLANK: BuiltInFunctions = BuiltInFunctions(48)
    public  val isIRI: BuiltInFunctions = BuiltInFunctions(49)
    public  val isLITERAL: BuiltInFunctions = BuiltInFunctions(50)
    public  val isNUMERIC: BuiltInFunctions = BuiltInFunctions(51)
    public  val isURI: BuiltInFunctions = BuiltInFunctions(52)
    public  val sameTerm: BuiltInFunctions = BuiltInFunctions(53)
    public const val values_size: Int = 54
    @JvmField public val names: Array<String> = arrayOf(
        "ABS",
        "BNODE",
        "BOUND",
        "CEIL",
        "COALESCE",
        "CONCAT",
        "CONTAINS",
        "DATATYPE",
        "DAY",
        "ENCODE_FOR_URI",
        "Exists",
        "FLOOR",
        "HOURS",
        "IF",
        "IRI",
        "LANG",
        "LANGMATCHES",
        "LCASE",
        "MD5",
        "MINUTES",
        "MONTH",
        "NOW",
        "NotExists",
        "RAND",
        "ROUND",
        "RegexExpression",
        "SECONDS",
        "SHA1",
        "SHA256",
        "SHA384",
        "SHA512",
        "STR",
        "STRAFTER",
        "STRBEFORE",
        "STRDT",
        "STRENDS",
        "STRLANG",
        "STRLEN",
        "STRSTARTS",
        "STRUUID",
        "StrReplaceExpression",
        "SubstringExpression",
        "TIMEZONE",
        "TZ",
        "UCASE",
        "URI",
        "UUID",
        "YEAR",
        "isBLANK",
        "isIRI",
        "isLITERAL",
        "isNUMERIC",
        "isURI",
        "sameTerm",
    )
}
