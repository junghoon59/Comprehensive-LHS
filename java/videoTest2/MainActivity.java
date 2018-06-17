package com.example.bluew.myapplication;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    //서피스 제어
    SurfaceHolder holder;
    // 음성 녹음 뿐만 아니라 동영상 녹음 가능
    MediaRecorder recorder ;
    String path="/sdcard/recored_video.mp4";
    Toast toast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView=(SurfaceView)findViewById(R.id.surfaceView);
        holder=surfaceView.getHolder();

    }


    //동영상 녹음시작
    public void onButton1Clicked(View v){

        try {
            recorder =new MediaRecorder();

            // 녹음기 설정
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //카메라 설정
            recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            //출력 형식 설정
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            // default 내장 default 형식으로 설정
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);

            recorder.setOutputFile(path);

            //화면에 어디에 뿌려질것인가 정보를 서피스 홀더 객체가 가져옴
            recorder.setPreviewDisplay(holder.getSurface());
            recorder.prepare();
            recorder.start();

            tostShow("녹화가 시작 되었습니다.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //동영상 녹음중지
    public void onButton2Clicked(View v){
        if(recorder!=null){
            recorder.stop();
            recorder.release();
            recorder=null;

            tostShow("녹화가 중지 되었습니다.");
        }
    }



    private void tostShow(String data){

        if(toast==null){
            toast=Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT);
        }else {
            toast.setText(data);
        }
        toast.show();
    }



}
