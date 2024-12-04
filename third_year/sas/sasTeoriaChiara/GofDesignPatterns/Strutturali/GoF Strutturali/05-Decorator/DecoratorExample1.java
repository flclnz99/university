public class DecoratorExample1 {
    public static void main(String arg[]) {
    
        Employee thisWillBeFamous = new Engineer( "William Gateway", "Programming Department" );
        
        System.out.println( "Who are you?");
        thisWillBeFamous.whoIs();
        
        thisWillBeFamous = new AdministrativeManager( thisWillBeFamous );
        
        System.out.println( "Who are you now?");
        thisWillBeFamous.whoIs();
        
        thisWillBeFamous = new ProjectManager( thisWillBeFamous, "D.O.S.- Doors Operating System" );
        
        System.out.println( "Who are you now?");
        thisWillBeFamous.whoIs();
        
        thisWillBeFamous = new ProjectManager( thisWillBeFamous, "EveryoneLoggedToInternet Explorer" );
        
        System.out.println( "Who are you now?");
        thisWillBeFamous.whoIs();
        
    }
}
