package lupos.codegen

@CodeDSLMarker
class CodeType(val type: String?, val pkg: String?) {
    fun generate(): String {
        if (type != null) {
            return type
        } else {
            return ""
        }
    }

    fun addImport(file: CodeFile) {
        if (pkg != null && type != null) {
            file.imports.add(pkg + "." + type)
        }
    }
}
