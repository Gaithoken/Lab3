package strathmore.com.lab3;

/**
 * Created by Ken on 10/24/2017.
 */
public class Contacts {

    int _id;
    String _name;
    String _phone_number;

    public Contacts(){


    }
    public Contacts(int id, String name, String phone_number){

        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;

    }

    public Contacts(String name, String _phone_number){
        this._name = name;
        this._phone_number = _phone_number;

    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getPhoneNumber() {
        return _phone_number;
    }

    public void setPhoneNumber(String _phone_number) {
        this._phone_number = _phone_number;
    }



}
