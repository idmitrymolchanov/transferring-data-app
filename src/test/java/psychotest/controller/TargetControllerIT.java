package psychotest.controller;
import com.google.common.collect.ImmutableList;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import psychotest.entity.EntitySbertest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TargetControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void saveSourceData() throws URISyntaxException {
        EntitySbertest entitySbertest1 = EntitySbertest
                .builder()
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

        List<EntitySbertest> list = new ArrayList<>();
        list.add(entitySbertest1);

        restTemplate.postForObject("http://localhost:" + port + "/target", list, ResponseEntity.class);

        ResponseEntity<List<EntitySbertest>> responseEntity =
                restTemplate.exchange("http://localhost:" + port + "/target/12455555", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<EntitySbertest>>() {
                        });

        List<EntitySbertest> actualList = responseEntity.getBody();
        List<String> actualId = actualList.stream()
                .map(entitySbertest -> entitySbertest.getExtidBckgr())
                .collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
        assertThat(actualId, Matchers.containsInAnyOrder("21212121"));
    }
}