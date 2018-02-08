package seo.com.dovizvealtinkurlari;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by jubat on 6.8.2017.
 */

public class XMLRead {

    static final String KEY_ITEM = "Currency"; // parent node
    static final String KEY_NAME = "Isim";
    static final String KEY_BUYING = "ForexBuying";
    static final String KEY_SELLING = "ForexSelling";

    public static ArrayList<Data> DataList =new ArrayList<Data>();
    Images images = new Images();


    public void DataRead(String urlText){
        try {
            URL url = new URL(urlText);
            URLConnection conn = url.openConnection();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(conn.getInputStream());

            NodeList nodes = doc.getElementsByTagName(KEY_ITEM);
            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                Element name =  (Element) element.getElementsByTagName(KEY_NAME).item(0);
                Element buying = (Element) element.getElementsByTagName(KEY_BUYING).item(0);
                Element selling = (Element) element.getElementsByTagName(KEY_SELLING).item(0);
                Data data = new Data();
                data.Bayrak=images.merkez[i];
                data.Para=name.getTextContent();
                data.Alis=buying.getTextContent();
                data.Satis=selling.getTextContent();
                data.Oran=R.drawable.xxx;
                DataList.add(data);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Data> GetData(){

        return DataList;
    }

    public int GetDataSize(){
        return DataList.size();
    }


}
