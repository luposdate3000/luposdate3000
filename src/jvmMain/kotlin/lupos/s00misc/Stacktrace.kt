package lupos.s00misc


fun Throwable.kotlinStacktrace() {
    GlobalLogger.log(ELoggerType.DEBUG, { this.printStackTrace() })
}
