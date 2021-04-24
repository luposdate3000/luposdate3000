package lupos.codegen

val registeredCodeSegments: List<CodeSegment> = mutableListOf(
    codeSegment("/") {
        val a = codeVar("a", codeTypes("Double"))
        val b = codeVar("b", codeTypes("Double"))
        val c = codeVar("c", codeTypes("Double"))
        val d = codeVal("d", { expVal(codeTypes("Double"), "0.0") })
        parameter {
            add(a)
            add(b)
        }
        statementIf(
            {
                expEq({ expRef(b) }, { expRef(d) })
            },
            {
                statementEvent(this, "", codeTypes("Error"))
            },
            {
                statementVal(c) {
                    expDiv({ expRef(a) }, { expRef(b) })
                }
                statementEvent(this, c)
            }
        )
    },
)
