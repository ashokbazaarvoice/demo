package com.aranin.aws.s3;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 3/19/13
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class PhotoFile {
    private String origName;
    private String targetName;
    public String imagePath;

    public String getOrigName() {
        return origName;
    }

    public void setOrigName(String origName) {
        this.origName = origName;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String toString(){
        return origName + "," +  targetName + "," + imagePath;
    }
}
