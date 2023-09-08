package org.animal.persistenceLayer.fileIO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.animal.businessLogicLayer.TreeNode;

import java.io.File;
import java.io.IOException;

public class JacksonDataBind {
    public static TreeNode fromJson(String path){
        ObjectMapper objectMapper = new ObjectMapper();

        TreeNode root = null;
        try {
            root = objectMapper.readValue(new File(path), new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println("Parsing error: " + e);
        }
        return  root;
    }

    public static void toJson(TreeNode root, String path){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(path), root);
        } catch (IOException e) {
            System.out.println("Output in Json error" + e);
        }
    }
}
