package es.example.android.contentproviderexample;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView dictionaryWordsTextView = (TextView) findViewById(R.id.dictionaryWords);

        // Get the ContentResolver which will send a message to the ContentProvider
        ContentResolver resolver = getContentResolver();

        // Get a Cursor containing all of the rows in the Words table
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI, null, null, null, null);

        try {
            while(cursor.moveToNext()) {
                int idColumn = cursor.getColumnIndex(UserDictionary.Words._ID);
                int frequencyColumn = cursor.getColumnIndex(UserDictionary.Words.FREQUENCY);
                int wordColumn = cursor.getColumnIndex(UserDictionary.Words.WORD);
                int id = cursor.getInt(idColumn);
                int frequency = cursor.getInt(frequencyColumn);
                String word = cursor.getString(wordColumn);
                dictionaryWordsTextView.append(("\n" + id + " - " + frequency + " - " + word));
            }
        } catch(Exception e){

        }
        finally {
            // Always close your cursor to avoid memory leaks
            cursor.close();
        }
    }

}
