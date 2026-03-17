import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookTest {
  private Book book1;
  private Book book2;
  private Book book3;

  @BeforeEach
  void setUp() {
    this.book1 = new Book("1984", "George Orwell", 328);
    this.book2 = new Book("To Kill a Mockingbird", "Harper Lee", 281);
    this.book3 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 180);
  }

  @Test
  void getTitle() {
    Assertions.assertEquals("1984", this.book1.getTitle());
    Assertions.assertEquals("To Kill a Mockingbird", this.book2.getTitle());
    Assertions.assertEquals("The Great Gatsby", this.book3.getTitle());
  }

  @Test
  void getAuthor() {
    Assertions.assertEquals("George Orwell", this.book1.getAuthor());
    Assertions.assertEquals("Harper Lee", this.book2.getAuthor());
    Assertions.assertEquals("F. Scott Fitzgerald", this.book3.getAuthor());
  }

  @Test
  void getPages() {
    Assertions.assertEquals(328, this.book1.getPages());
    Assertions.assertEquals(281, this.book2.getPages());
    Assertions.assertEquals(180, this.book3.getPages());
  }
}