package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class VisorAyudaController implements Initializable {

    @FXML
    private WebView wvVisorAyuda;
    private WebEngine webEngine;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		webEngine = wvVisorAyuda.getEngine();
		webEngine.load("https://docs.oracle.com/javafx/2/get_started/hello_world.htm");
	}

}
