package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Help;

public class VisorAyudaControllerHtml implements Initializable {
	@FXML
	private TreeView<Help> arbol;
	@FXML
	private WebView visor;
	private WebEngine webEngine;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Creamos el árbol del panel de la izquierda, el índice
		TreeItem<Help> rItem = new TreeItem<Help>(new Help("Raiz", ""));
		TreeItem<Help> r1Item = new TreeItem<Help>(new Help("Index", "index.html"));
		r1Item.getChildren().add(new TreeItem<Help>(new Help("Tema 1", "tema1.html")));
		r1Item.getChildren().add(new TreeItem<Help>(new Help("Tema 2", "tema2.html")));
		r1Item.getChildren().add(new TreeItem<Help>(new Help("Tema 3", "tema3.html")));
		rItem.getChildren().add(r1Item);
		arbol.setRoot(rItem);
		arbol.setShowRoot(false);
		//Mostramos el contenido inicial en el visor de la derecha
		webEngine = visor.getEngine();
		webEngine.load(getClass().getResource("/html/index.html").toExternalForm());
		//Añadimos un evento para cambiar de html al pinchar en el árbol
		arbol.setOnMouseClicked(e -> {
			if (arbol.getSelectionModel().getSelectedItem() != null) {
				Help elemento = (Help) arbol.getSelectionModel().getSelectedItem().getValue();
				if (elemento.getHtml() != null) {
					loadHelp(elemento.getHtml(), elemento.isLocal());
				}
			}
		});
	}

	private void loadHelp(String file, boolean local) {
		if (visor != null) {
			if (local) {
				webEngine.load(getClass().getResource("/html/" + file).toExternalForm());
			} else {
				webEngine.load(file);
			}
		}
	}
}