import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for IListOfBooks implementations (EmptyNode and ElementNode).
 */
public class IListOfBooksTest {

  private Book book1;
  private Book book2;
  private Book book3;
  private IListOfBooks emptyList;
  private IListOfBooks listOne;
  private IListOfBooks listTwo;
  private IListOfBooks listThree;

  @BeforeEach
  public void setUp() {
    book1 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, 10.0f);
    book2 = new Book("1984", "George Orwell", 1949, 15.0f);
    book3 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 12.0f);

    emptyList = new EmptyNode();
    listOne = new ElementNode(book1, emptyList);
    listTwo = new ElementNode(book2, listOne);
    // listTwo is: 1984 -> The Hobbit -> Empty
    listThree = new ElementNode(book3, listTwo);
    // listThree is: Gatsby -> 1984 -> The Hobbit -> Empty
  }

  // ---------------------------------------------------------
  // Tests for EmptyNode
  // ---------------------------------------------------------

  @Test
  public void testEmptyNodeCount() {
    // Assertion 1: An empty list should have size 0
    assertEquals(0, emptyList.count(), "Empty list count should be 0");
    
    // Assertion 2: A new EmptyNode instance should also have size 0
    assertEquals(0, new EmptyNode().count(), "New EmptyNode count should be 0");
  }

  @Test
  public void testEmptyNodeTotalPrice() {
    // Assertion 1: Total price of empty list should be 0.0
    assertEquals(0.0f, emptyList.totalPrice(), 0.01, "Empty list total price should be 0.0");
    
    // Assertion 2: Verify float precision handling
    assertEquals(0.0f, new EmptyNode().totalPrice(), 0.0001, "New EmptyNode price should be 0.0");
  }

  @Test
  public void testEmptyNodeAllBefore() {
    // Assertion 1: Filtering an empty list should return an empty list (count is 0)
    assertEquals(0, emptyList.allBefore(2000).count(), "Empty list allBefore should return empty list");
    
    // Assertion 2: The string representation should match an empty list
    assertEquals("", emptyList.allBefore(1900).toString(), "Empty list allBefore result should have empty string");
  }

  @Test
  public void testEmptyNodeAddAtEnd() {
    IListOfBooks newList = emptyList.addAtEnd(book1);
    
    // Assertion 1: Adding to empty should result in count 1
    assertEquals(1, newList.count(), "Adding to empty list should result in size 1");
    
    // Assertion 2: The total price should match the added book
    assertEquals(10.0f, newList.totalPrice(), 0.01, "List with one book should have that book's price");
  }

  @Test
  public void testEmptyNodeToString() {
    // Assertion 1: toString should be empty string
    assertEquals("", emptyList.toString(), "EmptyNode toString should be empty string");
    
    // Assertion 2: Length of string should be 0
    assertEquals(0, emptyList.toString().length(), "EmptyNode toString length should be 0");
  }

  // ---------------------------------------------------------
  // Tests for ElementNode
  // ---------------------------------------------------------

  @Test
  public void testElementNodeCount() {
    // Assertion 1: listOne has 1 book
    assertEquals(1, listOne.count(), "List with 1 book should have count 1");
    
    // Assertion 2: listThree has 3 books
    assertEquals(3, listThree.count(), "List with 3 books should have count 3");
  }

  @Test
  public void testElementNodeTotalPrice() {
    // Assertion 1: listOne price is 10.0
    assertEquals(10.0f, listOne.totalPrice(), 0.01, "ListOne price should be 10.0");
    
    // Assertion 2: listThree price is 12.0 + 15.0 + 10.0 = 37.0
    assertEquals(37.0f, listThree.totalPrice(), 0.01, "ListThree price should be sum of all books");
  }

  @Test
  public void testElementNodeAllBefore() {
    // listThree years: Gatsby(1925), 1984(1949), Hobbit(1937)
    
    // Assertion 1: Filter before 1940. Should keep Gatsby and Hobbit (2 books)
    IListOfBooks before1940 = listThree.allBefore(1940);
    assertEquals(2, before1940.count(), "Should include Gatsby and Hobbit");
    
    // Assertion 2: Filter before 1900. Should be empty.
    IListOfBooks before1900 = listThree.allBefore(1900);
    assertEquals(0, before1900.count(), "Should match no books");
  }

  @Test
  public void testElementNodeAddAtEnd() {
    Book book4 = new Book("Dune", "Frank Herbert", 1965, 20.0f);
    IListOfBooks extendedList = listThree.addAtEnd(book4);
    
    // Assertion 1: Count should increase by 1 (3 -> 4)
    assertEquals(4, extendedList.count(), "Count should increase after adding at end");
    
    // Assertion 2: The price should increase by 20.0 (37.0 + 20.0 = 57.0)
    assertEquals(57.0f, extendedList.totalPrice(), 0.01, "Total price should include new book");
  }

  @Test
  public void testElementNodeToString() {
    // Assertion 1: listOne string check
    // "Title: The Hobbit Author: J.R.R. Tolkien Year: 1937 Price: 10.00\n"
    String expectedOne = book1.toString() + "\n";
    assertEquals(expectedOne, listOne.toString(), "listOne toString matches");

    // Assertion 2: listTwo string check
    // "Title: 1984 ...\nTitle: The Hobbit ...\n"
    String expectedTwo = book2.toString() + "\n" + book1.toString() + "\n";
    assertEquals(expectedTwo, listTwo.toString(), "listTwo toString matches concatenated strings");
  }
}