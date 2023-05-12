package com.spring.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogTest {
//	private static final Logger logger = LoggerFactory.getLogger(LogTest.class);
	
	public static void main(String[] args) {
//		logger.error("error 레벨");
//		logger.warn("warn 레벨");
//		logger.info("info 레벨");
//		logger.debug("debug 레벨");
//		logger.trace("trace 레벨"); // 이거 안나오는 이유 : logback.xml 에 <root level ="debug> </root> 기 때문

		log.error("error 레벨");
		log.warn("warn 레벨");
		log.info("info 레벨");
		log.debug("debug 레벨");
		log.trace("trace 레벨"); // 이거 안나오는 이유 : logback.xml 에 <root level ="debug> </root> 기 때문
	}

}
