package com.example.netw707;

import android.arch.lifecycle.MutableLiveData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.netw707.databinding.ActivityMainBinding;

import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MutableLiveData<String> response =  new MutableLiveData<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        getApplicationContext(),
                        "button clicked",
                        Toast.LENGTH_LONG
                ).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Socket clientSocket = new Socket("IP", 2222);
                            String modifiedSentence = "tt";
                            response.postValue(modifiedSentence);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        response.observe(this,new Observer() {
            @Override
            public void onChanged(Object o) {
                Toast.makeText(
                        getApplicationContext(),
                        o.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
     }
    }

