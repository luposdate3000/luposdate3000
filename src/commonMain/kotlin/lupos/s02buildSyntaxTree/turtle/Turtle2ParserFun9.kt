package lupos.s02buildSyntaxTree.turtle
inline fun parseTripleEndOrObjectString(context:ParserContext,
 crossinline onLANGTAG:()->Unit,
 crossinline onIRI1:()->Unit,
 crossinline onPREDICATE_LIST1:()->Unit,
 crossinline onOBJECT_LIST1:()->Unit,
 crossinline onDOT:()->Unit
){
 context.buffer.clear()
 when(context.c){
  '@'->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    in ('A'..'Z'),in ('a'..'z')->{
     context.buffer.append(context.c)
     context.next()
     loop4@while(context.hasNext()){
      when(context.c){
       in ('A'..'Z'),in ('a'..'z')->{
        context.buffer.append(context.c)
        context.next()
       }
       else->{
        break@loop4
       }
      }
     }
     loop4@while(context.hasNext()){
      when(context.c){
       '-'->{
        context.buffer.append(context.c)
        context.next()
        when(context.c){
         in ('0'..'9'),in ('A'..'Z'),in ('a'..'z')->{
          context.buffer.append(context.c)
          context.next()
          loop9@while(context.hasNext()){
           when(context.c){
            in ('0'..'9'),in ('A'..'Z'),in ('a'..'z')->{
             context.buffer.append(context.c)
             context.next()
            }
            else->{
             break@loop9
            }
           }
          }
          continue@loop4
         }
         else->{
          throw Exception("unexpected char $c")
         }
        }
       }
       else->{
        break@loop4
       }
      }
     }
     onLANGTAG()
     return
    }
    else->{
     throw Exception("unexpected char $c")
    }
   }
  }
  0x5e.toChar()->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    0x5e.toChar()->{
     context.buffer.append(context.c)
     context.next()
     onIRI1()
     return
    }
    else->{
     throw Exception("unexpected char $c")
    }
   }
  }
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
