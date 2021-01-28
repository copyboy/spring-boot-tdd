package cn.deepj.tdd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.deepj.tdd.mapper")
public class SpringBootTddApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTddApplication.class, args);
    }

}
