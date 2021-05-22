package com.bit.tlt.model.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USER_LINKS", schema = "TLT", catalog = "THELINKTHING")
public class UserLinks {
    private Long id;
    private Link linkByLinkFk;

    @Id
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLinks userLinks = (UserLinks) o;
        return Objects.equals(id, userLinks.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "LINK_FK", referencedColumnName = "ID", nullable = false)
    public Link getLinkByLinkFk() {
        return linkByLinkFk;
    }

    public void setLinkByLinkFk(Link linkByLinkFk) {
        this.linkByLinkFk = linkByLinkFk;
    }
}
