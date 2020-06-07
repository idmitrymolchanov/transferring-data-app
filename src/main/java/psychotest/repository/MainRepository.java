package psychotest.repository;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import psychotest.entity.EntityClass;
import psychotest.entity.MainEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@Profile("local")
public class MainRepository {
    private static String sourceTableName;
    private static String value_helpTableName;
    private static String type_helpTableName;
    private static String notnull_helpTableName;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MainRepository(@Qualifier("jdbcTemplateUser") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Value("${datasource.four.name}")
    public void setSourceTableName(String sourceTableName) {
        this.sourceTableName = sourceTableName;
    }
    @Value("${datasource.help.values}")
    public static void setValue_helpTableName(String value_helpTableName) {
        MainRepository.value_helpTableName = value_helpTableName;
    }
    @Value("${datasource.help.types}")
    public static void setType_helpTableName(String type_helpTableName) {
        MainRepository.type_helpTableName = type_helpTableName;
    }
    @Value("${datasource.help.notnull}")
    public static void setNotnull_helpTableName(String notnull_helpTableName) {
        MainRepository.notnull_helpTableName = notnull_helpTableName;
    }

    private String sqlSelect;
    private String sqlInsert;
    public static Integer quantityTable;

    public void insertProcessing(List<String> list, String aim) {
        if(list.size() != 0) {
            if(aim.contains("Value"))
                sqlInsert = "INSERT INTO userdb.help_value (value1,value2,value3,value4,value5,value6,value7,value8,value9,value10,value11,value12,value13,value14,value15,value16,value17,value18,value19,value20,value21,value22,value23,value24,value25,value26,value27,value28,value29,value30,value31,value32,value33,value34,value35,value36,value37,value38,value39,value40) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            else if(aim.contains("Type"))
                sqlInsert = "INSERT INTO userdb.help_type (type1,type2,type3,type4,type5,type6,type7,type8,type9,type10,type11,type12,type13,type14,type15,type16,type17,type18,type19,type20,type21,type22,type23,type24,type25,type26,type27,type28,type29,type30,type31,type32,type33,type34,type35,type36,type37,type38,type39,type40) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            saveValuesInHelp(list, sqlInsert);
            quantityTable = list.size();
        }
    }
/*
    public void saveEntity(final ArrayList<EntityClass> list) {
        try {
            sqlInsert = "INSERT INTO "+ sourceTableName +"(value_en, type_en) VALUES(?,?);";
            final int batchSize = 500;
            List<List<EntityClass>> batchLists = Lists.partition(list, batchSize);
            for (List<EntityClass> batch : batchLists) {
                jdbcTemplate.batchUpdate(sqlInsert, new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i)
                            throws SQLException {
                        EntityClass entitySbertest = batch.get(i);
                        System.out.println(entitySbertest.getType());
                        ps.setString(1, entitySbertest.getValue());
                        ps.setString(2, entitySbertest.getType());
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
*/
    public void saveValuesInHelp(List<String> list, String sql) {
        try {
            Object[] objects = new Object[40];
            for(int i = 0; i < list.size(); i++)
                    objects[i] = list.get(i);
            for(int i = list.size(); i < 40; i++)
                objects[i] = null;

            jdbcTemplate.update(sql, objects);
        } catch (Exception e) {}
    }

    public void saveTableNameAll(EntityClass entityClass) {
        try {
            String sql = "INSERT INTO userdb.help_table_name (name_table,quantity) VALUES(?,?);";
            Object objects = entityClass;

            jdbcTemplate.update(sql, new Object[]{entityClass.getTable_name(),entityClass.getQuantity()});
        } catch (Exception e) {}
    }

    public EntityClass getTableNameAll() {
        String sql = "SELECT * FROM userdb.help_table_name ORDER BY id DESC LIMIT 1";
        EntityClass entityClass = jdbcTemplate.queryForObject(sql, new Object[]{}, EntityClass.class);
        return entityClass;
    }

    public Integer getQuantityTable() {
        return quantityTable;
    }


    public MainEntity findByIdEntity(Long id){
        try {
            sqlSelect = "select * from userdb.help_value where id = ?;";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlSelect, id);
            MainEntity obj = new MainEntity();
            for (Map<String, Object> row : rows) {
                Class<? extends MainEntity> mainEntityClass = obj.getClass();
                int count = 1;
                for (Field field : mainEntityClass.getDeclaredFields()) {
                    if(count <= 40)
                    if (field.getType() == String.class && field.getModifiers() == Modifier.PRIVATE) {
                        field.setAccessible(true);
                        String val = "value" + count;
                        field.set(obj, row.get((String) val));
                        count++;
                    }
                    else
                        break;
                }
            }
            return obj;
        } catch (Exception e){
            return new MainEntity();
        }
    }

    public MainEntity findByLastIdEntity(){
        try {
            sqlSelect = "SELECT * \n" +
                    "FROM userdb.help_value\n" +
                    "ORDER BY id DESC\n" +
                    "LIMIT 1";
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlSelect);
            MainEntity obj = new MainEntity();
            for (Map<String, Object> row : rows) {
                Class<? extends MainEntity> mainEntityClass = obj.getClass();
                int count = 1;
                for (Field field : mainEntityClass.getDeclaredFields()) {
                    if(count <= 40)
                        if (field.getType() == String.class && field.getModifiers() == Modifier.PRIVATE) {
                            field.setAccessible(true);
                            String val = "value" + count;
                            field.set(obj, row.get((String) val));
                            count++;
                        }
                        else
                            break;
                }
            }
            return obj;
        } catch (Exception e){
            return new MainEntity();
        }
    }

   /* public void createTable(String tableName, Long id){
        List<String> strings = new ArrayList<>();
        List<EntityClass> list = findByIdEntity(id);
        for(int i = 0; i < list.size(); i++) {
            String typeStr = list.get(i).getValue() + " " + list.get(i).getType()+",";
            if(i == list.size() - 1) {
                typeStr = typeStr.substring(0,typeStr.length()-2);
            }
            strings.add(typeStr);
        }

        String finalString = "";

        for(int i = 0; i < strings.size(); i++) {
            finalString += strings.get(i) + " ";
        }

        System.out.println(finalString);

        jdbcTemplate.execute("CREATE TABLE testdb1.T_REPORT_SBERTEST_USER_PSYCHOTEST (" +
                " " + finalString +" " +
                ");");
        jdbcTemplate.execute(tableName);
    }
*/
}
