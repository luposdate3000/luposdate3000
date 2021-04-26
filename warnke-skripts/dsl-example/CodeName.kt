package lupos.codegen

@CodeDSLMarker
class CodeName(var name: String) {
    fun generate(): String {
        return name
    }
}
