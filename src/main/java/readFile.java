import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;




public class readFile { public static void main(String[] args) {
        String datStart;
        String datStop;
        long milis;
        double minutHightTarif;
        int lenghtBasicTarif =5;
        double priceHightTarif = 1.0;
        double priceReducedTarif = 0.2;
        double privceNightTarif = 0.5;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Calendar kalStart = Calendar.getInstance();
        Calendar kalStop = Calendar.getInstance();
         String file = "C:/Users/milos/Dropbox/ItNetwork/ukolArbes/src/main/resources/data.txt";
         String delimiter = ",";
         String line;
         ArrayList lines = new ArrayList();
         try (BufferedReader br = new BufferedReader(new FileReader(file))) { while((line = br.readLine()) != null)
         { List values = Arrays.asList(line.split(delimiter));
             lines.add(values); }
             lines.forEach(l -> System.out.println(l)); }
         catch (Exception e){ System.out.println(e); }


        System.out.println("***********************************************");

        for (int i=0  ; i < lines.size(); i++ ) {
           datStart = lines.get(i).toString().substring(15,34);
           LocalDateTime startDateTime = LocalDateTime.parse(datStart, dtf);
           kalStart.set(startDateTime.getYear(),startDateTime.getMonthValue(),startDateTime.getDayOfMonth(),startDateTime.getHour(),startDateTime.getMinute(),startDateTime.getSecond());

           datStop = lines.get(i).toString().substring(36,55);
           LocalDateTime stopDateTime = LocalDateTime.parse(datStop, dtf);
            kalStop.set(stopDateTime.getYear(),stopDateTime.getMonthValue(),stopDateTime.getDayOfMonth(),stopDateTime.getHour(),stopDateTime.getMinute(),stopDateTime.getSecond());
           // System.out.println(i);
            System.out.println(datStart + "  *  " + datStop);
            System.out.println(startDateTime + "  -  " + stopDateTime + "  #  "+ kalStart.getTimeInMillis()+ " | " + kalStop.getTimeInMillis() +"  <>  " + ((kalStop.getTimeInMillis() - kalStart.getTimeInMillis())/1000 ));

            if (startDateTime.getHour()>= 8 && stopDateTime.getHour()<16){
                System.out.println("HightTarif");
                System.out.println(Math.ceil((kalStop.getTimeInMillis() - kalStart.getTimeInMillis())/1000L/(double) 60)  );
                minutHightTarif = (Math.ceil((kalStop.getTimeInMillis() - kalStart.getTimeInMillis())/1000L/(double) 60)  );
                System.out.println(minutHightTarif);
                if (minutHightTarif >= lenghtBasicTarif){
                    System.out.println("cena " + (((minutHightTarif - lenghtBasicTarif) * 0.2) + (lenghtBasicTarif * priceHightTarif)));
                }
                else System.out.println("Cena " +  (minutHightTarif * priceHightTarif));

            }
            else if (startDateTime.getHour()<8 && stopDateTime.getHour()>=8 && stopDateTime.getHour()<16 ) {

            }
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - ");
        }





        }
     }

