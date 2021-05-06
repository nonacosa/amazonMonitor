package com.github.pkwenda;

import com.github.pkwenda.util.Setting;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author wenda.zhuang
 * @Date 2021/5/6 下午7:42
 * @Description 通用接口
 * @E-mail sis.nonacosa@gmail.com
 */
public interface API extends PageProcessor {

	Object fetch();

	@Override
	default Site getSite() {
		return Setting.site.setSleepTime(0);
	}
}
