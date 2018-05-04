/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.social.FacebookConnect;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 *
 * @author bechir belkahla
 */
public class FacebookData extends ConnectionRequest implements UserData {

    String name;
        String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

        

        @Override
        public String getImage() {
            return "http://graph.facebook.com/v2.4/" + id + "/picture";
        }

        @Override
        public void fetchData(String token, Runnable callback) {
            ConnectionRequest req = new ConnectionRequest() {
                protected void readResponse(InputStream input) throws IOException {
                    JSONParser parser = new JSONParser();
                    Map<String, Object> parsed = parser.parseJSON(new InputStreamReader(input, "UTF-8"));
                    name = (String) parsed.get("name");
                    id = (String) parsed.get("id");
                }

                @Override
                protected void postResponse() {
                    callback.run();
                }

                @Override
                protected void handleErrorResponseCode(int code, String message) {
                    //access token not valid anymore
                    if(code >= 400 && code <= 410){
                       // doLogin(FacebookConnect.getInstance(), FacebookData.this, true);
                        return;
                    }
                    super.handleErrorResponseCode(code, message);
                }
            };
            req.setPost(false);
            req.setUrl("https://graph.facebook.com/v2.4/me");
            req.addArgumentNoEncoding("access_token", token);
            NetworkManager.getInstance().addToQueue(req);
        }
        
        
        
    
}
