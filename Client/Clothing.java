package sample.market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Clothing implements Initializable {

    @FXML
    private Button add1Button;

    @FXML
    private Button add2Button;

    @FXML
    private ImageView i1;

    @FXML
    private ImageView i2;

    @FXML
    private TextField q1;

    @FXML
    private TextField q2;

    @FXML
    private Button cartButton;

    @FXML
    private ImageView cimg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        i1.setImage(new Image("shirt.jpeg"));
        i2.setImage(new Image("pants.jpeg"));
        cimg.setImage(new Image("cart.png"));
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
    void onAddShirtClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("5");
        ItemsArrayList.q.add(q1.getText());
        ItemsArrayList.p.add("$250");
        ItemsArrayList.n.add("T-shirt");

    }

    @FXML
    void onAddPantsClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("6");
        ItemsArrayList.q.add(q2.getText());
        ItemsArrayList.p.add("$550");
        ItemsArrayList.n.add("Pants");

        
    }
}
