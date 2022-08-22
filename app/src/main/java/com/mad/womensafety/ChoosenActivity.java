package com.mad.womensafety;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChoosenActivity extends AppCompatActivity {
    CardView instruction, testing;
    String prevStarted = "yesChoosen";
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_choosen );

        instruction = findViewById( R.id.inst );
        testing = findViewById(R.id.mag);

        instruction.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        } );

        testing.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getApplicationContext(), Magnetometer.class ) );
            }
        } );


    }


    @Override
    // main menu means logout button has created in actionbar.
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.instructions_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.magnetometer:
                final AlertDialog.Builder alert = new AlertDialog.Builder(ChoosenActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_dialog,null);

                Button btn_okay = (Button)mView.findViewById(R.id.btn_okay);
                alert.setView(mView);
                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                btn_okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            default:
                return false;

            case R.id.camera:
                final AlertDialog.Builder alert2 = new AlertDialog.Builder(ChoosenActivity.this);
                View mView2 = getLayoutInflater().inflate(R.layout.custom_dialog2,null);

                Button btn_okay2 = (Button)mView2.findViewById(R.id.btn_okay);
                alert2.setView(mView2);
                final AlertDialog alertDialog2 = alert2.create();
                alertDialog2.setCanceledOnTouchOutside(false);
                btn_okay2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog2.dismiss();
                    }
                });
                alertDialog2.show();
            default2:
                return false;

        }

    }

}