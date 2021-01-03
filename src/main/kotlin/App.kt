fun main() {
    var x = kotlin.random.Random.nextInt()
    println("x: $x")
    var y = x
    println("y: $y")
    x = 3
    println("x: $y")
}