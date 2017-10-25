package strathmore.com.lab3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Databasehandler db = new Databasehandler(this);
        /**
         * CRUD Operations
         * */
        //Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contacts("Osumba", "098763337"));
        db.addContact(new Contacts("Jojo", "0788998765"));
        db.addContact(new Contacts("Samanyulas", "0987668998"));
        db.addContact(new Contacts("Legi", "8763637292"));

        Log.d("Insert:", "Inserting..");
        db.addNewspaper(new Newspaper("Daily Nation"));
        db.addNewspaper(new Newspaper("Business Daily"));
        db.addNewspaper(new Newspaper("The standard"));
        db.addNewspaper(new Newspaper("East African"));


        //Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        Log.d("Reading:",  "Reading all newspapers..");
        List<Contacts> contacts = db.getAllContacts();
        List<Newspaper> newspaper = db.getAllNewspaper();

        for (Contacts cn : contacts) {
            String log = "Id: " +cn.getId()+" ,Name: " + cn.getName() + " ,Phone:"
                    +cn.getPhoneNumber();
            //Writing contacts to log
            Log.d("Name: ", log);

        }
        for (Newspaper cn : newspaper) {
            String log = "Id: " +cn.getNewspaper_id()+" ,Name: " + cn.getNewspaper_name()
                    ;
            //Writing contacts to log
            Log.d("Name: ", log);


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}