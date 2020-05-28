package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
class ColumnIteratorRepeatIterator(val count: Int, val child: ColumnIterator) : ColumnIterator() {
    var index = 0
    var index2 = 0
    //TODO use pages instead
    val data = MyListValue()
    init {
Coverage.funStart(3884)
        SanityCheck.check { count > 0 }
Coverage.statementStart(3885)
        next = {
Coverage.statementStart(3886)
            var res: Value?
Coverage.statementStart(3887)
            val tmp = child.next()
Coverage.statementStart(3888)
            if (tmp == null) {
Coverage.ifStart(3889)
                if (data.size == 0 || count == 1) {
Coverage.ifStart(3890)
                    next = {
Coverage.statementStart(3891)
                        /*return*/null
                    }
Coverage.statementStart(3892)
                } else {
Coverage.ifStart(3893)
                    index = 2
Coverage.statementStart(3894)
                    next = {
Coverage.statementStart(3895)
                        var res2: Value?
Coverage.statementStart(3896)
                        if (index2 < data.size) {
Coverage.ifStart(3897)
                            res2 = data[index2++]
Coverage.statementStart(3898)
                        } else {
Coverage.ifStart(3899)
                            if (index < count) {
Coverage.ifStart(3900)
                                index++
Coverage.statementStart(3901)
                                index2 = 0
Coverage.statementStart(3902)
                                res2 = data[index2++]
Coverage.statementStart(3903)
                            } else {
Coverage.ifStart(3904)
                                res2 = null
Coverage.statementStart(3905)
                            }
Coverage.statementStart(3906)
                        }
Coverage.statementStart(3907)
                        /*return*/res2
                    }
Coverage.statementStart(3908)
                }
Coverage.statementStart(3909)
                res = next()
Coverage.statementStart(3910)
            } else {
Coverage.ifStart(3911)
                data.add(tmp)
Coverage.statementStart(3912)
                res = tmp
Coverage.statementStart(3913)
            }
Coverage.statementStart(3914)
/*return*/res
        }
Coverage.statementStart(3915)
        close = {
Coverage.statementStart(3916)
            child.close()
Coverage.statementStart(3917)
            _close()
Coverage.statementStart(3918)
        }
Coverage.statementStart(3919)
    }
}
