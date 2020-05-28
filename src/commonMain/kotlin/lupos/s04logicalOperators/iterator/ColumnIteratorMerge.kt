package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
object ColumnIteratorMerge {
    suspend operator fun invoke(a: ColumnIterator, comparator: Comparator<Value>): ColumnIterator {
Coverage.funStart(3499)
        var buf1 = IntArray(MERGE_SORT_MIN_ROWS)
Coverage.statementStart(3500)
        var buf2 = IntArray(MERGE_SORT_MIN_ROWS)
Coverage.statementStart(3501)
        var done = false
Coverage.statementStart(3502)
        var resultList = mutableListOf<ColumnIterator?>()
Coverage.statementStart(3503)
        while (!done) {
Coverage.whileLoopStart(3504)
            var i = 0
Coverage.statementStart(3505)
            while (i < buf1.size) {
Coverage.whileLoopStart(3506)
                val next = a.next()
Coverage.statementStart(3507)
                if (next == null) {
Coverage.ifStart(3508)
                    done = true
Coverage.statementStart(3509)
                    break
                } else {
Coverage.statementStart(3510)
                    buf1[i++] = next
Coverage.statementStart(3511)
                }
Coverage.statementStart(3512)
            }
Coverage.statementStart(3513)
            var total = i
Coverage.statementStart(3514)
            var off: Int
Coverage.statementStart(3515)
            var shift = 0
Coverage.statementStart(3516)
            var size = 1 shl shift
Coverage.statementStart(3517)
            var count: Int
Coverage.statementStart(3518)
            var mid: Int
Coverage.statementStart(3519)
            while (size / 2 < total) {
Coverage.whileLoopStart(3520)
                off = 0
Coverage.statementStart(3521)
                shift++
Coverage.statementStart(3522)
                size = 1 shl shift
Coverage.statementStart(3523)
                while (off < total) {
Coverage.whileLoopStart(3524)
                    if (off + size <= total) {
Coverage.ifStart(3525)
                        count = size
Coverage.statementStart(3526)
                    } else {
Coverage.ifStart(3527)
                        count = total - off
Coverage.statementStart(3528)
                    }
Coverage.statementStart(3529)
                    mid = size / 2
Coverage.statementStart(3530)
                    val aEnd = (off + mid)
Coverage.statementStart(3531)
                    val bEnd = (off + count)
Coverage.statementStart(3532)
                    var a_ = off
Coverage.statementStart(3533)
                    var b = aEnd
Coverage.statementStart(3534)
                    var c = a_
Coverage.statementStart(3535)
                    if (count < mid) {
Coverage.ifStart(3536)
                        b = a_
Coverage.statementStart(3537)
                        a_ = aEnd
Coverage.statementStart(3538)
                    }
Coverage.statementStart(3539)
                    loop@ while (a_ < aEnd && b < bEnd) {
Coverage.whileLoopStart(3540)
                        if (comparator.compare(buf1[a_], buf1[b]) < 0) {
Coverage.ifStart(3541)
                            buf2[c++] = buf1[a_++]
Coverage.statementStart(3542)
                        } else {
Coverage.ifStart(3543)
                            buf2[c++] = buf1[b++]
Coverage.statementStart(3544)
                        }
Coverage.statementStart(3545)
                    }
Coverage.statementStart(3546)
                    while (a_ < aEnd) {
Coverage.whileLoopStart(3547)
                        buf2[c++] = buf1[a_++]
Coverage.statementStart(3548)
                    }
Coverage.statementStart(3549)
                    while (b < bEnd) {
Coverage.whileLoopStart(3550)
                        buf2[c++] = buf1[b++]
Coverage.statementStart(3551)
                    }
Coverage.statementStart(3552)
                    off += size
Coverage.statementStart(3553)
                }
Coverage.statementStart(3554)
                var t = buf1
Coverage.statementStart(3555)
                buf1 = buf2
Coverage.statementStart(3556)
                buf2 = t
Coverage.statementStart(3557)
            }
Coverage.statementStart(3558)
            var it = ColumnIteratorMultiValue(buf1, total)
Coverage.statementStart(3559)
            if (i > 0 || resultList.size == 0) {
Coverage.ifStart(3560)
                if (resultList.size == 0) {
Coverage.ifStart(3561)
                    resultList.add(it)
Coverage.statementStart(3562)
                } else if (resultList[0] == null) {
Coverage.ifStart(3563)
                    resultList[0] = it
Coverage.statementStart(3564)
                } else {
Coverage.ifStart(3565)
                    resultList[0] = ColumnIteratorMerge1(resultList[0]!!, it, comparator)
Coverage.statementStart(3566)
                    if (resultList[resultList.size - 1] != null) {
Coverage.ifStart(3567)
                        resultList.add(null)
Coverage.statementStart(3568)
                    }
Coverage.statementStart(3569)
                    var j = 1
Coverage.statementStart(3570)
                    while (j < resultList.size) {
Coverage.whileLoopStart(3571)
                        if (resultList[j] == null) {
Coverage.ifStart(3572)
                            resultList[j] = resultList[j - 1]
Coverage.statementStart(3573)
                            resultList[j - 1] = null
Coverage.statementStart(3574)
                            break
                        } else {
Coverage.statementStart(3575)
                            resultList[j] = ColumnIteratorMerge1(resultList[j]!!, resultList[j - 1]!!, comparator)
Coverage.statementStart(3576)
                            resultList[j - 1] = null
Coverage.statementStart(3577)
                        }
Coverage.statementStart(3578)
                        j++
Coverage.statementStart(3579)
                    }
Coverage.statementStart(3580)
                }
Coverage.statementStart(3581)
            }
Coverage.statementStart(3582)
            buf1 = IntArray(MERGE_SORT_MIN_ROWS)
Coverage.statementStart(3583)
        }
Coverage.statementStart(3584)
        var j = 1
Coverage.statementStart(3585)
        while (j < resultList.size) {
Coverage.whileLoopStart(3586)
            if (resultList[j] == null) {
Coverage.ifStart(3587)
                resultList[j] = resultList[j - 1]
Coverage.statementStart(3588)
            } else if (resultList[j - 1] != null) {
Coverage.ifStart(3589)
                resultList[j] = ColumnIteratorMerge1(resultList[j]!!, resultList[j - 1]!!, comparator)
Coverage.statementStart(3590)
            }
Coverage.statementStart(3591)
            j++
Coverage.statementStart(3592)
        }
Coverage.statementStart(3593)
        SanityCheck.check { resultList.size > 0 }
Coverage.statementStart(3594)
        return resultList[resultList.size - 1]!!
    }
    suspend operator fun invoke(a: ColumnIterator): ColumnIterator {
Coverage.funStart(3595)
        var buf1 = IntArray(MERGE_SORT_MIN_ROWS)
Coverage.statementStart(3596)
        var buf2 = IntArray(MERGE_SORT_MIN_ROWS)
Coverage.statementStart(3597)
        var done = false
Coverage.statementStart(3598)
        var resultList = mutableListOf<ColumnIterator?>()
Coverage.statementStart(3599)
        while (!done) {
Coverage.whileLoopStart(3600)
            var i = 0
Coverage.statementStart(3601)
            while (i < buf1.size) {
Coverage.whileLoopStart(3602)
                val next = a.next()
Coverage.statementStart(3603)
                if (next == null) {
Coverage.ifStart(3604)
                    done = true
Coverage.statementStart(3605)
                    break
                } else {
Coverage.statementStart(3606)
                    buf1[i++] = next
Coverage.statementStart(3607)
                }
Coverage.statementStart(3608)
            }
Coverage.statementStart(3609)
            var total = i
Coverage.statementStart(3610)
            var off: Int
Coverage.statementStart(3611)
            var shift = 0
Coverage.statementStart(3612)
            var size = 1 shl shift
Coverage.statementStart(3613)
            var count: Int
Coverage.statementStart(3614)
            var mid: Int
Coverage.statementStart(3615)
            while (size / 2 < total) {
Coverage.whileLoopStart(3616)
                off = 0
Coverage.statementStart(3617)
                shift++
Coverage.statementStart(3618)
                size = 1 shl shift
Coverage.statementStart(3619)
                while (off < total) {
Coverage.whileLoopStart(3620)
                    if (off + size <= total) {
Coverage.ifStart(3621)
                        count = size
Coverage.statementStart(3622)
                    } else {
Coverage.ifStart(3623)
                        count = total - off
Coverage.statementStart(3624)
                    }
Coverage.statementStart(3625)
                    mid = size / 2
Coverage.statementStart(3626)
                    val aEnd = (off + mid)
Coverage.statementStart(3627)
                    val bEnd = (off + count)
Coverage.statementStart(3628)
                    var a_ = off
Coverage.statementStart(3629)
                    var b = aEnd
Coverage.statementStart(3630)
                    var c = a_
Coverage.statementStart(3631)
                    if (count < mid) {
Coverage.ifStart(3632)
                        b = a_
Coverage.statementStart(3633)
                        a_ = aEnd
Coverage.statementStart(3634)
                    }
Coverage.statementStart(3635)
                    loop@ while (a_ < aEnd && b < bEnd) {
Coverage.whileLoopStart(3636)
                        if (buf1[a_] < buf1[b]) {
Coverage.ifStart(3637)
                            buf2[c++] = buf1[a_++]
Coverage.statementStart(3638)
                        } else {
Coverage.ifStart(3639)
                            buf2[c++] = buf1[b++]
Coverage.statementStart(3640)
                        }
Coverage.statementStart(3641)
                    }
Coverage.statementStart(3642)
                    while (a_ < aEnd) {
Coverage.whileLoopStart(3643)
                        buf2[c++] = buf1[a_++]
Coverage.statementStart(3644)
                    }
Coverage.statementStart(3645)
                    while (b < bEnd) {
Coverage.whileLoopStart(3646)
                        buf2[c++] = buf1[b++]
Coverage.statementStart(3647)
                    }
Coverage.statementStart(3648)
                    off += size
Coverage.statementStart(3649)
                }
Coverage.statementStart(3650)
                var t = buf1
Coverage.statementStart(3651)
                buf1 = buf2
Coverage.statementStart(3652)
                buf2 = t
Coverage.statementStart(3653)
            }
Coverage.statementStart(3654)
            var it = ColumnIteratorMultiValue(buf1, total)
Coverage.statementStart(3655)
            if (i > 0 || resultList.size == 0) {
Coverage.ifStart(3656)
                if (resultList.size == 0) {
Coverage.ifStart(3657)
                    resultList.add(it)
Coverage.statementStart(3658)
                } else if (resultList[0] == null) {
Coverage.ifStart(3659)
                    resultList[0] = it
Coverage.statementStart(3660)
                } else {
Coverage.ifStart(3661)
                    resultList[0] = ColumnIteratorMerge2(resultList[0]!!, it)
Coverage.statementStart(3662)
                    if (resultList[resultList.size - 1] != null) {
Coverage.ifStart(3663)
                        resultList.add(null)
Coverage.statementStart(3664)
                    }
Coverage.statementStart(3665)
                    var j = 1
Coverage.statementStart(3666)
                    while (j < resultList.size) {
Coverage.whileLoopStart(3667)
                        if (resultList[j] == null) {
Coverage.ifStart(3668)
                            resultList[j] = resultList[j - 1]
Coverage.statementStart(3669)
                            resultList[j - 1] = null
Coverage.statementStart(3670)
                            break
                        } else {
Coverage.statementStart(3671)
                            resultList[j] = ColumnIteratorMerge2(resultList[j]!!, resultList[j - 1]!!)
Coverage.statementStart(3672)
                            resultList[j - 1] = null
Coverage.statementStart(3673)
                        }
Coverage.statementStart(3674)
                        j++
Coverage.statementStart(3675)
                    }
Coverage.statementStart(3676)
                }
Coverage.statementStart(3677)
            }
Coverage.statementStart(3678)
            buf1 = IntArray(MERGE_SORT_MIN_ROWS)
Coverage.statementStart(3679)
        }
Coverage.statementStart(3680)
        var j = 1
Coverage.statementStart(3681)
        while (j < resultList.size) {
Coverage.whileLoopStart(3682)
            if (resultList[j] == null) {
Coverage.ifStart(3683)
                resultList[j] = resultList[j - 1]
Coverage.statementStart(3684)
            } else if (resultList[j - 1] != null) {
Coverage.ifStart(3685)
                resultList[j] = ColumnIteratorMerge2(resultList[j]!!, resultList[j - 1]!!)
Coverage.statementStart(3686)
            }
Coverage.statementStart(3687)
            j++
Coverage.statementStart(3688)
        }
Coverage.statementStart(3689)
        SanityCheck.check { resultList.size > 0 }
Coverage.statementStart(3690)
        return resultList[resultList.size - 1]!!
    }
}
class ColumnIteratorMerge1(val a: ColumnIterator, val b: ColumnIterator, val comparator: Comparator<Value>) : ColumnIterator() {
    var flag = 3
    var aBuf: Value? = null
    var bBuf: Value? = null
    init {
Coverage.funStart(3691)
        next = {
Coverage.statementStart(3692)
            var res: Value? = null
Coverage.statementStart(3693)
            when (flag) {
                1 -> {//call next on a, b is empty
Coverage.whenCaseStart(3695)
                    res = a.next()
Coverage.statementStart(3696)
                    if (res == null) {
Coverage.ifStart(3697)
                        flag = 0
Coverage.statementStart(3698)
                    }
Coverage.statementStart(3699)
                }
                2 -> {//call next on b, a is empty
Coverage.whenCaseStart(3700)
                    res = b.next()
Coverage.statementStart(3701)
                    if (res == null) {
Coverage.ifStart(3702)
                        flag = 0
Coverage.statementStart(3703)
                    }
Coverage.statementStart(3704)
                }
                4 -> {//call next on a, b is not empty
Coverage.whenCaseStart(3705)
                    aBuf = a.next()
Coverage.statementStart(3706)
                    if (aBuf == null) {
Coverage.ifStart(3707)
                        res = bBuf
Coverage.statementStart(3708)
                        flag = 2
Coverage.statementStart(3709)
                    } else {
Coverage.ifStart(3710)
                        if (comparator.compare(aBuf, bBuf) < 0) {
Coverage.ifStart(3711)
                            res = aBuf
Coverage.statementStart(3712)
                            flag = 4
Coverage.statementStart(3713)
                        } else {
Coverage.ifStart(3714)
                            res = bBuf
Coverage.statementStart(3715)
                            flag = 5
Coverage.statementStart(3716)
                        }
Coverage.statementStart(3717)
                    }
Coverage.statementStart(3718)
                }
                5 -> {//call next on b, a is not empty
Coverage.whenCaseStart(3719)
                    bBuf = b.next()
Coverage.statementStart(3720)
                    if (bBuf == null) {
Coverage.ifStart(3721)
                        res = aBuf
Coverage.statementStart(3722)
                        flag = 1
Coverage.statementStart(3723)
                    } else {
Coverage.ifStart(3724)
                        if (comparator.compare(aBuf, bBuf) < 0) {
Coverage.ifStart(3725)
                            res = aBuf
Coverage.statementStart(3726)
                            flag = 4
Coverage.statementStart(3727)
                        } else {
Coverage.ifStart(3728)
                            res = bBuf
Coverage.statementStart(3729)
                            flag = 5
Coverage.statementStart(3730)
                        }
Coverage.statementStart(3731)
                    }
Coverage.statementStart(3732)
                }
                3 -> {//call next on both
Coverage.whenCaseStart(3733)
                    aBuf = a.next()
Coverage.statementStart(3734)
                    bBuf = b.next()
Coverage.statementStart(3735)
                    if (aBuf == null && bBuf == null) {
Coverage.ifStart(3736)
                        res = null
Coverage.statementStart(3737)
                        flag = 0
Coverage.statementStart(3738)
                    } else if (bBuf == null) {
Coverage.ifStart(3739)
                        res = aBuf
Coverage.statementStart(3740)
                        flag = 1
Coverage.statementStart(3741)
                    } else if (aBuf == null) {
Coverage.ifStart(3742)
                        res = bBuf
Coverage.statementStart(3743)
                        flag = 2
Coverage.statementStart(3744)
                    } else {
Coverage.ifStart(3745)
                        if (comparator.compare(aBuf, bBuf) < 0) {
Coverage.ifStart(3746)
                            res = aBuf
Coverage.statementStart(3747)
                            flag = 4
Coverage.statementStart(3748)
                        } else {
Coverage.ifStart(3749)
                            res = bBuf
Coverage.statementStart(3750)
                            flag = 5
Coverage.statementStart(3751)
                        }
Coverage.statementStart(3752)
                    }
Coverage.statementStart(3753)
                }
            }
Coverage.statementStart(3754)
            /*return*/ res
        }
Coverage.statementStart(3755)
    }
}
class ColumnIteratorMerge2(val a: ColumnIterator, val b: ColumnIterator) : ColumnIterator() {
    var flag = 3
    var aBuf: Value? = null
    var bBuf: Value? = null
    init {
Coverage.funStart(3756)
        next = {
Coverage.statementStart(3757)
            var res: Value? = null
Coverage.statementStart(3758)
            when (flag) {
                1 -> {//call next on a, b is empty
Coverage.whenCaseStart(3760)
                    res = a.next()
Coverage.statementStart(3761)
                    if (res == null) {
Coverage.ifStart(3762)
                        flag = 0
Coverage.statementStart(3763)
                    }
Coverage.statementStart(3764)
                }
                2 -> {//call next on b, a is empty
Coverage.whenCaseStart(3765)
                    res = b.next()
Coverage.statementStart(3766)
                    if (res == null) {
Coverage.ifStart(3767)
                        flag = 0
Coverage.statementStart(3768)
                    }
Coverage.statementStart(3769)
                }
                4 -> {//call next on a, b is not empty
Coverage.whenCaseStart(3770)
                    aBuf = a.next()
Coverage.statementStart(3771)
                    if (aBuf == null) {
Coverage.ifStart(3772)
                        res = bBuf
Coverage.statementStart(3773)
                        flag = 2
Coverage.statementStart(3774)
                    } else {
Coverage.ifStart(3775)
                        if (aBuf!! < bBuf!!) {
Coverage.ifStart(3776)
                            res = aBuf
Coverage.statementStart(3777)
                            flag = 4
Coverage.statementStart(3778)
                        } else {
Coverage.ifStart(3779)
                            res = bBuf
Coverage.statementStart(3780)
                            flag = 5
Coverage.statementStart(3781)
                        }
Coverage.statementStart(3782)
                    }
Coverage.statementStart(3783)
                }
                5 -> {//call next on b, a is not empty
Coverage.whenCaseStart(3784)
                    bBuf = b.next()
Coverage.statementStart(3785)
                    if (bBuf == null) {
Coverage.ifStart(3786)
                        res = aBuf
Coverage.statementStart(3787)
                        flag = 1
Coverage.statementStart(3788)
                    } else {
Coverage.ifStart(3789)
                        if (aBuf!! < bBuf!!) {
Coverage.ifStart(3790)
                            res = aBuf
Coverage.statementStart(3791)
                            flag = 4
Coverage.statementStart(3792)
                        } else {
Coverage.ifStart(3793)
                            res = bBuf
Coverage.statementStart(3794)
                            flag = 5
Coverage.statementStart(3795)
                        }
Coverage.statementStart(3796)
                    }
Coverage.statementStart(3797)
                }
                3 -> {//call next on both
Coverage.whenCaseStart(3798)
                    aBuf = a.next()
Coverage.statementStart(3799)
                    bBuf = b.next()
Coverage.statementStart(3800)
                    if (aBuf == null && bBuf == null) {
Coverage.ifStart(3801)
                        flag = 0
Coverage.statementStart(3802)
                    } else if (bBuf == null) {
Coverage.ifStart(3803)
                        res = aBuf
Coverage.statementStart(3804)
                        flag = 1
Coverage.statementStart(3805)
                    } else if (aBuf == null) {
Coverage.ifStart(3806)
                        res = bBuf
Coverage.statementStart(3807)
                        flag = 2
Coverage.statementStart(3808)
                    } else {
Coverage.ifStart(3809)
                        if (aBuf!! < bBuf!!) {
Coverage.ifStart(3810)
                            res = aBuf
Coverage.statementStart(3811)
                            flag = 4
Coverage.statementStart(3812)
                        } else {
Coverage.ifStart(3813)
                            res = bBuf
Coverage.statementStart(3814)
                            flag = 5
Coverage.statementStart(3815)
                        }
Coverage.statementStart(3816)
                    }
Coverage.statementStart(3817)
                }
            }
Coverage.statementStart(3818)
            /*return*/ res
        }
Coverage.statementStart(3819)
    }
}
