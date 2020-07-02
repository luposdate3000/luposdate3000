package lupos.s00misc
import java.math.RoundingMode
import java.lang.ArithmeticException
object DecimalHelper {
fun add(a:BigDecimal,b:BigDecimal):BigDecimal{
try{
return a .add (b,MathContext.UNLIMITED)
}catch(e:ArithmeticException){
return a .add (b,MathContext.DECIMAL128)
}
}
fun subtract(a:BigDecimal,b:BigDecimal):BigDecimal{
try{
return a .subtract (b,MathContext.UNLIMITED)
}catch(e:ArithmeticException){
return a .subtract (b,MathContext.DECIMAL128)
}
}
fun multiply(a:BigDecimal,b:BigDecimal):BigDecimal{
try{
return a .multiply (b,MathContext.UNLIMITED)
}catch(e:ArithmeticException){
return a .multiply (b,MathContext.DECIMAL128)
}
}
fun divide(a:BigDecimal,b:BigDecimal):BigDecimal{
try{
return a .divide (b,MathContext.UNLIMITED)
}catch(e:ArithmeticException){
return a .divide (b,MathContext.DECIMAL128)
}
}
fun ceil(a:BigDecimal):BigDecimal{
return a.setScale(0,RoundingMode.CEILING)
}
fun floor(a:BigDecimal):BigDecimal{
return a.setScale(0,RoundingMode.FLOOR)
}
fun round(a:BigDecimal):BigDecimal{
return a.setScale(0,RoundingMode.HALF_UP)
}
}
