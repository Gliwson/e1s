package io.github.e1s.components.views;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class ViewsServiceImplTest {

    @InjectMocks
    private ViewsServiceImpl viewsService;

    @Mock
    private ViewsRepository viewsRepository;

    @Test
    void shouldExecuteIncreaseViews() {
        //given
        doNothing().when(viewsRepository).findByIdAndIncreaseViewsByOne(anyLong());
        //when
        viewsService.increaseViews(anyLong());
        //then
        then(viewsRepository).should().findByIdAndIncreaseViewsByOne(any());
    }

    @Test
    void ifIdIsNullShouldDoNothing() {
        //given
        //when
        viewsService.increaseViews(null);
        //then
        then(viewsRepository).should(never()).findByIdAndIncreaseViewsByOne(any());
    }

}
