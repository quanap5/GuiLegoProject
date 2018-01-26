package Bigdata_Lego;

public class State
{
    private String alias;

    private String name_short;

    public String getAlias ()
    {
        return alias;
    }

    public void setAlias (String alias)
    {
        this.alias = alias;
    }

    public String getName_short ()
    {
        return name_short;
    }

    public void setName_short (String name_short)
    {
        this.name_short = name_short;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [alias = "+alias+", name_short = "+name_short+"]";
    }
}
			
			