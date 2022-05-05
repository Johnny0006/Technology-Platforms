package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
@EqualsAndHashCode
public class Book {

    @Id
    @GeneratedValue()
    private UUID id;
    @Setter
    private String title;
    @Setter
    private String author;
    @Setter
    @ManyToOne
    private BookShelf bookShelf;

    public Book(String title, String author, BookShelf bookShelf) {
        this.title = title;
        this.author = author;
        this.bookShelf = bookShelf;
    }

    public String toString() {
        return "entity.Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", bookShelf=" + bookShelf.getId() +
                '}';
    }
}
