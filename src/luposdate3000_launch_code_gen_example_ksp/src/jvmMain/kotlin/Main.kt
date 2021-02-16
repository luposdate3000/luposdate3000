package lupos

public fun main(args: Array<String>) {
    println(XYZ().x())
    println(abc())
}

public expect class XYZ {
    @MyAnnotation("query")
    public fun x(): Int
}

@MyAnnotation("select * where {}")
public expect fun abc(): String
