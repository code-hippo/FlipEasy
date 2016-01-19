package com.example.tarunkota.appv2;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class ListProducts extends Activity {

    int counter;
    String title;
    TextToSpeech t1;
    private static final int SPEECH_REQUEST_CODE = 0;


    List<Node> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_products);
        counter =0;


        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        Log.i("title",title);
        //load the first element.
        TextView ta = (TextView)findViewById(R.id.textView3);

        Log.i("title",title);
        data d = new data();

        d.init();
        products = d.ct.getChildren(title);
        ta.setText(products.get(counter).Name);

        Log.i("title", title);



        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
                String toSpeak = title+"   ."+products.get(counter).Name;
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });




        LinearLayout gV=(LinearLayout) findViewById(R.id.llay);
        gV.setOnTouchListener(new OnSwipeTouchListener(this) {


            @Override
            public void onSwipeRight() {
                //moving between elements.
                TextView ta = (TextView) findViewById(R.id.textView3);


                if(counter>0)
                {
                    t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                t1.setLanguage(Locale.UK);
                            }
                            String toSpeak = products.get(counter).Name;
                            t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    });

                    counter--;
                }
                else {
                    t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                t1.setLanguage(Locale.UK);
                            }
                            String toSpeak = "thats all folks";
                            t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    });
                }

                ta.setText(products.get(counter).Name);
            }

            public void onSwipeLeft() {
                //moving between different elements.
                TextView ta = (TextView)findViewById(R.id.textView3);

                if(counter<products.size()-1)
                {
                    t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                t1.setLanguage(Locale.UK);
                            }
                            String toSpeak = products.get(counter).Name;
                            t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    });
                    counter++;}
                else {
                    t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                t1.setLanguage(Locale.UK);
                            }
                            String toSpeak = "thats all folks";
                            t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    });
                }
                ta.setText(products.get(counter).Name);
            }

            public void onSwipeUp() {
                //going to another activity depending on the present activity.
                Intent i = new Intent(getApplicationContext(), ListProducts.class);
                i.putExtra("title",products.get(counter).Name);
                i.putExtra("max","10");
                startActivity(i);
            }

            public void onSwipeDown() {
                //going above depending on the present title.


            }


        });


    }







    //variables required for listproducts




    }




