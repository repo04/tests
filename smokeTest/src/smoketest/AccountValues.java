package smoketest;

import java.util.Properties;

public class AccountValues 
{
    Properties p = new Properties();
    
    public AccountValues( String testPropertiesName )
    {
        try
        {
            p.load(this.getClass().getClassLoader().getResourceAsStream(testPropertiesName + ".properties"));
        }
        
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
    public String getTokenValue( String t )
    {
        return p.getProperty(t);
    }
}
