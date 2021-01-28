package cn.deepj.tdd;

import cn.deepj.tdd.domain.Event;
import cn.deepj.tdd.mapper.EventMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO 求你写点注释吧
 *
 * @author qingdong.zhang
 * @version 1.0
 * @since 2021-01-27 19:25
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootTddApplication.class)
class EventMapperTest {

    @Autowired
    private EventMapper eventMapper;

    @BeforeEach
    void setUp() {
        Event event = new Event("武汉出现不明肺炎", "2019-12-01",
                "武汉华南海鲜市场出现不明肺炎", "2019-12-01", "IMAGE");
        event.setId(9999);
        eventMapper.insert(event);
    }

    @Test
    public void getEvents_returnEventDetails() throws Exception {

        List<Event> events = eventMapper.selectList(null);

        assertNotEquals(0, events.size());

        events.forEach(System.out::println);

        // 怎么做到不依赖数据库数据
        // 1. 使用 BeforeEach,AfterEach 做测试数据的插入与删除
        // 2.
    }

    @AfterEach
    void clean() {
        eventMapper.deleteById(9999);
    }
}