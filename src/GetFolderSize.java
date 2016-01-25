import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GetFolderSize {

  int totalFolder=0;
  int totalFile=0;
  File[] filelist;
  public static void main(String args [])
  {  
  String folder = "C:/";
  try{
      new readdatabase();
  GetFolderSize size=new GetFolderSize();
  long fileSizeByte=size.getFileSize(new File(folder));
  System.out.println("Folder Size: "+fileSizeByte+" Bytes" );
  System.out.println("Total Number of Folders: "+size.getTotalFolder());
  System.out.println("Total Number of Files: "+size.getTotalFile());
  }catch (Exception e)
  {}
  }
  public long getFileSize(File folder) {
  totalFolder++; 
  System.out.println("Folder: " + folder.getName());
  long foldersize = 0;
  if((folder.listFiles()==null)&& (folder.isDirectory())){
  System.out.println("Folder can't read");
    return foldersize;
  }
  
  File[] filelist = folder.listFiles();
  for (int i = 0; i < filelist.length; i++) {
  if (filelist[i].isDirectory()) {
  foldersize += getFileSize(filelist[i]);
  } else {
  totalFile++;
  foldersize += filelist[i].length();
   System.out.println( "- " + filelist[i].getAbsolutePath());

  
  }}
  
  
  


  return foldersize;
  }
  public int getTotalFolder() {
  return totalFolder;
  }
  public int getTotalFile() {
  return totalFile;
  }
}