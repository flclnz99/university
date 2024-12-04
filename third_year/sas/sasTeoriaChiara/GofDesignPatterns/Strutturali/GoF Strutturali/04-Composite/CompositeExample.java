public class CompositeExample {
    public static void main(String[] args) {
        // Creates single parts
        Component monitor = new SinglePart("LCD Monitor");
        Component keyboard = new SinglePart("Italian Keyboard");
        Component processor = new SinglePart("Pentium III Processor");
        Component ram = new SinglePart("256 KB RAM");
        Component hardDisk = new SinglePart("40 Gb Hard Disk");
        // A composite with 3 leaves
        Component mainSystem = new CompoundPart( "Main System" );
        try {
            mainSystem.add( processor );
            mainSystem.add( ram );
            mainSystem.add( hardDisk );
        } catch (SinglePartException e) {
            e.printStackTrace();
        }
        // A Composite compound by another Composite and one Leaf
        Component computer = new CompoundPart("Computer");
        try{
            computer.add( monitor );
            computer.add( keyboard );
            computer.add( mainSystem );
        } catch (SinglePartException e) {
            e.printStackTrace();
        }
        System.out.println("**Tries to describe the ’monitor’ component");
        monitor.describe();
        System.out.println("**Tries to describe the ’main system’ component");
        mainSystem.describe();
        System.out.println("**Tries to describe the ’computer’ component" );
        computer.describe();
        // Wrong: invocation of add() on a Leaf
        System.out.println( "**Tries to add a component to a single part (leaf) " );
        try{
            monitor.add( mainSystem );
        } catch (SinglePartException e){
            e.printStackTrace();
        }
    }
}
