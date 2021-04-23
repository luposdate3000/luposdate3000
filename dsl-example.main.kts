#!/usr/bin/env kotlin

val registeredTypes: Map<String, CodeType> = arrayOf(
    CodeType("Int", null),
    CodeType("Unit", null),
    CodeType("Double", null),
    CodeType("ByteArrayWrapper", "lupos.shared"),
).map { it.type to it }.toMap()

abstract class ACodeBase(val parent: ACodeBase?) {
    abstract fun prepareImports()
    fun getParentFile(): CodeFile {
        var p = parent
        if (p == null) {
            return this as CodeFile
        }
        while (p!!.parent != null) {
            p = p.parent
        }
        return p as CodeFile
    }
}

abstract class ACodeExpression(parent: ACodeBase, var resultType: CodeType) : ACodeBase(parent) {
    abstract fun generate(): String
}

class CodeExpressionBuilder(val parent: ACodeBase) {
    fun expValue(type: CodeType, value: String): ACodeExpression {
        return CodeValue(parent, type, value)
    }
}

abstract class ACodeStatement(parent: ACodeBase) : ACodeBase(parent) {
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

class CodeValue(parent: ACodeBase, type: CodeType, var value: String) : ACodeExpression(parent, type) {
    override fun prepareImports() {
        resultType.addImport(getParentFile())
    }

    override fun generate(): String {
        return value
    }
}

interface ICodeVariableOrConstantDefinition

class CodeConstantDefinition(parent: ACodeBase, var name: CodeName, var expression: ACodeExpression) : ACodeStatement(parent), ICodeVariableOrConstantDefinition {
    override fun prepareImports() {
        expression.resultType.addImport(getParentFile())
    }

    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("$indention${generate()}")
    }

    fun generate(): String {
        return "val ${name.generate()} : ${expression.resultType.generate()} = ${expression.generate()}"
    }
}

class CodeParamDefinition(parent: ACodeBase, var name: CodeName) : ACodeStatement(parent) {
    var expression: ACodeExpression? = null
    var type: CodeType? = null
    override fun prepareImports() {
        type?.addImport(getParentFile())
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

class CodeVariableDefinition(parent: ACodeBase, var name: CodeName) : ACodeStatement(parent), ICodeVariableOrConstantDefinition {
    var expression: ACodeExpression? = null
    var type: CodeType? = null
    override fun prepareImports() {
        type?.addImport(getParentFile())
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

class CodeAssignment(parent: ACodeBase, var target: CodeName, var expression: ACodeExpression) : ACodeStatement(parent) {
    override fun prepareImports() {
        expression.prepareImports()
    }

    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("$indention${target.generate()} = ${expression.generate()}")
    }
}

class CodeReturnValue(parent: ACodeBase, val expression: ACodeExpression? = null) : ACodeStatement(parent) {
    override fun prepareImports() {
        expression?.prepareImports()
    }

    override fun generate(indention: String, out: StringBuilder) {
        if (expression != null) {
            out.appendLine("${indention}return ${expression.generate()}")
        } else {
            out.appendLine("${indention}return")
        }
    }
}

class CodeFunction(parent: ACodeBase, var name: CodeName) : ACodeBase(parent) {
    val parameters = mutableListOf<CodeParamDefinition>()
    val statements = mutableListOf<ACodeStatement>()
    var returnType: CodeType? = null
    override fun prepareImports() {
        for (statement in statements) {
            statement.prepareImports()
        }
        for (parameter in parameters) {
            parameter.prepareImports()
        }
        returnType?.addImport(getParentFile())
    }

    fun addParameter(name: String, type: CodeType): CodeParamDefinition {
        val param = CodeParamDefinition(this, CodeName(name))
        param.type = type
        parameters.add(param)
        return param
    }

    fun addParameter(name: String, type: CodeType, value: String): CodeParamDefinition {
        val param = CodeParamDefinition(this, CodeName(name))
        param.expression = CodeValue(this, type, value)
        parameters.add(param)
        return param
    }

    fun statementAssign(name: String, init: CodeExpressionBuilder.() -> ACodeExpression): CodeAssignment {
        val expression = CodeExpressionBuilder(this).init()
        val ass = CodeAssignment(this, CodeName(name), expression)
        statements.add(ass)
        return ass
    }

    fun statementVar(name: String, type: CodeType): CodeVariableDefinition {
        val v = CodeVariableDefinition(this, CodeName(name))
        v.type = type
        statements.add(v)
        return v
    }

    fun statementVar(name: String, type: CodeType, value: String): CodeVariableDefinition {
        val v = CodeVariableDefinition(this, CodeName(name))
        v.expression = CodeValue(this, type, value)
        statements.add(v)
        return v
    }

    fun statementVal(name: String, type: CodeType, value: String): CodeConstantDefinition {
        val v = CodeConstantDefinition(this, CodeName(name), CodeValue(this, type, value))
        statements.add(v)
        return v
    }

    fun statementReturn(): CodeReturnValue {
        val r = CodeReturnValue(this, )
        statements.add(r)
        return r
    }

    fun statementReturn(init: CodeExpressionBuilder.() -> ACodeExpression): CodeReturnValue {
        val res = CodeExpressionBuilder(this).init()
        val r = CodeReturnValue(this, res)
        returnType = res.resultType
        statements.add(r)
        return r
    }

    fun generate(indention: String, out: StringBuilder) {
        if (returnType == null) {
            returnType = codeTypes("Unit")
        }
        out.appendLine("${indention}fun ${name.generate()}(${parameters.map { it.generate() }.joinToString()}) : ${returnType!!.generate()} {")
        for (statement in statements) {
            statement.generate(indention + "  ", out)
        }
        out.appendLine("$indention}")
    }
}

class CodeClazz(parent: ACodeBase, var name: CodeName) : ACodeBase(parent) {
    val functions = mutableListOf<CodeFunction>()
    override fun prepareImports() {
        for (function in functions) {
            function.prepareImports()
        }
    }

    fun function(name: String, init: CodeFunction.() -> Unit): CodeFunction {
        val func = CodeFunction(this, CodeName(name))
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

class CodeFile(var name: String, var pkg: String) : ACodeBase(null) {
    val imports = mutableSetOf<String>()
    val clazzes = mutableListOf<CodeClazz>()
    override fun prepareImports() {
        for (clazz in clazzes) {
            clazz.prepareImports()
        }
    }

    fun clazz(name: String, init: CodeClazz.() -> Unit): CodeClazz {
        val c = CodeClazz(this, CodeName(name))
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
        prepareImports()
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

// ///////////////////

codeFile("myfilename", "generatedPackage") {
    clazz("myclazzname") {
        function("x") {
            addParameter("a", codeTypes("Double"), "0.0")
            addParameter("b", codeTypes("Double"))
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
