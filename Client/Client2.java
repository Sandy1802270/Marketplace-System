
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ofaof
 */
public class Client2 {
    
    
       
    
static String send_ID_Password_Login(String userID,String password,PrintWriter out, BufferedReader in) throws IOException
{
    
    
            
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

static String send_ID_Password_CreateAccount(String userID,String password,String ConfirmPassword,PrintWriter out, BufferedReader in) throws IOException
{
    
       
 if(password.equals(ConfirmPassword))
 {

    // sending the FUNCTION ID to server
    out.println(String.valueOf(1));
    out.flush();
    
    // sending the user USERID to server
    out.println(userID);
    out.flush();
    // sending the user password to server
    out.println(password);
    out.flush();
    
    return "OKAY";
 }
 
 else 
 {
   // displaying server reply to check whether data sent are correct or not
    return "NOT OKAY";
 }
 
 
}

    
    

    
    
    
    // driver code
    public static void main(String[] args)
    {
        // establish a connection by providing host and port
        // number
        try (Socket socket = new Socket("localhost", 1234)) {
            
            // writing to server
            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);
  
            // reading from server
            BufferedReader in
                = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
  
//            // object of scanner class
//            Scanner sc = new Scanner(System.in);
//            String line = null;
//  
//            while (!"exit".equalsIgnoreCase(line)) {
//                
//                // reading from user
//                line = sc.nextLine();
//  
//                // sending the user input to server
//                out.println(line);
//                out.flush();
//  
//                // displaying server reply
//                System.out.println("Server replied "
//                                   + in.readLine());
//            }
//            
//            // closing the scanner object
//            sc.close();
        
    //addded
    while(true)
    {
System.out.println(send_ID_Password_Login("aboallgmail.com","1234",out,in));
    }   
        
        
        
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    

    
    
 
}
