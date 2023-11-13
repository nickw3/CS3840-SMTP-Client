import java.io.*;
import java.util.HashMap;

public class AddressBook {

    private HashMap<String, String> book;//= new HashMap<String, String>();
/*
    AddressBook(){
        writeAddressBook();
    }
*/


    AddressBook() {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("addressbook.txt"))) {
            book = (HashMap<String, String>) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            book = new HashMap<String, String>();
        }

        writeAddressBook();


    }

    private void writeAddressBook(){
        //write object to file
        try{
            FileOutputStream out = new FileOutputStream("addressbook.txt");
            ObjectOutputStream objOut = new ObjectOutputStream(out);

            objOut.writeObject(book);

            objOut.close();
            out.close();

        }catch(IOException e){

        }
    }

    public String getAddress(String nickname){
        return (book.get(nickname));
    }
    public int addAddress(String nickname, String address){
        book.put(nickname, address);
        writeAddressBook();
        return 1;
    }
    public int removeAddress(String nickname){
        book.remove(nickname);
        writeAddressBook();
        return 1;
    }
}
