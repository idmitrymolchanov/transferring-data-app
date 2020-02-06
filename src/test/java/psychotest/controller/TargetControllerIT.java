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
    public void saveAll_testSaveAndGetSameEntry_ListByIdReturned() {
        EntitySbertest entitySbertest1 = EntitySbertest
                .builder()
                .id(Long.valueOf(12455555))
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
        assertThat(actualId, Matchers.containsInAnyOrder("2168779357"));
    }
}