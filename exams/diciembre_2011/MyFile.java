package exams.diciembre_2011;

public class MyFile {

    private long lastmodified;
    private String contents;
    private String name;

    public MyFile(long last, String data, String name) {
        lastmodified=last;
        contents=data;
        this.name = name;
    }

    public long getLastModified() {
        return lastmodified;
    }

    public String getContents() {
        return contents;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return contents + "\n(" + lastmodified + ")";
    }
}
