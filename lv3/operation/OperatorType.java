package calculatingMachine.lv3.operation;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
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
        Divide ("/", (x,z) -> x / z);

        private final String symbol;
        private final BiFunction<Double, Double, Double> function;

        OperatorType(String symbol, BiFunction<Double, Double, Double> function) {
        this.symbol = symbol;
        this.function = function;
        }

        public double calculate(double x, double z) {
         return function.apply(x, z);
        }

        // static을 붙이지 않으면 this.operator is null이라는 오류가 발생하는데 왜 그런건지 모르겠다.
        public static OperatorType getOperator(String operator){
        return Arrays.stream(OperatorType.values()).filter(oper -> oper.symbol.equals(operator)).findFirst().
                orElseThrow(() -> new IllegalArgumentException("Unknown operator: " + operator));
        }

        //  리스트의 저장된 값들 중에서 입력받은 값보다 큰 값들만 추출하여 전달
        public List <Double> getLargeNumber(List<Double> list, double number){
          List <Double> nums = list.stream().filter(num -> num > number).toList();
          return nums;
        }

        // 리스트의 저장된 값들 중 가장 높은 값 전달.
        public double getMaxNumber(List<Double> list){
                // mapToDouble은 스트림을 DoubleStream으로 변환해주는 메소드이다.
                // 이 외에도 mapToInt, mapToLong, mapToObject 메서드들이 있다.
          return list.stream().mapToDouble(li -> li).max().orElseThrow(NoSuchElementException::new);
        }

        // 리스트의 저장된 값들 중 가장 작은 값 전달.
        public double getMinNumber(List <Double> list){
          return list.stream().mapToDouble(li -> li).min().orElseThrow(NoSuchElementException::new);
        }
}
