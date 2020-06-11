package lupos.s00misc

import java.util.Calendar

class DateHelper() {
    //https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
    val time = Calendar.getInstance()
    val year = time.get(Calendar.YEAR)
    val month = time.get(Calendar.MONTH)
    val day = time.get(Calendar.DAY_OF_MONTH)
    val hours = time.get(Calendar.HOUR)
    val minutes = time.get(Calendar.MINUTE)
    val seconds = time.get(Calendar.SECOND)
}
