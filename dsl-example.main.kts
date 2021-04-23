#!/usr/bin/env kotlin

interface ICodeBase
abstract class ACodeExpression(var resultType: CodeType) : ICodeBase {
    abstract fun generate(): String
}

class CodeExpressionBuilder {
    fun expValue(type: CodeType, value: String): ACodeExpression {
        return CodeValue(type, value)
    }
}

interface ICodeStatement : ICodeBase {
    fun generate(indention: String, out: StringBuilder)
}

class CodeType {
    val type: String

    constructor(type: String) {
        this.type = type
    }

    fun generate(): String {
        return type
    }

    companion object {
        val DOUBLE = CodeType("Double")
        val INT = CodeType("Int")
        val UNIT = CodeType("Unit")
    }
}

class CodeName(var name: String) : ICodeBase {
    fun generate(): String {
        return name
    }
}

class CodeValue(type: CodeType, var value: String) : ICodeBase, ACodeExpression(type) {
    override fun generate(): String {
        return value
    }
}

class CodeVariableDefinition(var name: CodeName) : ICodeStatement, ICodeBase {
    var value: ACodeExpression? = null
    var type: CodeType? = null
    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("$indention${generate()}")
    }

    fun generate(): String {
        if (value != null) {
            return "var ${name.generate()} : ${value!!.resultType.generate()} = ${value!!.generate()}"
        } else {
            return "var ${name.generate()} : ${type!!.generate()}"
        }
    }
}

class CodeAssignment(var target: CodeName, var expression: ACodeExpression) : ICodeStatement, ICodeBase {
    override fun generate(indention: String, out: StringBuilder) {
        out.appendLine("$indention${target.generate()} = ${expression.generate()}")
    }
}

class CodeReturnValue(val expression: ACodeExpression? = null) : ICodeStatement, ICodeBase {
    override fun generate(indention: String, out: StringBuilder) {
        if (expression != null) {
            out.appendLine("${indention}return ${expression.generate()}")
        } else {
            out.appendLine("${indention}return")
        }
    }
}

class CodeFunction(var name: CodeName) : ICodeBase {
    val parameters = mutableListOf<CodeVariableDefinition>()
    val statements = mutableListOf<ICodeStatement>()
    var returnType: CodeType? = null
    fun addParameter(name: String, type: CodeType): CodeVariableDefinition {
        val param = CodeVariableDefinition(CodeName(name))
        param.type = type
        parameters.add(param)
        return param
    }

    fun addParameter(name: String, type: CodeType, value: String): CodeVariableDefinition {
        val param = CodeVariableDefinition(CodeName(name))
        param.value = CodeValue(type, value)
        parameters.add(param)
        return param
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
        v.value = CodeValue(type, value)
        statements.add(v)
        return v
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

    fun generate(indention: String, out: StringBuilder) {
        if (returnType == null) {
            returnType = CodeType.UNIT
        }
        out.appendLine("${indention}fun ${name.generate()}(parameters.map{it.generate()}.joinToString()) : ${returnType!!.generate()}{")
        for (statement in statements) {
            statement.generate(indention + "  ", out)
        }
        out.appendLine("$indention}")
    }
}

class CodeClazz(var name: CodeName) : ICodeBase {
    val functions = mutableListOf<CodeFunction>()
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
        }
        out.appendLine("$indention}")
    }
}

class CodeFile(var name: String) : ICodeBase {
    val clazzes = mutableListOf<CodeClazz>()
    fun clazz(name: String, init: CodeClazz.() -> Unit): CodeClazz {
        val c = CodeClazz(CodeName(name))
        c.init()
        clazzes.add(c)
        return c
    }

    fun generate(indention: String, out: StringBuilder) {
        for (clazz in clazzes) {
            clazz.generate(indention + "", out)
        }
    }

    override fun toString(): String {
        val out = StringBuilder()
        generate("", out)
        return out.toString()
    }
}

fun codeFile(name: String, init: CodeFile.() -> Unit): CodeFile {
    val file = CodeFile(name)
    file.init()
    return file
}

// ///////////////////

codeFile("myfilename") {
    clazz("myclazzname") {
        function("x") {
            addParameter("a", CodeType.DOUBLE, "0.0")
            addParameter("b", CodeType.DOUBLE)
            statementVar("y", CodeType.INT)
            statementVar("x", CodeType.INT, "4")
            statementAssign("y") { expValue(CodeType.INT, "5") }
            statementReturn()
        }
        function("y") {
            statementReturn { expValue(CodeType.DOUBLE, "8") }
        }
    }
}
