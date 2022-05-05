package repository;

import entity.BookShelf;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BookShelfRepository extends JpaRepository<BookShelf, UUID> {

    public BookShelfRepository(EntityManagerFactory emf) {
        super(emf, BookShelf.class);
    }

    public Map<String, Integer> countAuthorsBooks(UUID id) {
        BookShelf bookShelf = this.find(id);
        Map<String, Integer> map = new HashMap();
        int count;
        String author;
        for (int i = 0; i < bookShelf.getBooks().size(); i++) {
            count = 0;
            author = bookShelf.getBooks().get(i).getAuthor();
            if (!map.containsKey(author)) {
                for (int j = i; j < bookShelf.getBooks().size(); j++) {
                    if (author.equals(bookShelf.getBooks().get(j).getAuthor())) {
                        count++;
                    }
                }
                map.put(author, count);
            }
        }
        return map;
    }
}
