package com.github.pkwenda;

import com.github.pkwenda.pojo.Product;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
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
public class Example implements PageProcessor {

	private static ConfigurableApplicationContext context;

	private static final String PRODUCT_ITEM = "#search > div.s-desktop-width-max.s-opposite-dir > div > div.s-matching-dir.sg-col-16-of-20.sg-col.sg-col-8-of-12.sg-col-12-of-16 > div > span:nth-child(4) > div.s-main-slot.s-result-list.s-search-results.sg-row > div";
	private static final String ITEM_NAME = "div:nth-child(3) > h2 > a > span";
	private static final String ITEM_PRISE = "div > a > span > span:nth-child(2) > span.a-price-whole";


	private int errorCount = 0;

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me()
			.setRetryTimes(3)
			.setSleepTime(0)
			.setTimeOut(60000 * 5);

	public static void main(String[] args) {
//		context = SpringApplication.run(BookApplication.class, args);

		spider("8T 硬盘");

	}

	public static void spider(String keyword) {
		Spider.create(new Example())
				//从你能看到的第一本书号开始   https://www.booksofun.com//update.html
				.addUrl("https://www.amazon.cn/s?k=" + keyword)
				//开启5个线程抓取
				.thread(1)
				//启动爬虫
				.run();
	}

	@Override
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		Html html = page.getHtml();
		List<Html> productItems = html.$(PRODUCT_ITEM).all().stream().map(e -> new Html(e)).collect(Collectors.toList());

		List<Product> products = productItems.stream().map(e -> {
			String name = e.$(ITEM_NAME,"text").get();
			if(StringUtils.isNotEmpty(name) && matchKeyword(name,"8t 硬盘")) {
				Product product = new Product();
				String prise = e.$(ITEM_PRISE,"text").get();
				product.setName(name);
				System.out.println(name + " " + prise);
				product.setPrise(Integer.parseInt(prise.replaceAll(",","")));
				return product;
			}
			return null;
		}).filter(e -> e != null).collect(Collectors.toList());
		System.out.println();
		try {

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// todo
	public boolean matchKeyword(String name,String keyword){
		return StringUtils.containsAny(name, keyword.split(" "));
	}

	@Override
	public Site getSite() {
		return site.setSleepTime(0);
	}
}
