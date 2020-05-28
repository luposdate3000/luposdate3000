package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
open class RowIteratorMerge(val a: RowIterator, val b: RowIterator, val comparator: Comparator<Value>, val compCount: Int) : RowIterator() {
    companion object {
        suspend operator fun invoke(a: RowIterator, comparator: Comparator<Value>, compCount: Int, columns: Array<String>): RowIterator {
Coverage.funStart(4021)
            SanityCheck.check { columns.size == a.columns.size }
Coverage.statementStart(4022)
            var buf1 = IntArray(columns.size * MERGE_SORT_MIN_ROWS)
Coverage.statementStart(4023)
            var buf2 = IntArray(columns.size * MERGE_SORT_MIN_ROWS)
Coverage.statementStart(4024)
            var done = false
Coverage.statementStart(4025)
            var resultList = mutableListOf<RowIterator?>()
Coverage.statementStart(4026)
            var columnMapping = IntArray(columns.size)
Coverage.statementStart(4027)
            for (i in 0 until columns.size) {
Coverage.forLoopStart(4028)
                for (j in 0 until columns.size) {
Coverage.forLoopStart(4029)
                    if (a.columns[j] == columns[i]) {
Coverage.ifStart(4030)
                        columnMapping[i] = j
Coverage.statementStart(4031)
                    }
Coverage.statementStart(4032)
                }
Coverage.statementStart(4033)
            }
Coverage.statementStart(4034)
            while (!done) {
Coverage.whileLoopStart(4035)
                var i = 0
Coverage.statementStart(4036)
                while (i < buf1.size) {
Coverage.whileLoopStart(4037)
                    val next = a.next()
Coverage.statementStart(4038)
                    if (next < 0) {
Coverage.ifStart(4039)
                        done = true
Coverage.statementStart(4040)
                        break
                    }
Coverage.statementStart(4041)
                    for (j in 0 until columns.size) {
Coverage.forLoopStart(4042)
                        buf1[i++] = a.buf[next + columnMapping[j]]
Coverage.statementStart(4043)
                    }
Coverage.statementStart(4044)
                }
Coverage.statementStart(4045)
                var total = i / columns.size
Coverage.statementStart(4046)
                var off: Int
Coverage.statementStart(4047)
                var shift = 0
Coverage.statementStart(4048)
                var size = 1 shl shift
Coverage.statementStart(4049)
                var count: Int
Coverage.statementStart(4050)
                var mid: Int
Coverage.statementStart(4051)
                while (size / 2 < total) {
Coverage.whileLoopStart(4052)
                    off = 0
Coverage.statementStart(4053)
                    shift++
Coverage.statementStart(4054)
                    size = 1 shl shift
Coverage.statementStart(4055)
                    while (off < total) {
Coverage.whileLoopStart(4056)
                        if (off + size <= total) {
Coverage.ifStart(4057)
                            count = size
Coverage.statementStart(4058)
                        } else {
Coverage.ifStart(4059)
                            count = total - off
Coverage.statementStart(4060)
                        }
Coverage.statementStart(4061)
                        mid = size / 2
Coverage.statementStart(4062)
                        val aEnd = (off + mid) * columns.size
Coverage.statementStart(4063)
                        val bEnd = (off + count) * columns.size
Coverage.statementStart(4064)
                        var a_ = off * columns.size
Coverage.statementStart(4065)
                        var b = aEnd
Coverage.statementStart(4066)
                        var c = a_
Coverage.statementStart(4067)
                        if (count < mid) {
Coverage.ifStart(4068)
                            b = a_
Coverage.statementStart(4069)
                            a_ = aEnd
Coverage.statementStart(4070)
                        }
Coverage.statementStart(4071)
                        loop@ while (a_ < aEnd && b < bEnd) {
Coverage.whileLoopStart(4072)
                            for (l in 0 until columns.size) {
Coverage.forLoopStart(4073)
                                var cmp: Int
Coverage.statementStart(4074)
                                var j = 0
Coverage.statementStart(4075)
                                while (j < compCount) {
Coverage.whileLoopStart(4076)
                                    cmp = comparator.compare(buf1[a_ + l], buf1[b + l])
Coverage.statementStart(4077)
                                    if (cmp < 0) {
Coverage.ifStart(4078)
                                        for (k in 0 until columns.size) {
Coverage.forLoopStart(4079)
                                            buf2[c++] = buf1[a_++]
Coverage.statementStart(4080)
                                        }
Coverage.statementStart(4081)
                                        continue@loop
                                    } else if (cmp > 0) {
Coverage.statementStart(4082)
                                        for (k in 0 until columns.size) {
Coverage.forLoopStart(4083)
                                            buf2[c++] = buf1[b++]
Coverage.statementStart(4084)
                                        }
Coverage.statementStart(4085)
                                        continue@loop
                                    }
Coverage.statementStart(4086)
                                    j++
Coverage.statementStart(4087)
                                }
Coverage.statementStart(4088)
                                while (j < columns.size) {
Coverage.whileLoopStart(4089)
                                    cmp = buf1[a_ + l] - buf1[b + l]
Coverage.statementStart(4090)
                                    if (cmp < 0) {
Coverage.ifStart(4091)
                                        for (k in 0 until columns.size) {
Coverage.forLoopStart(4092)
                                            buf2[c++] = buf1[a_++]
Coverage.statementStart(4093)
                                        }
Coverage.statementStart(4094)
                                        continue@loop
                                    } else if (cmp > 0) {
Coverage.statementStart(4095)
                                        for (k in 0 until columns.size) {
Coverage.forLoopStart(4096)
                                            buf2[c++] = buf1[b++]
Coverage.statementStart(4097)
                                        }
Coverage.statementStart(4098)
                                        continue@loop
                                    }
Coverage.statementStart(4099)
                                    j++
Coverage.statementStart(4100)
                                }
Coverage.statementStart(4101)
                            }
Coverage.statementStart(4102)
                            for (j in 0 until columns.size) {
Coverage.forLoopStart(4103)
                                buf2[c++] = buf1[a_++]
Coverage.statementStart(4104)
                            }
Coverage.statementStart(4105)
                            for (j in 0 until columns.size) {
Coverage.forLoopStart(4106)
                                buf2[c++] = buf1[b++]
Coverage.statementStart(4107)
                            }
Coverage.statementStart(4108)
                        }
Coverage.statementStart(4109)
                        while (a_ < aEnd) {
Coverage.whileLoopStart(4110)
                            buf2[c++] = buf1[a_++]
Coverage.statementStart(4111)
                        }
Coverage.statementStart(4112)
                        while (b < bEnd) {
Coverage.whileLoopStart(4113)
                            buf2[c++] = buf1[b++]
Coverage.statementStart(4114)
                        }
Coverage.statementStart(4115)
                        off += size
Coverage.statementStart(4116)
                    }
Coverage.statementStart(4117)
                    var t = buf1
Coverage.statementStart(4118)
                    buf1 = buf2
Coverage.statementStart(4119)
                    buf2 = t
Coverage.statementStart(4120)
                }
Coverage.statementStart(4121)
                var it = RowIteratorBuf(buf1, columns, i)
Coverage.statementStart(4122)
                if (i > 0 || resultList.size == 0) {
Coverage.ifStart(4123)
                    if (resultList.size == 0) {
Coverage.ifStart(4124)
                        resultList.add(it)
Coverage.statementStart(4125)
                    } else if (resultList[0] == null) {
Coverage.ifStart(4126)
                        resultList[0] = it
Coverage.statementStart(4127)
                    } else {
Coverage.ifStart(4128)
                        resultList[0] = RowIteratorMerge(resultList[0]!!, it, comparator, compCount)
Coverage.statementStart(4129)
                        if (resultList[resultList.size - 1] != null) {
Coverage.ifStart(4130)
                            resultList.add(null)
Coverage.statementStart(4131)
                        }
Coverage.statementStart(4132)
                        var j = 1
Coverage.statementStart(4133)
                        while (j < resultList.size) {
Coverage.whileLoopStart(4134)
                            if (resultList[j] == null) {
Coverage.ifStart(4135)
                                resultList[j] = resultList[j - 1]
Coverage.statementStart(4136)
                                resultList[j - 1] = null
Coverage.statementStart(4137)
                                break
                            } else {
Coverage.statementStart(4138)
                                resultList[j] = RowIteratorMerge(resultList[j]!!, resultList[j - 1]!!, comparator, compCount)
Coverage.statementStart(4139)
                                resultList[j - 1] = null
Coverage.statementStart(4140)
                            }
Coverage.statementStart(4141)
                            j++
Coverage.statementStart(4142)
                        }
Coverage.statementStart(4143)
                    }
Coverage.statementStart(4144)
                }
Coverage.statementStart(4145)
                buf1 = IntArray(columns.size * MERGE_SORT_MIN_ROWS)
Coverage.statementStart(4146)
            }
Coverage.statementStart(4147)
            var j = 1
Coverage.statementStart(4148)
            while (j < resultList.size) {
Coverage.whileLoopStart(4149)
                if (resultList[j] == null) {
Coverage.ifStart(4150)
                    resultList[j] = resultList[j - 1]
Coverage.statementStart(4151)
                } else if (resultList[j - 1] != null) {
Coverage.ifStart(4152)
                    resultList[j] = RowIteratorMerge(resultList[j]!!, resultList[j - 1]!!, comparator, compCount)
Coverage.statementStart(4153)
                }
Coverage.statementStart(4154)
                j++
Coverage.statementStart(4155)
            }
Coverage.statementStart(4156)
            SanityCheck.check { resultList.size > 0 }
Coverage.statementStart(4157)
            return resultList[resultList.size - 1]!!
        }
    }
    var flag = 3
    var aIdx = -1
    var bIdx = -1
    init {
Coverage.funStart(4158)
        SanityCheck {
Coverage.statementStart(4159)
            SanityCheck.check { a.columns.size == b.columns.size }
Coverage.statementStart(4160)
            for (i in 0 until a.columns.size) {
Coverage.forLoopStart(4161)
                SanityCheck.check { a.columns[i] == b.columns[i] }
Coverage.statementStart(4162)
            }
Coverage.statementStart(4163)
        }
Coverage.statementStart(4164)
        columns = a.columns
Coverage.statementStart(4165)
        next = {
Coverage.statementStart(4166)
            var res = -1
Coverage.statementStart(4167)
            when (flag) {
                1 -> {//call next on a, b is empty
Coverage.whenCaseStart(4169)
                    res = a.next()
Coverage.statementStart(4170)
                    buf = a.buf
Coverage.statementStart(4171)
                    if (res < 0) {
Coverage.ifStart(4172)
                        flag = 0
Coverage.statementStart(4173)
                    }
Coverage.statementStart(4174)
                }
                2 -> {//call next on b, a is empty
Coverage.whenCaseStart(4175)
                    res = b.next()
Coverage.statementStart(4176)
                    buf = b.buf
Coverage.statementStart(4177)
                    if (res < 0) {
Coverage.ifStart(4178)
                        flag = 0
Coverage.statementStart(4179)
                    }
Coverage.statementStart(4180)
                }
                4 -> {//call next on a, b is not empty
Coverage.whenCaseStart(4181)
                    aIdx = a.next()
Coverage.statementStart(4182)
                    if (aIdx < 0) {
Coverage.ifStart(4183)
                        buf = b.buf
Coverage.statementStart(4184)
                        res = bIdx
Coverage.statementStart(4185)
                        flag = 2
Coverage.statementStart(4186)
                    } else {
Coverage.ifStart(4187)
                        compare({
Coverage.statementStart(4188)
                            buf = a.buf
Coverage.statementStart(4189)
                            res = aIdx
Coverage.statementStart(4190)
                            flag = 4
Coverage.statementStart(4191)
                        }, {
Coverage.statementStart(4192)
                            buf = b.buf
Coverage.statementStart(4193)
                            res = bIdx
Coverage.statementStart(4194)
                            flag = 5
Coverage.statementStart(4195)
                        })
Coverage.statementStart(4196)
                    }
Coverage.statementStart(4197)
                }
                5 -> {//call next on b, a is not empty
Coverage.whenCaseStart(4198)
                    bIdx = b.next()
Coverage.statementStart(4199)
                    if (bIdx < 0) {
Coverage.ifStart(4200)
                        buf = a.buf
Coverage.statementStart(4201)
                        res = aIdx
Coverage.statementStart(4202)
                        flag = 1
Coverage.statementStart(4203)
                    } else {
Coverage.ifStart(4204)
                        compare({
Coverage.statementStart(4205)
                            buf = a.buf
Coverage.statementStart(4206)
                            res = aIdx
Coverage.statementStart(4207)
                            flag = 4
Coverage.statementStart(4208)
                        }, {
Coverage.statementStart(4209)
                            buf = b.buf
Coverage.statementStart(4210)
                            res = bIdx
Coverage.statementStart(4211)
                            flag = 5
Coverage.statementStart(4212)
                        })
Coverage.statementStart(4213)
                    }
Coverage.statementStart(4214)
                }
                3 -> {//call next on both
Coverage.whenCaseStart(4215)
                    aIdx = a.next()
Coverage.statementStart(4216)
                    bIdx = b.next()
Coverage.statementStart(4217)
                    if (aIdx < 0 && bIdx < 0) {
Coverage.ifStart(4218)
                        res = -1
Coverage.statementStart(4219)
                        flag = 0
Coverage.statementStart(4220)
                    } else if (bIdx < 0) {
Coverage.ifStart(4221)
                        buf = a.buf
Coverage.statementStart(4222)
                        res = aIdx
Coverage.statementStart(4223)
                        flag = 1
Coverage.statementStart(4224)
                    } else if (aIdx < 0) {
Coverage.ifStart(4225)
                        buf = b.buf
Coverage.statementStart(4226)
                        res = bIdx
Coverage.statementStart(4227)
                        flag = 2
Coverage.statementStart(4228)
                    } else {
Coverage.ifStart(4229)
                        compare({
Coverage.statementStart(4230)
                            buf = a.buf
Coverage.statementStart(4231)
                            res = aIdx
Coverage.statementStart(4232)
                            flag = 4
Coverage.statementStart(4233)
                        }, {
Coverage.statementStart(4234)
                            buf = b.buf
Coverage.statementStart(4235)
                            res = bIdx
Coverage.statementStart(4236)
                            flag = 5
Coverage.statementStart(4237)
                        })
Coverage.statementStart(4238)
                    }
Coverage.statementStart(4239)
                }
            }
Coverage.statementStart(4240)
            /*return*/ res
        }
Coverage.statementStart(4241)
    }
    inline fun compare(crossinline actionA: () -> Unit, crossinline actionB: () -> Unit) {
Coverage.funStart(4242)
        var i = 0
Coverage.statementStart(4243)
        while (i < compCount) {
Coverage.whileLoopStart(4244)
            val cmp = comparator.compare(a.buf[aIdx + i], b.buf[bIdx + i])
Coverage.statementStart(4245)
            if (cmp < 0) {
Coverage.ifStart(4246)
                actionA()
Coverage.statementStart(4247)
                return
            } else if (cmp > 0) {
Coverage.statementStart(4248)
                actionB()
Coverage.statementStart(4249)
                return
            }
Coverage.statementStart(4250)
            i++
Coverage.statementStart(4251)
        }
Coverage.statementStart(4252)
        while (i < columns.size) {
Coverage.whileLoopStart(4253)
            val cmp = a.buf[aIdx + i] - b.buf[bIdx + i]
Coverage.statementStart(4254)
            if (cmp < 0) {
Coverage.ifStart(4255)
                actionA()
Coverage.statementStart(4256)
                return
            } else if (cmp > 0) {
Coverage.statementStart(4257)
                actionB()
Coverage.statementStart(4258)
                return
            }
Coverage.statementStart(4259)
            i++
Coverage.statementStart(4260)
        }
Coverage.statementStart(4261)
        actionA()
Coverage.statementStart(4262)
    }
}
