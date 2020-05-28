package lupos.s04logicalOperators.iterator
import kotlinx.coroutines.runBlocking
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueComparatorFast
open class RowIteratorMinus(val a: RowIterator, val b: RowIterator, projection: Array<String>) : RowIterator() {
    var flag = 2
    var aIdx = -1
    var bIdx = -1
    init {
Coverage.funStart(4263)
        var compCount = 0
Coverage.statementStart(4264)
        var columnsA = mutableListOf<String>()
Coverage.statementStart(4265)
        var columnsB = mutableListOf<String>()
Coverage.statementStart(4266)
        for (i in 0 until a.columns.size) {
Coverage.forLoopStart(4267)
            for (j in 0 until b.columns.size) {
Coverage.forLoopStart(4268)
                if (a.columns[i] == b.columns[j]) {
Coverage.ifStart(4269)
                    columnsA.add(a.columns[i])
Coverage.statementStart(4270)
                    columnsB.add(a.columns[i])
Coverage.statementStart(4271)
                    compCount++
Coverage.statementStart(4272)
                }
Coverage.statementStart(4273)
            }
Coverage.statementStart(4274)
        }
Coverage.statementStart(4275)
        for (i in 0 until a.columns.size) {
Coverage.forLoopStart(4276)
            if (!columnsA.contains(a.columns[i])) {
Coverage.ifStart(4277)
                columnsA.add(a.columns[i])
Coverage.statementStart(4278)
            }
Coverage.statementStart(4279)
        }
Coverage.statementStart(4280)
        for (j in 0 until b.columns.size) {
Coverage.forLoopStart(4281)
            if (!columnsB.contains(b.columns[j])) {
Coverage.ifStart(4282)
                columnsB.add(b.columns[j])
Coverage.statementStart(4283)
            }
Coverage.statementStart(4284)
        }
Coverage.statementStart(4285)
        columns = projection
Coverage.statementStart(4286)
        val mapping = IntArray(projection.size)
Coverage.statementStart(4287)
        for (i in 0 until projection.size) {
Coverage.forLoopStart(4288)
            for (j in 0 until a.columns.size) {
Coverage.forLoopStart(4289)
                if (projection[i] == a.columns[j]) {
Coverage.ifStart(4290)
                    mapping[i] = j
Coverage.statementStart(4291)
                }
Coverage.statementStart(4292)
            }
Coverage.statementStart(4293)
        }
Coverage.statementStart(4294)
        buf = IntArray(mapping.size)
Coverage.statementStart(4295)
        runBlocking {
Coverage.statementStart(4296)
            bIdx = b.next()
Coverage.statementStart(4297)
            if (bIdx < 0) {
Coverage.ifStart(4298)
                flag = 1
Coverage.statementStart(4299)
            }
Coverage.statementStart(4300)
            next = {
Coverage.statementStart(4301)
                var res = -1
Coverage.statementStart(4302)
                loop@ while (true) {
Coverage.whileLoopStart(4303)
                    when (flag) {
                        0 -> {
Coverage.whenCaseStart(4305)
                            break@loop
                        }
                        1 -> {//nothing to remove left
Coverage.whenCaseStart(4306)
                            aIdx = a.next()
Coverage.statementStart(4307)
                            if (aIdx < 0) {
Coverage.ifStart(4308)
                                flag = 0
Coverage.statementStart(4309)
                            } else {
Coverage.ifStart(4310)
                                res = 0
Coverage.statementStart(4311)
                                for (i in 0 until mapping.size) {
Coverage.forLoopStart(4312)
                                    buf[i] = a.buf[mapping[i] + aIdx]
Coverage.statementStart(4313)
                                }
Coverage.statementStart(4314)
                            }
Coverage.statementStart(4315)
                            break@loop
                        }
                        2 -> {
Coverage.whenCaseStart(4316)
                            aIdx = a.next()
Coverage.statementStart(4317)
                            if (aIdx >= 0) {
Coverage.ifStart(4318)
                                for (i in 0 until compCount) {
Coverage.forLoopStart(4319)
                                    if (buf[i] < b.buf[i]) {
Coverage.ifStart(4320)
                                        res = 0
Coverage.statementStart(4321)
                                        for (k in 0 until mapping.size) {
Coverage.forLoopStart(4322)
                                            buf[k] = a.buf[mapping[k] + aIdx]
Coverage.statementStart(4323)
                                        }
Coverage.statementStart(4324)
                                        break@loop
                                    } else if (buf[i] > b.buf[i]) {
Coverage.statementStart(4325)
                                        bIdx = b.next()
Coverage.statementStart(4326)
                                        if (bIdx < 0) {
Coverage.ifStart(4327)
                                            flag = 1
Coverage.statementStart(4328)
                                        }
Coverage.statementStart(4329)
                                        continue@loop
                                    }
Coverage.statementStart(4330)
                                }
Coverage.statementStart(4331)
                            } else {
Coverage.ifStart(4332)
                                flag = 0
Coverage.statementStart(4333)
                                break@loop
                            }
Coverage.statementStart(4334)
                        }
                    }
Coverage.statementStart(4335)
                }
Coverage.statementStart(4336)
                /*return*/ res
            }
Coverage.statementStart(4337)
        }
Coverage.statementStart(4338)
    }
}
