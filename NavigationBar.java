package s;
import java.io.File;
import java.net.MalformedURLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
 
public class NavigationBar extends HBox
{
    private FileChooser fileChooser = new FileChooser();
     
    public NavigationBar(WebView webView, String homePageUrl, boolean goToHomePage) 
    {
        this.setSpacing(4);
         
        this.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
 
		TabPane bane = new TabPane();

        Label label = new Label("URL :");
         
        fileChooser.setTitle("Open Web Content");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("HTML Files", "*.html", "*.htm"));
         
        WebEngine webEngine = webView.getEngine();
         
        TextField pageUrl = new TextField();
         
        Button refreshButton = new Button();
        Button goButton = new Button();
        Button homeButton = new Button();
        Button openButton = new Button("Open");

        HBox.setHgrow(pageUrl, Priority.ALWAYS);
        
        Image home = new Image(getClass().getResourceAsStream("homeok.jpg"));
        homeButton.setGraphic(new ImageView(home));
        
        Image refresh = new Image(getClass().getResourceAsStream("refreshok.png"));
        refreshButton.setGraphic(new ImageView(refresh));
        
        Image go = new Image(getClass().getResourceAsStream("gook.png"));
        goButton.setGraphic(new ImageView(go));
        
        
        pageUrl.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                webEngine.load(pageUrl.getText());
            }
        });
         
        // Update the stage title when a new web page title is available        
        webEngine.locationProperty().addListener(new ChangeListener<String>()
        {
            public void changed(ObservableValue<? extends String> ov,
                    final String oldvalue, final String newvalue) 
            {
                pageUrl.setText(newvalue);
            }
        });
         
        refreshButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                webEngine.reload();
            }
        });
         
        goButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                webEngine.load(pageUrl.getText());
            }
        });
         
        homeButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                webEngine.load(homePageUrl);
            }
        });
                 
        openButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                File selectedFile = fileChooser.showOpenDialog(webView.getScene().getWindow());
                 
                if (selectedFile != null) 
                {
                    try
                    {
                        webEngine.load(selectedFile.toURI().toURL().toExternalForm());
                    }
                    catch(MalformedURLException ex) 
                    {
                        ex.printStackTrace();
                    }
                }
            }
        });
         
        this.getChildren().addAll(label, pageUrl,goButton, refreshButton, homeButton, openButton);
         
        if (goToHomePage) 
        {
            webEngine.load(homePageUrl);
        }
    }
}