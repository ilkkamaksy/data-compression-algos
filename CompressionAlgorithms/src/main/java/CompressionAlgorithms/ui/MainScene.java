package CompressionAlgorithms.ui;

import CompressionAlgorithms.domain.AppService;
import CompressionAlgorithms.utils.FileUtils;
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
import javafx.scene.control.TextArea;


public class MainScene {
    
    private AppService appService;
    private Text selectedFileStatus;
    private Text actionStatus;
    private Stage savedStage;
    Button compressFileBtn;
    private boolean isCompressedFile;
    private static final String SceneTitleText = "Compression with Huffman and LZW";
    
    private TextArea txtArea;

    public MainScene(AppService appService) {
        this.compressFileBtn = new Button("Compress");
        this.appService = appService;
    }
    
    public void initializeStage(Stage primaryStage) {

        primaryStage.setTitle(SceneTitleText);	

        Label contentTitle = new Label("Compress or extract a file");
        contentTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 22));
        HBox contentTitleContainer = new HBox();
        contentTitleContainer.setAlignment(Pos.CENTER);
        contentTitleContainer.getChildren().add(contentTitle);

        Button openFileBtn = new Button("Open a file...");
        openFileBtn.setOnAction(new OpenButtonListener());
        HBox openFileBtnContainer = new HBox(10);
        openFileBtnContainer.setAlignment(Pos.CENTER);
        openFileBtnContainer.getChildren().addAll(openFileBtn);

        selectedFileStatus = new Text();
        selectedFileStatus.setText("No file selected");
        selectedFileStatus.setFont(Font.font("Helvetica", FontWeight.NORMAL, 16));
        selectedFileStatus.setFill(Color.GRAY);

        compressFileBtn.setOnAction(new SaveButtonListener());
        HBox compressFileBtnContainer = new HBox(10);
        compressFileBtnContainer.setAlignment(Pos.CENTER);
        compressFileBtnContainer.getChildren().addAll(compressFileBtn);
        
        actionStatus = new Text();
        actionStatus.setText("--");
        actionStatus.setFont(Font.font("Helvetica", FontWeight.NORMAL, 16));
        actionStatus.setFill(Color.GRAY);
        
        VBox vbox = new VBox(30);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(contentTitleContainer, openFileBtnContainer, selectedFileStatus, compressFileBtnContainer, actionStatus);

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
            selectedFileStatus.setText("Selected file: " + selectedFile.getName());
            selectedFileStatus.setFill(Color.GREEN);
            isCompressedFile = FileUtils.isLzwFile(selectedFile.getName());
        } else {
            selectedFileStatus.setText("No file selected");
            selectedFileStatus.setFill(Color.GRAY);
        }
        
        if (isCompressedFile) {
            this.compressFileBtn.setText("Decompress");
        } else {
            this.compressFileBtn.setText("Compress");
        }
    }
    
    private void showSaveFileChooser() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as...");
        
        String initialFileName = FileUtils.setInitialFileExtension(this.appService.getSelectedFile().getName());
        fileChooser.setInitialFileName(initialFileName);
        
        File targetFile = fileChooser.showSaveDialog(savedStage);
        
        boolean success = false;
        if (this.isCompressedFile) {
            success = this.appService.decompressLzwFile(targetFile);
        } else {
            success = this.appService.compressFileLzw(targetFile);    
        }
        if (success) {
            actionStatus.setFill(Color.GREEN);    
        } else {
            actionStatus.setFill(Color.RED);
        }
        
        actionStatus.setText(this.appService.getActionStatus());
    }
    
    
}
