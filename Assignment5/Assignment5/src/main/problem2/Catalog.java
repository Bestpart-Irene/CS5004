package problem2;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a library catalog that stores and searches library items.
 * Supports three overloaded search methods: by keyword, by author, or by recording artist.
 */
public class Catalog {

    private List<LibraryItem> items;

    public Catalog() {
        this.items = new ArrayList<>();
    }

    public Catalog(List<LibraryItem> items) {
        this.items = new ArrayList<>(items);
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void removeItem(LibraryItem item) {
        items.remove(item);
    }

    public List<LibraryItem> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Returns all items whose title contains the keyword (case-insensitive).
     */
    public List<LibraryItem> search(String keyword) {
        List<LibraryItem> result = new ArrayList<>();
        String lower = keyword.toLowerCase();
        for (LibraryItem item : items) {
            if (item.getTitle().toLowerCase().contains(lower)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Returns all items created by the given author (exact match).
     * Only Book items can match, since only books have Authors.
     */
    public List<LibraryItem> search(Author author) {
        List<LibraryItem> result = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item instanceof Book && ((Book) item).getAuthor().equals(author)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Returns all Music items associated with the given recording artist.
     * Matches when the artist is the sole creator or a member of the creating band.
     */
    public List<LibraryItem> search(RecordingArtist artist) {
        List<LibraryItem> result = new ArrayList<>();
        for (LibraryItem item : items) {
            if (!(item instanceof Music)) continue;
            Creator creator = item.getCreator();
            if (creator instanceof RecordingArtist && creator.equals(artist)) {
                result.add(item);
            } else if (creator instanceof Band && ((Band) creator).hasMember(artist)) {
                result.add(item);
            }
        }
        return result;
    }
}
