CREATE TABLE dummy
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    is_deleted      BIT(1) NOT NULL,
    CONSTRAINT pk_dummy PRIMARY KEY (id)
);

DROP TABLE tbc_instructor;

DROP TABLE tbc_mentor;

DROP TABLE tbc_ta;

DROP TABLE tbc_user;