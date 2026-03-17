package problem2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a band (group) that creates music.
 * A band has a name and a collection of RecordingArtist members.
 */
public class Band implements Creator {

    private String name;
    private List<RecordingArtist> members;

    public Band(String name, List<RecordingArtist> members) {
        this.name = name;
        this.members = new ArrayList<>(members);
    }

    public String getName() {
        return name;
    }

    public List<RecordingArtist> getMembers() {
        return new ArrayList<>(members);
    }

    public boolean hasMember(RecordingArtist artist) {
        return members.contains(artist);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Band)) return false;
        Band other = (Band) o;
        return Objects.equals(name, other.name) && Objects.equals(members, other.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, members);
    }

    @Override
    public String toString() {
        return name;
    }
}
