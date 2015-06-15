package abc;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 3/12/15
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class SerilizationDemo {

    public static void main(String[] args) throws Exception {
        Person person = new Person("Firstname", "Lastname", 30);

        FileOutputStream fos = new FileOutputStream("/Users/ashok.agarwal/dev/github/HBaseTest1/tempdata.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(person);
        oos.close();

        FileInputStream fis = new FileInputStream("/Users/ashok.agarwal/dev/github/HBaseTest1/tempdata.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Person ted = (Person) ois.readObject();
        ois.close();


        System.out.println(ted.getFirstName());
//        System.out.println(ted.getSpouse().getFirstName());

        // Clean up the file
//        new File("tempdata.ser").delete();

    }
}
class Person
        implements java.io.Serializable
{
    public Person(String fn, String ln, int a)
    {
        this.firstName = fn; this.lastName = ln; this.age = a;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public Person getSpouse() { return spouse; }

    public void setFirstName(String value) { firstName = value; }
    public void setLastName(String value) { lastName = value; }
    public void setAge(int value) { age = value; }
    public void setSpouse(Person value) { spouse = value; }

    public String toString()
    {
        return "[Person: firstName=" + firstName +
                " lastName=" + lastName +
                " age=" + age +
                " spouse=" + spouse.getFirstName() +
                "]";
    }

    public void writeObject(Object obj){
        System.out.println("Called writeObject");
    }

    private String firstName;
    private String lastName;
    private int age;
    private Person spouse;

}