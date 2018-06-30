package np.com.prathapa.digitaldaybook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;

import java.util.ArrayList;

import np.com.prathapa.digitaldaybook.model.DaybookNameModel;

import static np.com.prathapa.digitaldaybook.util.StringClass.NAME_TAG;
import static np.com.prathapa.digitaldaybook.util.StringClass.PASSWORD;
import static np.com.prathapa.digitaldaybook.util.StringClass.USERNAME;

public class RegistrationActivity extends AppCompatActivity {
    Context context = RegistrationActivity.this;
    EditText mDaybookName;
    EditText mPassword;
    EditText mConfirmPassword;
    EditText mNoOfPeople;
    Button mRegisterAccount;
    int oldNumber = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ActiveAndroid.initialize(context);
        mDaybookName = findViewById(R.id.editTextRegistrationDaybookName);
        mPassword = findViewById(R.id.editTextRegistrationPassword);
        mConfirmPassword = findViewById(R.id.editTextRegistrationConfirmPassword);
        mNoOfPeople = findViewById(R.id.editTextRegistrationNoOfPeople);
        mRegisterAccount = findViewById(R.id.buttonRegisterRegister);
        registerAccount();
        addingNamesOfPeople();
    }

    private void registerAccount() {
        mRegisterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String daybookName = String.valueOf(mDaybookName.getText());
                String password = String.valueOf(mPassword.getText());
                String confirmPassword = String.valueOf(mConfirmPassword.getText());
                ArrayList<String> personNameList = new ArrayList<String>();
                int number = getNoOfPeople();
                if (number == 0) {
                    showSnackbar("No of people must be greater than '1'");
                } else {
                    for (int i = 0; i < number; i++) {
                        EditText et = findViewById(i);
                        String personName = String.valueOf(et.getText());
                        personNameList.add(personName);
                    }
                    if (password.equals(confirmPassword)) {
                        System.err.println("Name: " + daybookName);
                        System.err.println("Password: " + password);
                        StringBuilder names = new StringBuilder();
                        for (String s : personNameList) {
                            names.append(s).append(",");
                        }
                        System.err.println("Person Names: " + names);
                        saveRegisteredAccountToDb(daybookName, password, String.valueOf(names));
                    } else {
                        showSnackbar("Password do not match! Please Re-enter!!");
                    }
                }
            }
        });
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERNAME, "user");
        editor.putString(PASSWORD, "pass");
        editor.apply();


    }

    private void saveRegisteredAccountToDb(String daybookName, String password, String names) {
        DaybookNameModel daybookNameModel = new DaybookNameModel();
        daybookNameModel.daybookName = daybookName;
        daybookNameModel.password = password;
        daybookNameModel.personNames = names;
        try{
            daybookNameModel.save();
        }catch (Exception e){
            showSnackbar("Daybook name already registered!! Please use other names!");
        }
    }

    private void addingNamesOfPeople() {
        mNoOfPeople.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int number = getNoOfPeople();
                if (oldNumber != number) {
                    removeView();
                    oldNumber = number;
                    addEditTextField(number);
                }
            }
        });
    }

    private void removeView() {
        LinearLayout ll = findViewById(R.id.linearLayoutRegistration);
        for (int i = 0; i < ll.getChildCount(); i++) {
            try {
                EditText et = (EditText) ll.getChildAt(i);
                String tag = (String) et.getTag();
                if (tag != null) {
                    if (tag.contains(NAME_TAG)) {
                        ll.removeView(et);
                    }
                }
            } catch (ClassCastException ignored) {
            }
        }
    }

    private void addEditTextField(int number) {
        LinearLayout ll = findViewById(R.id.linearLayoutRegistration);
        for (int i = 0; i < number; i++) {
            EditText et = new EditText(context);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            et.setLayoutParams(p);
            et.setId(i);
            et.setTag(NAME_TAG);
            et.setHint("Person Name");
            ll.addView(et);
        }
    }

    private void showSnackbar(String message) {
        Snackbar.make(findViewById(R.id.linearLayoutRegistration), message, Snackbar.LENGTH_LONG).show();
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    private int getNoOfPeople() {
        String value = String.valueOf(mNoOfPeople.getText());
        int number;
        if (value.equals("")) {
            number = 0;
        } else {
            number = Integer.valueOf(value);
        }
        return number;
    }

}
