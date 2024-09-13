package calculatingMachine.lv3.reg;

import calculatingMachine.lv3.exception.BadInputException;

public enum Reg {
    OPERATION_REG("[+\\-*/]"),
    NUMBER_REG("^[0-9]*$"),
    DOUBLE_REG("^([0-9]{1}\\d{0,2}|0{1})(\\.{1}\\d{0,1})*$");

    private final String reg;

    Reg(String reg) {
        this.reg = reg;
    }

    public String getReg(){
        return reg;
    }

    public static boolean matches(String number) throws Exception{
        if(number.matches(NUMBER_REG.getReg()) || number.matches(DOUBLE_REG.getReg())){
            return true;
        }
        throw new BadInputException("정수, 실수");
    }

    public static boolean matchesOperation(String operation) throws Exception{
        if(operation.matches(OPERATION_REG.getReg())){
            return true;
        }
        throw new BadInputException("연산 기호");
    }

    public static boolean matcheNumbers(String targetNumber, String changeValue) throws Exception{
        if(targetNumber.matches(NUMBER_REG.getReg()) && changeValue.matches(DOUBLE_REG.getReg())){
            return true;
        }
        else{
            throw new BadInputException("정수");
        }
    }

}
