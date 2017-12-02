package name.zuoguoqing.jfx.address;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import name.zuoguoqing.jfx.address.model.Person;
import name.zuoguoqing.jfx.address.model.PersonListWrapper;
import name.zuoguoqing.jfx.address.view.PersonAddAndEditController;
import name.zuoguoqing.jfx.address.view.PersonViewController;
import name.zuoguoqing.jfx.address.view.RootLayoutController;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Person> personData = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public MainApp() {
		personData.add(new Person("Hans", "Muster"));
		personData.add(new Person("Ruth", "Mueller"));
		personData.add(new Person("Heinz", "Kurz"));
		personData.add(new Person("Cornelia", "Meier"));
		personData.add(new Person("Werner", "Meyer"));
		personData.add(new Person("Lydia", "Kunz"));
		personData.add(new Person("Anna", "Best"));
		personData.add(new Person("Stefan", "Meier"));
		personData.add(new Person("Martin", "Mueller"));
	}

	/**
	 * Start stage
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");
		this.primaryStage.getIcons().add(new Image("file:resource/images/AddressApp.png"));

		initRootLayout();

		showPersonView();

	}

	/**
	 * Initialize the root layout
	 */
	private void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//try to load person data that use last time
		File file = getPersonFilePath();
		if (file != null) {
			loadPersonDataFromFile(file);
		}
	}

	/**
	 * Show person view
	 */
	private void showPersonView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonView.fxml"));
			AnchorPane personView = (AnchorPane) loader.load();

			rootLayout.setCenter(personView);

			PersonViewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public boolean showAddAndEditPerson(Person person) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonAddAndEdit.fxml"));
			AnchorPane addAndEditPerson = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Add and Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setScene(new Scene(addAndEditPerson));

			PersonAddAndEditController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(person);

			dialogStage.showAndWait();

			return controller.isOKCliecked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * return primary stage
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Return person data
	 * 
	 * @return
	 */
	public ObservableList<Person> getPersonData() {
		return personData;
	}

	/**
	 * get file path from system
	 * @return
	 */
	public File getPersonFilePath() {
		Preferences preferences = Preferences.userNodeForPackage(MainApp.class);
		String filePath = preferences.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	/**
	 * set file path to system
	 * @param filePath
	 */
	public void setPersonFilePath(File filePath) {
		Preferences preferences = Preferences.userNodeForPackage(MainApp.class);
		if (filePath != null) {
			preferences.put("filePath", filePath.getName());
			primaryStage.setTitle("AddressApp - " + filePath.getName());
		} else {
			preferences.remove("filePath");
			primaryStage.setTitle("AddressApp");
		}
	}

	
	/**
	 * save person data to xml format file using JAXB
	 * @param file
	 */
	public void savePersonDataToFile(File file) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonListWrapper.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			PersonListWrapper persons = new PersonListWrapper();
			persons.setPersons(personData);
			
			marshaller.marshal(persons, file);
			
			setPersonFilePath(file);
		} catch (JAXBException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Save File Error");
			alert.setHeaderText(null);
			alert.setContentText("Fail to save person data to file!");
			alert.show();
		}

	}
	
	/**
	 * load person data from existing xml file using JAXB
	 * @param file
	 */
	public void loadPersonDataFromFile(File file) { 
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonListWrapper.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			PersonListWrapper persons = (PersonListWrapper)unmarshaller.unmarshal(file);
			personData.clear();
			personData.addAll(persons.getPersons());
			
			setPersonFilePath(file);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Fail to load xml data from given file");
			alert.show();
		}
	}
	
	public void showAbout() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/About.fxml"));
			BorderPane about = (BorderPane)loader.load();

			Scene scene = new Scene(about);
			Stage aboutStage = new Stage();
			aboutStage.initModality(Modality.WINDOW_MODAL);
			aboutStage.initOwner(primaryStage);
			aboutStage.initStyle(StageStyle.DECORATED);
			aboutStage.setScene(scene);
			aboutStage.setTitle("About");
			
			Label label = (Label) about.getCenter();
			if (label.getText() == null || label.getText() == "") {
				Image labelImage = new Image("file:resource/images/AddressApp.png");
				label.setGraphic(new ImageView(labelImage));
				aboutStage.getIcons().add(labelImage);
			}
			
			aboutStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
