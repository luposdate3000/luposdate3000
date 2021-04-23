#!/usr/bin/env kotlin

val registeredTypes: Map<String, CodeType> = mutableMapOf(
    "Int" to CodeType("Int", null),
    "Unit" to CodeType("Unit", null),
    "Double" to CodeType("Double", null),
    "ByteArrayWrapper" to CodeType("ByteArrayWrapper", "lupos.shared"),
)
val registeredCodeStatementGroups: List<CodeSegment> = mutableListOf()

abstract class ACodeBase() {
    abstract fun prepareImports(parentFile: CodeFile)
}

abstract class ACodeExpression(var resultType: CodeType) : ACodeBase() {
    abstract fun generate(): String
}

class CodeExpressionBuilder() {
    fun expValue(type: CodeType, value: String): ACodeExpression {
        return CodeValue(type, value)
    }
}

abstract class ACodeStatement() : ACodeBase() {
    abstract fun generate(indention: String, out: StringBuilder)
}

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

class CodeName(var name: String) {
    fun generate(): String {
        return name
    }
}

class CodeValue(type: CodeType, var value: String) : ACodeExpression(type) {
    override fun prepareImports(parentFile: CodeFile) {
        resultType.addImport(parentFile)
    }

    override fun generate(): String {
        return value
    }
}

interface ICodeVariableOrConstantDefinition

class CodeConstantDefinition(var name: CodeName, var expression: ACodeExpression) : ACodeStatement(), ICodeVariableOrConstantDefinition {
    override fun prepareImports(parentFile: CodeFile) {
        expression.resultType.addImport(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("$indention${generate()}")
    }

    fun generate(): String {
        return "val ${name.generate()} : ${expression.resultType.generate()} = ${expression.generate()}"
    }
}

class CodeParamDefinition(var name: CodeName) : ACodeStatement() {
    var expression: ACodeExpression? = null
    var type: CodeType? = null
    override fun prepareImports(parentFile: CodeFile) {
        type?.addImport(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        throw Exception("error")
    }

    fun generate(): String {
        if (expression != null) {
            return "${name.generate()} : ${expression!!.resultType.generate()} = ${expression!!.generate()}"
        } else {
            return "${name.generate()} : ${type!!.generate()}"
        }
    }
}

class CodeVariableDefinition(var name: CodeName) : ACodeStatement(), ICodeVariableOrConstantDefinition {
    var expression: ACodeExpression? = null
    var type: CodeType? = null
    override fun prepareImports(parentFile: CodeFile) {
        type?.addImport(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("$indention${generate()}")
    }

    fun generate(): String {
        if (expression != null) {
            return "var ${name.generate()} : ${expression!!.resultType.generate()} = ${expression!!.generate()}"
        } else {
            return "var ${name.generate()} : ${type!!.generate()}"
        }
    }
}

class CodeAssignment(var target: CodeName, var expression: ACodeExpression) : ACodeStatement() {
    override fun prepareImports(parentFile: CodeFile) {
        expression.prepareImports(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("$indention${target.generate()} = ${expression.generate()}")
    }
}

class CodeReturnValue(val expression: ACodeExpression? = null) : ACodeStatement() {
    override fun prepareImports(parentFile: CodeFile) {
        expression?.prepareImports(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        if (expression != null) {
            out.appendLine("${indention}return ${expression.generate()}")
        } else {
            out.appendLine("${indention}return")
        }
    }
}

class CodeParameterContainer {
    val parameters = mutableListOf<CodeParamDefinition>()
    fun generate(): String {
        return parameters.map { it.generate() }.joinToString()
    }

    fun add(name: String, type: CodeType): CodeParamDefinition {
        val param = CodeParamDefinition(CodeName(name))
        param.type = type
        parameters.add(param)
        return param
    }

    fun add(name: String, type: CodeType, value: String): CodeParamDefinition {
        val param = CodeParamDefinition(CodeName(name))
        param.expression = CodeValue(type, value)
        parameters.add(param)
        return param
    }

    fun prepareImports(parentFile: CodeFile) {
        for (parameter in parameters) {
            parameter.prepareImports(parentFile)
        }
    }
}

abstract class CodeStatementGroup() : ACodeBase() {
    val statements = mutableListOf<ACodeStatement>()
    override fun prepareImports(parentFile: CodeFile) {
        for (statement in statements) {
            statement.prepareImports(parentFile)
        }
    }

    fun statementAssign(name: String, init: CodeExpressionBuilder.() -> ACodeExpression): CodeAssignment {
        val expression = CodeExpressionBuilder().init()
        val ass = CodeAssignment(CodeName(name), expression)
        statements.add(ass)
        return ass
    }

