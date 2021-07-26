## Database internal structure

### steps to evaluate a SPARQL query

1. Start with SPARQL-String
2. Convert the String to Operator-AST - see Folder [sparql1_1](../src/luposdate3000_parser/src/commonMain/kotlin/lupos/parser/sparql1_1)
3. Convert the Operator-AST to Logical-Operatorgraph - see File [OperatorGraphVisitor.kt](../src/luposdate3000_optimizer_ast/src/commonMain/kotlin/lupos/optimizer/ast/OperatorGraphVisitor.kt)
4. Optimize Logical-Operatorgraph - see Folder [logical](../src/luposdate3000_optimizer_logical/src/commonMain/kotlin/lupos/optimizer/logical)
5. Convert Logical-Operatorgraph to Physical-Operatorgraph and optimize it - see Folder [physical](../src/luposdate3000_optimizer_physical/src/commonMain/kotlin/lupos/optimizer/physical)
6. Finally evaluate the Physical Operatorgraph (and convert the output to text) - see Folder [result_format](../src/luposdate3000_result_format/src/commonMain/kotlin/lupos/result_format)
7. The Physical Operators are first converted their final Iterator-representation
8. The iterators.next function is called until it returns the No-More-Elements-ID

Step 3 assigns IDs to the operators.
These IDs are changing a lot until (inclusive) phase 5.
This changing of IDs comes from the current kotlin-native memory model, where everything is freezed immediately after assignment such that any change is only possibly by a full duplication of the entire datastructure.
Within phase 6 the IDs are fixed, such that your code can depend on it.

### Operator implementations

The Logical Operators have "only" names, and metadata. These Operators can not be evaluated on their own.
All Logical Operators have the prefix "LOP" .
These Operators are defined in [logical](../src/luposdate3000_optimizer_logical/src/commonMain/kotlin/lupos/optimizer/logical).

The Aritmetic Operators are defined in [arithmetic](../src/luposdate3000_operator_arithmetik/src/commonMain/kotlin/lupos/operator/arithmetik).
Their common Prefix is "AOP".
These Operators can be evaluated directly, but they work only on a specific input row to calculate a single Value.

The Physical Operators use the prefix "POP" and are defined in [physical](../src/luposdate3000_optimizer_physical/src/commonMain/kotlin/lupos/optimizer/physical).
On evaluation these Operators return Iterators, which then finally perform the actual evaluation.
As a consequence a Physical Operatorgraph can be reused as it is, and still evaluate up to date values from the Store.

A physical Operatorgraph becomes invalid, if the used graphs are deleted or recreated.

### query evaluation

luposdate3000 uses a mix of column and row iterators to process the sparql queries.
Column iterators are preferred where possible, but operators like order-by are much easier (and faster) to be implemented using a row based approach.
Due to the focus on column iterators, there is the special case, where there is no column to be projected, but there needs to be the correct amount of output rows.
In this case luposdate3000 returns a special count-type, where the operator just implements an "has-next-row" instead of the actual row.

The basic iterators can be found in the folder [iterator](../src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/operator/iterator).

The usage can be seen in the (optimal) implementation of a merge-join (no optionals, only a single column from both inputs) can be found in the file
[POPJoinMergeSingleColumn.kt](../src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMergeSingleColumn.kt)

### modules

The module [shared](../src/luposdate3000_shared) is linked as dependency in EVERY other module.
Put all your interfaces here.

The module [shared_inline](../src/luposdate3000_shared_inline) is at compile time copy pasted into every other module.
This allows the compiler to inline every function, but on the other hand, this costs some compile time, and requires additionaly space in the resulting executable.

Dependencies to other modules are automatically detected by parsing the source-code, and scanning all the import-statements.
Due to dependency-order errors within gradle, all targets get a full recoursive list of all their dependencies directly, even, if for example the jvm source-code should be able to compile without the recoursive dependencies.
