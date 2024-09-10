package calculatingMachine.lv2;

import calculatingMachine.lv2.operation.*;
import java.util.ArrayList;

public class Calculator {
    private final ArrayList<Double> arrNumbers = new ArrayList<Double>();
    private AbstOperation operation;
    private double firstNumber;
    private double secondNumber;

    public void setOperation(AbstOperation operation) {
        this.operation = operation;
    }

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    // 들어온 연산 기호에 따라 operation을 담는다.
    public void setUpOperation(String operation) {
        switch (operation) {
            case "+":
                setOperation(new AddOperation());
                break;
            case "-":
                setOperation(new SubstractOperaction());
                break;
            case "*":
                setOperation(new MultiplyOperation());
                break;
            case "/":
                setOperation(new DivideOperation());
                break;
        }
    }

    // 연산 후 연산 결과 리스트에 저장 및 전달.
    public double calculator() {
       double result =  operation.operate(firstNumber, secondNumber);
       arrNumbers.add(result);
       return result;
    }

    // arrNumbers 리스트에 저장된 모든 값 전달
    public void getArrNumbersAll() {
        int num = 1;
        for (Double arrNumber : arrNumbers) {
            System.out.println(num+". "+arrNumber);
            num++;
        }
    }

    // 원하는 방번호의 값 전달
    public double getArrNumber(int idxNumber) {
        return arrNumbers.get(idxNumber);
    }

    // arrNumbers 리스트에 저장된 값 수정
    public void setArrNumbers(int idxNumber, double changeNumber) {
        arrNumbers.set(idxNumber, changeNumber);
    }

    // arrNumbers 리스트의 저장된 맨 처음 값 삭제
    public void removeArrNumber() {
            arrNumbers.remove(0);
    }

    // 컬렉션에 저장된 데이터가 하나도 없으면 true 리턴
    public boolean arrNumbersIsEmpty(){
        return arrNumbers.isEmpty();
    }
}
