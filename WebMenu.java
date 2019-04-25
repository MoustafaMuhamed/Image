package s;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.web.WebView;

public class WebMenu extends MenuButton
{
	public WebMenu(WebView webView)
	{
		this.setText("Settings");

		this.setStyle("-fx-padding: 10;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: blue;");

		CheckMenuItem ctxMenu = new CheckMenuItem("Enable Context Menu");
		ctxMenu.setSelected(true);

		MenuItem normalFontMenu = new MenuItem("Normal");
		MenuItem biggerFontMenu = new MenuItem("10% Bigger");
		MenuItem smallerFontMenu = new MenuItem("10% Smaller");

		MenuItem normalZoomMenu = new MenuItem("Normal");
		MenuItem biggerZoomMenu = new MenuItem("10% Bigger");
		MenuItem smallerZoomMenu = new MenuItem("10% Smaller");

		RadioMenuItem grayMenu = new RadioMenuItem("GRAY");
		grayMenu.setSelected(true);
		RadioMenuItem lcdMenu = new RadioMenuItem("LCD");
		lcdMenu.setSelected(true);
		Menu scalingMenu = new Menu("Font Scale");
		scalingMenu.textProperty().bind(new SimpleStringProperty("Font Scale ").concat(webView.fontScaleProperty().multiply(100.0)).concat("%"));

		Menu smoothingMenu = new Menu("Font Smoothing");
		
		Menu zoomMenu = new Menu("Zoom");
		zoomMenu.textProperty().bind(new SimpleStringProperty("Zoom ").concat(webView.zoomProperty().multiply(100.0)).concat("%"));

		scalingMenu.getItems().addAll(normalFontMenu, biggerFontMenu, smallerFontMenu);
		smoothingMenu.getItems().addAll(grayMenu, lcdMenu);
		zoomMenu.getItems().addAll(normalZoomMenu, biggerZoomMenu, smallerZoomMenu);

		new ToggleGroup().getToggles().addAll(lcdMenu, grayMenu);

		normalFontMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				webView.setFontScale(1.0);
			}
		});

		biggerFontMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				webView.setFontScale(webView.getFontScale() + 0.10);
			}
		});

		smallerFontMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				webView.setFontScale(webView.getFontScale() - 0.10);
			}
		});

		grayMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				webView.setFontSmoothingType(FontSmoothingType.GRAY);			}
		});

		lcdMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				webView.setFontSmoothingType(FontSmoothingType.LCD);
			}
		});

		normalZoomMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				webView.setZoom(1.0);
			}
		});

		biggerZoomMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				webView.setZoom(webView.getZoom() + 0.10);
			}
		});

		smallerZoomMenu.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				webView.setZoom(webView.getZoom() - 0.10);
			}
		});


		webView.contextMenuEnabledProperty().bind(ctxMenu.selectedProperty());

		

		this.getItems().addAll(ctxMenu, scalingMenu, smoothingMenu, zoomMenu, new SeparatorMenuItem());
	}

	
}