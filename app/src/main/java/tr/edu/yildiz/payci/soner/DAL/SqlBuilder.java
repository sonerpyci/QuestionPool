package tr.edu.yildiz.payci.soner.DAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringJoiner;

public class SqlBuilder implements ISqlBuilder {
    @Override
    public String BuildCreateTableCommand(String tableName, HashMap<String, String> createSpec ) {

        /*
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
            "(" +
                KEY_USER_ID + " INTEGER PRIMARY KEY," +
                KEY_FIRST_NAME + " TEXT," +
                KEY_LAST_NAME + " TEXT," +
                KEY_USERNAME + " TEXT," +
                KEY_PASSWORD + " TEXT," +
                KEY_AVATAR + " TEXT," +
                KEY_EMAIL + " TEXT," +
                KEY_PHONE + " TEXT," +
                KEY_DATE_OF_BIRTH + " TEXT" +
            ")";


        * */
        StringJoiner joiner = new StringJoiner(" ,");

        String sql = String.format("CREATE TABLE %s ", tableName);

        String keysSection = "( %s );";

        Iterator iterator = createSpec.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry keyValuePair = (Map.Entry)iterator.next();
            String joinerStr = " %s %s ";
            joiner.add(String.format(joinerStr, keyValuePair.getKey(), keyValuePair.getValue()));
            iterator.remove();
        }

        sql += String.format(keysSection, joiner.toString());
        System.out.println(String.format("SQL : %s", sql));
        return sql;
    }

    @Override
    public String BuildUpdateTableCommand() {
        return null;
    }

    @Override
    public String BuildDeleteTableCommand() {
        return null;
    }

    @Override
    public String BuildSelectCommand(String tableName, ArrayList<String> selectColumns, HashMap<String, String> whereParams) {
        String selectColumnsSql = String.join(", ", selectColumns) + " ";
        String sql = String.format("SELECT %s FROM %s ", selectColumnsSql, tableName);

        if (whereParams != null) {
            sql += " WHERE";
            StringJoiner joiner = new StringJoiner(" AND");
            Iterator iterator = whereParams.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry keyValuePair = (Map.Entry)iterator.next();
                String joinerStr = !iterator.hasNext() ? " %s='%s' " : " %s='%s';";
                joiner.add(String.format(joinerStr, keyValuePair.getKey(), keyValuePair.getValue().toString()));
                iterator.remove();
            }
            sql += joiner.toString();
        }
        return sql;
    }

    @Override
    public String BuildInsertCommand(String tableName, ArrayList<String> insertSpec) {
        /*
        String CREATE_USERS_TABLE = "INSERT INTO TABLE_USERS" +
            "(KEY_FIRST_NAME, KEY_LAST_NAME, KEY_USERNAME, KEY_PASSWORD, KEY_AVATAR, KEY_EMAIL, KEY_PHONE, KEY_DATE_OF_BIRTH, KEY_AVATAR)" +
            "VALUES (?, ?, ?, ....);
        */

        StringJoiner keysJoiner = new StringJoiner(", ");
        StringJoiner valuesJoiner = new StringJoiner(", ");

        String sql = String.format("INSERT INTO %s ", tableName);

        String keysSection = " ( %s )";
        String valuesSection = " VALUES ( %s );";

        for (String key: insertSpec) {
            keysJoiner.add(key);
            valuesJoiner.add("?");
        }

        sql += String.format(keysSection, keysJoiner.toString()) + " " + String.format(valuesSection, valuesJoiner.toString());

        System.out.println(String.format("SQL : %s", sql));
        return sql;
    }

    @Override
    public String BuildUpdateCommand() {
        return null;
    }

    @Override
    public String BuildDeleteCommand(String tableName, HashMap<String, Integer> whereParams) {

        return null;
    }
}
