package psychotest.repository;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import psychotest.database1.entity.SberTest1;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class Database1Repository{

    @Autowired
    @Qualifier("mySqljdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    private static final String SQL = "select * from T_REPORT_SBERTEST_USER_PSYCHOTEST";

    public List<SberTest1> isData() {

        List<SberTest1> sberTest1s = new ArrayList<SberTest1>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);

        for (Map<String, Object> row : rows) {
            SberTest1 sberTest1 = new SberTest1();

            sberTest1.setId((Long) row.get("id"));
            sberTest1.setEXTID_BCKGR((String)row.get("extid_BCKGR"));
            sberTest1.setEXTID_USER((String)row.get("extid_USER"));
            sberTest1.setTABNUM((String)row.get("tabnum"));
            sberTest1.setCHANGE_DATE((String)row.get("change_DATE"));
            sberTest1.setEXTID_PROGRAM((String)row.get("extid_PROGRAM"));
            sberTest1.setNAME_PROGRAM((String)row.get("name_PROGRAM"));
            sberTest1.setSCALE((String)row.get("scale"));
            sberTest1.setEND_DATE_SCORE((String)row.get("end_DATE_SCORE"));
            sberTest1.setNAME_SCORE((String)row.get("name_SCORE"));
            sberTest1.setSTART_DATE_SCORE((String)row.get("start_DATE_SCORE"));
            sberTest1.setEXTID_TEST((String)row.get("extid_TEST"));
            sberTest1.setNAME_TEST((String)row.get("name_TEST"));
            sberTest1.setRESULT_SCORE_NUM((Double) row.get("result_SCORE_NUM"));

            sberTest1s.add(sberTest1);
        }
        return sberTest1s;
    }

    public void saveBatch(final List<SberTest1> employeeList) {
        final int batchSize = 500;
        List<List<SberTest1>> batchLists = Lists.partition(employeeList, batchSize);
        String sql = "INSERT INTO T_REPORT_SBERTEST_USER_PSYCHOTEST(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        for(List<SberTest1> batch : batchLists) {
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i)
                        throws SQLException {
                    SberTest1 employee = batch.get(i);
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
}