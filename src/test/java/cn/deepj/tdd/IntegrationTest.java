package cn.deepj.tdd;

import cn.deepj.tdd.domain.Events;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * 自顶向下测试
 *  1. 从集成测试开始 failing-test(未实现功能返回失败的测试),并制定在测试结束时我们所期望实现功能结果
 *    Happy path test, 确保应用程序中的所有东西都运行良好,使用已知输入,并产生预期输出
 *  2.
 *
 * @author qingdong.zhang
 * @version 1.0
 * @since 2021-01-27 9:59
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql({"/schema.sql","/data.sql"})
@Transactional
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getEvents_returnEventDetails() {

        // arrange

        // act
        ResponseEntity<Events> response = restTemplate
                .getForEntity("/api/events", Events.class);
        printResponse(response);
        // assert
        // 请求状态
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // 内容样例信息
//        {
//            "mainTitle":"新冠记忆",
//            "timeTitle":"2019-至今",
//            "subTitle":"我们正在见证历史，我们正在创造历史",
//            "dataList": [
//            {
//                "title":"武汉出现不明肺炎",
//                "time":"2019-12-01",
//                "desc":"武汉华南海鲜市场出现不明肺炎",
//                "url":"2019-12-01",
//                "type":"IMAGE/VIDEO"
//            }
//            ]
//        }
        assertEquals("新冠记忆", response.getBody().getMainTitle());
        assertEquals("2019-至今", response.getBody().getTimeTitle());

        // failing-test 404 395ms

    }

    protected void printResponse(ResponseEntity<?> response) {
        System.out.println("Status: " + response.getStatusCode());
        System.out.println("Headers: " +  response.getHeaders());
        System.out.println("Body: " + response.getBody());
    }
}
