package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void sendIntent(SharedPreferences sharedPreferences) {
        EditText Username = (EditText) findViewById(R.id.editText);
        String Username_string = Username.getText().toString();
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("Message", Username_string);
        sharedPreferences.edit().putString("username", Username_string).apply();
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String usernameKey = "username";

        final SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String saved_username = sharedPreferences.getString(usernameKey, "");

        if (!saved_username.equals("")) {
            Intent intent = new Intent(this, Main2Activity.class);

            intent.putExtra("Message", saved_username);
            startActivity(intent);
        }else {
            setContentView(R.layout.activity_main);
            Button button = (Button) findViewById(R.id.button_id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendIntent(sharedPreferences);
                }
            });
        }


    }






}
