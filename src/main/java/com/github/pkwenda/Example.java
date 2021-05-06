package com.github.pkwenda;

import com.github.pkwenda.amazon.AmazonApi;
import com.github.pkwenda.pojo.Product;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * @author wenda.zhuang
 * @Date 2021/5/6 下午8:10
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
public class Example  {



	public static void main(String[] args) {
//		context = SpringApplication.run(BookApplication.class, args);

//		spider("8T 硬盘");
		API api = new AmazonApi();
		api.fetch();

	}

}
