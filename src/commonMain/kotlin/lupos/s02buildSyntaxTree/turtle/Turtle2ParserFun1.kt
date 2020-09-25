package lupos.s02buildSyntaxTree.turtle

inline fun parseStatement(context:ParserContext,
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
