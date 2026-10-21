import java.util.*;

class Book {
int id;
String name;
String author;
boolean available;
Queue<String> waitingQueue;

Book(int id, String name, String author) {
    this.id = id;
    this.name = name;
    this.author = author;
    this.available = true;
    this.waitingQueue = new LinkedList<>();
}

}

public class LibrarySystem {

static Book[] books = new Book[100];
static int count = 0;
static Scanner sc = new Scanner(System.in);

public static void main(String[] args) {

    int choice;

    do {
        System.out.println("\n===== LIBRARY BOOK RESERVATION SYSTEM =====");
        System.out.println("1. Add Book");
        System.out.println("2. Display Books");
        System.out.println("3. Search Book");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. Reserve Book");
        System.out.println("7. Display Waiting Queue");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");

        choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                displayBooks();
                break;
            case 3:
                searchBook();
                break;
            case 4:
                issueBook();
                break;
            case 5:
                returnBook();
                break;
            case 6:
                reserveBook();
                break;
            case 7:
                displayQueue();
                break;
            case 8:
                System.out.println("Thank you for using the Library System!");
                break;
            default:
                System.out.println("Invalid choice!");
        }

    } while (choice != 8);

    sc.close();
}

static void addBook() {
    if (count == books.length) {
        System.out.println("Book storage is full!");
        return;
    }

    System.out.print("Enter Book ID: ");
    int id = sc.nextInt();
    sc.nextLine();

    if (findBook(id) != null) {
        System.out.println("Book ID already exists!");
        return;
    }

    System.out.print("Enter Book Name: ");
    String name = sc.nextLine();

    System.out.print("Enter Author Name: ");
    String author = sc.nextLine();

    books[count++] = new Book(id, name, author);

    System.out.println("Book added successfully!");
}

static void displayBooks() {
    if (count == 0) {
        System.out.println("No books available!");
        return;
    }

    System.out.println("\nID\tBook Name\tAuthor\t\tStatus");

    for (int i = 0; i < count; i++) {
        System.out.println(
            books[i].id + "\t" +
            books[i].name + "\t" +
            books[i].author + "\t\t" +
            (books[i].available ? "Available" : "Issued")
        );
    }
}

static void searchBook() {
    System.out.print("Enter Book ID to search: ");
    int id = sc.nextInt();

    Book book = findBook(id);

    if (book != null) {
        System.out.println("Book Name: " + book.name);
        System.out.println("Author: " + book.author);
        System.out.println("Status: " +
            (book.available ? "Available" : "Issued"));
    } else {
        System.out.println("Book not found!");
    }
}

static void issueBook() {
    System.out.print("Enter Book ID to issue: ");
    int id = sc.nextInt();
    sc.nextLine();

    Book book = findBook(id);

    if (book == null) {
        System.out.println("Book not found!");
        return;
    }

    if (!book.available) {
        System.out.println("Book is already issued. Please reserve it.");
        return;
    }

    if (!book.waitingQueue.isEmpty()) {
        String student = book.waitingQueue.poll();
        book.available = false;
        System.out.println("Book issued to waiting student: " + student);
    } else {
        System.out.print("Enter Student Name: ");
        String student = sc.nextLine();

        book.available = false;
        System.out.println("Book issued successfully to " + student);
    }
}

static void returnBook() {
    System.out.print("Enter Book ID to return: ");
    int id = sc.nextInt();

    Book book = findBook(id);

    if (book == null) {
        System.out.println("Book not found!");
        return;
    }

    if (book.available) {
        System.out.println("This book is not currently issued!");
        return;
    }

    book.available = true;
    System.out.println("Book returned successfully!");

    if (!book.waitingQueue.isEmpty()) {
        String student = book.waitingQueue.poll();
        book.available = false;
        System.out.println("Book automatically issued to waiting student: " + student);
    }
}

static void reserveBook() {
    System.out.print("Enter Book ID to reserve: ");
    int id = sc.nextInt();
    sc.nextLine();

    Book book = findBook(id);

    if (book == null) {
        System.out.println("Book not found!");
        return;
    }

    if (book.available && book.waitingQueue.isEmpty()) {
        System.out.println("Book is available. You can issue it directly.");
        return;
    }

    System.out.print("Enter Student Name: ");
    String student = sc.nextLine();

    if (book.waitingQueue.contains(student)) {
        System.out.println("Student is already in the waiting queue!");
        return;
    }

    book.waitingQueue.offer(student);
    System.out.println("Reservation successful! Student added to waiting queue.");
}

static void displayQueue() {
    System.out.print("Enter Book ID: ");
    int id = sc.nextInt();

    Book book = findBook(id);

    if (book == null) {
        System.out.println("Book not found!");
        return;
    }

    if (book.waitingQueue.isEmpty()) {
        System.out.println("Waiting queue is empty!");
    } else {
        System.out.println("Students waiting for " + book.name + ":");
        for (String student : book.waitingQueue) {
            System.out.println(student);
        }
    }
}

static Book findBook(int id) {
    for (int i = 0; i < count; i++) {
        if (books[i].id == id) {
            return books[i];
        }
    }
    return null;
}

}
