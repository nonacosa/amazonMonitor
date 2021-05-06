package com.github.pkwenda;


import org.springframework.context.annotation.Bean;

import io.prometheus.client.exporter.PushGateway;
import lombok.SneakyThrows;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wenda.zhuang
 * @Date 2020/11/9 7:23 下午
 * @Description pto 交易对
 * @E-mail sis.nonacosa@gmail.com
 */
public class MonitorPTO {

	/**
	 * push网关
	 *
	 * @return
	 */
	@Bean
	public PushGateway getPushGateway() {
		return new PushGateway("localhost:9091");
	}




	@SneakyThrows
	public static void main(String[] args)   {
		try {
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MonitorPTO.class);

			while (true) {

				Thread.sleep(5000);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}



}
