package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s05tripleStore.*

class ColumnIteratorStore3c(val values: MyListValue) : ColumnIterator() {
    var counterPrimary: Int
    var counterSecondary: Int
    var counterTerniary: Int
    var index = 5

    init {
        if (values.size > 4) {
            println("CCC 0 c0")
            println("CCC 1 v0")
            println("CCC 2 c1")
            println("CCC 3 v1")
            println("CCC 4 c2")
            counterPrimary = values[0] - 1
            counterSecondary = values[2] - 1
            counterTerniary = values[4] - 1
            next = {
                println("CCC $index v2")
                var res: Value? = values[index]
                index++
                if (counterTerniary == 0) {
                    if (counterSecondary == 0) {
                        if (counterPrimary == 0) {
                            close()
                        } else {
                            counterPrimary--
                            println("CCC ${index} v0")
                            println("CCC ${index + 1} c1")
                            println("CCC ${index + 2} v1")
                            println("CCC ${index + 3} c2")
                            counterSecondary = values[index + 1] - 1
                            counterTerniary = values[index + 3] - 1
                            index += 4
                        }
                    } else {
                        counterSecondary--
                        println("CCC ${index} v1")
                        println("CCC ${index + 1} c2")
                        counterTerniary = values[index + 1] - 1
                        index += 2
                    }
                } else {
                    counterTerniary--
                }
/*return*/res
            }
        } else {
            counterPrimary = 0
            counterSecondary = 0
            counterTerniary = 0
        }
    }
}
