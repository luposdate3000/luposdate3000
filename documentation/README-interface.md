## Interface

The interface is defined in the File [LuposdateEndpoint.kt](../src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt).
The function "initialize" in that file must be called first before anything else.
If you just use the other functions from that file, the function is called automatically.
If you want to call any other function from the database, make sure that the "initialize" function is called first.
The function should be called only once, but it is protected such that it is not called twice.
If you are done with the database, call the "close" function, which saves all remaining data to the disk (not needed if you use an inmemory database).
The close function should be called automatically, if the database is reciving a kill-signal.

If you extend this interface:
* Do not overload any function there
* Do not use default parameters - which is basically function overloading too.
* Annotate all public functions with '@JsName("")'

All of the above should prevent name-mangling which makes it much easier to use this interface from native and javascript.
