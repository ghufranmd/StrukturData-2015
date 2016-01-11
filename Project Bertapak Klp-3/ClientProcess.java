import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.util.Date;
import java.util.Calendar;

public class ClientProcess implements Runnable {

    public ClientProcess(Socket koneksi, ArrayList <UserInformation> ID ){
        this.koneksi = koneksi;
        this.ID=ID;
    }

    public void run() {        
        if (koneksi != null) {
            // Ambil IP dari koneksi
            ipStr = koneksi.getInetAddress().getHostAddress();

            try {
                // Ambil InputStream
                masukan = koneksi.getInputStream();
                masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
                // Ambil OutputStream
                keluaran = koneksi.getOutputStream();
                keluaranWriter = new BufferedWriter(new OutputStreamWriter(keluaran)); 

                // Selama masih terhubung dengan client tangani
                tangani();
            }
            catch(IOException salah) { 
                System.out.println(salah);
            }
            finally {
                try { 
                    // Tutup koneksi
                    koneksi.close();
                }
                catch(IOException salah) {
                    System.out.println(salah);
                }                
            }
        }
    }

    private void tangani() throws IOException {
        String perintah = masukanReader.readLine();
        Date date = new Date();
        // Keluar jika tidak ada perintah
        if (perintah == null)
            return;            
        // Ada perintah, hilangkan spasi depan & belakang serta ubah ke huruf besar semua
        perintah = perintah.trim().toUpperCase();

        // Ambil parameter-nya
        String[] parameter = perintah.split(" ");

        // Tangani perintahnya
        if (parameter[0].compareTo("ID") == 0 && parameter[2].equals ("MULAI") && parameter.length == 3) {
            UserInformation idStr = null;
            UserInformation userInfo = infoCheck(parameter[1]);
            String balasan;

            if(userInfo != null){
                balasan="ID telah dipakai";
            }else{
                idStr = new UserInformation();
                idStr.setUserId(parameter[1]);
                ID.add(idStr);
                balasan="ID telah berhasil didaftar";
            }
            // Hanya bertanya siapa yang mengirim
            keluaranWriter.write(balasan);
            System.out.print("ID : "+parameter[1]);
            System.out.println(", "+date.toString());
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }else if(parameter[0].compareTo("LANGKAH") == 0 && parameter.length == 4){
            UserInformation userInfo = infoCheck(parameter[3]);
            String balasan;

            if(userInfo == null){
                balasan="ID anda belum terdaftar";
            }else{
                String koor = ""+parameter[1]+" "+parameter[2];
                userInfo.setPosisi(koor);
                balasan="Koordinat telah disimpan";
            }
            keluaranWriter.write(balasan);
            System.out.print("ID : "+parameter[3]);
            System.out.println(", "+date.toString());
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }else if(parameter[0].compareTo("ID") == 0 && parameter[2].equals("STEP") && parameter.length == 5){
            UserInformation userInfo = infoCheck(parameter[1]);
            String balasan;
            UserInformation userData = null;
            if(userInfo == null){
                balasan="ID anda belum terdaftar";
            }else{
                int hasil = userInfo.getStep(parameter[3],parameter[4]);
                if(hasil>=0)
                    balasan="Jumlah langkah anda adalah "+hasil;
                else
                    balasan="Format waktu salah";
            }
            keluaranWriter.write(balasan, 0, balasan.length());
            System.out.print("ID : "+parameter[1]);
            System.out.println(", "+date.toString());
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }else if(parameter[0].compareTo("ID") == 0 && parameter[2].equals("RUTE") && parameter.length == 5){
            UserInformation userInfo = infoCheck(parameter[1]);
            String balasan;
            UserInformation userData = null;
            if(userInfo == null){
                balasan="ID anda belum terdaftar";
            }else{
                String hasil = userInfo.getRute(parameter[3],parameter[4]);
                if(hasil!=null)
                    balasan="Rute anda adalah "+hasil;
                else
                    balasan="Format waktu salah";
            }
            keluaranWriter.write(balasan, 0, balasan.length());
            System.out.print("ID : "+parameter[1]);
            System.out.println(", "+date.toString());
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }
        else{
            keluaranWriter.write(PerintahSalah, 0,  PerintahSalah.length());
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }

        // Tampilkan perintahnya
        System.out.println("Dari: " +ipStr);
        System.out.println("perintah: " +perintah);
        System.out.println();

    }

    public UserInformation infoCheck(String ClientID){
        for(UserInformation info : ID){
            if(ClientID.equals(info.getID())){
                return info;
            }
        }
        return null;
    }
    ArrayList<UserInformation> ID = new ArrayList<UserInformation>();
    // Koneksi ke Client
    private Socket koneksi; 
    // IP address dari client
    private String ipStr; 

    // InputStream untuk baca perintah
    private InputStream masukan = null;
    // Reader untuk InputStream, pakai yang buffer
    private BufferedReader masukanReader = null;
    // OutputStream untuk tulis balasan
    private OutputStream keluaran = null;
    // Writer untuk OutputStream, pakai yang buffer
    private BufferedWriter keluaranWriter = null;

    private final static String PerintahSalah = "Perintah salah!";
}