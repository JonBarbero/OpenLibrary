package ehu.isad;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class OpenLibrary extends Application {

    private Liburua book;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Open library API-a aztertzen");

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().add("Blockchain: Blueprint for a New Economy");
        comboBox.getItems().add("R for Data Science");
        comboBox.getItems().add("Fluent Python");
        comboBox.getItems().add("Natural Language Processing with PyTorch");
        comboBox.getItems().add("Data Algorithms");

        Map<String,String> isbnMap = new HashMap<>();
        isbnMap.put("Blockchain: Blueprint for a New Economy","9781491920497");
        isbnMap.put("R for Data Science","1491910399");
        isbnMap.put("Fluent Python","1491946008");
        isbnMap.put("Natural Language Processing with PyTorch","1491978236");
        isbnMap.put("Data Algorithms","9781491906187");

        comboBox.setEditable(false);
        comboBox.getSelectionModel().selectFirst();
        Text text=new Text();
        text.wrappingWidthProperty().set(400);

        this.readFromUrl(isbnMap.get(comboBox.getValue()));
        text.setText(this.book.getXehetasunak().getTitle() + "\n" + this.book.getXehetasunak().getNumber_of_pages() + "\n" + this.book.getXehetasunak().getPublishers()[0]);

        comboBox.setOnAction(e -> {

            System.out.println(comboBox.getValue());
            try {
                this.readFromUrl(isbnMap.get(comboBox.getValue()));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            text.setText(this.book.getXehetasunak().getTitle()+"\n"+this.book.getXehetasunak().getNumber_of_pages()+"\n"+this.book.getXehetasunak().getPublishers()[0]);

        });

        VBox vbox = new VBox(comboBox,text);
        vbox.setPadding(new Insets(0,0,0,0));
        Scene scene = new Scene(vbox, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void readFromUrl(String isbn) throws IOException {

        URL api;

        try {
            api = new URL(" https://openlibrary.org/api/books?bibkeys=ISBN:"+isbn+"&jscmd=details&format=json");
            URLConnection yc = api.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = in.readLine();

            String[] zatiak = inputLine.split("ISBN:"+isbn+"\":");
            inputLine = zatiak[1].substring(0, zatiak[1].length()-1);

            Gson gson = new Gson();
            this.book = gson.fromJson(inputLine, Liburua.class);

            in.close();
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}