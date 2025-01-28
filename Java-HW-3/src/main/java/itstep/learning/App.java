package itstep.learning;

public class App
{
    public static void main( String[] args )
    {
        try {
            new Store().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
