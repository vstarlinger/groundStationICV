/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aerospaceresearch.mirka2rxgroundstation.process;

import com.aerospaceresearch.mirka2rxgroundstation.data.IridiumPackage;
import com.aerospaceresearch.mirka2rxgroundstation.data.MailEvent;
import com.aerospaceresearch.mirka2rxgroundstation.data.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;

/**
 *
 * @author valentinstarlinger, padmal
 */
public class MailReceiver {

    private static final String USER_NAME = "mirka2iridium@gmail.com";  // GMail user name
    private static final String bla = "RXLaunchMarch2016"; // GMail password

    public boolean running = true;
    private ArrayList<MailUpdateListener> listeners = new ArrayList<>();
    private Store store;
    private Folder folder;
    private Session session;

    public MailReceiver() {
        connect();
    }

    public boolean isConnected() {
        return true;
    }

    private void connect() {

        try {
            String host = "imap.gmail.com";
            final String user = USER_NAME;
            final String password = bla;
            Properties props = new Properties();
            props.setProperty("mail.imap.ssl.enable", "true");
            session = Session.getInstance(props);

            store = session.getStore("imap");
            store.connect(host, user, password);
            System.out.println("connected");
            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);
            startEmailThread(folder);
        } catch (MessagingException ex) {
            error("Error: Couldn't connect to e-mail server.");
        }

    }

    private void startEmailThread(final Folder folder) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    if (folder.getMessageCount() > 0) {
                        getMessage(folder, folder.getMessageCount() - 1);
                    }
                    int messageCount = folder.getMessageCount();
                    while (running) {
                        int diff = folder.getMessageCount() - messageCount;
                        if (diff < 0) {
                            messageCount = folder.getMessageCount();
                        } else if (diff > 0) {
                            for (int i = 0; i < diff; i++) {
                                getMessage(folder, messageCount + i);
                            }
                            messageCount = folder.getMessageCount();
                        }
                        Thread.sleep(500);
                    }
                    folder.close(true);
                    store.close();
                } catch (InterruptedException ex) {
                    error("Warning: Message Thread interrupted.");
                } catch (MessagingException ex) {
                    error("Error: Couldn't close folder or store.");
                }
            }
        };
        t.start();
    }

    public void close() {
        running = false;
    }

    public void reconnect() {
        close();
        connect();
    }

    public void getMessage(Folder folder, int i) {

        try {
            Message[] message = folder.getMessages();
            if (message[i].getSubject().toLowerCase().contains("message")) {
                if (message[i].getContent() instanceof Multipart) {
                    Multipart multipart = (Multipart) message[i].getContent();
                    for (int j = 0; j < multipart.getCount(); j++) {

                        MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(j);
                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                            System.out.println("reading attachment!");
                            //part.saveFile(new File());
//                            File f = new File("iridiumdummy.bin");
//                            FileInputStream fs = new FileInputStream(f);
                            InputStream stream = part.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
//                            String s, text = "";
//                            while ((s = br.readLine()) != null) {
//                                text += s + "\n";
//                            }
                            int b;
                            String text = "";
                            while ((b = br.read()) != -1) {
                                System.out.print(b);
                                String t = Util.intToBits(b);
//                                if (b == 32) {
//                                    t = "00000000";
//                                }
                                //System.out.println(t);
                                text += t;
                            }
                            text = text.trim();
                            System.out.println((new IridiumPackage(text)).toString());
                            //text = "00001000101010000000000000000000000000000000000000000000000000000000000011100000000000001101111000000000111000000000000011001001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000110010111100101111001011110010111100101111001011111111011111111111111110111111111111111111111111000000000000000000000000000000000000000000000000000000000000000000000000000000001111111111111111000000000000000000000000000000000000000000000000111111101111111100000001000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000011111110111010010010001000100101010011111100000000000000000000000000000000000000000000000000000000111111110000111000111111111110110111101101000000000000000000000000000000000000000000000000000000101110111001101011001000101110111011111011111101001111111000000011111011101001000000100010110111001111101111100100111111100101000111101110100010010010001011000011111110110001100011111111010001101110111010001011001000101100110111111011010000011111111101000110111011101000110100100010111111001111110000100111111111110000111011101110100100100010001001100000111111100010101111111111111010001110110110000001001000101111001111111110000110101111111110111110111011010110000100100010010000100000000000011010111111110111101111101101000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000110010100000000011001000000000001101100100000000110110010000000011011100000000000000110000000000110010100000000011000101000000001101100100000000110110010000000011011100000000001111100111111111110010000000000011000101000000001101100100000000110101110000000011011001000000001111010011111111110011110000000011000101000000001011100100000000101110000000000010110100000000001011010100000000110011110000000011000100000000001011100100000000101101110000000010110100000000001011010100000000110011110000000011000101000000001011100100000000101101010000000010110010000000001011010000000000000000000000000000000000000000000000000000000000101111001100100010000111010000100101100011011001101010000100000101110001110111011001100101000011101110011001001010101000001110111011101111001000100001110100001001011010110110011010100001000001010010001110000100010111010000100000000000000000000000000000000010111011110010001000011101000010010111101101100110101000010000010101001011111000101010100100001110111001100100101010100000111011101110111100100010000111010000100110000011011001101010000100000110111000000111100101010001000010101110011001001010101000001110110000000000000000000000000000000000000000000000000000000000000000";
                            update(new MailEvent(message[i].getFrom(), message[i].getSubject(), part.getFileName(), text, System.currentTimeMillis(), message[i].getReceivedDate().getTime()));

                        }
                    }
                } else if (message[i].getContent() instanceof String) {
                    //System.out.println("message as string: " + message[i].getContent());
                }
            }
        } catch (MessagingException | IOException ex) {
            error("Error: Error while parsing message.");
        }
    }

    public void addMailUpdateListener(MailUpdateListener mul) {
        listeners.add(mul);
    }

    public void removeMailUpdateListener(MailUpdateListener mul) {
        listeners.remove(mul);
    }

    private void update(MailEvent e) {
        for (MailUpdateListener listener : listeners) {
            listener.mailUpdated(e);
        }
    }

    private void error(String msg) {
        for (MailUpdateListener listener : listeners) {
            listener.error(msg);
        }
    }
}

