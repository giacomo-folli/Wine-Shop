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

            socket.close();

        } catch (IOException ex) {
            //TODO: handle  run() exception
        }
    }
}


