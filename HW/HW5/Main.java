package HW.HW5;

public class Main {
    public static void main(String[] args) {
        Mailbox mailbox = new Mailbox();

        Email e1 = new Email("课程通知", "明天上课时间改为上午8点", "teacher@school.edu");
        Email e2 = new Email("作业提醒", "请于本周五提交HW5", "ta@school.edu");
        Email e3 = new Email("会议邀请", "参加课程项目会议", "leader@group.com");

        mailbox.getFolder("收件箱").addEmail(e1);
        mailbox.getFolder("收件箱").addEmail(e2);
        mailbox.getFolder("收件箱").addEmail(e3);

        mailbox.listAll();

        System.out.println("将第一封邮件移到已发送...");
        mailbox.moveEmail("收件箱", "已发送", 0);

        System.out.println("删除收件箱中的第二封邮件...");
        mailbox.deleteEmail("收件箱", 1);

        mailbox.listAll();

        mailbox.save("mailbox.obj");

        System.out.println("加载邮箱...");
        Mailbox loaded = Mailbox.load("mailbox.obj");
        loaded.listAll();
    }
}
