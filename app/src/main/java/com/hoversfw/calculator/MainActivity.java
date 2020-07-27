package com.hoversfw.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int result;
    String currentInput="";
    String displayStatus="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView status=findViewById(R.id.status);
        final TextView input=findViewById(R.id.input);
        final Button sqrt=findViewById(R.id.sqrt);
        final Button sq=findViewById(R.id.sq);
        final Button back=findViewById(R.id.back);
        final Button add=findViewById(R.id.add);
        final Button sub=findViewById(R.id.subtract);
        final Button mult=findViewById(R.id.multi);
        final Button div=findViewById(R.id.div);
        final Button equal=findViewById(R.id.equal);
        final Button clear=findViewById(R.id.clear);
        final Button btn0=findViewById(R.id.btn0);
        final Button btn1=findViewById(R.id.btn1);
        final Button btn2=findViewById(R.id.btn2);
        final Button btn3=findViewById(R.id.btn3);
        final Button btn4=findViewById(R.id.btn4);
        final Button btn5=findViewById(R.id.btn5);
        final Button btn6=findViewById(R.id.btn6);
        final Button btn7=findViewById(R.id.btn7);
        final Button btn8=findViewById(R.id.btn8);
        final Button btn9=findViewById(R.id.btn9);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput="";
                displayStatus="";
                status.setText(displayStatus);
                input.setText(currentInput);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayStatus=displayStatus+currentInput+"+";
                currentInput="";
                status.setText(displayStatus);
                input.setText(currentInput);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayStatus=displayStatus+currentInput+"-";
                currentInput="";
                status.setText(displayStatus);
                input.setText(currentInput);
            }
        });
        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayStatus=displayStatus+currentInput+"*";
                currentInput="";
                status.setText(displayStatus);
                input.setText(currentInput);
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String equation=displayStatus+currentInput;
                if(!equation.equals("")){
                    Calculate c=new Calculate(equation);
                    displayStatus="";
                    currentInput=c.calculate(equation);
                    status.setText(displayStatus);
                    input.setText(currentInput);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput=currentInput.substring(0,currentInput.length()-1);
                input.setText(currentInput);
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput=currentInput+"0";
                input.setText(currentInput);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput=currentInput+"1";
                input.setText(currentInput);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput=currentInput+"2";
                input.setText(currentInput);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput=currentInput+"3";
                input.setText(currentInput);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput=currentInput+"4";
                input.setText(currentInput);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput=currentInput+"5";
                input.setText(currentInput);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput=currentInput+"6";
                input.setText(currentInput);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput=currentInput+"7";
                input.setText(currentInput);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput=currentInput+"8";
                input.setText(currentInput);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput=currentInput+"9";
                input.setText(currentInput);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setting:
                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                return true;
            case R.id.help:
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setTitle("Help")
                        .setMessage("There'll be FAQ section later!")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();
                return true;
        }
        return true;
    }
}