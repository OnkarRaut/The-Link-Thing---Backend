package com.bit.tlt.model.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Date createdAt;
    private Category categoryById;
    private Link linkById;
    private LinkGroup linkGroupById;
    private LinkGroup linkGroupById_0;
    private UserLinks userLinksById;

    @Id
    @Basic
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USERNAME", nullable = false, length = -1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "EMAIL", nullable = false, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "CREATED_AT", nullable = false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, createdAt);
    }

    @ManyToOne
    @JoinColumn(name = "ID", referencedColumnName = "CREATED_BY_FK", nullable = false)
    public Category getCategoryById() {
        return categoryById;
    }

    public void setCategoryById(Category categoryById) {
        this.categoryById = categoryById;
    }

    @ManyToOne
    @JoinColumn(name = "ID", referencedColumnName = "CREATED_BY_FK", nullable = false)
    public Link getLinkById() {
        return linkById;
    }

    public void setLinkById(Link linkById) {
        this.linkById = linkById;
    }

    @ManyToOne
    @JoinColumn(name = "ID", referencedColumnName = "CREATED_BY_FK", nullable = false)
    public LinkGroup getLinkGroupById() {
        return linkGroupById;
    }

    public void setLinkGroupById(LinkGroup linkGroupById) {
        this.linkGroupById = linkGroupById;
    }

    @ManyToOne
    @JoinColumn(name = "ID", referencedColumnName = "UPDATED_BY_FK", nullable = false)
    public LinkGroup getLinkGroupById_0() {
        return linkGroupById_0;
    }

    public void setLinkGroupById_0(LinkGroup linkGroupById_0) {
        this.linkGroupById_0 = linkGroupById_0;
    }

    @ManyToOne
    @JoinColumn(name = "ID", referencedColumnName = "USER_FK", nullable = false)
    public UserLinks getUserLinksById() {
        return userLinksById;
    }

    public void setUserLinksById(UserLinks userLinksById) {
        this.userLinksById = userLinksById;
    }
}
