package lupos.codegen

val registeredTypes: Map<String, CodeType> = mutableMapOf(
    "Boolean" to CodeType("Boolean", null),
    "Int" to CodeType("Int", null),
    "Unit" to CodeType("Unit", null),
    "Double" to CodeType("Double", null),
    "ByteArrayWrapper" to CodeType("ByteArrayWrapper", "lupos.shared"),
    "ByteArrayWrapperDouble" to CodeType("ByteArrayWrapper", "lupos.shared"),
)
