package calculatingMachine.lv3;

public class BadInputException extends Exception{
    public BadInputException(String e) {
        super(e+"를 잘 입력해주세요");
    }
}
