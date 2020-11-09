## Database internal structure

luposdate3000 uses a mix of column and row iterators to process the sparql queries.
Column iterators are preferred where possible, but operators like order-by are much easier (and faster) to be implemented using a row based approach.
Due to the focus on column iterators, there is the special case, where there is no column to be projected, but there needs to be the correct amount of output rows.
In this case luposdate3000 returns a special count-type, where the operator just implements an "has-next-row" instead of the actual row.

The basic iterators can be found in the folder "src/luposdate3000_shared/commonMain/kotlin/lupos/s04logicalOperators/iterator"

The usage can be seen in the (optimal) implementation of a merge-join (no optionals, only a single column from both inputs) can be found in the file
"src/luposdate3000_operators/commonMain/kotlin/lupos/s09physicalOperators/multiinput/POPJoinMergeSingleColumn.kt"

