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
        try (Stream<String> stream = Files.lines(Paths.get(url.getPath()), StandardCharsets.UTF_8)) {
            stream
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(elm -> new Artist( Long.parseLong(elm[0]), elm[1]))
                    //.forEach(em::merge);
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
