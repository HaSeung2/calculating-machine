package calculatingMachine.lv1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // exit를 입력받으면 flag를 false로 바꿔 while문 종료.
        boolean flag = true;
        // 연산 결과를 저장하기 위한 공간
        int result = 0;
        int firstNumber = 0;
        String operation = "";
        int secondNumber = 0;

        while (flag) {
            // 정수가 아닌 다른 타입의 문자를 입력을 방지하기위해 try ~ catch 문을 이용
            try {
                System.out.print("숫자 입력 : ");
                firstNumber = sc.nextInt();
                // nextLine을 쓸때 위에 nextLine이 아닌 다른 입력이 있으면 엔터키를 하나 더 먹는 이슈가 있어서 방패막으로 의미없는 line써줌.
                sc.nextLine();
                System.out.print("사칙연산 기호 입력(+,-,*,/) : ");
                operation = sc.nextLine();
                System.out.print("숫자 입력 : ");
                secondNumber = sc.nextInt();
            }
            // 정수가 아닌 다른 타입의 문자를 입력했다면 아래에 문구를 출력 후 실행 중지.
            catch (InputMismatchException e) {
                System.out.println("정수만 입력해주세요 !");
                System.out.println("다시 실행해주세요 ~ ");
                break;
            }
            switch (operation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    try {
                        result = firstNumber / secondNumber;
                        break;
                    } catch (ArithmeticException e) {
                        System.out.println("정수는 0으로 나눌 수 없습니다.");
                        continue;
                    }
                    // +,-,*,/ 가 아닌 다른 것을 입력하면 다시 입력해주세요라는 출력문과 함께
                    // switch문 반복
                default:
                    System.out.println("연산기호를 다시 확인해주세요 !");
                    continue;
            }
            System.out.println("결과 " + result);
            System.out.print("더 계산하시겠습니까 ? (exit 입력시 종료) : ");
            // nextLine으로 입력을 받으면 엔터가 자동으로 돼서 if문을 거치지 않고 바로 넘어가버린다.
            String answer = sc.next();
            // 입력된 값이 exit라면 flag를 false로 만들어서 while문 탈출
            if (answer.equals("exit")) {
                flag = false;
            }
            // 그게 아니라면 while문 반복
            else {
                flag = true;
            }
        }
    }
}