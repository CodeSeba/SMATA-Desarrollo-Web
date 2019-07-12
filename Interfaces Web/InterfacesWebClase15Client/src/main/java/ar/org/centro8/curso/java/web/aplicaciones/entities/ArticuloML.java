package ar.org.centro8.curso.java.web.aplicaciones.entities;
public class ArticuloML {
    private String item_id;
    private String item_url;
    private String image_content;
    private String price__fraction;

    public ArticuloML(String item_id, String item_url, String image_content, String price__fraction) {
        this.item_id = item_id;
        this.item_url = item_url;
        this.image_content = image_content;
        this.price__fraction = price__fraction;
    }

    @Override
    public String toString() {
        return "ArticuloML{" + "item_id=" + item_id + ", item_url=" + item_url + ", image_content=" + image_content + ", price__fraction=" + price__fraction + '}';
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public String getImage_content() {
        return image_content;
    }

    public void setImage_content(String image_content) {
        this.image_content = image_content;
    }

    public String getPrice__fraction() {
        return price__fraction;
    }

    public void setPrice__fraction(String price__fraction) {
        this.price__fraction = price__fraction;
    }
    
    
    
}
