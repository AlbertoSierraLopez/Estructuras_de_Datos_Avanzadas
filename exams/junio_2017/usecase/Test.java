package exams.junio_2017.usecase;

public class Test {
    public static void main(String[] args) {

        ArithmeticEvaluator evaluator = new ArithmeticEvaluator("1-2+4*8+1*5");
        System.out.println(evaluator.evaluate());

    }
}
