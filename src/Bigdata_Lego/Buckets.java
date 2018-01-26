package Bigdata_Lego;

public class Buckets
{
    private String doc_count;

    private String key;

    public String getDoc_count ()
    {
        return doc_count;
    }

    public void setDoc_count (String doc_count)
    {
        this.doc_count = doc_count;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [doc_count = "+doc_count+", key = "+key+"]";
    }
}
			