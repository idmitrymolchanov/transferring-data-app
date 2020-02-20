package psychotest.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import psychotest.entity.EntitySbertest;
import psychotest.repository.SourceRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
public class SourceServiceTest {

    @Mock
    SourceRepository sourceRepository;

    @InjectMocks
    SourceService sourceService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllSinceLastTargetDate_testOnIdentityValues_ListReturned() {
        EntitySbertest entitySbertest = EntitySbertest
                .builder()
                .id(Long.parseLong("1245678"))
                .extidBckgr("2168779357")
                .extidUser("154708")
                .tabnum("1497935")
                .changeDate("2019-01-25 08:21:32.0000000")
                .extidProgram("personal-char")
                .nameProgram("value")
                .scale("0 - 10")
                .endDateScore("2019-03-25 00:00:00.0000000")
                .nameScore("value")
                .startDateScore("2019-01-21 00:00:00.0000000")
                .extidTest("27f18987-bf6d-4d08-8aec-d6f145cafOff")
                .nameTest("value")
                .resultScoreNum(1.0)
                .build();

        List list = new ArrayList();
        list.add(entitySbertest);
        when(sourceRepository.findAllSinceLastTargetDate(LocalDate.parse("2018-11-10"))).thenReturn(list);

        List<EntitySbertest> entitySbertestList1 = sourceService.findAllSinceLastTargetDate(LocalDate.parse("2018-11-10"));

        assertEquals("2168779357", entitySbertestList1.get(0).getExtidBckgr());
        assertEquals("154708", entitySbertestList1.get(0).getExtidUser());
        assertEquals("1497935", entitySbertestList1.get(0).getTabnum());
        assertEquals("2019-01-25 08:21:32.0000000", entitySbertestList1.get(0).getChangeDate());
        assertEquals("personal-char", entitySbertestList1.get(0).getExtidProgram());
        assertEquals("value", entitySbertestList1.get(0).getNameProgram());
        assertEquals("0 - 10", entitySbertestList1.get(0).getScale());
        assertEquals("2019-03-25 00:00:00.0000000", entitySbertestList1.get(0).getEndDateScore());
        assertEquals("value", entitySbertestList1.get(0).getNameScore());
        assertEquals("2019-01-21 00:00:00.0000000", entitySbertestList1.get(0).getStartDateScore());
        assertEquals("27f18987-bf6d-4d08-8aec-d6f145cafOff", entitySbertestList1.get(0).getExtidTest());
        assertEquals("value", entitySbertestList1.get(0).getNameTest());
        assertEquals(Double.valueOf(1.0), entitySbertestList1.get(0).getResultScoreNum());
    }
}