import java.util.LinkedList;
import java.util.Queue;

public class BetterBST<T extends Comparable<? super T>>
 extends BinarySearchTree<T> {

    @Override
    int height() {
        return height(root);
    }
// Recursive helper method to calculate the height of a given node
    private int height(BinaryNode<T> root) {
        if (root == null) {
            return -1; // height of an empty tree is -1
        } else {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    @Override
    public T imbalance() {
        return imbalance(root).imbalanceNodeData;
    }
// // Recursively check the left subtree for an imbalanced node
    private ImbalanceInfo<T> imbalance(BinaryNode<T> node) {
        if (node == null) {
            return new ImbalanceInfo<>(null, -1);
        }

        ImbalanceInfo<T> leftInfo = imbalance(node.left);
        if (leftInfo.imbalanceNodeData != null) {
            return leftInfo;
        }

        ImbalanceInfo<T> rightInfo = imbalance(node.right);
        if (rightInfo.imbalanceNodeData != null) {
            return rightInfo;
        }

        int leftHeight = leftInfo.height + 1;
        int rightHeight = rightInfo.height + 1;

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return new ImbalanceInfo<>(node.data, -1);
        }

     return new ImbalanceInfo<>(null, Math.max(leftHeight, rightHeight));
    }

    private static class ImbalanceInfo<T> {
        T imbalanceNodeData;
        int height;

        ImbalanceInfo(T imbalanceNodeData, int height) {
            this.imbalanceNodeData = imbalanceNodeData;
            this.height = height;
        }
    }
    @Override

    T smallestGreaterThan(T t) {
        BinaryNode<T> current = root;
            T result = null;
            while (current != null) {
                if (current.data.compareTo(t) <= 0) {
                    // Ignore left subtree
                    current = current.right;
                } else {
                    // Update result and continue on the left subtree
                    result = current.data;
                    current = current.left;
                }
            }
            return result;
        }

//    @Override
//will mirror the orignial mirror without changing it 
public BinarySearchTree<T> mirror() {
    BetterBST<T> mirroredTree = new BetterBST<>();
    if (root != null) {
        mirroredTree.root = mirrorHelper(root);
    }
    return mirroredTree;
}

    private BinaryNode<T> mirrorHelper(BinaryNode<T> node) {
        BinaryNode<T> newNode = new BinaryNode<>(node.data);
        if (node.right != null) {
            newNode.left = mirrorHelper(node.right);
        }
        if (node.left != null) {
            newNode.right = mirrorHelper(node.left);
        }
        return newNode;
    }


    @Override
    //will return the levelorder traversal
    public LinkedList<BinaryNode<T>> levelOrderTraversal() {
        LinkedList<BinaryNode<T>> result = new LinkedList<>();
        Queue<BinaryNode<T>> queue = new LinkedList<>();

        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            BinaryNode<T> node = queue.remove();
            result.add(node);
            System.out.print(node.data + " ");

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return result;
    }
    //method to print inorder
    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(BinaryNode<T> node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.data + " ");
        printInOrder(node.right);
    }

}
