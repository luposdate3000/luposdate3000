package dummyImpl

import IDatabaseState
import IRouter
import java.io.File

class DatabaseState(
    override val ownAddress: Int,
    override var allAddresses: IntArray,
    override val sender: IRouter,
    override val absolutePathToDataDirectory: String): IDatabaseState
{
    val queriesInProgress = mutableMapOf<Int, Query>()
    var addressForQueryEndResult = -1
    lateinit var dataFile: File
}