package lupos.s00misc
actual class DateHelper() {
actual constructor(){}
    actual inline fun year ():Int= throw object : NotImplementedException("DateHelper", "year not implemented") {}
    actual inline fun month ():Int= throw object : NotImplementedException("DateHelper", "month not implemented") {}
    actual inline fun day ():Int= throw object : NotImplementedException("DateHelper", "day not implemented") {}
    actual inline fun hours ():Int = throw object : NotImplementedException("DateHelper", "hours not implemented") {}
    actual inline fun minutes ():Int = throw object : NotImplementedException("DateHelper", "minutes not implemented") {}
    actual inline fun seconds ():Int= throw object : NotImplementedException("DateHelper", "seconds not implemented") {}
}
