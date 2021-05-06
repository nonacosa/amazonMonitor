package com.github.pkwenda.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wenda.zhuang
 * @Date 2021/5/6 下午8:36
 * @Description ...
 * @E-mail sis.nonacosa@gmail.com
 */
@Data
@Accessors(chain = true)
public class Product {

	private String name;

	private int prise;

	private String date;

}
