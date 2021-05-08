create table USER
(
    ID         LONG auto_increment,
    USERNAME   VARCHAR2  not null,
    EMAIL      VARCHAR2  not null,
    PASSWORD   VARCHAR2  not null,
    CREATED_AT TIMESTAMP not null
);

create unique index USER_EMAIL_INDEX
    on USER (EMAIL);

create unique index USER_ID_INDEX
    on USER (ID);

create unique index USER_USERNAME_INDEX
    on USER (USERNAME);

create table CATEGORY
(
    ID            LONG auto_increment,
    NAME          VARCHAR2 not null,
    CREATED_BY_FK LONG     not null,
    constraint CATEGORY_CREATED_BY
        foreign key (CREATED_BY_FK) references USER (ID)
            on delete set default
);

create unique index CATEGORY_ID_INDEX
    on CATEGORY (ID);

alter table CATEGORY
    add constraint CATEGORY_PK
        primary key (ID);

create table LINK
(
    ID            LONG auto_increment,
    NAME          VARCHAR2       not null,
    URL           VARCHAR2       not null,
    CREATED_AT    TIMESTAMP      not null,
    UPDATED_AT    VARCHAR2       not null,
    CREATED_BY_FK LONG default 1 not null,
    CATEGORY_FK   LONG,
    constraint LINK_CATEGORY_FK
        foreign key (CATEGORY_FK) references CATEGORY (ID),
    constraint LINK_CREATED_BY
        foreign key (CREATED_BY_FK) references USER (ID)
            on delete set default
);

create unique index LINK_ID_INDEX
    on LINK (ID);

alter table LINK
    add constraint LINK_PK
        primary key (ID);

create table LINK_GROUP
(
    id long auto_increment,
    name varchar2 not null,
    created_by_fk long not null,
    updated_by_fk long not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    constraint created_by_fk
        foreign key (created_by_fk) references USER (id),
    constraint updated_by_fk
        foreign key (updated_by_fk) references USER (id)
);

create unique index LINK_GROUP_ID_INDEX
    on LINK_GROUP (id);

alter table LINK_GROUP
    add constraint LINK_GROUP_PK
        primary key (id);

create table USER_LINKS
(
    id long auto_increment,
    link_fk long default 0 not null,
    user_fk long default 0 not null,
    constraint link_fk
        foreign key (link_fk) references LINK (id),
    constraint user_fk
        foreign key (user_fk) references USER (id)
);

create unique index USER_LINKS_ID_INDEX
    on USER_LINKS (id);

alter table USER_LINKS
    add constraint USER_LINKS_PK
        primary key (id);
