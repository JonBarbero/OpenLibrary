package ehu.isad;

import java.util.Arrays;

public class LiburuXehetasunak {

    private String[] publishers;
    private String title;
    private Integer number_of_pages;

    public String[] getPublishers() {
        return publishers;
    }

    public String getTitle() {
        return title;
    }

    public Integer getNumber_of_pages() {
        return number_of_pages;
    }

    @Override
    public String toString() {
        return "LiburuXehetasunak{" +
                "publishers=" + Arrays.toString(publishers) +
                ", title='" + title + '\'' +
                ", number_of_pages=" + number_of_pages +
                '}';
    }
}
