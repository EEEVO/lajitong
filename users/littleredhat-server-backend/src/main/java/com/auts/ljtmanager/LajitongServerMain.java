package com.auts.ljtmanager;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.alibaba.fastjson.parser.ParserConfig;
//import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageHelper;

//import org.springframework.web.client.RestTemplate;

/**
 * 小红帽管理后台入口
 *
 * @author lb47917
 * @date 2018年12月12日 上午11:12:49
 */
@SpringBootApplication(scanBasePackages = { "com.auts.ljtmanager.**" })
@PropertySources({ @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true) })
@MapperScan("com.auts.lajitong.dao.**")
// @EnableCircuitBreaker
// @EnableDiscoveryClient
// @EnableEurekaClient
public class LajitongServerMain {
	private static Logger logger = LogManager
			.getLogger(LajitongServerMain.class);

	static {
		ParserConfig.getGlobalInstance().addAccept("com.auts.ljtmanager.model.");
	}

	// /**
	// * rest接口.
	// * @return rest模板
	// */
	// @Bean
	// @LoadBalanced
	// RestTemplate restTemplate() {
	// return new RestTemplate();
	// }

	// 配置mybatis的分页插件pageHelper
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}

	/**
	 * 项目启动方法入口.
	 *
	 * @param args
	 *            args
	 */
	public static void main(String[] args) {
		SpringApplication.run(LajitongServerMain.class, args);

		logger.info("startup success!");
	}

}
