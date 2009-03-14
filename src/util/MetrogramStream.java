/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author aslak
 */
public class MetrogramStream {
    
    double lat = 10.0;
    double lon = 60.1;
    int height = -1;
    String url = "http://84.215.93.57/";
    
    public MetrogramStream()
    {
        
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setLon(double lon)
    {
        this.lon = lon;
    }
    
    public void setHeight(int height)
    {
        this.height = height;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public InputStream fetch()
               throws Exception
    {
        InputStream stream = null;
        
        String queryUrl = this.url + "?lat=" + this.lat + "&lon=" + this.lon;
        if(this.height > 0)
            queryUrl += "&height="+height;
        
        HttpConnection con = null;
        try
        {
            con = (HttpConnection)Connector.open(queryUrl);
        }catch(IOException e )
        {
            throw new Exception("Trouble opening url;" + this.url + ", " + e.getMessage());
        }
        
        
        try
        {
           stream = con.openInputStream();
           if(stream == null)
               throw new Exception("empty svg resource");
        }catch(IOException e)
        {
            throw new Exception("problem downloading stream " + e.getMessage());
        }
        return stream;
    }
    
    
}
