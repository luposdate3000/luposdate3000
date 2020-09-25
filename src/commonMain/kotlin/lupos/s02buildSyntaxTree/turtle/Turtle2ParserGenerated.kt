package lupos.s02buildSyntaxTree.turtle
import kotlin.jvm.JvmField
class ParserContext(val input:CharIterator){
 @JvmField var c:Char=' '
 @JvmField var buffer=StringBuilder()
 fun next(){
  c=input.next()
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
inline fun parse_ws(context:ParserContext,
 crossinline onSKIP_WS:()->Unit
){
 context.buffer.clear()
 loop0@while(context.hasNext()){
  when(context.c){
   0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
    context.buffer.append(context.c)
    context.next()
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
   context.buffer.append(context.c)
   context.next()
   loop2@while(context.hasNext()){
    when(context.c){
     0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
      context.buffer.append(context.c)
      context.next()
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
   throw Exception("unexpected char $c")
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
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    'A'->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      'S'->{
       context.buffer.append(context.c)
       context.next()
       when(context.c){
        'E'->{
         context.buffer.append(context.c)
         context.next()
         onBASE()
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
    else->{
     throw Exception("unexpected char $c")
    }
   }
  }
  'P'->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    'R'->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      'E'->{
       context.buffer.append(context.c)
       context.next()
       when(context.c){
        'F'->{
         context.buffer.append(context.c)
         context.next()
         when(context.c){
          'I'->{
           context.buffer.append(context.c)
           context.next()
           when(context.c){
            'X'->{
             context.buffer.append(context.c)
             context.next()
             onPREFIX()
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
  '@'->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    'b'->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      'a'->{
       context.buffer.append(context.c)
       context.next()
       when(context.c){
        's'->{
         context.buffer.append(context.c)
         context.next()
         when(context.c){
          'e'->{
           context.buffer.append(context.c)
           context.next()
           onBASE2()
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
      else->{
       throw Exception("unexpected char $c")
      }
     }
    }
    'p'->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      'r'->{
       context.buffer.append(context.c)
       context.next()
       when(context.c){
        'e'->{
         context.buffer.append(context.c)
         context.next()
         when(context.c){
          'f'->{
           context.buffer.append(context.c)
           context.next()
           when(context.c){
            'i'->{
             context.buffer.append(context.c)
             context.next()
             when(context.c){
              'x'->{
               context.buffer.append(context.c)
               context.next()
               onPREFIX2()
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
  '_'->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    0x3a.toChar()->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      in (0x0.toChar()..0xffff.toChar()),in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
       context.buffer.append(context.c)
       context.next()
       loop6@while(context.hasNext()){
        loop7@while(context.hasNext()){
         when(context.c){
          '.'->{
           context.buffer.append(context.c)
           context.next()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
          context.buffer.append(context.c)
          context.next()
          continue@loop6
         }
         else->{
          throw Exception("unexpected char $c")
         }
        }
       }
       onBLANK_NODE_LABEL()
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
  else->{
   throw Exception("unexpected char $c")
  }
 }
}
inline fun parse_base(context:ParserContext,
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
inline fun parse_prefix(context:ParserContext,
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
inline fun parse_prefix2(context:ParserContext,
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
inline fun parse_predicate(context:ParserContext,
 crossinline onVERB1:()->Unit,
 crossinline onIRIREF:()->Unit,
 crossinline onPNAME_NS:()->Unit
){
 context.buffer.clear()
 when(context.c){
  'a'->{
   context.buffer.append(context.c)
   context.next()
   onVERB1()
   return
  }
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
  '_'->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    0x3a.toChar()->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      in (0x0.toChar()..0xffff.toChar()),in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
       context.buffer.append(context.c)
       context.next()
       loop6@while(context.hasNext()){
        loop7@while(context.hasNext()){
         when(context.c){
          '.'->{
           context.buffer.append(context.c)
           context.next()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
          context.buffer.append(context.c)
          context.next()
          continue@loop6
         }
         else->{
          throw Exception("unexpected char $c")
         }
        }
       }
       onBLANK_NODE_LABEL()
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
  0x22.toChar()->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
     context.buffer.append(context.c)
     context.next()
     loop4@while(context.hasNext()){
      when(context.c){
       in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
        context.buffer.append(context.c)
        context.next()
        continue@loop4
       }
       0x5c.toChar()->{
        context.buffer.append(context.c)
        context.next()
        when(context.c){
         0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
          context.buffer.append(context.c)
          context.next()
          continue@loop4
         }
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
                  continue@loop4
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
                          continue@loop4
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
        break@loop4
       }
      }
     }
     when(context.c){
      0x22.toChar()->{
       context.buffer.append(context.c)
       context.next()
       onSTRING_LITERAL_QUOTE()
       return
      }
      else->{
       throw Exception("unexpected char $c")
      }
     }
    }
    0x5c.toChar()->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
       context.buffer.append(context.c)
       context.next()
       loop6@while(context.hasNext()){
        when(context.c){
         in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
          context.buffer.append(context.c)
          context.next()
          continue@loop6
         }
         0x5c.toChar()->{
          context.buffer.append(context.c)
          context.next()
          when(context.c){
           0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
            context.buffer.append(context.c)
            context.next()
            continue@loop6
           }
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
                    continue@loop6
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
                            continue@loop6
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
          break@loop6
         }
        }
       }
       when(context.c){
        0x22.toChar()->{
         context.buffer.append(context.c)
         context.next()
         onSTRING_LITERAL_QUOTE()
         return
        }
        else->{
         throw Exception("unexpected char $c")
        }
       }
      }
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
               loop14@while(context.hasNext()){
                when(context.c){
                 in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
                  context.buffer.append(context.c)
                  context.next()
                  continue@loop14
                 }
                 0x5c.toChar()->{
                  context.buffer.append(context.c)
                  context.next()
                  when(context.c){
                   0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
                    context.buffer.append(context.c)
                    context.next()
                    continue@loop14
                   }
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
                            continue@loop14
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
                                    continue@loop14
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
                  break@loop14
                 }
                }
               }
               when(context.c){
                0x22.toChar()->{
                 context.buffer.append(context.c)
                 context.next()
                 onSTRING_LITERAL_QUOTE()
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
                       loop22@while(context.hasNext()){
                        when(context.c){
                         in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
                          context.buffer.append(context.c)
                          context.next()
                          continue@loop22
                         }
                         0x5c.toChar()->{
                          context.buffer.append(context.c)
                          context.next()
                          when(context.c){
                           0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
                            context.buffer.append(context.c)
                            context.next()
                            continue@loop22
                           }
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
                                    continue@loop22
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
                                            continue@loop22
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
                          break@loop22
                         }
                        }
                       }
                       when(context.c){
                        0x22.toChar()->{
                         context.buffer.append(context.c)
                         context.next()
                         onSTRING_LITERAL_QUOTE()
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
    0x22.toChar()->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      0x22.toChar()->{
       context.buffer.append(context.c)
       context.next()
       loop6@while(context.hasNext()){
        when(context.c){
         in (0x0.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
          context.buffer.append(context.c)
          context.next()
          continue@loop6
         }
         0x5c.toChar()->{
          context.buffer.append(context.c)
          context.next()
          when(context.c){
           0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
            context.buffer.append(context.c)
            context.next()
            continue@loop6
           }
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
                    continue@loop6
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
                            continue@loop6
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
         0x22.toChar()->{
          context.buffer.append(context.c)
          context.next()
          when(context.c){
           in (0x0.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
            context.buffer.append(context.c)
            context.next()
            continue@loop6
           }
           0x5c.toChar()->{
            context.buffer.append(context.c)
            context.next()
            when(context.c){
             0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
              context.buffer.append(context.c)
              context.next()
              continue@loop6
             }
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
                      continue@loop6
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
                              continue@loop6
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
           0x22.toChar()->{
            context.buffer.append(context.c)
            context.next()
            when(context.c){
             in (0x0.toChar()..'!'),in ('#'..'['),in (']'..0xffff.toChar())->{
              context.buffer.append(context.c)
              context.next()
              continue@loop6
             }
             0x5c.toChar()->{
              context.buffer.append(context.c)
              context.next()
              when(context.c){
               0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
                context.buffer.append(context.c)
                context.next()
                continue@loop6
               }
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
                        continue@loop6
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
                                continue@loop6
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
             0x22.toChar()->{
              context.buffer.append(context.c)
              context.next()
              onSTRING_LITERAL_LONG_QUOTE()
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
         else->{
          break@loop6
         }
        }
       }
       throw Exception("unexpected char $c")
      }
      else->{
       onSTRING_LITERAL_QUOTE()
       return
      }
     }
    }
    else->{
     throw Exception("unexpected char $c")
    }
   }
  }
  0x27.toChar()->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
     context.buffer.append(context.c)
     context.next()
     loop4@while(context.hasNext()){
      when(context.c){
       in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
        context.buffer.append(context.c)
        context.next()
        continue@loop4
       }
       0x5c.toChar()->{
        context.buffer.append(context.c)
        context.next()
        when(context.c){
         0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
          context.buffer.append(context.c)
          context.next()
          continue@loop4
         }
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
                  continue@loop4
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
                          continue@loop4
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
        break@loop4
       }
      }
     }
     when(context.c){
      0x27.toChar()->{
       context.buffer.append(context.c)
       context.next()
       onSTRING_LITERAL_SINGLE_QUOTE()
       return
      }
      else->{
       throw Exception("unexpected char $c")
      }
     }
    }
    0x5c.toChar()->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
       context.buffer.append(context.c)
       context.next()
       loop6@while(context.hasNext()){
        when(context.c){
         in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
          context.buffer.append(context.c)
          context.next()
          continue@loop6
         }
         0x5c.toChar()->{
          context.buffer.append(context.c)
          context.next()
          when(context.c){
           0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
            context.buffer.append(context.c)
            context.next()
            continue@loop6
           }
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
                    continue@loop6
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
                            continue@loop6
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
          break@loop6
         }
        }
       }
       when(context.c){
        0x27.toChar()->{
         context.buffer.append(context.c)
         context.next()
         onSTRING_LITERAL_SINGLE_QUOTE()
         return
        }
        else->{
         throw Exception("unexpected char $c")
        }
       }
      }
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
               loop14@while(context.hasNext()){
                when(context.c){
                 in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
                  context.buffer.append(context.c)
                  context.next()
                  continue@loop14
                 }
                 0x5c.toChar()->{
                  context.buffer.append(context.c)
                  context.next()
                  when(context.c){
                   0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
                    context.buffer.append(context.c)
                    context.next()
                    continue@loop14
                   }
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
                            continue@loop14
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
                                    continue@loop14
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
                  break@loop14
                 }
                }
               }
               when(context.c){
                0x27.toChar()->{
                 context.buffer.append(context.c)
                 context.next()
                 onSTRING_LITERAL_SINGLE_QUOTE()
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
                       loop22@while(context.hasNext()){
                        when(context.c){
                         in (0x0.toChar()..0x9.toChar()),in (0xb.toChar()..0xc.toChar()),in (0xe.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
                          context.buffer.append(context.c)
                          context.next()
                          continue@loop22
                         }
                         0x5c.toChar()->{
                          context.buffer.append(context.c)
                          context.next()
                          when(context.c){
                           0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
                            context.buffer.append(context.c)
                            context.next()
                            continue@loop22
                           }
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
                                    continue@loop22
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
                                            continue@loop22
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
                          break@loop22
                         }
                        }
                       }
                       when(context.c){
                        0x27.toChar()->{
                         context.buffer.append(context.c)
                         context.next()
                         onSTRING_LITERAL_SINGLE_QUOTE()
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
    0x27.toChar()->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      0x27.toChar()->{
       context.buffer.append(context.c)
       context.next()
       loop6@while(context.hasNext()){
        when(context.c){
         in (0x0.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
          context.buffer.append(context.c)
          context.next()
          continue@loop6
         }
         0x5c.toChar()->{
          context.buffer.append(context.c)
          context.next()
          when(context.c){
           0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
            context.buffer.append(context.c)
            context.next()
            continue@loop6
           }
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
                    continue@loop6
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
                            continue@loop6
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
         0x27.toChar()->{
          context.buffer.append(context.c)
          context.next()
          when(context.c){
           in (0x0.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
            context.buffer.append(context.c)
            context.next()
            continue@loop6
           }
           0x5c.toChar()->{
            context.buffer.append(context.c)
            context.next()
            when(context.c){
             0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
              context.buffer.append(context.c)
              context.next()
              continue@loop6
             }
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
                      continue@loop6
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
                              continue@loop6
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
           0x27.toChar()->{
            context.buffer.append(context.c)
            context.next()
            when(context.c){
             in (0x0.toChar()..'&'),in ('('..'['),in (']'..0xffff.toChar())->{
              context.buffer.append(context.c)
              context.next()
              continue@loop6
             }
             0x5c.toChar()->{
              context.buffer.append(context.c)
              context.next()
              when(context.c){
               0x22.toChar(),0x27.toChar(),0x5c.toChar(),'b','f','n','r','t'->{
                context.buffer.append(context.c)
                context.next()
                continue@loop6
               }
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
                        continue@loop6
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
                                continue@loop6
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
             0x27.toChar()->{
              context.buffer.append(context.c)
              context.next()
              onSTRING_LITERAL_LONG_SINGLE_QUOTE()
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
         else->{
          break@loop6
         }
        }
       }
       throw Exception("unexpected char $c")
      }
      else->{
       onSTRING_LITERAL_SINGLE_QUOTE()
       return
      }
     }
    }
    else->{
     throw Exception("unexpected char $c")
    }
   }
  }
  in ('0'..'9')->{
   context.buffer.append(context.c)
   context.next()
   loop2@while(context.hasNext()){
    when(context.c){
     in ('0'..'9')->{
      context.buffer.append(context.c)
      context.next()
     }
     else->{
      break@loop2
     }
    }
   }
   when(context.c){
    '.'->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      in ('0'..'9')->{
       context.buffer.append(context.c)
       context.next()
       loop6@while(context.hasNext()){
        when(context.c){
         in ('0'..'9')->{
          context.buffer.append(context.c)
          context.next()
         }
         else->{
          break@loop6
         }
        }
       }
       when(context.c){
        'E','e'->{
         context.buffer.append(context.c)
         context.next()
         when(context.c){
          in ('0'..'9')->{
           context.buffer.append(context.c)
           context.next()
           loop10@while(context.hasNext()){
            when(context.c){
             in ('0'..'9')->{
              context.buffer.append(context.c)
              context.next()
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
           context.buffer.append(context.c)
           context.next()
           when(context.c){
            in ('0'..'9')->{
             context.buffer.append(context.c)
             context.next()
             loop12@while(context.hasNext()){
              when(context.c){
               in ('0'..'9')->{
                context.buffer.append(context.c)
                context.next()
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
         onDECIMAL()
         return
        }
       }
      }
      'E','e'->{
       context.buffer.append(context.c)
       context.next()
       when(context.c){
        in ('0'..'9')->{
         context.buffer.append(context.c)
         context.next()
         loop8@while(context.hasNext()){
          when(context.c){
           in ('0'..'9')->{
            context.buffer.append(context.c)
            context.next()
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
         context.buffer.append(context.c)
         context.next()
         when(context.c){
          in ('0'..'9')->{
           context.buffer.append(context.c)
           context.next()
           loop10@while(context.hasNext()){
            when(context.c){
             in ('0'..'9')->{
              context.buffer.append(context.c)
              context.next()
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
    'E','e'->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      in ('0'..'9')->{
       context.buffer.append(context.c)
       context.next()
       loop6@while(context.hasNext()){
        when(context.c){
         in ('0'..'9')->{
          context.buffer.append(context.c)
          context.next()
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
       context.buffer.append(context.c)
       context.next()
       when(context.c){
        in ('0'..'9')->{
         context.buffer.append(context.c)
         context.next()
         loop8@while(context.hasNext()){
          when(context.c){
           in ('0'..'9')->{
            context.buffer.append(context.c)
            context.next()
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
     onINTEGER()
     return
    }
   }
  }
  '+','-'->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    in ('0'..'9')->{
     context.buffer.append(context.c)
     context.next()
     loop4@while(context.hasNext()){
      when(context.c){
       in ('0'..'9')->{
        context.buffer.append(context.c)
        context.next()
       }
       else->{
        break@loop4
       }
      }
     }
     when(context.c){
      '.'->{
       context.buffer.append(context.c)
       context.next()
       when(context.c){
        in ('0'..'9')->{
         context.buffer.append(context.c)
         context.next()
         loop8@while(context.hasNext()){
          when(context.c){
           in ('0'..'9')->{
            context.buffer.append(context.c)
            context.next()
           }
           else->{
            break@loop8
           }
          }
         }
         when(context.c){
          'E','e'->{
           context.buffer.append(context.c)
           context.next()
           when(context.c){
            in ('0'..'9')->{
             context.buffer.append(context.c)
             context.next()
             loop12@while(context.hasNext()){
              when(context.c){
               in ('0'..'9')->{
                context.buffer.append(context.c)
                context.next()
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
             context.buffer.append(context.c)
             context.next()
             when(context.c){
              in ('0'..'9')->{
               context.buffer.append(context.c)
               context.next()
               loop14@while(context.hasNext()){
                when(context.c){
                 in ('0'..'9')->{
                  context.buffer.append(context.c)
                  context.next()
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
           onDECIMAL()
           return
          }
         }
        }
        'E','e'->{
         context.buffer.append(context.c)
         context.next()
         when(context.c){
          in ('0'..'9')->{
           context.buffer.append(context.c)
           context.next()
           loop10@while(context.hasNext()){
            when(context.c){
             in ('0'..'9')->{
              context.buffer.append(context.c)
              context.next()
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
           context.buffer.append(context.c)
           context.next()
           when(context.c){
            in ('0'..'9')->{
             context.buffer.append(context.c)
             context.next()
             loop12@while(context.hasNext()){
              when(context.c){
               in ('0'..'9')->{
                context.buffer.append(context.c)
                context.next()
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
      'E','e'->{
       context.buffer.append(context.c)
       context.next()
       when(context.c){
        in ('0'..'9')->{
         context.buffer.append(context.c)
         context.next()
         loop8@while(context.hasNext()){
          when(context.c){
           in ('0'..'9')->{
            context.buffer.append(context.c)
            context.next()
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
         context.buffer.append(context.c)
         context.next()
         when(context.c){
          in ('0'..'9')->{
           context.buffer.append(context.c)
           context.next()
           loop10@while(context.hasNext()){
            when(context.c){
             in ('0'..'9')->{
              context.buffer.append(context.c)
              context.next()
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
       onINTEGER()
       return
      }
     }
    }
    '.'->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      in ('0'..'9')->{
       context.buffer.append(context.c)
       context.next()
       loop6@while(context.hasNext()){
        when(context.c){
         in ('0'..'9')->{
          context.buffer.append(context.c)
          context.next()
         }
         else->{
          break@loop6
         }
        }
       }
       when(context.c){
        'E','e'->{
         context.buffer.append(context.c)
         context.next()
         when(context.c){
          in ('0'..'9')->{
           context.buffer.append(context.c)
           context.next()
           loop10@while(context.hasNext()){
            when(context.c){
             in ('0'..'9')->{
              context.buffer.append(context.c)
              context.next()
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
           context.buffer.append(context.c)
           context.next()
           when(context.c){
            in ('0'..'9')->{
             context.buffer.append(context.c)
             context.next()
             loop12@while(context.hasNext()){
              when(context.c){
               in ('0'..'9')->{
                context.buffer.append(context.c)
                context.next()
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
         onDECIMAL()
         return
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
  '.'->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    in ('0'..'9')->{
     context.buffer.append(context.c)
     context.next()
     loop4@while(context.hasNext()){
      when(context.c){
       in ('0'..'9')->{
        context.buffer.append(context.c)
        context.next()
       }
       else->{
        break@loop4
       }
      }
     }
     when(context.c){
      'E','e'->{
       context.buffer.append(context.c)
       context.next()
       when(context.c){
        in ('0'..'9')->{
         context.buffer.append(context.c)
         context.next()
         loop8@while(context.hasNext()){
          when(context.c){
           in ('0'..'9')->{
            context.buffer.append(context.c)
            context.next()
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
         context.buffer.append(context.c)
         context.next()
         when(context.c){
          in ('0'..'9')->{
           context.buffer.append(context.c)
           context.next()
           loop10@while(context.hasNext()){
            when(context.c){
             in ('0'..'9')->{
              context.buffer.append(context.c)
              context.next()
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
       onDECIMAL()
       return
      }
     }
    }
    else->{
     throw Exception("unexpected char $c")
    }
   }
  }
  't'->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    'r'->{
     context.buffer.append(context.c)
     context.next()
     when(context.c){
      'u'->{
       context.buffer.append(context.c)
       context.next()
       when(context.c){
        'e'->{
         context.buffer.append(context.c)
         context.next()
         when(context.c){
          'f'->{
           context.buffer.append(context.c)
           context.next()
           when(context.c){
            'a'->{
             context.buffer.append(context.c)
             context.next()
             when(context.c){
              'l'->{
               context.buffer.append(context.c)
               context.next()
               when(context.c){
                's'->{
                 context.buffer.append(context.c)
                 context.next()
                 when(context.c){
                  'e'->{
                   context.buffer.append(context.c)
                   context.next()
                   onBOOLEAN()
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
inline fun parse_triple_end(context:ParserContext,
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
inline fun parse_triple_end_or_object_iri(context:ParserContext,
 crossinline onSKIP_WS_FORCED:()->Unit,
 crossinline onPN_LOCAL:()->Unit,
 crossinline onPREDICATE_LIST1:()->Unit,
 crossinline onOBJECT_LIST1:()->Unit,
 crossinline onDOT:()->Unit,
 crossinline onSKIP_WS_FORCED:()->Unit
){
 context.buffer.clear()
 when(context.c){
  0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
   context.buffer.append(context.c)
   context.next()
   loop2@while(context.hasNext()){
    when(context.c){
     0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
      context.buffer.append(context.c)
      context.next()
     }
     else->{
      break@loop2
     }
    }
   }
   onSKIP_WS_FORCED()
   return
  }
  in (0x0.toChar()..0xffff.toChar()),in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
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
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.buffer.append(context.c)
      context.next()
      continue@loop2
     }
     '%'->{
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
     0x5c.toChar()->{
      context.buffer.append(context.c)
      context.next()
      when(context.c){
       '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
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
   onPN_LOCAL()
   return
  }
  '%'->{
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
       loop6@while(context.hasNext()){
        loop7@while(context.hasNext()){
         when(context.c){
          '.'->{
           context.buffer.append(context.c)
           context.next()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
          context.buffer.append(context.c)
          context.next()
          continue@loop6
         }
         '%'->{
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
              continue@loop6
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
         0x5c.toChar()->{
          context.buffer.append(context.c)
          context.next()
          when(context.c){
           '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
            context.buffer.append(context.c)
            context.next()
            continue@loop6
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
       onPN_LOCAL()
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
  0x5c.toChar()->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
     context.buffer.append(context.c)
     context.next()
     loop4@while(context.hasNext()){
      loop5@while(context.hasNext()){
       when(context.c){
        '.'->{
         context.buffer.append(context.c)
         context.next()
        }
        else->{
         break@loop5
        }
       }
      }
      when(context.c){
       in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
        context.buffer.append(context.c)
        context.next()
        continue@loop4
       }
       '%'->{
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
            continue@loop4
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
       0x5c.toChar()->{
        context.buffer.append(context.c)
        context.next()
        when(context.c){
         '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
          context.buffer.append(context.c)
          context.next()
          continue@loop4
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
     onPN_LOCAL()
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
inline fun parse_triple_end_or_obj_string(context:ParserContext,
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
  0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
   context.buffer.append(context.c)
   context.next()
   loop2@while(context.hasNext()){
    when(context.c){
     0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
      context.buffer.append(context.c)
      context.next()
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
   throw Exception("unexpected char $c")
  }
 }
}
inline fun parse_triple_end_or_obj_string_typed(context:ParserContext,
 crossinline onIRIREF:()->Unit,
 crossinline onPNAME_NS:()->Unit
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
inline fun parse_triple_end_or_obj_string_typed_iri(context:ParserContext,
 crossinline onPN_LOCAL:()->Unit,
 crossinline onPREDICATE_LIST1:()->Unit,
 crossinline onOBJECT_LIST1:()->Unit,
 crossinline onDOT:()->Unit
){
 context.buffer.clear()
 when(context.c){
  in (0x0.toChar()..0xffff.toChar()),in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
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
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.buffer.append(context.c)
      context.next()
      continue@loop2
     }
     '%'->{
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
     0x5c.toChar()->{
      context.buffer.append(context.c)
      context.next()
      when(context.c){
       '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
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
   onPN_LOCAL()
   return
  }
  '%'->{
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
       loop6@while(context.hasNext()){
        loop7@while(context.hasNext()){
         when(context.c){
          '.'->{
           context.buffer.append(context.c)
           context.next()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
          context.buffer.append(context.c)
          context.next()
          continue@loop6
         }
         '%'->{
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
              continue@loop6
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
         0x5c.toChar()->{
          context.buffer.append(context.c)
          context.next()
          when(context.c){
           '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
            context.buffer.append(context.c)
            context.next()
            continue@loop6
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
       onPN_LOCAL()
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
  0x5c.toChar()->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
     context.buffer.append(context.c)
     context.next()
     loop4@while(context.hasNext()){
      loop5@while(context.hasNext()){
       when(context.c){
        '.'->{
         context.buffer.append(context.c)
         context.next()
        }
        else->{
         break@loop5
        }
       }
      }
      when(context.c){
       in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
        context.buffer.append(context.c)
        context.next()
        continue@loop4
       }
       '%'->{
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
            continue@loop4
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
       0x5c.toChar()->{
        context.buffer.append(context.c)
        context.next()
        when(context.c){
         '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
          context.buffer.append(context.c)
          context.next()
          continue@loop4
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
     onPN_LOCAL()
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
inline fun parse_subject_iri_or_ws(context:ParserContext,
 crossinline onPN_LOCAL:()->Unit,
 crossinline onSKIP_WS_FORCED:()->Unit
){
 context.buffer.clear()
 when(context.c){
  in (0x0.toChar()..0xffff.toChar()),in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
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
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.buffer.append(context.c)
      context.next()
      continue@loop2
     }
     '%'->{
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
     0x5c.toChar()->{
      context.buffer.append(context.c)
      context.next()
      when(context.c){
       '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
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
   onPN_LOCAL()
   return
  }
  '%'->{
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
       loop6@while(context.hasNext()){
        loop7@while(context.hasNext()){
         when(context.c){
          '.'->{
           context.buffer.append(context.c)
           context.next()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
          context.buffer.append(context.c)
          context.next()
          continue@loop6
         }
         '%'->{
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
              continue@loop6
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
         0x5c.toChar()->{
          context.buffer.append(context.c)
          context.next()
          when(context.c){
           '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
            context.buffer.append(context.c)
            context.next()
            continue@loop6
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
       onPN_LOCAL()
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
  0x5c.toChar()->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
     context.buffer.append(context.c)
     context.next()
     loop4@while(context.hasNext()){
      loop5@while(context.hasNext()){
       when(context.c){
        '.'->{
         context.buffer.append(context.c)
         context.next()
        }
        else->{
         break@loop5
        }
       }
      }
      when(context.c){
       in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
        context.buffer.append(context.c)
        context.next()
        continue@loop4
       }
       '%'->{
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
            continue@loop4
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
       0x5c.toChar()->{
        context.buffer.append(context.c)
        context.next()
        when(context.c){
         '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
          context.buffer.append(context.c)
          context.next()
          continue@loop4
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
     onPN_LOCAL()
     return
    }
    else->{
     throw Exception("unexpected char $c")
    }
   }
  }
  0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
   context.buffer.append(context.c)
   context.next()
   loop2@while(context.hasNext()){
    when(context.c){
     0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
      context.buffer.append(context.c)
      context.next()
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
   throw Exception("unexpected char $c")
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
     in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
      context.buffer.append(context.c)
      context.next()
      continue@loop2
     }
     '%'->{
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
     0x5c.toChar()->{
      context.buffer.append(context.c)
      context.next()
      when(context.c){
       '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
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
   onPN_LOCAL()
   return
  }
  '%'->{
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
       loop6@while(context.hasNext()){
        loop7@while(context.hasNext()){
         when(context.c){
          '.'->{
           context.buffer.append(context.c)
           context.next()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
          context.buffer.append(context.c)
          context.next()
          continue@loop6
         }
         '%'->{
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
              continue@loop6
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
         0x5c.toChar()->{
          context.buffer.append(context.c)
          context.next()
          when(context.c){
           '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
            context.buffer.append(context.c)
            context.next()
            continue@loop6
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
       onPN_LOCAL()
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
  0x5c.toChar()->{
   context.buffer.append(context.c)
   context.next()
   when(context.c){
    '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
     context.buffer.append(context.c)
     context.next()
     loop4@while(context.hasNext()){
      loop5@while(context.hasNext()){
       when(context.c){
        '.'->{
         context.buffer.append(context.c)
         context.next()
        }
        else->{
         break@loop5
        }
       }
      }
      when(context.c){
       in (0x0.toChar()..0xffff.toChar()),'-',in ('0'..'9'),0x3a.toChar(),in ('A'..'Z'),'_',in ('a'..'z'),0xb7.toChar(),in (0xc0.toChar()..0xd6.toChar()),in (0xd8.toChar()..0xf6.toChar()),in (0xf8.toChar()..0x2ff.toChar()),in (0x300.toChar()..0x36f.toChar()),in (0x370.toChar()..0x37d.toChar()),in (0x37f.toChar()..0x1fff.toChar()),in (0x200c.toChar()..0x200d.toChar()),in (0x203f.toChar()..0x2040.toChar()),in (0x2070.toChar()..0x218f.toChar()),in (0x2c00.toChar()..0x2fef.toChar()),in (0x3001.toChar()..0xd7ff.toChar()),in (0xf900.toChar()..0xfdcf.toChar()),in (0xfdf0.toChar()..0xfffd.toChar())->{
        context.buffer.append(context.c)
        context.next()
        continue@loop4
       }
       '%'->{
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
            continue@loop4
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
       0x5c.toChar()->{
        context.buffer.append(context.c)
        context.next()
        when(context.c){
         '!','#','$','%','&',0x27.toChar(),'(',')','*','+',',','-','.','/',';','=','?','@','_','~'->{
          context.buffer.append(context.c)
          context.next()
          continue@loop4
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
     onPN_LOCAL()
     return
    }
    else->{
     throw Exception("unexpected char $c")
    }
   }
  }
  0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
   context.buffer.append(context.c)
   context.next()
   loop2@while(context.hasNext()){
    when(context.c){
     0x9.toChar(),0xa.toChar(),0xd.toChar(),' '->{
      context.buffer.append(context.c)
      context.next()
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
   throw Exception("unexpected char $c")
  }
 }
}
