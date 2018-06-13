package com.example.dyllis.streamtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivty extends Activity{
    @Override
    protected void onCreate(Bundle savedlnstanceState) {
        super.onCreate(savedlnstanceState);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
