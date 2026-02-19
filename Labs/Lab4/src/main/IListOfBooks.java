/**
 * This interface represents a basic subset of methods for a list of books
 */
public interface IListOfBooks {
  /**
   * Return the number of books in this list
   * <p></p>
   * @return the size of this list
   */
  int count();

  /**
   * Return the sum of the prices of all books in this list
   * <p></p>
   * @return the total price of the list of books
   */
  float totalPrice();

  /**
   * Return a sublist containing the books published before the given year
   * <p></p>
   * @param year the year before which all the returned books are published
   * @return the list of all books published before the given year
   */
  IListOfBooks allBefore(int year);

  /**
   * Return an IListOfBooks obtained by appending a specified book to the end
   * <p></p>
   * @param book an instance of Class Book
   * @return IListOfBooks the modified list
   */
  IListOfBooks addAtEnd(Book book);

  /**
   * Create and return a string that can be used to print this list
   * <p></p>
   * Since toString is inherited from Object, this is merely a reminder
   * to provide a more useful version in your implementation.
   *
   * @return String for printing
   */
  String toString();
}
