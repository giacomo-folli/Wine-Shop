package com.example.gestorevini;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread{
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //TODO: implement server logic here

            //TODO: Register new user
            //TODO: Login
            //TODO: Search wine/customer/order
            //TODO: Update customer data
            //TODO: Delete customer/wine

            socket.close();

        } catch (IOException ex) {
            //TODO: handle  run() exception
        }
    }
}


