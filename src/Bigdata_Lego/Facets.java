package Bigdata_Lego;

public class Facets
{
    private Tags tags;

    public Tags getTags ()
    {
        return tags;
    }

    public void setTags (Tags tags)
    {
        this.tags = tags;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tags = "+tags+"]";
    }
}