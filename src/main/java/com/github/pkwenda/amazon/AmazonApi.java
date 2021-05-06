package com.github.pkwenda.amazon;

import com.github.pkwenda.API;
import com.github.pkwenda.Example;
import com.github.pkwenda.pojo.Product;
import com.github.pkwenda.util.Tool;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Html;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wenda.zhuang
 * @Date 2021/5/6 下午7:57
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
public class AmazonApi implements API {

	private static final String SEARCH_URL = "https://www.amazon.cn/s?k=";

	private static final String PRODUCT_ITEM = "#search > div.s-desktop-width-max.s-opposite-dir > div > div.s-matching-dir.sg-col-16-of-20.sg-col.sg-col-8-of-12.sg-col-12-of-16 > div > span:nth-child(4) > div.s-main-slot.s-result-list.s-search-results.sg-row > div";
	private static final String ITEM_NAME = "div:nth-child(3) > h2 > a > span";
	private static final String ITEM_PRISE = "div > a > span > span:nth-child(2) > span.a-price-whole";


	@Override
	public Object fetch() {
		spider("8T 硬盘");
		return null;
	}

	public static void main(String[] args) {
		spider("8T 硬盘");
	}

	public static void spider(String keyword) {
		Spider.create(new AmazonApi())
				.addUrl(SEARCH_URL + keyword)
				.thread(1)
				//启动爬虫
				.run();
	}

	@Override
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		//todo:http://webmagic.io/docs/zh/posts/ch6-custom-componenet/scheduler.html 自定义 scheduler 不要去重
		//https://github.com/code4craft/webmagic/issues/311
		page.addTargetRequest(page.getUrl().toString() );
		Html html = page.getHtml();
		List<Html> productItems = html.$(PRODUCT_ITEM).all().stream().map(e -> new Html(e)).collect(Collectors.toList());

		List<Product> products = productItems.stream().map(e -> {
			String name = e.$(ITEM_NAME,"text").get();
			if(StringUtils.isNotEmpty(name) && Tool.matchKeyword(name,"8t 硬盘")) {
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



}
