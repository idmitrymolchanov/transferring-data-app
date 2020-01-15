package psychotest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import psychotest.entity.EntitySbertest;
import psychotest.repository.TargetRepository;

import java.util.Arrays;
import java.util.List;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
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
    public void testGetTargetData()
            throws Exception {

        EntitySbertest entitySbertest = new EntitySbertest(Long.parseLong("1243551"), "", "", "", "","","","","","","","","",7.3);

        List<EntitySbertest> allEmployees = Arrays.asList(entitySbertest);

        given(targetRepository.getDataCall()).willReturn(allEmployees);

        mvc.perform(get("/sbertest1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(14)))
                .andExpect((ResultMatcher) jsonPath("$[0].id", is(entitySbertest.getId())));
    }

}