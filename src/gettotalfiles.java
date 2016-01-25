
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.lang.Thread;

public class gettotalfiles //implements Runnable{
{

    int totalFolder = 0;
    int totalFile = 0;
    File[] filelist;
    File folder23;
    //long foldersize = 0;
    int parameter = 0;
    File tfolder, tfolder1, tfolder2, tfolder3, tfolder4, tfolder5, tfolder6, tfolder7, tfolder8;

    gettotalfiles(File folder) {
        tfolder = folder;
        parameter = 1;
        int totalfiles = getFileSize((tfolder));
        System.out.println("Total Files: " + totalfiles);
        //    Thread gf= new Thread(this);
        //gf.start();
    }

    gettotalfiles(File folder, File folder1) {
        parameter = 2;
        tfolder = folder;
        tfolder1 = folder1;
        int totalfiles = getFileSize((tfolder));
        totalfiles += getFileSize((tfolder1));
        System.out.println("Total Files: " + totalfiles);
        //  Thread gf= new Thread(this);
        //gf.start();
    }

    gettotalfiles(File folder, File folder1, File folder2) {
        parameter = 3;
        tfolder = folder;
        tfolder1 = folder1;
        tfolder2 = folder2;

        int totalfiles = getFileSize((tfolder));
        totalfiles += getFileSize((tfolder1));
        totalfiles += getFileSize((tfolder2));
        // Thread gf= new Thread(this);
        //gf.start();
    }

    gettotalfiles(File folder, File folder1, File folder2, File folder3) {
        parameter = 4;
        tfolder = folder;
        tfolder1 = folder1;
        tfolder2 = folder2;
        tfolder3 = folder3;

        int totalfiles = getFileSize((tfolder));
        totalfiles += getFileSize((tfolder1));
        totalfiles += getFileSize((tfolder2));
        totalfiles += getFileSize((tfolder3));
        System.out.println("Total Files: " + totalfiles);
        //  Thread gf= new Thread(this);
        //gf.start();
    }

    gettotalfiles(File folder, File folder1, File folder2, File folder3, File folder4) {
        parameter = 5;
        tfolder = folder;
        tfolder1 = folder1;
        tfolder2 = folder2;
        tfolder3 = folder3;
        tfolder4 = folder4;

        int totalfiles = getFileSize((tfolder));
        totalfiles += getFileSize((tfolder1));
        totalfiles += getFileSize((tfolder2));
        totalfiles += getFileSize((tfolder3));
        totalfiles += getFileSize((tfolder4));
        System.out.println("Total Files: " + totalfiles);
    }

    gettotalfiles(File folder, File folder1, File folder2, File folder3, File folder4, File folder5) {
        parameter = 6;
        tfolder = folder;
        tfolder1 = folder1;
        tfolder2 = folder2;
        tfolder3 = folder3;
        tfolder4 = folder4;
        tfolder5 = folder5;

        int totalfiles = getFileSize((tfolder));
        totalfiles += getFileSize((tfolder1));
        totalfiles += getFileSize((tfolder2));
        totalfiles += getFileSize((tfolder3));
        totalfiles += getFileSize((tfolder4));
        totalfiles += getFileSize((tfolder5));
        System.out.println("Total Files: " + totalfiles);

    }

    gettotalfiles(File folder, File folder1, File folder2, File folder3, File folder4, File folder5, File folder6) {
        parameter = 7;
        tfolder = folder;
        tfolder1 = folder1;
        tfolder2 = folder2;
        tfolder3 = folder3;
        tfolder4 = folder4;
        tfolder5 = folder5;
        tfolder6 = folder6;

        int totalfiles = getFileSize((tfolder));
        totalfiles += getFileSize((tfolder1));
        totalfiles += getFileSize((tfolder2));
        totalfiles += getFileSize((tfolder3));
        totalfiles += getFileSize((tfolder4));
        totalfiles += getFileSize((tfolder5));
        totalfiles += getFileSize((tfolder6));
        System.out.println("Total Files: " + totalfiles);

    }

    gettotalfiles(File folder, File folder1, File folder2, File folder3, File folder4, File folder5, File folder6, File folder7) {
        parameter = 8;

        tfolder = folder;
        tfolder1 = folder1;
        tfolder2 = folder2;
        tfolder3 = folder3;
        tfolder4 = folder4;
        tfolder5 = folder5;
        tfolder6 = folder6;
        tfolder7 = folder7;

        int totalfiles = getFileSize((tfolder));
        totalfiles += getFileSize((tfolder1));
        totalfiles += getFileSize((tfolder2));
        totalfiles += getFileSize((tfolder3));
        totalfiles += getFileSize((tfolder4));
        totalfiles += getFileSize((tfolder5));
        totalfiles += getFileSize((tfolder6));
        totalfiles += getFileSize((tfolder7));

        System.out.println("Total Files: " + totalfiles);
    }

