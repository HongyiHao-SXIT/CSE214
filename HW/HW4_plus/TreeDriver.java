package HW.HW4_plus;

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
                // 假设文件格式正确，每行分别为label, prompt, message，
                // 且有表示子节点数量的行，例如 "label <number-of-children>"
                // 示例代码：
                // String[] lines = {"root", "Root Prompt", "What Model is the Washing Machine?",
                // "1 3", "WM200", "What is the problem?", "WM300", "Another problem?", "WM400", "Yet another problem?"};
                // int i = 0;
                // while (i < lines.length) {
                //     String label = lines[i];
                //     if (label.matches("\\d+ \\d+")) {
                //         String[] parts = label.split(" ");
                //         int parentIndex = Integer.parseInt(parts[0]);
                //         int childCount = Integer.parseInt(parts[1]);
                //         for (int j = 0; j < childCount; j++) {
                //             String childLabel = lines[i + 1 + j * 3];
                //             String prompt = lines[i + 2 + j * 3];
                //             String message = lines[i + 3 + j * 3];
                //             tree.addNode(childLabel, prompt, message, parts[0]);
                //         }
                //         i += 1 + childCount * 3;
                //     } else {
                //         String prompt = lines[i + 1];
                //         String message = lines[i + 2];
                //         if ("root".equals(label)) {
                //             tree.addNode(label, prompt, message, "root");
                //         } else {
                //             // 假设能从label解析出parentLabel
                //             String parentLabel = label.substring(0, label.lastIndexOf('-'));
                //             tree.addNode(label, prompt, message, parentLabel);
                //         }
                //         i += 3;
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