package com.example.grzegorzbetlej.ticktask.dto;

import android.provider.BaseColumns;

public final class TaskHeaderModel {

    private Integer id;
    private String name;
    private String description;

    public TaskHeaderModel(){}

    public static class TaskHeaderEntry implements BaseColumns {
        public static final String TABLE_NAME = "task";
        public static final String COLUMN_NAME_TITLE = "name";
        public static final String COLUMN_NAME_SUBTITLE = "description";
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String descripton ) {
        this.description = descripton;
    }
}
