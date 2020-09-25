package lupos.s02buildSyntaxTree.turtle
inline fun parseIRIREF(context:ParserContext,
 crossinline onIRIREF:()->Unit
){
 context.buffer.clear()
 when(context.c){
  '<'->{
   context.buffer.append(context.c)
   context.next()
   loop2@while(context.hasNext()){
    when(context.c){
     '!',in ('#'..';'),'=',in ('?'..'['),']','_',in ('a'..'z'),in ('~'..0xffff.toChar())->{
      context.buffer.append(context.c)
      context.next()
      continue@loop2
     }
     0x5c.toChar()->{
      context.buffer.append(context.c)
      context.next()
      when(context.c){
       'u'->{
        context.buffer.append(context.c)
        context.next()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.buffer.append(context.c)
          context.next()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.buffer.append(context.c)
            context.next()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.buffer.append(context.c)
              context.next()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.buffer.append(context.c)
                context.next()
                continue@loop2
               }
               else->{
                throw Exception("unexpected char $c")
               }
              }
             }
             else->{
              throw Exception("unexpected char $c")
             }
            }
           }
           else->{
            throw Exception("unexpected char $c")
           }
          }
         }
         else->{
          throw Exception("unexpected char $c")
         }
        }
       }
       'U'->{
        context.buffer.append(context.c)
        context.next()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.buffer.append(context.c)
          context.next()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.buffer.append(context.c)
            context.next()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.buffer.append(context.c)
              context.next()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.buffer.append(context.c)
                context.next()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.buffer.append(context.c)
                  context.next()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.buffer.append(context.c)
                    context.next()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.buffer.append(context.c)
                      context.next()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.buffer.append(context.c)
                        context.next()
                        continue@loop2
                       }
                       else->{
                        throw Exception("unexpected char $c")
                       }
                      }
                     }
                     else->{
                      throw Exception("unexpected char $c")
                     }
                    }
                   }
                   else->{
                    throw Exception("unexpected char $c")
                   }
                  }
                 }
                 else->{
                  throw Exception("unexpected char $c")
                 }
                }
               }
               else->{
                throw Exception("unexpected char $c")
               }
              }
             }
             else->{
              throw Exception("unexpected char $c")
             }
            }
           }
           else->{
            throw Exception("unexpected char $c")
           }
          }
         }
         else->{
          throw Exception("unexpected char $c")
         }
        }
       }
       else->{
        throw Exception("unexpected char $c")
       }
      }
     }
     else->{
      break@loop2
     }
    }
   }
   when(context.c){
    '>'->{
     context.buffer.append(context.c)
     context.next()
     onIRIREF()
     return
    }
    else->{
     throw Exception("unexpected char $c")
    }
   }
  }
  else->{
   throw Exception("unexpected char $c")
  }
 }
}
