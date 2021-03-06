USE testdb;

DROP TABLE IF EXISTS TEST_TABLE;
CREATE TABLE TEST_TABLE
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
    result DOUBLE
);

INSERT INTO TEST_TABLE(id, value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, result) VALUES (1111111, '1111111111', '111111', '141111', '2014-01-25 08:21:32.0000000', 'value', 'value', '0 - 10', '2019-01-25 00:00:00.0000000', 'value', '2019-01-21 00:00:00.0000000', 'value', 'value', 1);
INSERT INTO TEST_TABLE(id, value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, result) VALUES (1111112, '1111111112', '111112', '141112', '2014-01-26 08:21:32.0000000', 'value', 'value', '0 - 10', '2019-01-26 00:00:00.0000000', 'value', '2019-01-22 00:00:00.0000000', 'value', 'value', 1);
INSERT INTO TEST_TABLE(id, value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, result) VALUES (1111113, '1111111113', '111113', '141113', '2014-01-27 08:21:32.0000000', 'value', 'value', '0 - 10', '2019-01-27 00:00:00.0000000', 'value', '2019-01-23 00:00:00.0000000', 'value', 'value', 1);
INSERT INTO TEST_TABLE(id, value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, result) VALUES (1111114, '1111111114', '111114', '141114', '2014-01-28 08:21:32.0000000', 'value', 'value', '0 - 10', '2019-01-28 00:00:00.0000000', 'value', '2019-01-24 00:00:00.0000000', 'value', 'value', 1);
INSERT INTO TEST_TABLE(id, value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, result) VALUES (1111115, '1111111111', '111111', '141111', '2014-01-29 08:21:32.0000000', 'value', 'value', '0 - 10', '2019-01-29 00:00:00.0000000', 'value', '2019-01-25 00:00:00.0000000', 'value', 'value', 1);
INSERT INTO TEST_TABLE(id, value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, result) VALUES (1111116, '1111111112', '111112', '141112', '2014-02-01 08:21:32.0000000', 'value', 'value', '0 - 10', '2019-02-01 00:00:00.0000000', 'value', '2019-01-26 00:00:00.0000000', 'value', 'value', 1);
INSERT INTO TEST_TABLE(id, value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, result) VALUES (1111117, '1111111113', '111113', '141113', '2014-03-02 08:21:32.0000000', 'value', 'value', '0 - 10', '2019-03-02 00:00:00.0000000', 'value', '2019-01-27 00:00:00.0000000', 'value', 'value', 1);
INSERT INTO TEST_TABLE(id, value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, result) VALUES (1111118, '1111111114', '111114', '141114', '2014-04-03 08:21:32.0000000', 'value', 'value', '0 - 10', '2019-04-03 00:00:00.0000000', 'value', '2019-01-28 00:00:00.0000000', 'value', 'value', 1);
INSERT INTO TEST_TABLE(id, value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, result) VALUES (1111119, '1111111111', '111111', '141111', '2014-05-04 08:21:32.0000000', 'value', 'value', '0 - 10', '2019-05-04 00:00:00.0000000', 'value', '2019-01-29 00:00:00.0000000', 'value', 'value', 1);
INSERT INTO TEST_TABLE(id, value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, result) VALUES (1111120, '1111111112', '111112', '141112', '2014-06-05 08:21:32.0000000', 'value', 'value', '0 - 10', '2019-06-05 00:00:00.0000000', 'value', '2019-02-01 00:00:00.0000000', 'value', 'value', 1);
INSERT INTO TEST_TABLE(id, value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, result) VALUES (1111121, '1111111113', '111113', '141113', '2014-07-06 08:21:32.0000000', 'value', 'value', '0 - 10', '2019-07-06 00:00:00.0000000', 'value', '2019-03-02 00:00:00.0000000', 'value', 'value', 1);
INSERT INTO TEST_TABLE(id, value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, result) VALUES (1111122, '1111111114', '111114', '141114', '2014-08-07 08:21:32.0000000', 'value', 'value', '0 - 10', '2019-08-07 00:00:00.0000000', 'value', '2019-04-03 00:00:00.0000000', 'value', 'value', 1);
