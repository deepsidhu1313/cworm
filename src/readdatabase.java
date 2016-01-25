
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;



public class readdatabase{

    Database db= new Database();
static ArrayList data = new ArrayList();
    { 
    try {
            
            int numb=0;
            String temp2[],readline;
            
            FileReader fReader = new FileReader("antivirus signautres.adb");
            BufferedReader inFile = new BufferedReader(fReader);
            String input;
            while ((input = inFile.readLine()) != null) {
                System.out.println("" + input);
                temp2=input.split(" &&@ ");
              //  readline=input;
                for(int j=0;j<=temp2.length;j++){
                //{
                 //data.add(readline);
                   
                data.add(temp2[j].trim());
              
                   System.out.println(""+temp2[j]);
                }
                          
            }
            System.out.println("---------End of File");
        } catch (Exception e) {
            System.out.println("Array list" + data);
            System.out.println("ERROR" + e);
        }

  
    
    
    
    
    }
    
    public static void main(String args[])
    {
    readdatabase rt= new readdatabase();
    
    }
}