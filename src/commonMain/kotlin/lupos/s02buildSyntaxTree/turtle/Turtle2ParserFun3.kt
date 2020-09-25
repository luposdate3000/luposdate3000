package lupos.s02buildSyntaxTree.turtle
inline fun parseDOT(context:ParserContext,
 crossinline onDOT:()->Unit
){
 context.buffer.clear()
 when(context.c){
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
