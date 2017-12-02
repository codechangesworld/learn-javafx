package name.zuoguoqing.jfx.cs.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import name.zuoguoqing.jfx.cs.MainApp;

public class RootLayoutController {
    private MainApp mainApp;
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private void handleAboutMenuItem() {
        mainApp.showAbout();
    }

    @FXML
    private void handleServerMenuItem() {
        mainApp.showServerLayout();
    }

    @FXML
    private void handleClientMenuItem() {
        mainApp.showClientLayout();
    }
    
    @FXML
    private void handleBroadcastMenuItem() {
        
    }
    
    @FXML
    private void handleCSMenuItem() {
        mainApp.showCSLayout();
    }

    @FXML
    private void handleCloseMenuItem() {
        Platform.exit();
    }
}
