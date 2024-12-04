public class UpdatingMinState extends ClockState {
    public UpdatingMinState(Clock clock) {
        super( clock );
        System.out.println("** UPDATING MIN: Press CHANGE button to increase minutes.");
    }
    public void modeButton() {
        clock.setState( new NormalDisplayState( clock ) );
    }
    public void changeButton() {
        clock.min++;
        if(clock.min == 60)
            clock.min = 0;
        System.out.print( "CHANGE pressed - ");
        clock.showTime();
    }
}
