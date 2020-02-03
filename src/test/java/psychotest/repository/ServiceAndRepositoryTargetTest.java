package psychotest.repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import psychotest.entity.EntitySbertest;
import psychotest.service.TargetService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
public class ServiceAndRepositoryTargetTest {
    @InjectMocks
    TargetService targetService;

    @Mock
    TargetRepository targetRepository;

    private static List<EntitySbertest> entitySbertestList;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        EntitySbertest entitySbertest = EntitySbertest.builder()
                .id(Long.parseLong("12455555"))
                .extidBckgr("21212121")
                .extidUser("1111")
                .tabnum("1111")
                .changeDate("1111")
                .extidProgram("1111")
                .nameProgram("1111")
                .scale("1111")
                .endDateScore("1111")
                .nameScore("1111")
                .startDateScore("1111")
                .extidTest("1111")
                .nameTest("1111")
                .resultScoreNum(23.0)
                .build();

        entitySbertestList = new ArrayList<>();
        entitySbertestList.add(entitySbertest);
    }

    @Test
    public void findByIdTestOnSize()
    {
        when(targetRepository.findById(Long.parseLong("12455555"))).thenReturn(entitySbertestList);

        List<EntitySbertest> empList = targetService.findById(Long.parseLong("12455555"));
        assertEquals(1, empList.size());
    }

    @Test
    public void findBuIdTestOnValues()
    {
        when(targetRepository.findById(Long.parseLong("12455555"))).thenReturn(entitySbertestList);

        List<EntitySbertest> entitySbertestList1 = targetService.findById(Long.parseLong("12455555"));

        assertEquals("21212121", entitySbertestList1.get(0).getExtidBckgr());
        assertEquals("1111", entitySbertestList1.get(0).getExtidUser());
    }
}
