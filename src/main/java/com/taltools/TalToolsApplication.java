package com.taltools;

import com.taltools.utils.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

/**
 * @author czy-20210423
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class TalToolsApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TalToolsApplication.class, args);
        SpringContextUtil.setApplicationContext(context);
    }
}
