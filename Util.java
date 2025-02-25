import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Util {
    // This method is what automatically sets up the dependency for JDBC and loads the driver
    // using the jar file in the lib/ directory. This needs to run so that the program can access
    // JDBC-related functions
    public static void loadDriver() {
        try {
            File jarFile = new File("lib/mysql-connector-j-9.2.0.jar");
            URL url = jarFile.toURI().toURL();
            URLClassLoader loader = new URLClassLoader(new URL[]{url}, ClassLoader.getSystemClassLoader());
            Class.forName("com.mysql.cj.jdbc.Driver", true, loader);
            System.out.println("MySQL JDBC Driver Loaded Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        } // try
    } // loadDriver
} // Util
