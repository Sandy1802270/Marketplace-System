package sample.market;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class NewHomeAppController implements Initializable{

    @FXML
    private Button add1Button;

    @FXML
    private Button add2Button;

    @FXML
    private ImageView i1;

    @FXML
    private ImageView i2;
    @FXML
    private ImageView cimg;
    @FXML
    private TextField q1;

    @FXML
    private TextField q2;

        @FXML
    private GridPane productGridPane;
    private TextArea resultArea;
    private Connection connect;
    private PreparedStatement statement;
    ObservableList<ModelTable> ob = FXCollections.observableArrayList();
    static void caseElec(ArrayList<String> a, PrintWriter out, BufferedReader in) throws IOException {
        out.println(String.valueOf(13));
        out.flush();
        int size= Integer.parseInt(in.readLine());

        for (int i = 0; i < size; i++) {
            a.add(in.readLine());
        }    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try (Socket socket = new Socket("localhost", 1234)) {
            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            ArrayList<String> a=new ArrayList<>();
            caseElec(a,out,in);
            int i = 0;
            int j = 0;
            int n=0;
            while(n<a.size()-3) {
                if (j == 2) {
                    j = 0;
                }
                VBox layout = new VBox();
                File imageFile = new File(a.get(n));
                ImageView imgv = new ImageView();
                imgv.setImage(new Image(imageFile.toURI().toString()));
                imgv.setFitWidth(210);
                imgv.setFitHeight(210);
                Label productName = new Label(a.get(n + 1));
                Label price = new Label(a.get(n + 3) + "  Quantity: " + a.get(n + 2));
                layout.getChildren().addAll(imgv, productName, price);
                productGridPane.add(layout, i++, 0);
                productGridPane.setHgap(25); //horizontal gap in pixels => that's what you are asking for
                productGridPane.setVgap(25); //vertical gap in pixels
                productGridPane.setPadding(new Insets(10, 10, 10, 10));
                n += 4;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("Categories");
    }
    @FXML
    void onCartClick(ActionEvent event) throws IOException {
        App.setRoot("cart");  
    }
        
        @FXML
    void onAddVacClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("9");
        ItemsArrayList.q.add(q1.getText());
        ItemsArrayList.p.add("$1000");
        ItemsArrayList.n.add("Vaccuum Cleaner");
    }
    @FXML
    void onAddWMClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("10");
        ItemsArrayList.q.add(q2.getText());
        ItemsArrayList.p.add("$1150");
        ItemsArrayList.n.add("Washing Machine");
    }
}

