/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virus.removal.javafxapplication;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author cgopi
 */
public class JavaFXApplication extends Application {
	static Stage primaryStage;

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
		stage.getIcons().add(new Image("img/taskbar.png"));
		stage.initStyle(StageStyle.UNDECORATED);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
		primaryStage = stage;
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
			
		});
	}
	
	/**
	 * This method is called when the application should stop, 
	 * and provides a convenient place to prepare for application exit and destroy resources. 
	 */
	@Override
	public void stop() throws Exception 
	{
	    super.stop();
	    Platform.exit();
	    System.exit(0);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
