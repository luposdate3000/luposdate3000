package lupos.codegen

class CodeType(val type: String, val pkg: String?) {
    fun generate(): String {
        return type
    }

    fun addImport(file: CodeFile) {
        if (pkg != null) {
            file.imports.add(pkg + "." + type)
        }
    }
}
