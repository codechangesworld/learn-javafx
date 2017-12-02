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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import name.zuoguoqing.jfx.address.MainApp;
import name.zuoguoqing.jfx.address.model.Person;
import name.zuoguoqing.jfx.address.util.DateUtil;

/**
 * @author zuoguoqing
 *
 */
public class PersonViewController {
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label birthdayLabel;

	@FXML
	private Button newButton;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;

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
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		
		showPersonDetials(null);

		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetials(newValue));
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		personTable.setItems(this.mainApp.getPersonData());
	}

	/**
	 * 
	 * @param person
	 */
	public void showPersonDetials(Person person) {
		if (person != null) {
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			cityLabel.setText(person.getCity());
			postalCodeLabel.setText(person.getPostalCode().toString());
			birthdayLabel.setText(DateUtil.format(person.getBirthday()));
		} else {
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			cityLabel.setText("");
			postalCodeLabel.setText("");
			birthdayLabel.setText("");
		}
	}
	
	@FXML
	private void handleDeletePerson() {
		int selectIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectIndex >=0) {
			personTable.getItems().remove(selectIndex);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("None Selection");
			alert.setHeaderText(null);
			alert.setContentText("You must select one person to delete!");
			
			alert.show();
		}
	}
	
	@FXML
	private void handleNewPerson() {
		Person person = new Person();
		boolean okClicked = mainApp.showAddAndEditPerson(person);
		if (okClicked) {
			personTable.getItems().add(person);
			personTable.getSelectionModel().select(person);
			showPersonDetials(person);
		}
	}
	
	@FXML
	private void handleEditPerson() {
		int selectIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectIndex != -1) {
			Person person = personTable.getItems().get(selectIndex);
			boolean okClicked = mainApp.showAddAndEditPerson(person);
			if (okClicked) {
				showPersonDetials(person);
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("None Selection");
			alert.setHeaderText(null);
			alert.setContentText("You must select one person to edit!");
			
			alert.show();
		}
	}

}
