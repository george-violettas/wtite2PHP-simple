package com.example.tutorial_clean;

import android.os.AsyncTask;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Syn extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String ... params) {
        if(params.length>0){
            if(params[0].equals("syn")){
                onSyn();
            }else if(params[0].equals("insert")){
               return onInsert(params);
            }
        }
        return null;
    }
    private String onInsert(String ... params){
        /* BE VERY CAREFUL WITH THE CORRECT IP */
        String phpPageULR="http://10.1.0.91:8888/sqli/insertData.php";
        try {
            // preparing the URL for the connection
            URL url=new URL(phpPageULR);
            // open a channel between the client(android device) and the server (PHP)
            HttpURLConnection channel=(HttpURLConnection) url.openConnection();
            // specify what do you need post or get method
            channel.setRequestMethod("POST");
            channel.setDoOutput(true);

            // create a sub-channel inside the channel to specify weither you want to write or read
            // output means write, input means read
            OutputStream subChannel=channel.getOutputStream();
            // create a pen to write in a specific information and in which language should this pen write.
            OutputStreamWriter pen=new OutputStreamWriter(subChannel,"UTF-8");
            // create a object to start write the information
            BufferedWriter student =new BufferedWriter(pen);
            // information to write
            String user = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
            String pass = URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");
            String information=user+"&"+pass;
            // student will start writing the information
            student.write(information);
            // student will push the information from the client side to the server side
            student.flush();
            // student finished his job
            student.close();
            System.out.println(params[1]);
            // closing the sub-channel
            subChannel.close();

            InputStream serverResponse = channel.getInputStream();
            serverResponse.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Trying to Insert data to MySQL";
    }
    private void onSyn(){

        System.out.println("INSERTING DATA");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}

