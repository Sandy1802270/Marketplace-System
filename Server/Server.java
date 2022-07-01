
import java.io.*;
import java.net.*;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author ofaof
 */
/*
Function_IDs {
  
  send_Email_Password_Login=0
  send_Email_Password_CreateAccount=1
  
  
  
}    
 */
// Server class
public class Server {

    public static void main(String[] args) {
        ServerSocket server = null;

        try {

            // server is listening on port 1234
            server = new ServerSocket(1234);
            server.setReuseAddress(true);

            // running infinite loop for getting
            // client request
            while (true) {

                // socket object to receive incoming client
                // requests
                Socket client = server.accept();

                // Displaying that new client is connected
                // to server
                System.out.println("New client connected"
                        + client.getInetAddress()
                                .getHostAddress());

                // create a new thread object
                ClientHandler clientSock
                        = new ClientHandler(client);

                // This thread will handle the client
                // separately
                new Thread(clientSock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // ClientHandler class
    private static class ClientHandler implements Runnable {

        private final Socket clientSocket;

        // Constructor
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            PrintWriter out = null;
            BufferedReader in = null;
            try {

                // get the outputstream of client
                out = new PrintWriter(
                        clientSocket.getOutputStream(), true);

                // get the inputstream of client
                in = new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()));

                String line;

                while ((line = in.readLine()) != null) {
                    if (line.equals("exit")) {
                        break;

                    }

                    switch (Integer.parseInt(line)) {

                        case (0)://login 
                        {
                            String USER_ID = in.readLine();
                            String password = in.readLine();
                            /*check at DATABASE*/
                            Functions f = new Functions();
                            out.println(f.case0(USER_ID, password));

                        }
                        case (1): {
                            String USER_ID = in.readLine();
                            String password = in.readLine();
                            String fname = in.readLine();
                            String lname = in.readLine();
                            Functions f = new Functions();
                            String s = f.case1(USER_ID, password, fname, lname);
                            out.println(s);
                        }
//                        case (1): //create new account
//                        {
//                          String USER_ID = in.readLine();
//                          String password = in.readLine();
//                          
//                          /*ADD USER ID AND PASSWORD TO THE DATABASE*/

//                        case (2): {
//                            String item_ID = in.readLine();
//                            String quantity = in.readLine();
//                            /*check at DATABASE*/
//                            if ("123".equals(item_ID) && "4".equals(quantity)) {
//                                out.println("Added");
//                            } else {
//                                out.println("Not added");
//
//                            }
//                        }
//                        case (3): {
//                            String item_ID = in.readLine();
//                            String quantity = in.readLine();
//                            /*check at DATABASE*/
//                            if ("123".equals(item_ID) && "4".equals(quantity)) {
//                                out.println("Edited");
//                            } else {
//                                out.println("Not edited");
//
//                            }
//                        }
//                        case (4): {
//                            String item_ID = in.readLine();
//                            String quantity = in.readLine();
//                            /*check at DATABASE*/
//                            if ("123".equals(item_ID) && "4".equals(quantity)) {
//                                out.println("Removed");
//                            } else {
//                                out.println("Not removed");
//                            }
//                        }
                        case (5): { //view balance
                            String id = in.readLine();
                            Functions f = new Functions();
                            out.println(f.case5(id));
                            /*check at DATABASE for the password and display info*/
                        }
                        case (6): {

                            String USER_ID = in.readLine();
                            int USER_balance = 4000;
                            int totalprice = 0;

                            int array_size = Integer.parseInt(in.readLine());
                            ArrayList<String> items_ID = new ArrayList<>();
                            ArrayList<String> quantity = new ArrayList<>();
                            ArrayList<String> price = new ArrayList<>();

                            for (int i = 0; i < array_size; i++) {

                                items_ID.add(in.readLine());
                                quantity.add(in.readLine());

                            }

                            /*database will fill the array of prices*/
//                            price.add("20");
//                            price.add("30");
//                            price.add("40");
//                            
                            for (int i = 0; i < array_size; i++) {

                                totalprice += Integer.parseInt(price.get(i)) * Integer.parseInt(quantity.get(i));

                            }

                            /*check the DATABASE for the available cash*/
                            if (USER_balance >= totalprice) {

                                out.println("okay");

                                /*remove this items from database*/
                                //decrease balance in database
                            } else {
                                out.println("not okay");

                            }

                        }
                        case (7): {//search
                            String iname = in.readLine();
                            /*check the DATABASE for the item name*/
                            Functions f = new Functions();
                            out.println(f.case7(iname));    
                        }
                        case (8): {//deposit
                            String id = in.readLine();
                            String amount = in.readLine();
                            Functions f = new Functions();
                            out.println(f.case8(id,amount)); 
                        }
                        case(9):{
                            String iname=in.readLine();
                            //database check
                            Functions f = new Functions();
                            ArrayList<String> IP = new ArrayList<>();
                            f.case9(iname,IP);
                            out.println(IP.get(0));//id
                            out.println(IP.get(1));//price

                            
                            

//                            out.println();
                        
                        }
                            

                        default: {
                        }

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
