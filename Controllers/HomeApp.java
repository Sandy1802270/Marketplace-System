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

public class HomeApp implements Initializable{

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

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        i1.setImage(new Image("vaccuum.png"));
        i2.setImage(new Image("washing.png"));
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

