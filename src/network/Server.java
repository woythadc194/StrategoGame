package network;

/*
 * Author: DLYAN WOYTHAL
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server extends Thread{

	private static ArrayList<BufferedReader> clientInList;
	private static ArrayList<DataOutputStream> clientOutList;
	
    public static void main(String argv[]) throws Exception{
        @SuppressWarnings("unused")
		Server svr = new Server();
    }
	
    public Server() throws Exception{
    	ServerSocket welcomeSocket = new ServerSocket(6789);
		
        Socket connectionSocket = welcomeSocket.accept();

        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        clientInList.add( inFromClient );
        
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        clientOutList.add( outToClient );
                
        while(true){
            String lineReceived = inFromClient.readLine();
            System.out.println("SERVER Received: " + lineReceived);
            System.out.print("SERVER Reply: ");
            
            outToClient.writeBytes( "" + '\n' );
        }
    }
}
