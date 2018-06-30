package np.com.prathapa.digitaldaybook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.activeandroid.ActiveAndroid;

import java.util.ArrayList;

import np.com.prathapa.digitaldaybook.main_activity.MainActivity;
import np.com.prathapa.digitaldaybook.model.DaybookName;
import np.com.prathapa.digitaldaybook.model.DaybookNameModel;

import static np.com.prathapa.digitaldaybook.util.StringClass.DAYBOOK_NAME;

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
                    if (name.equals(e.daybookName) && password.equals(e.password)) {
                        Intent intent = new Intent(context, MainActivity.class);
                        DaybookName daybookName = new DaybookName();
                        daybookName.setDaybookName(e.daybookName);
                        ArrayList<String> personNameList = new ArrayList<>();
                        StringBuilder personName = new StringBuilder();
                        for (int i = 0; i < e.personNames.length(); i++) {
                            char character = e.personNames.charAt(i);
                            if (character == ',') {
                                personNameList.add(personName.toString());
                                personName = new StringBuilder();
                            } else {
                                personName.append(character);
                            }
                        }
                        daybookName.setPersonNames(personNameList);
                        intent.putExtra(DAYBOOK_NAME, daybookName);
                        startActivity(intent);
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
