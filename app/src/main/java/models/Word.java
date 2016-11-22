package models;

/**
 * Created by pavel on 20/10/2016.
 */
public class Word {

    private String english = "";
    private String russian = "";

    public Word(String english, String russian) {
        this.english = english;
        this.russian = russian;
    }

    public String getEnglish() {
        return english;
    }

    public String getRussian() {
        return russian;
    }


}
