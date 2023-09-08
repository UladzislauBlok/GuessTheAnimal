package org.animal.businessLogicLayer.responseSevrice;

public class FileResponseSingleton {
    private static FileResponse singleton;

    public static FileResponse getSingleton(){
        if (singleton == null)
            singleton = new FileResponse();

        return singleton;
    }
}
