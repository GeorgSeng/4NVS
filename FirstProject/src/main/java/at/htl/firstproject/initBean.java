package at.htl.firstproject;

import org.w3c.dom.ls.LSOutput;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.json.bind.annotation.JsonbTransient;

@Startup
@Singleton
public class initBean {

    @PostConstruct
    private void Init(){
        System.out.println("*** it works! ***");
    }
}
