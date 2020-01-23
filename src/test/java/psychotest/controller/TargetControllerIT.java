package psychotest.controller;
import com.google.common.collect.ImmutableList;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import psychotest.SpringBootApp;
import psychotest.entity.EntitySbertest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

@Profile("local")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestExecutionListeners({DirtiesContextTestExecutionListener.class})
public class TargetControllerIT {
    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getSourceData() {
        ResponseEntity<List<EntitySbertest>> responseEntity =
                restTemplate.exchange("http://localhost:8090/sbertest1", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<EntitySbertest>>() {
                        });
        List<EntitySbertest> actualList = responseEntity.getBody();
        assertThat(actualList.size(), Matchers.is(4));
        List<String> actualId = actualList.stream()
                .map(entitySbertest -> entitySbertest.getExtidBckgr())
                .collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
        assertThat(actualId, Matchers.containsInAnyOrder("2168779357", "2168779358", "2168779359", "2168779360"));
    }

    @Test
    public void saveSourceData() throws URISyntaxException {

        EntitySbertest entitySbertest0 = new EntitySbertest(Long.parseLong("1243593"), "21", "154708", "1497935", "2019-01-25 08:21:32.0000000", "personal-char", "MongoValue", "0 - 10", "2019-07-25 00:00:00.0000000", "value", "2019-01-21 00:00:00.0000000", "27f18987-bf6d-4d08-8aec-d6f145cafOff", "value", 1.0);
        EntitySbertest entitySbertest1 = new EntitySbertest(Long.parseLong("1243594"), "216", "154708", "1497935", "2019-01-25 08:21:32.0000000", "personal-char", "MongoValue", "0 - 10", "2019-07-25 00:00:00.0000000", "value", "2019-01-21 00:00:00.0000000", "27f18987-bf6d-4d08-8aec-d6f145cafOff", "value", 1.0);
        EntitySbertest entitySbertest2 = new EntitySbertest(Long.parseLong("1243505"), "2", "154708", "1497935", "2019-01-25 08:21:32.0000000", "personal-char", "MongoValue", "0 - 10", "2019-07-25 00:00:00.0000000", "value", "2019-01-21 00:00:00.0000000", "27f18987-bf6d-4d08-8aec-d6f145cafOff", "value", 1.0);
        List<EntitySbertest> list = new ArrayList<>();

        list.add(entitySbertest0);
        list.add(entitySbertest1);
        list.add(entitySbertest2);

        restTemplate.postForObject("http://localhost:8090/sbertest1", list, ResponseEntity.class);


        ResponseEntity<List<EntitySbertest>> responseEntity =
                restTemplate.exchange("http://localhost:8090/sbertest1", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<EntitySbertest>>() {
                        });

        List<EntitySbertest> actualList = responseEntity.getBody();
        assertThat(actualList.size(), Matchers.is(7));
        List<String> actualId = actualList.stream()
                .map(entitySbertest -> entitySbertest.getExtidBckgr())
                .collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
        assertThat(actualId, Matchers.containsInAnyOrder("2168779357", "2168779358", "2168779359", "2168779360", "21", "216", "2"));
    }
}