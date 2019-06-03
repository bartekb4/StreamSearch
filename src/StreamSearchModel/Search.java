package StreamSearchModel;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


    public class Search {
        String movieTitle = new String();
        String series = new String();
        Item item=new Item(movieTitle);

        public String SearchMovie() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj nazwe");
            String query=scanner.nextLine();
            item.setTitle(query );
            return query;
        }

        public Image Phposter(String url, int target){   //method for scaling photos
            URL urlback=null;

            try {

                urlback = new URL(url);
            } catch (MalformedURLException e) {
                System.err.println("An URL exception was caught :" + e.getMessage());
            }

            BufferedImage image1 = null;

            try {
                Throwable throwable=new Throwable("Error");
                SearchException searchException = new SearchException("Nie wypelniles", throwable);

                image1 = ImageIO.read(urlback);
            } catch (SearchException e) {
                System.err.println("Nope"+ e.getMessage());

            } catch (IllegalArgumentException e) {
                System.err.println("An IAE was caught :" + e.getMessage());
            } catch (IOException e) {
                System.err.println("An IO was caught :" + e.getMessage());

            }


            BufferedImage scaledImage = Scalr.resize(image1, target);
            return  scaledImage;
        }
        public String[] inputCombo(List<Object> list){  //combobox string[] setting nm,
            String[] strings = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                strings[i] = String.valueOf(list.get(i));
            }
            return strings;
        }

        public void write(String string) throws IOException {

            BufferedWriter writer = new BufferedWriter(new FileWriter("response.json", true));
            writer.write(string);

            writer.close();
        }
    }


