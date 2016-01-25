
import java.io.File;
import javax.swing.UIManager;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicBorders;







class settings implements Runnable,ItemListener,ActionListener{

static JFrame settingframe;
static JCheckBox aggresive, mod,minimum;
JPanel jp1,jp2,jp3,jp4,bpnl,spnl,jp5;
ButtonGroup group ;
static JComboBox days,hrs,mins,secs;
static Boolean schstatus;
static JCheckBox enasch;
JButton custom,ok,globals,paths,scheduler,browse1,browse2,browse3;
static JTextField database,logs,charts;
JLabel jl1,jl2,jl3,slabl,icnl1,icnl2,icnl3;
PrintStream setting;
ImageIcon agg,med,min,glo,glo1,loc1,loc,sch,sch1,brw;
static int mode=2,jdiamode=0;
ButtonModel bm1;
settings(){
new scheduling();
Thread set= new Thread(this);
set.start();

}

public void run()

{
    
    agg= new ImageIcon("icons/agress.png");
    med= new ImageIcon("icons/mod.png");
    min= new ImageIcon("icons/min.png");
    glo= new ImageIcon("icons/gs.png");
    glo1= new ImageIcon();
    loc1= new ImageIcon("icons/gens.png");
    loc= new ImageIcon("icons/p1s.png");
    sch= new ImageIcon("icons/schs.png");
    sch1= new ImageIcon("icons/schs2.png");
    brw= new ImageIcon("icons/browse.png");
    bm1= new DefaultButtonModel();

         

    icnl1= new JLabel(agg);
    icnl2= new JLabel(med);
    icnl3= new JLabel(min);
settingframe=new JFrame("Settings");
jp1= new JPanel();
jp2= new JPanel();
jp3= new JPanel();
jp4= new JPanel();
bpnl= new JPanel();
spnl= new JPanel();
jp5= new JPanel();
group = new ButtonGroup();

aggresive= new JCheckBox("<HTML>Agressive: This will allow Antivirus to <BR> automatically Detect & Delete Viruses.</HTML>");
mod= new JCheckBox("<HTML>Moderate: This Will allow user to <BR> delete viruses on Detection.</HTML>");
minimum= new JCheckBox("<HTML>Minimium (Manual): User have to detect <BR> & delete virus manually by scanning.</HTML>");
jl1= new JLabel("Database Location :");
jl2= new JLabel("Logs :");
jl3= new JLabel("Charts :");

slabl= new JLabel("Automatic Full System Scan Starts On:");
database= new JTextField();
logs= new JTextField();
charts= new JTextField();
custom= new JButton("Custom");
ok= new JButton("OK");
globals= new JButton("<HTML>Globals<BR></HTML>",glo);
globals.setVerticalTextPosition(SwingConstants.BOTTOM);
globals.setHorizontalTextPosition(SwingConstants.CENTER);

paths= new JButton("Paths",loc);
paths.setRolloverIcon(loc1);
paths.setVerticalTextPosition(SwingConstants.BOTTOM);
paths.setHorizontalTextPosition(SwingConstants.CENTER);
scheduler= new JButton("Scheduler",sch);
scheduler.setRolloverIcon(sch1);
scheduler.setVerticalTextPosition(SwingConstants.BOTTOM);
scheduler.setHorizontalTextPosition(SwingConstants.CENTER);
String[] dys= {"Everyday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
days= new JComboBox(dys);

String[] hurs= {"HH","00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","19","19","20","21","22","23"};
hrs= new JComboBox(hurs);
mins= new JComboBox();
mins.addItem("MINS");
for(int mn=0;mn<=59;mn++)
{
mins.addItem(mn);
}
secs= new JComboBox();
secs.addItem("SECS");
for(int sc=0;sc<=59;sc++)
{
secs.addItem(sc);
}
browse1= new JButton("Browse",brw);

browse1.setVerticalTextPosition(SwingConstants.CENTER);
browse1.setHorizontalTextPosition(SwingConstants.LEFT);
browse2= new JButton("Browse",brw);
browse2.setVerticalTextPosition(SwingConstants.CENTER);
browse2.setHorizontalTextPosition(SwingConstants.LEFT);
browse3= new JButton("Browse",brw);
browse3.setVerticalTextPosition(SwingConstants.CENTER);
browse3.setHorizontalTextPosition(SwingConstants.LEFT);

enasch= new JCheckBox("Enable Scheduler");


settingframe.setLayout(null);

globals.setBounds(15, 15, 80, 90);
paths.setBounds(105, 15, 80, 90);
scheduler.setBounds(195, 15, 80, 90);
jp3.setBounds(5, 130, 450, 300);
jp4.setBounds(5, 130, 450, 300);
jp5.setBounds(5, 120, 450, 300);
bpnl.setBounds(5, 0, 450, 130);
jp1.setVisible(true);
jp2.setVisible(true);
jp3.setVisible(true);
jp4.setVisible(false);
spnl.setVisible(true);
jp5.setVisible(false);
aggresive.setBounds(30, 10, 215, 70);
mod.setBounds(30, 100, 215, 70);
minimum.setBounds(30, 200, 215, 70);
icnl1.setBounds(230, 10, 175, 70);
icnl2.setBounds(230, 100, 175, 70);
icnl3.setBounds(230, 200, 175, 70);
//custom.setBounds(110, 310, 100, 30);
ok.setBounds(300, 440, 100, 30);

jl1.setBounds(30, 10, 300, 30);
database.setBounds(30, 50, 250, 30);
jl2.setBounds(30, 100, 300, 30);
logs.setBounds(30, 140, 250, 30);
jl3.setBounds(30, 190, 300, 30);
charts.setBounds(30, 230, 250, 30);
browse1.setBounds(300, 50, 110, 30);
browse2.setBounds(300, 140, 110, 30);
browse3.setBounds(300, 230, 110, 30);


enasch.setBounds(20, 20, 300, 30);
slabl.setBounds(30, 70, 300, 30);
days.setBounds(50, 120, 100, 30);
hrs.setBounds(160, 120, 50, 30);
mins.setBounds(220, 120, 50, 30);
secs.setBounds(300, 120, 50, 30);

//jp1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
bpnl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
jp3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
jp4.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
jp5.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
group.add(aggresive);
group.add(mod);
group.add(minimum);


 settingframe.setIconImage(Toolkit.getDefaultToolkit().getImage("icons/sett2.png"));

jp2.add(jl1);
jp2.add(jl2);
jp2.add(jl3);
jp2.add(database);
jp2.add(logs);
jp2.add(charts);
jp2.add(browse1);
jp2.add(browse2);
jp2.add(browse3);

jp1.add(aggresive);
jp1.add(mod);
jp1.add(minimum);
jp1.add(icnl1);
jp1.add(icnl2);
jp1.add(icnl3);
//jp1.add(custom);

bpnl.add(globals);
bpnl.add(paths);
bpnl.add(scheduler);
bpnl.setLayout(null);




slabl.setEnabled(enasch.isSelected());
days.setEnabled(enasch.isSelected());
hrs.setEnabled(enasch.isSelected());
mins.setEnabled(enasch.isSelected());
secs.setEnabled(enasch.isSelected());


spnl.add(enasch);
spnl.add(slabl);
spnl.add(days);
spnl.add(hrs);
spnl.add(mins);
spnl.add(secs);


jp3.setLayout(new GridLayout(1,1));
jp4.setLayout(new GridLayout(1,1));
jp5.setLayout(new GridLayout(1,1));
spnl.setLayout(null);
jp1.setLayout(null);
jp2.setLayout(null);
jp3.add(jp1);
jp4.add(jp2);
jp5.add(spnl);
//settingframe.add(globals);
//settingframe.add(paths);
settingframe.add(ok);
settingframe.add(bpnl);
settingframe.add(jp3);
settingframe.add(jp4);
settingframe.add(jp5);
try{
        aggresive.addItemListener(this);
        aggresive.setSelected(true);
        mode=2;
        mod.addItemListener(this);
        minimum.addItemListener(this);
        enasch.addItemListener(this);
}
        catch (Exception err) 
        {}

    ok.addActionListener(this);
    globals.addActionListener(this);
    paths.addActionListener(this);
    scheduler.addActionListener(this);
browse1.addActionListener(this);
browse2.addActionListener(this);
browse3.addActionListener(this);

settingframe.setSize(500, 590);
      //  settingframe.setPreferredSize(new Dimension(500, 600));
        settingframe.setVisible(false);
        
        settingframe.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - settingframe.getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - settingframe.getHeight()) / 2);
        readsettings();
        writesettings();
} 

