package study.esampaio.viper.contracts.login;

/**
 * Created by eduar on 16/02/2018.
 */

public interface LoginPresenter {

    public void onDestroy();

    public void onLoginButtonPressed(String userName,String password);

}
