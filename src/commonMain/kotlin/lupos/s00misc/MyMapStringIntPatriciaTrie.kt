package lupos.s00misc
import lupos.s00misc.Coverage
class MyMapStringIntPatriciaTrie(val undefinedValue: Int = Int.MAX_VALUE) {
    var root: MyMapStringIntPatriciaTrieNode = MyMapStringIntPatriciaTrieNodeN()
    var rootValue: Int = undefinedValue
    var size = 0
    abstract class MyMapStringIntPatriciaTrieNode() {
        var str = ""
    }
    class MyMapStringIntPatriciaTrieNodeN() : MyMapStringIntPatriciaTrieNode() {
        var arr = IntArray(0) /*second half: child values, first half offsets in str*/
        var childs = arrayOf<MyMapStringIntPatriciaTrieNode?>()
    }
    class MyMapStringIntPatriciaTrieNode2() : MyMapStringIntPatriciaTrieNode() {
        var arr1: Int = 0
        var arr2: Int = 0
        var arr3: Int = 0
        var childs0: MyMapStringIntPatriciaTrieNode? = null
        var childs1: MyMapStringIntPatriciaTrieNode? = null
    }
    inline fun walkInternal(_key: String, crossinline onCreate: () -> Int, crossinline onExist: (Int) -> Int, crossinline onNotFound: () -> Unit, create: Boolean) {
Coverage.funStart(452)
        if (_key == "") {
Coverage.ifStart(453)
            rootValue = onExist(rootValue)
Coverage.statementStart(454)
        } else {
Coverage.ifStart(455)
            var key = _key
Coverage.statementStart(456)
            var prev: MyMapStringIntPatriciaTrieNode? = null
Coverage.statementStart(457)
            var nextnode = root
Coverage.statementStart(458)
            loop@ while (true) {
Coverage.whileLoopStart(459)
                SanityCheck.check { key.length > 0 }
Coverage.statementStart(460)
                val node = nextnode
Coverage.statementStart(461)
                when (node) {
                    is MyMapStringIntPatriciaTrieNode2 -> {
Coverage.whenCaseStart(463)
                        if (node.str[0] == key[0]) {
Coverage.ifStart(464)
                            var childKeyStart = 0
Coverage.statementStart(465)
                            var childKeyEnd = node.arr1
Coverage.statementStart(466)
                            var childKey = node.str.substring(childKeyStart, childKeyEnd)
Coverage.statementStart(467)
                            SanityCheck.check { childKey.length > 0 }
Coverage.statementStart(468)
                            var commonKey = key.commonPrefixWith(childKey)
Coverage.statementStart(469)
                            SanityCheck.check { commonKey.length > 0 }
Coverage.statementStart(470)
                            if (commonKey.length == key.length && commonKey.length == childKey.length) {
Coverage.ifStart(471)
                                node.arr2 = onExist(node.arr2)
Coverage.statementStart(472)
                                return
                            } else if (commonKey.length == childKey.length) {
Coverage.statementStart(473)
                                if (node.childs0 != null) {
Coverage.ifStart(474)
                                    prev = node
Coverage.statementStart(475)
                                    nextnode = node.childs0!!
Coverage.statementStart(476)
                                    key = key.substring(childKey.length, key.length)
Coverage.statementStart(477)
                                    SanityCheck.check { key.length > 0 }
Coverage.statementStart(478)
                                    continue@loop
                                } else {
Coverage.statementStart(479)
                                    if (create) {
Coverage.ifStart(480)
                                        val newNode = MyMapStringIntPatriciaTrieNodeN()
Coverage.statementStart(481)
                                        newNode.childs = arrayOf(null)
Coverage.statementStart(482)
                                        newNode.arr = intArrayOf(0, onCreate())
Coverage.statementStart(483)
                                        newNode.str = key.substring(commonKey.length, key.length)
Coverage.statementStart(484)
                                        SanityCheck.check { newNode.str.length > 0 }
Coverage.statementStart(485)
                                        node.childs0 = newNode
Coverage.statementStart(486)
                                        size++
Coverage.statementStart(487)
                                    } else {
Coverage.ifStart(488)
                                        onNotFound()
Coverage.statementStart(489)
                                    }
Coverage.statementStart(490)
                                    return
                                }
Coverage.statementStart(491)
                            } else {
Coverage.ifStart(492)
                                if (create) {
Coverage.ifStart(493)
                                    if (commonKey.length == key.length) {
Coverage.ifStart(494)
                                        var otherKey = childKey.substring(commonKey.length, childKey.length)
Coverage.statementStart(495)
                                        SanityCheck.check { otherKey.length > 0 }
Coverage.statementStart(496)
                                        var newNode = MyMapStringIntPatriciaTrieNodeN()
Coverage.statementStart(497)
                                        newNode.childs = arrayOf(node.childs0)
Coverage.statementStart(498)
                                        newNode.str = otherKey
Coverage.statementStart(499)
                                        newNode.arr = intArrayOf(0, node.arr2)
Coverage.statementStart(500)
                                        node.childs0 = newNode
Coverage.statementStart(501)
                                        node.arr2 = onCreate()
Coverage.statementStart(502)
                                        node.str = commonKey + node.str.substring(childKeyEnd, node.str.length)
Coverage.statementStart(503)
                                        SanityCheck.check { node.str.length > commonKey.length }
Coverage.statementStart(504)
                                        node.arr1 = commonKey.length
Coverage.statementStart(505)
                                        SanityCheck.check { node.arr1 < node.str.length }
Coverage.statementStart(506)
                                    } else {
Coverage.ifStart(507)
                                        var otherKey = childKey.substring(commonKey.length, childKey.length)
Coverage.statementStart(508)
                                        SanityCheck.check { otherKey.length > 0 }
Coverage.statementStart(509)
                                        var newKey = key.substring(commonKey.length, key.length)
Coverage.statementStart(510)
                                        SanityCheck.check { newKey.length > 0 }
Coverage.statementStart(511)
                                        var newNode = MyMapStringIntPatriciaTrieNode2()
Coverage.statementStart(512)
                                        newNode.childs0 = node.childs0
Coverage.statementStart(513)
                                        newNode.childs1 = null
Coverage.statementStart(514)
                                        newNode.str = otherKey + newKey
Coverage.statementStart(515)
                                        newNode.arr1 = otherKey.length
Coverage.statementStart(516)
                                        newNode.arr2 = node.arr2
Coverage.statementStart(517)
                                        newNode.arr3 = onCreate()
Coverage.statementStart(518)
                                        node.childs0 = newNode
Coverage.statementStart(519)
                                        node.arr2 = undefinedValue
Coverage.statementStart(520)
                                        node.str = commonKey + node.str.substring(childKeyEnd, node.str.length)
Coverage.statementStart(521)
                                        SanityCheck.check { node.str.length > commonKey.length }
Coverage.statementStart(522)
                                        node.arr1 = commonKey.length
Coverage.statementStart(523)
                                    }
Coverage.statementStart(524)
                                    size++
Coverage.statementStart(525)
                                } else {
Coverage.ifStart(526)
                                    onNotFound()
Coverage.statementStart(527)
                                }
Coverage.statementStart(528)
                                return
                            }
Coverage.statementStart(529)
                        } else if (node.str[node.arr1] == key[0]) {
Coverage.ifStart(530)
                            var childKeyStart = node.arr1
Coverage.statementStart(531)
                            var childKeyEnd = node.str.length
Coverage.statementStart(532)
                            var childKey = node.str.substring(childKeyStart, childKeyEnd)
Coverage.statementStart(533)
                            SanityCheck.check { childKey.length > 0 }
Coverage.statementStart(534)
                            var commonKey = key.commonPrefixWith(childKey)
Coverage.statementStart(535)
                            if (commonKey.length == key.length && commonKey.length == childKey.length) {
Coverage.ifStart(536)
                                node.arr3 = onExist(node.arr3)
Coverage.statementStart(537)
                                return
                            } else if (commonKey.length == childKey.length) {
Coverage.statementStart(538)
                                if (node.childs1 != null) {
Coverage.ifStart(539)
                                    prev = node
Coverage.statementStart(540)
                                    nextnode = node.childs1!!
Coverage.statementStart(541)
                                    key = key.substring(childKey.length, key.length)
Coverage.statementStart(542)
                                    SanityCheck.check { key.length > 0 }
Coverage.statementStart(543)
                                    continue@loop
                                } else {
Coverage.statementStart(544)
                                    if (create) {
Coverage.ifStart(545)
                                        val newNode = MyMapStringIntPatriciaTrieNodeN()
Coverage.statementStart(546)
                                        newNode.childs = arrayOf(null)
Coverage.statementStart(547)
                                        newNode.arr = intArrayOf(0, onCreate())
Coverage.statementStart(548)
                                        newNode.str = key.substring(commonKey.length, key.length)
Coverage.statementStart(549)
                                        SanityCheck.check { newNode.str.length > 0 }
Coverage.statementStart(550)
                                        node.childs1 = newNode
Coverage.statementStart(551)
                                        size++
Coverage.statementStart(552)
                                    } else {
Coverage.ifStart(553)
                                        onNotFound()
Coverage.statementStart(554)
                                    }
Coverage.statementStart(555)
                                    return
                                }
Coverage.statementStart(556)
                            } else {
Coverage.ifStart(557)
                                if (create) {
Coverage.ifStart(558)
                                    if (commonKey.length == key.length) {
Coverage.ifStart(559)
                                        var otherKey = childKey.substring(commonKey.length, childKey.length)
Coverage.statementStart(560)
                                        SanityCheck.check { otherKey.length > 0 }
Coverage.statementStart(561)
                                        var newNode = MyMapStringIntPatriciaTrieNodeN()
Coverage.statementStart(562)
                                        newNode.childs = arrayOf(node.childs1)
Coverage.statementStart(563)
                                        newNode.str = otherKey
Coverage.statementStart(564)
                                        newNode.arr = intArrayOf(0, node.arr3)
Coverage.statementStart(565)
                                        node.childs1 = newNode
Coverage.statementStart(566)
                                        node.arr3 = onCreate()
Coverage.statementStart(567)
                                        node.str = node.str.substring(0, childKeyStart) + commonKey
Coverage.statementStart(568)
                                        SanityCheck.check { node.str.length > commonKey.length }
Coverage.statementStart(569)
                                    } else {
Coverage.ifStart(570)
                                        var otherKey = childKey.substring(commonKey.length, childKey.length)
Coverage.statementStart(571)
                                        var newKey = key.substring(commonKey.length, key.length)
Coverage.statementStart(572)
                                        SanityCheck.check { newKey.length > 0 }
Coverage.statementStart(573)
                                        var newNode = MyMapStringIntPatriciaTrieNode2()
Coverage.statementStart(574)
                                        newNode.childs0 = node.childs1
Coverage.statementStart(575)
                                        newNode.childs1 = null
Coverage.statementStart(576)
                                        newNode.str = otherKey + newKey
Coverage.statementStart(577)
                                        newNode.arr1 = otherKey.length
Coverage.statementStart(578)
                                        newNode.arr2 = node.arr3
Coverage.statementStart(579)
                                        newNode.arr3 = onCreate()
Coverage.statementStart(580)
                                        node.childs1 = newNode
Coverage.statementStart(581)
                                        node.arr3 = undefinedValue
Coverage.statementStart(582)
                                        node.str = node.str.substring(0, childKeyStart) + commonKey
Coverage.statementStart(583)
                                        SanityCheck.check { node.str.length > commonKey.length }
Coverage.statementStart(584)
                                    }
Coverage.statementStart(585)
                                    size++
Coverage.statementStart(586)
                                } else {
Coverage.ifStart(587)
                                    onNotFound()
Coverage.statementStart(588)
                                }
Coverage.statementStart(589)
                                return
                            }
Coverage.statementStart(590)
                        } else if (create) {
Coverage.ifStart(591)
                            var childs = arrayOf(node.childs0, node.childs1, null)
Coverage.statementStart(592)
                            var arr = intArrayOf(0, node.arr1, node.str.length, node.arr2, node.arr3, onCreate())
Coverage.statementStart(593)
                            var nodetmp = MyMapStringIntPatriciaTrieNodeN()
Coverage.statementStart(594)
                            if (prev == null) {
Coverage.ifStart(595)
                                root = nodetmp
Coverage.statementStart(596)
                            } else {
Coverage.ifStart(597)
                                if (prev is MyMapStringIntPatriciaTrieNode2) {
Coverage.ifStart(598)
                                    if (prev.childs0 == node) {
Coverage.ifStart(599)
                                        prev.childs0 = nodetmp
Coverage.statementStart(600)
                                    } else {
Coverage.ifStart(601)
                                        prev.childs1 = nodetmp
Coverage.statementStart(602)
                                    }
Coverage.statementStart(603)
                                } else {
Coverage.ifStart(604)
                                    SanityCheck.check { prev is MyMapStringIntPatriciaTrieNodeN }
Coverage.statementStart(605)
                                    for (i in 0 until (prev as MyMapStringIntPatriciaTrieNodeN).childs.size) {
Coverage.forLoopStart(606)
                                        if (prev.childs[i] == node) {
Coverage.ifStart(607)
                                            prev.childs[i] = nodetmp
Coverage.statementStart(608)
                                        }
Coverage.statementStart(609)
                                    }
Coverage.statementStart(610)
                                }
Coverage.statementStart(611)
                            }
Coverage.statementStart(612)
                            nodetmp.str = node.str + key
Coverage.statementStart(613)
                            nodetmp.childs = childs
Coverage.statementStart(614)
                            nodetmp.arr = arr
Coverage.statementStart(615)
                            size++
Coverage.statementStart(616)
                        } else {
Coverage.ifStart(617)
                            onNotFound()
Coverage.statementStart(618)
                        }
Coverage.statementStart(619)
                        return
                    }
                    is MyMapStringIntPatriciaTrieNodeN -> {
Coverage.whenCaseStart(620)
                        var childCount = node.childs.size
Coverage.statementStart(621)
                        for (childIdx in 0 until childCount) {
Coverage.forLoopStart(622)
                            if (node.str[node.arr[childIdx]] == key[0]) {
Coverage.ifStart(623)
                                var childKeyStart = node.arr[childIdx]
Coverage.statementStart(624)
                                var childKeyEnd: Int
Coverage.statementStart(625)
                                if (childIdx == childCount - 1) {
Coverage.ifStart(626)
                                    childKeyEnd = node.str.length
Coverage.statementStart(627)
                                } else {
Coverage.ifStart(628)
                                    childKeyEnd = node.arr[childIdx + 1]
Coverage.statementStart(629)
                                }
Coverage.statementStart(630)
                                var childKey = node.str.substring(childKeyStart, childKeyEnd)
Coverage.statementStart(631)
                                SanityCheck.check { childKey.length > 0 }
Coverage.statementStart(632)
                                var commonKey = key.commonPrefixWith(childKey)
Coverage.statementStart(633)
                                if (commonKey.length == key.length && commonKey.length == childKey.length) {
Coverage.ifStart(634)
                                    node.arr[childCount + childIdx] = onExist(node.arr[childCount + childIdx])
Coverage.statementStart(635)
                                    return
                                } else if (commonKey.length == childKey.length) {
Coverage.statementStart(636)
                                    if (node.childs[childIdx] != null) {
Coverage.ifStart(637)
                                        prev = node
Coverage.statementStart(638)
                                        nextnode = node.childs[childIdx]!!
Coverage.statementStart(639)
                                        key = key.substring(childKey.length, key.length)
Coverage.statementStart(640)
                                        SanityCheck.check { key.length > 0 }
Coverage.statementStart(641)
                                        continue@loop
                                    } else {
Coverage.statementStart(642)
                                        if (create) {
Coverage.ifStart(643)
                                            val newNode = MyMapStringIntPatriciaTrieNodeN()
Coverage.statementStart(644)
                                            newNode.childs = arrayOf(null)
Coverage.statementStart(645)
                                            newNode.arr = intArrayOf(0, onCreate())
Coverage.statementStart(646)
                                            newNode.str = key.substring(commonKey.length, key.length)
Coverage.statementStart(647)
                                            SanityCheck.check { newNode.str.length > 0 }
Coverage.statementStart(648)
                                            node.childs[childIdx] = newNode
Coverage.statementStart(649)
                                            size++
Coverage.statementStart(650)
                                        } else {
Coverage.ifStart(651)
                                            onNotFound()
Coverage.statementStart(652)
                                        }
Coverage.statementStart(653)
                                        return
                                    }
Coverage.statementStart(654)
                                } else {
Coverage.ifStart(655)
                                    if (create) {
Coverage.ifStart(656)
                                        if (commonKey.length == key.length) {
Coverage.ifStart(657)
                                            var otherKey = childKey.substring(commonKey.length, childKey.length)
Coverage.statementStart(658)
                                            SanityCheck.check { otherKey.length > 0 }
Coverage.statementStart(659)
                                            var newNode = MyMapStringIntPatriciaTrieNodeN()
Coverage.statementStart(660)
                                            newNode.childs = arrayOf(node.childs[childIdx])
Coverage.statementStart(661)
                                            newNode.str = otherKey
Coverage.statementStart(662)
                                            newNode.arr = intArrayOf(0, node.arr[childCount + childIdx])
Coverage.statementStart(663)
                                            node.childs[childIdx] = newNode
Coverage.statementStart(664)
                                            node.arr[childCount + childIdx] = onCreate()
Coverage.statementStart(665)
                                            node.str = node.str.substring(0, childKeyStart) + commonKey + node.str.substring(childKeyEnd, node.str.length)
Coverage.statementStart(666)
                                            SanityCheck.check { node.str.length > commonKey.length || childCount == 1 }
Coverage.statementStart(667)
                                            for (j in childIdx + 1 until childCount) {
Coverage.forLoopStart(668)
                                                node.arr[j] -= otherKey.length
Coverage.statementStart(669)
                                                SanityCheck.check { node.arr[j] < node.str.length }
Coverage.statementStart(670)
                                            }
Coverage.statementStart(671)
                                        } else {
Coverage.ifStart(672)
                                            var otherKey = childKey.substring(commonKey.length, childKey.length)
Coverage.statementStart(673)
                                            SanityCheck.check { otherKey.length > 0 }
Coverage.statementStart(674)
                                            var newKey = key.substring(commonKey.length, key.length)
Coverage.statementStart(675)
                                            SanityCheck.check { newKey.length > 0 }
Coverage.statementStart(676)
                                            var newNode = MyMapStringIntPatriciaTrieNode2()
Coverage.statementStart(677)
                                            newNode.childs0 = node.childs[childIdx]
Coverage.statementStart(678)
                                            newNode.childs1 = null
Coverage.statementStart(679)
                                            newNode.str = otherKey + newKey
Coverage.statementStart(680)
                                            newNode.arr1 = otherKey.length
Coverage.statementStart(681)
                                            newNode.arr2 = node.arr[childCount + childIdx]
Coverage.statementStart(682)
                                            newNode.arr3 = onCreate()
Coverage.statementStart(683)
                                            node.childs[childIdx] = newNode
Coverage.statementStart(684)
                                            node.arr[childCount + childIdx] = undefinedValue
Coverage.statementStart(685)
                                            node.str = node.str.substring(0, childKeyStart) + commonKey + node.str.substring(childKeyEnd, node.str.length)
Coverage.statementStart(686)
                                            SanityCheck.check { node.str.length > commonKey.length || childCount == 1 }
Coverage.statementStart(687)
                                            for (j in childIdx + 1 until childCount) {
Coverage.forLoopStart(688)
                                                node.arr[j] -= otherKey.length
Coverage.statementStart(689)
                                                SanityCheck.check { node.arr[j] < node.str.length }
Coverage.statementStart(690)
                                            }
Coverage.statementStart(691)
                                        }
Coverage.statementStart(692)
                                        size++
Coverage.statementStart(693)
                                    } else {
Coverage.ifStart(694)
                                        onNotFound()
Coverage.statementStart(695)
                                    }
Coverage.statementStart(696)
                                    return
                                }
Coverage.statementStart(697)
                            }
Coverage.statementStart(698)
                        }
Coverage.statementStart(699)
                        if (create) {
Coverage.ifStart(700)
                            var childs = Array(childCount + 1) {
Coverage.statementStart(701)
                                val res: MyMapStringIntPatriciaTrieNode?
Coverage.statementStart(702)
                                if (it < childCount) {
Coverage.ifStart(703)
                                    res = node.childs[it]
Coverage.statementStart(704)
                                } else {
Coverage.ifStart(705)
                                    res = null
Coverage.statementStart(706)
                                }
Coverage.statementStart(707)
                                /*return*/ res
                            }
Coverage.statementStart(708)
                            var arr = IntArray(node.arr.size + 2)
Coverage.statementStart(709)
                            for (childIdx in 0 until childCount) {
Coverage.forLoopStart(710)
                                arr[childIdx] = node.arr[childIdx]
Coverage.statementStart(711)
                                arr[childCount + 1 + childIdx] = node.arr[childCount + childIdx]
Coverage.statementStart(712)
                            }
Coverage.statementStart(713)
                            arr[childCount] = node.str.length
Coverage.statementStart(714)
                            arr[childCount + childCount + 1] = onCreate()
Coverage.statementStart(715)
                            node.str += key
Coverage.statementStart(716)
                            node.childs = childs
Coverage.statementStart(717)
                            node.arr = arr
Coverage.statementStart(718)
                            size++
Coverage.statementStart(719)
                        } else {
Coverage.ifStart(720)
                            onNotFound()
Coverage.statementStart(721)
                        }
Coverage.statementStart(722)
                        return
                    }
                    else -> {
Coverage.whenCaseStart(723)
                        SanityCheck.check { false }
Coverage.statementStart(724)
                    }
                }
Coverage.statementStart(725)
            }
Coverage.statementStart(726)
        }
Coverage.statementStart(727)
    }
    inline operator fun get(key: String): Int? {
Coverage.funStart(728)
        var res: Int? = null
Coverage.statementStart(729)
        walkInternal(key, { undefinedValue }, {
Coverage.statementStart(730)
            res = it
Coverage.statementStart(731)
            /*return*/ it
        }, {}, false)
Coverage.statementStart(732)
        return res
    }
    inline operator fun set(key: String, value: Int) {
Coverage.funStart(733)
        walkInternal(key, { value }, { value }, {}, true)
Coverage.statementStart(734)
    }
    inline fun getOrCreate(key: String, crossinline onCreate: () -> Int): Int {
Coverage.funStart(735)
        var res = undefinedValue
Coverage.statementStart(736)
        walkInternal(key, {
Coverage.statementStart(737)
            res = onCreate()
Coverage.statementStart(738)
            /*return*/ res
        }, {
Coverage.statementStart(739)
            res = it
Coverage.statementStart(740)
            /*return*/ it
        }, {}, true)
Coverage.statementStart(741)
        return res
    }
    inline fun appendAssumeSorted(key: String, value: Int): Int {
Coverage.funStart(742)
        set(key, value)
Coverage.statementStart(743)
        return value
    }
    inline fun clear() {
Coverage.funStart(744)
        root = MyMapStringIntPatriciaTrieNodeN()
Coverage.statementStart(745)
        rootValue = undefinedValue
Coverage.statementStart(746)
        size = 0
Coverage.statementStart(747)
    }
    inline fun forEach(crossinline action: (String, Int) -> Unit) {
Coverage.funStart(748)
        var queue = mutableListOf<Pair<String, MyMapStringIntPatriciaTrieNode>>()
Coverage.statementStart(749)
        if (rootValue != undefinedValue) {
Coverage.ifStart(750)
            action("", rootValue)
Coverage.statementStart(751)
        }
Coverage.statementStart(752)
        val roottmp = root
Coverage.statementStart(753)
        when (roottmp) {
            is MyMapStringIntPatriciaTrieNode2 -> {
Coverage.whenCaseStart(755)
                var childKeyStart = 0
Coverage.statementStart(756)
                var childKeyEnd = roottmp.arr1
Coverage.statementStart(757)
                var childKey = roottmp.str.substring(childKeyStart, childKeyEnd)
Coverage.statementStart(758)
                if (roottmp.arr2 != undefinedValue) {
Coverage.ifStart(759)
                    action(childKey, roottmp.arr2)
Coverage.statementStart(760)
                }
Coverage.statementStart(761)
                if (roottmp.childs0 != null) {
Coverage.ifStart(762)
                    queue.add(Pair(childKey, roottmp.childs0!!))
Coverage.statementStart(763)
                }
Coverage.statementStart(764)
                childKeyStart = roottmp.arr1
Coverage.statementStart(765)
                childKeyEnd = roottmp.str.length
Coverage.statementStart(766)
                childKey = roottmp.str.substring(childKeyStart, childKeyEnd)
Coverage.statementStart(767)
                if (roottmp.arr3 != undefinedValue) {
Coverage.ifStart(768)
                    action(childKey, roottmp.arr3)
Coverage.statementStart(769)
                }
Coverage.statementStart(770)
                if (roottmp.childs1 != null) {
Coverage.ifStart(771)
                    queue.add(Pair(childKey, roottmp.childs1!!))
Coverage.statementStart(772)
                }
Coverage.statementStart(773)
            }
            is MyMapStringIntPatriciaTrieNodeN -> {
Coverage.whenCaseStart(774)
                var childCount = roottmp.childs.size
Coverage.statementStart(775)
                for (childIdx in 0 until childCount) {
Coverage.forLoopStart(776)
                    var childKeyStart = roottmp.arr[childIdx]
Coverage.statementStart(777)
                    var childKeyEnd: Int
Coverage.statementStart(778)
                    if (childIdx == childCount - 1) {
Coverage.ifStart(779)
                        childKeyEnd = roottmp.str.length
Coverage.statementStart(780)
                    } else {
Coverage.ifStart(781)
                        childKeyEnd = roottmp.arr[childIdx + 1]
Coverage.statementStart(782)
                    }
Coverage.statementStart(783)
                    var childKey = roottmp.str.substring(childKeyStart, childKeyEnd)
Coverage.statementStart(784)
                    if (roottmp.arr[childCount + childIdx] != undefinedValue) {
Coverage.ifStart(785)
                        action(childKey, roottmp.arr[childCount + childIdx])
Coverage.statementStart(786)
                    }
Coverage.statementStart(787)
                    if (roottmp.childs[childIdx] != null) {
Coverage.ifStart(788)
                        queue.add(Pair(childKey, roottmp.childs[childIdx]!!))
Coverage.statementStart(789)
                    }
Coverage.statementStart(790)
                }
Coverage.statementStart(791)
            }
            else -> {
Coverage.whenCaseStart(792)
                SanityCheck.check { false }
Coverage.statementStart(793)
            }
        }
Coverage.statementStart(794)
        while (queue.size > 0) {
Coverage.whileLoopStart(795)
            var current = queue.removeAt(0)
Coverage.statementStart(796)
            var prefix = current.first
Coverage.statementStart(797)
            val node = current.second
Coverage.statementStart(798)
            when (node) {
                is MyMapStringIntPatriciaTrieNode2 -> {
Coverage.whenCaseStart(800)
                    var childKeyStart = 0
Coverage.statementStart(801)
                    var childKeyEnd = node.arr1
Coverage.statementStart(802)
                    var childKey = prefix + node.str.substring(childKeyStart, childKeyEnd)
Coverage.statementStart(803)
                    if (node.arr2 != undefinedValue) {
Coverage.ifStart(804)
                        action(childKey, node.arr2)
Coverage.statementStart(805)
                    }
Coverage.statementStart(806)
                    if (node.childs0 != null) {
Coverage.ifStart(807)
                        queue.add(Pair(childKey, node.childs0!!))
Coverage.statementStart(808)
                    }
Coverage.statementStart(809)
                    childKeyStart = node.arr1
Coverage.statementStart(810)
                    childKeyEnd = node.str.length
Coverage.statementStart(811)
                    childKey = prefix + node.str.substring(childKeyStart, childKeyEnd)
Coverage.statementStart(812)
                    if (node.arr3 != undefinedValue) {
Coverage.ifStart(813)
                        action(childKey, node.arr3)
Coverage.statementStart(814)
                    }
Coverage.statementStart(815)
                    if (node.childs1 != null) {
Coverage.ifStart(816)
                        queue.add(Pair(childKey, node.childs1!!))
Coverage.statementStart(817)
                    }
Coverage.statementStart(818)
                }
                is MyMapStringIntPatriciaTrieNodeN -> {
Coverage.whenCaseStart(819)
                    var childCount = node.childs.size
Coverage.statementStart(820)
                    for (childIdx in 0 until childCount) {
Coverage.forLoopStart(821)
                        var childKeyStart = node.arr[childIdx]
Coverage.statementStart(822)
                        var childKeyEnd: Int
Coverage.statementStart(823)
                        if (childIdx == childCount - 1) {
Coverage.ifStart(824)
                            childKeyEnd = node.str.length
Coverage.statementStart(825)
                        } else {
Coverage.ifStart(826)
                            childKeyEnd = node.arr[childIdx + 1]
Coverage.statementStart(827)
                        }
Coverage.statementStart(828)
                        var childKey = prefix + node.str.substring(childKeyStart, childKeyEnd)
Coverage.statementStart(829)
                        if (node.arr[childCount + childIdx] != undefinedValue) {
Coverage.ifStart(830)
                            action(childKey, node.arr[childCount + childIdx])
Coverage.statementStart(831)
                        }
Coverage.statementStart(832)
                        if (node.childs[childIdx] != null) {
Coverage.ifStart(833)
                            queue.add(Pair(childKey, node.childs[childIdx]!!))
Coverage.statementStart(834)
                        }
Coverage.statementStart(835)
                    }
Coverage.statementStart(836)
                }
                else -> {
Coverage.whenCaseStart(837)
                    SanityCheck.check { false }
Coverage.statementStart(838)
                }
            }
Coverage.statementStart(839)
        }
Coverage.statementStart(840)
    }
    fun safeToFile(filename: String) {
Coverage.funStart(841)
        var queue = mutableListOf<MyMapStringIntPatriciaTrieNode>()
Coverage.statementStart(842)
        File(filename).dataOutputStream { out ->
Coverage.statementStart(843)
            out.writeInt(rootValue)
Coverage.statementStart(844)
            queue.add(root)
Coverage.statementStart(845)
            while (queue.size > 0) {
Coverage.whileLoopStart(846)
                val node = queue.removeAt(0)
Coverage.statementStart(847)
                when (node) {
                    is MyMapStringIntPatriciaTrieNode2 -> {
Coverage.whenCaseStart(849)
                        out.writeShort(2)
Coverage.statementStart(850)
                        for (c in node.str) {
Coverage.forLoopStart(851)
                            out.writeChar(c.toInt())
Coverage.statementStart(852)
                        }
Coverage.statementStart(853)
                        out.writeChar(0)
Coverage.statementStart(854)
                        out.writeInt(0)
Coverage.statementStart(855)
                        out.writeInt(node.arr2)
Coverage.statementStart(856)
                        if (node.childs0 == null) {
Coverage.ifStart(857)
                            out.writeChar(0)
Coverage.statementStart(858)
                        } else {
Coverage.ifStart(859)
                            out.writeChar(1)
Coverage.statementStart(860)
                            queue.add(node.childs0!!)
Coverage.statementStart(861)
                        }
Coverage.statementStart(862)
                        out.writeInt(node.arr1)
Coverage.statementStart(863)
                        out.writeInt(node.arr3)
Coverage.statementStart(864)
                        if (node.childs1 == null) {
Coverage.ifStart(865)
                            out.writeChar(0)
Coverage.statementStart(866)
                        } else {
Coverage.ifStart(867)
                            out.writeChar(1)
Coverage.statementStart(868)
                            queue.add(node.childs1!!)
Coverage.statementStart(869)
                        }
Coverage.statementStart(870)
                    }
                    is MyMapStringIntPatriciaTrieNodeN -> {
Coverage.whenCaseStart(871)
                        var childCount = node.childs.size
Coverage.statementStart(872)
                        out.writeShort(childCount)
Coverage.statementStart(873)
                        for (c in node.str) {
Coverage.forLoopStart(874)
                            out.writeChar(c.toInt())
Coverage.statementStart(875)
                        }
Coverage.statementStart(876)
                        out.writeChar(0)
Coverage.statementStart(877)
                        for (i in 0 until childCount) {
Coverage.forLoopStart(878)
                            out.writeInt(node.arr[i])
Coverage.statementStart(879)
                            out.writeInt(node.arr[childCount + i])
Coverage.statementStart(880)
                            if (node.childs[i] == null) {
Coverage.ifStart(881)
                                out.writeChar(0)
Coverage.statementStart(882)
                            } else {
Coverage.ifStart(883)
                                out.writeChar(1)
Coverage.statementStart(884)
                                queue.add(node.childs[i]!!)
Coverage.statementStart(885)
                            }
Coverage.statementStart(886)
                        }
Coverage.statementStart(887)
                    }
                    else -> {
Coverage.whenCaseStart(888)
                        SanityCheck.check { false }
Coverage.statementStart(889)
                    }
                }
Coverage.statementStart(890)
            }
Coverage.statementStart(891)
        }
Coverage.statementStart(892)
    }
    fun loadFromFile(filename: String) {
Coverage.funStart(893)
        var queue = mutableListOf<Pair<Int, MyMapStringIntPatriciaTrieNode>>()
Coverage.statementStart(894)
        File(filename).dataInputStream { fis ->
Coverage.statementStart(895)
            rootValue = fis.readInt()
Coverage.statementStart(896)
            if (rootValue != undefinedValue) {
Coverage.ifStart(897)
                size++
Coverage.statementStart(898)
            }
Coverage.statementStart(899)
            var first = true
Coverage.statementStart(900)
            while (queue.size > 0 || first) {
Coverage.whileLoopStart(901)
                var childCount = fis.readShort()
Coverage.statementStart(902)
                val node: MyMapStringIntPatriciaTrieNode
Coverage.statementStart(903)
                if (childCount.toInt() == 2) {
Coverage.ifStart(904)
                    node = MyMapStringIntPatriciaTrieNode2()
Coverage.statementStart(905)
                } else {
Coverage.ifStart(906)
                    node = MyMapStringIntPatriciaTrieNodeN()
Coverage.statementStart(907)
                    node.childs = Array<MyMapStringIntPatriciaTrieNode?>(childCount.toInt()) { null }
Coverage.statementStart(908)
                    node.arr = IntArray(childCount * 2)
Coverage.statementStart(909)
                }
Coverage.statementStart(910)
                if (first) {
Coverage.ifStart(911)
                    root = node
Coverage.statementStart(912)
                    first = false
Coverage.statementStart(913)
                } else {
Coverage.ifStart(914)
                    var current = queue.removeAt(0)
Coverage.statementStart(915)
                    var tmp = current.second
Coverage.statementStart(916)
                    when (tmp) {
                        is MyMapStringIntPatriciaTrieNode2 -> {
Coverage.whenCaseStart(918)
                            if (current.first == 0) {
Coverage.ifStart(919)
                                tmp.childs0 = node
Coverage.statementStart(920)
                            } else {
Coverage.ifStart(921)
                                tmp.childs1 = node
Coverage.statementStart(922)
                            }
Coverage.statementStart(923)
                        }
                        is MyMapStringIntPatriciaTrieNodeN -> {
Coverage.whenCaseStart(924)
                            tmp.childs[current.first] = node
Coverage.statementStart(925)
                        }
                        else -> {
Coverage.whenCaseStart(926)
                            SanityCheck.check { false }
Coverage.statementStart(927)
                        }
                    }
Coverage.statementStart(928)
                }
Coverage.statementStart(929)
                size = 0
Coverage.statementStart(930)
                var c = fis.readChar()
Coverage.statementStart(931)
                while (c.toInt() != 0) {
Coverage.whileLoopStart(932)
                    node.str += c
Coverage.statementStart(933)
                    c = fis.readChar()
Coverage.statementStart(934)
                }
Coverage.statementStart(935)
                when (node) {
                    is MyMapStringIntPatriciaTrieNode2 -> {
Coverage.whenCaseStart(937)
                        fis.readInt()
Coverage.statementStart(938)
                        node.arr2 = fis.readInt()
Coverage.statementStart(939)
                        if (node.arr2 != undefinedValue) {
Coverage.ifStart(940)
                            size++
Coverage.statementStart(941)
                        }
Coverage.statementStart(942)
                        var x = fis.readChar()
Coverage.statementStart(943)
                        if (x.toInt() == 1) {
Coverage.ifStart(944)
                            queue.add(Pair(0, node))
Coverage.statementStart(945)
                        }
Coverage.statementStart(946)
                        node.arr1 = fis.readInt()
Coverage.statementStart(947)
                        node.arr3 = fis.readInt()
Coverage.statementStart(948)
                        if (node.arr3 != undefinedValue) {
Coverage.ifStart(949)
                            size++
Coverage.statementStart(950)
                        }
Coverage.statementStart(951)
                        x = fis.readChar()
Coverage.statementStart(952)
                        if (x.toInt() == 1) {
Coverage.ifStart(953)
                            queue.add(Pair(1, node))
Coverage.statementStart(954)
                        }
Coverage.statementStart(955)
                    }
                    is MyMapStringIntPatriciaTrieNodeN -> {
Coverage.whenCaseStart(956)
                        for (i in 0 until childCount) {
Coverage.forLoopStart(957)
                            node.arr[i] = fis.readInt()
Coverage.statementStart(958)
                            node.arr[childCount + i] = fis.readInt()
Coverage.statementStart(959)
                            if (node.arr[childCount + i] != undefinedValue) {
Coverage.ifStart(960)
                                size++
Coverage.statementStart(961)
                            }
Coverage.statementStart(962)
                            var x = fis.readChar()
Coverage.statementStart(963)
                            if (x.toInt() == 1) {
Coverage.ifStart(964)
                                queue.add(Pair(i, node))
Coverage.statementStart(965)
                            }
Coverage.statementStart(966)
                        }
Coverage.statementStart(967)
                    }
                    else -> {
Coverage.whenCaseStart(968)
                        SanityCheck.check { false }
Coverage.statementStart(969)
                    }
                }
Coverage.statementStart(970)
            }
Coverage.statementStart(971)
        }
Coverage.statementStart(972)
    }
    fun debugInternal(prefix: String, node: MyMapStringIntPatriciaTrieNode, depth: Int) {
Coverage.funStart(973)
        SanityCheck {
Coverage.statementStart(974)
            when (node) {
                is MyMapStringIntPatriciaTrieNode2 -> {
Coverage.whenCaseStart(976)
                    var i = 0
Coverage.statementStart(977)
                    if (node.childs0 != null) {
Coverage.ifStart(978)
                        i++
Coverage.statementStart(979)
                    }
Coverage.statementStart(980)
                    if (node.childs1 != null) {
Coverage.ifStart(981)
                        i++
Coverage.statementStart(982)
                    }
Coverage.statementStart(983)
                    println("debug ${prefix}${node.str}:2@${node.str.length}-${depth}+$i,${2 - i}#2")
Coverage.statementStart(984)
                    if (node.childs0 != null) {
Coverage.ifStart(985)
                        debugInternal(prefix + " ", node.childs0!!, depth + 1)
Coverage.statementStart(986)
                    }
Coverage.statementStart(987)
                    if (node.childs1 != null) {
Coverage.ifStart(988)
                        debugInternal(prefix + " ", node.childs1!!, depth + 1)
Coverage.statementStart(989)
                    }
Coverage.statementStart(990)
                }
                is MyMapStringIntPatriciaTrieNodeN -> {
Coverage.whenCaseStart(991)
                    var i = 0
Coverage.statementStart(992)
                    for (c in node.childs) {
Coverage.forLoopStart(993)
                        if (c != null) {
Coverage.ifStart(994)
                            i++
Coverage.statementStart(995)
                        }
Coverage.statementStart(996)
                    }
Coverage.statementStart(997)
                    println("debug ${prefix}${node.str}:${node.childs.size}@${node.str.length}-${depth}+$i,${node.childs.size - i}#N")
Coverage.statementStart(998)
                    for (c in node.childs) {
Coverage.forLoopStart(999)
                        if (c != null) {
Coverage.ifStart(1000)
                            debugInternal(prefix + " ", c, depth + 1)
Coverage.statementStart(1001)
                        }
Coverage.statementStart(1002)
                    }
Coverage.statementStart(1003)
                }
                else -> {
Coverage.whenCaseStart(1004)
                    SanityCheck.check { false }
Coverage.statementStart(1005)
                }
            }
Coverage.statementStart(1006)
        }
Coverage.statementStart(1007)
    }
    fun debug() = debugInternal("", root, 0)
}
