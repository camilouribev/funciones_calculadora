
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

final public class Main {
    static IntBinaryOperator sum = (num1, num2) -> num1 + num2;
    static IntBinaryOperator subtract = (num1, num2) -> num1- num2;

    static IntBinaryOperator multiply= (num1, num2) -> {
        Integer absNum1 = Math.abs(num1);
        Integer absNum2 = Math.abs(num2);
        Integer result =  IntStream.range(0, absNum2+1).
                reduce((acc, current)->sum.applyAsInt(absNum1, acc)).
                getAsInt();

        return (num1 < 0 && num2 < 0)  || (num1 > 0 && num2 > 0)  ? result : -result;
    };

    final static IntBinaryOperator divide = (dividend, divisor) -> {
       if (divisor == 0) throw new IllegalArgumentException("0 is not a valid number");
        Integer absDividend = Math.abs(dividend);
        Integer absDivisor = Math.abs(divisor);
        Integer result = IntStream.range(0, absDividend+1)
                .reduce((acc, num) ->( multiply.applyAsInt(num, absDivisor) <= absDividend) ? sum.applyAsInt(acc,1): acc)
                .orElse(0);
        return (dividend < 0 && divisor < 0)  || (dividend > 0 && divisor > 0)  ? result : -result;
    };

    public static void main(String[] args) {

        System.out.println(multiply.applyAsInt(12,-5));
        System.out.println(divide.applyAsInt(12,-5));

    }


}
