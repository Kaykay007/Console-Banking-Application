package org.example.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread{
    private Socket socket;
    private ObjectOutputStream bufferedWriter;
    private ObjectInputStream bufferedReader;

    public void chatWithCustomerCareAgent(){
        try {
            this.socket = new Socket("localhost" , 3455);
            this.bufferedWriter = new ObjectOutputStream(socket.getOutputStream());
            this.bufferedReader = new ObjectInputStream(socket.getInputStream());

            String message = "";
            while (socket.isConnected()){
                Scanner input = new Scanner(System.in);

                System.out.print("Enter Your Message: ");
                message = input.nextLine();
                bufferedWriter.writeObject(message);
                System.out.println("Customer Care: " +bufferedReader.readObject());
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(){
        chatWithCustomerCareAgent();
    }
}