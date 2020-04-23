package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s05tripleStore.*

class ColumnIteratorStore2b(val values: MyListValue, start: Int) : ColumnIterator() {
    var counterSecondary: Int
    var counterTerniary: Int
    var index = start + 3

    init {
        println("_BB ${index - 3} c1")
        println("_BB ${index - 2} v1")
        println("_BB ${index - 1} c2")
        counterSecondary = values[index - 3] - 1
        counterTerniary = values[index - 1] - 1
        next = {
            println("_BB ${index} v2")
            var res: Value? = values[index]
            index++
            if (counterTerniary == 0) {
                if (counterSecondary == 0) {
                    close()
                } else {
                    counterSecondary--
                    println("_BB ${index} v1")
                    println("_BB ${index + 1} c2")
                    counterTerniary = values[index + 1] - 1
                    index += 2
                }
            } else {
                counterTerniary--
            }
/*return*/res
        }
    }
}
