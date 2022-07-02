/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.market;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {
    @FXML
    private TextField q1;

    @FXML
    private TextField q2;

    @FXML
    private TextField q3;

    @FXML
    private TextField q4;
    @FXML
    private GridPane productGridPane;
    private TextArea resultArea;
    private Connection connect;
    private PreparedStatement statement;
    ObservableList<ModelTable> ob = FXCollections.observableArrayList();


    static void caseFood(ArrayList<String> a, PrintWriter out, BufferedReader in) throws IOException {
        out.println(String.valueOf(11));
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
            caseFood(a,out,in);
            int i = 0;
            int j = 0;
            int n=0;
            while(n<a.size()-3){
                if (j == 2) {
                    j = 0;
                    i++;
                }
                if (i == 3) {
                    i = 0;
                }
                VBox layout = new VBox();
                File imageFile = new File(a.get(n));
                ImageView imgv = new ImageView();
                imgv.setImage(new Image(imageFile.toURI().toString()));
                imgv.setFitWidth(150);
                imgv.setFitHeight(150);
//                            a.add(pic);
//                            a.add(iname);
//                            a.add(qty);
//                            a.add(price);
                Label productName = new Label(a.get(n+1));
                Label price = new Label(a.get(n+3) + "  Quantity: " + a.get(n+2));
                layout.getChildren().addAll(imgv, productName, price);
                productGridPane.add(layout, i, j++);
                productGridPane.setHgap(25);
                productGridPane.setVgap(25);
                productGridPane.setPadding(new Insets(10, 10, 10, 10));
                n+=4;
            }
            } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
        
            @FXML
    void onAddTomClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("1");
        ItemsArrayList.q.add(q1.getText());
        ItemsArrayList.p.add("$3");
        ItemsArrayList.n.add("Tomato");

    }

    @FXML
    void onAddBanClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("2");
        ItemsArrayList.q.add(q2.getText());
        ItemsArrayList.p.add("$4");
        ItemsArrayList.n.add("Banana");

    }

    @FXML
    void onAddCheeseClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("3");
        ItemsArrayList.q.add(q3.getText());
        ItemsArrayList.p.add("$6");
        ItemsArrayList.n.add("Cheese");

    }

    @FXML
    void onAddPizzaClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("4");
        ItemsArrayList.q.add(q4.getText());
        ItemsArrayList.p.add("$15");
        ItemsArrayList.n.add("Pizza");
    }
    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("Categories");
    }
    @FXML
    void onCartClick(ActionEvent event) throws IOException {
        App.setRoot("cart");
    }
}

