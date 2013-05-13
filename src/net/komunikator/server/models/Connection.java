package net.komunikator.server.models;

import javax.crypto.Cipher;
import javax.crypto.NullCipher;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 25.03.13
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
public class Connection {
    int id;
    String name;
    String username;
    String password;
    String domain;
    String resource;

    Cipher crypto;

    public Connection(int id, String name, String username, String domain, String resource) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.domain = domain;
        this.resource = resource;
        try {
            crypto = Cipher.getInstance("DES");
        } catch (Exception e) {
            e.printStackTrace();
            crypto = new NullCipher();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PrePersist
    void prePersist(Object object) {
        //TODO: szyfrowanie
    }

    @PreUpdate
    void preUpdate(Object object) {
        //TODO: szyfrowanie
    }

    @PostLoad
    void postLoad(Object object) {
        //TODO: deszyfrowanie
    }
}
