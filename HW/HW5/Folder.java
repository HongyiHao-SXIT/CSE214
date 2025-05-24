package HW.HW5;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Folder implements Serializable {
    private String name;
    private ArrayList<Email> emails;

    public Folder(String name) {
        this.name = name;
        this.emails = new ArrayList<>();
    }

    public String getName() { return name; }

    public void addEmail(Email email) {
        emails.add(email);
        sortEmails();
    }

    public void removeEmail(Email email) {
        emails.remove(email);
    }

    public ArrayList<Email> getEmails() {
        return emails;
    }

    public void sortEmails() {
        Collections.sort(emails);
    }

    public void listEmails() {
        System.out.println("文件夹: " + name);
        for (int i = 0; i < emails.size(); i++) {
            System.out.println((i + 1) + ". " + emails.get(i));
        }
    }

    public Email getEmail(int index) {
        if (index >= 0 && index < emails.size()) {
            return emails.get(index);
        }
        return null;
    }
}
