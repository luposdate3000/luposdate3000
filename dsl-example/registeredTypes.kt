package lupos.codegen

val registeredTypes: Map<String, CodeType> = mutableMapOf(
    "Error" to CodeType(null, null),
    "Boolean" to CodeType("Boolean", null),
    "Unit" to CodeType("Unit", null),
    "Int" to CodeType("Int", null),
    "Double" to CodeType("Double", null),
    "BigInteger" to CodeType("BigInteger", "com.ionspin.kotlin.bignum.integer"),
    "BigDecimal" to CodeType("BigDecimal", "com.ionspin.kotlin.bignum.decimal"),
    "ByteArrayWrapper" to CodeType("ByteArrayWrapper", "lupos.shared"),
    "ByteArrayWrapperDouble" to CodeType("ByteArrayWrapper", "lupos.shared"),
    "ByteArrayWrapperFloat" to CodeType("ByteArrayWrapper", "lupos.shared"),
    "ByteArrayWrapperInteger" to CodeType("ByteArrayWrapper", "lupos.shared"),
    "ByteArrayWrapperDecimal" to CodeType("ByteArrayWrapper", "lupos.shared"),
)
