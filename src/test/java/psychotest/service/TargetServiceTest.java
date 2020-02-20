package psychotest.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import psychotest.repository.TargetRepository;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
public class TargetServiceTest {

    @Mock
    TargetRepository targetRepository;

    @InjectMocks
    TargetService targetService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getLastDate_testOnIdentityValues_ListReturned() {
        LocalDate localDate = LocalDate.parse("2018-11-10");
        when(targetRepository.getLastDate()).thenReturn(localDate);

        LocalDate result = targetService.getLastDate();
        assertEquals(localDate, result);
    }
}