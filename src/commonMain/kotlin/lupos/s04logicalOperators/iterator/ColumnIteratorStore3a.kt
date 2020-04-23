package lupos.s04logicalOperators.iterator

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s05tripleStore.*

class ColumnIteratorStore3a(val values: MyListValue) : ColumnIterator() {
    var counterPrimary: Int
    var counterSecondary: Int
    var counterTerniary: Int
    var index = 5
    var value = values[1]

    init {
        if (values.size > 4) {
            println("AAA 0 c0")
            println("AAA 1 v0")
            println("AAA 2 c1")
            println("AAA 3 v1")
            println("AAA 4 c2")
            counterPrimary = values[0] - 1
            counterSecondary = values[2] - 1
            counterTerniary = values[4] - 1
            next = {
                var res: Value? = value
                println("AAA $index v2")
                index++
                if (counterTerniary == 0) {
                    if (counterSecondary == 0) {
                        if (counterPrimary == 0) {
                            close()
                        } else {
                            counterPrimary--
                            println("AAA ${index} v0")
                            println("AAA ${index + 1} c1")
                            println("AAA ${index + 2} v1")
                            println("AAA ${index + 3} c2")
                            value = values[index]
                            counterSecondary = values[index + 1] - 1
                            counterTerniary = values[index + 3] - 1
                            index += 4
                        }
                    } else {
                        counterSecondary--
                        println("AAA ${index + 0} v1")
                        println("AAA ${index + 1} c2")
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
