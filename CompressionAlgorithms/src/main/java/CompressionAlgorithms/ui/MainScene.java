package CompressionAlgorithms.ui;

import CompressionAlgorithms.domain.AppService;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.TextArea;


public class MainScene {
    
    private AppService appService;
    private Text actionStatus;
    private Stage savedStage;
    private static final String SceneTitleText = "Compression with Huffman and LZW";
    
    private TextArea txtArea;

    public MainScene(AppService appService) {
        this.appService = appService;
    }
    
    
    public void initializeStage(Stage primaryStage) {

        primaryStage.setTitle(SceneTitleText);	

        Label contentTitle = new Label("Compress or extract a file");
        contentTitle.setTextFill(Color.DARKBLUE);
        contentTitle.setFont(Font.font("Calibri", FontWeight.BOLD, 26));
        HBox contentTitleContainer = new HBox();
        contentTitleContainer.setAlignment(Pos.CENTER);
        contentTitleContainer.getChildren().add(contentTitle);

        Button openFileBtn = new Button("Open a file...");
        openFileBtn.setOnAction(new OpenButtonListener());
        HBox openFileBtnContainer = new HBox(10);
        openFileBtnContainer.setAlignment(Pos.CENTER);
        openFileBtnContainer.getChildren().addAll(openFileBtn);

        actionStatus = new Text();
        actionStatus.setText("No file selected");
        actionStatus.setFont(Font.font("Calibri", FontWeight.NORMAL, 16));
        actionStatus.setFill(Color.GRAY);

        Button compressFileBtn = new Button("Compress");
        compressFileBtn.setOnAction(new SaveButtonListener());
        HBox compressFileBtnContainer = new HBox(10);
        compressFileBtnContainer.setAlignment(Pos.CENTER);
        compressFileBtnContainer.getChildren().addAll(compressFileBtn);
        
        VBox vbox = new VBox(30);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(contentTitleContainer, openFileBtnContainer, actionStatus, compressFileBtnContainer);

        Scene scene = new Scene(vbox, 500, 300); 
        primaryStage.setScene(scene);
        primaryStage.show();

        savedStage = primaryStage;
    }

    private class OpenButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            showFileChooser();
        }
    }
    
    private class SaveButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            showSaveFileChooser();
        }
    }

    private void showFileChooser() {

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        
        this.appService.setSelectedFile(selectedFile);
        
        if (selectedFile != null) {
            actionStatus.setText("File selected: " + selectedFile.getName());
            actionStatus.setFill(Color.GREEN);
        }
        else {
            actionStatus.setText("No file selected");
            actionStatus.setFill(Color.GRAY);
        }
        
        
    }
    
    private void showSaveFileChooser() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as...");
        fileChooser.setInitialFileName(this.appService.getSelectedFile().getName());
        File savedFile = fileChooser.showSaveDialog(savedStage);
        
        this.appService.saveFile(savedFile);
        this.actionStatus.setText(this.appService.getActionStatus());
    }
    
}
