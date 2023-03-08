package main.model;

import javax.persistence.*;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    private Long id;
    private String name;
    private String pass;
    private String sessionID;

    public User(String name, String pass, String sessionID) {
        this.name = name;
        this.pass = pass;
        this.sessionID= sessionID;

    }

    public User() {

    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

}