public void readsettings(){


try {
            FileReader fReader = new FileReader("settings/cnfg.adb");
            BufferedReader inFile = new BufferedReader(fReader);
            String input;
            String md="Mode:";
            String dbl="Database Location :" ;
            String lg="Logs :";
            String ch="Charts :";
            String scs="Scheduler Settings:";
            String dy="Days:";
            String hr="Hours:";
            String mn="Minutes:";
            String sc="Seconds:";

            while ((input = inFile.readLine()) != null) {
                System.out.println("" + input);
                
                if(input.contains(md)){
                mode=Integer.parseInt(input.substring(input.lastIndexOf(":")+1).trim());
                if(mode==2){
                    aggresive.setSelected(true);
                    mod.setSelected(false);
                    minimum.setSelected(false);
                }else if(mode==1){
                    
                    aggresive.setSelected(false);
                mod.setSelected(true);
                minimum.setSelected(false);
                }
                
                else {
                    aggresive.setSelected(false);
                    mod.setSelected(false);
                   minimum.setSelected(true);
                }
                }
                
                if(input.contains(dbl)){
                database.setText(input.substring(input.lastIndexOf(":")+1).trim());
                }

                if(input.contains(lg)){
                logs.setText(input.substring(input.lastIndexOf(":")+1).trim());
                }

                if(input.contains(ch)){
                charts.setText(input.substring(input.lastIndexOf(":")+1).trim());
                }
                
                if(input.contains(scs)){
                
                enasch.setSelected(true);
                }
                if(input.contains(dy)){
                
                days.setSelectedIndex(Integer.parseInt(input.substring(input.lastIndexOf(":")+1).trim()));
                }
                if(input.contains(hr)){
                
                hrs.setSelectedIndex(Integer.parseInt(input.substring(input.lastIndexOf(":")+1).trim()));
                }
                if(input.contains(mn)){
                
                mins.setSelectedIndex(Integer.parseInt(input.substring(input.lastIndexOf(":")+1).trim()));
                }
                
                if(input.contains(sc)){
                
                secs.setSelectedIndex(Integer.parseInt(input.substring(input.lastIndexOf(":")+1).trim()));
                }
            
            
            
            }

            System.out.println("---------End of Line");
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }






}


