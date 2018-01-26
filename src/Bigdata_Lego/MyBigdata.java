package Bigdata_Lego;

public class MyBigdata
{
    private String total;

    private Facets facets;

    private Data[] data;

    private String max_score;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public Facets getFacets ()
    {
        return facets;
    }

    public void setFacets (Facets facets)
    {
        this.facets = facets;
    }

    public Data[] getData ()
    {
        return data;
    }

    public void setData (Data[] data)
    {
        this.data = data;
    }

    public String getMax_score ()
    {
        return max_score;
    }

    public void setMax_score (String max_score)
    {
        this.max_score = max_score;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+", facets = "+facets+", data = "+data+", max_score = "+max_score+"]";
    }
}
			