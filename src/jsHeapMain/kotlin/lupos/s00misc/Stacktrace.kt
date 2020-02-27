package lupos.s00misc


fun Throwable.kotlinStacktrace() {
    GlobalLogger.log(ELoggerType.RELEASE, { this.message })
    GlobalLogger.log(ELoggerType.DEBUG, { this })
}
