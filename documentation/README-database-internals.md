## Database internal structure

### steps to evaluate a SPARQL query

1. start with SPARQL-String
2. convert the String to Operator-AST - see Folder "src/luposdate3000_parser/commonMain/kotlin/lupos/s02buildSyntaxTree/sparql1_1"
3. convert the Operator-AST to Logical-Operatorgraph - see File "src/luposdate3000_optimizer/commonMain/kotlin/lupos/s06buildOperatorGraph/OperatorGraphVisitor.kt"
4. optimize Logical-Operatorgraph - see Folder "src/luposdate3000_optimizer/commonMain/kotlin/lupos/s08logicalOptimisation/"
5. convert Logical-Operatorgraph to Physical-Operatorgraph and optimize it - see Folder "src/luposdate3000_optimizer/commonMain/kotlin/lupos/s10physicalOptimisation/"
6. finally evaluate the Physical Operatorgraph (and convert the output to text) - see Folder "src/luposdate3000_result_format/commonMain/kotlin/lupos/s11outputResult/"
6.1 The Physical Operators are first converted their final Iterator-representation
6.2 The iterators.next function is called until it returns the No-More-Elements-ID

Step 3 assigns IDs to the operators.
These IDs are changing a lot until (inclusive) phase 5.
This changing of IDs comes from the current kotlin-native memory model, where everything is freezed immediately after assignment such that any change is only possibly by a full duplication of the whole datastructure.
Within phase 6 the IDs are fixed, such that your code can depend on it.

### Operator implementations

The Logical Operators have "only" names, and metadata. These Operators are not evaluateable on their own.
All Logical Operators have the prefix "LOP" .
These Operators are defined in src/luposdate3000_operators/commonMain/kotlin/lupos/s04logicalOperators.

The Aritmetic Operators are defined in src/luposdate3000_operators/commonMain/kotlin/lupos/s04arithmetikOperators.
Their common Prefix is "AOP".
These Operators can be evaluated directly, but they work only on a specific input row to calculate a single Value.

The Physical Operators use the prefix "POP" and are defined in src/luposdate3000_operators/commonMain/kotlin/lupos/s09physicalOperators.
On evaluation these Operators return Iterators, which then finally perform the actual evaluation.
As a consequence a Physical Operatorgraph can be reused as it is, and still evaluate up to date values from the Store.

### query evaluation

luposdate3000 uses a mix of column and row iterators to process the sparql queries.
Column iterators are preferred where possible, but operators like order-by are much easier (and faster) to be implemented using a row based approach.
Due to the focus on column iterators, there is the special case, where there is no column to be projected, but there needs to be the correct amount of output rows.
In this case luposdate3000 returns a special count-type, where the operator just implements an "has-next-row" instead of the actual row.

The basic iterators can be found in the folder "src/luposdate3000_shared/commonMain/kotlin/lupos/s04logicalOperators/iterator"

The usage can be seen in the (optimal) implementation of a merge-join (no optionals, only a single column from both inputs) can be found in the file
"src/luposdate3000_operators/commonMain/kotlin/lupos/s09physicalOperators/multiinput/POPJoinMergeSingleColumn.kt"

### modules

The module "src/luposdate3000_shared" is linked as dependency in EVERY other module.
Put all your interfaces here.

The module "src/luposdate3000_shared_inline" is at compile time copy pasted into every other module.
This allows the compiler to inline every function, but on the other hand, this costs a lot of compile time.

All other dependencies must be explicitly listed in the corresponding dependency file e.g. "src/luposdate3000_launch_binary_test_suite/commonDependencies"
