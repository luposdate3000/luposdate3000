package lupos.s02buildSyntaxTree.turtle
import kotlin.jvm.JvmField
import lupos.s00misc.Luposdate3000Exception
class ParserException(msg:String):Luposdate3000Exception("ParserContext",msg)
class ParserExceptionEOF():ParserException("EOF")
class ParserExceptionUnexpectedChar(context:ParserContext):ParserException("unexpected char ${context.c} at ${context.line}:${context.column}")
class ParserContext(val input:CharIterator){
 @JvmField var c:Char=' '
 @JvmField var buffer=StringBuilder()
 @JvmField var line=0
 @JvmField var column=0
 fun next(){
  val tmp=(c=='\r') || (c=='\n')
  if(!hasNext){
   throw ParserExceptionEOF()
  }
  c=input.next()
  if((c=='\r') || (c=='\n')){
   if(!tmp){
    line++
    column=0
   }
  } else {
   column++
  }
 }
 fun append(){
  buffer.append(c)
  next()
 }
 fun hasNext():Boolean{
  return input.hasNext()
 }
 fun getValue():String{
  return buffer.toString()
 }
 init{
  next()
 }
}
inline fun parse_dot(context:ParserContext,
 crossinline onDOT:()->Unit
){
 context.buffer.clear()
 when(context.c){
  '.'->{
   context.append()
   onDOT()
   return
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_ws(context:ParserContext,
 crossinline onSKIP_WS:()->Unit
){
 context.buffer.clear()
 loop0@while(context.hasNext()){
  when(context.c){
   0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
    context.append()
   }
   else->{
    break@loop0
   }
  }
 }
 onSKIP_WS()
 return
}
inline fun parse_ws_forced(context:ParserContext,
 crossinline onSKIP_WS_FORCED:()->Unit
){
 context.buffer.clear()
 when(context.c){
  0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
   context.append()
   loop2@while(context.hasNext()){
    when(context.c){
     0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
      context.append()
     }
     else->{
      break@loop2
     }
    }
   }
   onSKIP_WS_FORCED()
   return
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_statement(context:ParserContext,
 crossinline onBASE:()->Unit,
 crossinline onPREFIX:()->Unit,
 crossinline onBASE2:()->Unit,
 crossinline onPREFIX2:()->Unit,
 crossinline onIRIREF:()->Unit,
 crossinline onPNAME_NS:()->Unit,
 crossinline onBLANK_NODE_LABEL:()->Unit
){
 context.buffer.clear()
 when(context.c){
  'B'->{
   context.append()
   when(context.c){
    'A'->{
     context.append()
     when(context.c){
      'S'->{
       context.append()
       when(context.c){
        'E'->{
         context.append()
         onBASE()
         return
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  'P'->{
   context.append()
   when(context.c){
    'R'->{
     context.append()
     when(context.c){
      'E'->{
       context.append()
       when(context.c){
        'F'->{
         context.append()
         when(context.c){
          'I'->{
           context.append()
           when(context.c){
            'X'->{
             context.append()
             onPREFIX()
             return
            }
            else->{
             throw ParserExceptionUnexpectedChar(context)
            }
           }
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  '@'->{
   context.append()
   when(context.c){
    'b'->{
     context.append()
     when(context.c){
      'a'->{
       context.append()
       when(context.c){
        's'->{
         context.append()
         when(context.c){
          'e'->{
           context.append()
           onBASE2()
           return
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    'p'->{
     context.append()
     when(context.c){
      'r'->{
       context.append()
       when(context.c){
        'e'->{
         context.append()
         when(context.c){
          'f'->{
           context.append()
           when(context.c){
            'i'->{
             context.append()
             when(context.c){
              'x'->{
               context.append()
               onPREFIX2()
               return
              }
              else->{
               throw ParserExceptionUnexpectedChar(context)
              }
             }
            }
            else->{
             throw ParserExceptionUnexpectedChar(context)
            }
           }
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  '<'->{
   context.append()
   loop2@while(context.hasNext()){
    when(context.c){
     '!',in ('#'..';'),'=',in ('?'..'['),']','_',in ('a'..'z'),in ('~'..0xffff.toChar())->{
      context.append()
      continue@loop2
     }
     0x5c.toChar()->{
      context.append()
      when(context.c){
       'u'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                continue@loop2
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       'U'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        continue@loop2
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
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
     context.append()
     onIRIREF()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  in (0x0.toChar()..0xffff.toChar()),in ('A'..'Z'),in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
   context.append()
   loop2@while(context.hasNext()){
    loop3@while(context.hasNext()){
     when(context.c){
      '.'->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.append()
      continue@loop2
     }
     else->{
      throw ParserExceptionUnexpectedChar(context)
     }
    }
   }
   when(context.c){
    0x3a.toChar()->{
     context.append()
     onPNAME_NS()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x3a.toChar()->{
   context.append()
   onPNAME_NS()
   return
  }
  '_'->{
   context.append()
   when(context.c){
    0x3a.toChar()->{
     context.append()
     when(context.c){
      in (0x0.toChar()..0xffff.toChar()),in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
       context.append()
       loop6@while(context.hasNext()){
        loop7@while(context.hasNext()){
         when(context.c){
          '.'->{
           context.append()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
          context.append()
          continue@loop6
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       onBLANK_NODE_LABEL()
       return
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_base(context:ParserContext,
 crossinline onIRIREF:()->Unit
){
 context.buffer.clear()
 when(context.c){
  '<'->{
   context.append()
   loop2@while(context.hasNext()){
    when(context.c){
     '!',in ('#'..';'),'=',in ('?'..'['),']','_',in ('a'..'z'),in ('~'..0xffff.toChar())->{
      context.append()
      continue@loop2
     }
     0x5c.toChar()->{
      context.append()
      when(context.c){
       'u'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                continue@loop2
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       'U'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        continue@loop2
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
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
     context.append()
     onIRIREF()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_prefix(context:ParserContext,
 crossinline onPNAME_NS:()->Unit
){
 context.buffer.clear()
 when(context.c){
  in (0x0.toChar()..0xffff.toChar()),in ('A'..'Z'),in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
   context.append()
   loop2@while(context.hasNext()){
    loop3@while(context.hasNext()){
     when(context.c){
      '.'->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.append()
      continue@loop2
     }
     else->{
      throw ParserExceptionUnexpectedChar(context)
     }
    }
   }
   when(context.c){
    0x3a.toChar()->{
     context.append()
     onPNAME_NS()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x3a.toChar()->{
   context.append()
   onPNAME_NS()
   return
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_prefix2(context:ParserContext,
 crossinline onIRIREF:()->Unit
){
 context.buffer.clear()
 when(context.c){
  '<'->{
   context.append()
   loop2@while(context.hasNext()){
    when(context.c){
     '!',in ('#'..';'),'=',in ('?'..'['),']','_',in ('a'..'z'),in ('~'..0xffff.toChar())->{
      context.append()
      continue@loop2
     }
     0x5c.toChar()->{
      context.append()
      when(context.c){
       'u'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                continue@loop2
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       'U'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        continue@loop2
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
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
     context.append()
     onIRIREF()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_predicate(context:ParserContext,
 crossinline onVERB1:()->Unit,
 crossinline onIRIREF:()->Unit,
 crossinline onPNAME_NS:()->Unit
){
 context.buffer.clear()
 when(context.c){
  'a'->{
   context.append()
   onVERB1()
   return
  }
  '<'->{
   context.append()
   loop2@while(context.hasNext()){
    when(context.c){
     '!',in ('#'..';'),'=',in ('?'..'['),']','_',in ('a'..'z'),in ('~'..0xffff.toChar())->{
      context.append()
      continue@loop2
     }
     0x5c.toChar()->{
      context.append()
      when(context.c){
       'u'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                continue@loop2
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       'U'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        continue@loop2
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
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
     context.append()
     onIRIREF()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  in (0x0.toChar()..0xffff.toChar()),in ('A'..'Z'),in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
   context.append()
   loop2@while(context.hasNext()){
    loop3@while(context.hasNext()){
     when(context.c){
      '.'->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.append()
      continue@loop2
     }
     else->{
      throw ParserExceptionUnexpectedChar(context)
     }
    }
   }
   when(context.c){
    0x3a.toChar()->{
     context.append()
     onPNAME_NS()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x3a.toChar()->{
   context.append()
   onPNAME_NS()
   return
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_obj(context:ParserContext,
 crossinline onIRIREF:()->Unit,
 crossinline onPNAME_NS:()->Unit,
 crossinline onBLANK_NODE_LABEL:()->Unit,
 crossinline onSTRING_LITERAL_QUOTE:()->Unit,
 crossinline onSTRING_LITERAL_SINGLE_QUOTE:()->Unit,
 crossinline onSTRING_LITERAL_LONG_SINGLE_QUOTE:()->Unit,
 crossinline onSTRING_LITERAL_LONG_QUOTE:()->Unit,
 crossinline onINTEGER:()->Unit,
 crossinline onDECIMAL:()->Unit,
 crossinline onDOUBLE:()->Unit,
 crossinline onBOOLEAN:()->Unit
){
 context.buffer.clear()
 when(context.c){
  '<'->{
   context.append()
   loop2@while(context.hasNext()){
    when(context.c){
     '!',in ('#'..';'),'=',in ('?'..'['),']','_',in ('a'..'z'),in ('~'..0xffff.toChar())->{
      context.append()
      continue@loop2
     }
     0x5c.toChar()->{
      context.append()
      when(context.c){
       'u'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                continue@loop2
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       'U'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        continue@loop2
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
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
     context.append()
     onIRIREF()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  in (0x0.toChar()..0xffff.toChar()),in ('A'..'Z'),in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
   context.append()
   loop2@while(context.hasNext()){
    loop3@while(context.hasNext()){
     when(context.c){
      '.'->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.append()
      continue@loop2
     }
     else->{
      throw ParserExceptionUnexpectedChar(context)
     }
    }
   }
   when(context.c){
    0x3a.toChar()->{
     context.append()
     onPNAME_NS()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x3a.toChar()->{
   context.append()
   onPNAME_NS()
   return
  }
  '_'->{
   context.append()
   when(context.c){
    0x3a.toChar()->{
     context.append()
     when(context.c){
      in (0x0.toChar()..0xffff.toChar()),in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
       context.append()
       loop6@while(context.hasNext()){
        loop7@while(context.hasNext()){
         when(context.c){
          '.'->{
           context.append()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
          context.append()
          continue@loop6
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       onBLANK_NODE_LABEL()
       return
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x22.toChar()->{
   context.append()
   when(context.c){
    in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
     context.append()
     loop4@while(context.hasNext()){
      when(context.c){
       in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
        context.append()
        continue@loop4
       }
       0x5c.toChar()->{
        context.append()
        when(context.c){
         0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
          context.append()
          continue@loop4
         }
         'u'->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  continue@loop4
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         'U'->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          continue@loop4
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        break@loop4
       }
      }
     }
     when(context.c){
      0x22.toChar()->{
       context.append()
       onSTRING_LITERAL_QUOTE()
       return
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    0x5c.toChar()->{
     context.append()
     when(context.c){
      0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
       context.append()
       loop6@while(context.hasNext()){
        when(context.c){
         in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
          context.append()
          continue@loop6
         }
         0x5c.toChar()->{
          context.append()
          when(context.c){
           0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
            context.append()
            continue@loop6
           }
           'u'->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    continue@loop6
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           'U'->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          when(context.c){
                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                            context.append()
                            continue@loop6
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          break@loop6
         }
        }
       }
       when(context.c){
        0x22.toChar()->{
         context.append()
         onSTRING_LITERAL_QUOTE()
         return
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      'u'->{
       context.append()
       when(context.c){
        in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
         context.append()
         when(context.c){
          in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
           context.append()
           when(context.c){
            in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
             context.append()
             when(context.c){
              in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
               context.append()
               loop14@while(context.hasNext()){
                when(context.c){
                 in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
                  context.append()
                  continue@loop14
                 }
                 0x5c.toChar()->{
                  context.append()
                  when(context.c){
                   0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
                    context.append()
                    continue@loop14
                   }
                   'u'->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          when(context.c){
                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                            context.append()
                            continue@loop14
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   'U'->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          when(context.c){
                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                            context.append()
                            when(context.c){
                             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                              context.append()
                              when(context.c){
                               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                context.append()
                                when(context.c){
                                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                  context.append()
                                  when(context.c){
                                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                    context.append()
                                    continue@loop14
                                   }
                                   else->{
                                    throw ParserExceptionUnexpectedChar(context)
                                   }
                                  }
                                 }
                                 else->{
                                  throw ParserExceptionUnexpectedChar(context)
                                 }
                                }
                               }
                               else->{
                                throw ParserExceptionUnexpectedChar(context)
                               }
                              }
                             }
                             else->{
                              throw ParserExceptionUnexpectedChar(context)
                             }
                            }
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  break@loop14
                 }
                }
               }
               when(context.c){
                0x22.toChar()->{
                 context.append()
                 onSTRING_LITERAL_QUOTE()
                 return
                }
                else->{
                 throw ParserExceptionUnexpectedChar(context)
                }
               }
              }
              else->{
               throw ParserExceptionUnexpectedChar(context)
              }
             }
            }
            else->{
             throw ParserExceptionUnexpectedChar(context)
            }
           }
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      'U'->{
       context.append()
       when(context.c){
        in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
         context.append()
         when(context.c){
          in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
           context.append()
           when(context.c){
            in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
             context.append()
             when(context.c){
              in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
               context.append()
               when(context.c){
                in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                 context.append()
                 when(context.c){
                  in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                   context.append()
                   when(context.c){
                    in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                     context.append()
                     when(context.c){
                      in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                       context.append()
                       loop22@while(context.hasNext()){
                        when(context.c){
                         in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
                          context.append()
                          continue@loop22
                         }
                         0x5c.toChar()->{
                          context.append()
                          when(context.c){
                           0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
                            context.append()
                            continue@loop22
                           }
                           'u'->{
                            context.append()
                            when(context.c){
                             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                              context.append()
                              when(context.c){
                               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                context.append()
                                when(context.c){
                                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                  context.append()
                                  when(context.c){
                                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                    context.append()
                                    continue@loop22
                                   }
                                   else->{
                                    throw ParserExceptionUnexpectedChar(context)
                                   }
                                  }
                                 }
                                 else->{
                                  throw ParserExceptionUnexpectedChar(context)
                                 }
                                }
                               }
                               else->{
                                throw ParserExceptionUnexpectedChar(context)
                               }
                              }
                             }
                             else->{
                              throw ParserExceptionUnexpectedChar(context)
                             }
                            }
                           }
                           'U'->{
                            context.append()
                            when(context.c){
                             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                              context.append()
                              when(context.c){
                               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                context.append()
                                when(context.c){
                                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                  context.append()
                                  when(context.c){
                                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                    context.append()
                                    when(context.c){
                                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                      context.append()
                                      when(context.c){
                                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                        context.append()
                                        when(context.c){
                                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                          context.append()
                                          when(context.c){
                                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                            context.append()
                                            continue@loop22
                                           }
                                           else->{
                                            throw ParserExceptionUnexpectedChar(context)
                                           }
                                          }
                                         }
                                         else->{
                                          throw ParserExceptionUnexpectedChar(context)
                                         }
                                        }
                                       }
                                       else->{
                                        throw ParserExceptionUnexpectedChar(context)
                                       }
                                      }
                                     }
                                     else->{
                                      throw ParserExceptionUnexpectedChar(context)
                                     }
                                    }
                                   }
                                   else->{
                                    throw ParserExceptionUnexpectedChar(context)
                                   }
                                  }
                                 }
                                 else->{
                                  throw ParserExceptionUnexpectedChar(context)
                                 }
                                }
                               }
                               else->{
                                throw ParserExceptionUnexpectedChar(context)
                               }
                              }
                             }
                             else->{
                              throw ParserExceptionUnexpectedChar(context)
                             }
                            }
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          break@loop22
                         }
                        }
                       }
                       when(context.c){
                        0x22.toChar()->{
                         context.append()
                         onSTRING_LITERAL_QUOTE()
                         return
                        }
                        else->{
                         throw ParserExceptionUnexpectedChar(context)
                        }
                       }
                      }
                      else->{
                       throw ParserExceptionUnexpectedChar(context)
                      }
                     }
                    }
                    else->{
                     throw ParserExceptionUnexpectedChar(context)
                    }
                   }
                  }
                  else->{
                   throw ParserExceptionUnexpectedChar(context)
                  }
                 }
                }
                else->{
                 throw ParserExceptionUnexpectedChar(context)
                }
               }
              }
              else->{
               throw ParserExceptionUnexpectedChar(context)
              }
             }
            }
            else->{
             throw ParserExceptionUnexpectedChar(context)
            }
           }
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    0x22.toChar()->{
     context.append()
     when(context.c){
      0x22.toChar()->{
       context.append()
       loop6@while(context.hasNext()){
        when(context.c){
         in (0x0.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
          context.append()
          continue@loop6
         }
         0x5c.toChar()->{
          context.append()
          when(context.c){
           0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
            context.append()
            continue@loop6
           }
           'u'->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    continue@loop6
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           'U'->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          when(context.c){
                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                            context.append()
                            continue@loop6
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         0x22.toChar()->{
          context.append()
          when(context.c){
           in (0x0.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
            context.append()
            continue@loop6
           }
           0x5c.toChar()->{
            context.append()
            when(context.c){
             0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
              context.append()
              continue@loop6
             }
             'u'->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      continue@loop6
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             'U'->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          when(context.c){
                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                            context.append()
                            when(context.c){
                             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                              context.append()
                              continue@loop6
                             }
                             else->{
                              throw ParserExceptionUnexpectedChar(context)
                             }
                            }
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           0x22.toChar()->{
            context.append()
            when(context.c){
             in (0x0.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
              context.append()
              continue@loop6
             }
             0x5c.toChar()->{
              context.append()
              when(context.c){
               0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
                context.append()
                continue@loop6
               }
               'u'->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        continue@loop6
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               'U'->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          when(context.c){
                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                            context.append()
                            when(context.c){
                             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                              context.append()
                              when(context.c){
                               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                context.append()
                                continue@loop6
                               }
                               else->{
                                throw ParserExceptionUnexpectedChar(context)
                               }
                              }
                             }
                             else->{
                              throw ParserExceptionUnexpectedChar(context)
                             }
                            }
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             0x22.toChar()->{
              context.append()
              onSTRING_LITERAL_LONG_QUOTE()
              return
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          break@loop6
         }
        }
       }
       throw ParserExceptionUnexpectedChar(context)
      }
      else->{
       onSTRING_LITERAL_QUOTE()
       return
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x27.toChar()->{
   context.append()
   when(context.c){
    in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
     context.append()
     loop4@while(context.hasNext()){
      when(context.c){
       in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
        context.append()
        continue@loop4
       }
       0x5c.toChar()->{
        context.append()
        when(context.c){
         0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
          context.append()
          continue@loop4
         }
         'u'->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  continue@loop4
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         'U'->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          continue@loop4
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        break@loop4
       }
      }
     }
     when(context.c){
      0x27.toChar()->{
       context.append()
       onSTRING_LITERAL_SINGLE_QUOTE()
       return
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    0x5c.toChar()->{
     context.append()
     when(context.c){
      0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
       context.append()
       loop6@while(context.hasNext()){
        when(context.c){
         in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
          context.append()
          continue@loop6
         }
         0x5c.toChar()->{
          context.append()
          when(context.c){
           0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
            context.append()
            continue@loop6
           }
           'u'->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    continue@loop6
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           'U'->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          when(context.c){
                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                            context.append()
                            continue@loop6
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          break@loop6
         }
        }
       }
       when(context.c){
        0x27.toChar()->{
         context.append()
         onSTRING_LITERAL_SINGLE_QUOTE()
         return
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      'u'->{
       context.append()
       when(context.c){
        in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
         context.append()
         when(context.c){
          in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
           context.append()
           when(context.c){
            in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
             context.append()
             when(context.c){
              in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
               context.append()
               loop14@while(context.hasNext()){
                when(context.c){
                 in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
                  context.append()
                  continue@loop14
                 }
                 0x5c.toChar()->{
                  context.append()
                  when(context.c){
                   0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
                    context.append()
                    continue@loop14
                   }
                   'u'->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          when(context.c){
                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                            context.append()
                            continue@loop14
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   'U'->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          when(context.c){
                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                            context.append()
                            when(context.c){
                             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                              context.append()
                              when(context.c){
                               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                context.append()
                                when(context.c){
                                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                  context.append()
                                  when(context.c){
                                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                    context.append()
                                    continue@loop14
                                   }
                                   else->{
                                    throw ParserExceptionUnexpectedChar(context)
                                   }
                                  }
                                 }
                                 else->{
                                  throw ParserExceptionUnexpectedChar(context)
                                 }
                                }
                               }
                               else->{
                                throw ParserExceptionUnexpectedChar(context)
                               }
                              }
                             }
                             else->{
                              throw ParserExceptionUnexpectedChar(context)
                             }
                            }
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  break@loop14
                 }
                }
               }
               when(context.c){
                0x27.toChar()->{
                 context.append()
                 onSTRING_LITERAL_SINGLE_QUOTE()
                 return
                }
                else->{
                 throw ParserExceptionUnexpectedChar(context)
                }
               }
              }
              else->{
               throw ParserExceptionUnexpectedChar(context)
              }
             }
            }
            else->{
             throw ParserExceptionUnexpectedChar(context)
            }
           }
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      'U'->{
       context.append()
       when(context.c){
        in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
         context.append()
         when(context.c){
          in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
           context.append()
           when(context.c){
            in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
             context.append()
             when(context.c){
              in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
               context.append()
               when(context.c){
                in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                 context.append()
                 when(context.c){
                  in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                   context.append()
                   when(context.c){
                    in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                     context.append()
                     when(context.c){
                      in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                       context.append()
                       loop22@while(context.hasNext()){
                        when(context.c){
                         in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
                          context.append()
                          continue@loop22
                         }
                         0x5c.toChar()->{
                          context.append()
                          when(context.c){
                           0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
                            context.append()
                            continue@loop22
                           }
                           'u'->{
                            context.append()
                            when(context.c){
                             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                              context.append()
                              when(context.c){
                               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                context.append()
                                when(context.c){
                                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                  context.append()
                                  when(context.c){
                                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                    context.append()
                                    continue@loop22
                                   }
                                   else->{
                                    throw ParserExceptionUnexpectedChar(context)
                                   }
                                  }
                                 }
                                 else->{
                                  throw ParserExceptionUnexpectedChar(context)
                                 }
                                }
                               }
                               else->{
                                throw ParserExceptionUnexpectedChar(context)
                               }
                              }
                             }
                             else->{
                              throw ParserExceptionUnexpectedChar(context)
                             }
                            }
                           }
                           'U'->{
                            context.append()
                            when(context.c){
                             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                              context.append()
                              when(context.c){
                               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                context.append()
                                when(context.c){
                                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                  context.append()
                                  when(context.c){
                                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                    context.append()
                                    when(context.c){
                                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                      context.append()
                                      when(context.c){
                                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                        context.append()
                                        when(context.c){
                                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                          context.append()
                                          when(context.c){
                                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                            context.append()
                                            continue@loop22
                                           }
                                           else->{
                                            throw ParserExceptionUnexpectedChar(context)
                                           }
                                          }
                                         }
                                         else->{
                                          throw ParserExceptionUnexpectedChar(context)
                                         }
                                        }
                                       }
                                       else->{
                                        throw ParserExceptionUnexpectedChar(context)
                                       }
                                      }
                                     }
                                     else->{
                                      throw ParserExceptionUnexpectedChar(context)
                                     }
                                    }
                                   }
                                   else->{
                                    throw ParserExceptionUnexpectedChar(context)
                                   }
                                  }
                                 }
                                 else->{
                                  throw ParserExceptionUnexpectedChar(context)
                                 }
                                }
                               }
                               else->{
                                throw ParserExceptionUnexpectedChar(context)
                               }
                              }
                             }
                             else->{
                              throw ParserExceptionUnexpectedChar(context)
                             }
                            }
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          break@loop22
                         }
                        }
                       }
                       when(context.c){
                        0x27.toChar()->{
                         context.append()
                         onSTRING_LITERAL_SINGLE_QUOTE()
                         return
                        }
                        else->{
                         throw ParserExceptionUnexpectedChar(context)
                        }
                       }
                      }
                      else->{
                       throw ParserExceptionUnexpectedChar(context)
                      }
                     }
                    }
                    else->{
                     throw ParserExceptionUnexpectedChar(context)
                    }
                   }
                  }
                  else->{
                   throw ParserExceptionUnexpectedChar(context)
                  }
                 }
                }
                else->{
                 throw ParserExceptionUnexpectedChar(context)
                }
               }
              }
              else->{
               throw ParserExceptionUnexpectedChar(context)
              }
             }
            }
            else->{
             throw ParserExceptionUnexpectedChar(context)
            }
           }
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    0x27.toChar()->{
     context.append()
     when(context.c){
      0x27.toChar()->{
       context.append()
       loop6@while(context.hasNext()){
        when(context.c){
         in (0x0.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
          context.append()
          continue@loop6
         }
         0x5c.toChar()->{
          context.append()
          when(context.c){
           0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
            context.append()
            continue@loop6
           }
           'u'->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    continue@loop6
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           'U'->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          when(context.c){
                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                            context.append()
                            continue@loop6
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         0x27.toChar()->{
          context.append()
          when(context.c){
           in (0x0.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
            context.append()
            continue@loop6
           }
           0x5c.toChar()->{
            context.append()
            when(context.c){
             0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
              context.append()
              continue@loop6
             }
             'u'->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      continue@loop6
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             'U'->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          when(context.c){
                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                            context.append()
                            when(context.c){
                             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                              context.append()
                              continue@loop6
                             }
                             else->{
                              throw ParserExceptionUnexpectedChar(context)
                             }
                            }
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           0x27.toChar()->{
            context.append()
            when(context.c){
             in (0x0.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
              context.append()
              continue@loop6
             }
             0x5c.toChar()->{
              context.append()
              when(context.c){
               0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
                context.append()
                continue@loop6
               }
               'u'->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        continue@loop6
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               'U'->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        when(context.c){
                         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                          context.append()
                          when(context.c){
                           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                            context.append()
                            when(context.c){
                             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                              context.append()
                              when(context.c){
                               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                                context.append()
                                continue@loop6
                               }
                               else->{
                                throw ParserExceptionUnexpectedChar(context)
                               }
                              }
                             }
                             else->{
                              throw ParserExceptionUnexpectedChar(context)
                             }
                            }
                           }
                           else->{
                            throw ParserExceptionUnexpectedChar(context)
                           }
                          }
                         }
                         else->{
                          throw ParserExceptionUnexpectedChar(context)
                         }
                        }
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             0x27.toChar()->{
              context.append()
              onSTRING_LITERAL_LONG_SINGLE_QUOTE()
              return
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          break@loop6
         }
        }
       }
       throw ParserExceptionUnexpectedChar(context)
      }
      else->{
       onSTRING_LITERAL_SINGLE_QUOTE()
       return
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  in ('0'..'9')->{
   context.append()
   loop2@while(context.hasNext()){
    when(context.c){
     in ('0'..'9')->{
      context.append()
     }
     else->{
      break@loop2
     }
    }
   }
   when(context.c){
    '.'->{
     context.append()
     when(context.c){
      in ('0'..'9')->{
       context.append()
       loop6@while(context.hasNext()){
        when(context.c){
         in ('0'..'9')->{
          context.append()
         }
         else->{
          break@loop6
         }
        }
       }
       when(context.c){
        'E','e'->{
         context.append()
         when(context.c){
          in ('0'..'9')->{
           context.append()
           loop10@while(context.hasNext()){
            when(context.c){
             in ('0'..'9')->{
              context.append()
             }
             else->{
              break@loop10
             }
            }
           }
           onDOUBLE()
           return
          }
          '+','-'->{
           context.append()
           when(context.c){
            in ('0'..'9')->{
             context.append()
             loop12@while(context.hasNext()){
              when(context.c){
               in ('0'..'9')->{
                context.append()
               }
               else->{
                break@loop12
               }
              }
             }
             onDOUBLE()
             return
            }
            else->{
             throw ParserExceptionUnexpectedChar(context)
            }
           }
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         onDECIMAL()
         return
        }
       }
      }
      'E','e'->{
       context.append()
       when(context.c){
        in ('0'..'9')->{
         context.append()
         loop8@while(context.hasNext()){
          when(context.c){
           in ('0'..'9')->{
            context.append()
           }
           else->{
            break@loop8
           }
          }
         }
         onDOUBLE()
         return
        }
        '+','-'->{
         context.append()
         when(context.c){
          in ('0'..'9')->{
           context.append()
           loop10@while(context.hasNext()){
            when(context.c){
             in ('0'..'9')->{
              context.append()
             }
             else->{
              break@loop10
             }
            }
           }
           onDOUBLE()
           return
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    'E','e'->{
     context.append()
     when(context.c){
      in ('0'..'9')->{
       context.append()
       loop6@while(context.hasNext()){
        when(context.c){
         in ('0'..'9')->{
          context.append()
         }
         else->{
          break@loop6
         }
        }
       }
       onDOUBLE()
       return
      }
      '+','-'->{
       context.append()
       when(context.c){
        in ('0'..'9')->{
         context.append()
         loop8@while(context.hasNext()){
          when(context.c){
           in ('0'..'9')->{
            context.append()
           }
           else->{
            break@loop8
           }
          }
         }
         onDOUBLE()
         return
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    else->{
     onINTEGER()
     return
    }
   }
  }
  '+','-'->{
   context.append()
   when(context.c){
    in ('0'..'9')->{
     context.append()
     loop4@while(context.hasNext()){
      when(context.c){
       in ('0'..'9')->{
        context.append()
       }
       else->{
        break@loop4
       }
      }
     }
     when(context.c){
      '.'->{
       context.append()
       when(context.c){
        in ('0'..'9')->{
         context.append()
         loop8@while(context.hasNext()){
          when(context.c){
           in ('0'..'9')->{
            context.append()
           }
           else->{
            break@loop8
           }
          }
         }
         when(context.c){
          'E','e'->{
           context.append()
           when(context.c){
            in ('0'..'9')->{
             context.append()
             loop12@while(context.hasNext()){
              when(context.c){
               in ('0'..'9')->{
                context.append()
               }
               else->{
                break@loop12
               }
              }
             }
             onDOUBLE()
             return
            }
            '+','-'->{
             context.append()
             when(context.c){
              in ('0'..'9')->{
               context.append()
               loop14@while(context.hasNext()){
                when(context.c){
                 in ('0'..'9')->{
                  context.append()
                 }
                 else->{
                  break@loop14
                 }
                }
               }
               onDOUBLE()
               return
              }
              else->{
               throw ParserExceptionUnexpectedChar(context)
              }
             }
            }
            else->{
             throw ParserExceptionUnexpectedChar(context)
            }
           }
          }
          else->{
           onDECIMAL()
           return
          }
         }
        }
        'E','e'->{
         context.append()
         when(context.c){
          in ('0'..'9')->{
           context.append()
           loop10@while(context.hasNext()){
            when(context.c){
             in ('0'..'9')->{
              context.append()
             }
             else->{
              break@loop10
             }
            }
           }
           onDOUBLE()
           return
          }
          '+','-'->{
           context.append()
           when(context.c){
            in ('0'..'9')->{
             context.append()
             loop12@while(context.hasNext()){
              when(context.c){
               in ('0'..'9')->{
                context.append()
               }
               else->{
                break@loop12
               }
              }
             }
             onDOUBLE()
             return
            }
            else->{
             throw ParserExceptionUnexpectedChar(context)
            }
           }
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      'E','e'->{
       context.append()
       when(context.c){
        in ('0'..'9')->{
         context.append()
         loop8@while(context.hasNext()){
          when(context.c){
           in ('0'..'9')->{
            context.append()
           }
           else->{
            break@loop8
           }
          }
         }
         onDOUBLE()
         return
        }
        '+','-'->{
         context.append()
         when(context.c){
          in ('0'..'9')->{
           context.append()
           loop10@while(context.hasNext()){
            when(context.c){
             in ('0'..'9')->{
              context.append()
             }
             else->{
              break@loop10
             }
            }
           }
           onDOUBLE()
           return
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      else->{
       onINTEGER()
       return
      }
     }
    }
    '.'->{
     context.append()
     when(context.c){
      in ('0'..'9')->{
       context.append()
       loop6@while(context.hasNext()){
        when(context.c){
         in ('0'..'9')->{
          context.append()
         }
         else->{
          break@loop6
         }
        }
       }
       when(context.c){
        'E','e'->{
         context.append()
         when(context.c){
          in ('0'..'9')->{
           context.append()
           loop10@while(context.hasNext()){
            when(context.c){
             in ('0'..'9')->{
              context.append()
             }
             else->{
              break@loop10
             }
            }
           }
           onDOUBLE()
           return
          }
          '+','-'->{
           context.append()
           when(context.c){
            in ('0'..'9')->{
             context.append()
             loop12@while(context.hasNext()){
              when(context.c){
               in ('0'..'9')->{
                context.append()
               }
               else->{
                break@loop12
               }
              }
             }
             onDOUBLE()
             return
            }
            else->{
             throw ParserExceptionUnexpectedChar(context)
            }
           }
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         onDECIMAL()
         return
        }
       }
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  '.'->{
   context.append()
   when(context.c){
    in ('0'..'9')->{
     context.append()
     loop4@while(context.hasNext()){
      when(context.c){
       in ('0'..'9')->{
        context.append()
       }
       else->{
        break@loop4
       }
      }
     }
     when(context.c){
      'E','e'->{
       context.append()
       when(context.c){
        in ('0'..'9')->{
         context.append()
         loop8@while(context.hasNext()){
          when(context.c){
           in ('0'..'9')->{
            context.append()
           }
           else->{
            break@loop8
           }
          }
         }
         onDOUBLE()
         return
        }
        '+','-'->{
         context.append()
         when(context.c){
          in ('0'..'9')->{
           context.append()
           loop10@while(context.hasNext()){
            when(context.c){
             in ('0'..'9')->{
              context.append()
             }
             else->{
              break@loop10
             }
            }
           }
           onDOUBLE()
           return
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      else->{
       onDECIMAL()
       return
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  't'->{
   context.append()
   when(context.c){
    'r'->{
     context.append()
     when(context.c){
      'u'->{
       context.append()
       when(context.c){
        'e'->{
         context.append()
         when(context.c){
          'f'->{
           context.append()
           when(context.c){
            'a'->{
             context.append()
             when(context.c){
              'l'->{
               context.append()
               when(context.c){
                's'->{
                 context.append()
                 when(context.c){
                  'e'->{
                   context.append()
                   onBOOLEAN()
                   return
                  }
                  else->{
                   throw ParserExceptionUnexpectedChar(context)
                  }
                 }
                }
                else->{
                 throw ParserExceptionUnexpectedChar(context)
                }
               }
              }
              else->{
               throw ParserExceptionUnexpectedChar(context)
              }
             }
            }
            else->{
             throw ParserExceptionUnexpectedChar(context)
            }
           }
          }
          else->{
           throw ParserExceptionUnexpectedChar(context)
          }
         }
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_triple_end(context:ParserContext,
 crossinline onPREDICATE_LIST1:()->Unit,
 crossinline onOBJECT_LIST1:()->Unit,
 crossinline onDOT:()->Unit
){
 context.buffer.clear()
 when(context.c){
  ';'->{
   context.append()
   onPREDICATE_LIST1()
   return
  }
  ','->{
   context.append()
   onOBJECT_LIST1()
   return
  }
  '.'->{
   context.append()
   onDOT()
   return
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_triple_end_or_object_iri(context:ParserContext,
 crossinline onPN_LOCAL:()->Unit,
 crossinline onPREDICATE_LIST1:()->Unit,
 crossinline onOBJECT_LIST1:()->Unit,
 crossinline onDOT:()->Unit,
 crossinline onSKIP_WS_FORCED:()->Unit
){
 context.buffer.clear()
 when(context.c){
  in (0x0.toChar()..0xffff.toChar()),in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
   context.append()
   loop2@while(context.hasNext()){
    loop3@while(context.hasNext()){
     when(context.c){
      '.'->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.append()
      continue@loop2
     }
     '%'->{
      context.append()
      when(context.c){
       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          continue@loop2
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
       }
      }
     }
     0x5c.toChar()->{
      context.append()
      when(context.c){
       '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
        context.append()
        continue@loop2
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
       }
      }
     }
     else->{
      throw ParserExceptionUnexpectedChar(context)
     }
    }
   }
   onPN_LOCAL()
   return
  }
  '%'->{
   context.append()
   when(context.c){
    in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
     context.append()
     when(context.c){
      in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
       context.append()
       loop6@while(context.hasNext()){
        loop7@while(context.hasNext()){
         when(context.c){
          '.'->{
           context.append()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
          context.append()
          continue@loop6
         }
         '%'->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              continue@loop6
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         0x5c.toChar()->{
          context.append()
          when(context.c){
           '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
            context.append()
            continue@loop6
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       onPN_LOCAL()
       return
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x5c.toChar()->{
   context.append()
   when(context.c){
    '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
     context.append()
     loop4@while(context.hasNext()){
      loop5@while(context.hasNext()){
       when(context.c){
        '.'->{
         context.append()
        }
        else->{
         break@loop5
        }
       }
      }
      when(context.c){
       in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
        context.append()
        continue@loop4
       }
       '%'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            continue@loop4
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       0x5c.toChar()->{
        context.append()
        when(context.c){
         '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
          context.append()
          continue@loop4
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
       }
      }
     }
     onPN_LOCAL()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  ';'->{
   context.append()
   onPREDICATE_LIST1()
   return
  }
  ','->{
   context.append()
   onOBJECT_LIST1()
   return
  }
  '.'->{
   context.append()
   onDOT()
   return
  }
  0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
   context.append()
   loop2@while(context.hasNext()){
    when(context.c){
     0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
      context.append()
     }
     else->{
      break@loop2
     }
    }
   }
   onSKIP_WS_FORCED()
   return
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_triple_end_or_object_string(context:ParserContext,
 crossinline onLANGTAG:()->Unit,
 crossinline onIRI1:()->Unit,
 crossinline onPREDICATE_LIST1:()->Unit,
 crossinline onOBJECT_LIST1:()->Unit,
 crossinline onDOT:()->Unit,
 crossinline onSKIP_WS_FORCED:()->Unit
){
 context.buffer.clear()
 when(context.c){
  '@'->{
   context.append()
   when(context.c){
    in ('A'..'Z'),in ('a'..'z')->{
     context.append()
     loop4@while(context.hasNext()){
      when(context.c){
       in ('A'..'Z'),in ('a'..'z')->{
        context.append()
       }
       else->{
        break@loop4
       }
      }
     }
     loop4@while(context.hasNext()){
      when(context.c){
       '-'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'Z'),in ('a'..'z')->{
          context.append()
          loop9@while(context.hasNext()){
           when(context.c){
            in ('0'..'9'),in ('A'..'Z'),in ('a'..'z')->{
             context.append()
            }
            else->{
             break@loop9
            }
           }
          }
          continue@loop4
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
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
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x5e.toChar()->{
   context.append()
   when(context.c){
    0x5e.toChar()->{
     context.append()
     onIRI1()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  ';'->{
   context.append()
   onPREDICATE_LIST1()
   return
  }
  ','->{
   context.append()
   onOBJECT_LIST1()
   return
  }
  '.'->{
   context.append()
   onDOT()
   return
  }
  0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
   context.append()
   loop2@while(context.hasNext()){
    when(context.c){
     0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
      context.append()
     }
     else->{
      break@loop2
     }
    }
   }
   onSKIP_WS_FORCED()
   return
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_triple_end_or_object_string_typed(context:ParserContext,
 crossinline onIRIREF:()->Unit,
 crossinline onPNAME_NS:()->Unit
){
 context.buffer.clear()
 when(context.c){
  '<'->{
   context.append()
   loop2@while(context.hasNext()){
    when(context.c){
     '!',in ('#'..';'),'=',in ('?'..'['),']','_',in ('a'..'z'),in ('~'..0xffff.toChar())->{
      context.append()
      continue@loop2
     }
     0x5c.toChar()->{
      context.append()
      when(context.c){
       'u'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                continue@loop2
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       'U'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              when(context.c){
               in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                context.append()
                when(context.c){
                 in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                  context.append()
                  when(context.c){
                   in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                    context.append()
                    when(context.c){
                     in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                      context.append()
                      when(context.c){
                       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
                        context.append()
                        continue@loop2
                       }
                       else->{
                        throw ParserExceptionUnexpectedChar(context)
                       }
                      }
                     }
                     else->{
                      throw ParserExceptionUnexpectedChar(context)
                     }
                    }
                   }
                   else->{
                    throw ParserExceptionUnexpectedChar(context)
                   }
                  }
                 }
                 else->{
                  throw ParserExceptionUnexpectedChar(context)
                 }
                }
               }
               else->{
                throw ParserExceptionUnexpectedChar(context)
               }
              }
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
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
     context.append()
     onIRIREF()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  in (0x0.toChar()..0xffff.toChar()),in ('A'..'Z'),in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
   context.append()
   loop2@while(context.hasNext()){
    loop3@while(context.hasNext()){
     when(context.c){
      '.'->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.append()
      continue@loop2
     }
     else->{
      throw ParserExceptionUnexpectedChar(context)
     }
    }
   }
   when(context.c){
    0x3a.toChar()->{
     context.append()
     onPNAME_NS()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x3a.toChar()->{
   context.append()
   onPNAME_NS()
   return
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_triple_end_or_object_string_typed_iri(context:ParserContext,
 crossinline onPN_LOCAL:()->Unit,
 crossinline onPREDICATE_LIST1:()->Unit,
 crossinline onOBJECT_LIST1:()->Unit,
 crossinline onDOT:()->Unit,
 crossinline onSKIP_WS_FORCED:()->Unit
){
 context.buffer.clear()
 when(context.c){
  in (0x0.toChar()..0xffff.toChar()),in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
   context.append()
   loop2@while(context.hasNext()){
    loop3@while(context.hasNext()){
     when(context.c){
      '.'->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.append()
      continue@loop2
     }
     '%'->{
      context.append()
      when(context.c){
       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          continue@loop2
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
       }
      }
     }
     0x5c.toChar()->{
      context.append()
      when(context.c){
       '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
        context.append()
        continue@loop2
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
       }
      }
     }
     else->{
      throw ParserExceptionUnexpectedChar(context)
     }
    }
   }
   onPN_LOCAL()
   return
  }
  '%'->{
   context.append()
   when(context.c){
    in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
     context.append()
     when(context.c){
      in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
       context.append()
       loop6@while(context.hasNext()){
        loop7@while(context.hasNext()){
         when(context.c){
          '.'->{
           context.append()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
          context.append()
          continue@loop6
         }
         '%'->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              continue@loop6
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         0x5c.toChar()->{
          context.append()
          when(context.c){
           '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
            context.append()
            continue@loop6
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       onPN_LOCAL()
       return
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x5c.toChar()->{
   context.append()
   when(context.c){
    '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
     context.append()
     loop4@while(context.hasNext()){
      loop5@while(context.hasNext()){
       when(context.c){
        '.'->{
         context.append()
        }
        else->{
         break@loop5
        }
       }
      }
      when(context.c){
       in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
        context.append()
        continue@loop4
       }
       '%'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            continue@loop4
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       0x5c.toChar()->{
        context.append()
        when(context.c){
         '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
          context.append()
          continue@loop4
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
       }
      }
     }
     onPN_LOCAL()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  ';'->{
   context.append()
   onPREDICATE_LIST1()
   return
  }
  ','->{
   context.append()
   onOBJECT_LIST1()
   return
  }
  '.'->{
   context.append()
   onDOT()
   return
  }
  0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
   context.append()
   loop2@while(context.hasNext()){
    when(context.c){
     0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
      context.append()
     }
     else->{
      break@loop2
     }
    }
   }
   onSKIP_WS_FORCED()
   return
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_subject_iri_or_ws(context:ParserContext,
 crossinline onPN_LOCAL:()->Unit,
 crossinline onSKIP_WS_FORCED:()->Unit
){
 context.buffer.clear()
 when(context.c){
  in (0x0.toChar()..0xffff.toChar()),in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
   context.append()
   loop2@while(context.hasNext()){
    loop3@while(context.hasNext()){
     when(context.c){
      '.'->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.append()
      continue@loop2
     }
     '%'->{
      context.append()
      when(context.c){
       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          continue@loop2
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
       }
      }
     }
     0x5c.toChar()->{
      context.append()
      when(context.c){
       '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
        context.append()
        continue@loop2
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
       }
      }
     }
     else->{
      throw ParserExceptionUnexpectedChar(context)
     }
    }
   }
   onPN_LOCAL()
   return
  }
  '%'->{
   context.append()
   when(context.c){
    in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
     context.append()
     when(context.c){
      in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
       context.append()
       loop6@while(context.hasNext()){
        loop7@while(context.hasNext()){
         when(context.c){
          '.'->{
           context.append()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
          context.append()
          continue@loop6
         }
         '%'->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              continue@loop6
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         0x5c.toChar()->{
          context.append()
          when(context.c){
           '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
            context.append()
            continue@loop6
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       onPN_LOCAL()
       return
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x5c.toChar()->{
   context.append()
   when(context.c){
    '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
     context.append()
     loop4@while(context.hasNext()){
      loop5@while(context.hasNext()){
       when(context.c){
        '.'->{
         context.append()
        }
        else->{
         break@loop5
        }
       }
      }
      when(context.c){
       in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
        context.append()
        continue@loop4
       }
       '%'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            continue@loop4
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       0x5c.toChar()->{
        context.append()
        when(context.c){
         '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
          context.append()
          continue@loop4
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
       }
      }
     }
     onPN_LOCAL()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
   context.append()
   loop2@while(context.hasNext()){
    when(context.c){
     0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
      context.append()
     }
     else->{
      break@loop2
     }
    }
   }
   onSKIP_WS_FORCED()
   return
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
inline fun parse_predicate_iri_or_ws(context:ParserContext,
 crossinline onPN_LOCAL:()->Unit,
 crossinline onSKIP_WS_FORCED:()->Unit
){
 context.buffer.clear()
 when(context.c){
  in (0x0.toChar()..0xffff.toChar()),in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
   context.append()
   loop2@while(context.hasNext()){
    loop3@while(context.hasNext()){
     when(context.c){
      '.'->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.append()
      continue@loop2
     }
     '%'->{
      context.append()
      when(context.c){
       in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          continue@loop2
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
       }
      }
     }
     0x5c.toChar()->{
      context.append()
      when(context.c){
       '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
        context.append()
        continue@loop2
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
       }
      }
     }
     else->{
      throw ParserExceptionUnexpectedChar(context)
     }
    }
   }
   onPN_LOCAL()
   return
  }
  '%'->{
   context.append()
   when(context.c){
    in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
     context.append()
     when(context.c){
      in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
       context.append()
       loop6@while(context.hasNext()){
        loop7@while(context.hasNext()){
         when(context.c){
          '.'->{
           context.append()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
          context.append()
          continue@loop6
         }
         '%'->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            when(context.c){
             in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
              context.append()
              continue@loop6
             }
             else->{
              throw ParserExceptionUnexpectedChar(context)
             }
            }
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         0x5c.toChar()->{
          context.append()
          when(context.c){
           '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
            context.append()
            continue@loop6
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       onPN_LOCAL()
       return
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x5c.toChar()->{
   context.append()
   when(context.c){
    '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
     context.append()
     loop4@while(context.hasNext()){
      loop5@while(context.hasNext()){
       when(context.c){
        '.'->{
         context.append()
        }
        else->{
         break@loop5
        }
       }
      }
      when(context.c){
       in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
        context.append()
        continue@loop4
       }
       '%'->{
        context.append()
        when(context.c){
         in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
          context.append()
          when(context.c){
           in ('0'..'9'),in ('A'..'F'),in ('a'..'f')->{
            context.append()
            continue@loop4
           }
           else->{
            throw ParserExceptionUnexpectedChar(context)
           }
          }
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       0x5c.toChar()->{
        context.append()
        when(context.c){
         '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
          context.append()
          continue@loop4
         }
         else->{
          throw ParserExceptionUnexpectedChar(context)
         }
        }
       }
       else->{
        throw ParserExceptionUnexpectedChar(context)
       }
      }
     }
     onPN_LOCAL()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
   context.append()
   loop2@while(context.hasNext()){
    when(context.c){
     0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
      context.append()
     }
     else->{
      break@loop2
     }
    }
   }
   onSKIP_WS_FORCED()
   return
  }
  else->{
   throw ParserExceptionUnexpectedChar(context)
  }
 }
}
