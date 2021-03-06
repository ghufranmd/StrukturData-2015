import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Upper{
    public static void main (String[] args){
        try {
            Upper x = new Upper();
            x.upper("sumber.txt","upper.txt");
        }
        catch (IOException kesalahan){
            System.out.printf("Terjadi kesalahan : %s",kesalahan);
        }
    }
    
   public void upper(String sumber, String sasaran) throws IOException {
        FileInputStream masukan = null;
        FileOutputStream keluaran = null;
        try {
            masukan = new FileInputStream(sumber);
            keluaran = new FileOutputStream(sasaran);
          
            int karakter = masukan.read();
            while (karakter != -1) {
               karakter = Character.toUpperCase(karakter);
                keluaran.write(karakter);
                karakter = masukan.read();
            }
            keluaran.flush();
        } 
        finally {
            if (masukan != null)
                masukan.close();
            if (keluaran != null)
                keluaran.close();
        }
    }
}