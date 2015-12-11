import java.net.Socket;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedInputStream;
import java.io.Writer;
import java.io.BufferedWriter;

public class WebCrawler {    
    public static void main(String[] args) {
        try {
            WebCrawler tanya = new WebCrawler();
            tanya.index();
        }
        catch (UnknownHostException ex) {
            System.err.println(ex);
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    public void index() 
    throws UnknownHostException, IOException {
        // 0. Buka socket
        koneksi = new Socket("google.co.id", 80);

        // Kirim perintah untuk informasi namaDomain
        kirimPerintah("GET index.html");

        // Baca balasannya
        bacaBalasan();

        // Tutup socket-nya => dengan sendirinya menutup semua stream
        koneksi.close();
    }

    public void kirimPerintah(String namaDomain) throws IOException {
        Writer keluaranWriter = new OutputStreamWriter(koneksi.getOutputStream()); 
        BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
        keluaranBuff.write(namaDomain);
        keluaranBuff.newLine();
        keluaranBuff.flush();

    }

    public void bacaBalasan() throws IOException {
        // 1 & 2. Minta socket untuk baca -> Langsung dibuka
        InputStream masukan = koneksi.getInputStream();
        // Karena keluarannya panjang sehingga harus dibuffer
        BufferedInputStream masukanBuffer = new BufferedInputStream(masukan);
        // Selagi masih ada data baca
        int data = masukanBuffer.read();
        while (data != -1) {
            System.out.write((char) data);
            data = masukanBuffer.read();
        }
    }

    private Socket koneksi = null;
}
