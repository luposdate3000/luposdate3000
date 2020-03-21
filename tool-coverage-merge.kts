import java.io.File

var res = mutableMapOf<String, Int>()

for (arg in args) {
    File(arg).bufferedReader().readLines().forEach {
        val arr = it.split(",")
        if (arr.size == 2) {
            if (res[arr[1]] == null) {
                res[arr[1]] = arr[0].toInt()
            } else {
                res[arr[1]] = res[arr[1]]!! + arr[0].toInt()
            }
        }
    }
    File(arg).delete()
}

res.forEach { k, v ->
    println("$v,$k")
}
