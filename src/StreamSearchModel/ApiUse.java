package StreamSearchModel;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class  ApiUse {
    private Item item;
    String query=null;
    private Search search;

    public ApiUse() {

    }
    public String connect(String query) throws IOException, ParseException {

        URL url = new URL("https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?");
        URL url2 = new URL(url + "term=" + query + "&country=uk");
        HttpURLConnection con3 = (HttpURLConnection) url2.openConnection();
        con3.setRequestProperty("X-RapidAPI-Host", "utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com"); ///making utelly api connection
        con3.setRequestProperty("X-RapidAPI-Key", "8a2f94d881msh0cee2e1de8e452ep14186ajsnc0a39f09d0de");
        con3.setRequestMethod("GET");
        con3.setRequestProperty("Content-Type", "application/json; utf-8");
        con3.setRequestProperty("Accept", "application/json");

        System.out.println(con3);
        String res;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con3.getInputStream(), "utf-8"))) {  // gettting response
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            res = String.valueOf(response);
            System.out.println(res);

            return res;

        }
    }


    public JSONArray mainResults(String res) throws ParseException {
        JSONParser jsonParser1 = new JSONParser();
        List<Object> names=null;
        JSONObject jsonObject1 = (JSONObject) jsonParser1.parse(res);
        JSONArray jsonArray1 = (JSONArray) jsonObject1.get("results");
        return jsonArray1;
    }


    public List<Object> getAllnames(String res, String key) throws ParseException {
        JSONParser jsonParser1 = new JSONParser();
        List<Object> names=null;
        JSONObject jsonObject1 = (JSONObject) jsonParser1.parse(res);
        JSONArray jsonArray1 = (JSONArray) jsonObject1.get("results");

        names = IntStream.range(0, jsonArray1.size())
                .mapToObj(index -> ((JSONObject) jsonArray1.get(index)).get(key))
                .collect(Collectors.toList());
        System.out.println(names);
        return names;
    }
    public List<Object> getAllPosters(String res) throws ParseException {
        JSONParser jsonParser1 = new JSONParser();
        List<Object> names=null;
        JSONObject jsonObject1 = (JSONObject) jsonParser1.parse(res);
        JSONArray jsonArray1 = (JSONArray) jsonObject1.get("results");

        List<Object> posters = IntStream.range(0, jsonArray1.size())
                .mapToObj(index -> ((JSONObject) jsonArray1.get(index)).get("picture"))
                .collect(Collectors.toList());
        System.out.println(posters);
        return posters;
    }


    public List<Object> getAllIcons(String res, int indexes) throws ParseException {

        List<Object> icons = null;

//Parse the JSON data present in the string format
        JSONParser parse1 = new JSONParser();
//Type caste the parsed json data in json object
        JSONObject jobj1 = (JSONObject) parse1.parse(res);
//Store the JSON object in JSON array as objects (For level 1 array element i.e Results)
        JSONArray jsonarr_1 = (JSONArray) jobj1.get("results");
        if (jsonarr_1.isEmpty()) {
            System.out.println("No results, sorry");
        } else {
            System.out.println(jsonarr_1);
            JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(indexes);
            JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("locations");

            icons= IntStream.range(0, jsonarr_2.size())
                    .mapToObj(index -> ((JSONObject) jsonarr_2.get(index)).get("icon"))
                    .collect(Collectors.toList());
        }
        System.out.println(icons);

        return icons;
    }

    public List<Object> getAllUrls(String res, int indexes) throws ParseException {
        List<Object> services = null;
        List<Object> urls = null;
        List<Object> icons = null;

//Parse the JSON data present in the string format
        JSONParser parse1 = new JSONParser();
//Type caste the parsed json data in json object
        JSONObject jobj1 = (JSONObject) parse1.parse(res);
//Store the JSON object in JSON array as objects (For level 1 array element i.e Results)
        JSONArray jsonarr_1 = (JSONArray) jobj1.get("results");
        if (jsonarr_1.isEmpty()) {
            System.out.println("No results, sorry");
        } else {
            System.out.println(jsonarr_1);
            //Get data for Results array
            //Store the JSON objects in an array
            //Get the index of the JSON object and print the values as per the index
            JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(indexes);
            //Store the JSON object in JSON array as objects (For level 2 array element i.e Address Components)
            String data2 = (String) jsonobj_1.get("name");
            JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("locations");

            urls= IntStream.range(0, jsonarr_2.size())
                    .mapToObj(index -> ((JSONObject) jsonarr_2.get(index)).get("url"))
                    .collect(Collectors.toList());
        }
        return urls;
    }
    public List<Object> getAllServices(String res, int indexes) throws ParseException {

        List<Object> services = null;
//Parse the JSON data present in the string format
        JSONParser parse1 = new JSONParser();
//Type caste the parsed json data in json object
        JSONObject jobj1 = (JSONObject) parse1.parse(res);
//Store the JSON object in JSON array as objects (For level 1 array element i.e Results)
        JSONArray jsonarr_1 = (JSONArray) jobj1.get("results");
        if (jsonarr_1.isEmpty()) {
            System.out.println("No results, sorry");
        } else {
                //Get data for Results array

                //Store the JSON objects in an array
                //Get the index of the JSON object and print the values as per the index
                JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(indexes);
                System.out.println(jsonobj_1);

                //Store the JSON object in JSON array as objects (For level 2 array element i.e Address Components)
                String data2 = (String) jsonobj_1.get("name");
                System.out.println(data2);
                // JSONObject jsonobj_111= (JSONObject) jsonarr_1.get(i+2);
                // String data4=(String) jsonobj_111.get("name");

                JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("locations");
                System.out.println(data2);
                services= IntStream.range(0, jsonarr_2.size())
                        .mapToObj(index -> ((JSONObject) jsonarr_2.get(index)).get("display_name"))
                        .collect(Collectors.toList());
        }

        System.out.println(services);
        return services;
    }




}






