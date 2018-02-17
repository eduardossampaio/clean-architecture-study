package study.esampaio.viper.contracts.login;

import study.esampaio.entities.User;

/**
 * Created by eduar on 16/02/2018.
 */
//fonte : https://cheesecakelabs.com/blog/using-viper-architecture-android/

public interface LoginView {

    void goToHomeScreen(User user);

    void showError(String message );
}
