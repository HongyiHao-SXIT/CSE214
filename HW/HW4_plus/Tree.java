package HW.HW4_plus;

class Tree {
    private TreeNode root;

    public Tree() {
        this.root = null;
    }

    public boolean addNode(String label, String prompt, String message, String parentLabel) {
        TreeNode newNode = new TreeNode(label, message, prompt);
        if (root == null) {
            if ("root".equals(parentLabel)) {
                root = newNode;
                return true;
            } else {
                return false;
            }
        } else {
            TreeNode parent = getNodeReference(parentLabel);
            if (parent == null) {
                return false;
            }
            for (int i = 0; i < 9; i++) {
                if (parent.getChild(i) == null) {
                    parent.setChild(i, newNode);
                    return true;
                }
            }
            return false;
        }
    }

    public TreeNode getNodeReference(String label) {
        return getNodeReferenceHelper(root, label);
    }

    private TreeNode getNodeReferenceHelper(TreeNode node, String label) {
        if (node == null) {
            return null;
        }
        if (node.getLabel().equals(label)) {
            return node;
        }
        for (int i = 0; i < 9; i++) {
            TreeNode result = getNodeReferenceHelper(node.getChild(i), label);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public void preOrder() {
        preOrderHelper(root);
    }

    private void preOrderHelper(TreeNode node) {
        if (node != null) {
            System.out.println("Label: " + node.getLabel());
            System.out.println("Prompt: " + node.getPrompt());
            System.out.println("Message: " + node.getMessage());
            System.out.println();
            for (int i = 0; i < 9; i++) {
                preOrderHelper(node.getChild(i));
            }
        }
    }

    public void beginSession() {
        if (root == null) {
            System.out.println("没有设置树，请先加载文件构建树。");
            return;
        }
        beginSessionHelper(root);
    }

    private void beginSessionHelper(TreeNode node) {
        if (node.isLeaf()) {
            System.out.println(node.getMessage());
            System.out.println("Thank you for using our automated help system.");
            return;
        }
        System.out.println(node.getMessage());
        for (int i = 0; i < 9; i++) {
            TreeNode child = node.getChild(i);
            if (child != null) {
                System.out.println((i + 1) + ") " + child.getPrompt());
            }
        }
        System.out.println("0) Exit Session.");
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        if (userChoice == 0) {
            return;
        } else if (userChoice > 0 && userChoice <= 9 && node.getChild(userChoice - 1) != null) {
            beginSessionHelper(node.getChild(userChoice - 1));
        } else {
            System.out.println("无效的选择，请重新输入。");
            beginSessionHelper(node);
        }
    }
}