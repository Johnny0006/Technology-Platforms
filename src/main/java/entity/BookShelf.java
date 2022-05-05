package entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Entity
public class BookShelf {

    @Id
    @GeneratedValue
    private UUID id;
    @Setter
    @OneToMany(mappedBy = "bookShelf", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Book> books;

}
