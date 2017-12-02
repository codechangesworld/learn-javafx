/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.address.controller
 * @file PersonAddAndEditController.java
 * @author zuoguoqing
 * @date 2017年4月9日
 * @version 
 */
package name.zuoguoqing.jfx.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import name.zuoguoqing.jfx.address.model.Person;
import name.zuoguoqing.jfx.address.util.DateUtil;

/**
 * @author zuoguoqing
 *
 */
public class PersonAddAndEditController {
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField postalCodeField;
	@FXML
	private TextField birthdayField;
	
	@FXML
	private Button okButton;
	
	private Stage dialogStage;
	private Person person;
	private boolean okClicked = false;
	
	public PersonAddAndEditController() {
		
	}
	
	@FXML
	private void initialize() {
		
	}
	
	public void setPerson(Person person) {
		this.person = person;
		
		firstNameField.setText(person.getFirstName());
		lastNameField.setText(person.getLastName());
		streetField.setText(person.getStreet());
		cityField.setText(person.getCity());
		postalCodeField.setText(person.getPostalCode().toString());
		birthdayField.setText(DateUtil.format(person.getBirthday()));
		birthdayField.setPromptText("yyyy.MM.dd");
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public boolean isOKCliecked() {
		return okClicked;
	}
	
	@FXML
	private void handleOK() {
		if (isInputValid()) {
			person.setFirstName(firstNameField.getText());
			person.setLastName(lastNameField.getText());
			person.setStreet(streetField.getText());
			person.setCity(cityField.getText());
			person.setPostalCode(Integer.valueOf(postalCodeField.getText()));
			person.setBirthday(DateUtil.parse(birthdayField.getText()));
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isInputValid() {
		String errorMsg = "";
		
		if (firstNameField.getText() == null || firstNameField.getText() == "") {
			errorMsg += "No invalid first name!\n";
		}
		if (lastNameField.getText() == null || lastNameField.getText() == "") {
			errorMsg += "No invalid last name!\n";
		}
		if (streetField.getText() == null || streetField.getText() == "") {
			errorMsg += "No invalid street!\n";
		}
		if (cityField.getText() == null || cityField.getText() == "") {
			errorMsg += "No invalid city!\n";
		}
		if (postalCodeField.getText() == null || postalCodeField.getText() == "") {
			errorMsg += "No invalid postal code!\n";
		} else {
			try {
				Integer.valueOf(postalCodeField.getText());
			} catch (NumberFormatException e) {
				errorMsg += "No invalid postal code! Must be Integer!";
			}
		}
		if (birthdayField.getText() == null || birthdayField.getText() == "") {
			errorMsg = "No invalid birthday!\n";
		} else {
			try {
				DateUtil.parse(birthdayField.getText());
			} catch (Exception e) {
				errorMsg += "No invalid birthday! Must like 'yyyy.MM.dd' style!";
			}
		}
		
		if (errorMsg.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Invalid Field!");
			alert.setHeaderText("Please correct invalid fields!");
			alert.setContentText(errorMsg);
			alert.show();
			
			return false;
		}
		
		
	}
	
}
