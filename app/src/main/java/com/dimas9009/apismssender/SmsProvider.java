package com.dimas9009.apismssender;

import android.database.Cursor;
import android.telephony.SmsManager;

public class SmsProvider {


    public ReceivedSms GetLastSms(String phoneNumber, Cursor cursor)  {
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                ReceivedSms sms = ParseSmsFromCursor(cursor);
                if (sms.PhoneNumber.equals(phoneNumber))
                {
                    return sms;
                }
            } while (cursor.moveToNext());
        } else {
            return null;
        }

        return null;
    }

    public void SendSms (String phoneNumber, String textBody)
    {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, textBody, null, null);
        } catch (Exception e) {
        }
    }

    private ReceivedSms ParseSmsFromCursor(Cursor cursor) {

        ReceivedSms result = new ReceivedSms();
        result.Message = cursor.getString(13);
        result.PhoneNumber = cursor.getString(3);
        result.Id = cursor.getInt(0);
        return result;
    }
}
