package study.esampaio.viper.contracts_impl.login;

import android.os.Handler;
import android.os.Looper;

import study.esampaio.core.flows.login.LoginFlow;
import study.esampaio.interactors.login.LoginInteractor;
import study.esampaio.interactors.login.LoginInteractorOutput;
import study.esampaio.viper.contracts.login.LoginPresenter;
import study.esampaio.viper.contracts.login.LoginView;
import study.esampaio.entities.User;

/**
 * Created by eduar on 16/02/2018.
 */

public class LoginPresenterImpl implements LoginPresenter, LoginInteractorOutput {
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        this.loginInteractor = new LoginFlow(this);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onLoginButtonPressed(String userName, String password) {
        if(loginInteractor!=null) {
            loginInteractor.doLogin(userName, password);
        }
    }


    @Override
    public void onLoginSuccess(final User user) {
        runOnMainThread(new Runnable() {
            @Override
            public void run() {
                loginView.goToHomeScreen(user);
            }
        });

    }

    @Override
    public void onLoginError(final String message) {
        runOnMainThread(new Runnable() {
            @Override
            public void run() {
                loginView.showError(message);
            }
        });
    }

    private void runOnMainThread(Runnable runnable){
        Handler uiHandler = new Handler(Looper.getMainLooper());
        uiHandler.post(runnable);

    }
}
