package tr.edu.yildiz.payci.soner.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;

import tr.edu.yildiz.payci.soner.R;

public class UserBase {

    private ArrayList<Person> userList;

    public UserBase() {
        userList = new ArrayList<Person>();

        userList.add( new Person("alice", "123654", R.drawable.ic_launcher_background));
        userList.add( new Person("bob", "123", R.drawable.ic_launcher_background));
        userList.add( new Person("charlotte", "", R.drawable.ic_launcher_background));
        userList.add( new Person("emma", "", R.drawable.ic_launcher_background));
        userList.add( new Person("frank", "", R.drawable.ic_launcher_background));
        userList.add( new Person("henry", "", R.drawable.ic_launcher_background));
        userList.add( new Person("james", "1234a", R.drawable.ic_launcher_background));
        userList.add( new Person("john", "123654", R.drawable.ic_launcher_background));
        userList.add( new Person("oliver", "", R.drawable.ic_launcher_background));
        userList.add( new Person("olivia", "", R.drawable.ic_launcher_background));
        userList.add( new Person("rory", "123456", R.drawable.ic_launcher_background));
        userList.add( new Person("soner", "123", R.drawable.ic_launcher_background));
        userList.add( new Person("william", "", R.drawable.ic_launcher_background));
    }


    public ArrayList<Person> GetUserList() {
        return userList;
    }

    public Boolean AddNewUser(Person person) {
        try {
            this.userList.add(person);
            return true;
        } catch (Exception E) {
            return false;
        }

    }


    public static String convertStringToSHA256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            final StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                final String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