public void writesettings(){

try{
      setting = new PrintStream(("settings/cnfg.adb"));
  }catch (Exception ew){}
    setting.print("Mode: "+mode+"\n");
    setting.print("Database Location : "+database.getText()+"\n");
    setting.print("Logs :"+logs.getText()+"\n");
    setting.print("Charts :"+charts.getText()+"\n");
    if(enasch.isSelected()==true){
        setting.print("Scheduler Settings:");
        setting.print("Days:"+days.getSelectedIndex()+"\n");
        setting.print("Hours:"+hrs.getSelectedIndex()+"\n");
        setting.print("Minutes:"+mins.getSelectedIndex()+"\n");
        setting.print("Seconds:"+secs.getSelectedIndex()+"\n");
                         }else{
    
    }
}

public void itemStateChanged(ItemEvent e) {
        int index = 0;
        char c = '-';
        Object source = e.getItemSelectable();

        if (source == aggresive) {
                  mode=2;        
       mod.setSelected(false);
       minimum.setSelected(false);
        
        }
        if (source == mod ) {
                  mode=1; 
        aggresive.setSelected(false);
        minimum.setSelected(false);
         
        }
        if (source == minimum ) {
                   mode=0;
        aggresive.setSelected(false);
        mod.setSelected(false);
        } 
        if(source== enasch){
        
    slabl.setEnabled(enasch.isSelected());
    days.setEnabled(enasch.isSelected());
    hrs.setEnabled(enasch.isSelected());
    mins.setEnabled(enasch.isSelected());
    secs.setEnabled(enasch.isSelected());
        schstatus=enasch.isSelected();
        }
    }

