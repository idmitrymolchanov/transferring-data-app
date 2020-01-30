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
public class SourceControllerIT {
    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getSourceData() {
        ResponseEntity<List<EntitySbertest>> responseEntity =
                restTemplate.exchange("http://localhost:8090/sbertest2", HttpMethod.GET, null,
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
        EntitySbertest entitySbertest0 = EntitySbertest
                .builder()
                .id(Long.parseLong("1243593"))
                .extidBckgr("21")
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

        List<EntitySbertest> list = new ArrayList<>();
        list.add(entitySbertest0);

        restTemplate.postForObject("http://localhost:8090/sbertest2", list, ResponseEntity.class);


        ResponseEntity<List<EntitySbertest>> responseEntity =
                restTemplate.exchange("http://localhost:8090/sbertest2", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<EntitySbertest>>() {
                        });

        List<EntitySbertest> actualList = responseEntity.getBody();
        assertThat(actualList.size(), Matchers.is(5));
        List<String> actualId = actualList.stream()
                .map(entitySbertest -> entitySbertest.getExtidBckgr())
                .collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
        assertThat(actualId, Matchers.containsInAnyOrder("2168779357", "2168779358", "2168779359", "2168779360", "21"));
    }
}