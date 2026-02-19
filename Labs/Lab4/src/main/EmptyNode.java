/**
 * Represents an empty node in the list of books.
 * This denotes the end of a list or an empty list.
 */
public class EmptyNode implements IListOfBooks {

  /**
   * Default constructor for EmptyNode.
   */
  public EmptyNode() {
    // Nothing to initialize
  }

  /**
   * Return the number of books in this list.
   *
   * @return 0, as an empty node contains no books.
   */
  @Override
  public int count() {
    return 0;
  }

  /**
   * Return the sum of the prices of all books in this list.
   *
   * @return 0.0f, as there are no books to sum.
   */
  @Override
  public float totalPrice() {
    return 0.0f;
  }

  /**
   * Return a sublist containing the books published before the given year.
   *
   * @param year the year before which all the returned books are published
   * @return a new EmptyNode, as there are no books to filter.
   */
  @Override
  public IListOfBooks allBefore(int year) {
    return new EmptyNode();
  }

  /**
   * Return an IListOfBooks obtained by appending a specified book to the end.
   *
   * @param book an instance of Class Book
   * @return a new ElementNode containing the book and this empty node as the rest.
   */
  @Override
  public IListOfBooks addAtEnd(Book book) {
    return new ElementNode(book, this);
  }

  /**
   * Create and return a string that can be used to print this list.
   *
   * @return an empty string.
   */
  @Override
  public String toString() {
    return "";
  }
}