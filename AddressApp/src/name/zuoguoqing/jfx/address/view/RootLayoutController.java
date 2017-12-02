/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.address.view
 * @file RootLayoutController.java
 * @author zuoguoqing
 * @date 2017年4月9日
 * @version 
 */
package name.zuoguoqing.jfx.address.view;

import java.io.File;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import name.zuoguoqing.jfx.address.MainApp;

/**
 * @author zuoguoqing
 *
 */
public class RootLayoutController {
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleNewMenuItem() {
		mainApp.getPersonData().clear();
		mainApp.setPersonFilePath(null);
	}

	@FXML
	private void handleOpenMenuItem() {
		FileChooser chooser = new FileChooser();
		FileChooser.ExtensionFilter extensionFilter = 
				new FileChooser.ExtensionFilter("XML File (*.xml)", "*.xml");
		chooser.getExtensionFilters().add(extensionFilter);
		
		File file = chooser.showOpenDialog(mainApp.getPrimaryStage());
		if (file != null) {
			mainApp.loadPersonDataFromFile(file);
		}
	}
	
	@FXML
	private void handleSaveMenuItem() {
		File filePath = mainApp.getPersonFilePath();
		if (filePath != null) {
			mainApp.savePersonDataToFile(filePath);
		} else {
			handleSaveAsMenuItem();
		}
	}
	
	@FXML
	private void handleSaveAsMenuItem() {
		FileChooser chooser = new FileChooser();
		FileChooser.ExtensionFilter extensionFilter = 
				new FileChooser.ExtensionFilter("XML File (*.xml)", "*.xml");
		chooser.getExtensionFilters().add(extensionFilter);
		
		File file = chooser.showSaveDialog(mainApp.getPrimaryStage());
		if (file != null) {
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.savePersonDataToFile(file);
		} 
	}
	
	@FXML
	private void handleExitMenuItem() {
		Platform.exit();
	}
	
	@FXML
	private void handleAboutMenuItem() {
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setTitle("About");
//		alert.setHeaderText(null);
//		alert.setContentText("This is a address app!");
//		alert.show();
		
		mainApp.showAbout();
	}
}
