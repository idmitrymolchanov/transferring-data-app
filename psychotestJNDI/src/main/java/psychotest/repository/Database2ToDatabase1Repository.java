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
import java.util.List;

@Repository
public class Database2ToDatabase1Repository implements Database2Repo{

    @Autowired
    @Qualifier("mySqljdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveBatch(final List<SberTest2> employeeList) {
        try {
            final int batchSize = 500;
            List<List<SberTest2>> batchLists = Lists.partition(employeeList, batchSize);
            String sql = "INSERT INTO T_REPORT_SBERTEST_USER_PSYCHOTEST(id, extid_BCKGR, extid_USER, tabnum, change_DATE, extid_PROGRAM, name_PROGRAM, scale, end_DATE_SCORE, name_SCORE, start_DATE_SCORE, extid_TEST, name_TEST, result_SCORE_NUM) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            for (List<SberTest2> batch : batchLists) {
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
        } catch (Exception e){
            return;
        }
    }
}
