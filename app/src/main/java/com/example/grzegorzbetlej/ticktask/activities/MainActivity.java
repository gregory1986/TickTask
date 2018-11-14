package com.example.grzegorzbetlej.ticktask.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.grzegorzbetlej.ticktask.db.DbHelper;
import com.example.grzegorzbetlej.ticktask.R;
import com.example.grzegorzbetlej.ticktask.adapters.TaskAdapter;
import com.example.grzegorzbetlej.ticktask.dto.TaskHeaderModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private static TaskAdapter adapter;
    private ArrayList<TaskHeaderModel> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(getApplicationContext() );

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskHeaderModel.TaskHeaderEntry.COLUMN_NAME_TITLE, "Test 1");
        values.put(TaskHeaderModel.TaskHeaderEntry.COLUMN_NAME_SUBTITLE, "Testowa lista zadań");
        long newRowId = db.insert(TaskHeaderModel.TaskHeaderEntry.TABLE_NAME, null, values);

        listView=(ListView)findViewById(R.id.tasksList);


        db = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                TaskHeaderModel.TaskHeaderEntry.COLUMN_NAME_TITLE,
                TaskHeaderModel.TaskHeaderEntry.COLUMN_NAME_SUBTITLE
        };

// Filter results WHERE "title" = 'My Title'
        String selection = TaskHeaderModel.TaskHeaderEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "Test 1" };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                TaskHeaderModel.TaskHeaderEntry.COLUMN_NAME_TITLE + " DESC";

        Cursor cursor = db.query(
                TaskHeaderModel.TaskHeaderEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

items = new ArrayList<TaskHeaderModel>();
        while(cursor.moveToNext()) {

            TaskHeaderModel item = new TaskHeaderModel();
            item.setId(cursor.getInt(cursor.getColumnIndex(TaskHeaderModel.TaskHeaderEntry._ID)));
            item.setName(cursor.getString(cursor.getColumnIndex(TaskHeaderModel.TaskHeaderEntry.COLUMN_NAME_TITLE)));
            item.setDescription(cursor.getString(cursor.getColumnIndex(TaskHeaderModel.TaskHeaderEntry.COLUMN_NAME_SUBTITLE)));
            items.add(item);
        }
        cursor.close();

        adapter= new TaskAdapter(items ,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TaskHeaderModel dataModel= items.get(position);

                Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getDescription()+" API: "+dataModel.getId(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }
}
