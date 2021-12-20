package com.example.sensoresembebidos;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

    private Button btnOn;
    private Button btnOff;
    private TextView txtStatus;
    private BluetoothAdapter bluetoothAdapter;
    private boolean bluetooth_soportado = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--> Begin
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        btnOn = (Button)findViewById(R.id.btnOn);
        btnOff = (Button)findViewById(R.id.btnOff);
        btnOn.setOnClickListener( this );
        btnOff.setOnClickListener( this );

        //se optiene dispositivo Bluetooth
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if( bluetoothAdapter == null ) {
            bluetooth_soportado=false;
            txtStatus.setText("Status: Bluetooth no soportado en este dispositivo");
        }else{
            txtStatus.setText("Status: - ");
        }
        //--> End

    }


    @Override
    public void onClick(View v) {

        if( bluetooth_soportado ){
            switch ( v.getId() )
            {
                case R.id.btnOn :
                    if ( !bluetoothAdapter.isEnabled() ) {
                        Intent turnOnIntent = new Intent( BluetoothAdapter.ACTION_REQUEST_ENABLE );
                        startActivityForResult( turnOnIntent, 1 );
                        txtStatus.setText("Status: Bluetooth ACTIVADO");
                    }else{
                        txtStatus.setText("Status: Bluetooth YA ESTA ACTIVADO");
                    }
                    break;
                case R.id.btnOff:
                    bluetoothAdapter.disable();
                    txtStatus.setText("Status: DESACTIVADO");
                    break;
            }

        }else{
            Toast.makeText(
                    getApplicationContext(),
                    "Bluetooth no soportado en este dispositivo",
                    Toast.LENGTH_LONG).show();
        }
    }

}