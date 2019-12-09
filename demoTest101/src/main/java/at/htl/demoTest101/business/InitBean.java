package at.htl.demoTest101.business;

//import javax.enterprise.context.ApplicationScoped;

import at.htl.demoTest101.model.Album;
import at.htl.demoTest101.model.Artist;
import at.htl.demoTest101.model.Genre;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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

    @Transactional
    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) Object object){

        System.out.println("Hello");
        //readArtistFromCsv(_artistFile);
        //readGenreFromCsv(_genreFile);
        //readAlbumFromCsv(_albumFile); //Error
    }

    private void readArtistFromCsv(String fileName) {
        URL url = Thread.currentThread().getContextClassLoader()
                .getResource(fileName);
        try (Stream<String> stream = Files.lines(Paths.get(url.getPath()), StandardCharsets.ISO_8859_1)) {

            List<Artist> artistList = stream
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map( elem -> new Artist( Long.valueOf(elem[0]), elem[1]))
                    .collect(Collectors.toList());
            //em.getTransaction().begin();
            for (Artist item : artistList ) {
                System.out.println(item);
                em.merge(item);
            }
            //em.getTransaction().commit();
                    //.forEach(em::merge);
                    //.forEach(p -> System.out.println(p));
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
            //em.getTransaction().begin();
            for (Genre item : artistList ) {
                System.out.println(item);
                em.merge(item);
            }
            //em.getTransaction().commit();
            //.forEach(em::merge);
            //.forEach(p -> System.out.println(p));
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
                    .map( elem -> new Album( Long.valueOf(elem[0]), elem[1], em.find(Artist.class, Integer.valueOf(elem[2]))))//
                    .collect(Collectors.toList());
            //em.getTransaction().begin();
            for (Album item : artistList ) {
                //em.find(Artist.class, elem[2])
                System.out.println(item);
                em.merge(item);
            }
            //em.getTransaction().commit();
            //.forEach(em::merge);
            //.forEach(p -> System.out.println(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Caused by: java.lang.IllegalArgumentException: Provided id of the wrong type for class at.htl.demoTest101.model.Artist. Expected: class java.lang.Long, got class java.lang.Integer
	at org.hibernate@5.3.10.Final//org.hibernate.internal.SessionImpl.find(SessionImpl.java:3511)
	at org.hibernate@5.3.10.Final//org.hibernate.internal.SessionImpl.find(SessionImpl.java:3454)
	at org.jboss.as.jpa@17.0.1.Final//org.jboss.as.jpa.container.AbstractEntityManager.find(AbstractEntityManager.java:213)
	at deployment.demoTest101-1.0-SNAPSHOT.war//at.htl.demoTest101.business.InitBean.lambda$readAlbumFromCsv$5(InitBean.java:101)

    * */
}
