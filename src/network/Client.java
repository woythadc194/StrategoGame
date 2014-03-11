package network;

/*
 * Author: DLYAN WOYTHAL
 */

import java.util.*;
import java.io.*;
import java.net.*;

public class Client{

    public static void main(String argv[]) throws Exception {

        String input;
        String returned;

		@SuppressWarnings("resource")
		Scanner inFromUser = new Scanner( System.in );
        Socket clientSocket = new Socket("localhost", 6789);

        while(true){
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.print( "CLIENT Input: ");
            input = inFromUser.nextLine();
            
            outToServer.writeBytes(input + '\n');
            returned = inFromServer.readLine();
            System.out.println("CLIENT Received: " + returned);
            if( returned.equals( "EXIT" ) )
            	break;
        }
            clientSocket.close();
    }
}