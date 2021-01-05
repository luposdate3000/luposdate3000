package lupos.s15tripleStoreDistributed
import lupos.s04logicalOperators.IQuery
import lupos.s05tripleStore.EnabledPartitionContainer
import lupos.s05tripleStore.ITripleStoreLocalBase
public interface IPersistentStoreLocal {
    public fun getGraphNames(includeDefault: Boolean = false): List<String>
    public fun createGraph(query: IQuery, name: String): ITripleStoreLocalBase
    /*suspend*/ public fun dropGraph(query: IQuery, name: String)
    /*suspend*/ public fun clearGraph(query: IQuery, name: String)
    /*suspend*/ public fun getNamedGraph(query: IQuery, name: String, create: Boolean = false): ITripleStoreLocalBase
    /*suspend*/ public fun getDefaultGraph(query: IQuery): ITripleStoreLocalBase
    /*suspend*/ public fun commit(query: IQuery)
    public fun getEnabledPartitions(name: String): Array<EnabledPartitionContainer>
}
