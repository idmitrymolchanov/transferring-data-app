package psychotest.repository;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.database2.entity.SberTest2;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class Database2Repository implements Database2Repo{

    @Autowired
    @Qualifier("mySqljdbcTemplate2")
    private JdbcTemplate jdbcTemplate;

    private static final String SQL = "select * from T_REPORT_SBERTEST_USER_PSYCHOTEST1";

    private static Date mainDate;

    static {
        try {
            mainDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-28");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Date getMainDate() {
        return mainDate;
    }

    public static void setMainDate(Date mainDate) {
        psychotest.repository.Database2Repository.mainDate = mainDate;
    }

    public List<SberTest2> isData() {

        List<SberTest2> sberTest2s = new ArrayList<SberTest2>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);

        for (Map<String, Object> row : rows) {
            SberTest2 sberTest2 = new SberTest2();

            sberTest2.setId((Long) row.get("id"));
            sberTest2.setEXTID_BCKGR((String)row.get("extid_BCKGR"));
            sberTest2.setEXTID_USER((String)row.get("extid_USER"));
            sberTest2.setTABNUM((String)row.get("tabnum"));
            sberTest2.setCHANGE_DATE((String)row.get("change_DATE"));
            sberTest2.setEXTID_PROGRAM((String)row.get("extid_PROGRAM"));
            sberTest2.setNAME_PROGRAM((String)row.get("name_PROGRAM"));
            sberTest2.setSCALE((String)row.get("scale"));
            sberTest2.setEND_DATE_SCORE((String)row.get("end_DATE_SCORE"));
            sberTest2.setNAME_SCORE((String)row.get("name_SCORE"));
            sberTest2.setSTART_DATE_SCORE((String)row.get("start_DATE_SCORE"));
            sberTest2.setEXTID_TEST((String)row.get("extid_TEST"));
            sberTest2.setNAME_TEST((String)row.get("name_TEST"));
            sberTest2.setRESULT_SCORE_NUM((Double) row.get("result_SCORE_NUM"));

            sberTest2s.add(sberTest2);
        }

        return sberTest2s;

    }

    @Override
    public void saveBatch(final List<SberTest2> employeeList) {
        final int batchSize = 500;
        List<List<SberTest2>> batchLists = Lists.partition(employeeList, batchSize);
        String sql = "INSERT INTO T_REPORT_SBERTEST_USER_PSYCHOTEST1(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        for(List<SberTest2> batch : batchLists) {
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    SberTest2 employee = batch.get(i);
                    ps.setLong(1, employee.getId());
                    ps.setString(2, employee.getEXTID_BCKGR());
                    ps.setString(3, employee.getExtid_USER());
                    ps.setString(4, employee.getTABNUM());
                    ps.setString(5, employee.getCHANGE_DATE());
                    ps.setString(6, employee.getEXTID_PROGRAM());
                    ps.setString(7, employee.getNAME_PROGRAM());
                    ps.setString(8, employee.getSCALE());
                    ps.setString(9, employee.getEND_DATE_SCORE());
                    ps.setString(10, employee.getNAME_SCORE());
                    ps.setString(11, employee.getSTART_DATE_SCORE());
                    ps.setString(12, employee.getEXTID_TEST());
                    ps.setString(13, employee.getNAME_TEST());
                    ps.setDouble(14, employee.getRESULT_SCORE_NUM());
                }

                @Override
                public int getBatchSize() {
                    return batch.size();
                }
            });
        }
    }

    public List<SberTest2> getDataList2SinceCurrentDate(Date date) {
        try {
            String sql = "select * from T_REPORT_SBERTEST_USER_PSYCHOTEST1 where unix_timestamp(end_DATE_SCORE)*1000 > ?;";

            List<SberTest2> sberTest2s = new ArrayList<SberTest2>();

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, mainDate.getTime());

            for (Map<String, Object> row : rows) {
                SberTest2 sberTest2 = new SberTest2();

                sberTest2.setId((long) row.get("id"));
                sberTest2.setEXTID_BCKGR((String) row.get("extid_BCKGR"));
                sberTest2.setEXTID_USER((String) row.get("extid_USER"));
                sberTest2.setTABNUM((String) row.get("tabnum"));
                sberTest2.setCHANGE_DATE((String) row.get("change_DATE"));
                sberTest2.setEXTID_PROGRAM((String) row.get("extid_PROGRAM"));
                sberTest2.setNAME_PROGRAM((String) row.get("name_PROGRAM"));
                sberTest2.setSCALE((String) row.get("scale"));
                sberTest2.setEND_DATE_SCORE((String) row.get("end_DATE_SCORE"));
                sberTest2.setNAME_SCORE((String) row.get("name_SCORE"));
                sberTest2.setSTART_DATE_SCORE((String) row.get("start_DATE_SCORE"));
                sberTest2.setEXTID_TEST((String) row.get("extid_TEST"));
                sberTest2.setNAME_TEST((String) row.get("name_TEST"));
                sberTest2.setRESULT_SCORE_NUM((double) row.get("result_SCORE_NUM"));

                sberTest2s.add(sberTest2);
            }

            for (int i = 0; i < sberTest2s.size(); i++) {
                System.out.println(sberTest2s.get(i).getEND_DATE_SCORE());
            }

            setMainDate(date);
            return sberTest2s;
        } catch (Exception e){
            return Collections.EMPTY_LIST;
        }
    }
}


