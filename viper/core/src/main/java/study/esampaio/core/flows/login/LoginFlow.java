package study.esampaio.core.flows.login;

import study.esampaio.interactors.login.LoginInteractor;
import study.esampaio.interactors.login.LoginInteractorOutput;
import study.esampaio.entities.User;

/**
 * Created by eduar on 16/02/2018.
 */

public class LoginFlow implements LoginInteractor {
    private LoginInteractorOutput loginInteractorOutput;

    public LoginFlow(LoginInteractorOutput loginInteractorOutput) {
        this.loginInteractorOutput = loginInteractorOutput;
    }

    @Override
    public void doLogin(final String login, final String password) {
        //do the login

        new Thread(new Runnable() {
            @Override
            public void run() {
                login(login,password);
            }
        }).start();

    }

    private void login(String login, String password){
        try{
            Thread.sleep(2000);
        }catch (Exception e){}

        boolean success = login!=null && password !=null && login.contains("@");

        if( loginInteractorOutput != null){
            if(success) {
                User user = new User(login.substring(0,login.indexOf("@")), login);
                saveUserToDatabase(user);
                loginInteractorOutput.onLoginSuccess(user);
            }else{
                loginInteractorOutput.onLoginError("Username or password wrong");
            }
        }
    }

    private void saveUserToDatabase(User  user){
        //save to database
    }
}
