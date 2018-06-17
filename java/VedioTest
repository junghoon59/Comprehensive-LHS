package com.example.bluew.androidcamera;
import android.app.Activity;

import java.io.*;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import android.view.SurfaceHolder.Callback;
import android.view.*;
import android.widget.*;


public class VideoRecordActivity extends Activity implements Callback, View.OnClickListener {


    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    public MediaRecorder mrec = new MediaRecorder();



    File video;
    private Camera mCamera;
    boolean recording;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(null , "Video starting");


        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);



        mCamera = Camera.open();
        surfaceView.setClickable(true);
        surfaceView.setOnClickListener(this);

        Toast.makeText(this, "녹화시작/끝, 화면터치", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)	{
        menu.add(0, 0, 0, "StartRecording");
        menu.add(0, 1, 0, "StopRecording");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)	{
        switch (item.getItemId()) {
            case 0:
                try {
                    initRecording();
                    startRecording();
                } catch (Exception e) {
                    String message = e.getMessage();
                    Log.i(null, "Problem Start"+message);
                    mrec.release();
                }

                break;

            case 1:

                mrec.stop();
                mrec.release();
                mrec = null;
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecording() throws IllegalStateException, IOException {
        if(mrec==null)mrec = new MediaRecorder();  // Works well
        mCamera.unlock();
        mrec.setCamera(mCamera);
        mrec.setPreviewDisplay(surfaceHolder.getSurface());
        mrec.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mrec.setAudioSource(MediaRecorder.AudioSource.MIC);
        mrec.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_LOW));
        String savePath = Environment.getExternalStorageDirectory()+"/DCIM/Camera";
        mrec.setOutputFile(savePath+"/video-test.mp4");
        mrec.setMaxDuration(50000); // 50 seconds
        mrec.setMaxFileSize(5000000); // Approximately 5 megabytes
        //mrec.setVideoSize(480, 320);
        //mrec.setVideoFrameRate(15);
        mrec.prepare();
    }

    protected void startRecording() throws IOException {
        mrec.start();
    }
    @Override
    public void onClick(View v) {
        if (recording) {
            mrec.stop();
            recording = false;
        } else {
            recording = true;
            try{
                initRecording();
                mrec.start();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    protected void stopRecording() {
        mrec.stop();
        mrec.release();
        mCamera.release();
    }

   private void releaseMediaRecorder(){
        if (mrec != null) {
            mrec.reset();   // clear recorder configuration
            mrec.release(); // release the recorder object
            mrec = null;
            mCamera.lock();           // lock camera for later use
        }
    }

    private void releaseCamera(){
        if (mCamera != null){
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mCamera != null){
            Parameters params = mCamera.getParameters();
            mCamera.setParameters(params);
        }
        else {
            Toast.makeText(getApplicationContext(), "Camera not available!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mrec!=null) mrec.release();
        mrec = null;
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }
}
