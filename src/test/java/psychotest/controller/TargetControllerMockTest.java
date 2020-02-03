package psychotest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import psychotest.entity.EntitySbertest;
import psychotest.service.TargetService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TargetControllerMockTest {

    private MockMvc mvc;

    @Mock
    private TargetService targetService;

    @InjectMocks
    private TargetController targetController;

    private JacksonTester<List<EntitySbertest>> jsonEntity;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(targetController)
                .build();
    }

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        // given
        List<EntitySbertest> list = new ArrayList<>();
        EntitySbertest entitySbertest = EntitySbertest
                .builder()
                .id(Long.parseLong("1243550"))
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
        list.add(entitySbertest);
        given(targetService.findById(Long.parseLong("1243550")))
                .willReturn(list);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("http://localhost:" + port + "/target/1243550")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonEntity.write(list).getJson()
        );
        verify(targetService).findById(Long.parseLong("1243550"));
    }

    @Test
    public void canRetrieveByIdWhenDoesNotExist() throws Exception {
        // given
        given(targetService.findById(Long.parseLong("1240")))
                .willReturn(null);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("http://localhost:" + port + "/target/1240")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getContentAsString()).isEmpty();
    }

    @Test
    public void canRetrieveByIdWhenDoesNotExist2() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                get("http://localhost:" + port + "/target1234")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void canCreateANewEntitySbertest() throws Exception {
        List<EntitySbertest> list = new ArrayList<>();
        MockHttpServletResponse response = mvc.perform(
                post("http://localhost:" + port + "/target")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonEntity.write(list).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        verify(targetService).saveAll(list);
    }
}