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

    // 입력받아온 숫자가 숫자형식인지 정규식으로 비교한 후 숫자가 맞다면
    // double 형태로 형변환을 하는 동시에 firstNumber에 값을 저장.
    public void setFirstNumber(String firstNumber) throws Exception {
        if(Pattern.matches(Double_REG,firstNumber)){
            this.firstNumber = Double.parseDouble(firstNumber);
        }
        else{
            throw new BadInputException("정수, 실수");
        }
    }
    // 입력받아온 숫자가 숫자형식인지 정규식으로 비교한 후 숫자가 맞다면
    // double 형태로 형변환을 하는 동시에 secondNumber에 값을 저장.
    public void setSecondNumber(String secondNumber) throws Exception {
        if(Pattern.matches(Double_REG,secondNumber)){
            this.secondNumber = Double.parseDouble(secondNumber);
        }
        else{
            throw new BadInputException("정수, 실수");
        }
    }

    // 리스트의 저장된 모든 값을 가져옴
    public void getArrNumbersAll(){
        calculator.getArrNumbersAll();
    }

    // 입력 받은 연산 기호가 연산기호 이외에 다른 문자가 아닌지 판별 후
    // 우리가 정해둔 연산기호가 맞다면 setUpOperation 메서드를 실행.
    // 아니라면 예외 처리
    public void setUpOperation(String operation) throws Exception{
        if(Pattern.matches(OPERATION_REG, operation)){
            calculator.setUpOperation(operation);
        }
        else{
            throw new BadInputException("연산 기호");
        }
    }

    // 받아온 인덱스 번호의 값 전달해주는 메서드 실행.
    public double getArrNumber(int idxNumber){
        return calculator.getArrNumber(idxNumber);
    }

    // 연산과 연산 결과 리스트의 저장하는 메서드 실행
    public double calculator(){
        return calculator.calculator(this.firstNumber,this.secondNumber);
    }

    // 받아온 입력값이 정수or실수가 맞는지 확인 후 맞다면 리스트 값을 수정해주는 set 메서드 실행
    // 아니라면 예외처리
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
    
    // 가장 맨 앞에 저장되어 있는 데이터 삭제하는 메서드 실행
    public void removeArrNumber(){
        calculator.removeArrNumber();
    }
}
