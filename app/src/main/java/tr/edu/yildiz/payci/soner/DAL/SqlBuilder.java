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

        String sql = String.format("CREATE TABLE %s ( ", tableName);


        Iterator iterator = createSpec.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry keyValuePair = (Map.Entry)iterator.next();
            String joinerStr = !iterator.hasNext() ? " %s %s, " : " %s %s );";
            joiner.add(String.format(joinerStr, keyValuePair.getKey(), keyValuePair.getValue()));
            iterator.remove();
        }

        sql += joiner.toString();
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
                String joinerStr = !iterator.hasNext() ? " %s=%d, " : " %s=%d;";
                joiner.add(String.format(joinerStr, keyValuePair.getKey(), keyValuePair.getValue()));
                iterator.remove();
            }
            sql += joiner.toString();
        }
        return sql;
    }

    @Override
    public String BuildInsertCommand() {
        return null;
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
