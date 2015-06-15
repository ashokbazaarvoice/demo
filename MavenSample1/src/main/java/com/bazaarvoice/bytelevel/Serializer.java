package com.bazaarvoice.bytelevel;

import java.io.*;
import java.util.Arrays;

/**
 * Created by ashok.agarwal on 6/5/15.
 */
public class Serializer {


    public static void main(String args[]) {

        Serializer serializer = new Serializer();
        serializer.serializeAddress("wall street", "united state");
    }

    public void serializeAddress(String street, String country) {

        Address address = new Address();
        address.setStreet(street);
        address.setCountry(country);

        try {

            FileOutputStream fout = new FileOutputStream("/Users/ashok.agarwal/dev/github/MavenSample1/byteoutput/address.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(address);
            oos.close();

            // The object below is serialized using ObjectOutputStream so it has metadata for class and instance variables
            // Hence this has greater bytes size.
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(b);
            o.writeObject(address);
            System.out.println(Arrays.toString(b.toByteArray()));


            // The object below is serialized using DataOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            address.serialize(out);

            System.out.println(Arrays.toString(baos.toByteArray()));

            System.out.println("Done");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class Address implements Serializable {

    String street;
    String country;

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return this.street;
    }

    public String getCountry() {
        return this.country;
    }

    @Override
    public String toString() {
        return new StringBuffer(" Street : ")
                .append(this.street)
                .append(" Country : ")
                .append(this.country).toString();
    }

    public void serialize(DataOutputStream out) throws IOException{
        out.writeUTF(street);
        out.writeUTF(country);
    }



    public void deserialize(DataInputStream in) throws IOException {
        setStreet(in.readUTF());
        setCountry(in.readUTF());
    }

}