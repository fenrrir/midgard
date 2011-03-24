/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network.mailbox;

import com.sun.spot.ipv6.tcp.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import midgard.kernel.Debug;

/**
 *
 * @author fenrrir
 */
public class Message implements IReplyMessage, IReceivedMessage {
    private Socket socket;
    private String reply;
    private IMailBox mailBox;
    private String content;

    public Message(Socket socket, IMailBox mailBox) {
        this.socket = socket;
        this.mailBox = mailBox;
        reply = "";
        content = "";
    }


    public void read() {
        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            content = inputStream.readUTF();
            Debug.debug("[APP] received=" + content, -1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void reply(String content) {
        reply = content;
        mailBox.putOutboxMessage(this);
        mailBox.removeInboxMessage(this);
    }

    public void send() throws IOException {
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        outputStream.writeUTF(reply);
        Debug.debug("[APP] send=" + reply, -1);
        outputStream.close();
    }

    public String getContent() {
        return content;
    }





}
