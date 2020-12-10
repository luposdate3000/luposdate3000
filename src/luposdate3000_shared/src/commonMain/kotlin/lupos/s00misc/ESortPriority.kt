package lupos.s00misc

enum class ESortPriority {
    SAME_AS_CHILD,
    ANY_PROVIDED_VARIABLE,
    PREVENT_ANY,
    JOIN,
    MINUS,
    UNION,
    BIND,
    SORT,
    GROUP
}
