package com.project.mhack8.mhk8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.project.mhack8.mhk8.MainActivity;
import com.project.mhack8.mhk8.R;
import com.project.mhack8.mhk8.ReserveActivity;

/**
 * Created by eric on 2016/10/8.
 */
public class CommonActivity extends AppCompatActivity {

    public static int PAGE_ID = 0;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.sHome:
                changeScheme(0);
                return true;
            case R.id.sRequest:
                changeScheme(1);
                return true;
            case R.id.sResponse:
                changeScheme(2);
                return true;
            case R.id.sSafeMap:
                changeScheme(3);
                return true;
            case R.id.sProfile:
                changeScheme(4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void setButton(final int id, final int pageId){

        final Button button = (Button) findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Button Clicked: ", String.valueOf(pageId));
                changeScheme(pageId);
            }
        });

    }

    public void changeScheme(int pageId){

        PAGE_ID = pageId;
        Intent intent;
        Log.v("Jump to: ", String.valueOf(PAGE_ID));
        switch (pageId){
            case 0: intent = new Intent(this, MainActivity.class);
                    intent.putExtra("page id", PAGE_ID);
                    startActivity(intent);
                    break;

            case 1: intent = new Intent(this, ReserveActivity.class);
                    intent.putExtra("page id", PAGE_ID);
                    startActivity(intent);
                    break;

<<<<<<< HEAD
            case 2: intent = new Intent(this, ListActivity.class);
                intent.putExtra("page id", PAGE_ID);
                startActivity(intent);
                break;

=======
            case 3: intent = new Intent(this, MapsActivity.class);
                    intent.putExtra("page id", PAGE_ID);
                    startActivity(intent);
                    break;
>>>>>>> afb394c1a9b0c195b664708f28e44465e7238a1e

            case 4: intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("page id", PAGE_ID);
                startActivity(intent);
                break;


            default:Log.v("Page: ", "No Response");
                    break;
        }
    }

}
