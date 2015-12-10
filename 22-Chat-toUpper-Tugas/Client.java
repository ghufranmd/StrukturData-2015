import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;

import java.util.Scanner;

public class Client {
    public void chatToUpper() 
    throws UnknownHostException, IOException {
        Socket socket = new Socket("localhost", 33333);

        try {
            // Ketik
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Pesan : ");
            String line = keyboard.nextLine();

            // Tulis ke socket
            Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
            BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
            keluaranBuff.write(line);
            keluaranBuff.write("\n");
            keluaranBuff.flush();

            // Baca dari Server
            System.out.print("Dari server : ");
            Reader masukan = new InputStreamReader(socket.getInputStream()); 
            BufferedReader masukanBuff = new BufferedReader(masukan);
            String baris = masukanBuff.readLine();
            System.out.println(baris); 

            //Mengubah balasan dari server ke dalam bentuk Upper
            baris = baris.toUpperCase();

            // Tulis kembali ke socket dengan kondisi Upper
            keluaranBuff.write(baris);
            keluaranBuff.flush();

            // Pesan berhasil terkirim
            System.out.println("Pesan Upper berhasil terkirim");
            System.out.println();
        }
        catch(IOException salah) {
            System.out.println(salah);
        }
        finally {
            if (socket != null)
                socket.close();
        }
    }
}
