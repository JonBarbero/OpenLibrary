package ehu.isad;

public class Liburua{
    private String info_url;
    private String bib_key;
    private String preview_url;
    private String thumbnail_url;
    private LiburuXehetasunak details;
    private String preview;

    public LiburuXehetasunak getXehetasunak() {
        return details;
    }

    @Override
    public String toString() {
        return "Liburua{" +
                "info_url='" + info_url + '\'' +
                ", bib_key='" + bib_key + '\'' +
                ", preview_url='" + preview_url + '\'' +
                ", thumbnail_url='" + thumbnail_url + '\'' +
                ", details=" + details +
                ", preview='" + preview + '\'' +
                '}';
    }
}