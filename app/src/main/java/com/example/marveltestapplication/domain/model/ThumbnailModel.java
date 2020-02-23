package com.example.marveltestapplication.domain.model;

public class ThumbnailModel {
    private  String path;
    private  String extension;

    public ThumbnailModel(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }

    public ThumbnailModel(){
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPortrait(){
       return path.concat("/").concat("portrait_medium").concat(".").concat(extension);
   }
}
