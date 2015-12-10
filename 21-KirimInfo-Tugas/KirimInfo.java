import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.BufferedInputStream;

public class KirimInfo {    

    public static void main(String[] args) 
    throws UnknownHostException, IOException {
        String data = "Ghufran Mochammad (1408107010074)";
        String ip = "192.168.212.1";
        Socket koneksi = new Socket(ip, 33333);
        OutputStream keluaran = koneksi.getOutputStream();
        Writer keluaranWriter = new OutputStreamWriter(keluaran); 
        keluaranWriter.write(data);
        keluaranWriter.write("\r\n");
        keluaranWriter.flush();
        koneksi.close();
    }
}
