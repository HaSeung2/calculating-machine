package calculatingMachine.lv2.operation;

public class DivideOperation extends AbstOperation{
    @Override
    public double operate(double firstNumber, double secondNumber) {
        return firstNumber / secondNumber;
    }
}
