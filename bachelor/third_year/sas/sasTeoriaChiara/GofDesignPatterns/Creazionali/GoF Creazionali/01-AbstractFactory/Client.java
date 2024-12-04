class Client {

    DevicesFactory technology;
    
    public void selectTechnology( DevicesFactory df ) {
        technology = df;
    }
    public void test(String song) {
    
        Media media = technology.createMedia();
        Recorder recorder = technology.createRecorder();
        Player player = technology.createPlayer();
        
        System.out.println( "Recording the song : " + song );
        recorder.accept( media );
        recorder.record( song );
                
        System.out.println( "Listening the record:" );
        player.accept( media );
        player.play();
        
        //DevicesFactory temp = new CDDevicesFactory();
        //Player otherplayer = temp.createPlayer();

        //System.out.println( "Listening the record:" );
        //otherplayer.accept( media );
        //otherplayer.play();
        
   }
}
