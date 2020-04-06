package lupos.s03resultRepresentation

object PatriciaTrieTest {
    operator fun invoke(data: List<String>) {
        val dictionary = PatriciaTrie()
        val input = data.distinct()
        for (keyIndex in 0 until input.size) {
            val key = input[keyIndex]
            val value = dictionary.insert(key)
            require(value == keyIndex, { "a $value ${keyIndex}" })
            for (keyIndex1 in 0 until keyIndex + 1) {
                val key = input[keyIndex1]
                val value = dictionary.find(key)
                require(value == keyIndex1, { "b $value $keyIndex1" })
            }
            val values = dictionary.values()
            require(values.size == keyIndex + 1, { "d ${values.size} ${keyIndex} $values" })
            for (key in values) {
                require(input[key.second] == key.first, { "c ${input[key.second]} ${key.first}" })
            }
        }
    }
}
