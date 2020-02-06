drop schema if exists testdb1;
create schema testdb1;

CREATE TABLE testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    extid_BCKGR VARCHAR(255),
    extid_USER VARCHAR(255),
    tabnum VARCHAR(255),
    change_DATE VARCHAR(255),
    extid_PROGRAM VARCHAR(255),
    name_PROGRAM VARCHAR(255),
    scale VARCHAR(255),
    end_DATE_SCORE VARCHAR(255),
    name_SCORE VARCHAR(255),
    start_DATE_SCORE VARCHAR(255),
    extid_TEST VARCHAR(255),
    name_TEST VARCHAR(255),
    result_SCORE_NUM DOUBLE
);

INSERT INTO testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES (1243580, '2168779357', '154708', '1497935', '2019-01-25 08:21:32.0000000', 'personal-char', 'value', '0 - 10', '2019-01-25 00:00:00.0000000', 'value', '2019-01-21 00:00:00.0000000', '27f18987-bf6d-4d08-8aec-d6f145cafOff', 'value', 1);
INSERT INTO testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES (1243581, '2168779357', '154708', '1497935', '2019-01-26 08:21:32.0000000', 'personal-char', 'value', '0 - 10', '2019-01-26 00:00:00.0000000', 'value', '2019-01-22 00:00:00.0000000', '27f18987-bf6d-4d08-8aec-d6f145cafOff', 'value', 1);
