package lupos.s00misc
import lupos.s00misc.Coverage
class MyMapStringIntPatriciaTrieDouble() {
    val undefinedValue = -1
    var root: MyMapStringIntPatriciaTrieNode = MyMapStringIntPatriciaTrieNode()
    var rootValue: Int = undefinedValue
    var size: Int = 0
        get() = allOutNodes.size
    val allNodes = MyListGeneric<MyMapStringIntPatriciaTrieNode>()
    val allOutNodes = MyListInt()//index in allNodes
    val allOutOffsets = MyListInt()//index in allNodesChilds
    init {
Coverage.funStart(172)
        allNodes.add(root)
Coverage.statementStart(173)
    }
    class MyMapStringIntPatriciaTrieNode() {
        var parent = 0
        var str = ""
        var data = IntArray(0) /* offsets in str, child-values, child-pointers  */
    }
    operator fun get(key: Int): String {
Coverage.funStart(174)
        if (key == rootValue) {
Coverage.ifStart(175)
            return ""
        }
Coverage.statementStart(176)
        var nodeIdx = allOutNodes[key]
Coverage.statementStart(177)
        var childIdx = allOutOffsets[key]
Coverage.statementStart(178)
        var res = ""
Coverage.statementStart(179)
        var node = allNodes[nodeIdx]
Coverage.statementStart(180)
        while (true) {
Coverage.whileLoopStart(181)
            val childKeyStart = node.data[childIdx]
Coverage.statementStart(182)
            val childKeyEnd: Int
Coverage.statementStart(183)
            val childCount = node.data.size / 3
Coverage.statementStart(184)
            if (childIdx == childCount - 1) {
Coverage.ifStart(185)
                childKeyEnd = node.str.length
Coverage.statementStart(186)
            } else {
Coverage.ifStart(187)
                childKeyEnd = node.data[childIdx + 1]
Coverage.statementStart(188)
            }
Coverage.statementStart(189)
            res = node.str.substring(childKeyStart, childKeyEnd) + res
Coverage.statementStart(190)
            if (nodeIdx == node.parent) {
Coverage.ifStart(191)
                return res
            }
Coverage.statementStart(192)
            childIdx = 0
Coverage.statementStart(193)
            val parent = allNodes[node.parent]
Coverage.statementStart(194)
            val parentChildCount = parent.data.size / 3
Coverage.statementStart(195)
            val parentChildCount2 = parentChildCount + parentChildCount
Coverage.statementStart(196)
            while (parent.data[parentChildCount2 + childIdx] != nodeIdx) {
Coverage.whileLoopStart(197)
                childIdx++
Coverage.statementStart(198)
            }
Coverage.statementStart(199)
            nodeIdx = node.parent
Coverage.statementStart(200)
            node = parent
Coverage.statementStart(201)
        }
Coverage.statementStart(202)
    }
    fun debug() {
Coverage.funStart(203)
        SanityCheck {
Coverage.statementStart(204)
            println("debug ->")
Coverage.statementStart(205)
            if (rootValue != undefinedValue) {
Coverage.ifStart(206)
                println("debug rootValue -> $rootValue")
Coverage.statementStart(207)
            }
Coverage.statementStart(208)
            val it = allNodes.iterator()
Coverage.statementStart(209)
            while (it.hasNext()) {
Coverage.whileLoopStart(210)
                val node = it.next()
Coverage.statementStart(211)
                println("debug ${node.str} ${node.data.map { it }} ${node.parent}")
Coverage.statementStart(212)
            }
Coverage.statementStart(213)
            println("debug --")
Coverage.statementStart(214)
            var it2 = allOutNodes.iterator()
Coverage.statementStart(215)
            var it3 = allOutOffsets.iterator()
Coverage.statementStart(216)
            while (it2.hasNext()) {
Coverage.whileLoopStart(217)
                println("debug ${it2.next()} ${it3.next()}")
Coverage.statementStart(218)
            }
Coverage.statementStart(219)
            println("debug <-")
Coverage.statementStart(220)
        }
Coverage.statementStart(221)
    }
    fun walkInternal(_key: String, create: Boolean): Int {
Coverage.funStart(222)
        if (_key == "") {
Coverage.ifStart(223)
            if (create && rootValue == undefinedValue) {
Coverage.ifStart(224)
                rootValue = allOutNodes.size
Coverage.statementStart(225)
                allOutNodes.add(-1)
Coverage.statementStart(226)
                allOutOffsets.add(-1)
Coverage.statementStart(227)
            }
Coverage.statementStart(228)
            return rootValue
        } else {
Coverage.statementStart(229)
            var key = _key
Coverage.statementStart(230)
            var nextNode = root
Coverage.statementStart(231)
            var nextNodeIdx = 0
Coverage.statementStart(232)
            loop@ while (true) {
Coverage.whileLoopStart(233)
                val node = nextNode
Coverage.statementStart(234)
                val nodeIdx = nextNodeIdx
Coverage.statementStart(235)
                var childCount = node.data.size / 3
Coverage.statementStart(236)
                for (childIdx in 0 until childCount) {
Coverage.forLoopStart(237)
                    if (node.str[node.data[childIdx]] == key[0]) {
Coverage.ifStart(238)
                        val childNodeIdx = node.data[childCount + childCount + childIdx]
Coverage.statementStart(239)
                        val childNode = allNodes[childNodeIdx]
Coverage.statementStart(240)
                        var childKeyStart = node.data[childIdx]
Coverage.statementStart(241)
                        var childKeyEnd: Int
Coverage.statementStart(242)
                        if (childIdx == childCount - 1) {
Coverage.ifStart(243)
                            childKeyEnd = node.str.length
Coverage.statementStart(244)
                        } else {
Coverage.ifStart(245)
                            childKeyEnd = node.data[childIdx + 1]
Coverage.statementStart(246)
                        }
Coverage.statementStart(247)
                        var childKey = node.str.substring(childKeyStart, childKeyEnd)
Coverage.statementStart(248)
                        var commonKey = key.commonPrefixWith(childKey)
Coverage.statementStart(249)
                        if (commonKey.length == key.length && commonKey.length == childKey.length) {
Coverage.ifStart(250)
                            var result = node.data[childCount + childIdx]
Coverage.statementStart(251)
                            if (result == undefinedValue) {
Coverage.ifStart(252)
                                result = allOutNodes.size
Coverage.statementStart(253)
                                node.data[childCount + childIdx] = result
Coverage.statementStart(254)
                                allOutNodes.add(nodeIdx)
Coverage.statementStart(255)
                                allOutOffsets.add(childIdx)
Coverage.statementStart(256)
                            }
Coverage.statementStart(257)
                            return result
                        } else if (commonKey.length == childKey.length) {
Coverage.statementStart(258)
                            if (childNodeIdx != 0) {
Coverage.ifStart(259)
                                nextNode = childNode
Coverage.statementStart(260)
                                nextNodeIdx = childNodeIdx
Coverage.statementStart(261)
                                key = key.substring(childKey.length, key.length)
Coverage.statementStart(262)
                                continue@loop
                            } else {
Coverage.statementStart(263)
                                var result = undefinedValue
Coverage.statementStart(264)
                                if (create) {
Coverage.ifStart(265)
                                    //previous key was a prefix of the new key
Coverage.statementStart(266)
                                    val newNode = MyMapStringIntPatriciaTrieNode()
Coverage.statementStart(267)
                                    val newNodeIdx = allNodes.size
Coverage.statementStart(268)
                                    allNodes.add(newNode)
Coverage.statementStart(269)
                                    result = allOutNodes.size
Coverage.statementStart(270)
                                    allOutNodes.add(newNodeIdx)
Coverage.statementStart(271)
                                    allOutOffsets.add(0)
Coverage.statementStart(272)
                                    newNode.parent = nodeIdx
Coverage.statementStart(273)
                                    newNode.data = intArrayOf(0, result, 0)
Coverage.statementStart(274)
                                    newNode.str = key.substring(commonKey.length, key.length)
Coverage.statementStart(275)
                                    node.data[childCount + childCount + childIdx] = newNodeIdx
Coverage.statementStart(276)
                                    size++
Coverage.statementStart(277)
                                }
Coverage.statementStart(278)
                                return result
                            }
Coverage.statementStart(279)
                        } else {
Coverage.ifStart(280)
                            var result = undefinedValue
Coverage.statementStart(281)
                            if (create) {
Coverage.ifStart(282)
                                val otherKey = childKey.substring(commonKey.length, childKey.length)
Coverage.statementStart(283)
                                val otherChildIdx = node.data[childCount + childCount + childIdx]
Coverage.statementStart(284)
                                val otherResult = node.data[childCount + childIdx]
Coverage.statementStart(285)
                                val newNode = MyMapStringIntPatriciaTrieNode()
Coverage.statementStart(286)
                                val newNodeIdx = allNodes.size
Coverage.statementStart(287)
                                newNode.parent = nodeIdx
Coverage.statementStart(288)
                                allNodes.add(newNode)
Coverage.statementStart(289)
                                result = allOutNodes.size
Coverage.statementStart(290)
                                if (otherChildIdx != 0) {
Coverage.ifStart(291)
                                    val otherChild = allNodes[otherChildIdx]
Coverage.statementStart(292)
                                    otherChild.parent = newNodeIdx
Coverage.statementStart(293)
                                }
Coverage.statementStart(294)
                                if (commonKey.length == key.length) {
Coverage.ifStart(295)
                                    allOutNodes.add(nodeIdx)
Coverage.statementStart(296)
                                    allOutOffsets.add(childIdx)
Coverage.statementStart(297)
                                    //new key is a prefix of the old key
Coverage.statementStart(298)
                                    newNode.str = otherKey
Coverage.statementStart(299)
                                    newNode.data = intArrayOf(0, otherResult, otherChildIdx)
Coverage.statementStart(300)
                                    if (otherResult != undefinedValue) {
Coverage.ifStart(301)
                                        allOutNodes[otherResult] = newNodeIdx
Coverage.statementStart(302)
                                        allOutOffsets[otherResult] = 0
Coverage.statementStart(303)
                                    }
Coverage.statementStart(304)
                                    node.data[childCount + childCount + childIdx] = newNodeIdx
Coverage.statementStart(305)
                                    node.data[childCount + childIdx] = result
Coverage.statementStart(306)
                                    node.str = node.str.substring(0, childKeyStart) + commonKey + node.str.substring(childKeyEnd, node.str.length)
Coverage.statementStart(307)
                                    for (j in childIdx + 1 until childCount) {
Coverage.forLoopStart(308)
                                        node.data[j] -= otherKey.length
Coverage.statementStart(309)
                                    }
Coverage.statementStart(310)
                                } else {
Coverage.ifStart(311)
                                    allOutNodes.add(newNodeIdx)
Coverage.statementStart(312)
                                    allOutOffsets.add(0)
Coverage.statementStart(313)
                                    val newKey = key.substring(commonKey.length, key.length)
Coverage.statementStart(314)
                                    //both keys share a common prefix - both need to be changed
Coverage.statementStart(315)
                                    newNode.str = newKey + otherKey
Coverage.statementStart(316)
                                    newNode.data = intArrayOf(0, newKey.length, result, otherResult, 0, otherChildIdx)
Coverage.statementStart(317)
                                    if (otherResult != undefinedValue) {
Coverage.ifStart(318)
                                        allOutNodes[otherResult] = newNodeIdx
Coverage.statementStart(319)
                                        allOutOffsets[otherResult] = 1
Coverage.statementStart(320)
                                    }
Coverage.statementStart(321)
                                    node.data[childCount + childCount + childIdx] = newNodeIdx
Coverage.statementStart(322)
                                    node.data[childCount + childIdx] = undefinedValue
Coverage.statementStart(323)
                                    node.str = node.str.substring(0, childKeyStart) + commonKey + node.str.substring(childKeyEnd, node.str.length)
Coverage.statementStart(324)
                                    for (j in childIdx + 1 until childCount) {
Coverage.forLoopStart(325)
                                        node.data[j] -= otherKey.length
Coverage.statementStart(326)
                                    }
Coverage.statementStart(327)
                                }
Coverage.statementStart(328)
                                size++
Coverage.statementStart(329)
                            }
Coverage.statementStart(330)
                            return result
                        }
Coverage.statementStart(331)
                    }
Coverage.statementStart(332)
                }
Coverage.statementStart(333)
                var result = undefinedValue
Coverage.statementStart(334)
                if (create) {
Coverage.ifStart(335)
                    result = allOutNodes.size
Coverage.statementStart(336)
                    allOutNodes.add(nodeIdx)
Coverage.statementStart(337)
                    allOutOffsets.add(childCount)
Coverage.statementStart(338)
                    val data = IntArray(node.data.size + 3)
Coverage.statementStart(339)
                    for (i in 0 until childCount) {
Coverage.forLoopStart(340)
                        data[i] = node.data[i]
Coverage.statementStart(341)
                        data[childCount + 1 + i] = node.data[childCount + i]
Coverage.statementStart(342)
                        data[childCount + childCount + 2 + i] = node.data[childCount + childCount + i]
Coverage.statementStart(343)
                    }
Coverage.statementStart(344)
                    data[childCount] = node.str.length
Coverage.statementStart(345)
                    data[childCount + childCount + 1] = result
Coverage.statementStart(346)
                    node.data = data
Coverage.statementStart(347)
                    node.str += key
Coverage.statementStart(348)
                    size++
Coverage.statementStart(349)
                }
Coverage.statementStart(350)
                return result
            }
Coverage.statementStart(351)
        }
Coverage.statementStart(352)
    }
    operator fun get(key: String): Int? {
Coverage.funStart(353)
        var res = walkInternal(key, false)
Coverage.statementStart(354)
        if (res == undefinedValue) {
Coverage.ifStart(355)
            return null
        }
Coverage.statementStart(356)
        return res
    }
    operator fun set(key: String, value: Int) {
Coverage.funStart(357)
        throw Exception("not implemented")
    }
    fun getOrCreate(key: String): Int {
Coverage.funStart(358)
        var list = mutableListOf<String>()
Coverage.statementStart(359)
        SanityCheck {
Coverage.statementStart(360)
            for (i in 0 until allOutNodes.size) {
Coverage.forLoopStart(361)
                list.add(this[i])
Coverage.statementStart(362)
            }
Coverage.statementStart(363)
        }
Coverage.statementStart(364)
        var res = walkInternal(key, true)
Coverage.statementStart(365)
        SanityCheck {
Coverage.statementStart(366)
            //debug()
Coverage.statementStart(367)
            if (res == list.size) {
Coverage.ifStart(368)
                list.add(key)
Coverage.statementStart(369)
            }
Coverage.statementStart(370)
            for (i in 0 until allOutNodes.size) {
Coverage.forLoopStart(371)
                var tmp = this[i]
Coverage.statementStart(372)
                SanityCheck.check({ tmp == list[i] }, { "old value changed ${list[i]} -> ${tmp}" })
Coverage.statementStart(373)
                SanityCheck.check({ this[tmp] == i }, { "error in path .. $tmp $i ${this[tmp]}" })
Coverage.statementStart(374)
            }
Coverage.statementStart(375)
        }
Coverage.statementStart(376)
        return res
    }
    fun appendAssumeSorted(key: String, value: Int): Int {
Coverage.funStart(377)
        return getOrCreate(key)
    }
    fun clear() {
Coverage.funStart(378)
        root = MyMapStringIntPatriciaTrieNode()
Coverage.statementStart(379)
        rootValue = undefinedValue
Coverage.statementStart(380)
        allNodes.clear()
Coverage.statementStart(381)
        allNodes.add(root)
Coverage.statementStart(382)
        allOutNodes.clear()
Coverage.statementStart(383)
        allOutOffsets.clear()
Coverage.statementStart(384)
    }
    fun safeToFile(filename: String) {
Coverage.funStart(385)
        File(filename).dataOutputStream { out ->
Coverage.statementStart(386)
            out.writeInt(rootValue)
Coverage.statementStart(387)
            out.writeInt(allNodes.size)
Coverage.statementStart(388)
            val nodeIterator = allNodes.iterator()
Coverage.statementStart(389)
            while (nodeIterator.hasNext()) {
Coverage.whileLoopStart(390)
                val node = nodeIterator.next()
Coverage.statementStart(391)
                for (c in node.str) {
Coverage.forLoopStart(392)
                    out.writeChar(c.toInt())
Coverage.statementStart(393)
                }
Coverage.statementStart(394)
                out.writeChar(0)
Coverage.statementStart(395)
                out.writeShort(node.data.size)
Coverage.statementStart(396)
                for (i in 0 until node.data.size) {
Coverage.forLoopStart(397)
                    out.writeInt(node.data[i])
Coverage.statementStart(398)
                }
Coverage.statementStart(399)
            }
Coverage.statementStart(400)
        }
Coverage.statementStart(401)
    }
    fun loadFromFile(filename: String) {
Coverage.funStart(402)
        File(filename).dataInputStream { fis ->
Coverage.statementStart(403)
            rootValue = fis.readInt()
Coverage.statementStart(404)
            if (rootValue != undefinedValue) {
Coverage.ifStart(405)
                size++
Coverage.statementStart(406)
            }
Coverage.statementStart(407)
            val allNodesSize = fis.readInt()
Coverage.statementStart(408)
            for (counter in 0 until allNodesSize) {
Coverage.forLoopStart(409)
                var node: MyMapStringIntPatriciaTrieNode
Coverage.statementStart(410)
                if (allNodes.size < counter) {
Coverage.ifStart(411)
                    node = MyMapStringIntPatriciaTrieNode()
Coverage.statementStart(412)
                    allNodes[counter] = node
Coverage.statementStart(413)
                } else {
Coverage.ifStart(414)
                    node = allNodes[counter]
Coverage.statementStart(415)
                }
Coverage.statementStart(416)
                var c = fis.readChar()
Coverage.statementStart(417)
                while (c.toInt() != 0) {
Coverage.whileLoopStart(418)
                    node.str += c
Coverage.statementStart(419)
                    c = fis.readChar()
Coverage.statementStart(420)
                }
Coverage.statementStart(421)
                val nodeDataSize = fis.readShort()
Coverage.statementStart(422)
                node.data = IntArray(nodeDataSize.toInt())
Coverage.statementStart(423)
                val valueOffset = nodeDataSize / 3
Coverage.statementStart(424)
                val childOffset = valueOffset * 2
Coverage.statementStart(425)
                for (i in 0 until nodeDataSize) {
Coverage.forLoopStart(426)
                    val value = fis.readInt()
Coverage.statementStart(427)
                    node.data[i] = value
Coverage.statementStart(428)
                    if (i >= valueOffset) {
Coverage.ifStart(429)
                        if (i < childOffset) {
Coverage.ifStart(430)
                            while (value > allOutNodes.size) {
Coverage.whileLoopStart(431)
                                allOutNodes.add(0)
Coverage.statementStart(432)
                                allOutOffsets.add(0)
Coverage.statementStart(433)
                            }
Coverage.statementStart(434)
                            if (value != undefinedValue) {
Coverage.ifStart(435)
                                allOutNodes[value] = counter
Coverage.statementStart(436)
                                allOutOffsets[value] = i
Coverage.statementStart(437)
                            }
Coverage.statementStart(438)
                        } else {
Coverage.ifStart(439)
                            while (value >= allNodes.size) {
Coverage.whileLoopStart(440)
                                allNodes.add(MyMapStringIntPatriciaTrieNode())
Coverage.statementStart(441)
                            }
Coverage.statementStart(442)
                            if (value != undefinedValue) {
Coverage.ifStart(443)
                                allNodes[value].parent = counter
Coverage.statementStart(444)
                            }
Coverage.statementStart(445)
                        }
Coverage.statementStart(446)
                    }
Coverage.statementStart(447)
                }
Coverage.statementStart(448)
            }
Coverage.statementStart(449)
        }
Coverage.statementStart(450)
        root = allNodes[0]
Coverage.statementStart(451)
    }
}
