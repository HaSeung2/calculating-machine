package calculatingMachine.lv2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();
        // exit를 입력받으면 flag를 false로 바꿔 while문 종료.
        boolean flag = true;
        // 연산 결과를 저장하기 위한 공간
        int result = 0;
        int firstNumber = 0;
        int secondNumber = 0;
        String operation = "";

        while (flag) {
            // 숫자가 아닌 다른것을 쳤을 때 예외 처리
            try {
                System.out.print("숫자 입력 : ");
                firstNumber = sc.nextInt();
                System.out.print("사칙연산 기호 입력(+,-,*,/) : ");
                operation = sc.next();
                System.out.print("숫자 입력 : ");
                secondNumber = sc.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("정수만 입력해주세요 !");
                // 왠지는 모르겠지만 맨 위로가서 다시 반복문을 수행할때 nextInt()에서 엔터키가 자동으로 먹는다 그것을 방지하기 위해
                // nextLine으로 막아준다.
                sc.nextLine();
                continue;
            }
            //사칙 연산 기호를 제대로 썼다면 메서드 실행.
            if (operation.equals("+") || operation.equals("-") || operation.equals("*") || operation.equals("/")) {
                try {
                    calculator.calculator(operation, firstNumber, secondNumber);
                }
                // 여기에 들어오는 경우는 정수를 0으로 나누려고 했을 때만 들어온다.
                catch (ArithmeticException e) {
                    System.out.println("정수는 0으로 나눌 수 없습니다 !");
                    continue;
                }
                try {
                    System.out.print("지금까지 계산하신 결과값들을 보시겠습니까 ? (Y, N) : ");
                    String resultAnswer = sc.next();
                    // Y or y 입력시 아래 if문 실행
                    if (resultAnswer.equals("Y") || resultAnswer.equals("y")) {
                        calculator.getArrNumbersAll();
                        System.out.print("수정하고 싶은 번호가 있으신가요 ? (Y, N) : ");
                        String setAnswer = sc.next();
                        if (setAnswer.equals("Y") || setAnswer.equals("y")) {
                            System.out.print("수정하고 싶으신 번호의 번호를 입력해주세요 : ");
                            int targetNumber = sc.nextInt();
                            System.out.print("뭐로 수정 하실건가요 ? : ");
                            int changeNumber = sc.nextInt();
                            try {
                                calculator.setArrNumbers(targetNumber, changeNumber);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("수정하고 싶은 번호의 번호를 다시 한번 확인해 주세요");
                                continue;
                            }
                            calculator.getArrNumbersAll();
                            System.out.print("맨 처음에 있는 숫자 삭제하실건가요 ? (Y, N) : ");
                            String removeAnswer = sc.next();
                            if (removeAnswer.equals("Y") || removeAnswer.equals("y")) {
                                calculator.removeArrNumber();
                                System.out.println("삭제 완료");
                                calculator.getArrNumbersAll();
                            }
                        }
                        System.out.print("더 계산하시겠습니까 ? (exit 입력시 종료) : ");
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
                catch (InputMismatchException e) {
                    System.out.println("정수를 입력해주세요");
                    // 왠지는 모르겠지만 맨 위로가서 다시 반복문을 수행할때 nextInt()에서 엔터키가 자동으로 먹는다 그것을 방지하기 위해
                    // nextLine으로 막아준다.
                    sc.nextLine();
                }
                // 사칙 연산 기호를 알맞게 입력하지 않았다면 다시 처음으로 돌아가 입력 다시
            }
            else{
                    System.out.println("사칙연산 기호 다시 입력해주세요.");
            }
        }
    }
}