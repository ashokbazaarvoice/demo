package com.jbksoft.app;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Hello world!
 *
 */
public class App
{
    public String field;


    public boolean initialized;

    public static void main( String[] args )
    {
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();


        System.out.println( "Hello World!" );

        App a = new App();
        List<Object> list = Lists.newArrayList();
        a.doSomething_Guava(list);

        Person person = new Person("ram","ram@gmail.com","almighty", "0000");
        System.out.println(person);

        long millis = stopwatch.elapsedMillis();
        long nanos = stopwatch.elapsedTime(
                TimeUnit.NANOSECONDS);

        System.out.println(millis);
        System.out.println(nanos);

        System.out.println(Splitter.on(",").trimResults().split("Kurt,   Kevin, Chris"));
        System.out.println(Joiner.on(",").skipNulls().join("Kurt", "Kevin", null, "Chris"));



        Optional<List<String>> fromNull = Optional.fromNullable(getList());
        if (fromNull.isPresent()) {
            // do something
            System.out.println("heehe");
        }

    }

    private static List<String> getList() {
        return null;
    }

    public void doSomething( List<Object> list ) {
        if( list == null ) {
            throw new IllegalArgumentException( "List must not be null" );
        }
        if( list.isEmpty() ) {
            throw new IllegalArgumentException( "List must not be empty" );
        }
        if( this.field == null ) {
            throw new IllegalStateException( "Field is not initialized" );
        }
        //doSomethingMore( list );
    }

    public void doSomething_Guava( List<Object> list ) {
        checkArgument( list != null, "List must not be null" );
//        checkArgument( !list.isEmpty(), "List must not be empty" );
   //     checkState(initialized, "Argument is not initialized" );
     //   checkNotNull(field, "Field is not initialized");
//        doSomethingMore( list );
    }
}

class Person{
    private String name, emailAddress, department, yearJoined;

    private Sex sex;

    enum Sex {
        MALE, FEMALE;
//        private Sex() {
//        }
    }


    Person(String name, String emailAddress, String department, String yearJoined) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.department = department;
        this.yearJoined = yearJoined;
        this.sex = Sex.MALE;
    }

    Person(String name, String emailAddress, String department, String yearJoined, Sex sex) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.department = department;
        this.yearJoined = yearJoined;
        this.sex = sex;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getEmailAddress() {
        return emailAddress;
    }

    void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    String getDepartment() {
        return department;
    }

    void setDepartment(String department) {
        this.department = department;
    }

    String getYearJoined() {
        return yearJoined;
    }

    void setYearJoined(String yearJoined) {
        this.yearJoined = yearJoined;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this.getClass()).add("name", name)
                .add("emailAddress", emailAddress)
                .add("department", department).add("yearJoined", yearJoined)
                        .add("sex", sex)
              //  .omitNullValues()
                .toString();
    }
}
