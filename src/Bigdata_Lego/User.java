package Bigdata_Lego;

public class User
{
    private String id;

    private String alias;

    private String uuid;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getAlias ()
    {
        return alias;
    }

    public void setAlias (String alias)
    {
        this.alias = alias;
    }

    public String getUuid ()
    {
        return uuid;
    }

    public void setUuid (String uuid)
    {
        this.uuid = uuid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", alias = "+alias+", uuid = "+uuid+"]";
    }
}