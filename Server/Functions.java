
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

public class Functions {

    Connection connect;
    PreparedStatement statement;
    ResultSet result;
    static int d = 0;

    public String case0(String username, String pass) {
        ConnectDB db = new ConnectDB();
        connect = db.connectDB();
        try {
            String sql = "SELECT * FROM client WHERE cid = ? and pass = ?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, pass);
            result = statement.executeQuery();
            if (result.next()) {
                //if entered data is correct,, this area would be executed after.
                return "okay";
            } else {
                return "not okay";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String case1(String USER_ID, String pass, String fname, String lname) {

        ConnectDB db = new ConnectDB();
        connect = db.connectDB();

        try {
            String sql1 = " SELECT * FROM client ";
            statement = connect.prepareStatement(sql1);
            ResultSet rs = statement.executeQuery(sql1);
            while (rs.next()) {
                String ID = rs.getString("cid");
                if (ID.equals(USER_ID)) {
                    return "not okay";
                    //return not valid
                }
            }
            //ADD USER ID AND PASSWORD TO THE DATABASE
            String sql2 = " INSERT INTO client (cid, pass,fname,lname)" + " values (?, ?, ?, ?)";
            statement = connect.prepareStatement(sql2);
            statement.setString(1, USER_ID);
            statement.setString(2, pass);
            statement.setString(3, fname);
            statement.setString(4, lname);
            boolean res = statement.execute();
            if (res == true) {
                return "okay";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "okay";
    }

    public String case5(String id) {//view balance
        String balance = ".";
        ConnectDB db = new ConnectDB();
        connect = db.connectDB();
        try {
            String sql5 = " SELECT balance FROM client WHERE cid=?";
            statement = connect.prepareStatement(sql5);
            statement.setString(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                balance = result.getString("balance");
                System.out.println(balance);
                return balance;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public String case8(String id, String amount) {//deposit, inc balance
        ConnectDB db = new ConnectDB();
        connect = db.connectDB();
        try {
            String sql5 = " update client set balance=balance+? where cid=?";
            statement = connect.prepareStatement(sql5);
            statement.setInt(1, Integer.parseInt(amount));
            statement.setString(2, id);
            statement.executeUpdate();
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    public String case7(String iname) {//search
        ConnectDB db = new ConnectDB();
        connect = db.connectDB();
        String price = "";
        try {
            String sql7 = " select price from item where iname=?";
            statement = connect.prepareStatement(sql7);
            statement.setString(1, iname);
            result = statement.executeQuery();
            while (result.next()) {
                price = result.getString("price");
                return price;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "not okay";
    }

    public void case9(String iname, ArrayList<String> a) {
        //retrieve item id and price,, having item name
        ConnectDB db = new ConnectDB();
        connect = db.connectDB();
        String price;
        String id;

        try {
            String sql9 = " select price,itemid from item where iname= ?";
            statement = connect.prepareStatement(sql9);
            statement.setString(1, iname);
            result = statement.executeQuery();
            while (result.next()) {
                price = result.getString("price");
                id = result.getString("itemid");
                a.add(id);
                a.add(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String case61(String user) {//balance check
        String balance;
        try {
            String sql61 = "select balance from client where cid=?";
            statement = connect.prepareStatement(sql61);
            statement.setString(1, user);
            result = statement.executeQuery();
            //String sql6="insert into purchased_items (cid,oid,itemid) values (?,?,?)";
            while (result.next()) {
                balance = result.getString("balance");
                return balance;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "not okay";
    }

    public String case62(ArrayList<String> itemid) { //check qty
        ConnectDB db = new ConnectDB();
        connect = db.connectDB();
        ArrayList<String> qty = new ArrayList<>();
        try {
            for (int i = 0; i < itemid.size(); i++) {
                String sql62 = "select qty from item where itemid=?";
                //String sql62="update qty set qty=qty-? where itemid=?";
                statement = connect.prepareStatement(sql62);
                statement.setString(1, itemid.get(i));
                result = statement.executeQuery();
                while (result.next()) {
                    qty.add(result.getString("qty"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "fail";
    }
}