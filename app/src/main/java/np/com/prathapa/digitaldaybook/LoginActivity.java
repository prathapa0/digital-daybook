package np.com.prathapa.digitaldaybook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.activeandroid.ActiveAndroid;

import java.util.ArrayList;

import np.com.prathapa.digitaldaybook.main_activity.MainActivity;
import np.com.prathapa.digitaldaybook.model.DaybookNameModel;

import static np.com.prathapa.digitaldaybook.util.StringClass.PASSWORD;
import static np.com.prathapa.digitaldaybook.util.StringClass.USERNAME;

public class LoginActivity extends AppCompatActivity {
    Context context = LoginActivity.this;
    EditText mDaybookName;
    EditText mPassword;
    Button mButtonLogin;
    Button mButtonRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActiveAndroid.initialize(context);
        mDaybookName = findViewById(R.id.editTextLoginDaybookName);
        mPassword = findViewById(R.id.editTextLoginPassword);
        mButtonLogin = findViewById(R.id.buttonLoginLogin);
        mButtonRegister = findViewById(R.id.buttonLoginRegister);
        login();
        register();
    }

    private void login() {
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(mDaybookName.getText()).trim();
                String password = String.valueOf(mPassword.getText()).trim();
                ArrayList<DaybookNameModel> list = (ArrayList<DaybookNameModel>) new DaybookNameModel().getListOfDaybookNames();
                for (DaybookNameModel e : list) {
                    System.err.println(e.daybookName + " ---> " + e.password);
                    if (name.equals(e.daybookName) && password.equals(e.password)) {
                        startActivity(new Intent(context, MainActivity.class));
                    }
                }
            }
        });
    }

    private void register() {
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, RegistrationActivity.class));
            }
        });
    }

}
