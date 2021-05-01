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
    Button compressLzwBtn;
    Button compressHuffmanBtn;
    private boolean isLzwFile;
    FileType fileType = FileType.TXT;
    private static final String SceneTitleText = "Compression with Huffman and LZW";
    
    private TextArea txtArea;

    public MainScene(AppService appService) {
        this.compressLzwBtn = new Button("Compress with LZW");
        this.compressHuffmanBtn = new Button("Compress with Huffman Code");
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

        compressLzwBtn.setOnAction(new CompressLzwButtonListener());
        compressHuffmanBtn.setOnAction(new CompressHffButtonListener());
        HBox compressFileBtnContainer = new HBox(10);
        compressFileBtnContainer.setAlignment(Pos.CENTER);
        compressFileBtnContainer.getChildren().addAll(compressLzwBtn, compressHuffmanBtn);
        
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
    
    private class CompressLzwButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            fileType = FileType.LZW;
            setActionStatusBusy();
            initSaveFileChooser();
        }
    }
    
    private class CompressHffButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            fileType = FileType.HFF;
            setActionStatusBusy();
            initSaveFileChooser();
        }
    }
   
    private void showFileChooser() {

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        
        this.appService.setSelectedFile(selectedFile);
        
        if (selectedFile != null) {
            selectedFileStatus.setText("Selected file: " + selectedFile.getName());
            selectedFileStatus.setFill(Color.GREEN);
        } else {
            selectedFileStatus.setText("No file selected");
            selectedFileStatus.setFill(Color.GRAY);
        }

        resetActionStatus();
        
        if (FileUtils.isLzwFile(selectedFile)) {
            this.compressLzwBtn.setText("Decompress");
        } else if (FileUtils.isHffFile(selectedFile)) {
            this.compressHuffmanBtn.setText("Decompress");
        } else {
            this.compressLzwBtn.setText("Compress with LZW");
            this.compressHuffmanBtn.setText("Compress with Huffman Code");
        }
    }
    
    private void initSaveFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as...");
        fileChooser.setInitialFileName(FileUtils.setInitialFileExtension(this.appService.getSelectedFile()));
        File targetFile = fileChooser.showSaveDialog(savedStage);
        boolean success = showSaveFileChooserFoo(targetFile);
        setActionStatus(success);
    }
    
    private boolean showSaveFileChooserFoo(File targetFile) {
        switch (fileType) {
            case LZW:
                return FileUtils.isLzwFile(this.appService.getSelectedFile()) ? this.appService.decompressLzwFile(targetFile) : this.appService.compressWithLzw(targetFile);
            case HFF:
                return FileUtils.isHffFile(this.appService.getSelectedFile()) ? this.appService.decompressHffFile(targetFile) : this.appService.compressWithHff(targetFile);
            default:
                return false;
        }
    }
    
    private void resetActionStatus() {
        actionStatus.setFill(Color.GRAY);
        actionStatus.setText("--");
    }
    
    private void setActionStatusBusy() {
        actionStatus.setFill(Color.GRAY);
        actionStatus.setText("Working on it...");
    }
    
    private void setActionStatus(boolean success) {
        if (success) {
            actionStatus.setFill(Color.GREEN);    
        } else {
            actionStatus.setFill(Color.RED);
        }
        actionStatus.setText(this.appService.getActionStatus());
    }
}
