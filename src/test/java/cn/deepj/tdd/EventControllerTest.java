package cn.deepj.tdd;

import cn.deepj.tdd.domain.Events;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 自顶向下测试
 *  1. 从集成测试开始 failing-test(未实现功能返回失败的测试),并制定在测试结束时我们所期望实现功能结果
 *      Happy path test, 确保应用程序中的所有东西都运行良好,使用已知输入,并产生预期输出
 *  2. 控制器测试专注于控制器层面的测试,如找不到该事件,或其他极端情况
 *     使用mockMvc 单独测试controller，无需启动springboot
 *
 * @author qingdong.zhang
 * @version 1.0
 * @since 2021-01-27 10:45
 */
@WebMvcTest(EventController.class)
//@DisplayName("测试事件控制器")
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Test
    public void getEvent_shouldReturnEvents() throws Exception {

        // given
        given(eventService.getEventDetails(anyString()))
                .willReturn(new Events("新冠记忆", "2019-至今"));
        // when

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/events"))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("mainTitle").value("新冠记忆"))
                .andExpect(jsonPath("timeTitle").value("2019-至今"))
        ;

        // 1） failing-test 404 211ms
        // 2） 趟坑之旅 eventService.getEventDetails(anyString())
        //      当EventController中 eventService.getEventDetails(null) 时 返回 No value at JSON path "mainTitle"
        //      是因为没有模拟到given的情况, 所以最好用any()， 而不是anyString()
        // 3）中文返回乱码问题的解决： @GetMapping(value = "/api/events", produces = "application/json;charset=UTF-8")
        //      produces属性可以设置返回数据的类型以及编码
        //      在请求时就设置相应编码

        // 4） 写完mapper的测试类后，此处执行失败Failed to load ApplicationContext
        //       Error creating bean with name 'eventController' defined
        //       -> Error creating bean with name 'eventMapper' defined
        //       -> Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required



    }

    @Test
    public void getEvent_notFound() throws Exception {
        given(eventService.getEventDetails(any()))
                .willThrow(new EventNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/events"))
                .andExpect(status().isNotFound());

        // 两种 异常情况处理方式
        // 1) 使用ExceptionHandler 捕获异常处理
        //    @ExceptionHandler(EventNotFoundException.class)
        //    @ResponseStatus(HttpStatus.NOT_FOUND)
        //    private void eventNotFoundHandler(EventNotFoundException ex) {}

        // 2) 在EventNotFoundException 添加 @ResponseStatus(HttpStatus.NOT_FOUND) 注解,指定返回错误码


    }



}
