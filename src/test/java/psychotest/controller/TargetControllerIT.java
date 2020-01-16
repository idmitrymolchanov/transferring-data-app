package psychotest.controller;

import com.google.common.collect.ImmutableList;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import psychotest.SpringBootApp;
import psychotest.entity.EntitySbertest;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootApp.class)
@WebAppConfiguration
public class TargetControllerIT {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void before(){
        mongoTemplate.dropCollection(EntitySbertest.class);
        mongoTemplate.save(new EntitySbertest(Long.parseLong("1243550"), "2168779357", "154708", "1497935", "2019-01-25 08:21:32.0000000", "personal-char", "MongoValue", "0 - 10", "2019-07-25 00:00:00.0000000", "value", "2019-01-21 00:00:00.0000000", "27f18987-bf6d-4d08-8aec-d6f145cafOff", "value", 1.0));
        mongoTemplate.save(new EntitySbertest(Long.parseLong("1243551"), "2168779358", "154708", "1497935", "2019-01-25 08:21:32.0000000", "personal-char", "value", "0 - 10", "2019-08-25 00:00:00.0000000", "value", "2019-01-21 00:00:00.0000000", "27f18987-bf6d-4d08-8aec-d6f145cafOff", "value", 1.0));
        mongoTemplate.save(new EntitySbertest(Long.parseLong("1243552"), "2168779359", "154708", "1497935", "2019-01-25 08:21:32.0000000", "personal-char", "value", "0 - 10", "2019-09-25 00:00:00.0000000", "value", "2019-01-21 00:00:00.0000000", "27f18987-bf6d-4d08-8aec-d6f145cafOff", "value", 1.0));
        mongoTemplate.save(new EntitySbertest(Long.parseLong("1243553"), "2168779360", "154708", "1497935", "2019-01-25 08:21:32.0000000", "personal-char", "value", "0 - 10", "2019-10-25 00:00:00.0000000", "value", "2019-01-21 00:00:00.0000000", "27f18987-bf6d-4d08-8aec-d6f145cafOff", "value", 1.0));
    }

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
}