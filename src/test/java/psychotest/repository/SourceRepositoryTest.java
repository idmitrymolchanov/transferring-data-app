package psychotest.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import psychotest.entity.EntitySbertest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SourceRepositoryTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SourceRepository userRepository;

    @Test
    public void registrationWorksThroughAllLayers() throws Exception {
        EntitySbertest user = new EntitySbertest(Long.parseLong("124350"), "2168779357", "154708", "1497935", "2019-01-25 08:21:32.0000000", "personal-char", "MongoValue", "0 - 10", "2019-07-25 00:00:00.0000000", "value", "2019-01-21 00:00:00.0000000", "27f18987-bf6d-4d08-8aec-d6f145cafOff", "value", 1.0);

        mockMvc.perform(post("/sbertest2")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        List<EntitySbertest> userEntity = userRepository.getDataCall();
        assertThat(userEntity.get(0).getExtidBckgr()).isEqualTo("2168779357");
    }

}