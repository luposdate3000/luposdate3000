#!/usr/bin/env kotlin

val registeredTypes: Map<String, CodeType> = mutableMapOf(
    "Int" to CodeType("Int", null),
    "Unit" to CodeType("Unit", null),
    "Double" to CodeType("Double", null),
    "ByteArrayWrapper" to CodeType("ByteArrayWrapper", "lupos.shared"),
    "ByteArrayWrapperDouble" to CodeType("ByteArrayWrapper", "lupos.shared"),
)
val registeredCodeSegments: List<CodeSegment> = mutableListOf(
    codeSegment("/") {
        val a = codeVar("a", codeTypes("ByteArrayWrapperDouble"))
        val b = codeVar("b", codeTypes("ByteArrayWrapperDouble"))
        val c = codeVar("c", codeTypes("ByteArrayWrapperDouble"))
        parameter {
            add(a)
            add(b)
        }
        statementVar(c)
        statementAssign(c) { expDiv({ expVar(a) }, { expVar(b) }) }
        statementEvent(c)
    },
)

abstract class ACodeBase() {
    abstract fun prepareImports(parentFile: CodeFile)
}

abstract class ACodeExpression(var resultType: CodeType) : ACodeBase() {
    abstract fun generate(): String
}

class CodeReturnEvent(val name: CodeName, val type: CodeType) : ACodeStatement() {
    override fun prepareImports(parentFile: CodeFile) {
        throw Exception("dont call this")
    }

    override fun generate(indention: String, out: StringBuilder) {
        throw Exception("dont call this")
    }
}

class CodeSegment(val name: String) : CodeStatementGroup() {
    val parameterContainer = CodeParameterContainer()
    fun statementEvent(name: String, type: CodeType): CodeReturnEvent {
        val r = CodeReturnEvent(CodeName(name), type)
        statements.add(r)
        return r
    }

    fun statementEvent(v: CodeVariableDefinition): CodeReturnEvent {
        val r = CodeReturnEvent(v.name, v.getType())
        statements.add(r)
        return r
    }

    fun parameter(init: CodeParameterContainer.() -> Unit) {
        parameterContainer.init()
    }

    fun generate(target: CodeStatementGroup, onEvent: (CodeReturnEvent) -> Unit) {
        for (statement in statements) {
            if (statement is CodeReturnEvent) {
                onEvent(statement)
            } else {
                target.statements.add(statement)
            }
        }
    }

    override fun prepareImports(parentFile: CodeFile) {
        throw Exception("dont call this")
    }
}

class CodeExpressionBuilder() {
    fun expVal(type: CodeType, value: String): ACodeExpression {
        return CodeVal(type, value)
    }

    fun expVar(v: CodeVariableDefinition): ACodeExpression {
        return CodeVarRef(v.name.name, v.getType())
    }

    fun expVar(name: String, type: CodeType): ACodeExpression {
        return CodeVarRef(name, type)
    }

    fun expDiv(a: CodeExpressionBuilder.() -> ACodeExpression, b: CodeExpressionBuilder.() -> ACodeExpression): ACodeExpression {
        val ax = CodeExpressionBuilder().a()
        val bx = CodeExpressionBuilder().b()
        return CodePrimitive(ax.resultType, "/", ax, bx)
    }
}

class CodePrimitive(resultType: CodeType, var symbol: String, var a: ACodeExpression, var b: ACodeExpression) : ACodeExpression(resultType) {
    override fun generate(): String {
        return "${a.generate()} $symbol ${b.generate()}"
    }

