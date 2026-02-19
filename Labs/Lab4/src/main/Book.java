/**
 * This class represents a basic book with a title, author, year, and price
 * <p></p>
 * To simplify the assignment, the author field is just a String
 */
public class Book {
  private String title;
  private String author;
  private int year;
  private float price;

  /**
   * Construct a Book object that has the provided title, author and  price
   * <p></p>
   * @param title  the title to be given to this book
   * @param author the author of this book
   * @param year   year in which the book was published
   * @param price  the price to be assigned to this book
   */
  public Book(String title, String author, int year, float price) {
    this.title = title;
    this.author = author;
    this.year = year;
    this.price = price;
  }

  /**
   * Getter for the books' title
   * <p></p>
   * @return the title of this book
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Getter for the book's author as a String
   * <p></p>
   * @return the author of this book
   */
  public String getAuthor() {
    return this.author;
  }

  /**
   * Getter for the book's price as a float
   * <p></p>
   * @return the price of this book
   */
  public float getPrice() {
    return this.price;
  }

  /**
   * Getter for the books year of publication
   * <p></p>
   * @return the year of publication
   */
  public int getYear() {
    return this.year;
  }


  /**
   * Determine if this book was published before a given year
   * <p></p>
   * @param year the year to test against
   * @return true if this book was published before the provided year, false
   * otherwise
   */
  public boolean before(int year) {
    return this.year < year;
  }

  /**
   * Determine if this book is cheaper than the book passed to it
   * <p></p>
   * @param book the book whose price should be compared to this book
   * @return true if this book is cheaper than the other book, false otherwise
   */
  public boolean cheaperThan(Book book) {
    return this.price < book.getPrice();
  }

  /**
   * Override Object's default toString method with a more meaningful format
   * <p></p>
   * @return a single-line String describing the book by its attributes.
   */
  @Override
  public String toString() {
    return String.format("Title: %s Author: %s Year: %d Price: %.2f",
            this.title, this.author, this.year, this.price);
  }
}
