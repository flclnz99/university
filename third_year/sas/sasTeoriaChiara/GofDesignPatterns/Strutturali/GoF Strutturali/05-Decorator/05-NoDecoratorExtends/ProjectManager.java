public class ProjectManager extends Engineer {
    private String project;
    public ProjectManager( String nam, String off, String proj ) {
        super(nam, off);
        project = proj;
    }
    public void whoIs() {
        super.whoIs();
        System.out.println( "I am the Manager of the Project:" + project );
    }
}
