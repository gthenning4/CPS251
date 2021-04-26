package com.ebookfrenzy.contactsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.ebookfrenzy.contactsproject.ui.main.FindName;
import com.ebookfrenzy.contactsproject.ui.main.MainFragment;
import com.ebookfrenzy.contactsproject.ui.main.AddName;
import com.ebookfrenzy.contactsproject.ui.main.SortNamesAZ;
import com.ebookfrenzy.contactsproject.ui.main.SortNamesZA;

public class MainActivity extends AppCompatActivity {
    public static EditText contactNameMain;
    public static EditText contactNumberMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(myToolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle bundle = new Bundle();
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Link5:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, SortNamesZA.newInstance())
                        .commitNow();
                return true;
            case R.id.Link4:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, SortNamesAZ.newInstance())
                        .commitNow();
                return true;
            case R.id.Link3:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, MainFragment.newInstance())
                        .commitNow();
                return true;
            case R.id.findName:
                contactNameMain = findViewById(R.id.contactNameMain);
                String nameStr = contactNameMain.getText().toString();

                bundle.putString("name",nameStr);
                FindName fn = new FindName();
                fn.setArguments(bundle);
                if (!nameStr.equals("")){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fn)
                            .commitNow();
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence message = "You must enter a value in the add name field.";
                    int duration = Toast.LENGTH_SHORT;
                    TextView toastText = new TextView(context);

                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast,
                            (ViewGroup) findViewById(R.id.toast_layout_root));

                    ImageView image = (ImageView) layout.findViewById(R.id.image);
                    image.setImageResource(R.drawable.frown);
                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText(message);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP, 0, 275 );
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                }

                return true;
            case R.id.addName:

                contactNameMain = findViewById(R.id.contactNameMain);
                nameStr = contactNameMain.getText().toString();

                contactNumberMain = findViewById(R.id.contactNumberMain);
                String numberStr = contactNumberMain.getText().toString();
                if(!nameStr.equals("") && !numberStr.equals("")){
                    bundle.putString("name",nameStr);
                    bundle.putString("number",numberStr);
                    AddName an = new AddName();
                    an.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, an)
                            .commitNow();
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence message = "Enter a name and a number";
                    int duration = Toast.LENGTH_SHORT;
                    TextView toastText = new TextView(context);

                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast,
                            (ViewGroup) findViewById(R.id.toast_layout_root));

                    ImageView image = (ImageView) layout.findViewById(R.id.image);
                    image.setImageResource(R.drawable.frown);
                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText(message);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.TOP, 0, 275 );
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}