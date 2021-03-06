package at.htl.demoTest101.business;

//import javax.enterprise.context.ApplicationScoped;

import at.htl.demoTest101.model.Album;
import at.htl.demoTest101.model.Artist;
import at.htl.demoTest101.model.Genre;
import at.htl.demoTest101.model.Track;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class InitBean {

    @PersistenceContext
    EntityManager em;

    private static final String _artistFile = "Artist.csv";
    private static final String _genreFile = "Genre.csv";
    private static final String _albumFile = "Album.csv";
    private static final String _trackFile = "Track.csv";

    @Transactional
    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) Object object){

        System.out.println("Hello");
        readArtistFromCsv(_artistFile);
        readGenreFromCsv(_genreFile);
        readAlbumFromCsv(_albumFile);
        readTrackFromCsv(_trackFile);


        //Track t = new Track((long)1, "NoWay", em.find(Album.class, (long)1), em.find(Genre.class, (long)1), "King", (long)123, (long)123, (double) 12.2);
        //em.merge(t);
        //System.out.println("YES");
    }

    private void readTrackFromCsv(String trackFile) {

        //Id;Name;                                     AlbumId;GenreId;                                  Composer; Milliseconds;    Bytes; UnitPrice
        //1; For Those About To Rock (We Salute You);        1;      1; Angus Young, Malcolm Young, Brian Johnson;       343719; 11170334; 0,99

//        Track(long id, String name, Artist artist, Album album, Genre genre, String composer,
//        long milliseconds, long bytes, double unitPrice)
//
        URL url = Thread.currentThread().getContextClassLoader()
                .getResource(trackFile);
        try (Stream<String> stream = Files.lines(Paths.get(url.getPath()), StandardCharsets.ISO_8859_1)){
            stream.skip(1)
                    .map(line -> line.split(";"))
                    .map( elem -> new Track(
                            Long.valueOf(elem[0]),
                            elem[1],
                            em.find(Album.class, Long.valueOf(elem[2])),
                            em.find(Genre.class, Long.valueOf(elem[3])),
                            elem[4],
                            Long.valueOf(elem[5]),
                            Long.valueOf(elem[6]),
                            Double.valueOf(elem[7].replace(',', '.'))))
                    .forEach(em::merge);
            System.out.println("~~~~~~~~ Read: Track");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readArtistFromCsv(String fileName) {
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ read Genre.csv");
        URL url = Thread.currentThread().getContextClassLoader()
                .getResource(fileName);
        try (Stream<String> stream = Files.lines(Paths.get(url.getPath()), StandardCharsets.ISO_8859_1)) {

            List<Artist> artistList = stream
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map( elem -> new Artist( Long.valueOf(elem[0]), elem[1]))
                    .collect(Collectors.toList());
            for (Artist item : artistList ) {
                //System.out.println(item);
                em.merge(item);
            }
            //em.getTransaction().commit();
                    //.forEach(em::merge);
                    //.forEach(p -> System.out.println(p));
            System.out.println("~~~~~~~~ Read: Artist");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readGenreFromCsv(String fileName) {
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ read Genre.csv");
        URL url = Thread.currentThread().getContextClassLoader()
                .getResource(fileName);
        try (Stream<String> stream = Files.lines(Paths.get(url.getPath()), StandardCharsets.ISO_8859_1)) {

            List<Genre> artistList = stream
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map( elem -> new Genre( Long.valueOf(elem[0]), elem[1]))
                    .collect(Collectors.toList());
            for (Genre item : artistList ) {
                //System.out.println(item);
                em.merge(item);
            }
            System.out.println("~~~~~~~~ Read: Genre");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAlbumFromCsv(String fileName) {
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ read Genre.csv");
        URL url = Thread.currentThread().getContextClassLoader()
                .getResource(fileName);
        try (Stream<String> stream = Files.lines(Paths.get(url.getPath()), StandardCharsets.ISO_8859_1)) {
            //Id;Title;ArtistId
            List<Album> artistList = stream
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map( elem -> new Album( Long.valueOf(elem[0]), elem[1], em.find(Artist.class, Long.valueOf(elem[2]))))//
                    .collect(Collectors.toList());
            for (Album item : artistList ) {
                //System.out.println(item);
                em.merge(item);
            }
            System.out.println("~~~~~~~~ Read: Album");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
