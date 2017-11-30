import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String PREF_SAVE = "color"; //String to save
    private static final String PREF_SAVE2 = "color_text";
    private TextView textView;
    private EditText editText;
    private Button buttonSave;
    private Button buttonsave_text;
    private Saver saver; //call Saver Class
    private RelativeLayout rel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //collect ids from layout
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        buttonSave = (Button) findViewById(R.id.buttonSave); //get the saved name from preferences
        buttonsave_text = (Button) findViewById(R.id.buttonSave_text);
        rel = (RelativeLayout) findViewById(R.id.background_call);
        //call Saver.java class
        saver = Saver.getInstance(this); //start instance
        String savedcolor = saver.getString(PREF_SAVE); //collect the set String
        String savedtextcolor = saver.getString(PREF_SAVE2);
        if(savedcolor == null) { //check to see if the String is empty
            rel.setBackgroundColor(Color.parseColor("#ffffff")); //set the color from collected String
        } else {
            textView.setText("Your background color is : #"+savedcolor);
            rel.setBackgroundColor(Color.parseColor("#"+savedcolor)); //set the color from collected String
        }
        if (savedtextcolor == null) {
            textView.setTextColor(Color.parseColor("#000000"));
        } else {
            textView.setTextColor(Color.parseColor("#"+savedtextcolor));
        }

    }

    public void textColor(View i) {
        String textColor = editText.getText().toString();
        if(textColor.trim().equals("")) {
            //do something
        } else {
            saver = Saver.getInstance(this); // call saver instance
            saver.saveString(PREF_SAVE2, textColor); //save the String
            textView.setText("Your text color is : #"+textColor);
            textView.setTextColor(Color.parseColor("#"+textColor));
        }

    }

    public void bgColor(View v) {
        String color = editText.getText().toString();
        if(color.trim().equals("")) {
            //do something
        }
        else {
            saver = Saver.getInstance(this); // call saver instance
            saver.saveString(PREF_SAVE, color); //save the String
            textView.setText("Your background color is : #"+color);
            rel.setBackgroundColor(Color.parseColor("#"+color));
        }
    }

    public void clearColor(View view)
    {
        boolean ok = saver.clearColor(); //clear the string
        if(ok == true) { //check if its worked.
            Toast.makeText(this, "Cleared", Toast.LENGTH_LONG).show();
            rel.setBackgroundColor(Color.WHITE); //set background color to default.
            textView.setTextColor(Color.BLACK);
            textView.setText("");
        }
        else
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
    }
}
