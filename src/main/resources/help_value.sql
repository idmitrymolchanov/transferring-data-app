USE userdb;

DROP TABLE IF EXISTS HELP_VALUE;
CREATE TABLE HELP_VALUE
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    value1 VARCHAR(255),
    value2 VARCHAR(255),
    value3 VARCHAR(255),
    value4 VARCHAR(255),
    value5 VARCHAR(255),
    value6 VARCHAR(255),
    value7 VARCHAR(255),
    value8 VARCHAR(255),
    value9 VARCHAR(255),
    value10 VARCHAR(255),
    value11 VARCHAR(255),
    value12 VARCHAR(255),
    value13 VARCHAR(255),
    value14 VARCHAR(255),
    value15 VARCHAR(255),
    value16 VARCHAR(255),
    value17 VARCHAR(255),
    value18 VARCHAR(255),
    value19 VARCHAR(255),
    value20 VARCHAR(255),
    value21 VARCHAR(255),
    value22 VARCHAR(255),
    value23 VARCHAR(255),
    value24 VARCHAR(255),
    value25 VARCHAR(255),
    value26 VARCHAR(255),
    value27 VARCHAR(255),
    value28 VARCHAR(255),
    value29 VARCHAR(255),
    value30 VARCHAR(255),
    value31 VARCHAR(255),
    value32 VARCHAR(255),
    value33 VARCHAR(255),
    value34 VARCHAR(255),
    value35 VARCHAR(255),
    value36 VARCHAR(255),
    value37 VARCHAR(255),
    value38 VARCHAR(255),
    value39 VARCHAR(255),
    value40 VARCHAR(255)
);

DROP TABLE IF EXISTS HELP_TYPE;
CREATE TABLE HELP_TYPE
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type1 VARCHAR(255),
    type2 VARCHAR(255),
    type3 VARCHAR(255),
    type4 VARCHAR(255),
    type5 VARCHAR(255),
    type6 VARCHAR(255),
    type7 VARCHAR(255),
    type8 VARCHAR(255),
    type9 VARCHAR(255),
    type10 VARCHAR(255),
    type11 VARCHAR(255),
    type12 VARCHAR(255),
    type13 VARCHAR(255),
    type14 VARCHAR(255),
    type15 VARCHAR(255),
    type16 VARCHAR(255),
    type17 VARCHAR(255),
    type18 VARCHAR(255),
    type19 VARCHAR(255),
    type20 VARCHAR(255),
    type21 VARCHAR(255),
    type22 VARCHAR(255),
    type23 VARCHAR(255),
    type24 VARCHAR(255),
    type25 VARCHAR(255),
    type26 VARCHAR(255),
    type27 VARCHAR(255),
    type28 VARCHAR(255),
    type29 VARCHAR(255),
    type30 VARCHAR(255),
    type31 VARCHAR(255),
    type32 VARCHAR(255),
    type33 VARCHAR(255),
    type34 VARCHAR(255),
    type35 VARCHAR(255),
    type36 VARCHAR(255),
    type37 VARCHAR(255),
    type38 VARCHAR(255),
    type39 VARCHAR(255),
    type40 VARCHAR(255)
);

INSERT INTO HELP_TYPE(type1,type2,type3,type4,type5,type6,type7,type8,type9,type10,type11,type12,type13,type14,type15,type16,type17,type18,type19,type20,type21,type22,type23,type24,type25,type26,type27,type28,type29,type30,type31,type32,type33,type34,type35,type36,type37,type38,type39,type40) VALUES('kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk','kkkk');


DROP TABLE IF EXISTS HELP_NOT_NULL;
CREATE TABLE HELP_NOT_NULL
(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    v_not1 VARCHAR(255),
    v_not2 VARCHAR(255),
    v_not3 VARCHAR(255),
    v_not4 VARCHAR(255),
    v_not5 VARCHAR(255),
    v_not6 VARCHAR(255),
    v_not7 VARCHAR(255),
    v_not8 VARCHAR(255),
    v_not9 VARCHAR(255),
    v_not10 VARCHAR(255),
    v_not11 VARCHAR(255),
    v_not12 VARCHAR(255),
    v_not13 VARCHAR(255),
    v_not14 VARCHAR(255),
    v_not15 VARCHAR(255),
    v_not16 VARCHAR(255),
    v_not17 VARCHAR(255),
    v_not18 VARCHAR(255),
    v_not19 VARCHAR(255),
    v_not20 VARCHAR(255),
    v_not21 VARCHAR(255),
    v_not22 VARCHAR(255),
    v_not23 VARCHAR(255),
    v_not24 VARCHAR(255),
    v_not25 VARCHAR(255),
    v_not26 VARCHAR(255),
    v_not27 VARCHAR(255),
    v_not28 VARCHAR(255),
    v_not29 VARCHAR(255),
    v_not30 VARCHAR(255),
    v_not31 VARCHAR(255),
    v_not32 VARCHAR(255),
    v_not33 VARCHAR(255),
    v_not34 VARCHAR(255),
    v_not35 VARCHAR(255),
    v_not36 VARCHAR(255),
    v_not37 VARCHAR(255),
    v_not38 VARCHAR(255),
    v_not39 VARCHAR(255),
    v_not40 VARCHAR(255)
);

DROP TABLE IF EXISTS HELP_TABLE_NAME;
CREATE TABLE HELP_TABLE_NAME
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    table_name VARCHAR(255),
    quantity INT
);

DROP TABLE IF EXISTS HELP_DATASOURCE;
CREATE TABLE HELP_DATASOURCE
(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    driver_class_name VARCHAR(255),
    url VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255)
);
