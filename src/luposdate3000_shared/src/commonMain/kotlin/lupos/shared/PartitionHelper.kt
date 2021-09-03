/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */


public class PartitionHelper(){
public var partition: Map<String, Int>()//variableName->partition
public var keys=mutableListOf<PartitionHelper2>()
public fun getKeysFor(uuid:Int,partition:Map<String,Int>,query:IQuery,count:Int,ignoreVariable:String):IntArray{
loop@for(key in keys){
if(key.uuid!=uuid){ 
continue@loop
}
for((k,v) in partition){ 
if(k!=ignoreVariable&&key.partition[k]!=v){ 
continue@loop
}
}
return key.keys
}
val m=mutableMapOf<String,Int>()
m.putAll(partition)
val res=PartitionHelper2(uuid,m,IntArray(count){query.createPartitionKey()})
keys.add(res)
return res.keys
}
public fun getKeysFor(uuid:Int,partition:Map<String,Int>,query:IQuery,count:Int):IntArray{
loop@for(key in keys){
if(key.uuid!=uuid){
continue@loop
}
for((k,v) in partition){
if(key.partition[k]!=v){
continue@loop
}
}
return key.keys
}
val m=mutableMapOf<String,Int>()
m.putAll(partition)
val res=PartitionHelper2(uuid,m,IntArray(count){query.createPartitionKey()})
keys.add(res)
return res.keys
}
}
internal class PartitionHelper2(internal val uuid:Int,internal var partition: Map<String, Int>,internal val keys:IntArray){
} 
