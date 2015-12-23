import java.util.Scanner;
import java.net.Socket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Calendar;

public class ProcessThread implements Runnable{
    private Socket koneksi;
    private String perintah;
    public int N;
    String pesanServer=null;
    OutputStream keluaran=null;
    BufferedWriter keluaranBuf=null;
    

    public ProcessThread(Socket koneksiKiriman, int angka) {
        koneksi = koneksiKiriman;
        this.perintah=""+perintah;
    }

    public void run()
    {
        try{
            if (koneksi != null)
                prosesPermintaanClient();
        }catch(IOException err) {
            System.out.println(err);
        }
    }

    private void prosesPermintaanClient() throws IOException 
    {
        String ip = koneksi.getInetAddress().getHostAddress();
        System.out.println("Dari: " + ip);
        String pesanServer=null;
     
        for(N=1;N<10;N++)
        {
            // Ambil dan tampilkan masukan
            InputStream masukan = koneksi.getInputStream();
            BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
            String perintah = masukanReader.readLine();
            System.out.println("Perintah Client : "+perintah);

            if(perintah.equalsIgnoreCase("SIAPA"))
                synchronized(this){
                    OutputStream keluaran = koneksi.getOutputStream();
                    BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                    keluaranBuf.write(ip);
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                    koneksi.close();
                }
            else if (perintah.equalsIgnoreCase("WAKTU"))
                synchronized(this) {
                    OutputStream keluaran = koneksi.getOutputStream();
                    BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                    Calendar kalender = Calendar.getInstance();
                    String waktuStr = kalender.getTime().toString();
                    keluaranBuf.write(waktuStr);
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                    koneksi.close();
                }
            else{
                synchronized(this){
                    OutputStream keluaran = koneksi.getOutputStream();
                    BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                    keluaranBuf.write("Perintah tidak dikenal!");
                    keluaranBuf.newLine();
                    keluaranBuf.flush();
                    koneksi.close();
                }
            }
        }
    }
}