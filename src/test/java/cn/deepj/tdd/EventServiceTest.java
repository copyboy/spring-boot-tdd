package cn.deepj.tdd;

import cn.deepj.tdd.domain.Event;
import cn.deepj.tdd.domain.Events;
import cn.deepj.tdd.mapper.EventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * 自顶向下测试
 *  1. 从集成测试开始 failing-test(未实现功能返回失败的测试),并制定在测试结束时我们所期望实现功能结果
 *      Happy path test, 确保应用程序中的所有东西都运行良好,使用已知输入,并产生预期输出
 *  2. 控制器测试专注于控制器层面的测试,如找不到该事件,或其他极端情况
 *     使用mockMvc 单独测试controller，无需启动springboot
 *  3. 服务层测试 仅测试服务层切面,mock数据层
 *
 * @author qingdong.zhang
 * @version 1.0
 * @since 2021-01-27 18:36
 */
@ExtendWith(SpringExtension.class)
public class EventServiceTest {

    @Mock
    private EventMapper eventMapper;

    private EventService eventService;

    @BeforeEach
    public void setUp() {
        eventService = new EventService(eventMapper);
    }

    @Test
    public void getEvent_shouldReturnEvents() throws Exception {

        // given
        Event event = new Event("武汉出现不明肺炎", "2019-12-01", "武汉华南海鲜市场出现不明肺炎", "2019-12-01", "IMAGE");
        given(eventMapper.selectList(any()))
                .willReturn(Collections.singletonList(event));
        // when
        Events events = eventService.getEventDetails("input");

        // then
        assertEquals("新冠记忆",events.getMainTitle());
        assertEquals("2019-至今",events.getTimeTitle());


    }

    @Test
    public void getEvent_notFound() throws Exception {
        // given
        given(eventMapper.selectList(any()))
                .willReturn(null);
        // when

        // then
        assertThrows(EventNotFoundException.class,
                ()->eventService.getEventDetails("input"));

        // 使用 junit5 assertThrows 取代 junit4 @Test(expected=EventNotFoundException.class)
        // 服务层每个返回,无论正常还是异常返回结果,都应该有对应的测试案例

    }
}