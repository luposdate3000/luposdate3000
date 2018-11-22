package lupos.datastructures.b_plus_tree

import lupos.io.buffer.bufferManager

fun testBPlusTree(){
    // build B+-tree
    val filename = "test"
    val page = bufferManager.getPage(filename, 0)
    var adr = 0L
    page.putByte(adr, 0.toByte()) // root node is leaf node
    adr++
    page.putInt(adr, 4)
    adr+=4

    page.putInt(adr, 0)
    adr+=4
    page.putInt(adr, 0)
    adr+=4

    page.putInt(adr, 1)
    adr+=4
    page.putInt(adr, 1)
    adr+=4

    page.putInt(adr, 2)
    adr+=4
    page.putInt(adr, 2)
    adr+=4

    page.putInt(adr, 3)
    adr+=4
    page.putInt(adr, 3)
    adr+=4

    // now test search
    val b = B_Plus_Tree_Uncompressed_Int_to_Int(filename)
    try {
        println(b[0]);
        println(b[1]);
        println(b[2]);
        println(b[3]);
        println(b[4]);
    }catch(e:NotFoundException){
        println(e);
    }
    // TODO much more rigorously
}

fun testBPlusTree2(){
    val filename = "test"
    // initialize and create B_Plus_Tree
    val b = B_Plus_Tree_Uncompressed_Int_to_Int(filename, 8000, 8000)
    val list = mutableListOf<Pair<Int, Int>>()
    val size = 500000
    for(i in 1..size){
        list+=Pair(i,i)
    }
    b.generate(size, list.asIterable().iterator())
    try {
        for(i in size downTo 1){
            println(b[i])
        }
    }catch(e:NotFoundException){
        println(e);
    }
    // TODO much more rigorously
}

fun testBPlusTree3(){
    val filename = "test2"
    // initialize and create B_Plus_Tree
    val b = B_Plus_Tree_VariableSize_Int_to_Int(filename)
    val list = mutableListOf<Pair<Int, Int>>()
    val size = 500000
    for(i in 1..size){
        list+=Pair(i,i)
    }
    b.generate(size, list.asIterable().iterator())
    try {
        for(i in size downTo 1){
            println(b[i])
        }
    }catch(e:NotFoundException){
        println(e);
    }
    // TODO much more rigorously
}

fun testBPlusTree4(){
    val filename = "test3"
    // initialize and create B_Plus_Tree
    val b = B_Plus_Tree_Uncompressed_Int_to_Int(filename)
    val list = mutableListOf<Pair<Int, Int>>()
    val size = 500000
    for(i in 1..size){
        list+=Pair(i,i)
    }
    b.generate(size, list.asIterable().iterator())
    try {
        val range = b.range_search(100,2000)
        do {
            val result = range()
            println(result)
        } while(result!=null)
    }catch(e:NotFoundException){
        println(e);
    }
    // TODO much more rigorously
}

fun testBPlusTree4b(){
    val filename = "test3"
    // initialize and create B_Plus_Tree
    val b = B_Plus_Tree_StaticCompressed_Int_to_Int(filename) // B_Plus_Tree_Uncompressed_Int_to_Int(filename)
    val list = mutableListOf<Pair<Int, Int>>()
    val size = 500000
    for(i in 1..size){
        list+=Pair(i,i)
    }
    b.generate(size, list.asIterable().iterator())
    try {
        val range = b.sip_search(100, size)
        var i = 101
        do {
            val result = range(i)
            println("$i: $result")
            i=i*2
        } while(result!=null)
    }catch(e:NotFoundException){
        println(e);
    }
    // TODO much more rigorously
}

fun testBPlusTree5(){
    val filename = "test3"
    // initialize and create B_Plus_Tree
    val b = B_Plus_Tree_VariableSize_Int_to_Int(filename)
    val list = mutableListOf<Pair<Int, Int>>()
    val size = 500000
    for(i in 1..size){
        list+=Pair(i,i)
    }
    b.generate(size, list.asIterable().iterator())
    try {
        val range = b.range_search(100,2200)
        do {
            val result = range()
            println(result)
        } while(result!=null)
    }catch(e:NotFoundException){
        println(e);
    }
    // TODO much more rigorously
}

