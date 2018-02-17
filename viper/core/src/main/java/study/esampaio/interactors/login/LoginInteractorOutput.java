package study.esampaio.interactors.login;


import study.esampaio.entities.User;

/**
 * Created by eduar on 16/02/2018.
 */

public interface LoginInteractorOutput {

    public void onLoginSuccess(User user);

    public void onLoginError(String message);

}
