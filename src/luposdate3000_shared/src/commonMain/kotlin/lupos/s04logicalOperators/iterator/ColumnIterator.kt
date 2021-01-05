package lupos.s04logicalOperators.iterator
public abstract class ColumnIterator {
    public abstract /*suspend*/ fun next(): Int
    public abstract /*suspend*/ fun close()
   public  open /*suspend*/ fun nextSIP(minValue: Int, result: IntArray) {
        result[0] = 0
        result[1] = next()
    }
public     open /*suspend*/ fun skipSIP(skipCount: Int): Int {
        for (i in 0 until skipCount) {
            next()
        }
        return next()
    }
}
