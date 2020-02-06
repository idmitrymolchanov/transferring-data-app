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

    private static List<EntitySbertest> entitySbertestList;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById_testOnListSize_OneEntryInListReturned() {
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

        entitySbertestList = new ArrayList<>();
        entitySbertestList.add(entitySbertest);

        when(sourceRepository.findById(Long.parseLong("1245678"))).thenReturn(entitySbertestList);

        List<EntitySbertest> empList = sourceService.findById(Long.parseLong("1245678"));
        assertEquals(1, empList.size());
    }

    @Test
    public void findById_testOnIdentityValues_ListByIdReturned() {
        when(sourceRepository.findById(Long.parseLong("1245678"))).thenReturn(entitySbertestList);

        List<EntitySbertest> entitySbertestList1 = sourceService.findById(Long.parseLong("1245678"));

        assertEquals("2168779357", entitySbertestList1.get(0).getExtidBckgr());
        assertEquals("154708", entitySbertestList1.get(0).getExtidUser());
    }
}