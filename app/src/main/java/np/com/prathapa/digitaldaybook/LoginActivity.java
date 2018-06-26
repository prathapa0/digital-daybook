package np.com.prathapa.digitaldaybook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import static np.com.prathapa.digitaldaybook.util.StringClass.PASSWORD;
import static np.com.prathapa.digitaldaybook.util.StringClass.USERNAME;

public class LoginActivity extends AppCompatActivity {
    Context context = LoginActivity.this;
    EditText mDaybookName;
    EditText mPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDaybookName = findViewById(R.id.editTextDaybookName);
        mPassword = findViewById(R.id.editTextPassword);
        login();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if(preferences.getString(USERNAME,null) != null){
            startActivity(new Intent(context,MainActivity.class));
        }
    }

    private void login() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERNAME,"user");
        editor.putString(PASSWORD,"pass");
        editor.apply();
    }

}
