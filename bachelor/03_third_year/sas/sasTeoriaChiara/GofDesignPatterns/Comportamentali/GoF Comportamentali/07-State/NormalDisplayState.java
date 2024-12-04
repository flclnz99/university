public class NormalDisplayState extends ClockState {
    public NormalDisplayState(Clock clock) {
        super( clock );
        System.out.println( "** Clock is in normal display.");
    }
    public void modeButton() {
        clock.setState( new UpdatingHrState( clock ) );
    }
    public void changeButton() {
        System.out.print( "LIGHT ON: " );
        clock.showTime();
    }
}
