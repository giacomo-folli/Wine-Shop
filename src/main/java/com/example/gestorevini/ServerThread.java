package com.example.gestorevini;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
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

            while (true) {//TODO: implement server logic here
                //String line = in.nextLine();

                /*
                if (line.equals("SHOW_WINES")) {
                    //TODO: Show wines
                    System.out.println("Show wines handled");
                } else if (line.equals("SEARCH_WINE")) {
                    //TODO: Search wine
                } else if (line.equals("SEARCH_ORDER")) {
                    //TODO: Search order
                } else if (line.equals("SEARCH_CUSTOMER")) {
                    //TODO: Search customer
                } else if (line.equals("GET_HELP")) {
                    //TODO: Get help
                } else if (line.equals("UPDATE_CUSTOMER_DATA")) {
                    //TODO: Update customer data
                } else if (line.equals("UPDATE_WINE_DATA")) {
                    //TODO: Update wine data
                }
                */

            }
        } catch (IOException e) {
            //TODO: handle  run() exception
        }
    }
}


