package psychotest.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import psychotest.entity.EntitySbertest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TargetRepositoryTest {

    @Autowired
    private TargetRepository targetRepository;

    @MockBean
    private TargetRepository targetRepositoryMock;

    @Test
    public void testRetrieveStudentWithMockRepository() throws Exception {
        List<EntitySbertest> list = new ArrayList<>();
        list.add(new EntitySbertest(Long.parseLong("124350"), "2168779357", "154708", "1497935", "2019-01-25 08:21:32.0000000", "personal-char", "MongoValue", "0 - 10", "2019-07-25 00:00:00.0000000", "value", "2019-01-21 00:00:00.0000000", "27f18987-bf6d-4d08-8aec-d6f145cafOff", "value", 1.0));
        list.add(new EntitySbertest(Long.parseLong("124351"), "2168779358", "154708", "1497935", "2019-01-25 08:21:32.0000000", "personal-char", "MongoValue", "0 - 10", "2019-07-25 00:00:00.0000000", "value", "2019-01-21 00:00:00.0000000", "27f18987-bf6d-4d08-8aec-d6f145cafOff", "value", 1.0));
        when(targetRepositoryMock.getDataCall()).thenReturn(list);
        assertTrue(targetRepository.getDataCall().get(0).getExtidBckgr().contains("2168779357"));
        assertTrue(targetRepository.getDataCall().get(1).getExtidBckgr().contains("2168779358"));
    }
}