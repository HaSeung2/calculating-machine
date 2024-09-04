package calculatingMachine.lv2;

import java.util.ArrayList;

public class Calculator {
    private ArrayList<Integer> arrNumbers = new ArrayList<Integer>();
    int result;

    // 연산 후 연산 결과 리스트에 저장 및 전달.
    public void calculator(String operation, int firstNumber, int secondNumber) {
        boolean flag = true;
        // while 조건문을 flag = true로 해두고 반복하게함.
        while (flag) {
            switch (operation) {
                case "+":
                    arrNumbers.add(firstNumber + secondNumber);
                    break;
                case "-":
                    arrNumbers.add(firstNumber - secondNumber);
                    break;
                case "*":
                    arrNumbers.add(firstNumber * secondNumber);
                    break;
                case "/":
                    arrNumbers.add(firstNumber / secondNumber);
                    break;
            }
            // break로 스위치 문을 빠져나온다면 flag를 false로 바꾸어서
            // while문 탈출
            flag = false;
        }
            result = arrNumbers.get(arrNumbers.size() - 1);
            System.out.println("결과는 : " + result);
    }

    // arrNumbers 리스트에 저장된 모든 값 전달
    public void getArrNumbersAll() {
        int num = 1;
        for (Integer arrNumber : arrNumbers) {
            System.out.println(num+". "+arrNumber);
            num++;
        }
    }

    // 원하는 방번호의 값 전달
    public int getArrNumber(int idxNumber) {
        return arrNumbers.get(idxNumber);
    }

    // arrNumbers 리스트에 저장된 값 수정
    public void setArrNumbers(int targetNumber, int changeNumber) {
        int idxNumber = targetNumber-1;
        arrNumbers.set(idxNumber, changeNumber);
    }

    // arrNumbers 리스트의 저장된 맨 처음 값 삭제
    public void removeArrNumber() {
        // 컬렉션에 저장된 데이터가 하나도 없을때는 더 이상 삭제할 데이터가 없다는 문구 출력
        if (arrNumbers.isEmpty()) {
            System.out.println("더 이상 삭제할 데이터가 없습니다.");
        }
        // 데이터가 하나 이상있다면 제일 먼저 저장된 0번방의 데이터를 삭제 후
        // 삭제 완료 문구 출력
        else {
            arrNumbers.remove(0);
        }
    }
}
