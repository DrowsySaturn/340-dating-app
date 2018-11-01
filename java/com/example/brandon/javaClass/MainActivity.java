package com.example.brandon.javaClass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {
LinearLayout parent;
Button b;
View space;
HorizontalScrollView lineSpace;
LinearLayout.LayoutParams vidView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }
    public void loadSearch(View view)
    {
        int[] data = new int[20];
        setContentView(R.layout.find_person);
        //find_person v = new find_person();
        parent = (LinearLayout)findViewById(R.id.find_person_layout);
        //data = v.generateList(parent, view);

        //parent = (LinearLayout)findViewById(R.id.find_person_layout);
       for(int i=0;i< data.length;i++)
        {


                b = new Button(MainActivity.this);
                b.setLayoutParams(vidView);
                b.setId(i);
                b.setText("" + (i + 1));
                b.setTag(1);
                b.getLayoutParams().width=500;
                b.getLayoutParams().height=250;
                parent.addView(b);
                if(i%2 == 0)
                {
                    space = new View(MainActivity.this);
                    space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    space.setId(i);
                    space.getLayoutParams().width=50;
                    parent.addView(space);
                }
                else
                {

                    lineSpace = new HorizontalScrollView(MainActivity.this);
                    lineSpace.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    lineSpace.getLayoutParams().height=50;
                    lineSpace.setId(i);
                    parent.addView(lineSpace);
                }


        }

    }

    public void loadMatches(View view)
    {
        setContentView(R.layout.matches);

    }

    public void loadProfile(View view)
    {
        setContentView(R.layout.profile);
    }

    public void loadRecording(View view)
    {
        setContentView(R.layout.recording);
    }

    public void loadMain(View view)
    {
        setContentView(R.layout.main_menu);
    }
    public void loadSettings(View view)
    {
        setContentView(R.layout.settings);
    }

}
