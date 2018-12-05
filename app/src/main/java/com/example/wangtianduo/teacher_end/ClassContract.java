package com.example.wangtianduo.teacher_end;

import android.provider.BaseColumns;

/**
 * Created by norman_lee on 6/10/17.
 */

public class ClassContract {

    //TODO 7.1 Examine the static inner classes. No coding is needed

    //TODO 7.2 Prevent Instantiation of this Contract class

    private ClassContract() { }

    public static final class ClassEntry implements BaseColumns {

        public static final String TABLE_NAME = "Chara";
        public static final String COL_NAME = "name";
        public static final String COL_SESSION = "session";
        public static final String COL_DATE = "date";
        public static final String COL_TIMING = "timing";
        public static final String COL_VENUE = "venue";
//        public static final String COL_FILE = "file";

    }

    public static final class ClassSql {

        public static String SPACE = " ";
        public static String COMMA = ",";

        public static String SQL_CREATE_TABLE = "CREATE TABLE" + SPACE
                + ClassEntry.TABLE_NAME + SPACE + "("
                + ClassEntry._ID + SPACE + "INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA
                + ClassEntry.COL_NAME + SPACE + "TEXT NOT NULL" + COMMA
                + ClassEntry.COL_SESSION + SPACE + "TEXT NOT NULL" + COMMA
                + ClassEntry.COL_DATE + SPACE + "TEXT NOT NULL" + COMMA
                + ClassEntry.COL_TIMING + SPACE + "TEXT NOT NULL" + COMMA
                + ClassEntry.COL_VENUE + SPACE + "TEXT NOT NULL" +");";

        public static String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + ClassEntry.TABLE_NAME;

        public static String SQL_QUERY_ONE_RANDOM_ROW = "SELECT * FROM" + SPACE
                + ClassEntry.TABLE_NAME + SPACE
                + "ORDER BY RANDOM() LIMIT 1";

        public static String SQL_QUERY_ALL_ROWS = "SELECT * FROM " + ClassEntry.TABLE_NAME;

    }
}
