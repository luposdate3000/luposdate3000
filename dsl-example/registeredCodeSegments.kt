package lupos.codegen

val registeredCodeSegments: List<CodeSegment> = mutableListOf(
    codeSegment("/") {
        val a = codeVar("a", codeTypes("ByteArrayWrapperDouble"))
        val b = codeVar("b", codeTypes("ByteArrayWrapperDouble"))
        val c = codeVar("c", codeTypes("ByteArrayWrapperDouble"))
        parameter {
            add(a)
            add(b)
        }
        statementVal(c) { expDiv({ expVar(a) }, { expVar(b) }) }
        statementEvent(c)
    },
)
