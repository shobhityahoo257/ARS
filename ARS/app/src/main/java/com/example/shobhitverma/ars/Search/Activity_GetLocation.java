package com.example.shobhitverma.ars.Search;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shobhitverma.ars.R;

public class Activity_GetLocation extends AppCompatActivity {
    private LocationManager lm;
    private LocationListener ll;
    private Button mb;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(Activity_GetLocation.this,"Starting Activity",Toast.LENGTH_LONG).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__get_location);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        mb=(Button)findViewById(R.id.button);
        ll = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                String s=location.getLatitude()+ " " + location.getLongitude();
                Toast.makeText(Activity_GetLocation.this,"hi shobhit"+ s,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            Toast.makeText(Activity_GetLocation.this,"If part",Toast.LENGTH_LONG).show();
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET},10 );
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        else
        {
            Toast.makeText(Activity_GetLocation.this,"Else part",Toast.LENGTH_LONG).show();
            configureButton();
        }

    }
    private void configureButton()
    {
        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.requestLocationUpdates("gps", 2000, 0, (LocationListener) ll);

            }
        });
       }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case 10:
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) ;
            {

                configureButton();
                Toast.makeText(Activity_GetLocation.this,"P Granted",Toast.LENGTH_LONG).show();

            }
            return;
        }

    }
}
