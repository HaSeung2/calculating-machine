package calculatingMachine.lv2;

import java.util.Scanner;

public class App {
    // appStart()안에서 CalculatorApp 클래스를 객체화 하고 숫자타입이나 연산자 기호 타입을 잘못 쳤을때 예외 발생으로
    // 반복문의 처음으로 돌아가 다시 시작할 때 리스트안의 내용들이 다 날라가는데
    // 클래스 필드의 선언해두고 실행을하면 예외 발생으로 반복문의 처음으로 돌아가 다시 시작해도 리스트 안의 내용들이 그대로 남아있다.
    // exit로 실행을 중지 하기 전까지 데이터들이 리스트안에 계속 쌓인다.
    // exit로 실행을 중지 하고 다시 재실행하면 리스트 데이터들은 다시 리셋된다.
    CalculatorApp app = new CalculatorApp();
    public static void main(String[] args) {
        boolean mainFlag = false;
        App apple = new App();
        //처음에 mainFlag에 false를 기본값으로 주고
        // app.appStart 메서드에서 마지막에 exit를 입력하면
        // return true를 받게 하여 while문 탈출.
        // 그 외에 예외처리나 exit를 입력하지 않을 시에는 계속 반복.
        while(!mainFlag) {
            try{
                mainFlag = apple.appStart();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    public boolean appStart() throws Exception {
        Scanner sc = new Scanner(System.in);
        // exit를 입력받으면 flag를 false로 바꿔 while문 종료.
        boolean flag = true;
        while (flag) {
            // 숫자가 아닌 다른것을 쳤을 때 예외 처리
            System.out.print("숫자 입력 : ");
            String firstNumber = sc.nextLine();
            app.setFirstNumber(firstNumber);
            System.out.print("사칙연산 기호 입력(+,-,*,/) : ");
            String operation = sc.nextLine();
            app.setUpOperation(operation);
            System.out.print("숫자 입력 : ");
            String secondNumber = sc.nextLine();
            app.setSecondNumber(secondNumber);

            // 0으로 나누기를 시도 할시에 이 if문 안으로 들어와 출력문 수행
            if (secondNumber.equals("0") && operation.equals("/")) {
                System.out.println("0으로는 나눌 수 없습니다.");
                continue;
            }
            System.out.println("결과 : " + app.calculator());
            System.out.print("지금까지 계산하신 결과값들을 보시겠습니까 ? (Y, N) : ");
            String resultAnswer = sc.nextLine();
            // Y or y 입력시 아래 if문 실행
            if (resultAnswer.equals("Y") || resultAnswer.equals("y")) {
                app.getArrNumbersAll();
                System.out.print("수정하고 싶은 번호가 있으신가요 ? (Y, N) : ");
                String setAnswer = sc.nextLine();
                // Y, y 이외에 다른 것 을 치면 아래로 내려가
                // 다시 시작하거나 exit를 입력하여 while문 탈출
                if (setAnswer.equals("Y") || setAnswer.equals("y")) {
                    app.getArrNumbersAll();
                    System.out.print("수정하고 싶으신 번호의 번호를 입력해주세요 : ");
                    String targetNumber = sc.nextLine();
                    System.out.print("뭐로 수정 하실건가요 ? : ");
                    String changeNumber = sc.nextLine();
                    app.setArrNumber(targetNumber, changeNumber);
                    app.getArrNumbersAll();
                    System.out.print("맨 처음에 있는 숫자 삭제하실건가요 ? (Y, N) : ");
                    String removeAnswer = sc.nextLine();
                    // Y, y 이외에 다른 것 을 치면 아래로 내려가
                    // 다시 시작하거나 exit를 입력하여 while문 탈출
                    if (removeAnswer.equals("Y") || removeAnswer.equals("y")) {
                        app.removeArrNumber();
                        app.getArrNumbersAll();
                    }
                }
            }
            System.out.print("더 계산하시겠습니까 ? (exit 입력시 종료) : ");
            String answer = sc.nextLine();
            // 입력된 값이 exit라면 true를 return하여
            // main 메서드의 while문 탈출.
            if (answer.equals("exit")) {
                return true;
            }
        }
        // 그게 아니라면 계속 반복.
        return false;
    }
}