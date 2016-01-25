
import java.io.BufferedReader;
import java.io.InputStreamReader;

class NewEmpty{
String tmp="";
 public void getexepth(){
    try {
       //sleep(5000);
        String line;
        
        System.out.println("Get Virus path Called");
        Runtime systemShell = Runtime.getRuntime();
     Process getpth = systemShell.exec("wmic  process get description,executablepath");
        
        
        //Process getpth = Runtime.getRuntime().exec("wmic /OUTPUT:C:\\ProcessList.txt process get description,executablepath");
        BufferedReader input2 =
                new BufferedReader(new InputStreamReader(getpth.getInputStream()));
        System.out.println("Get Virus path p excecuted");
        while (((line = input2.readLine()) != null)) {
            //System.out.println(line); //<-- Parse data here.
        System.out.println("getting err");
            tmp=line;
            System.out.println("Appmonitor is running");
            System.out.println("getting err"+tmp);
        
        
        }
         System.out.println("get");
        input2.close();
    } catch (Exception err) {
        System.out.println("Get path error"+err);
    }
    
    
    
    }
public static void main(String args[]){

NewEmpty test=new NewEmpty();
test.getexepth();
}
}