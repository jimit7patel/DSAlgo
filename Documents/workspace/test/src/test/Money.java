package test;
import java.lang.Math;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays; 
import java.util.List; 
public class Money { private BigDecimal value; public Money() {}; public Money(BigDecimal value) {  this.value = value; } public void setValue(BigDecimal value) { this.value = value; } public void setValue(String value) { this.value = new BigDecimal(value);  } public BigDecimal getDollars() { return value.setScale(0, RoundingMode.FLOOR); } public BigDecimal getCents() { return value.subtract(getDollars());  } public Money multiplyBy(BigDecimal value) { return new Money(value.multiply(value).setScale(2, RoundingMode.HALF_UP));  } public String toString() { return value.toString(); } }