package org.animal.RequestHandlers;

public class RequestHandlerSingleton {
    private static RequestHandler singleton;

    public static RequestHandler getSingleton(){
        if (singleton == null)
            singleton = new RequestHandler();

        return singleton;
    }
}
