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

import static np.com.prathapa.digitaldaybook.util.StringClass.PASSWORD;
import static np.com.prathapa.digitaldaybook.util.StringClass.USERNAME;

public class LoginActivity extends AppCompatActivity {
    Context context = LoginActivity.this;
    EditText mDaybookName;
    EditText mPassword;
    Button mButtonRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDaybookName = findViewById(R.id.editTextLoginDaybookName);
        mPassword = findViewById(R.id.editTextLoginPassword);
        mButtonRegister = findViewById(R.id.buttonLoginRegister);
        login();
        register();
    }

    private void login() {

    }

    private void register() {
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,RegistrationActivity.class));
            }
        });
    }

}
