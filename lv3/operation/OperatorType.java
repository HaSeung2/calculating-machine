package calculatingMachine.lv3.operation;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum OperatorType {
//    익명 클래스
//   Add{
//      @Override
//      public double operate(double firstNumber, double secondNumber) {
//         return firstNumber + secondNumber;
//      }
//   },
//   Divide{
//      @Override
//      public double operate(double firstNumber, double secondNumber) {
//         return firstNumber / secondNumber;
//      }
//   },
//   Multiply{
//      @Override
//      public double operate(double firstNumber, double secondNumber) {
//         return firstNumber * secondNumber;
//      }
//   },
//   Subtract{
//      @Override
//      public double operate(double firstNumber, double secondNumber) {
//         return firstNumber - secondNumber;
//      }
//   };
//
//   public abstract double operate(double firstNumber, double secondNumber);

        //람다식 이용
        Add ("+", Double::sum),
        Subtract ("-", (x,z) -> x - z),
        Multiply ("*", (x,z) -> x * z),
        Divide ("/", (x,z) -> {
                if(z == 0){
                        throw new ArithmeticException("0으로 나눌 수 없습니다");
                }
                     return x / z;
        });

        private final String symbol;
        private final BiFunction<Double, Double, Double> function;

        OperatorType(String symbol, BiFunction<Double, Double, Double> function) {
        this.symbol = symbol;
        this.function = function;
        }

        public double calculate(double x, double z) {
         return function.apply(x, z);
        }

        public static OperatorType getOperator(String operator){
        return Arrays.stream(OperatorType.values()).filter(oper -> oper.symbol.equals(operator)).findFirst().
                orElseThrow(() -> new IllegalArgumentException("Unknown operator: " + operator));
        }
}
