package strathmore.com.lab3;

/**
 * Created by Ken on 10/24/2017.
 */

public class Newspaper {

    int newspaper_id;
    String newspaper_name;

    public Newspaper(){

    }
    public Newspaper(int newspaper_id, String newspaper_name) {
        this.newspaper_id = newspaper_id;
        this.newspaper_name = newspaper_name;
    }
    public Newspaper(String newspaper_name){
        this.newspaper_name = newspaper_name;

    }

    public  int getNewspaper_id() {
        return newspaper_id;
    }

    public String getNewspaper_name() {
        return newspaper_name;
    }

    public void setNewspaper_id(int newspaper_id) {
        this.newspaper_id = newspaper_id;
    }

    public void setNewspaper_name(String newspaper_name) {
        this.newspaper_name = newspaper_name;
    }
}