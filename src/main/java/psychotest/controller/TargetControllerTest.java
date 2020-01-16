package psychotest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import psychotest.entity.EntitySbertest;
import psychotest.repository.TargetRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TargetController.class)
public class TargetControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    public TargetRepository targetRepository;

    @Test
    public void testGetTargetData() throws Exception {

        EntitySbertest entitySbertest = new EntitySbertest(Long.parseLong("1243550"), "2168779357", "154708", "1497935", "2019-01-25 08:21:32.0000000", "personal-char", "valueNeRabotaet!", "0 - 10", "2019-01-25 00:00:00.0000000", "value", "2019-01-21 00:00:00.0000000", "27f18987-bf6d-4d08-8aec-d6f145cafOff", "value", 1.0);

        List<EntitySbertest> allEmployees = Arrays.asList(entitySbertest);

        given(targetRepository.getDataCall()).willReturn(allEmployees);

        mvc.perform(get("/sbertest1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}