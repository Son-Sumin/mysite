package com.bitacademy.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.bitacademy.mysite.config.app.DBConfig;
import com.bitacademy.mysite.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.bitacademy.mysite.service", "com.bitacademy.mysite.repository", "com.bitacademy.mysite.aspect"})
@Import({DBConfig.class, MyBatisConfig.class})  // 한번에 쓰지 말고 설정들을 클래스로 분리하여 관리
public class AppConfig {
}
