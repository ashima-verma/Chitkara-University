package com.example.dell.chitkarauniversity.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dell on 4/23/2017.
 */

public class ChitkaraContract {
    public static final String AUTHORITY ="com.example.dell.chtkarauniversity.app";
    public static final Uri CONTENT_URI =Uri.parse("content://" +AUTHORITY);

    private ChitkaraContract(){}

    public static final class ChitkaraStudent implements BaseColumns{

        public static final String TABLE_NAME ="chitkara";

        public static final String _ID =BaseColumns._ID;

        public static final String NAME_COLUMN ="name";

        public  static final String ROLLNO_COLUMN ="rollno";

        public static final String GENDER_COLUMN ="gender";

        public static final String MARKS_COLUMN ="marks";

        public static final String MALE_GENDER ="Male";

        public static final String FEMALE_GENDER ="Female";

        public static final String UNKNOWN_GENDER ="Unknown";
    }
}
