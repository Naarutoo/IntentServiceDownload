package com.example.intentserviceand03;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaParser;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class DownloadService extends IntentService {



    public DownloadService() {
        super("DownloadService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("lloyd","oncreate");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("lloyd","onhandleIntent"+ "runs on"+ Thread.currentThread().getName());
        downloadfile();
    }

    private void downloadfile() {
try {
    File directory = new File(getFilesDir() + File.separator + "Vogella");

    if (!directory.exists()) {
        directory.mkdir();
    }


File file = new File(directory,"vogella.html");

    if (!file.exists()){
        file.createNewFile();
    }

    URL url = new URL("https://www.vogella.com/index.html");

    InputStream inputStream = url.openConnection().getInputStream();
    InputStreamReader reader = new InputStreamReader(inputStream);
    FileOutputStream writer =  new FileOutputStream(file,true);
    int data = reader.read();
    while (data!=-1){
        char ch =(char) data;
        writer.write(ch);
        data = reader.read();
    }
    FileInputStream fileInputStream = new FileInputStream(file);
    int fileData = fileInputStream.read();
    StringBuffer stringBuffer = new StringBuffer();
    while (fileData!=-1){
        char ch = (char)fileData;
        stringBuffer = stringBuffer.append(ch);
        fileData = fileInputStream.read();
    }
}



catch (Exception e){

}
    }
}