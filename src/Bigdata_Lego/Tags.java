package Bigdata_Lego;

public class Tags
{
    private String sum_other_doc_count;

    private Buckets[] buckets;

    private String doc_count_error_upper_bound;

    public String getSum_other_doc_count ()
    {
        return sum_other_doc_count;
    }

    public void setSum_other_doc_count (String sum_other_doc_count)
    {
        this.sum_other_doc_count = sum_other_doc_count;
    }

    public Buckets[] getBuckets ()
    {
        return buckets;
    }

    public void setBuckets (Buckets[] buckets)
    {
        this.buckets = buckets;
    }

    public String getDoc_count_error_upper_bound ()
    {
        return doc_count_error_upper_bound;
    }

    public void setDoc_count_error_upper_bound (String doc_count_error_upper_bound)
    {
        this.doc_count_error_upper_bound = doc_count_error_upper_bound;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sum_other_doc_count = "+sum_other_doc_count+", buckets = "+buckets+", doc_count_error_upper_bound = "+doc_count_error_upper_bound+"]";
    }
}