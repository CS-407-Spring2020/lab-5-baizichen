package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {
    int noteid = -1;

    public void saveMethod(View view){

        EditText text = (EditText) findViewById(R.id.editText4);
        String content = text.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase db = context.openOrCreateDatabase("notes",
                Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(db);

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1) {
            title = "NOTE_" + (Main2Activity.notes.size() +1);
            dbHelper.saveNotes(username, title, content, date);
        } else {
            title = "NOTE_" + (noteid +1);
            dbHelper.updateNotes(title, date, content, username);
        }

        Intent intent2 = new Intent(this, Main2Activity.class);
        intent2.putExtra("Message", username);
        startActivity(intent2);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        EditText text = (EditText) findViewById(R.id.editText4);
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid", -1);
        if (noteid != -1) {
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();
            text.setText(noteContent);
        }
    }
}
