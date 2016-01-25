
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;

class Database implements Runnable {

    int totalFolder = -1;
    int totalFile = 0;
    PrintStream out, sign;
    String folder = "avdb";
    ;
  static Database size;

    Database() {
        Thread db = new Thread(this);
        db.start();

    }

    public static void main(String args[]) {
        {

            try {
                size = new Database();

            } catch (Exception e) {
            }
        }

        //Database  size=new Database();
    }

    public void run() {
        long fileSizeByte = size.getFileSize(new File(folder));
//  PrintStream out;

        System.out.println("Folder Size: " + fileSizeByte + " Bytes");
        System.out.println("Total Number of Folders: " + size.getTotalFolder());
        System.out.println("Total Number of Files: " + size.getTotalFile());
    }

    public long getFileSize(File folder) {
        totalFolder++;
        try {
            out = new PrintStream(("antivirus signautres.adb"));
            sign = new PrintStream(("fnamesig.adb"));
        } catch (Exception ew) {
        }

        System.out.println("Folder: " + folder.getName());
        long foldersize = 0;

        File[] filelist = folder.listFiles();
        for (int i = 0; i < filelist.length; i++) {
            if (filelist[i].isDirectory()) {
                foldersize += getFileSize(filelist[i]);
                System.out.println("- " + filelist[i].getName());
                sign.append("- " + filelist[i].getName());
                // System.out.println( "- " + filelist[i].getAbsolutePath());

            } else {
                totalFile++;

                foldersize += filelist[i].length();
                //System.out.println( "- " + filelist[i].getName());
                //System.out.println( "- " + filelist[i].getAbsolutePath());
                String name = "" + filelist[i].getName();
                sign.print("- " + filelist[i].getName());
                try {

                    String temp2[], readline;
                    FileReader fReader = new FileReader("" + filelist[i].getAbsolutePath());
                    BufferedReader inFile = new BufferedReader(fReader);
                    String input;
                    while ((input = inFile.readLine()) != null) {
                        // System.out.println("" + input);

                        readline = input;
                        out.print("\t " + readline);
                        out.print("\t");
                        sign.append("" + readline);
                        sign.append("\t");

                    }
                    out.print(" &&@ ");
                    sign.append(" &&@ ");
                    System.out.println("---------End of File");
                } catch (Exception e) {
                    System.out.println("ERROR" + e);
                }
                // if(name.contains(".exe")){
                //System.out.println( "- " + filelist[i].getName()+ "is Excetuable" );

                // System.out.println( "- " + filelist[i].getAbsolutePath());
                // out.close();
                // sign.close();
            }
        }

        return foldersize;
    }

    public int getTotalFolder() {
        return totalFolder;
    }

    public int getTotalFile() {
        return totalFile;
    }
}
