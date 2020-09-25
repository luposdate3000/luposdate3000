package lupos.s02buildSyntaxTree.turtle
inline fun parsePNAME_NS(context:ParserContext,
 crossinline onPNAME_NS:()->Unit
){
 context.buffer.clear()
 when(context.c){
  in (0x0.toChar()..0xffff.toChar()),in ('A'..'Z'),in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
   context.buffer.append(context.c)
   context.next()
   loop2@while(context.hasNext()){
    loop3@while(context.hasNext()){
     when(context.c){
      '.'->{
       context.buffer.append(context.c)
       context.next()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.buffer.append(context.c)
      context.next()
      continue@loop2
     }
     else->{
      throw Exception("unexpected char $c")
     }
    }
   }
   when(context.c){
    0x3a.toChar()->{
     context.buffer.append(context.c)
     context.next()
     onPNAME_NS()
     return
    }
    else->{
     throw Exception("unexpected char $c")
    }
   }
  }
  0x3a.toChar()->{
   context.buffer.append(context.c)
   context.next()
   onPNAME_NS()
   return
  }
  else->{
   throw Exception("unexpected char $c")
  }
 }
}
