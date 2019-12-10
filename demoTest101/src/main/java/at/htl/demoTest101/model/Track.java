package at.htl.demoTest101.model;

import javax.persistence.*;

@Entity
@Table(name = "Track")
public class Track {
    //Id;Name;AlbumId;GenreId;Composer;Milliseconds;Bytes;UnitPrice
    //1;For Those About To Rock (We Salute You);1;1;Angus Young, Malcolm Young, Brian Johnson;343719;11170334;0,99
    @Id
    private long id;
    private String name;

    @ManyToOne
    //@JoinColumn(name = "Album.Id")
    private Album album;

    @ManyToOne
    //@JoinColumn(name = "Genre.id")
    private Genre genre;

    private String composer;
    private long milliseconds;
    private long bytes;
    private double unitPrice;

    @ManyToOne
    //@JoinColumn(name = "Artist.id");
    private Artist artist;

    //region Constructors

    public Track() {
    }

    public Track(long id, String name, Album album, Genre genre, String composer,
                 long milliseconds, long bytes, double unitPrice){
        this.id = id;
        this.name = name;
        this.album = album;
        this.genre = genre;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }

    public Track(String name, Album album, Genre genre, String composer,
                 long milliseconds, long bytes, double unitPrice){
        this.name = name;
        this.album = album;
        this.genre = genre;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }
    //endregion


    //region Properties

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Album='" + album + '\'' +
                ", Genre='" + genre + '\'' +
                ", composer='" + composer + '\'' +
                ", milliseconds='" + milliseconds + '\'' +
                ", bytes='" + bytes + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                '}';
    }

    //endregion

}