public void actionPerformed(ActionEvent ae) {
        final Object o = ae.getSource();

if(o==ok){
    
    String str1="Everyday";
    
    String str2="HH";
    String str3="MINS";
    String str4="SECS";
    
    if((enasch.isSelected()==true)&& ((hrs.getSelectedItem().equals(str2))||(mins.getSelectedItem().equals(str3))||(secs.getSelectedItem().equals(str4))))
    {
    javax.swing.JOptionPane.showMessageDialog(null, "Scheduler Settings Are Not Valid !", "Settings Not Valid", 0);
    
    }
    else{
        writesettings();
settingframe.setVisible(false);
    }

}
if(o==globals){
//jp1.setVisible(true);
try{

jp3.setVisible(true);
jp4.setVisible(false);
jp5.setVisible(false);    //jp3.removeAll();
    //jp3.add(jp1);
}catch(Exception e3){
//jp3.remove(jp2);}catch(Exception e3){

System.out.println("exception"+e3);
}
//jp2.setVisible(false);
}
if(o==paths){
try{
jp4.setVisible(true);
jp3.setVisible(false);
jp5.setVisible(false);
    //jp3.removeAll();
    //jp3.add(jp2);
}catch(Exception e3){

System.out.println("exception"+e3);
}
}
if(o==scheduler){
try{
jp5.setVisible(true);
jp3.setVisible(false);
jp4.setVisible(false);
    //jp3.removeAll();
    //jp3.add(jp2);
}catch(Exception e3){

System.out.println("exception"+e3);
}
}
if(o==browse1){
JFileChooser chooser = new JFileChooser();
chooser.setDialogTitle("Select target directory");
//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//FileChooserUI fd = new FileChooserUI(jf2, "Save", FileDialog.SAVE);
   int returnVal=     chooser.showDialog(settingframe, "Select");//show();
        
//int returnVal = chooser.showOpenDialog(parentFrame);
if(returnVal == JFileChooser.APPROVE_OPTION) {
File path = chooser.getSelectedFile();
database.setText(""+path.getPath());//AbsolutePath());
//System.out.println(beanFile.getAbsolutePath() + beanFile.isDirectory());
}
if(returnVal == JFileChooser.CANCEL_OPTION) {

database.setText("antivirus signautres.adb");//AbsolutePath());
//System.out.println(beanFile.getAbsolutePath() + beanFile.isDirectory());
}



}
if(o==browse2){
JFileChooser chooser = new JFileChooser();
chooser.setDialogTitle("Select target directory");
chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//FileChooserUI fd = new FileChooserUI(jf2, "Save", FileDialog.SAVE);
   int returnVal=     chooser.showDialog(settingframe, "Select");//show();
        
//int returnVal = chooser.showOpenDialog(parentFrame);
if(returnVal == JFileChooser.APPROVE_OPTION) {
File path = chooser.getSelectedFile();
logs.setText(""+path.getPath()+"\\");//AbsolutePath());
//System.out.println(beanFile.getAbsolutePath() + beanFile.isDirectory());
}
if(returnVal == JFileChooser.CANCEL_OPTION) {

logs.setText("logs/");//AbsolutePath());
//System.out.println(beanFile.getAbsolutePath() + beanFile.isDirectory());
}



}
if(o==browse3){
JFileChooser chooser = new JFileChooser();
chooser.setDialogTitle("Select target directory");
chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//FileChooserUI fd = new FileChooserUI(jf2, "Save", FileDialog.SAVE);
   int returnVal=     chooser.showDialog(settingframe, "Select");//show();
        
//int returnVal = chooser.showOpenDialog(parentFrame);
if(returnVal == JFileChooser.APPROVE_OPTION) {
File path = chooser.getSelectedFile();
charts.setText(""+path.getPath()+"\\");//AbsolutePath());
//System.out.println(beanFile.getAbsolutePath() + beanFile.isDirectory());
}
if(returnVal == JFileChooser.CANCEL_OPTION) {

charts.setText("charts/");//AbsolutePath());
//System.out.println(beanFile.getAbsolutePath() + beanFile.isDirectory());
}



}
}

public static void main(String args [])
  {  
  try{
      UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
  settings set=new settings();
  }catch (Exception e)
  {}
  }
  
}