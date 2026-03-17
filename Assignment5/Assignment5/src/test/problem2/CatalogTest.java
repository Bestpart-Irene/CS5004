package problem2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {

    private Author tolkien;
    private Author rowling;
    private RecordingArtist john;
    private RecordingArtist paul;
    private Band beatles;

    private Book lordOfRings;
    private Book harryPotter;
    private Music abbeyRoad;
    private Music johnSolo;

    private Catalog catalog;

    @BeforeEach
    void setUp() {
        tolkien = new Author("J.R.R.", "Tolkien");
        rowling = new Author("J.K.", "Rowling");
        john = new RecordingArtist("John", "Lennon");
        paul = new RecordingArtist("Paul", "McCartney");
        beatles = new Band("The Beatles", Arrays.asList(john, paul));

        lordOfRings = new Book(tolkien, "The Lord of the Rings", 1954);
        harryPotter = new Book(rowling, "Harry Potter and the Sorcerer's Stone", 1997);
        abbeyRoad = new Music(beatles, "Abbey Road", 1969);
        johnSolo = new Music(john, "Imagine", 1971);

        catalog = new Catalog(Arrays.asList(lordOfRings, harryPotter, abbeyRoad, johnSolo));
    }

    // ----- search by keyword -----

    @Test
    void searchKeyword_matchesTitleCaseInsensitive() {
        List<LibraryItem> results = catalog.search("harry");
        assertEquals(1, results.size());
        assertTrue(results.contains(harryPotter));
    }

    @Test
    void searchKeyword_matchesMultipleItems() {
        // "a" appears in "Abbey Road" and "Harry Potter..."
        List<LibraryItem> results = catalog.search("a");
        assertTrue(results.contains(abbeyRoad));
        assertTrue(results.contains(harryPotter));
    }

    @Test
    void searchKeyword_noMatch_returnsEmpty() {
        List<LibraryItem> results = catalog.search("zzz");
        assertTrue(results.isEmpty());
    }

    @Test
    void searchKeyword_caseInsensitive() {
        List<LibraryItem> lower = catalog.search("abbey road");
        List<LibraryItem> upper = catalog.search("ABBEY ROAD");
        assertEquals(lower, upper);
    }

    // ----- search by Author -----

    @Test
    void searchAuthor_returnsCorrectBook() {
        List<LibraryItem> results = catalog.search(tolkien);
        assertEquals(1, results.size());
        assertTrue(results.contains(lordOfRings));
    }

    @Test
    void searchAuthor_noMatch_returnsEmpty() {
        Author unknown = new Author("Unknown", "Person");
        assertTrue(catalog.search(unknown).isEmpty());
    }

    @Test
    void searchAuthor_doesNotReturnMusicItems() {
        List<LibraryItem> results = catalog.search(tolkien);
        results.forEach(item -> assertInstanceOf(Book.class, item));
    }

    // ----- search by RecordingArtist -----

    @Test
    void searchArtist_matchesSoloMusic() {
        List<LibraryItem> results = catalog.search(john);
        assertTrue(results.contains(johnSolo));
    }

    @Test
    void searchArtist_matchesBandMember() {
        // John is a member of The Beatles → Abbey Road should appear
        List<LibraryItem> results = catalog.search(john);
        assertTrue(results.contains(abbeyRoad));
    }

    @Test
    void searchArtist_doesNotReturnBooks() {
        List<LibraryItem> results = catalog.search(john);
        results.forEach(item -> assertInstanceOf(Music.class, item));
    }

    @Test
    void searchArtist_noMatch_returnsEmpty() {
        RecordingArtist ringo = new RecordingArtist("Ringo", "Starr");
        assertTrue(catalog.search(ringo).isEmpty());
    }

    // ----- addItem / removeItem -----

    @Test
    void addItem_increasesSize() {
        int before = catalog.getItems().size();
        catalog.addItem(new Book(tolkien, "The Hobbit", 1937));
        assertEquals(before + 1, catalog.getItems().size());
    }

    @Test
    void removeItem_decreasesSize() {
        int before = catalog.getItems().size();
        catalog.removeItem(lordOfRings);
        assertEquals(before - 1, catalog.getItems().size());
    }

    // ----- empty catalog constructor -----

    @Test
    void emptyCatalogConstructor_worksWithNoItems() {
        Catalog empty = new Catalog();
        assertTrue(empty.getItems().isEmpty());
        assertTrue(empty.search("anything").isEmpty());
    }

    // ----- Author / RecordingArtist equality -----

    @Test
    void author_equalsWorks() {
        Author a1 = new Author("J.R.R.", "Tolkien");
        Author a2 = new Author("J.R.R.", "Tolkien");
        assertEquals(a1, a2);
    }

    @Test
    void recordingArtist_equalsWorks() {
        RecordingArtist r1 = new RecordingArtist("John", "Lennon");
        RecordingArtist r2 = new RecordingArtist("John", "Lennon");
        assertEquals(r1, r2);
    }

    @Test
    void author_notEqualToRecordingArtist_withSameName() {
        Author a = new Author("John", "Lennon");
        RecordingArtist r = new RecordingArtist("John", "Lennon");
        assertNotEquals(a, r);
    }
}
