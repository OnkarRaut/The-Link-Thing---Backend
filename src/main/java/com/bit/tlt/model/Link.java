package com.bit.tlt.model;

import lombok.Data;

import java.util.Date;

@Data
public class Link {

    private Long id;

    private String name;

    private String url;

    private Date createdAt;

    private Date updatedAt;

}
