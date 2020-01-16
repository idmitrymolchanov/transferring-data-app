package psychotest.controller;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import psychotest.entity.EntitySbertest;
import psychotest.repository.SourceRepository;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SourceControllerTest {
    @Mock
    private SourceRepository sourceRepository;
    @InjectMocks
    SourceController sourceController;

    @Test
    public void getSourceData() {
        when(sourceRepository.getDataCall()).thenReturn(ImmutableList.of());
        List<EntitySbertest> list = sourceController.getSourceData();
        verify(sourceRepository).getDataCall();
    }
}