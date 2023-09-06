package org.animal.ResponseUtil;

public class ResponseSingleton {
    private static Response singleton;

    public static Response getSingleton(){
        if (singleton == null)
            singleton = new Response();

        return singleton;
    }
}
