package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    private static Util instance;
    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }
    private SessionFactory factory;
    public SessionFactory getFactory () {
        if (factory == null) {
            try {
                Configuration configuration = new Configuration().addAnnotatedClass(User.class);
                factory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return factory;
    }
}


