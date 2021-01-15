## Interface
The interface is defined in the File [LuposdateEndpoint.kt](../src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/s16network/LuposdateEndpoint.kt)
The function "initialize" in that file must be called first before anything else.
If you just use the other functions from that file, the function is called automatically.
If you want to call any other function from the database, make sure that the "initialize" function is called first.
The function should be called only once, but it is protected such that it is not called twice.

If you extend this interface:
* Do not overload any function there
* Do not use default parameters - which is basically function overloading too.
* Annotate all public functions with '@JsName("")'

All of the above should prevent name-mangling which makes it much easier to use this interface from native and javascript.
