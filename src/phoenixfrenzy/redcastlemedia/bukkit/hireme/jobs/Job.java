package phoenixfrenzy.redcastlemedia.bukkit.hireme.jobs;

import org.bukkit.Location;

/**
 *
 * @author Phoenix_Frenzy
 */
public class Job 
{
    private final int id;
    private String title;
    private String description;
    private double pay;
    private final String employer;
    private final JobCategory category;
    private final String target;
    private final Location location;
    
    //ID
    //Title
    //Description
    //Pay
    //Job Employer
    //Category
    //Target
    //Location
    
    public Job(int id, 
               String title,
               String description,
               double pay,
               String employer, 
               JobCategory category, 
               String target,
               Location location)
    {
        //ID
        this.id = id;
        //Title        
        this.title = title;
        //Description
        this.description = description;
        //Pay
        this.pay = pay;
        //Job Employer
        this.employer = employer;
        //Category
        this.category = category;
        //Target
        this.target = target;
        //Location
        this.location = location;
    }

    public Job(int id, String title, String description, double pay, String employer, String category, String target, String location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
//
// Get Methods
//
    
//ID   
    public int getID()
    {
        return id;
    }
//Title
    public String getTitle()
    {
        return title;
    }
//Description
    public String getDescription()
    {
        return description;
    }
//Pay
    public double getPay()
    {
        return pay;
    }
//Job Employer
    public String getEmployer()
    {
        return employer;
    }
//Category
    public JobCategory getCategory()
    {
        return category;
    }
//Target
    public String getTarget()
    {
        return target;
    }
//Location
    public Location getLocation()
    {
        return location;
    }
    
//
// Set Methods
//
    
//Title
    public void setTitle(String title)
    {
        this.title = title;
    }
//Description
    public void setDescription(String description)
    {
        this.description = description;
    }
//Pay
    public void setPay(double pay)
    {
        this.pay = pay;
    }
}
