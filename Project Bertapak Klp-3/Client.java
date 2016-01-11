import java.net.Socket;
import java.net.InetAddress;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

public class Client {    
    public String perintah(String ipStr, int port, String perintah) 
           throws IOException {
        String balasan = null;
        
        // Buka socket ke server
        InetAddress ip = InetAddress.getByName(ipStr);
        Socket koneksi = new Socket(ip, port);

        // Minta stream untuk tulis
        OutputStream keluaran = koneksi.getOutputStream();
        BufferedWriter keluaranWriter = new BufferedWriter(new OutputStreamWriter(keluaran)); 
        
        // Minta stream untuk baca
        InputStream masukan = koneksi.getInputStream();
        BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 

        // Kirim perintah ke server
        keluaranWriter.write(perintah, 0, perintah.length());               
        keluaranWriter.newLine();
        keluaranWriter.flush();
        
        perintah = perintah.trim().toUpperCase();
        
        // Ambil parameter-nya
        String[] parameter = perintah.split(" ");
        
        // Baca balasan dari Server
         if (parameter[0].compareTo("ID") == 0) {
            // Baca balasan dari server        
            balasan = masukanReader.readLine();
            while (balasan == null) {
                // coba terus sampai ada balasan
                balasan = masukanReader.readLine();
            }
        }else  if (parameter[1].compareTo("Koordinat") == 0) {
            // Baca balasan dari server        
            balasan = masukanReader.readLine();
            while (balasan == null) {
                // coba terus sampai ada balasan
                balasan = masukanReader.readLine();
            }
        }else  if (parameter[0].compareTo("LANGKAH") == 0) {
            // Baca balasan dari server        
            balasan = masukanReader.readLine();
            while (balasan == null) {
                // coba terus sampai ada balasan
                balasan = masukanReader.readLine();
            }
        }
        //balasan = masukanReader.readLine();

        // Tutup koneksi
        koneksi.close();
        
        // Kembalikan balasan
        return balasan;
    }
}
