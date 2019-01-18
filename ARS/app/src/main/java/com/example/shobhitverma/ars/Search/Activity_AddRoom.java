package com.example.shobhitverma.ars.Search;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shobhitverma.ars.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Activity_AddRoom extends AppCompatActivity {
    private ImageButton mimageButton;
    private ProgressBar mpb;
    private static final int RESULT_LOAD_IMAGE=1;
    private StorageReference mref;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addroom);
        mimageButton=(ImageButton)findViewById(R.id.activity_addroom_imageButton);
        mpb=(ProgressBar)findViewById(R.id.activity_addroom_progressbar);
        mpb.setVisibility(View.INVISIBLE);
        mref=FirebaseStorage.getInstance().getReference();
        mimageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), RESULT_LOAD_IMAGE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK)
        {
            if(data.getClipData()!=null)
            {
                Toast.makeText(Activity_AddRoom.this,"Selected Multiple files",Toast.LENGTH_LONG).show();
                int c=data.getClipData().getItemCount();
                Uri fileUri;
                for(int i=0;i<c;i++)
                {
                 fileUri=data.getClipData().getItemAt(i).getUri();
                 String filename=getFileName(fileUri);
                 mref.child("roomimages").child(filename).putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                     @Override
                     public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                         Toast.makeText(Activity_AddRoom.this," file Uploaded",Toast.LENGTH_LONG).show();
                     }
                 }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    // Toast.makeText(Activity_AddRoom.this," file Uploaded",Toast.LEN
                     @Override
                     public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                         double p=taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount()*100;
                               mpb.setVisibility(View.VISIBLE);
                               mpb.setProgress((int)p);

                     }
                 });
                }
            }
            else if(data.getData()!=null)
            {
            Toast.makeText(Activity_AddRoom.this,"Selected Single file ",Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            Toast.makeText(Activity_AddRoom.this,"Please select Atleast one Image file ",Toast.LENGTH_LONG).show();

        }
        }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
    }

