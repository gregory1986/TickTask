package com.example.grzegorzbetlej.ticktask.db;

import com.example.grzegorzbetlej.ticktask.dto.TaskHeaderModel;

public final class SQLqueries {

    public static final String SQL_CREATE_TABLE_TASK =
            "CREATE TABLE " + TaskHeaderModel.TaskHeaderEntry.TABLE_NAME + " (" +
                    TaskHeaderModel.TaskHeaderEntry._ID + " INTEGER PRIMARY KEY," +
                    TaskHeaderModel.TaskHeaderEntry.COLUMN_NAME_TITLE + " TEXT," +
                    TaskHeaderModel.TaskHeaderEntry.COLUMN_NAME_SUBTITLE + " TEXT)";
}