    fun statementVar(name: String, type: CodeType): CodeVariableDefinition {
        val v = CodeVariableDefinition(CodeName(name))
        v.type = type
        statements.add(v)
        return v
    }

    fun statementVar(name: String, type: CodeType, value: String): CodeVariableDefinition {
        val v = CodeVariableDefinition(CodeName(name))
        v.expression = CodeValue(type, value)
        statements.add(v)
        return v
    }

    fun statementVal(name: String, type: CodeType, value: String): CodeConstantDefinition {
        val v = CodeConstantDefinition(CodeName(name), CodeValue(type, value))
        statements.add(v)
        return v
    }

    open fun generate(indention: String, out: StringBuilder) {
        for (statement in statements) {
            statement.generate(indention + "  ", out)
        }
    }
}

class CodeSegment(val name: String) : CodeStatementGroup()
class CodeFunction(var name: CodeName) : CodeStatementGroup() {
    var returnType: CodeType? = null
    val parameterContainer = CodeParameterContainer()
    fun parameter(init: CodeParameterContainer.() -> Unit) {
        parameterContainer.init()
    }

    override fun prepareImports(parentFile: CodeFile) {
        super.prepareImports(parentFile)
        returnType?.addImport(parentFile)
        parameterContainer.prepareImports(parentFile)
    }

    fun statementReturn(): CodeReturnValue {
        val r = CodeReturnValue()
        statements.add(r)
        return r
    }

    fun statementReturn(init: CodeExpressionBuilder.() -> ACodeExpression): CodeReturnValue {
        val res = CodeExpressionBuilder().init()
        val r = CodeReturnValue(res)
        returnType = res.resultType
        statements.add(r)
        return r
    }

    override fun generate(indention: String, out: StringBuilder) {
        if (returnType == null) {
            returnType = codeTypes("Unit")
        }
        out.appendLine("${indention}fun ${name.generate()}(${parameterContainer.generate()}) : ${returnType!!.generate()} {")
        super.generate(indention, out)
        out.appendLine("$indention}")
    }
}

class CodeClazz(var name: CodeName) : ACodeBase() {
    val functions = mutableListOf<CodeFunction>()
    override fun prepareImports(parentFile: CodeFile) {
        for (function in functions) {
            function.prepareImports(parentFile)
        }
    }

    fun function(name: String, init: CodeFunction.() -> Unit): CodeFunction {
        val func = CodeFunction(CodeName(name))
        func.init()
        functions.add(func)
        return func
    }

    fun generate(indention: String, out: StringBuilder) {
        out.appendLine("${indention}class ${name.generate()} {")
        for (function in functions) {
            function.generate(indention + "  ", out)
            out.appendLine()
        }
        out.appendLine("$indention}")
    }
}

class CodeFile(var name: String, var pkg: String) : ACodeBase() {
    val imports = mutableSetOf<String>()
    val clazzes = mutableListOf<CodeClazz>()
    override fun prepareImports(parentFile: CodeFile) {
        for (clazz in clazzes) {
            clazz.prepareImports(parentFile)
        }
    }

    fun clazz(name: String, init: CodeClazz.() -> Unit): CodeClazz {
        val c = CodeClazz(CodeName(name))
        c.init()
        clazzes.add(c)
        return c
    }

    fun generate(indention: String, out: StringBuilder) {
        for (clazz in clazzes) {
            clazz.generate(indention + "", out)
            out.appendLine()
        }
    }

    override fun toString(): String {
        val out = StringBuilder()
        prepareImports(this)
        out.appendLine("package $pkg")
        out.appendLine()
        for (i in imports) {
            out.appendLine("import $i")
        }
        out.appendLine()
        generate("", out)
        return out.toString()
    }
}

fun codeTypes(type: String): CodeType {
    return registeredTypes[type]!!
}

fun codeFile(name: String, pkg: String, init: CodeFile.() -> Unit): CodeFile {
    val file = CodeFile(name, pkg)
    file.init()
    return file
}

fun codeSegment(name: String, init: CodeSegment.() -> Unit): CodeSegment {
    val seg = CodeSegment(name)
    seg.init()
    return seg
}

// ///////////////////

codeFile("myfilename", "generatedPackage") {
    clazz("myclazzname") {
        function("x") {
            parameter {
                add("a", codeTypes("Double"), "0.0")
                add("b", codeTypes("Double"))
            }
            statementVar("y", codeTypes("Int"))
            statementVar("z", codeTypes("ByteArrayWrapper"))
            statementVal("x", codeTypes("Int"), "4")
            statementAssign("y") { expValue(codeTypes("Int"), "5") }
            statementReturn()
        }
        function("y") {
            statementReturn { expValue(codeTypes("Double"), "8") }
        }
    }
}
