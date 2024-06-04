package bookstoremanagement;

import java.io.Serializable;

public class Author implements Serializable {
    private int authorId;
    private String name;

    public Author(int authorId, String name) {
        this.authorId = authorId;
        this.name = name;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Author" +
                "authorId=" + authorId +
                ", name='" + name + '\'';
    }
}
