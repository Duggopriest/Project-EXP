import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.util.Vector;
import java.io.BufferedReader;

class Contact {
    public String firstName;
    public String lastName;
}

public class PhoneBook {
    PhoneBook() {load();}
    public static Vector<Contact> conList = new Vector<Contact>();
    private Scanner scan = new Scanner(System.in);

    private String mainOptions =
            "1) Add Contact\n" +
            "2) List Contacts\n" +
                    "3) Search Contact by name\n" +
                    "4) Edit Contact Info\n" +
                    "5) Exit";
    private String EditOptions =
            "1) Edit First Name\n" +
                    "2) Edit Last Name\n" +
                    "3) Back";

    public void addContact (String firstName, String lastName) {
        Contact newContact = new Contact();
        newContact.firstName = firstName;
        newContact.lastName = lastName;
        conList.add(newContact);
    }

    void addContactUI() {
        System.out.println("Add User Menu");
        System.out.print("First Name:");
        String temp = scan.nextLine();
        System.out.print("Last Name:");
        addContact(temp, scan.nextLine());

        int i = conList.size() - 1;
        clearScreen();
        System.out.println("Added User -> " + conList.elementAt(i).firstName + " " + conList.elementAt(i).lastName);
    }
    void printList () {
        int len = conList.size();
        if (len == 0)
            System.out.println("List Empty!");
        for (int i = 0; i < len; i++)
        {
            System.out.println("-|" + conList.elementAt(i).firstName + "\t" + conList.elementAt(i).lastName);
        }
        System.out.println("1) Back");
        scan.nextLine();
        clearScreen();
    }

    public void save()
    {
        System.out.println("Saving to Book.txt");

        try {
            FileWriter Book = new FileWriter("Book.txt");
            int len = conList.size();
            for (int i = 0; i < len; i++) {
                Book.write(conList.elementAt(i).firstName + "\t" + conList.elementAt(i).lastName + "\n");
            }
            Book.close();
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public void load()
    {
        System.out.println("Loading to Book.txt");

        try {
            BufferedReader Book = new BufferedReader(new FileReader("Book.txt"));
            String str;
            String[] temp;

            while ((str = Book.readLine()) != null)
            {
                temp = str.split("\t");
                addContact(temp[0], temp[1]);
            }
            Book.close();
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    void clearScreen() {
        System.out.print("\n\n\n\n\n\n");
    }

    Contact search(String name) {
        int len = conList.size();
        for (int i = 0; i < len; i++)
        {
            if (conList.elementAt(i).firstName.contains(name))
                return conList.elementAt(i);
        }
        return null;
    }

    void searchContact () {
        System.out.println("Search By Name");
        Contact toEdit = search(scan.nextLine());
        if (toEdit == null)
        {
            clearScreen();
            System.out.println("Could Not Find Contact");
            return;
        }
        while (true) {
            clearScreen();
            System.out.println("Found -> " + toEdit.firstName + " " + toEdit.lastName);
            System.out.println("1) Edit First Name");
            System.out.println("2) Edit Last Name");
            System.out.println("3) Back");

            switch (Integer.parseInt(scan.nextLine())) {
                case 1: {
                    System.out.print("from -> " + toEdit.firstName + ". To :");
                    toEdit.firstName = scan.nextLine();
                    continue;
                }
                case 2: {
                    System.out.print("from -> " + toEdit.lastName + ". To :");
                    toEdit.lastName = scan.nextLine();
                    continue;
                }
                default: return;
            }
        }
    }

    public void mainLoop() {
        while (true) {
            save();
            System.out.println("Welcome to PhoneBook");
            System.out.println(mainOptions);

            switch (Integer.parseInt(scan.nextLine())) {
                case 1: {addContactUI();continue;}
                case 2: {printList();continue;}
                case 3: {searchContact();continue;}
                default: {return;}
            }
        }
    }
}