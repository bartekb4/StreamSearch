package StreamSearchModel;



import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Hello World!");

        ApiUse apiUse=new ApiUse();
        Search search=new Search();
        String result= null;
        try {
            result = apiUse.connect(search.SearchMovie());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //apiUse.availability(result);
        //  apiUse.chooseOne(result);

        apiUse.getAllIcons(result,1);


      //  apiUse.getAllLocs(result);




    }
}
