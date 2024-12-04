public class UpdatingHrState extends ClockState {
    public UpdatingHrState(Clock clock) {
        super( clock );
        System.out.println("** UPDATING HR: Press CHANGE button to increase hours.");
    }    
    public void modeButton() {
        clock.setState( new UpdatingMinState( clock ) );
    }
    public void changeButton() {
        clock.hr++;
        if(clock.hr == 24)
            clock.hr = 0;
        System.out.print( "CHANGE pressed - ");
        clock.showTime();
    }
}
