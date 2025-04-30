package HW.HW4;

class TreeDriver {
    public static void main(String[] args) {
        Tree tree = new Tree();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("L - Load a Tree.");
            System.out.println("H - Begin a Help Session.");
            System.out.println("T - Traverse the Tree in preorder.");
            System.out.println("Q - Quit");
            System.out.print("Choice> ");
            String choice = scanner.nextLine();
            if ("L".equals(choice)) {
            System.out.print("Enter the file name> ");
            String fileName = scanner.nextLine();
            // 这里需要实现从文件加载树的逻辑，目前仅示例，实际需按文件格式解析
            // 假设文件格式正确，每行分别为label, prompt, message
            // 示例代码：
            // String[] lines = {"root", "Root Prompt", "What Model is the Washing Machine?",
            // "1", "WM200", "What is the problem?",
            // "1-1", "No Water.", "Is the hose attached?"};
            // for (int i = 0; i < lines.length; i += 3) {
            //     String label = lines[i];
            //     String prompt = lines[i + 1];
            //     String message = lines[i + 2];
            //     if ("root".equals(label)) {
            //         tree.addNode(label, prompt, message, "root");
            //     } else {
            //         String parentLabel = label.substring(0, label.lastIndexOf('-'));
            //         tree.addNode(label, prompt, message, parentLabel);
            //     }
            // }
            System.out.println("Tree created successfully!");
            } else if ("H".equals(choice)) {
                tree.beginSession();
            } else if ("T".equals(choice)) {
                System.out.println("Traversing the tree in preorder: ");
                tree.preOrder();
            } else if ("Q".equals(choice)) {
                System.out.println("Thank you for using our automated help services!");
                break;
            } else {
                System.out.println("无效的选择，请重新输入。");
            }
        }
    }
}