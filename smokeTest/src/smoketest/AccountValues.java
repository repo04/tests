package smoketest;

import java.io.IOException;
import java.util.Properties;
import javax.el.PropertyNotFoundException;

public class AccountValues {

    Properties p = new Properties();

    /**
     * Access property file
     *
     * @param testPropertiesName
     */
    public AccountValues(String testPropertiesName) {

        try {
            p.load(this.getClass().getClassLoader().getResourceAsStream(testPropertiesName + ".properties"));
        } catch (IOException e) {
        }
    }

    /**
     * Get property value
     *
     * @param t
     * @return
     */
    public String getTokenValue(String t) {

        if (p.getProperty(t) == null) {
            throw new PropertyNotFoundException(t + ": No such property");
        }
        return p.getProperty(t);
    }
}
