
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.ArrayList;

/*
Function_IDs {
  
send_Email_Password_Login=0
send_Email_Password_CreateAccount=1
Add_Item=2
Edit_Item=3
Remove_Item=4
View_Acc_Info=5
Purchase=6
Search=7
Deposit_Cash=8
  
}    
 */
// Client class
class Client {

    static String send_ID_Password_Login(String userID, String password, PrintWriter out, BufferedReader in) throws IOException {
        // sending the FUNCTION ID to server
        out.println(String.valueOf(0));
        out.flush();

        // sending the user USERID to server
        out.println(userID);
        out.flush();
        // sending the user password to server
        out.println(password);
        out.flush();
        // displaying server reply to check whether data sent are correct or not
        return in.readLine();

    }

    static String send_ID_Password_CreateAccount(String userID, String password, String ConfirmPassword, PrintWriter out, BufferedReader in) throws IOException {

        if (password.equals(ConfirmPassword)) {

            // sending the FUNCTION ID to server
            out.println(String.valueOf(1));
            out.flush();

            // sending the user USERID to server
            out.println(userID);
            out.flush();
            // sending the user password to server
            out.println(password);
            out.flush();

            return in.readLine();

        } else {
            // displaying server reply to check whether data sent are correct or not
            return "not same";
        }

    }


    static String View_Acc_Info(String User_ID, PrintWriter out, BufferedReader in) throws IOException {
        // sending the FUNCTION ID to server
        out.println(String.valueOf(5));
        out.flush();

        // sending the User_ID to server
        out.println(User_ID);
        out.flush();

        // displaying server reply to check whether data sent are valid or not
        return in.readLine();
    }

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

    static String deposit(String userID, String amount, PrintWriter out, BufferedReader in) throws IOException {
        // sending the FUNCTION ID to server
        out.println(String.valueOf(8));
        out.flush();

        // sending the user USERID to server
        out.println(userID);
        out.flush();

        // sending the itemID to server
        out.println(amount);
        out.flush();
        // displaying server reply to check whether data sent are valid or not
        return in.readLine();
    }

    static void close_program(PrintWriter out) {
        // sending the exit to server to end program 
        out.println("exit");
        out.flush();

    }

    // driver code
    public static void main(String[] args) {
        // establish a connection by providing host and port
        // number
        String serverIP = "192.168.1.121";
        try (Socket socket = new Socket(serverIP, 1234)) {

            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));

            ArrayList<String> items_ID = new ArrayList<>();
            ArrayList<String> quantity = new ArrayList<>();
            items_ID.add("1");
            items_ID.add("2");
            items_ID.add("3");
            quantity.add("1");
            quantity.add("2");
            quantity.add("3");

            System.out.println(purchase("123", items_ID, quantity, out, in));


            close_program(out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
