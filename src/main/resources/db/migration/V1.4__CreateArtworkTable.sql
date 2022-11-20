CREATE TABLE ARTWORK(
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    TITLE VARCHAR(255),
    DESCRIPTION VARCHAR(255),
    ART_IMAGE_PATH VARCHAR(255),
    CATEGORY_ID BIGINT REFERENCES CATEGORY (ID),
    USER_ID BIGINT REFERENCES USERS (ID),
    GALLERY_ID BIGINT REFERENCES GALLERY (ID),
    AMOUNT NUMERIC(8,2),
    CURRENCY VARCHAR(255),
    PRIMARY KEY (ID)
);