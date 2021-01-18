import layer1.config.ConfigParser

fun main() {

    println("Start Simulation")
    val parser = ConfigParser()
    parser.parse(parser.configFile)
}