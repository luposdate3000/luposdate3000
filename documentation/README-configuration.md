## Configuration
You can configure the database as you want.

Take a look into "compile-module-all.main.kts" .
There you can change the parameters for the indiviual modules.

The options "--nosuspend" or "--suspend" must be the same for EVERY module, because this changes method signatures in the binarys.

All configurable options can be found in the files src/*/configOptions.
These csv files columns are: "optionName,optionType(const val/val/typedef),variableType(Int,...),defaultValue" .

Additionally you can exchange some modules.
Currently these exchangeable Modules are:
* luposdate3000_endpoint_none or luposdate3000_endpoint_java_sockets
* luposdate3000_jena_wrapper_on or luposdate3000_jena_wrapper_off
* one of luposdate3000_launch*
You can see, which modules are interchangeable by looking at the "--prefix" flag. Modules using the same prefix are interchangeable, and are only allowed one at a time.
