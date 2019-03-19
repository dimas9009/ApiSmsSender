package com.dimas9009.apismssender;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class CommunicationThread implements Runnable {

    private Socket clientSocket;
    private MainActivity context;

    private BufferedReader input;

    public CommunicationThread(MainActivity context, Socket clientSocket) {

        this.clientSocket = clientSocket;
        this.context = context;

        try {
            this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            try {

                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = input.readLine()) != null)
                {
                    sb.append(line + "\n");
                }

                sendResponse("TestREsponse");

                UiProvider.ShowMessageOnUi(context, "Message from server");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendResponse (String response)
    {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(this.clientSocket.getOutputStream())),
                    true);
            out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
