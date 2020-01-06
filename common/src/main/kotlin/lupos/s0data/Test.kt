package lupos.s0data

fun main() {
    val res = ResultRow()
    res.setVariable(VariableName("a"), "b")
    res.setVariable(VariableName("c"), "d")
    println(res.getVariable(VariableName("a")))
    require(res.getVariable(VariableName("a")).equals("b"))
    println(res.getVariable(VariableName("c")))
    require(res.getVariable(VariableName("c")).equals("d"))
}