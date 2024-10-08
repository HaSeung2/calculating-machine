package calculatingMachine.lv3;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// T extends Number로 해두면 클래스 또는 메소드 선언에서 제네릭 타입 T가 Number 또는
// 그 하위 클래스를 의미하도록 제한한다.
public class ArithmeticCalculator<T extends Number> {
    private final ArrayList<T> arrayList = new ArrayList<>();
    private T firstNumber;
    private T secondNumber;

    public void add(T results) {
        this.arrayList.add(results);
    }

    public void setFirstNumber(T firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setSecondNumber(T secondNumber) {
        this.secondNumber = secondNumber;
    }

    public T getFirstNumber() {
        return firstNumber;
    }

    public T getSecondNumber() {
        return secondNumber;
    }

    // 리스트가 비어있는지 확인
    public boolean arrayIsEmpty() {
        return arrayList.isEmpty();
    }

    // 0번째 방 데이터 삭제
    public boolean indexZeroRemover() {
        if(!arrayIsEmpty()){
            arrayList.remove(0);
            return true;
        }
        else{
            return false;
        }
    }

    // 원하는 방의 데이터 삭제
    public void wantIndexRemove(int idxNumber){
        arrayList.remove(idxNumber);
    }

    // 받아온 인덱스 번호의 값 전달
    public T getArrNumber(int idxNumber) {
        return arrayList.get(idxNumber);
    }

    // 받아온 index 방번호에 바꿀 값 저장.
    public void setArrValue(int idxNumber, T changeValue) {
        arrayList.set(idxNumber, changeValue);
    }

    // 리스트의 저장된 모든 값을 List 형태로 전달
    public List<T> getArrayList() {
        return arrayList.stream().toList();
    }

    //  리스트의 저장된 값들 중에서 입력받은 값보다 큰 값들만 추출하여 전달
    public List <Double> getLargeNumber(List<Double> list ,double number){
        return list.stream().filter(n -> n > number).toList();
    }

    // 리스트의 저장된 값들 중 가장 높은 값 전달.
    public double getMaxNumber(List<Double> list){
        return list.stream().mapToDouble(li -> li).max().orElseThrow(NoSuchElementException::new);
    }

    // 리스트의 저장된 값들 중 가장 작은 값 전달.
    public double getMinNumber(List<Double> list){
        return list.stream().mapToDouble(li -> li).min().orElseThrow(NoSuchElementException::new);
    }
}
