/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author sliu1_000
 */
public class MenuBean
{
    private String mid;
    private String category;
    private String name;
    private double price;
    private String description;
    
     public MenuBean(String mid,String category, String name, double price, String description)
    {
        this.mid = mid;
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
    }
     public String getMid()
    {
        return  mid;
    }

    public void setMid(String mid)
    {
        this.mid = mid;
    }
    
    public String getCategory()
    {
        return  category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }    
}

