package psychotest.entity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EntitySbertestTest {

    private EntitySbertest entitySbertest = new EntitySbertest(Long.parseLong("124350"), "2168779357", "154708", "1497935", "2019-01-25 08:21:32.0000000", "personal-char", "MongoValue", "0 - 10", "2019-07-25 00:00:00.0000000", "value", "2019-01-21 00:00:00.0000000", "27f18987-bf6d-4d08-8aec-d6f145cafOff", "value", 1.0);

    @Test
    public void getId() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getId(), is(Long.parseLong("124350")));
    }

    @Test
    public void getExtidBckgr() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getExtidBckgr(), is("2168779357"));
    }

    @Test
    public void getExtidUser() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getExtidUser(), is("154708"));
    }

    @Test
    public void getTabnum() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getTabnum(), is("1497935"));
    }

    @Test
    public void getChangeDate() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getChangeDate(), is("2019-01-25 08:21:32.0000000"));
    }

    @Test
    public void getExtidProgram() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getExtidProgram(), is("personal-char"));
    }

    @Test
    public void getNameProgram() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getNameProgram(), is("MongoValue"));
    }

    @Test
    public void getScale() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getScale(), is("0 - 10"));
    }

    @Test
    public void getEndDateScore() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getEndDateScore(), is("2019-07-25 00:00:00.0000000"));
    }

    @Test
    public void getNameScore() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getNameScore(), is("value"));
    }

    @Test
    public void getStartDateScore() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getStartDateScore(), is("2019-01-21 00:00:00.0000000"));
    }

    @Test
    public void getExtidTest() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getExtidTest(), is("27f18987-bf6d-4d08-8aec-d6f145cafOff"));
    }

    @Test
    public void getNameTest() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getNameTest(), is("value"));
    }

    @Test
    public void getResultScoreNum() {
        EntitySbertest entitySbertest1 = entitySbertest;
        assertThat(entitySbertest1.getResultScoreNum(), is(1.0));
    }
}