import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.ParseException;

public class UserInformation {

    private String userId;
    private String koordinat;
    ArrayList<Object[]> userData;

    public void setUserId(String userId) {
        this.userId = userId;
        userData = new ArrayList<Object[]>();
        setPosisi("0 0");
    }

    public void setPosisi(String koordinat) {
        Object[] data = new Object[2];
        data[0]= koordinat;
        data[1]= Calendar.getInstance().getTime();
        userData.add(data);
    }

    public String getID(){
        return userId;
    }

    public String getStep(){
        String hasil="";
        for(int i=0; i<userData.size();i++){
            hasil+=(userData.get(i))[0]+" ";
        }
        return hasil;
    }

    public int getStep(String waktuAwal, String waktuAkhir){
        try {
            int hasil=0;
            String[] timeAwal=waktuAwal.split(":");
            String[] timeAkhir=waktuAkhir.split(":");

            Calendar value;
            Date awal,akhir,current;

            value = Calendar.getInstance();
            value.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeAwal[0]));
            value.set(Calendar.MINUTE, Integer.parseInt(timeAwal[1]));
            awal=value.getTime();

            value = Calendar.getInstance();
            value.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeAkhir[0]));
            value.set(Calendar.MINUTE, Integer.parseInt(timeAkhir[1]));
            akhir=value.getTime();

            for(int i=0; i<userData.size() ;i++){
                current=(Date)((userData.get(i))[1]);
                if(current.after(awal)&&current.before(akhir))
                    synchronized(this){
                        hasil++;
                    }
                else if(current.after(akhir))
                    break;
            }
            return hasil-1;
        } catch (Exception e) {
            return -1;
        }
    }

    public String getRute(String waktuAwal, String waktuAkhir){
        try {
            String hasil="";
            String[] timeAwal=waktuAwal.split(":");
            String[] timeAkhir=waktuAkhir.split(":");

            System.out.println(timeAwal[0]+" "+timeAwal[1]);

            Calendar value;
            Date awal,akhir,current;

            value = Calendar.getInstance();
            value.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeAwal[0]));
            value.set(Calendar.MINUTE, Integer.parseInt(timeAwal[1]));
            awal=value.getTime();

            value = Calendar.getInstance();
            value.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeAkhir[0]));
            value.set(Calendar.MINUTE, Integer.parseInt(timeAkhir[1]));
            akhir=value.getTime();

            for(int i=0; i<userData.size() ;i++){
                current=(Date)((userData.get(i))[1]);
                if(current.after(awal)&&current.before(akhir))
                    hasil+="("+(String)((userData.get(i))[0])+") ";
                else if(current.after(akhir))
                    break;
            }

            return hasil;
        } catch (Exception e) {
            return null;
        }
    }

    public String getRute(){
        String hasil="";
        for(int i=0; i<userData.size() ;i++){
            hasil+="("+(String)((userData.get(i))[0])+") ";
        }
        return hasil;
    }
}