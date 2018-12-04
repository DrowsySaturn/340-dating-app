package com.datingapp.attempt12;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.datingapp.client.controllers.logincontroller.LoginController;
import com.datingapp.client.controllers.profilecontroller.ProfileController;
import com.datingapp.client.controllers.signupcontroller.SignUpController;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    protected LinearLayout parent;
    protected Button button;
    protected ImageButton imageButton;
    protected View space;
    protected TextView text;
    protected LinearLayout bar;
    protected EditText emailSubmit;
    protected EditText userNameSubmit;
    protected EditText passwordSubmit;
    protected EditText ageSubmit;
    protected Spinner sexSubmit;
    protected EditText nameSubmit;
    protected HorizontalScrollView lineSpace;
    protected String login = "login";
    protected int SIZE = 20;
    protected LoginController loginController;
    protected SignUpController signUpController;
    protected ProfileController profileController;


    protected LinearLayout.LayoutParams vidView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    /**
     ** @param _savedInstanceState
     *
     *  11/20/2018
     * When Application is opened loads the main menu.
     *
     */
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        loadMain(space);
    }

    /**
     * @param _view
     *
     * 11/23/2018
     * The buttons to delete videos are set to visible or invisible
     * depending on weather or not the add video buttons are visible.
     *
     */
    public void deleteVideo(View _view) {
        findViewById(R.id.deleteVideo).setVisibility(findViewById(R.id.deleteVideo).getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        if(findViewById(R.id.addVideo1).getVisibility()== View.VISIBLE)
            findViewById(R.id.deleteVideo1).setVisibility(View.INVISIBLE);
        else
            findViewById(R.id.deleteVideo1).setVisibility(View.VISIBLE);
        if(findViewById(R.id.addVideo2).getVisibility()== View.VISIBLE)
            findViewById(R.id.deleteVideo2).setVisibility(View.INVISIBLE);
        else
            findViewById(R.id.deleteVideo2).setVisibility(View.VISIBLE);
        if(findViewById(R.id.addVideo3).getVisibility()== View.VISIBLE)
            findViewById(R.id.deleteVideo3).setVisibility(View.INVISIBLE);
        else
            findViewById(R.id.deleteVideo3).setVisibility(View.VISIBLE);
        if(findViewById(R.id.addVideo4).getVisibility()== View.VISIBLE)
            findViewById(R.id.deleteVideo4).setVisibility(View.INVISIBLE);
        else
            findViewById(R.id.deleteVideo4).setVisibility(View.VISIBLE);
        if(findViewById(R.id.addVideo5).getVisibility()== View.VISIBLE)
            findViewById(R.id.deleteVideo5).setVisibility(View.INVISIBLE);
        else
            findViewById(R.id.deleteVideo5).setVisibility(View.VISIBLE);
    }

    /**
     *
     * @param _view
     *
     * 12/3/2018
     * Setting up controllers for login when login button is pressed sends
     * the string values in email and password to login.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void getLogin(View _view){
        emailSubmit = findViewById(R.id.editEmail);
        passwordSubmit = findViewById(R.id.editPassword);
        try {
            loginController.login(emailSubmit.toString(), passwordSubmit.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     * @param _view
     *
     * 11/23/2018
     * When Register button is first pressed, sets up screen for registering user.
     *
     * When Register button is pressed again, checks all values filled and sends
     * the data to the register controller.
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("ResourceAsColor")
    public void getRegister(View _view){
        if(findViewById(R.id.editAge).getVisibility()==View.INVISIBLE){
            findViewById(R.id.addVideo).setVisibility(View.VISIBLE);
            findViewById(R.id.editName).setVisibility(View.VISIBLE);
            findViewById(R.id.editAge).setVisibility(View.VISIBLE);
            findViewById(R.id.sexSpinner).setVisibility(View.VISIBLE);
            //findViewById(R.id.loginButton).setOnClickListener(this::loadProfile);
            button = findViewById(R.id.loginButton);
            button.setText("cancel");
            button.setOnClickListener(this::loadProfile);
        }
        else {
            boolean register = true;
            emailSubmit = findViewById(R.id.editEmail);
            passwordSubmit = findViewById(R.id.editPassword);
            nameSubmit = findViewById(R.id.editName);
            ageSubmit = findViewById(R.id.editAge);
            sexSubmit = findViewById(R.id.sexSpinner);
            if (emailSubmit.getText().length() < 1) {
                emailSubmit.setBackgroundResource(R.color.red);
                register = false;
            }
            if (passwordSubmit.getText().length() < 1) {
                passwordSubmit.setBackgroundResource(R.color.red);
                register = false;
            }
            if (nameSubmit.getText().length() < 1) {
                nameSubmit.setBackgroundResource(R.color.red);
                register = false;
            }
            if (ageSubmit.getText().length() < 1) {
                ageSubmit.setBackgroundResource(R.color.red);
                register = false;
            }
            if((sexSubmit.getSelectedItem().toString()=="Sex")){
                sexSubmit.setBackgroundResource(R.color.red);
                register = false;
            }
            if(register == true) {
                try {
                    signUpController.signUp(emailSubmit.toString(), passwordSubmit.toString());
                    profileController.createProfile(Integer.parseInt(ageSubmit.getText().toString()), nameSubmit.toString(), nameSubmit.toString(),"Test");
                }
                catch (Exception e){
                    e.printStackTrace();
                    findViewById(R.id.editAge).setVisibility(View.INVISIBLE);
                    getRegister(_view);
                }
                try {
                    loginController.login(emailSubmit.toString(), passwordSubmit.toString());
                }catch(Exception e){
                    e.printStackTrace();
                }
                loadMain(_view);
            }


        }

    }
    /**
     * @param _view
     *11/10/2018
     *Loads main menu layout upon button event.
     **/
    public void loadMain(View _view){
        setContentView(R.layout.main_menu);
        if((login == "login")) {
            findViewById(R.id.SearchButton).setBackgroundColor(getResources().getColor(R.color.grey));
            findViewById(R.id.SearchButton).setClickable(false);
            findViewById(R.id.MatchButton).setClickable(false);
            findViewById(R.id.MatchButton).setBackgroundColor(getResources().getColor(R.color.grey));
        }
    }


    /**
     * @param _view
     *11/10/2018
     *Loads matches layout upon button event.
     **/
    public void loadMatches(View _view){
        try {
            //data = match.loadRandomProfiles(emailSubmit.toString(), sexSubmit.toString());
        }catch(Exception e) {
            e.printStackTrace();
        }


        setContentView(R.layout.find_person);
        parent = findViewById(R.id.scroll);

        for(int i = 0; i < SIZE; i++){
            if(i % 2 == 1){
                space = new View(MainActivity.this);
                space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                space.setId(i);
                space.getLayoutParams().width = 50;
                bar.addView(space);
            }
            else{
                bar = new LinearLayout(MainActivity.this);
                bar.setId(i);
                bar.setHorizontalScrollBarEnabled(true);
                parent.addView(bar);
            }

            button = new Button(MainActivity.this);
            button.setLayoutParams(vidView);
            button.setId(i);
            button.setTag(1);
            //button.setText(data.get(i).getPersonalMessage());
            button.getLayoutParams().width = 500;
            button.getLayoutParams().height = 250;
            bar.addView(button);
        }
    }


    /**
     * @param _view
     *11/10/2018
     *Loads profile layout upon button event.
     **/
    public void loadProfile(View _view){
        setContentView(R.layout.profile);
        if((login == "login")) {
            findViewById(R.id.newUser).setVisibility(View.VISIBLE);
            findViewById(R.id.editPassword).setVisibility(View.VISIBLE);
            findViewById(R.id.addVideo).setVisibility(View.INVISIBLE);
            findViewById(R.id.editName).setVisibility(View.INVISIBLE);
            findViewById(R.id.editAge).setVisibility(View.INVISIBLE);
            findViewById(R.id.sexSpinner).setVisibility(View.INVISIBLE);
            findViewById(R.id.ageText).setVisibility(View.INVISIBLE);
            findViewById(R.id.nameText).setVisibility(View.INVISIBLE);
            findViewById(R.id.sexText).setVisibility(View.INVISIBLE);
            findViewById(R.id.ratingText).setVisibility(View.INVISIBLE);
            findViewById(R.id.videoView2).setVisibility(View.INVISIBLE);
            findViewById(R.id.videoView3).setVisibility(View.INVISIBLE);
            findViewById(R.id.videoView4).setVisibility(View.INVISIBLE);
            findViewById(R.id.videoView5).setVisibility(View.INVISIBLE);
            findViewById(R.id.deleteVideos).setVisibility(View.INVISIBLE);
        }
        else {
            findViewById(R.id.addVideo1).setVisibility((login == "login") ? View.VISIBLE : View.INVISIBLE);
            findViewById(R.id.addVideo2).setVisibility((login == "login") ? View.VISIBLE : View.INVISIBLE);
            findViewById(R.id.addVideo3).setVisibility((login == "login") ? View.VISIBLE : View.INVISIBLE);
            findViewById(R.id.addVideo4).setVisibility((login == "login") ? View.VISIBLE : View.INVISIBLE);
            findViewById(R.id.addVideo5).setVisibility((login == "login") ? View.VISIBLE : View.INVISIBLE);
        }
    }


    /**
     * @param _view
     *11/10/2018
     *Loads recording layout upon button event.
     **/
    public void loadRecording(View _view){
        setContentView(R.layout.recording);
    }


    /**
     * @param _view
     *11/10/2018
     *as of this moment the int[] data is a test value upon connection with the model will modify accordingly.
     *
     *11/10/2018
     *Sets up the initial view within the find person layout.
     *
     *11/15/2018
     *On even groups sets up new horizontal bar and pushes to the vertical scroll bar, after
     * which it pushes the new clickable person.
     * On odds it will add a simple space to that horizontal bar before pushing a new clickable person.
     *
     *11/19/2018
     *This will setup all search sets later will be used by both normal searching and result of matches.
     *
     * 11/23/2018
     * Implemented the controller for pulling search
     **/
    public void loadSearch(View _view){
        try {
            //data.addAll(match.loadRandomProfiles(login, login));
        }catch(Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.find_person);
        parent = findViewById(R.id.scroll);

        for(int i = 0; i < SIZE; i++){
            if(i % 2 == 1){
                space = new View(MainActivity.this);
                space.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                space.setId(i);
                space.getLayoutParams().width = 50;
                bar.addView(space);
            }
            else{
                bar = new LinearLayout(MainActivity.this);
                bar.setId(i);
                bar.setHorizontalScrollBarEnabled(true);
                parent.addView(bar);
            }

            button = new Button(MainActivity.this);
            button.setLayoutParams(vidView);
            button.setId(i);
            button.setTag(1);
            //button.setText(data.get(i).getPersonalMessage().toString());
            button.getLayoutParams().width = 500;
            button.getLayoutParams().height = 250;
            bar.addView(button);
        }
    }


    /**
     * @param _view
     *11/10/2018
     *Loads settings layout upon button event.
     **/
    public void loadSettings(View _view){
        setContentView(R.layout.settings);
    }
}
