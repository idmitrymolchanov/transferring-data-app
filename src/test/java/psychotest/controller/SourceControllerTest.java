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
import psychotest.service.SourceService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SourceControllerTest {

    private MockMvc mvc;

    @Mock
    private SourceService sourceService;

    @InjectMocks
    private SourceController sourceController;

    private JacksonTester<List<EntitySbertest>> jsonEntity;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(sourceController)
                .build();
    }

    @Test
    public void findById_canRetrieveByIdWhenExists_ListByIdReturned() throws Exception {
        EntitySbertest entitySbertest = EntitySbertest
                .builder()
                .id(Long.valueOf(1243550))
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
        list.add(entitySbertest);

        given(sourceService.findById(Long.valueOf(1243550)))
                .willReturn(list);

        MockHttpServletResponse response = mvc.perform(
                get("http://localhost:" + port + "/source/1243550")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonEntity.write(list).getJson()
        );
        verify(sourceService).findById(Long.valueOf(1243550));
    }

    @Test
    public void findById_canRetrieveByIdWhenDoesNotExist_emptyListReturned() throws Exception {
        given(sourceService.findById(Long.valueOf(1240)))
                .willReturn(null);

        MockHttpServletResponse response = mvc.perform(
                get("http://localhost:" + port + "/source/1240")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).isEmpty();
    }

    @Test
    public void findById_tryToFindByIdWithIncorrectMapping_httpStatusNotFoundReturned() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                get("http://localhost:" + port + "/source1234")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void saveAll_canCreateANewEntry_httpStatusCreatedReturned() throws Exception {
        List<EntitySbertest> list = new ArrayList<>();
        MockHttpServletResponse response = mvc.perform(
                post("http://localhost:" + port + "/source")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonEntity.write(list).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        verify(sourceService).save(list);
    }

}