public class AdministrativeManager extends Engineer {
    public AdministrativeManager( String nam, String off ) {
        super(nam, off);
    }
    public void whoIs() {
        sayIamBoss();
        super.whoIs();
    }
    private void sayIamBoss(){
        System.out.print( "I am a boss. " );
    }
}
