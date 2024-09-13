package calculatingMachine.lv3;

import calculatingMachine.lv3.operation.OperatorType;
import calculatingMachine.lv3.reg.Reg;

import java.util.ArrayList;
import java.util.List;

public class CalculatorApp {
    private final ArithmeticCalculator<Double> doubleCalculator = new ArithmeticCalculator<>();
    private OperatorType operator;

    // 입력받아온 숫자가 숫자형식인지 정규식으로 비교한 후 숫자가 맞다면
    // double 형태로 형변환을 하는 동시에 firstNumber에 값을 저장.
    public void setFirstNumber(String firstNumber) throws Exception {
        if (Reg.matches(firstNumber)) {
            doubleCalculator.setFirstNumber(Double.parseDouble(firstNumber));
        }
    }

    // 입력받아온 숫자가 숫자형식인지 정규식으로 비교한 후 숫자가 맞다면
    // double 형태로 형변환을 하는 동시에 secondNumber에 값을 저장.
    public void setSecondNumber(String secondNumber) throws Exception {
        if (Reg.matches(secondNumber)) {
            doubleCalculator.setSecondNumber(Double.parseDouble(secondNumber));
        }
    }

    // 리스트의 저장된 모든 값을 가져와서 list 형식으로 전달
    public List<Double> getArrayList() {
        return doubleCalculator.getArrayList();
    }

    // 입력 받은 연산 기호가 연산기호 이외에 다른 문자가 아닌지 판별 후
    // 우리가 정해둔 연산기호가 맞다면 OperatorType 클래스에 만들어둔 getOp 메서드 실행.
    // 아니라면 예외 처리
    public void getOperationAndSet(String operation) throws Exception {
        if (Reg.matchesOperation(operation)) {
            this.operator = OperatorType.getOperator(operation);
        }
    }

    // 받아온 인덱스 번호의 값 전달해주는 메서드 실행.
    public double getArrNumber(int idxNumber) {
        return doubleCalculator.getArrNumber(idxNumber);
    }

    // 연산과 연산 결과 리스트의 저장하는 메서드 실행
    public double calculate() {
        double x = doubleCalculator.getFirstNumber();
        double z = doubleCalculator.getSecondNumber();
        double result = operator.calculate(x, z);
        doubleCalculator.add(result);
        return result;
    }

    // 입력 받은 값보다 큰 값들 리스트 형태로 받아서 전달.
    public List<Double> getLargeNumber(String number) throws Exception {
        List<Double> list = doubleCalculator.getArrayList();
        List <Double> largeNumbers = new ArrayList<>();
        if (Reg.matches(number)) {
            largeNumbers =  doubleCalculator.getLargeNumber(list, Double.parseDouble(number));
        }
        return largeNumbers;
    }

    // 받아온 입력값이 정수or실수가 맞는지 확인 후 맞다면 리스트 값을 수정해주는 set 메서드 실행
    // 아니라면 예외처리
    public void setArrNumber(String targetNumber, String changeValue) throws Exception {
        if (Reg.matcheNumbers(targetNumber, changeValue) && Integer.parseInt(targetNumber) <= doubleCalculator.getArrayList().size()) {
            int idxNumber = Integer.parseInt(targetNumber) - 1;
            doubleCalculator.setArrValue(idxNumber, Double.parseDouble(changeValue));
        }
        else{
        // 존재하는 리스트의 배열 길이를 초과하는 번호 입력 시 예외 처리를 해준다.
            throw new IndexOutOfBoundsException("번호를 잘 확인해주세요");
        }
    }

    // 가장 맨 앞에 저장되어 있는 데이터 삭제하는 메서드 실행
    public boolean indexZeroRemover() {
        return doubleCalculator.indexZeroRemover();
    }

    // 가장 높은 값 전달
    public double getMaxNumber(){
        // doubleCalculator.getArrayList 메서드는 리스트 형태로 값을 전달하기 때문에
        // getMaxNumber 매개변수로 바로 전달한다.
        return doubleCalculator.getMaxNumber(doubleCalculator.getArrayList());
    }

    // 가장 작은 값 전달
    public double getMinNumber(){
        // doubleCalculator.getArrayList 메서드는 리스트 형태로 값을 전달하기 때문에
        // getMaxNumber 매개변수로 바로 전달한다.
        return doubleCalculator.getMinNumber(doubleCalculator.getArrayList());
    }

    // 원하는 방의 데이터 삭제
    public void wantIndexRemove(int idxNumber){
        doubleCalculator.wantIndexRemove(idxNumber);
    }
}
