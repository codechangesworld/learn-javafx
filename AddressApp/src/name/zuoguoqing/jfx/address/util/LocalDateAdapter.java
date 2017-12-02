/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.address.util
 * @file LocalDateAdapter.java
 * @author zuoguoqing
 * @date 2017年4月9日
 * @version 
 */
package name.zuoguoqing.jfx.address.util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author zuoguoqing
 *
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return DateUtil.parse(v);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}
	
}
