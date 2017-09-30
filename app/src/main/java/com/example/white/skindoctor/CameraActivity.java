package com.example.white.skindoctor;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    ImageButton camera_init;
    ImageButton upload_pic;
    public static final int REQUEST_TAKE_PHOTO=0;
    public static final int REQUEST_PIC_PHOTO=1;


    public static final int MEDIA_TYPE_IMAGE=4;
    public static final int MEDIA_TYPE_VIDEO=5;

    private Uri mMediaUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        upload_pic = (ImageButton) findViewById(R.id.upload_pic);

        upload_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code for uploading image goes here
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_PIC_PHOTO);
            }
        });

        camera_init= (ImageButton) findViewById(R.id.camera_image);
        camera_init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMediaUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);


                Log.d("Sucess","Sucess");
                if (mMediaUri==null){
                    Toast.makeText(CameraActivity.this,"error accesing your file system",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mMediaUri);
                    startActivityForResult(intent, REQUEST_TAKE_PHOTO);
                }
            }
        });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view);
        navigationView1.setItemIconTintList(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            if (requestCode==REQUEST_PIC_PHOTO){
                //upload to server code;
            }
        }
    }

    private Uri getOutputMediaFileUri(int mediaType) {
        if (isExSDcardAvailable()){
            File mediaStorageDir = getExternalFilesDir(Environment.DIRECTORY_DCIM);

            String fileName = "";
            String fileType = "";
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            if (mediaType == MEDIA_TYPE_IMAGE) {
                fileName = "Case_"+ timeStamp;
                fileType = ".jpg";
            } else if(mediaType == MEDIA_TYPE_VIDEO) {
                fileName = "VID_"+ timeStamp;
                fileType = ".mp4";
            } else {
                return null;
            }
            File mediaFile;
            try {
                mediaFile=File.createTempFile(fileName,fileType,mediaStorageDir);
                Log.d("Camera activity","File: "+Uri.fromFile(mediaFile));
                return Uri.fromFile(mediaFile);
            }
            catch (IOException e){
                Log.e("CameraAct","Error Creating file");
            }

        }
        return null;
    }

    private boolean isExSDcardAvailable(){
        String state= Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        else
            return false;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_first_layout) {

            Intent intent = new Intent(this,DoctorLoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_second_layout) {
            Intent intent = new Intent(this,SecondFragment.class);
            startActivity(intent);

        } else if (id == R.id.our_doctors) {
            Intent intent = new Intent(this,OurDoctors.class);
            Log.d("test","pass");
            startActivity(intent);

        }  else if (id == R.id.nav_share) {
            Intent intent = new Intent(this,aboutUs.class);
            startActivity(intent);


        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(this,ContactUs.class);
            startActivity(intent);

        }

        else if (id == R.id.home) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
