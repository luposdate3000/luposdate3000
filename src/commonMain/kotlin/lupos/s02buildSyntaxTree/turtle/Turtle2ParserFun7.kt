package lupos.s02buildSyntaxTree.turtle
inline fun parseTripleEnd(context:ParserContext,
 crossinline onPREDICATE_LIST1:()->Unit,
 crossinline onOBJECT_LIST1:()->Unit,
 crossinline onDOT:()->Unit
){
 context.buffer.clear()
 when(context.c){
  ';'->{
   context.buffer.append(context.c)
   context.next()
   onPREDICATE_LIST1()
   return
  }
  ','->{
   context.buffer.append(context.c)
   context.next()
   onOBJECT_LIST1()
   return
  }
  '.'->{
   context.buffer.append(context.c)
   context.next()
   onDOT()
   return
  }
  else->{
   throw Exception("unexpected char $c")
  }
 }
}
