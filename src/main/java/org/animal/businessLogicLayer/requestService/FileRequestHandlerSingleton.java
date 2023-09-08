package org.animal.businessLogicLayer.requestService;

public class FileRequestHandlerSingleton {
    private static FileRequestHandler singleton;

    public static FileRequestHandler getSingleton(){
        if (singleton == null)
            singleton = new FileRequestHandler();

        return singleton;
    }
}
