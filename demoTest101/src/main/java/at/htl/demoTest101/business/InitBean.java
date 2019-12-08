package at.htl.demoTest101.business;

//import javax.enterprise.context.ApplicationScoped;

import at.htl.demoTest101.model.Artist;

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

    @Transactional
    public void init(
            @Observes
            @Initialized(ApplicationScoped.class) Object object){

        System.out.println("Hello");
        readArtistFromCsv(_artistFile);
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
                em.merge(item);
            }
            //em.getTransaction().commit();
                    //.forEach(em::merge);
                    //.forEach(p -> System.out.println(p));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
