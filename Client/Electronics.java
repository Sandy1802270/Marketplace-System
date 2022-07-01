/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.market;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Electronics implements Initializable {

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
    private ImageView cimg;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        i1.setImage(new Image("laptop.png"));
        i2.setImage(new Image("mobile.png"));
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
    void onAddLaptopClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("7");
        ItemsArrayList.q.add(q1.getText());
        ItemsArrayList.p.add("$1500");
        ItemsArrayList.n.add("Laptop");

    }

    @FXML
    void onAddMobileClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("8");
        ItemsArrayList.q.add(q2.getText());
        ItemsArrayList.p.add("$600");
        ItemsArrayList.n.add("Mobile");

    }
}
