package cn.deepj.tdd;

import cn.deepj.tdd.domain.Event;
import cn.deepj.tdd.mapper.EventMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *
 * 自顶向下测试
 *  1. 从集成测试开始 failing-test(未实现功能返回失败的测试),并制定在测试结束时我们所期望实现功能结果
 *      Happy path test, 确保应用程序中的所有东西都运行良好,使用已知输入,并产生预期输出
 *  2. 控制器测试专注于控制器层面的测试,如找不到该事件,或其他极端情况
 *     使用mockMvc 单独测试controller，无需启动springboot
 *  3. 服务层测试 仅测试服务层切面,mock数据层
 *  4. 数据库层面测试 仅测试数据库方法切面
 *
 * @author qingdong.zhang
 * @version 1.0
 * @since 2021-01-27 19:25
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootTddApplication.class)
@Transactional
class EventMapperTest {

    @Autowired
    private EventMapper eventMapper;

//    @BeforeEach
//    void setUp() {
//        Event event = new Event("武汉出现不明肺炎", "2019-12-01",
//                "武汉华南海鲜市场出现不明肺炎", "2019-12-01", "IMAGE");
//        event.setId(9999);
//        eventMapper.insert(event);
//    }

    @Test
    public void getEvents_returnEventDetails() throws Exception {

        Event event = new Event("武汉出现不明肺炎", "2019-12-01",
                "武汉华南海鲜市场出现不明肺炎", "2019-12-01", "IMAGE");
                event.setId(9999);
        eventMapper.insert(event);

        List<Event> events = eventMapper.selectList(null);

        assertNotEquals(0, events.size());

        events.forEach(System.out::println);

        // 怎么做到不依赖数据库数据
        // 1. 使用 BeforeEach,AfterEach 做测试数据的插入与删除
        // 2. 使用@Transactional 注解,支持数据回滚，避免测试数据污染环境
    }

//    @AfterEach
//    void clean() {
//        eventMapper.deleteById(9999);
//    }
}