package calculatingMachine.lv3;

import calculatingMachine.lv3.operation.OperatorType;

import java.util.List;
import java.util.regex.Pattern;

public class CalculatorApp {
    private final ArithmeticCalculator<Double> doubleCalculator = new ArithmeticCalculator<>();
    private OperatorType operator;

    // 정수, 실수, 연산 기호 유효성 검사
    enum Reg {
        OPERATION_REG("[+\\-*/]"),
        NUMBER_REG("^[0-9]*$"),
        DOUBLE_LEG("^([0-9]{1}\\d{0,2}|0{1})(\\.{1}\\d{0,1})*$");

        private final String reg;

        Reg(String reg) {
            this.reg = reg;
        }
    }

    // 입력받아온 숫자가 숫자형식인지 정규식으로 비교한 후 숫자가 맞다면
    // double 형태로 형변환을 하는 동시에 firstNumber에 값을 저장.
    public void setFirstNumber(String firstNumber) throws Exception {
        if (!Pattern.matches(Reg.NUMBER_REG.reg, firstNumber) || !Pattern.matches(Reg.DOUBLE_LEG.reg, firstNumber)) {
            throw new BadInputException("정수, 실수");
        }
        doubleCalculator.setFirstNumber(Double.parseDouble(firstNumber));
    }

    // 입력받아온 숫자가 숫자형식인지 정규식으로 비교한 후 숫자가 맞다면
    // double 형태로 형변환을 하는 동시에 secondNumber에 값을 저장.
    public void setSecondNumber(String secondNumber) throws Exception {
        if (!Pattern.matches(Reg.DOUBLE_LEG.reg, secondNumber) || !Pattern.matches(Reg.DOUBLE_LEG.reg, secondNumber)) {
            throw new BadInputException("정수, 실수");
        }
        doubleCalculator.setSecondNumber(Double.parseDouble(secondNumber));
    }

    // 리스트의 저장된 모든 값을 가져와서 스트림을이용해 sout로 값들 출력
    public void getArrayList() {
        List<Double> list = doubleCalculator.getArrayList();
        // stream forEach문을 이용하여 sout를 출력하려 했지만
        // 람다 표현식에 사용되는 변수는 final 또는 유사 final이어야 하기 때문에 리스트의 값들 앞에 1. 2. 이런식으로 붙여줄 수가 없다.
        //list.stream().toList().forEach(li -> System.out.println(li.doubleValue()));
        int num = 1;
        // 그래서 향상된 for문을 이용하여 출력해준다.
        for (Double li : list) {
            System.out.println(num + ". " + li);
            num++;
        }
    }

    // 입력 받은 연산 기호가 연산기호 이외에 다른 문자가 아닌지 판별 후
    // 우리가 정해둔 연산기호가 맞다면 OperatorType 클래스에 만들어둔 getOp 메서드 실행.
    // 아니라면 예외 처리
    public void setUpOperation(String operation) throws Exception {
        if (!Pattern.matches(Reg.OPERATION_REG.reg, operation)) {
            throw new BadInputException("연산 기호");
        }
        this.operator = OperatorType.getOperator(operation);
    }

    // 받아온 인덱스 번호의 값 전달해주는 메서드 실행.
    public double getArrNumber(int idxNumber) {
        return doubleCalculator.getArrNumber(idxNumber);
    }

    // 연산과 연산 결과 리스트의 저장하는 메서드 실행
    public double calculator() {
        double x = doubleCalculator.getFirstNumber();
        double z = doubleCalculator.getSecondNumber();
        double result = operator.calculate(x, z);
        doubleCalculator.add(result);
        return result;
    }

    // 입력 받은 값보다 큰 값들 리스트 받아서 빠른for문으로 sout 출력
    public void getLargeNumber(double number) {
        List<Double> list = doubleCalculator.getArrayList();
        List<Double> lageNumbers = operator.getLargeNumber(list, number);
        int num = 1;
        for (double li : lageNumbers) {
            System.out.println(num + ". " + li);
            num++;
        }
    }

    // 받아온 입력값이 정수or실수가 맞는지 확인 후 맞다면 리스트 값을 수정해주는 set 메서드 실행
    // 아니라면 예외처리
    public void setArrNumber(String targetNumber, String changeValue) throws Exception {
        if (!Pattern.matches(Reg.NUMBER_REG.reg, targetNumber) || !Pattern.matches(Reg.DOUBLE_LEG.reg, changeValue)) {
            throw new BadInputException("정수");
        }
        // 존재하는 리스트의 배열 길이를 초과하는 번호 입력 시 예외 처리를 해준다.
        else if(Integer.parseInt(targetNumber) > doubleCalculator.getArrayList().size()) {
            throw new IndexOutOfBoundsException("번호를 잘 확인해주세요");
        }
        int idxNumber = Integer.parseInt(targetNumber) - 1;
        doubleCalculator.setArrValue(idxNumber, Double.parseDouble(changeValue));
    }

    // 가장 맨 앞에 저장되어 있는 데이터 삭제하는 메서드 실행
    public void indexZeroRemove() {
        // 컬렉션에 저장된 데이터가 하나도 없을때는 더 이상 삭제할 데이터가 없다는 문구 출력
        if (doubleCalculator.arrayIsEmpty()) {
            System.out.println("더 이상 삭제할 데이터가 없습니다.");
        }
        // 데이터가 하나 이상있다면 제일 먼저 저장된 0번방의 데이터를 삭제 후
        // 삭제 완료 문구 출력
        else {
            doubleCalculator.indexZeroRemove();
            System.out.println("삭제 완료 !");
        }
    }

    // 가장 높은 값 전달
    public double getMaxNumber(){
        // doubleCalculator.getArrayList 메서드는 리스트 형태로 값을 전달하기 때문에
        // getMaxNumber 매개변수로 바로 전달한다.
        return operator.getMaxNumber(doubleCalculator.getArrayList());
    }

    // 가장 작은 값 전달
    public double getMinNumber(){
        // doubleCalculator.getArrayList 메서드는 리스트 형태로 값을 전달하기 때문에
        // getMaxNumber 매개변수로 바로 전달한다.
        return operator.getMinNumber(doubleCalculator.getArrayList());
    }

    // 원하는 방의 데이터 삭제
    public void wantIndexRemove(int idxNumber){
        doubleCalculator.wantIndexRemove(idxNumber);
    }
}
