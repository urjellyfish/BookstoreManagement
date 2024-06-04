package bookstoremanagement;

import java.io.Serializable;

public class Book implements Serializable {

    private String name;
    private double price;
    private int authorId;

    public Book(String name, double price, int authorId) {
        this.name = name;
        this.price = price;
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book"
                + "name='" + name + '\''
                + ", price=" + price
                + ", authorId=" + authorId;
    }
}
