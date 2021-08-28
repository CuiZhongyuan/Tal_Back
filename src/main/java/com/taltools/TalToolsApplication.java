package com.taltools;

import com.taltools.utils.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan({"com.taltools.dao","com.taltools.entity"})
public class TalToolsApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TalToolsApplication.class, args);
		SpringContextUtil.setApplicationContext(context);
	}
}
