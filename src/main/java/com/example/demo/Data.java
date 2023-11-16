package com.example.demo;


public class Data{
//    private int sno;
    private String path;
    private String name;

    Data( String path, String name){
//        this.sno = sno;
        this.path = path;
        this.name = name;
    }

//    public int getSno(){
//        return sno;
//    }

    public String getPath(){
        return path;
    }

//    public void setSno(int Sno){
//        this.sno = Sno;
//    }

    public void setPath(String Path){
        this.path = Path;
    }

    public  void setName(String name){this.name = name;}
    public String getName(){return name;}


}
