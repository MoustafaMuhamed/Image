package s;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class teest2 extends Application {

    @Override
    public void start(Stage primaryStage) {
     Group root = new Group();
     BorderPane borderPane = new BorderPane();
        TabPane tb=new TabPane();
        tb.setPrefSize(1365, 768);
        tb.setSide(Side.TOP);
        Createfirsttab(tb);
        final Tab newtab = new Tab();  
        newtab.setText(" + ");  
        newtab.setClosable(false);
        tb.getTabs().addAll(newtab);

        ScrollPane sp = new ScrollPane();

        tb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {  
                @Override  
                public void changed(ObservableValue<? extends Tab> observable,  
                          Tab oldSelectedTab, Tab newSelectedTab) {  
                     if (newSelectedTab == newtab) {  
                    	 
                          Tab tab = new Tab(); 
                          tab.setText("        ");  
                          WebView webView = new WebView();  
                          final WebEngine webEngine = webView.getEngine();  
                          final TextField urlField = new TextField("http://");  
                          webEngine.locationProperty().addListener(new ChangeListener<String>() {  
                               @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {  
                                    urlField.setText(newValue);  
                    		    	primaryStage.setTitle(newValue);

                               }  
                          });  
                          EventHandler<ActionEvent> goAction = new EventHandler<ActionEvent>() {  
                               @Override public void handle(ActionEvent event) {  
                                    webEngine.load(urlField.getText().startsWith("http://")   
                                              ? urlField.getText()   
                                                        : "http://" + urlField.getText());  
                               }  
                          };
                          urlField.setOnAction(goAction);  
                          Button goButton = new Button("Go");    
                          goButton.setOnAction(goAction);

                  		String homePageUrl = "http://www.google.com";

                          MenuButton menu = new WebMenu(webView);

                  		BrowserHistory history = new BrowserHistory(webView);

                  		NavigationBar navigationBar = new NavigationBar(webView, homePageUrl, true);

                  		navigationBar.getChildren().addAll(menu,history);

                  		VBox root = new VBox(navigationBar, webView);

                  		root.setStyle("-fx-padding: 10;" +
                  				"-fx-border-style: solid inside;" +
                  				"-fx-border-width: 2;" +
                  				"-fx-border-insets: 5;" +
                  				"-fx-border-radius: 5;" +
                  				"-fx-border-color: blue;");

                  		Scene scene = new Scene(root);

                  		sp.setContent(webView);
                          
                          VBox.setVgrow(webView, Priority.ALWAYS);  
                          tab.setContent(root);
                          final ObservableList<Tab> tabs = tb.getTabs();  
                          tab.closableProperty().bind(Bindings.size(tabs).greaterThan(2));  
                          tabs.add(tabs.size() - 1, tab);
                          tb.getTabs().add(tab);


                          tb.getSelectionModel().select(tab);
                     }
                      }
           });

        borderPane.setCenter(tb);
        root.getChildren().add(borderPane);
        Scene scene = new Scene(root, 1200, 600);

        primaryStage.setTitle("Wellcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private Tab Createfirsttab(TabPane tb) {
        Tab stab =new Tab(" Wellcome ");
        Label label = new Label();
        label.setText("\n\t\t\t To start browsing, click on New Tab.");  
           label.setFont(Font.font("Arial", FontWeight.BOLD, 20));  
           stab.setContent(label);
           tb.getTabs().add(stab);
           tb.getSelectionModel().select(stab);

           return stab;  
    }
}