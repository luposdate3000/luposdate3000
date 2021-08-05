package lupos.parser.turtle

public expect class MyFileReader public constructor(name: String) {
    public fun read(buf: CharArray): Int
    public fun read(buf: CharArray, offset: Int, len: Int): Int
}