    gettotalfiles(File folder, File folder1, File folder2, File folder3, File folder4, File folder5, File folder6, File folder7, File folder8) {
        parameter = 9;
        tfolder = folder;
        tfolder1 = folder1;
        tfolder2 = folder2;
        tfolder3 = folder3;
        tfolder4 = folder4;
        tfolder5 = folder5;
        tfolder6 = folder6;
        tfolder7 = folder7;
        tfolder8 = folder8;

        int totalfiles = getFileSize((tfolder));
        totalfiles += getFileSize((tfolder1));
        totalfiles += getFileSize((tfolder2));
        totalfiles += getFileSize((tfolder3));
        totalfiles += getFileSize((tfolder4));
        totalfiles += getFileSize((tfolder5));
        totalfiles += getFileSize((tfolder6));
        totalfiles += getFileSize((tfolder7));

        totalfiles += getFileSize((tfolder8));
        System.out.println("Total Files: " + totalfiles);

    }

    public int getFileSize(File folder) {
        totalFolder++;
        folder23 = folder;
        long foldersize = 0;
        try {
            System.out.println("Folder: " + folder23.getName());
        } catch (Exception fn) {
        }
        if (((folder.listFiles() == null) && (folder.isDirectory())) || !folder.exists()) {
            System.out.println("Folder can't read");
            totalFolder--;
            return totalFile;
        }

        File[] filelist = folder.listFiles();
        for (int i = 0; i < filelist.length; i++) {
            if (filelist[i].isDirectory()) {
                foldersize += getFileSize(filelist[i]);
            } else {
                totalFile++;
                foldersize += filelist[i].length();
                //  System.out.println( "- " + filelist[i].getAbsolutePath());
            }
        }
        return totalFile;

    }

    public int getTotalFolder() {
        return totalFolder;
    }

    public int getTotalFile() {
        return totalFile;
    }

    /*  public void run(){
  if (parameter==1){
  
    int totalfiles=getFileSize((tfolder));
  
  System.out.println("Total Files: "+totalfiles);
 //  System.out.println("Total Number of Folders: "+getTotalFolder());
//  System.out.println("Total Number of Files: "+getTotalFile());
  }
  else if(parameter==2){
  int totalfiles=getFileSize((tfolder));
  totalfiles += getFileSize((tfolder1));
  
  System.out.println("Total Files: "+totalfiles);
      
      
  }else if(parameter==3){
  int totalfiles=getFileSize((tfolder));
  totalfiles += getFileSize((tfolder1));
  totalfiles += getFileSize((tfolder2));
  
  System.out.println("Total Files: "+totalfiles);
      
      
  }else if(parameter==4){
  int totalfiles=getFileSize((tfolder));
  totalfiles += getFileSize((tfolder1));
  totalfiles += getFileSize((tfolder2));
  totalfiles += getFileSize((tfolder3));
  
  System.out.println("Total Files: "+totalfiles);
      
      
  }else if(parameter==5){
  
  int totalfiles=getFileSize((tfolder));
  totalfiles += getFileSize((tfolder1));
  totalfiles += getFileSize((tfolder2));
  totalfiles += getFileSize((tfolder3));
  totalfiles += getFileSize((tfolder4));
  
  System.out.println("Total Files: "+totalfiles);
      
      
  }else if(parameter==6){
  int totalfiles=getFileSize((tfolder));
  totalfiles += getFileSize((tfolder1));
  totalfiles += getFileSize((tfolder2));
  totalfiles += getFileSize((tfolder3));
  totalfiles += getFileSize((tfolder4));
  totalfiles += getFileSize((tfolder5));
  
  System.out.println("Total Files: "+totalfiles);
      
      
  }else if(parameter==7){
  int totalfiles=getFileSize((tfolder));
  totalfiles += getFileSize((tfolder1));
  totalfiles += getFileSize((tfolder2));
  totalfiles += getFileSize((tfolder3));
  totalfiles += getFileSize((tfolder4));
  totalfiles += getFileSize((tfolder5));
  totalfiles += getFileSize((tfolder6));
  
  System.out.println("Total Files: "+totalfiles);
      
      
  }else if(parameter==8){
  int totalfiles=getFileSize((tfolder));
  
  totalfiles += getFileSize((tfolder1));
  totalfiles += getFileSize((tfolder2));
  totalfiles += getFileSize((tfolder3));
  totalfiles += getFileSize((tfolder4));
  totalfiles += getFileSize((tfolder5));
  totalfiles += getFileSize((tfolder6));
  totalfiles += getFileSize((tfolder7));
  System.out.println("Total Files: "+totalfiles);
      
      
  }else if(parameter==9){

      
  }*/
    public static void main(String args[]) {
        String folder = "C:/dx";
        String folder1 = "A:/WINDOWS";
        String folder2 = "A:/Program Files";
        try {
            gettotalfiles tf = new gettotalfiles(new File(folder));
            //long fileSizeByte=tf.getFileSize(new File(folder));

            // System.out.println("Total Number of Folders: "+tf.getTotalFolder());
            // System.out.println("Total Number of Files: "+tf.getTotalFile());
        } catch (Exception e) {
        }
    }
}
