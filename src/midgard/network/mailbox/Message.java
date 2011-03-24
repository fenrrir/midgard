/*
* Copyright (C) 2011 Rodrigo Pinheiro Marques de Araujo
*
* This program is free software; you can redistribute it and/or modify it under
* the terms of the GNU General Public License as published by the Free Software
* Foundation; either version 2 of the License, or (at your option) any later
* version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
* details.
*
* You should have received a copy of the GNU General Public License along with
* this program; if not, write to the Free Software Foundation, Inc., 51
* Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
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
