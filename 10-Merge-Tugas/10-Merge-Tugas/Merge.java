import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Merge {

    public void merge (String file1, String file2, String file3, String sasaran)throws IOException {
         FileInputStream merge1 = null;
         FileInputStream merge2 = null;
         FileInputStream merge3 = null;
         FileOutputStream keluaran = null;
        try {
            merge1 = new FileInputStream(file1);
            merge2 = new FileInputStream(file2);
            merge3 = new FileInputStream(file3);
            keluaran = new FileOutputStream(sasaran);
            
            int karakter = merge1.read();
            
            while (karakter != -1) {
                keluaran.write(karakter);
                karakter = merge1.read();
            }
            
            
            keluaran = new FileOutputStream(sasaran,true);
            karakter = merge2.read();
            while (karakter != -1) {
                keluaran.write(karakter);
                karakter = merge2.read();
            }
            
            
            keluaran = new FileOutputStream(sasaran,true);
            karakter = merge3.read();
            while (karakter != -1) {
                keluaran.write(karakter);
                karakter = merge3.read();
            }
            
            keluaran.flush();
        } 
        finally {
            if (merge1 !=null)
                merge1.close();
            if (merge2 != null)
                merge2.close();
            if (merge3 != null)
                merge3.close(); 
                
        
           if (keluaran != null)
            keluaran.close();
        }
    }
}