package problem2;

/**
 * Represents a music item in the library catalog.
 * A music item's creator can be a RecordingArtist or a Band.
 */
public class Music extends LibraryItem {

    public Music(Creator creator, String title, int year) {
        super(creator, title, year);
    }
}
