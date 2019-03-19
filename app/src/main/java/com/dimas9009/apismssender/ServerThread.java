package com.dimas9009.apismssender;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable {

    private ServerSocket serverSocket;
    private MainActivity context;

    public ServerThread(MainActivity context, ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        this.context = context;
    }

    public void run() {
        Socket socket = null;

        while (!Thread.currentThread().isInterrupted()) {

            try {

                socket = serverSocket.accept();

                CommunicationThread commThread = new CommunicationThread(context, socket);
                new Thread(commThread).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
