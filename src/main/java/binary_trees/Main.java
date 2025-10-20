package binary_trees;

public class Main {
    static void main() {
        BinaryTree tree = new BinaryTree();

        tree.root = new TreeNode(1);

        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);

        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);

        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(7);

        System.out.println("Pre-order traversal: ");
        tree.preOrder(tree.root);

        System.out.println("\nIn-order traversal: ");
        tree.inOrder(tree.root);

        System.out.println("\nPost-order traversal: ");
        tree.postOrder(tree.root);
    }
}
