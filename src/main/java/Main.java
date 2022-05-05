public class Main {

    public static void main(String[] args) {

        System.out.println("Waiting for client\n");
        Thread thread = new Thread(new IncomingSocketConnectionHandler(9797));
        thread.start();

    }

}

