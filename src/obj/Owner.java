package obj;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Owner {
    private static int NEXT_ID = 1;
    private final int id;
    private String first_name;
    private String last_name;
    private int age;

    public Owner(String first_name, String last_name, int age) {
        this.id = NEXT_ID++;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
    }

    public Owner() {
        this.id = NEXT_ID++;
        this.first_name = "John";
        this.last_name = "Doe";
        this.age = 0;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                        "id='" + id + '\'' +
                        ", first_name='" + first_name + '\'' +
                        ", last_name='" + last_name + '\'' +
                        ", age='" + age +
                        "'}";
    }

    // This method also helps to keep the id in order
    public static Owner toOwner(String s){
        // Trim the string to exclude the userId (it will get incremented automatically)
        // and to also exclude the {}
        s = s.substring(8, s.length() - 1);

        // Regex to get the values of the attributes
        Pattern pattern = Pattern.compile("'([^']*)'");
        Matcher matcher = pattern.matcher(s);

        // List to hold the extracted values
        ArrayList<String> val = new ArrayList<>();

        // We will add all the matched values to an array list in the correct order
        while (matcher.find() && val.size() < 4) {
            val.add(matcher.group(1));
        };

        // Preparing the attributes for the return
        String first_name = val.get(0);
        String last_name = val.get(1);
        int age = Integer.parseInt(val.get(2));

        // Return a new object of type owner, created from the string
        return new Owner(first_name, last_name, age);
    }
}
