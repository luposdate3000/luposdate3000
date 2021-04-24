package lupos.codegen

val registeredCodeSegments: List<CodeSegment> = mutableListOf(
    codeSegment("/") {
        val a = codeVar("a", codeTypes("Double"))
        val b = codeVar("b", codeTypes("Double"))
        val c = codeVar("c", codeTypes("Double"))
        parameter {
            add(a)
            add(b)
        }
        statementIf(
            {
                expEq({ expRef(b) }, { expVal(codeTypes("Double"), "0.0") })
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
