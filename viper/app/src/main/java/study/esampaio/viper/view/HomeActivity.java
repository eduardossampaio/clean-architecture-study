package study.esampaio.viper.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import study.esampaio.entities.User;
import study.esampaio.viper.R;

public class HomeActivity extends AppCompatActivity {
    private User user;
    private TextView welcomeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        welcomeTextView = findViewById(R.id.welcome_text);

       user = getIncomingUser();
       if(user!=null){
            showWelcomeMessage(user);
        }
    }
    private User getIncomingUser(){
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
           return (User) extras.getSerializable("USER_KEY");
        }
        return null;
    }
    private void showWelcomeMessage(User  user){
        welcomeTextView.setText("Welcome: "+user.getName());
    }
}
