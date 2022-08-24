public class EncryptApiApplication {
    private int port ;

    public EncryptApiApplication(int port) {
        this.port = port;
    }
    public static void main(String[] args) {
        EncryptApiApplication application = new EncryptApiApplication(8080);
        application.start();
    }

    public void start(){

    }
}
