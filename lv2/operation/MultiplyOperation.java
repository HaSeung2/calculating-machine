package calculatingMachine.lv2.operation;

public class MultiplyOperation extends AbstOperation{
    @Override
    public double operate(double firstNumber, double secondNumber) {
        return firstNumber * secondNumber;
    }
}
