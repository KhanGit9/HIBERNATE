package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

    public class UserDaoHibernateImpl implements UserDao {
        public UserDaoHibernateImpl() {}
        @Override
        public void createUsersTable() {
            Session session = null;
            try {
                session = Util.getInstance().getFactory().openSession();
                session.beginTransaction();
                session.createSQLQuery("CREATE TABLE IF NOT EXISTS User"+
                        "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(45), lastname VARCHAR (45),"+
                        "age TINYINT (2))").executeUpdate();
                session.getTransaction().commit();
                System.out.printf("Таблица создана\n");
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        @Override
        public void dropUsersTable() {
            Session session = null;
            try {
                session = Util.getInstance().getFactory().openSession();
                session.beginTransaction();
                session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
                session.getTransaction().commit();
                System.out.printf("Таблица удалена");
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        @Override
        public void saveUser(String name, String lastName, byte age) {
            Session session = null;
            try {
                session = Util.getInstance().getFactory().openSession();
                session.beginTransaction();
                session.save(new User(name, lastName, age));
                session.getTransaction().commit();
                System.out.println("Пользователь " + name + " Добавлен");
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        @Override
        public void removeUserById(long id) {
            Session session = null;
            try {
                session = Util.getInstance().getFactory().openSession();
                session.beginTransaction();
                User user = session.get(User.class, id);
                session.delete(user);
                session.getTransaction().commit();
                System.out.println("Пользователь с ID = " + id + " удален");
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        @Override
        public List<User> getAllUsers() {
            List <User> userList = new ArrayList<>();
            Session session = null;
            try {
                session = Util.getInstance().getFactory().openSession();
                session.beginTransaction();
                userList = session.createQuery("FROM User").list();
                session.getTransaction().commit();
                System.out.println(userList);
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
            return userList;
        }
        @Override
        public void cleanUsersTable() {
            Session session = null;
            try {
                session = Util.getInstance().getFactory().openSession();
                session.beginTransaction();
                session.createSQLQuery("TRUNCATE TABLE User").executeUpdate();
                session.getTransaction().commit();
                System.out.println("Таблица очищена");
            } catch (Exception e) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
    }