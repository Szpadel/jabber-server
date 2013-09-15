package net.komunikator.server.database;

import net.komunikator.server.models.Connection;
import net.komunikator.server.models.Contact;
import net.komunikator.server.models.Message;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: radiasztan
 * Date: 14.09.13
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */
public class DBAccess {

    private static DBAccess dbaccess = null;

    protected DBAccess() {
    }

    public static DBAccess getInstance() {
        if (dbaccess == null) {
            dbaccess = new DBAccess();
        }
        return dbaccess;
    }


    public void save(Connection connection) {
        Transaction trns = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            trns = session.beginTransaction();
            session.save(connection);
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void save(Contact contact) {
        Transaction trns = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            trns = session.beginTransaction();
            session.save(contact);
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void save(Message message) {
        Transaction trns = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            trns = session.beginTransaction();
            session.save(message);
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void update(Connection connection) {
        Transaction trns = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            trns = session.beginTransaction();
            session.update(connection);
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void update(Contact contact) {
        Transaction trns = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            trns = session.beginTransaction();
            session.update(contact);
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void update(Message message) {
        Transaction trns = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            trns = session.beginTransaction();
            session.update(message);
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(Connection connection) {
        Transaction trns = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            trns = session.beginTransaction();
            session.delete(connection);
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(Contact contact) {
        Transaction trns = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            trns = session.beginTransaction();
            session.delete(contact);
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(Message message) {
        Transaction trns = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            trns = session.beginTransaction();
            session.delete(message);
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Connection getConnectionById(Integer id) {
        Connection connection = new Connection();
        Transaction trns = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            trns = session.beginTransaction();
            connection = (Connection) session.get(Connection.class, id);
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return connection;
    }

    public Contact getContactById(Integer id) {
        Contact contact = new Contact();
        Transaction trns = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            trns = session.beginTransaction();
            contact = (Contact) session.get(Contact.class, id);
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return contact;
    }

    public Message getMessageById(Integer id) {
        Message message = new Message();
        Transaction trns = null;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            trns = session.beginTransaction();
            message = (Message) session.get(Message.class, id);
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return message;
    }

    public List<Connection> getAllConnections() {

        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Connection> connections = null;

        try {

            trns = session.beginTransaction();
            connections = session.createQuery("from Connection").list();
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return connections;
    }

    public List<Contact> getAllContacts() {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Contact> contacts = null;

        try {

            trns = session.beginTransaction();
            contacts = session.createQuery("from Contact").list();
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return contacts;
    }

    public List<Message> getAllMessages() {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Message> messages = null;

        try {

            trns = session.beginTransaction();
            messages = session.createQuery("from Message").list();
            trns.commit();

        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return messages;
    }
}
