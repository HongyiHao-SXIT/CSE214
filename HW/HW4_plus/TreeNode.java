package HW.HW4_plus;

class TreeNode {
    private TreeNode[] children;
    private String label;
    private String message;
    private String prompt;

    public TreeNode(String label, String message, String prompt) {
        this.label = label;
        this.message = message;
        this.prompt = prompt;
        this.children = new TreeNode[9];
    }

    public TreeNode getChild(int index) {
        if (index < 0 || index >= 9) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        return children[index];
    }

    public void setChild(int index, TreeNode child) {
        if (index < 0 || index >= 9) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        children[index] = child;
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
        for (TreeNode child : children) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }
}