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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static sample.market.App.setRoot;
import static sample.market.ClientController.send_ID_Password_Login;

public class Food implements Initializable {

    static String purchase(String userID, ArrayList<String> Cart_IDS, ArrayList<String> Cart_QTY, PrintWriter out, BufferedReader in) throws IOException //add more code (case more item to buy)
    {
        // sending the FUNCTION ID to server
        out.println(String.valueOf(6));
        out.flush();

        // sending the user USERID to server
        out.println(userID);
        out.flush();

        out.println(String.valueOf(Cart_IDS.size()));
        out.flush();

        for (int i = 0; i < Cart_IDS.size(); i++) {

            out.println(Cart_IDS.get(i));
            out.flush();

            out.println(Cart_QTY.get(i));
            out.flush();

        }

        // displaying server reply to check whether data sent are valid or not
        return in.readLine();

    }

    private TextArea resultArea;
    private Connection connect;
    private PreparedStatement statement;
    @FXML
    private ImageView i1;

    @FXML
    private ImageView i2;

    @FXML
    private ImageView i3;

    @FXML
    private ImageView i4;
    @FXML
    private Button add1Button;

    @FXML
    private Button add2Button;

    @FXML
    private Button add3Button;

    @FXML
    private Button add4Button;

    @FXML
    private TextField q1;

    @FXML
    private ImageView cimg;
    @FXML
    private TextField q2;

    @FXML
    private TextField q3;

    @FXML
    private TextField q4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String sql="select iname,price,qty,pic from item";
        try {
            ConnectDB db = new ConnectDB();
            connect = db.connectDB();
            ResultSet rs = connect.createStatement().executeQuery(sql);
            while (rs.next()) {
                //File imageFile = new File("mountains001.jpg");
//      System.out.println(imageFile.getAbsolutePath());
//      if (imageFile.exists()) {
//         ImageView imageView = new ImageView();
//         Image image = new Image(imageFile.toURI().toString());
//         imageView.setImage(image);
//         root.getChildren().add(imageView);
//      }
                File imageFile = new File(rs.getString("pic"));
                ImageView imgv=new ImageView();
                imgv.setImage(new Image(imageFile.toURI().toString()));
                Label lb = new Label(rs.getString("iname"));                
                Label lb1 = new Label(rs.getString("price"));
                Label lb2 = new Label(rs.getString("qty"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        i1.setImage(new Image("tom.jpg"));
        i2.setImage(new Image("ban.jpg"));
        i3.setImage(new Image("ch.jpg"));
        i4.setImage(new Image("pizza.jpg"));
        cimg.setImage(new Image("cart.png"));

    }

    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("Categories");
    }

    @FXML
    void onAddTomClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("1");
        ItemsArrayList.q.add(q1.getText());
        ItemsArrayList.p.add("$2.99");
        ItemsArrayList.n.add("Tomato");

    }

    @FXML
    void onAddBanClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("2");
        ItemsArrayList.q.add(q2.getText());
        ItemsArrayList.p.add("$4.99");
        ItemsArrayList.n.add("Banana");

    }

    @FXML
    void onAddCheeseClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("3");
        ItemsArrayList.q.add(q3.getText());
        ItemsArrayList.p.add("$6.99");
        ItemsArrayList.n.add("Cheese");

    }

    @FXML
    void onAddPizzaClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("4");
        ItemsArrayList.q.add(q4.getText());
        ItemsArrayList.p.add("$15.99");
        ItemsArrayList.n.add("Pizza");

    }

    @FXML
    void onCartClick(ActionEvent event) throws IOException {
        App.setRoot("cart");
    }

}
