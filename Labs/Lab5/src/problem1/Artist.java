import java.util.ArrayList;
import java.util.List;

// --- 1. 抽象基类 Artist ---
abstract class Artist {
    protected String firstName;
    protected String lastName;
    protected int age;
    protected List<String> genres;
    protected List<String> awards;

    public Artist(String firstName, String lastName, int age, List<String> genres, List<String> awards) {
        if (age < 0 || age > 128) {
            throw new IllegalArgumentException("Age must be between 0 and 128");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.genres = genres;
        this.awards = awards;
    }

    // 题目要求的 receiveAward 方法
    public void receiveAward(String award) {
        if (this.awards == null) {
            this.awards = new ArrayList<>();
        }
        this.awards.add(award);
    }
    
    // Getters can be added here
}

// --- 2. 中间抽象类 MultimediaArtist (Actor, Dancer, Filmmaker) ---
abstract class MultimediaArtist extends Artist {
    protected List<String> movies;
    protected List<String> series;
    protected List<String> otherMultimedia;

    public MultimediaArtist(String fName, String lName, int age, List<String> genres, List<String> awards,
                            List<String> movies, List<String> series, List<String> other) {
        super(fName, lName, age, genres, awards);
        this.movies = movies;
        this.series = series;
        this.otherMultimedia = other;
    }
}

// --- 3. 中间抽象类 VisualArtist (Painter, Photographer) ---
abstract class VisualArtist extends Artist {
    protected List<String> exhibits;

    public VisualArtist(String fName, String lName, int age, List<String> genres, List<String> awards,
                        List<String> exhibits) {
        super(fName, lName, age, genres, awards);
        this.exhibits = exhibits;
    }
}

// --- 4. 具体类实现 ---

class Actor extends MultimediaArtist {
    public Actor(String fName, String lName, int age, List<String> genres, List<String> awards,
                 List<String> movies, List<String> series, List<String> other) {
        super(fName, lName, age, genres, awards, movies, series, other);
    }
}

class Dancer extends MultimediaArtist {
    public Dancer(String fName, String lName, int age, List<String> genres, List<String> awards,
                  List<String> movies, List<String> series, List<String> other) {
        super(fName, lName, age, genres, awards, movies, series, other);
    }
}

class Filmmaker extends MultimediaArtist {
    public Filmmaker(String fName, String lName, int age, List<String> genres, List<String> awards,
                     List<String> movies, List<String> series, List<String> other) {
        super(fName, lName, age, genres, awards, movies, series, other);
    }
}

class Painter extends VisualArtist {
    public Painter(String fName, String lName, int age, List<String> genres, List<String> awards, List<String> exhibits) {
        super(fName, lName, age, genres, awards, exhibits);
    }
}

class Photographer extends VisualArtist {
    public Photographer(String fName, String lName, int age, List<String> genres, List<String> awards, List<String> exhibits) {
        super(fName, lName, age, genres, awards, exhibits);
    }
}

class Musician extends Artist {
    private String recordingCompany;
    private String lastRecordAlbum;

    public Musician(String fName, String lName, int age, List<String> genres, List<String> awards,
                    String recordingCompany, String lastRecordAlbum) {
        super(fName, lName, age, genres, awards);
        this.recordingCompany = recordingCompany;
        this.lastRecordAlbum = lastRecordAlbum;
    }
}

class Poet extends Artist {
    private String publishingCompany;
    private String lastPublishedCollection;

    public Poet(String fName, String lName, int age, List<String> genres, List<String> awards,
                String publishingCompany, String lastPublishedCollection) {
        super(fName, lName, age, genres, awards);
        this.publishingCompany = publishingCompany;
        this.lastPublishedCollection = lastPublishedCollection;
    }
}