interface IDatabaseAccess {

        fun start(dataDirectoryPath: String)
        fun activate()
        fun deactivate()
        fun close()
        fun saveData(data: String)
}