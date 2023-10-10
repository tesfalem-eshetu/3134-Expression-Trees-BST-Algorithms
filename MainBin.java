public class MainBin {
    public static void main(String[] args) {
        BetterBST<Integer> trial = new BetterBST<>();
        trial.insert(32);
        trial.insert(20);
        trial.insert(39);
        trial.insert(10);
        trial.insert(22);
        //trial.insert(39);
        trial.insert(29);
        trial.insert(42);
        trial.insert(8);
        trial.insert(7);

        System.out.println(trial.height());
        System.out.println(trial.imbalance());
        System.out.println(trial.smallestGreaterThan(8));
        trial.levelOrderTraversal();
        BinarySearchTree<Integer> mirroredTree = trial.mirror();
        System.out.println(" ");
        mirroredTree.levelOrderTraversal();

    }
}
