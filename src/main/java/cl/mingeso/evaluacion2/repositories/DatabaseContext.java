package cl.mingeso.evaluacion2.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sql2o.Sql2o;

/*
username = doadmin
password = KZTYXAVKOtBleNTB
host = db-postgresql-nyc3-90274-do-user-10562283-0.b.db.ondigitalocean.com
port = 25060
database = defaultdb
sslmode = require
*/

@Configuration
public class DatabaseContext {
    @Value("${database.url}")
    private String dbUrl;

    @Value("${database.user}")
    private String dbUser;

    @Value("${database.password}")
    private String dbPass;
    
    @Bean
    public Sql2o sql2o(){
        return new Sql2o(dbUrl, dbUser, dbPass);
    }

}