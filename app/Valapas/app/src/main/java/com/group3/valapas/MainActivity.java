package com.group3.valapas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.group3.valapas.ApiHandler.ApiCallbacks.IUploadedImagesCallback;
import com.group3.valapas.ApiHandler.ApiHandler;
import com.group3.valapas.BrowsePages.BrowsePage;
import com.group3.valapas.Models.Company;
import com.group3.valapas.Models.CompanyBuilder;
import com.group3.valapas.RegisterPages.Register;
import com.group3.valapas.UserPages.UserBrowse;
import com.group3.valapas.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements IUploadedImagesCallback
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectLogin(View v)
    {
        Intent i = new Intent (this, UserBrowse.class);
        startActivity(i);
    }

    public void selectRegister(View v)
    {
        Intent i = new Intent (this, Register.class);
        startActivity(i);
    }

    public void selectBrowse(View v)
    {
        Intent i = new Intent (this, BrowsePage.class);
        startActivity(i);
    }

    public void pickImages(View view)
    {
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    100);
            return;
        }

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (requestCode == 1 & resultCode == RESULT_OK)
        {
            List<Bitmap> bitmaps = new ArrayList<>();

            ClipData clipData =  data.getClipData();

            if (clipData != null)
            {
                for (int i = 0; i < clipData.getItemCount(); i++)
                {
                    Uri imageUri = clipData.getItemAt(i).getUri();

                    try {
                        InputStream is = getContentResolver().openInputStream(imageUri);

                        Bitmap bitmap = BitmapFactory.decodeStream(is);

                        bitmaps.add(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            else
            {
                Uri imageUri = data.getData();

                try {
                    InputStream is = getContentResolver().openInputStream(imageUri);

                    Bitmap bitmap = BitmapFactory.decodeStream(is);

                    bitmaps.add(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

            List<File> files = new ArrayList<>();

            for (int i = 0; i < bitmaps.size(); i++) {

                Log.d("AAA", "\nimage " + i);
                //create a file to write bitmap data
                File f = new File(getCacheDir(), "image" + i);
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Bitmap newBitmap = bitmaps.get(i);
                //Convert bitmap to byte array
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                byte[] bitMapData = bos.toByteArray();


                //write the bytes in file
                FileOutputStream fos = null;
                try {
                    Log.d("AAA", "cacat");
                    fos = new FileOutputStream(f);
                    fos.write(bitMapData);
                    fos.flush();
                    fos.close();
                    Log.d("AAA", "cacat2");


                    files.add(f);
                    Log.d("AAA", "added to araylist ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Log.d("AAA", "filesize: " + files.size());
            Log.d("AAA", files.get(0).getPath());

            File[] files1 = new File[files.size()];
            files.toArray(files1);

            Company company = new CompanyBuilder()
                    .id("5e9881fe2eed7172a049046d")
                    .email("abc")
                    .password("123")
                    .name("abc")
                    .description("123")
                    .images(files1)
                    .address("abc")
                    .postalCode("abc")
                    .location("abc")
                    .city("abc")
                    .country("abc")
                    .buildCompany();

            ApiHandler.uploadImage(this, company, this);
        }
    }

    @Override
    public void uploadedImages() {
        Log.d("AAA", "uploadedImages" );
    }
}