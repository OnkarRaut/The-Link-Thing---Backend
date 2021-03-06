package com.bit.tlt.model.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Link {
    private Long id;
    private String name;
    private String url;
    private Date createdAt;
    private Date updatedAt;
    private Category categoryByCategoryFk;

    @Id
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "URL", nullable = false, length = -1)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "CREATED_AT", nullable = false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "UPDATED_AT", nullable = false)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(id, link.id) &&
                Objects.equals(name, link.name) &&
                Objects.equals(url, link.url) &&
                Objects.equals(createdAt, link.createdAt) &&
                Objects.equals(updatedAt, link.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, createdAt, updatedAt);
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORY_FK", referencedColumnName = "ID")
    public Category getCategoryByCategoryFk() {
        return categoryByCategoryFk;
    }

    public void setCategoryByCategoryFk(Category categoryByCategoryFk) {
        this.categoryByCategoryFk = categoryByCategoryFk;
    }
}
