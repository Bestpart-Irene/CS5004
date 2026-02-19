/**
 * Represents an element node in the list of books.
 * It contains a book and a reference to the rest of the list.
 */
public class ElementNode implements IListOfBooks {
  private Book book;
  private IListOfBooks rest;

  /**
   * Constructor for ElementNode.
   *
   * @param book the book at this node
   * @param rest the rest of the list
   */
  public ElementNode(Book book, IListOfBooks rest) {
    this.book = book;
    this.rest = rest;
  }

  /**
   * Return the number of books in this list.
   *
   * @return 1 plus the count of the rest of the list.
   */
  @Override
  public int count() {
    return 1 + this.rest.count();
  }

  /**
   * Return the sum of the prices of all books in this list.
   *
   * @return the price of this book plus the total price of the rest of the list.
   */
  @Override
  public float totalPrice() {
    return this.book.getPrice() + this.rest.totalPrice();
  }

  /**
   * Return a sublist containing the books published before the given year.
   *
   * @param year the year before which all the returned books are published
   * @return a list containing only books published before the given year.
   */
  @Override
  public IListOfBooks allBefore(int year) {
    if (this.book.before(year)) {
      return new ElementNode(this.book, this.rest.allBefore(year));
    } else {
      return this.rest.allBefore(year);
    }
  }

  /**
   * Return an IListOfBooks obtained by appending a specified book to the end.
   *
   * @param book an instance of Class Book
   * @return a new ElementNode with the current book and the new book added to the end of the rest.
   */
  @Override
  public IListOfBooks addAtEnd(Book book) {
    return new ElementNode(this.book, this.rest.addAtEnd(book));
  }

  /**
   * Create and return a string that can be used to print this list.
   *
   * @return a string describing this book and the rest of the list.
   */
  @Override
  public String toString() {
    return this.book.toString() + "\n" + this.rest.toString();
  }
}