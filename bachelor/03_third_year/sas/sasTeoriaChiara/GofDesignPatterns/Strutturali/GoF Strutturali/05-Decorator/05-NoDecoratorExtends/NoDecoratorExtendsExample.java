public class NoDecoratorExtendsExample {
    public static void main(String arg[]) {
    
        Employee thisWillBeFamous = new Engineer( "William Gateway", "Programming Department" );
        
        System.out.println( "Who are you?");
        thisWillBeFamous.whoIs();
        
        thisWillBeFamous = new AdministrativeManager( "William Gateway", "Programming Department" );
        
        System.out.println( "Who are you now?");
        thisWillBeFamous.whoIs();
        
        thisWillBeFamous = new ProjectManager( "William Gateway", "Programming Department", "D.O.S.- Doors Operating System" );
        
        System.out.println( "Who are you now?");
        thisWillBeFamous.whoIs();
        
        thisWillBeFamous = new ProjectManager( "William Gateway", "Programming Department", "EveryoneLoggedToInternet Explorer" );
        
        System.out.println( "Who are you now?");
        thisWillBeFamous.whoIs();
        
    }
}
