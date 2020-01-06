package lupos.s0data

actual class VariableName {
    var name: String

    actual constructor(name: String) {
        this.name = name
    }
}

actual class ResultRow {
    private var values: MutableMap<String, String> = mutableMapOf<String, String>()
    actual fun setVariable(name: VariableName, value: String) {
        this.values[name.name] = value
    }

    actual fun getVariable(name: VariableName): String? {
        return values[name.name]
    }
}