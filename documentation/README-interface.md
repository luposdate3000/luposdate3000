## Interface

The interface is defined in the File [LuposdateEndpoint.kt](../src/luposdate3000_endpoint/src/commonMain/kotlin/lupos/endpoint/LuposdateEndpoint.kt).
The function "initialize" in that file must be called to obtain an instance of the database..
Afterwards you can pass this instance to all other functions.
If you are done with the database, call the "close" function, which saves all remaining data to the disk (not needed if you use an inmemory database).
The close function should be called automatically, if the database is reciving a kill-signal.

If you extend this interface:
* Do not overload any function there
* Do not use default parameters - which is basically function overloading too.
* Annotate all public functions with '@JsName("")'

All of the above should prevent name-mangling which makes it much easier to use this interface from native and javascript.
