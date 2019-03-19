package com.dimas9009.apismssender;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketProvider {

    private ServerSocket serverSocket;
    private Thread serverThread = null;
    private MainActivity context;

    public SocketProvider(MainActivity context) {
        this.context = context;
    }

    public void StartServerCommunication(Integer serverPort){

        try {
            UiProvider.ShowMessageOnUi(context, "Starting listen message");
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.printStackTrace();
            // Print some error text
            return;
        }

        this.serverThread = new Thread(new ServerThread(context, serverSocket));
        this.serverThread.start();
    }

    public void StopServerCommunication(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ParseAndExecuteAction(String message) throws JSONException {

        ExecutionRule execRule = new ExecutionRule();

        JSONObject jObject = new JSONObject(message);
        execRule.Action = SmsAction.valueOf(jObject.getString("Action"));
        if (execRule.Action == SmsAction.SendSms) {
            execRule.Message = jObject.getString("Message");
        }
        execRule.PhoneNumber = jObject.getString("PhoneNumber");


    }


}
