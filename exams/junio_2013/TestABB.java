package exams.junio_2013;

public class TestABB {
    public static void main(String[] args) {

        LinkedBinarySearchTreeExtension<Integer> binTree = new LinkedBinarySearchTreeExtension<>();

        binTree.insert(5);
        binTree.insert(3);
        binTree.insert(6);
        binTree.insert(2);
        binTree.insert(4);
        binTree.insert(12);
        binTree.insert(10);
        binTree.insert(14);
        binTree.insert(9);

        System.out.println(binTree.median());
    }
}
