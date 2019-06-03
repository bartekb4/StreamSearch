package StreamSearchModel;

import org.json.simple.JSONArray;

import javax.swing.*;
import java.net.URL;
import java.util.List;

public class SearchException extends RuntimeException {
    private String string;
    private Throwable throwable;
    private URL url;

    public SearchException(){
        super();
    }
    public SearchException(String wiadomosc, Throwable przyczyna){
        super(wiadomosc,przyczyna);
    }
    public void userError(String wiadomosc, String tytul){
        JOptionPane.showMessageDialog(null, wiadomosc, tytul, JOptionPane.INFORMATION_MESSAGE);
    }
    public void emptyString(String string){
        this.string=string;
        this.throwable=new Throwable("BłądUzytkownika");
        if(string.isEmpty()){

            throw new SearchException("Empty Search",throwable);

        }
    }
    public void emptyObj(Object obj){
        if(obj==null){
            throw new SearchException("Empty Search",throwable);
        }
    }
    public void noAnswer(String sss){
        this.string=sss;
        this.throwable=new Throwable("BłądUzytkownika");
        if(sss.isEmpty()){
            userError("No answer","Error");
            throw new SearchException("Empty Search",throwable);

        }
    }
    public void emptyJson(JSONArray jsonArray){

        if(jsonArray.isEmpty()){

            throw new SearchException("Empty Search",throwable);
        }
    }
}
