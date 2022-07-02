/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchItems implements Initializable {

    static String search(String itemName, PrintWriter out, BufferedReader in) throws IOException {
        // sending the FUNCTION ID to server
        out.println(String.valueOf(7));
        out.flush();

        // sending the itemID to server
        out.println(itemName);
        out.flush();
        // displaying server reply to check whether data sent are valid or not
        return in.readLine();
    }

    static void addtocart(String itemName, ArrayList<String> a, PrintWriter out, BufferedReader in) throws IOException {
        // sending the FUNCTION ID to server
        out.println(String.valueOf(9));
        out.flush();

        // sending the itemID to server
        out.println(itemName);
        out.flush();

        a.add(in.readLine());//id
        a.add(in.readLine());//price
    }

    @FXML
    private TextField iname;

    @FXML
    private TextField qty;
    @FXML
    private TableColumn<ModelTable, String> itemColumn;

    @FXML
    private TableColumn<ModelTable, String> priceColumn;

    @FXML
    private TableView<ModelTable> table;

    private Connection connect;
    private PreparedStatement statement;

    //ObservableList<ModelTable> ob = FXCollections.observableArrayList();
    ObservableList<ModelTable> ob = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("Categories");
    }

    @FXML
    void onSearchCompletedClick(ActionEvent event) throws IOException {

        for (int i = 0; i < table.getItems().size(); i++) {
            table.getItems().clear();
        }
        try (Socket socket = new Socket("localhost", 1234)) {
            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));
            String s = iname.getText().toLowerCase();
            s = (s.substring(0, 1).toUpperCase()) + s.substring(1, s.length());
            String res = search(s, out, in);
            //App.setRoot("SearchItems");

            if (!(res.equals("not okay"))) {
                ModelTable m = new ModelTable(s, res);
                ob.add(m);
                itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
                priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                table.getItems().addAll(ob);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Message !");
                alert.setHeaderText("ERROR !");
                String str = "Item is sold out !";
                alert.setContentText(str);
                alert.show();
            }
            out.println("exit");
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void onAddClick(ActionEvent event) throws IOException {
        if (!(qty.getText() == null) && !(iname.getText() == null)) {
            //If Available in Database,, Added Successfully Message Box
            try (Socket socket = new Socket("localhost", 1234)) {
                // writing to server
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true);
                // reading from server
                BufferedReader in
                        = new BufferedReader(new InputStreamReader(
                                socket.getInputStream()));
                ArrayList<String> ip = new ArrayList<>();
                String s = iname.getText().toLowerCase();
                s = (s.substring(0, 1).toUpperCase()) + s.substring(1, s.length());
                String res = search(s, out, in);
                //App.setRoot("SearchItems");

                if (!(res.equals("not okay"))) {
                    try (Socket socket1 = new Socket("localhost", 1234)) {
                        // writing to server
                        PrintWriter out1 = new PrintWriter(
                                socket1.getOutputStream(), true);
                        // reading from server
                        BufferedReader in1
                                = new BufferedReader(new InputStreamReader(
                                        socket1.getInputStream()));
                        addtocart(s, ip, out1, in1);
                        ItemsArrayList.i.add(ip.get(0));
                        ItemsArrayList.q.add(qty.getText());
                        ItemsArrayList.p.add(ip.get(1));
                        ItemsArrayList.n.add(s);
                        System.out.println(ip.get(0));
                        System.out.println(ip.get(1));
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message !");
                    alert.setHeaderText("ERROR !");
                    String str = "Item not found. Try Again !";
                    alert.setContentText(str);
                    alert.show();
                }
                out.println("exit");
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}


