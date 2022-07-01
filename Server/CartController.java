package sample.market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CartController implements Initializable {

    static String purchase(String userID, ArrayList<String> Cart_IDS,
            ArrayList<String> Cart_QTY, PrintWriter out, BufferedReader in)
            throws IOException //add more code (case more item to buy)
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
    @FXML
    private TableColumn<ModelTable, String> itemColumn;

    @FXML
    private TableColumn<ModelTable, String> priceColumn;

    @FXML
    private TableColumn<ModelTable, String> quantityColumn;

    @FXML
    private TableView<ModelTable> table;

    ObservableList<ModelTable> ob = FXCollections.observableArrayList();

    @FXML
    private TextField qty;
    @FXML
    private TextField edititem;
    @FXML
    private TextField removeitem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ItemsArrayList v = new ItemsArrayList();
        for (int i = 0; i < ItemsArrayList.q.size(); i++) {
            ob.add(new ModelTable(
                    ItemsArrayList.n.get(i),
                    ItemsArrayList.q.get(i),
                    ItemsArrayList.p.get(i)));
        }
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.getItems().addAll(ob);
    }

    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("Categories");
    }

    @FXML
    void onChangeClick(ActionEvent event) throws IOException {
        int index = -1;
        for (int i = 0; i < ItemsArrayList.n.size(); i++) {
            if ((edititem.getText()).equals(ItemsArrayList.n.get(i))) {
                index = i;
            }
        }

        if (index != -1) {
            ItemsArrayList.q.set(index, qty.getText());
            App.setRoot("cart");

        } else if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Message !");
            alert.setHeaderText("ERROR !");
            String s = "Item is not in your cart !";
            alert.setContentText(s);
            alert.show();
        }
    }

    @FXML
    void onRemoveClick(ActionEvent event) throws IOException {
        int index = -1;
        for (int i = 0; i < ItemsArrayList.n.size(); i++) {
            if ((removeitem.getText()).equals(ItemsArrayList.n.get(i))) {
                index = i;
            }
        }

        if (index != -1) {
            ItemsArrayList.n.remove(index);
            ItemsArrayList.i.remove(index);
            ItemsArrayList.p.remove(index);
            ItemsArrayList.q.remove(index);
            App.setRoot("cart");

        } else if (index == -1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Message !");
            alert.setHeaderText("ERROR !");
            String s = "Item is not in your cart !";
            alert.setContentText(s);
            alert.show();
        }
    }

    @FXML
    void onCheckoutClick(ActionEvent event) {
        for (int x = 0; x < ItemsArrayList.q.size(); x++) {
            System.out.println(ItemsArrayList.n.get(x));
            System.out.println(ItemsArrayList.q.get(x));
        }
    }
}
