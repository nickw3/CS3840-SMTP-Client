import java.io.*;
import java.net.*;

public class EmailSender
{
    public static void main(String[] args) throws Exception
    {
        // Establish a TCP connection with the mail server.
        Socket socket = new Socket("ASPMX.L.GOOGLE.COM", 25);


        // Create a BufferedReader to read a line at a time.
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        // Read greeting from the server.
        String response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("220")) {
            throw new Exception("220 reply not received from server.");
        }

        // Get a reference to the socket's output stream.
        OutputStream os = socket.getOutputStream();

        // Send HELO command and get server response.
        String command = "HELO alice\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send MAIL FROM command.
        String mailFrom = "MAIL FROM: <nickwhorton3@gmail.com> \r\n";
        System.out.print(mailFrom);
        os.write(mailFrom.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);

        // Send RCPT TO command.
        String rcptTo = "RCPT TO: <nickwhorton3@gmail.com> \r\n";
        System.out.print(rcptTo);
        os.write(rcptTo.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);

        // Send DATA command.
        String data = "DATA \r\n";
        System.out.print(data);
        os.write(data.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);

        // Send message data.
        String message = "Hi this is nick. \r\n";
        System.out.print(message);
        os.write(message.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);

        // End with line with a single period.
        String period = ".\r\n";
        System.out.print(period);
        os.write(period.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);

        // Send QUIT command.
        String quit = ". \r\n";
        System.out.print(quit);
        os.write(quit.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
    }
}

 