fun testBPlusTree6(){
    val filename = "test2"
    // initialize and create B_Plus_Tree
    val b = B_Plus_Tree_Difference_Encoding_Int_to_Int(filename) // , 8000, 8000)
    val list = mutableListOf<Pair<Int, Int>>()
    val size = 500000
    for(i in 1..size){
        list+=Pair(i,i)
    }
    b.generate(size, list.asIterable().iterator())
    try {
        for(i in size downTo 1){
            println(b[i])
        }
        b[2*size]
    }catch(e:NotFoundException){
        println(e);
    }
    // TODO much more rigorously
}

fun testBPlusTree7(){
    val filename = "test7"
    // initialize and create B_Plus_Tree
    val b = B_Plus_Tree_Difference_Encoding_Int_to_Int(filename) // , 8000, 8000)
    val list = mutableListOf<Pair<Int, Int>>()
    val size = 500000
    for(i in 1..size){
        list+=Pair(i,i)
    }
    b.generate(size, list.asIterable().iterator())
    try {
        val range = b.range_search(5200,10000)
        do {
            val result = range()
            println(result)
        } while(result!=null)
        val range2 = b.range_search(2*size,2*size+10000)
    }catch(e:NotFoundException){
        println(e);
    }
    // TODO much more rigorously
}

fun testBPlusTree8(){
    val filename = "test"
    // initialize and create B_Plus_Tree
    val b = B_Plus_Tree_Uncompressed_Int_to_Int(filename)
    val list = mutableListOf<Pair<Int, Int>>()
    val size = 500000
    for(i in 1..size){
        list+=Pair(i,i)
    }
    b.generate(size, list.asIterable().iterator())
    try {
        b.binarySearch(499001)
        for(i in size downTo 1){
            println(b.binarySearch(i))
        }
    }catch(e:NotFoundException){
        println(e);
    }
    // TODO much more rigorously
}

fun testBPlusTree9(){
    val filename = "test3"
    // initialize and create B_Plus_Tree
    val b = B_Plus_Tree_Uncompressed_Int_to_Int(filename)
    val list = mutableListOf<Pair<Int, Int>>()
    val size = 500000
    for(i in 1..size){
        list+=Pair(i,i)
    }
    b.generate(size, list.asIterable().iterator())
    try {
        val range = b.range_binary_search(20100,30000)
        do {
            val result = range()
            println(result)
        } while(result!=null)
    }catch(e:NotFoundException){
        println(e);
    }
    // TODO much more rigorously
}

fun testBPlusTree10(){
    val filename = "test2"
    // initialize and create B_Plus_Tree
    val b = B_Plus_Tree_VariableSizePointers_Int_to_Int(filename)
    val list = mutableListOf<Pair<Int, Int>>()
    val size = 500000
    for(i in 1..size){
        list+=Pair(i,i)
    }
    b.generate(size, list.asIterable().iterator())
    try {
        for(i in size downTo 1){
            println(b[i])
        }
        b[2*size]
    }catch(e:NotFoundException){
        println(e);
    }
    // TODO much more rigorously
}

fun testBPlusTree11(){
    val filename = "test2"
    // initialize and create B_Plus_Tree
    val b = B_Plus_Tree_VariableSizePointers_Int_to_Int(filename)
    val list = mutableListOf<Pair<Int, Int>>()
    val size = 500000
    for(i in 1..size){
        list+=Pair(i,i)
    }
    b.generate(size, list.asIterable().iterator())
    try {
        val range = b.range_search(20100,30000)
        do {
            val result = range()
            println(result)
        } while(result!=null)
    }catch(e:NotFoundException){
        println(e);
    }
    // TODO much more rigorously
}

fun testBPlusTree12(){
    val filename = "test2"
    // initialize and create B_Plus_Tree
    val b = B_Plus_Tree_Static_Int_to_Int(filename)
    val list = mutableListOf<Pair<Int, Int>>()
    val size = 5*500000
    for(i in 1..size){
        list+=Pair(i,i)
    }
    b.generate(size, list.asIterable().iterator())
    println(b[2089990])
    try {
        for(i in size downTo 1){
            println(b[i])
        }
    }catch(e:NotFoundException){
        println(e);
    }
    // TODO much more rigorously
}