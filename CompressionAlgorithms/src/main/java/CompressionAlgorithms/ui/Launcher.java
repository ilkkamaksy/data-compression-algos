/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CompressionAlgorithms.ui;

import CompressionAlgorithms.domain.AppService;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author ilkka
 */
public class Launcher extends Application {
    
    private AppService appService;
    
    public static void run(String [] args) {
        Application.launch(args);
    }
    
    @Override
    public void init() throws Exception {
        this.appService = new AppService();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        MainScene mainScene = new MainScene(this.appService);
        mainScene.initializeStage(stage);
    }
}