    override fun prepareImports(parentFile: CodeFile) {
        a.prepareImports(parentFile)
        b.prepareImports(parentFile)
        resultType.addImport(parentFile)
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

class CodeVarRef(var name: String, var type: CodeType) : ACodeExpression(type) {
    override fun generate(): String {
        return name
    }

    override fun prepareImports(parentFile: CodeFile) {
        resultType.addImport(parentFile)
    }
}

class CodeVal(type: CodeType, var value: String) : ACodeExpression(type) {
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
    override fun equals(other: Any?): Boolean {
        return other is CodeParamDefinition && other.getType() == getType()
    }

    var expression: ACodeExpression? = null
    var type_: CodeType? = null
    fun getType(): CodeType {
        if (expression != null) {
            return expression!!.resultType
        } else {
            return type_!!
        }
    }

    override fun prepareImports(parentFile: CodeFile) {
        type_?.addImport(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        throw Exception("dont call this")
    }

    fun generate(): String {
        if (expression != null) {
            return "${name.generate()} : ${expression!!.resultType.generate()} = ${expression!!.generate()}"
        } else {
            return "${name.generate()} : ${type_!!.generate()}"
        }
    }
}

class CodeVariableDefinition(var name: CodeName) : ACodeStatement(), ICodeVariableOrConstantDefinition {
    var expression: ACodeExpression? = null
    var type_: CodeType? = null
    fun getType(): CodeType {
        if (expression != null) {
            return expression!!.resultType
        } else {
            return type_!!
        }
    }

    override fun prepareImports(parentFile: CodeFile) {
        getType().addImport(parentFile)
    }

    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("$indention${generate()}")
    }

    fun generate(): String {
        if (expression != null) {
            return "var ${name.generate()} : ${expression!!.resultType.generate()} = ${expression!!.generate()}"
        } else {
            return "var ${name.generate()} : ${type_!!.generate()}"
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

    fun add(v: CodeVariableDefinition): CodeParamDefinition {
        val param = CodeParamDefinition(v.name)
        param.type_ = v.getType()
        parameters.add(param)
        return param
    }

    fun add(name: String, type: CodeType): CodeParamDefinition {
        val param = CodeParamDefinition(CodeName(name))
        param.type_ = type
        parameters.add(param)
        return param
    }

    fun add(name: String, type: CodeType, value: String): CodeParamDefinition {
        val param = CodeParamDefinition(CodeName(name))
        param.expression = CodeVal(type, value)
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

    fun statementAssign(v: CodeVariableDefinition, event: CodeReturnEvent): CodeAssignment {
        if (event.type != v.getType()) {
            throw Exception("incompatible types")
        }
        val ass = CodeAssignment(v.name, CodeVarRef(event.name.name, event.type))
        statements.add(ass)
        return ass
    }

    fun statementAssign(v: CodeVariableDefinition, init: CodeExpressionBuilder.() -> ACodeExpression): CodeAssignment {
        val expression = CodeExpressionBuilder().init()
        if (expression.resultType != v.getType()) {
            throw Exception("incompatible types")
        }
        val ass = CodeAssignment(v.name, expression)
        statements.add(ass)
        return ass
    }

    fun statementVar(v: CodeVariableDefinition): CodeVariableDefinition {
        statements.add(v)
        return v
    }

    fun statementVar(name: String, type: CodeType): CodeVariableDefinition {
        val v = CodeVariableDefinition(CodeName(name))
        v.type_ = type
        statements.add(v)
        return v
    }

    fun statementVal(v: CodeVariableDefinition, value: String): CodeConstantDefinition {
        val v2 = CodeConstantDefinition(v.name, CodeVal(v.getType(), value))
        statements.add(v2)
        return v2
    }

    fun statementVal(name: String, type: CodeType, value: String): CodeConstantDefinition {
        val v = CodeConstantDefinition(CodeName(name), CodeVal(type, value))
        statements.add(v)
        return v
    }

    open fun generate(indention: String, out: StringBuilder) {
        for (statement in statements) {
            statement.generate(indention + "  ", out)
        }
    }
}

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

    fun statementUse(name: String, init: CodeParameterContainer.() -> Unit, onEvent: (CodeReturnEvent) -> Unit) {
        val params = CodeParameterContainer()
        params.init()
        var found = false
        loop@ for (segment in registeredCodeSegments) {
            if (segment.name == name) {
                var flag = segment.parameterContainer.parameters.size == params.parameters.size
                var i = 0
                while (flag && i < segment.parameterContainer.parameters.size) {
                    flag = segment.parameterContainer.parameters[i] == params.parameters[i]
                    i++
                }
                if (flag) {
                    found = true
                    segment.generate(this) { it ->
                        onEvent(it)
                    }
                    break@loop
                }
            }
        }
        if (!found) {
            throw Exception("function not found")
        }
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

fun codeVar(name: String, type: CodeType): CodeVariableDefinition {
    val v = CodeVariableDefinition(CodeName(name))
    v.type_ = type
    return v
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
            statementAssign("y") { expVal(codeTypes("Int"), "5") }
            statementReturn()
        }
        function("y") {
            val a = codeVar("a", codeTypes("ByteArrayWrapperDouble"))
            val b = codeVar("b", codeTypes("ByteArrayWrapperDouble"))
            val z = codeVar("z", codeTypes("ByteArrayWrapperDouble"))
            statementVal(a, "0.0")
            statementVal(b, "0.0")
            statementUse(
                "/",
                {
                    add(a)
                    add(b)
                },
                { event ->
                    statementAssign(z, event)
                }
            )
            statementReturn { expVal(codeTypes("Double"), "8") }
        }
    }
}
