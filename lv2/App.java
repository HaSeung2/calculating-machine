package calculatingMachine.lv2;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();
        // exit를 입력받으면 flag를 false로 바꿔 while문 종료.
        boolean flag = true;
        // 연산 결과를 저장하기 위한 공간
        int result = 0;

        while (flag) {
            System.out.print("숫자 입력 : ");
            int firstNumber = sc.nextInt();
            // nextLine을 쓸때 위에 nextLine이 아닌 다른 입력이 있으면 엔터키를 하나 더 먹는 이슈가 있어서 방패막으로 의미없는 line써줌.
            sc.nextLine();
            System.out.print("사칙연산 기호 입력(+,-,*,/) : ");
            String operation = sc.nextLine();
            System.out.print("숫자 입력 : ");
            int secondNumber = sc.nextInt();

            calculator.calculator(operation, firstNumber, secondNumber);
            System.out.print("더 계산하시겠습니까 ? (exit 입력시 종료) : ");
            String answer = sc.next();
            // 입력된 값이 exit라면 flag를 false로 만들어서 while문 탈출
            if (answer.equals("exit")) {
                flag = false;
            }
            // 그게 아니라면 while문 반복
            else{
                flag = true;
            }
        }
        // 리스트의 저장된 값 모두 가져옴
        calculator.getArrNumbersAll();

        // 보내준 idxNumber에 해당하는 값 가져옴
        int arrGetNum = calculator.getArrNumber(0);

        // 리스트의 저장된 데이터 수정
        calculator.setArrNumbers(0,30);
        System.out.println();
        calculator.getArrNumbersAll();

        // 리스트에 가장 먼저 담긴 데이터 삭제
        calculator.removeArrNumber();
        System.out.println();
        calculator.getArrNumbersAll();
    }
}