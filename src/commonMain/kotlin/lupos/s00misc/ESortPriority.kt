package lupos.s00misc

import lupos.s00misc.Coverage

enum class ESortPriority {
    SAME_AS_CHILD,
    ANY_PROVIDED_VARIABLE,
    PREVENT_ANY,
    JOIN,
    MINUS,
    UNION,
    BIND,
    SORT
}
