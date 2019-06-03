package StreamSearchModel;

public class Item {

    private String title;
    private boolean isMovie;
    private String stream;
    private String photoIcon;
    private String photoPoster;
    private String url;
    public Item(String title){
        this.title=title;
    }


    public Item(String title, String stream){
        this.title=title;

        this.stream=stream;
    }
    public Item(String title,String photoPoster, String stream){
        this.title=title;
        this.stream=stream;
        this.photoPoster=photoPoster;

    }
    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public boolean isMovie() {
        return isMovie;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getPhotoIcon() {
        return photoIcon;
    }

    public void setPhotoIcon(String photoIcon) {
        this.photoIcon = photoIcon;
    }

    public String getPhotoPoster() {
        return photoPoster;
    }

    public void setPhotoPoster(String photoPoster) {
        this.photoPoster = photoPoster;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

