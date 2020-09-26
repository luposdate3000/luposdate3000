package lupos.s02buildSyntaxTree.turtle
import kotlin.jvm.JvmField
import lupos.s00misc.Luposdate3000Exception
import lupos.s00misc.MyInputStream
open class ParserException(msg:String):Luposdate3000Exception("ParserContext",msg)
class ParserExceptionEOF():ParserException("EOF")
class ParserExceptionUnexpectedChar(context:ParserContext):ParserException("unexpected char 0x${context.c.toString(16)} at ${context.line}:${context.column}")
class ParserContext(val input:MyInputStream){
 companion object{
  const val EOF=0x7fffffff.toInt()
 }
 @JvmField var c:Int=0
 @JvmField var buffer=StringBuilder()
 @JvmField var line=1
 @JvmField var column=0
 fun next(){
  val tmp=(c=='\r'.toInt()) || (c=='\n'.toInt())
  if(!hasNext()){//append 1 whitespace to the end of the input to prevent unexpected crashes
   if(c==EOF){
    throw ParserExceptionEOF()
   } else {
    c=EOF
    return
   }
  }
  val t:Int=input.next() and 0xff
  if((t and 0x80)==0){
   //1byte
   c=t
  }else if((t and 0x20)==0){
   //2byte
   c=(t and 0x1f) shl 6
   c=c or (input.next() and 0x3f)
  }else if((t and 0x10)==0){
   //3byte
   c=(t and 0x0f) shl 12
   c=c or (input.next() and 0x3f) shl 6
   c=c or (input.next() and 0x3f)
  }else{
   //4byte
   c=(t and 0x07) shl 18
   c=c or (input.next() and 0x3f) shl 12
   c=c or (input.next() and 0x3f) shl 6
   c=c or (input.next() and 0x3f)
  }
  if((c=='\r'.toInt()) || (c=='\n'.toInt())){
   if(!tmp){
    line++
    column=1
   }
  } else {
   column++
  }
 }
 fun append(){
  if(c<=0xd7ff ||(c>=0xe000 && c<=0xffff)){
   buffer.append(c.toChar())
  }else{
   c-=0x100000
   buffer.append((0xd800+((c shr 10)and 0x03ff)).toChar())
   buffer.append((0xdc00+(c and 0x03ff)).toChar())
  }
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
  0x2e->{
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
 loop0@while(context.c!=ParserContext.EOF){
  when(context.c){
   in (0x9..0xa),0xd,0x20->{
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
  in (0x9..0xa),0xd,0x20->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    when(context.c){
     in (0x9..0xa),0xd,0x20->{
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
  0x42->{
   context.append()
   when(context.c){
    0x41->{
     context.append()
     when(context.c){
      0x53->{
       context.append()
       when(context.c){
        0x45->{
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
  0x50->{
   context.append()
   when(context.c){
    0x52->{
     context.append()
     when(context.c){
      0x45->{
       context.append()
       when(context.c){
        0x46->{
         context.append()
         when(context.c){
          0x49->{
           context.append()
           when(context.c){
            0x58->{
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
  0x40->{
   context.append()
   when(context.c){
    0x62->{
     context.append()
     when(context.c){
      0x61->{
       context.append()
       when(context.c){
        0x73->{
         context.append()
         when(context.c){
          0x65->{
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
    0x70->{
     context.append()
     when(context.c){
      0x72->{
       context.append()
       when(context.c){
        0x65->{
         context.append()
         when(context.c){
          0x66->{
           context.append()
           when(context.c){
            0x69->{
             context.append()
             when(context.c){
              0x78->{
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
  0x3c->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    when(context.c){
     0x21,in (0x23..0x3b),0x3d,in (0x3f..0x5b),0x5d,0x5f,in (0x61..0x7a),in (0x7e..0x3fffff)->{
      context.append()
      continue@loop2
     }
     0x5c->{
      context.append()
      when(context.c){
       0x75->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
       0x55->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
    0x3e->{
     context.append()
     onIRIREF()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  in (0x41..0x5a),in (0x61..0x7a),in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x2ff),in (0x370..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    loop3@while(context.c!=ParserContext.EOF){
     when(context.c){
      0x2e->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     0x2d,in (0x30..0x39),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
      context.append()
      continue@loop2
     }
     else->{
      break@loop2
     }
    }
   }
   when(context.c){
    0x3a->{
     context.append()
     onPNAME_NS()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x3a->{
   context.append()
   onPNAME_NS()
   return
  }
  0x5f->{
   context.append()
   when(context.c){
    0x3a->{
     context.append()
     when(context.c){
      in (0x30..0x39),in (0x41..0x5a),0x5f,in (0x61..0x7a),in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x2ff),in (0x370..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
       context.append()
       loop6@while(context.c!=ParserContext.EOF){
        loop7@while(context.c!=ParserContext.EOF){
         when(context.c){
          0x2e->{
           context.append()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         0x2d,in (0x30..0x39),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
          context.append()
          continue@loop6
         }
         else->{
          break@loop6
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
  0x3c->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    when(context.c){
     0x21,in (0x23..0x3b),0x3d,in (0x3f..0x5b),0x5d,0x5f,in (0x61..0x7a),in (0x7e..0x3fffff)->{
      context.append()
      continue@loop2
     }
     0x5c->{
      context.append()
      when(context.c){
       0x75->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
       0x55->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
    0x3e->{
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
  in (0x41..0x5a),in (0x61..0x7a),in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x2ff),in (0x370..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    loop3@while(context.c!=ParserContext.EOF){
     when(context.c){
      0x2e->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     0x2d,in (0x30..0x39),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
      context.append()
      continue@loop2
     }
     else->{
      break@loop2
     }
    }
   }
   when(context.c){
    0x3a->{
     context.append()
     onPNAME_NS()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x3a->{
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
  0x3c->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    when(context.c){
     0x21,in (0x23..0x3b),0x3d,in (0x3f..0x5b),0x5d,0x5f,in (0x61..0x7a),in (0x7e..0x3fffff)->{
      context.append()
      continue@loop2
     }
     0x5c->{
      context.append()
      when(context.c){
       0x75->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
       0x55->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
    0x3e->{
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
  0x61->{
   context.append()
   onVERB1()
   return
  }
  0x3c->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    when(context.c){
     0x21,in (0x23..0x3b),0x3d,in (0x3f..0x5b),0x5d,0x5f,in (0x61..0x7a),in (0x7e..0x3fffff)->{
      context.append()
      continue@loop2
     }
     0x5c->{
      context.append()
      when(context.c){
       0x75->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
       0x55->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
    0x3e->{
     context.append()
     onIRIREF()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  in (0x41..0x5a),in (0x61..0x7a),in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x2ff),in (0x370..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    loop3@while(context.c!=ParserContext.EOF){
     when(context.c){
      0x2e->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     0x2d,in (0x30..0x39),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
      context.append()
      continue@loop2
     }
     else->{
      break@loop2
     }
    }
   }
   when(context.c){
    0x3a->{
     context.append()
     onPNAME_NS()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x3a->{
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
  0x3c->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    when(context.c){
     0x21,in (0x23..0x3b),0x3d,in (0x3f..0x5b),0x5d,0x5f,in (0x61..0x7a),in (0x7e..0x3fffff)->{
      context.append()
      continue@loop2
     }
     0x5c->{
      context.append()
      when(context.c){
       0x75->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
       0x55->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
    0x3e->{
     context.append()
     onIRIREF()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  in (0x41..0x5a),in (0x61..0x7a),in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x2ff),in (0x370..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    loop3@while(context.c!=ParserContext.EOF){
     when(context.c){
      0x2e->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     0x2d,in (0x30..0x39),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
      context.append()
      continue@loop2
     }
     else->{
      break@loop2
     }
    }
   }
   when(context.c){
    0x3a->{
     context.append()
     onPNAME_NS()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x3a->{
   context.append()
   onPNAME_NS()
   return
  }
  0x5f->{
   context.append()
   when(context.c){
    0x3a->{
     context.append()
     when(context.c){
      in (0x30..0x39),in (0x41..0x5a),0x5f,in (0x61..0x7a),in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x2ff),in (0x370..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
       context.append()
       loop6@while(context.c!=ParserContext.EOF){
        loop7@while(context.c!=ParserContext.EOF){
         when(context.c){
          0x2e->{
           context.append()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         0x2d,in (0x30..0x39),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
          context.append()
          continue@loop6
         }
         else->{
          break@loop6
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
  0x22->{
   context.append()
   when(context.c){
    in (0x0..0x9),in (0xb..0xc),in (0xe..0x21),in (0x23..0x5b),in (0x5d..0x3fffff)->{
     context.append()
     loop4@while(context.c!=ParserContext.EOF){
      when(context.c){
       in (0x0..0x9),in (0xb..0xc),in (0xe..0x21),in (0x23..0x5b),in (0x5d..0x3fffff)->{
        context.append()
        continue@loop4
       }
       0x5c->{
        context.append()
        when(context.c){
         0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
          context.append()
          continue@loop4
         }
         0x75->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
         0x55->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
      0x22->{
       context.append()
       onSTRING_LITERAL_QUOTE()
       return
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    0x5c->{
     context.append()
     when(context.c){
      0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
       context.append()
       loop6@while(context.c!=ParserContext.EOF){
        when(context.c){
         in (0x0..0x9),in (0xb..0xc),in (0xe..0x21),in (0x23..0x5b),in (0x5d..0x3fffff)->{
          context.append()
          continue@loop6
         }
         0x5c->{
          context.append()
          when(context.c){
           0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
            context.append()
            continue@loop6
           }
           0x75->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
           0x55->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                          context.append()
                          when(context.c){
                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
        0x22->{
         context.append()
         onSTRING_LITERAL_QUOTE()
         return
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      0x75->{
       context.append()
       when(context.c){
        in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
         context.append()
         when(context.c){
          in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
           context.append()
           when(context.c){
            in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
             context.append()
             when(context.c){
              in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
               context.append()
               loop14@while(context.c!=ParserContext.EOF){
                when(context.c){
                 in (0x0..0x9),in (0xb..0xc),in (0xe..0x21),in (0x23..0x5b),in (0x5d..0x3fffff)->{
                  context.append()
                  continue@loop14
                 }
                 0x5c->{
                  context.append()
                  when(context.c){
                   0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
                    context.append()
                    continue@loop14
                   }
                   0x75->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                          context.append()
                          when(context.c){
                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
                   0x55->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                          context.append()
                          when(context.c){
                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                            context.append()
                            when(context.c){
                             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                              context.append()
                              when(context.c){
                               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                context.append()
                                when(context.c){
                                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                  context.append()
                                  when(context.c){
                                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
                0x22->{
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
      0x55->{
       context.append()
       when(context.c){
        in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
         context.append()
         when(context.c){
          in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
           context.append()
           when(context.c){
            in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
             context.append()
             when(context.c){
              in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
               context.append()
               when(context.c){
                in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                 context.append()
                 when(context.c){
                  in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                   context.append()
                   when(context.c){
                    in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                     context.append()
                     when(context.c){
                      in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                       context.append()
                       loop22@while(context.c!=ParserContext.EOF){
                        when(context.c){
                         in (0x0..0x9),in (0xb..0xc),in (0xe..0x21),in (0x23..0x5b),in (0x5d..0x3fffff)->{
                          context.append()
                          continue@loop22
                         }
                         0x5c->{
                          context.append()
                          when(context.c){
                           0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
                            context.append()
                            continue@loop22
                           }
                           0x75->{
                            context.append()
                            when(context.c){
                             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                              context.append()
                              when(context.c){
                               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                context.append()
                                when(context.c){
                                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                  context.append()
                                  when(context.c){
                                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
                           0x55->{
                            context.append()
                            when(context.c){
                             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                              context.append()
                              when(context.c){
                               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                context.append()
                                when(context.c){
                                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                  context.append()
                                  when(context.c){
                                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                    context.append()
                                    when(context.c){
                                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                      context.append()
                                      when(context.c){
                                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                        context.append()
                                        when(context.c){
                                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                          context.append()
                                          when(context.c){
                                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
                        0x22->{
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
    0x22->{
     context.append()
     when(context.c){
      0x22->{
       context.append()
       loop6@while(context.c!=ParserContext.EOF){
        when(context.c){
         in (0x0..0x21),in (0x23..0x5b),in (0x5d..0x3fffff)->{
          context.append()
          continue@loop6
         }
         0x5c->{
          context.append()
          when(context.c){
           0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
            context.append()
            continue@loop6
           }
           0x75->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
           0x55->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                          context.append()
                          when(context.c){
                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
         0x22->{
          context.append()
          when(context.c){
           in (0x0..0x21),in (0x23..0x5b),in (0x5d..0x3fffff)->{
            context.append()
            continue@loop6
           }
           0x5c->{
            context.append()
            when(context.c){
             0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
              context.append()
              continue@loop6
             }
             0x75->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
             0x55->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                          context.append()
                          when(context.c){
                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                            context.append()
                            when(context.c){
                             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
           0x22->{
            context.append()
            when(context.c){
             in (0x0..0x21),in (0x23..0x5b),in (0x5d..0x3fffff)->{
              context.append()
              continue@loop6
             }
             0x5c->{
              context.append()
              when(context.c){
               0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
                context.append()
                continue@loop6
               }
               0x75->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
               0x55->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                          context.append()
                          when(context.c){
                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                            context.append()
                            when(context.c){
                             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                              context.append()
                              when(context.c){
                               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
             0x22->{
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
  0x27->{
   context.append()
   when(context.c){
    in (0x0..0x9),in (0xb..0xc),in (0xe..0x26),in (0x28..0x5b),in (0x5d..0x3fffff)->{
     context.append()
     loop4@while(context.c!=ParserContext.EOF){
      when(context.c){
       in (0x0..0x9),in (0xb..0xc),in (0xe..0x26),in (0x28..0x5b),in (0x5d..0x3fffff)->{
        context.append()
        continue@loop4
       }
       0x5c->{
        context.append()
        when(context.c){
         0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
          context.append()
          continue@loop4
         }
         0x75->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
         0x55->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
      0x27->{
       context.append()
       onSTRING_LITERAL_SINGLE_QUOTE()
       return
      }
      else->{
       throw ParserExceptionUnexpectedChar(context)
      }
     }
    }
    0x5c->{
     context.append()
     when(context.c){
      0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
       context.append()
       loop6@while(context.c!=ParserContext.EOF){
        when(context.c){
         in (0x0..0x9),in (0xb..0xc),in (0xe..0x26),in (0x28..0x5b),in (0x5d..0x3fffff)->{
          context.append()
          continue@loop6
         }
         0x5c->{
          context.append()
          when(context.c){
           0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
            context.append()
            continue@loop6
           }
           0x75->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
           0x55->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                          context.append()
                          when(context.c){
                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
        0x27->{
         context.append()
         onSTRING_LITERAL_SINGLE_QUOTE()
         return
        }
        else->{
         throw ParserExceptionUnexpectedChar(context)
        }
       }
      }
      0x75->{
       context.append()
       when(context.c){
        in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
         context.append()
         when(context.c){
          in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
           context.append()
           when(context.c){
            in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
             context.append()
             when(context.c){
              in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
               context.append()
               loop14@while(context.c!=ParserContext.EOF){
                when(context.c){
                 in (0x0..0x9),in (0xb..0xc),in (0xe..0x26),in (0x28..0x5b),in (0x5d..0x3fffff)->{
                  context.append()
                  continue@loop14
                 }
                 0x5c->{
                  context.append()
                  when(context.c){
                   0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
                    context.append()
                    continue@loop14
                   }
                   0x75->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                          context.append()
                          when(context.c){
                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
                   0x55->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                          context.append()
                          when(context.c){
                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                            context.append()
                            when(context.c){
                             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                              context.append()
                              when(context.c){
                               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                context.append()
                                when(context.c){
                                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                  context.append()
                                  when(context.c){
                                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
                0x27->{
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
      0x55->{
       context.append()
       when(context.c){
        in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
         context.append()
         when(context.c){
          in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
           context.append()
           when(context.c){
            in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
             context.append()
             when(context.c){
              in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
               context.append()
               when(context.c){
                in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                 context.append()
                 when(context.c){
                  in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                   context.append()
                   when(context.c){
                    in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                     context.append()
                     when(context.c){
                      in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                       context.append()
                       loop22@while(context.c!=ParserContext.EOF){
                        when(context.c){
                         in (0x0..0x9),in (0xb..0xc),in (0xe..0x26),in (0x28..0x5b),in (0x5d..0x3fffff)->{
                          context.append()
                          continue@loop22
                         }
                         0x5c->{
                          context.append()
                          when(context.c){
                           0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
                            context.append()
                            continue@loop22
                           }
                           0x75->{
                            context.append()
                            when(context.c){
                             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                              context.append()
                              when(context.c){
                               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                context.append()
                                when(context.c){
                                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                  context.append()
                                  when(context.c){
                                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
                           0x55->{
                            context.append()
                            when(context.c){
                             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                              context.append()
                              when(context.c){
                               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                context.append()
                                when(context.c){
                                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                  context.append()
                                  when(context.c){
                                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                    context.append()
                                    when(context.c){
                                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                      context.append()
                                      when(context.c){
                                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                        context.append()
                                        when(context.c){
                                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                                          context.append()
                                          when(context.c){
                                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
                        0x27->{
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
    0x27->{
     context.append()
     when(context.c){
      0x27->{
       context.append()
       loop6@while(context.c!=ParserContext.EOF){
        when(context.c){
         in (0x0..0x26),in (0x28..0x5b),in (0x5d..0x3fffff)->{
          context.append()
          continue@loop6
         }
         0x5c->{
          context.append()
          when(context.c){
           0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
            context.append()
            continue@loop6
           }
           0x75->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
           0x55->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                          context.append()
                          when(context.c){
                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
         0x27->{
          context.append()
          when(context.c){
           in (0x0..0x26),in (0x28..0x5b),in (0x5d..0x3fffff)->{
            context.append()
            continue@loop6
           }
           0x5c->{
            context.append()
            when(context.c){
             0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
              context.append()
              continue@loop6
             }
             0x75->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
             0x55->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                          context.append()
                          when(context.c){
                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                            context.append()
                            when(context.c){
                             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
           0x27->{
            context.append()
            when(context.c){
             in (0x0..0x26),in (0x28..0x5b),in (0x5d..0x3fffff)->{
              context.append()
              continue@loop6
             }
             0x5c->{
              context.append()
              when(context.c){
               0x22,0x27,0x5c,0x62,0x66,0x6e,0x72,0x74->{
                context.append()
                continue@loop6
               }
               0x75->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
               0x55->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                        context.append()
                        when(context.c){
                         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                          context.append()
                          when(context.c){
                           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                            context.append()
                            when(context.c){
                             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                              context.append()
                              when(context.c){
                               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
             0x27->{
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
  in (0x30..0x39)->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    when(context.c){
     in (0x30..0x39)->{
      context.append()
     }
     else->{
      break@loop2
     }
    }
   }
   when(context.c){
    0x2e->{
     context.append()
     when(context.c){
      in (0x30..0x39)->{
       context.append()
       loop6@while(context.c!=ParserContext.EOF){
        when(context.c){
         in (0x30..0x39)->{
          context.append()
         }
         else->{
          break@loop6
         }
        }
       }
       when(context.c){
        0x45,0x65->{
         context.append()
         when(context.c){
          in (0x30..0x39)->{
           context.append()
           loop10@while(context.c!=ParserContext.EOF){
            when(context.c){
             in (0x30..0x39)->{
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
          0x2b,0x2d->{
           context.append()
           when(context.c){
            in (0x30..0x39)->{
             context.append()
             loop12@while(context.c!=ParserContext.EOF){
              when(context.c){
               in (0x30..0x39)->{
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
      0x45,0x65->{
       context.append()
       when(context.c){
        in (0x30..0x39)->{
         context.append()
         loop8@while(context.c!=ParserContext.EOF){
          when(context.c){
           in (0x30..0x39)->{
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
        0x2b,0x2d->{
         context.append()
         when(context.c){
          in (0x30..0x39)->{
           context.append()
           loop10@while(context.c!=ParserContext.EOF){
            when(context.c){
             in (0x30..0x39)->{
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
    0x45,0x65->{
     context.append()
     when(context.c){
      in (0x30..0x39)->{
       context.append()
       loop6@while(context.c!=ParserContext.EOF){
        when(context.c){
         in (0x30..0x39)->{
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
      0x2b,0x2d->{
       context.append()
       when(context.c){
        in (0x30..0x39)->{
         context.append()
         loop8@while(context.c!=ParserContext.EOF){
          when(context.c){
           in (0x30..0x39)->{
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
  0x2b,0x2d->{
   context.append()
   when(context.c){
    in (0x30..0x39)->{
     context.append()
     loop4@while(context.c!=ParserContext.EOF){
      when(context.c){
       in (0x30..0x39)->{
        context.append()
       }
       else->{
        break@loop4
       }
      }
     }
     when(context.c){
      0x2e->{
       context.append()
       when(context.c){
        in (0x30..0x39)->{
         context.append()
         loop8@while(context.c!=ParserContext.EOF){
          when(context.c){
           in (0x30..0x39)->{
            context.append()
           }
           else->{
            break@loop8
           }
          }
         }
         when(context.c){
          0x45,0x65->{
           context.append()
           when(context.c){
            in (0x30..0x39)->{
             context.append()
             loop12@while(context.c!=ParserContext.EOF){
              when(context.c){
               in (0x30..0x39)->{
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
            0x2b,0x2d->{
             context.append()
             when(context.c){
              in (0x30..0x39)->{
               context.append()
               loop14@while(context.c!=ParserContext.EOF){
                when(context.c){
                 in (0x30..0x39)->{
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
        0x45,0x65->{
         context.append()
         when(context.c){
          in (0x30..0x39)->{
           context.append()
           loop10@while(context.c!=ParserContext.EOF){
            when(context.c){
             in (0x30..0x39)->{
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
          0x2b,0x2d->{
           context.append()
           when(context.c){
            in (0x30..0x39)->{
             context.append()
             loop12@while(context.c!=ParserContext.EOF){
              when(context.c){
               in (0x30..0x39)->{
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
      0x45,0x65->{
       context.append()
       when(context.c){
        in (0x30..0x39)->{
         context.append()
         loop8@while(context.c!=ParserContext.EOF){
          when(context.c){
           in (0x30..0x39)->{
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
        0x2b,0x2d->{
         context.append()
         when(context.c){
          in (0x30..0x39)->{
           context.append()
           loop10@while(context.c!=ParserContext.EOF){
            when(context.c){
             in (0x30..0x39)->{
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
    0x2e->{
     context.append()
     when(context.c){
      in (0x30..0x39)->{
       context.append()
       loop6@while(context.c!=ParserContext.EOF){
        when(context.c){
         in (0x30..0x39)->{
          context.append()
         }
         else->{
          break@loop6
         }
        }
       }
       when(context.c){
        0x45,0x65->{
         context.append()
         when(context.c){
          in (0x30..0x39)->{
           context.append()
           loop10@while(context.c!=ParserContext.EOF){
            when(context.c){
             in (0x30..0x39)->{
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
          0x2b,0x2d->{
           context.append()
           when(context.c){
            in (0x30..0x39)->{
             context.append()
             loop12@while(context.c!=ParserContext.EOF){
              when(context.c){
               in (0x30..0x39)->{
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
  0x2e->{
   context.append()
   when(context.c){
    in (0x30..0x39)->{
     context.append()
     loop4@while(context.c!=ParserContext.EOF){
      when(context.c){
       in (0x30..0x39)->{
        context.append()
       }
       else->{
        break@loop4
       }
      }
     }
     when(context.c){
      0x45,0x65->{
       context.append()
       when(context.c){
        in (0x30..0x39)->{
         context.append()
         loop8@while(context.c!=ParserContext.EOF){
          when(context.c){
           in (0x30..0x39)->{
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
        0x2b,0x2d->{
         context.append()
         when(context.c){
          in (0x30..0x39)->{
           context.append()
           loop10@while(context.c!=ParserContext.EOF){
            when(context.c){
             in (0x30..0x39)->{
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
  0x74->{
   context.append()
   when(context.c){
    0x72->{
     context.append()
     when(context.c){
      0x75->{
       context.append()
       when(context.c){
        0x65->{
         context.append()
         when(context.c){
          0x66->{
           context.append()
           when(context.c){
            0x61->{
             context.append()
             when(context.c){
              0x6c->{
               context.append()
               when(context.c){
                0x73->{
                 context.append()
                 when(context.c){
                  0x65->{
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
  0x3b->{
   context.append()
   onPREDICATE_LIST1()
   return
  }
  0x2c->{
   context.append()
   onOBJECT_LIST1()
   return
  }
  0x2e->{
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
  in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x2ff),in (0x370..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    loop3@while(context.c!=ParserContext.EOF){
     when(context.c){
      0x2e->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     0x2d,in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
      context.append()
      continue@loop2
     }
     0x25->{
      context.append()
      when(context.c){
       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
     0x5c->{
      context.append()
      when(context.c){
       0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
        context.append()
        continue@loop2
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
   onPN_LOCAL()
   return
  }
  0x25->{
   context.append()
   when(context.c){
    in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
     context.append()
     when(context.c){
      in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
       context.append()
       loop6@while(context.c!=ParserContext.EOF){
        loop7@while(context.c!=ParserContext.EOF){
         when(context.c){
          0x2e->{
           context.append()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         0x2d,in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
          context.append()
          continue@loop6
         }
         0x25->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
         0x5c->{
          context.append()
          when(context.c){
           0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
            context.append()
            continue@loop6
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
  0x5c->{
   context.append()
   when(context.c){
    0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
     context.append()
     loop4@while(context.c!=ParserContext.EOF){
      loop5@while(context.c!=ParserContext.EOF){
       when(context.c){
        0x2e->{
         context.append()
        }
        else->{
         break@loop5
        }
       }
      }
      when(context.c){
       0x2d,in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
        context.append()
        continue@loop4
       }
       0x25->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
       0x5c->{
        context.append()
        when(context.c){
         0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
          context.append()
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
     onPN_LOCAL()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x3b->{
   context.append()
   onPREDICATE_LIST1()
   return
  }
  0x2c->{
   context.append()
   onOBJECT_LIST1()
   return
  }
  0x2e->{
   context.append()
   onDOT()
   return
  }
  in (0x9..0xa),0xd,0x20->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    when(context.c){
     in (0x9..0xa),0xd,0x20->{
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
  0x40->{
   context.append()
   when(context.c){
    in (0x41..0x5a),in (0x61..0x7a)->{
     context.append()
     loop4@while(context.c!=ParserContext.EOF){
      when(context.c){
       in (0x41..0x5a),in (0x61..0x7a)->{
        context.append()
       }
       else->{
        break@loop4
       }
      }
     }
     loop4@while(context.c!=ParserContext.EOF){
      when(context.c){
       0x2d->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x5a),in (0x61..0x7a)->{
          context.append()
          loop9@while(context.c!=ParserContext.EOF){
           when(context.c){
            in (0x30..0x39),in (0x41..0x5a),in (0x61..0x7a)->{
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
  0x5e->{
   context.append()
   when(context.c){
    0x5e->{
     context.append()
     onIRI1()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x3b->{
   context.append()
   onPREDICATE_LIST1()
   return
  }
  0x2c->{
   context.append()
   onOBJECT_LIST1()
   return
  }
  0x2e->{
   context.append()
   onDOT()
   return
  }
  in (0x9..0xa),0xd,0x20->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    when(context.c){
     in (0x9..0xa),0xd,0x20->{
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
  0x3c->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    when(context.c){
     0x21,in (0x23..0x3b),0x3d,in (0x3f..0x5b),0x5d,0x5f,in (0x61..0x7a),in (0x7e..0x3fffff)->{
      context.append()
      continue@loop2
     }
     0x5c->{
      context.append()
      when(context.c){
       0x75->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
       0x55->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
              context.append()
              when(context.c){
               in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                context.append()
                when(context.c){
                 in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                  context.append()
                  when(context.c){
                   in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                    context.append()
                    when(context.c){
                     in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
                      context.append()
                      when(context.c){
                       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
    0x3e->{
     context.append()
     onIRIREF()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  in (0x41..0x5a),in (0x61..0x7a),in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x2ff),in (0x370..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    loop3@while(context.c!=ParserContext.EOF){
     when(context.c){
      0x2e->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     0x2d,in (0x30..0x39),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
      context.append()
      continue@loop2
     }
     else->{
      break@loop2
     }
    }
   }
   when(context.c){
    0x3a->{
     context.append()
     onPNAME_NS()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x3a->{
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
  in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x2ff),in (0x370..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    loop3@while(context.c!=ParserContext.EOF){
     when(context.c){
      0x2e->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     0x2d,in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
      context.append()
      continue@loop2
     }
     0x25->{
      context.append()
      when(context.c){
       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
     0x5c->{
      context.append()
      when(context.c){
       0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
        context.append()
        continue@loop2
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
   onPN_LOCAL()
   return
  }
  0x25->{
   context.append()
   when(context.c){
    in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
     context.append()
     when(context.c){
      in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
       context.append()
       loop6@while(context.c!=ParserContext.EOF){
        loop7@while(context.c!=ParserContext.EOF){
         when(context.c){
          0x2e->{
           context.append()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         0x2d,in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
          context.append()
          continue@loop6
         }
         0x25->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
         0x5c->{
          context.append()
          when(context.c){
           0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
            context.append()
            continue@loop6
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
  0x5c->{
   context.append()
   when(context.c){
    0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
     context.append()
     loop4@while(context.c!=ParserContext.EOF){
      loop5@while(context.c!=ParserContext.EOF){
       when(context.c){
        0x2e->{
         context.append()
        }
        else->{
         break@loop5
        }
       }
      }
      when(context.c){
       0x2d,in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
        context.append()
        continue@loop4
       }
       0x25->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
       0x5c->{
        context.append()
        when(context.c){
         0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
          context.append()
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
     onPN_LOCAL()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  0x3b->{
   context.append()
   onPREDICATE_LIST1()
   return
  }
  0x2c->{
   context.append()
   onOBJECT_LIST1()
   return
  }
  0x2e->{
   context.append()
   onDOT()
   return
  }
  in (0x9..0xa),0xd,0x20->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    when(context.c){
     in (0x9..0xa),0xd,0x20->{
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
  in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x2ff),in (0x370..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    loop3@while(context.c!=ParserContext.EOF){
     when(context.c){
      0x2e->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     0x2d,in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
      context.append()
      continue@loop2
     }
     0x25->{
      context.append()
      when(context.c){
       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
     0x5c->{
      context.append()
      when(context.c){
       0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
        context.append()
        continue@loop2
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
   onPN_LOCAL()
   return
  }
  0x25->{
   context.append()
   when(context.c){
    in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
     context.append()
     when(context.c){
      in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
       context.append()
       loop6@while(context.c!=ParserContext.EOF){
        loop7@while(context.c!=ParserContext.EOF){
         when(context.c){
          0x2e->{
           context.append()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         0x2d,in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
          context.append()
          continue@loop6
         }
         0x25->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
         0x5c->{
          context.append()
          when(context.c){
           0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
            context.append()
            continue@loop6
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
  0x5c->{
   context.append()
   when(context.c){
    0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
     context.append()
     loop4@while(context.c!=ParserContext.EOF){
      loop5@while(context.c!=ParserContext.EOF){
       when(context.c){
        0x2e->{
         context.append()
        }
        else->{
         break@loop5
        }
       }
      }
      when(context.c){
       0x2d,in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
        context.append()
        continue@loop4
       }
       0x25->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
       0x5c->{
        context.append()
        when(context.c){
         0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
          context.append()
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
     onPN_LOCAL()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  in (0x9..0xa),0xd,0x20->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    when(context.c){
     in (0x9..0xa),0xd,0x20->{
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
  in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x2ff),in (0x370..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    loop3@while(context.c!=ParserContext.EOF){
     when(context.c){
      0x2e->{
       context.append()
      }
      else->{
       break@loop3
      }
     }
    }
    when(context.c){
     0x2d,in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
      context.append()
      continue@loop2
     }
     0x25->{
      context.append()
      when(context.c){
       in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
     0x5c->{
      context.append()
      when(context.c){
       0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
        context.append()
        continue@loop2
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
   onPN_LOCAL()
   return
  }
  0x25->{
   context.append()
   when(context.c){
    in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
     context.append()
     when(context.c){
      in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
       context.append()
       loop6@while(context.c!=ParserContext.EOF){
        loop7@while(context.c!=ParserContext.EOF){
         when(context.c){
          0x2e->{
           context.append()
          }
          else->{
           break@loop7
          }
         }
        }
        when(context.c){
         0x2d,in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
          context.append()
          continue@loop6
         }
         0x25->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
            context.append()
            when(context.c){
             in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
         0x5c->{
          context.append()
          when(context.c){
           0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
            context.append()
            continue@loop6
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
  0x5c->{
   context.append()
   when(context.c){
    0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
     context.append()
     loop4@while(context.c!=ParserContext.EOF){
      loop5@while(context.c!=ParserContext.EOF){
       when(context.c){
        0x2e->{
         context.append()
        }
        else->{
         break@loop5
        }
       }
      }
      when(context.c){
       0x2d,in (0x30..0x3a),in (0x41..0x5a),0x5f,in (0x61..0x7a),0xb7,in (0xc0..0xd6),in (0xd8..0xf6),in (0xf8..0x37d),in (0x37f..0x1fff),in (0x200c..0x200d),in (0x203f..0x2040),in (0x2070..0x218f),in (0x2c00..0x2fef),in (0x3001..0xd7ff),in (0xf900..0xfdcf),in (0xfdf0..0xfffd),in (0x10000..0x3fffff)->{
        context.append()
        continue@loop4
       }
       0x25->{
        context.append()
        when(context.c){
         in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
          context.append()
          when(context.c){
           in (0x30..0x39),in (0x41..0x46),in (0x61..0x66)->{
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
       0x5c->{
        context.append()
        when(context.c){
         0x21,in (0x23..0x2f),0x3b,0x3d,in (0x3f..0x40),0x5f,0x7e->{
          context.append()
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
     onPN_LOCAL()
     return
    }
    else->{
     throw ParserExceptionUnexpectedChar(context)
    }
   }
  }
  in (0x9..0xa),0xd,0x20->{
   context.append()
   loop2@while(context.c!=ParserContext.EOF){
    when(context.c){
     in (0x9..0xa),0xd,0x20->{
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
