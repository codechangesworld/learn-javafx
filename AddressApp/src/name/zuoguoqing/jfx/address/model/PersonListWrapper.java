/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.address.model
 * @file PersonListWrapper.java
 * @author zuoguoqing
 * @date 2017年4月9日
 * @version 
 */
package name.zuoguoqing.jfx.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zuoguoqing
 *
 */
@XmlRootElement(name="persons")
public class PersonListWrapper {
	private List<Person> persons;

	@XmlElement(name="person")
	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	
}
