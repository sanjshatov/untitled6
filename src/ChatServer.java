import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    ArrayList<Client> clients = new ArrayList<>(); // создали массив для хранения всех клиентов. Чтобы клиентам обмениваться между собой
    ServerSocket serverSocket;

    ChatServer() throws IOException {
        // создаем серверный сокет на порту 1234
        serverSocket = new ServerSocket(1234);
    }
void sendAll(String message) { // ф. будет принимать некоторую строку и раздавать ее всем клиентам, в том числе на себя
        for (Client client : clients) {
            client.receive(message);
        }

}
    public void run() {
        while (true) {
            System.out.println("Waiting...");

            try {
                // ждем клиента из сети
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                // создаем клиента на своей стороне
                clients.add(new Client(socket, this));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        public static void main (String[]args) throws IOException {
            new ChatServer().run(); // создаем настоящий объект сервера

        }
    }
