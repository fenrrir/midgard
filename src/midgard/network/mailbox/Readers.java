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

/**
 *
 * @author fenrrir
 */
public class Readers {
    private IMailBox mailbox;
    private Reader [] readers;
    private int size;
    private int initializedThreads;
    private int nextThread;

    public Readers(int size, IMailBox mailbox) {
        this.mailbox = mailbox;
        this.size = size;
        nextThread = -1;
        readers = new Reader[size];
        initializedThreads = -1;
    }

    public Reader getNextReader(){
        //round robin
        nextThread++;
        if (nextThread >= size){
            nextThread = 0;
        }

        if(nextThread > initializedThreads){
            readers[nextThread] = new Reader(mailbox);
        }


        return readers[nextThread];
    }

    public void addMessage(IReceivedMessage message){
        Reader reader = getNextReader();
        reader.addMessage(message);
    }

    public void stop(){
        for(int i=0; i< nextThread; i++){
            Reader reader = readers[i];
            reader.stop();
        }
    }

}
