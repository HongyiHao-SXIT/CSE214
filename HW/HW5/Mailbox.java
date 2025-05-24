package HW.HW5;

import java.io.*;
import java.util.HashMap;

public class Mailbox implements Serializable {
    private HashMap<String, Folder> folders;

    public Mailbox() {
        folders = new HashMap<>();
        folders.put("收件箱", new Folder("收件箱"));
        folders.put("已发送", new Folder("已发送"));
        folders.put("垃圾箱", new Folder("垃圾箱"));
    }

    public Folder getFolder(String name) {
        return folders.get(name);
    }

    public void addFolder(String name) {
        folders.putIfAbsent(name, new Folder(name));
    }

    public void deleteEmail(String folderName, int index) {
        Folder folder = folders.get(folderName);
        if (folder != null) {
            Email e = folder.getEmail(index);
            if (e != null) {
                folders.get("垃圾箱").addEmail(e);
                folder.removeEmail(e);
            }
        }
    }

    public void moveEmail(String from, String to, int index) {
        Folder source = folders.get(from);
        Folder target = folders.get(to);
        if (source != null && target != null) {
            Email e = source.getEmail(index);
            if (e != null) {
                target.addEmail(e);
                source.removeEmail(e);
            }
        }
    }

    public void save(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("邮箱已保存到: " + filename);
        } catch (IOException e) {
            System.err.println("保存失败: " + e.getMessage());
        }
    }

    public static Mailbox load(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Mailbox) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("加载失败: " + e.getMessage());
            return new Mailbox();
        }
    }

    public void listAll() {
        for (String name : folders.keySet()) {
            folders.get(name).listEmails();
            System.out.println();
        }
    }
}
