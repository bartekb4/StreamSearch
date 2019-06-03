package StreamSearchGUI;

import StreamSearchModel.ApiUse;
import StreamSearchModel.Item;
import StreamSearchModel.Search;

import StreamSearchModel.SearchException;
import org.jdesktop.swingx.JXImageView;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class StreamSearchGUI {

    private JTextPane textPane1;
    private JPanel SEARCHPanel;
    private JComboBox choose;
    private JFormattedTextField servicename;
    private JButton linkage;

    private JXImageView Poster;
    private JXImageView service;
    private JComboBox chooseService;
    private JButton getQuery;
    private JTextField queryfield;
    private JTextField textField2;
    static JFrame jFrame;
    private ApiUse apiUse;
    private Search searching;
    private Item item;
    private List<Object> names;
    private List<Object> services;
    private List<Object> urls;
    private List<Object> icon;
    private List<Object> poster;
    private String res;

    public List<Object> getIcon() {
        return icon;
    }

    public void setIcon(List<Object> icon) {
        this.icon = icon;
    }

    public void setPoster(List<Object> poster) {
        this.poster = poster;
    }

    public JXImageView getPoster() {
        return Poster;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getRes() {
        return res;
    }

    public List<Object> getServices() {
        return services;
    }

    public List<Object> getUrls() {
        return urls;
    }

    public void setUrls(List<Object> urls) {
        this.urls = urls;
    }

    public void setServices(List<Object> services) {
        this.services = services;
    }

    public List<Object> getNames() {
        return names;
    }

    public void setNames(List<Object> names) {
        this.names = names;
    }

    public ApiUse getApiUse() {
        return apiUse;
    }

    public void setApiUse(ApiUse apiUse) {
        this.apiUse = apiUse;
    }

    public Search getSearching() {
        return searching;
    }


    public void setSearching(Search searching) {
        this.searching = searching;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    public static void wyrzucBlad(String msg, String title) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void connected(){  ///getting response from api
        Search search=new Search();
        String query=queryfield.getText();
        String stream="https://onweb.gr/wp-content/uploads/2018/04/video-streaming-med-1.jpg";
        String glass = "https://cdn2.iconfinder.com/data/icons/media-and-navigation-buttons-square/512/Button_15-512.png";
        try {
            Throwable throwable = new Throwable("ERROR");
            SearchException searchException = new SearchException("Empty Search", throwable);
            searchException.emptyString(query);
        } catch (SearchException e) {
            wyrzucBlad("Empty", "ERROR");
        }
        String title1 = query.toLowerCase();
        String title2 = title1.replaceAll("\\s+", "");
        try {
            res = getApiUse().connect(title2);
            Throwable throwable = new Throwable("ERROR");
            SearchException searchException = new SearchException("Empty Search", throwable);
            searchException.emptyJson(getApiUse().mainResults(res));

            setRes(res);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        catch (SearchException e){
            wyrzucBlad("No results", "ERROR");
            service.setImage(search.Phposter(glass,150));
            linkage.setText("");
            choose.setModel(new DefaultComboBoxModel(search.inputCombo(Collections.emptyList())));
        }

    }

    public void lookFor() throws ParseException { //choosing movie fromm combobox, poster and services
        String stream="https://onweb.gr/wp-content/uploads/2018/04/video-streaming-med-1.jpg";
        String glass = "https://cdn2.iconfinder.com/data/icons/media-and-navigation-buttons-square/512/Button_15-512.png";

        Search search=new Search();
        int index= choose.getSelectedIndex();

        System.out.println(index);
        setServices(getApiUse().getAllServices(getRes(),index));
        chooseService.setModel(new DefaultComboBoxModel(search.inputCombo(services)));
        setUrls(getApiUse().getAllUrls(getRes(),index));
        setPoster(getApiUse().getAllPosters(getRes()));
        String phPost = String.valueOf(poster.get(index));
        try {
            Throwable throwable = new Throwable("ERROR");
            SearchException searchException = new SearchException("Empty Search", throwable);
            searchException.emptyObj(poster.get(index));
            Poster.setImage(search.Phposter(phPost,800));
        } catch (SearchException e) {
            wyrzucBlad("Sorry, no poster", "ERROR");
            Poster.setImage(search.Phposter(glass,800));
        }
        System.out.println(phPost);

        linkage.setText("");
        service.setImage(search.Phposter(stream,150));
        try {
            search.write(res);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void Open() { //poster glass setting
        Search search= new Search();
        String glass = "https://cdn2.iconfinder.com/data/icons/media-and-navigation-buttons-square/512/Button_15-512.png";
        Poster.setImage(search.Phposter(glass,800));

    }
    public void showAll() throws IOException, ParseException { //showin all movie results for query
        Search search=new Search();
        setNames(getApiUse().getAllnames(getRes(),"name"));
        String stream="https://onweb.gr/wp-content/uploads/2018/04/video-streaming-med-1.jpg";
        String glass = "https://cdn2.iconfinder.com/data/icons/media-and-navigation-buttons-square/512/Button_15-512.png";
        choose.setModel(new DefaultComboBoxModel(search.inputCombo(names)));
        Poster.setImage(search.Phposter(glass,800));
        service.setImage(search.Phposter(stream,150));
        linkage.setText("");
        chooseService.setModel(new DefaultComboBoxModel(search.inputCombo(Collections.emptyList())));




    }
    public void setServ() throws ParseException, IOException { //choosin service , setting icon and link
        Search search= new Search();
        String stream="https://onweb.gr/wp-content/uploads/2018/04/video-streaming-med-1.jpg";
        int index= choose.getSelectedIndex();
        int indexServ=chooseService.getSelectedIndex();
        setIcon(getApiUse().getAllIcons(getRes(),index));
        setUrls(getApiUse().getAllUrls(getRes(),index));
        try {
            Throwable throwable = new Throwable("ERROR");
            SearchException searchException = new SearchException("Empty Search", throwable);
            searchException.emptyObj(urls.get(indexServ));
            linkage.setText(String.valueOf(urls.get(indexServ)));
        } catch (SearchException e) {
            wyrzucBlad("Sorry, no icon", "ERROR");
            linkage.setText("Sorry, google needed");
        }
        String link = String.valueOf(urls.get(indexServ));
        linkage.setText(link);
        String phIcon = String.valueOf(getApiUse().getAllIcons(getRes(),index).get(indexServ));
        try {
            Throwable throwable = new Throwable("ERROR");
            SearchException searchException = new SearchException("Empty Search", throwable);
            searchException.emptyObj(icon.get(indexServ));
            service.setImage(search.Phposter(phIcon,150));
        } catch (SearchException e) {
            wyrzucBlad("Sorry, no icon", "ERROR");
            service.setImage(search.Phposter(stream,150));
        }

    //    String stream="https://onweb.gr/wp-content/uploads/2018/04/video-streaming-med-1.jpg";

    }


    public StreamSearchGUI(ApiUse apiUse, Item item) throws IOException {
        this.apiUse=apiUse;
        this.item=item;



        linkage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runtime rt = Runtime.getRuntime();
                String url = linkage.getText();
                try {
                    rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    lookFor();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });
        chooseService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setServ();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

        getQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    connected();
                    showAll();
                    Open();

                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }


            }
        });

    }


    public static void main(String[] args) throws IOException {

        Item item=new Item("matrix","netflix");
        ApiUse apiUse=new ApiUse();
        jFrame = new JFrame("StreamSeachGUI");
        jFrame.setContentPane(new StreamSearchGUI(apiUse,item).SEARCHPanel);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
