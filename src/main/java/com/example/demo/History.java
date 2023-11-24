package com.example.demo;

public class History {
    private String path;
    private String name;

    private String method;

    History( String name, String path, String method){
        this.path = path;
        this.name = name;
        this.method = method;
    }

    public String getPath(){
        return path;
    }
    public void setPath(String Path){
        this.path = Path;
    }

    public  void setName(String name){this.name = name;}
    public String getName(){return name;}

    public String getMethod(){return method;}

    public void setMethod(String method){this.method = method;}


}
