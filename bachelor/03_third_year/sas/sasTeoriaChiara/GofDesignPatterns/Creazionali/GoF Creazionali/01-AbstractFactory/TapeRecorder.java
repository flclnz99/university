public class TapeRecorder implements Recorder {
    Tape tapeInside;
    public void accept( Media med ) {
        tapeInside = (Tape) med;
    }
    public void record( String sound ) {
        if( tapeInside == null )
            System.out.println( "Error: Insert a tape." );
        else
            tapeInside.saveOnTape( sound );
    }
}
