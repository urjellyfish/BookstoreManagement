package bookstoremanagement;

import java.io.*;
import java.util.*;

public class BookstoreManagement {
    private Map<Integer, Author> authors;
    private List<Book> books;

    public BookstoreManagement() {
        authors = new HashMap<>();
        books = new ArrayList<>();
    }

    public void addAuthor(Author author) {
        authors.put(author.getAuthorId(), author);
    }

    public void addBook(Book book) {
        if (authors.containsKey(book.getAuthorId())) {
            books.add(book);
        } else {
            System.out.println("Author ID " + book.getAuthorId() + " not found. Cannot add book.");
        }
    }

    public void updateBook(String name, String newName, double newPrice, int newAuthorId) {
        for (Book book : books) {
            if (book.getName().equals(name)) {
                book.setName(newName);
                book.setPrice(newPrice);
                book.setAuthorId(newAuthorId);
                System.out.println("Book updated successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void deleteBook(String name) {
        for (Book book : books) {
            if (book.getName().equals(name)) {
                books.remove(book);
                System.out.println("Book deleted successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void searchBook(String name) {
        for (Book book : books) {
            if (book.getName().equals(name)) {
                System.out.println(book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void listAuthors() {
        for (Author author : authors.values()) {
            System.out.println(author);
        }
    }

    public void storeDataToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(authors);
            oos.writeObject(books);
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    public void loadDataFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            authors = (HashMap<Integer, Author>) ois.readObject();
            books = (ArrayList<Book>) ois.readObject();
            System.out.println("Data loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        BookstoreManagement bookstore = new BookstoreManagement();
        Scanner scanner = new Scanner(System.in);
        String filename = "bookstore.dat";

        while (true) {
            System.out.println("\nBookstore Management System");
            System.out.println("1. Add Author");
            System.out.println("2. Add Book");
            System.out.println("3. Update Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Search Book");
            System.out.println("6. List Books");
            System.out.println("7. List Authors");
            System.out.println("8. Store Data to File");
            System.out.println("9. Load Data from File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter author ID: ");
                    int authorId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter author name: ");
                    String authorName = scanner.nextLine();
                    bookstore.addAuthor(new Author(authorId, authorName));
                    break;
                case 2:
                    System.out.print("Enter book name: ");
                    String bookName = scanner.nextLine();
                    System.out.print("Enter book price: ");
                    double bookPrice = scanner.nextDouble();
                    System.out.print("Enter author ID: ");
                    int bookAuthorId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    bookstore.addBook(new Book(bookName, bookPrice, bookAuthorId));
                    break;
                case 3:
                    System.out.print("Enter the name of the book to update: ");
                    String oldName = scanner.nextLine();
                    System.out.print("Enter new book name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new book price: ");
                    double newPrice = scanner.nextDouble();
                    System.out.print("Enter new author ID: ");
                    int newAuthorId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    bookstore.updateBook(oldName, newName, newPrice, newAuthorId);
                    break;
                case 4:
                    System.out.print("Enter the name of the book to delete: ");
                    String nameToDelete = scanner.nextLine();
                    bookstore.deleteBook(nameToDelete);
                    break;
                case 5:
                    System.out.print("Enter the name of the book to search: ");
                    String nameToSearch = scanner.nextLine();
                    bookstore.searchBook(nameToSearch);
                    break;
                case 6:
                    bookstore.listBooks();
                    break;
                case 7:
                    bookstore.listAuthors();
                    break;
                case 8:
                    bookstore.storeDataToFile(filename);
                    break;
                case 9:
                    bookstore.loadDataFromFile(filename);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
