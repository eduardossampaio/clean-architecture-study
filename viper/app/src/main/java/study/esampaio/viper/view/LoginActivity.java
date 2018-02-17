package study.esampaio.viper.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import study.esampaio.viper.R;
import study.esampaio.viper.contracts.login.LoginPresenter;
import study.esampaio.viper.contracts.login.LoginView;
import study.esampaio.viper.contracts_impl.login.LoginPresenterImpl;
import study.esampaio.entities.User;

public class LoginActivity extends AppCompatActivity implements LoginView {

    LoginPresenter loginPresenter = new LoginPresenterImpl(this);

    private Dialog loadingDialog;

    private Button loginButton;
    private EditText emailInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.button_login);
        emailInput = findViewById(R.id.input_email);
        passwordInput = findViewById(R.id.input_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
    }
    private void showLoadingDialog(String message){
        loadingDialog = ProgressDialog.show (this,"Loading",message,true);
    }
    private void hideLoadingDialog(){
        loadingDialog.dismiss();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(loginPresenter != null) {
            loginPresenter.onDestroy();
            loginPresenter= null;
        }
    }

    private void doLogin(){
        String email = emailInput.getText().toString();
        String password= passwordInput.getText().toString();
        showLoadingDialog("Doing login, please wait");
        loginPresenter.onLoginButtonPressed(email,password);
    }

    @Override
    public void goToHomeScreen(User user) {
        hideLoadingDialog();
        Intent intent = new Intent(this,HomeActivity.class);
        intent.putExtra("USER_KEY",user);
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        hideLoadingDialog();
        Toast.makeText(this, "Error: "+message, Toast.LENGTH_SHORT).show();
    }
}
