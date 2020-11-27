import lupos.s00misc.*
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s15tripleStoreDistributed.distributedTripleStore
import lupos.s16network.LuposdateEndpoint

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
val partitionOptions=listOf(4,8,16)
val joinPartitionOptions=listOf(4,8,16)
val sortOptions=listOf(0,1)

for(x in partitionOptions){
for(y in partitionOptions){
for(z in partitionOptions){
for(a in joinPartitionOptions){
for(b in joinPartitionOptions){
for(c in sortOptions){

}
}
}
}
}
}
}
