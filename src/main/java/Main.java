import entity.Book;
import entity.BookShelf;
import repository.BookRepository;
import repository.BookShelfRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4Pu");

        BookRepository bookRepository = new BookRepository(emf);
        BookShelfRepository bookShelfRepository = new BookShelfRepository(emf);

        BookShelf[] shelfs = {new BookShelf(), new BookShelf(), new BookShelf()};
        Book[] books =
                {new Book("Dziady IV", "Adam Mickiewicz", shelfs[0]),
                        new Book("Dziady III", "Adam Mickiewicz", shelfs[0]),
                        new Book("Krzyzacy", "Henryk Sienkiewicz", shelfs[0]),
                        new Book("Ferdydurke", "Witold Gombrowicz", shelfs[1]),
                        new Book("Zbrodnia i kara", "Fiodor Dostojewski", shelfs[1]),
                        new Book("Biesy", "Fiodor Dostojewski", shelfs[1]),
                        new Book("Kamizelka", "Bolesław Prus", shelfs[1]),
                        new Book("Lalka", "Bolesław Prus", shelfs[1]),
                        new Book("Mendel Gdański", "Maria Konopnicka", shelfs[1]),
                        new Book("Rota", "Maria Konopnicka", shelfs[1]),
                        new Book("Dym", "Maria Konopnicka", shelfs[1]),
                        new Book("Potop", "Henryk Sienkiewicz", shelfs[2]),
                        new Book("Ogniem i mieczem", "Henryk Sienkiewicz", shelfs[2]),
                        new Book("Wesele", "Stanisław Wyspiański", shelfs[2]),
                        new Book("Ludzie bezdomni", "Stefan Żeromski", shelfs[2]),};

        shelfs[0].setBooks(Arrays.asList(Arrays.copyOfRange(books, 0, 3)));
        shelfs[1].setBooks(Arrays.asList(Arrays.copyOfRange(books, 3, 7)));
        shelfs[2].setBooks(Arrays.asList(Arrays.copyOfRange(books, 7, books.length)));

        for (BookShelf s : shelfs) {
            bookShelfRepository.add(s);
        }

        for (Book b : books) {
            bookRepository.add(b);
        }

        System.out.println("Polki:");
        for (BookShelf s : bookShelfRepository.findAll()) {
            System.out.println(s);
        }
        System.out.println();

        System.out.println("Ksiazki:");
        for (Book b : bookRepository.findAll()) {
            System.out.println(b);
        }
        System.out.println();

        System.out.println(bookShelfRepository.countAuthorsBooks(shelfs[0].getId()));
        System.out.println(bookShelfRepository.countAuthorsBooks(shelfs[1].getId()));
        System.out.println(bookShelfRepository.countAuthorsBooks(shelfs[2].getId()));
        System.out.println();

        bookShelfRepository.delete(shelfs[0]);
        bookRepository.delete(books[books.length - 1]);

        System.out.println("Polki:");
        for (BookShelf s : bookShelfRepository.findAll()) {
            System.out.println(s);
        }
        System.out.println();

        System.out.println("Ksiazki:");
        for (Book b : bookRepository.findAll()) {
            System.out.println(b);
        }
        System.out.println();

        System.out.println(bookShelfRepository.countAuthorsBooks(shelfs[1].getId()));
        System.out.println(bookShelfRepository.countAuthorsBooks(shelfs[2].getId()));

        emf.close();
    }

}
