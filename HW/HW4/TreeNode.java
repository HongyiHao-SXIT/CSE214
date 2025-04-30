import java.util.Scanner;

class TreeNode {

    private TreeNode left;
    private TreeNode middle;
    private TreeNode right;
    private String label;
    private String message;
    private String prompt;

    public TreeNode(String label, String message, String prompt) {
        this.label = label;
        this.message = message;
        this.prompt = prompt;
        this.left = null;
        this.middle = null;
        this.right = null;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getMiddle() {
        return middle;
    }

    public void setMiddle(TreeNode middle) {
        this.middle = middle;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public boolean isLeaf() {
        return left == null && middle == null && right == null;
    }
}
