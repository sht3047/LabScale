package com.springlab.jdbctest.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/* spring Configuration 선언 */
@Configuration
/* board 패키지 스캔 */
@ComponentScan(basePackages = {"com/springlab/jdbctest/board"})
public class AppContextConfig {

}

