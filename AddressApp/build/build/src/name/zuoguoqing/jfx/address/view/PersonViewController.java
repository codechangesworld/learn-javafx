/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.address.view
 * @file PersonViewController.java
 * @author zuoguoqing
 * @date 2017年4月8日
 * @version 
 */
package name.zuoguoqing.jfx.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import name.zuoguoqing.jfx.address.MainApp;
import name.zuoguoqing.jfx.address.model.Person;

/**
 * @author zuoguoqing
 *
 */
public class PersonViewController {
	@FXML
	TableView<Person> personTableValue;
	@FXML
	TableColumn<Person, String> firstNameColumnValue;
	@FXML
	TableColumn<Person, String> lastNameColumnValue;
	
	@FXML
	Label firstNameValueLabelValue;
	@FXML
	Label lastNameValueLabelValue;
	@FXML
	Label streetValueLabelValue;
	@FXML
	Label cityValueLabelValue;
	@FXML
	Label postalCodeValueLabelValue;
	@FXML
	Label birthdayValueLabelValue;
	
	@FXML
	Button newButton;
	@FXML
	Button editButton;
	@FXML
	Button deleteButton;
	
	private MainApp mainApp;
	
	/**
	 * Constructor
	 */
	public PersonViewController() {
		
	}
	
	/**
	 * 
	 */
	@FXML
	private void initialize() {
		firstNameColumnValue.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumnValue.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		personTableValue.setItems(this.mainApp.getPersonData());
	}
	
	
}
