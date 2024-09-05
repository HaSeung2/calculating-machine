package calculatingMachine.lv2;

import java.util.regex.Pattern;

public class CalculatorApp {
    private final Calculator calculator = new Calculator();
    private static final String OPERATION_REG = "[+\\-*/]";
    private static final String NUMBER_REG = "^[0-9]*$";
    private static final String Double_REG = "^([0-9]{1}\\d{0,2}|0{1})(\\.{1}\\d{0,1})*$";
    private double firstNumber;
    private double secondNumber;

    public void setOperation(String operation) {
        calculator.setUpOperation(operation);
    }

    public void setFirstNumber(String firstNumber) throws Exception {
        if(Pattern.matches(Double_REG,firstNumber)){
            this.firstNumber = Double.parseDouble(firstNumber);
        }
        else{
            throw new BadInputException("정수, 실수");
        }
    }
    public void setSecondNumber(String secondNumber) throws Exception {
        if(Pattern.matches(Double_REG,secondNumber)){
            this.secondNumber = Double.parseDouble(secondNumber);
        }
        else{
            throw new BadInputException("정수, 실수");
        }
    }

    public void getArrNumbersAll(){
        calculator.getArrNumbersAll();
    }

    public void setUpOperation(String operation) throws Exception{
        if(Pattern.matches(OPERATION_REG, operation)){
            calculator.setUpOperation(operation);
        }
        else{
            throw new BadInputException("연산 기호");
        }
    }

    public double getArrNumber(int idxNumber){
        return calculator.getArrNumber(idxNumber);
    }

    public double calculator(){
        return calculator.calculator(this.firstNumber,this.secondNumber);
    }

    public void setArrNumber(String targetNumber, String changeNumber) throws Exception{
        if(Pattern.matches(NUMBER_REG, targetNumber)){
           if(Pattern.matches(Double_REG, changeNumber)){
               int idxNumber = Integer.parseInt(targetNumber)-1;
               calculator.setArrNumbers(idxNumber, Double.parseDouble(changeNumber));
           }
           else{
               throw new BadInputException("정수");
           }
        }
        else{
            throw new BadInputException("정수");
        }
    }

    public void removeArrNumber(){
        calculator.removeArrNumber();
    }
}
