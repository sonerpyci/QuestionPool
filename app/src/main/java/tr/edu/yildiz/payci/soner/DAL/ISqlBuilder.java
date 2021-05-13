package tr.edu.yildiz.payci.soner.DAL;

import java.util.HashMap;

public interface ISqlBuilder {

    public String BuildCreateTableCommand(String tableName, HashMap<String, String> createSpec);

    public String BuildUpdateTableCommand();

    public String BuildDeleteTableCommand();

    public String BuildSelectCommand();

    public String BuildCreateCommand();

    public String BuildUpdateCommand();

    public String BuildDeleteCommand();







}
