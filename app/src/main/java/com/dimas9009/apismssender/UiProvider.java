package com.dimas9009.apismssender;

public class UiProvider {

    public static void ShowMessageOnUi(final MainActivity context, final String message){
        context.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                context.TextView.setText(message);
            }
        });
    }
}
