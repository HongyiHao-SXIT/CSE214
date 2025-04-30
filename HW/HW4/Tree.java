package HW.HW4;

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
            if (parent.getLeft() == null) {
                parent.setLeft(newNode);
            } else if (parent.getMiddle() == null) {
                parent.setMiddle(newNode);
            } else if (parent.getRight() == null) {
                parent.setRight(newNode);
            } else {
                return false;
            }
            return true;
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
        TreeNode result = getNodeReferenceHelper(node.getLeft(), label);
        if (result != null) {
            return result;
        }
        result = getNodeReferenceHelper(node.getMiddle(), label);
        if (result != null) {
            return result;
        }
        return getNodeReferenceHelper(node.getRight(), label);
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
            preOrderHelper(node.getLeft());
            preOrderHelper(node.getMiddle());
            preOrderHelper(node.getRight());
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
        int choice = 1;
        if (node.getLeft() != null) {
            System.out.println(choice + ") " + node.getLeft().getPrompt());
            choice++;
        }
        if (node.getMiddle() != null) {
            System.out.println(choice + ") " + node.getMiddle().getPrompt());
            choice++;
        }
        if (node.getRight() != null) {
            System.out.println(choice + ") " + node.getRight().getPrompt());
        }
        System.out.println("0) Exit Session.");
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        if (userChoice == 0) {
            return;
        } else if (userChoice == 1 && node.getLeft() != null) {
            beginSessionHelper(node.getLeft());
        } else if (userChoice == 2 && node.getMiddle() != null) {
            beginSessionHelper(node.getMiddle());
        } else if (userChoice == 3 && node.getRight() != null) {
            beginSessionHelper(node.getRight());
        } else {
            System.out.println("无效的选择，请重新输入。");
            beginSessionHelper(node);
        }
    }
}