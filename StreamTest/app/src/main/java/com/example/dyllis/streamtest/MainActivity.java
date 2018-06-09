package com.example.dyllis.streamtest;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dyllis.streamtest.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView txtRead;
    final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/fff.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRead = (TextView)findViewById(R.id.txtRead);
    }

    public void mOnFileRead(View v){
        String read = ReadTextFile(filePath);
        txtRead.setText(read);
    }


    //경로의 텍스트 파일읽기
    public String ReadTextFile(String path){
        StringBuffer strBuffer = new StringBuffer();
        try{
            InputStream is = new FileInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line="";
            while((line=reader.readLine())!=null){
                strBuffer.append(line+"\n");
            }

            reader.close();
            is.close();
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
        return strBuffer.toString();
    }
}

