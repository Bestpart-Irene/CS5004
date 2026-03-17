package problem2;

/**
 * Represents a book in the library catalog.
 * A book's creator is always an Author.
 */
public class Book extends LibraryItem {

    public Book(Author author, String title, int year) {
        super(author, title, year);
    }

    public Author getAuthor() {
        return (Author) getCreator();
    }
}
