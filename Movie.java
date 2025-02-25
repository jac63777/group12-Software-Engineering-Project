public class Movie {
    private int id;
    private String title;
    private String genre;
    private String cast;
    private String director;
    private String producer;
    private String synopsis;
    private String reviews;
    private String picture;
    private String video;
    private String mppa;
    private String showtimes;

    public Movie(int id, String title, String genre, String cast, String director,
                 String producer, String synopsis, String reviews, String picture,
                 String video, String mppa, String showtimes) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.cast = cast;
        this.director = director;
        this.producer = producer;
        this.synopsis = synopsis;
        this.reviews = reviews;
        this.picture = picture;
        this.video = video;
        this.mppa = mppa;
        this.showtimes = showtimes;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getCast() {
        return cast;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getReviews() {
        return reviews;
    }

    public String getPicture() {
        return picture;
    }

    public String getVideo() {
        return video;
    }

    public String getMppa() {
        return mppa;
    }

    public String getShowtimes() {
        return showtimes;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setMppa(String mppa) {
        this.mppa = mppa;
    }

    public void setShowtimes(String showtimes) {
        this.showtimes = showtimes;
    }

    // Display movie details
    public void displayMovie() {
        System.out.println("Movie ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Genre: " + genre);
        System.out.println("Cast: " + cast);
        System.out.println("Director: " + director);
        System.out.println("Producer: " + producer);
        System.out.println("Synopsis: " + synopsis);
        System.out.println("Reviews: " + reviews);
        System.out.println("Picture URL: " + picture);
        System.out.println("Video URL: " + video);
        System.out.println("MPAA Rating: " + mppa);
        System.out.println("Showtimes: " + showtimes);
    }
}
