
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.Document;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

public class Scan implements Runnable, ActionListener {

    int totalFolder = 0;
    int totalFile = 0, infected = 0, skipped = 0, safe = 0, totalfles = 0;
    PrintStream dataset, log, result;
    File[] filelist;
    JLabel jl1, jl2, jl3, jl4, jl5, jl6;
    JButton pause, resume, stop, viewresult, close, empty1, empty2, empty3;
    JButton del, Ignore;
    JProgressBar jp1;
    JPanel jpnl, bnl;
    Image pi;
    ImageIcon pimage;
    static int interfcnt = 0;
    static JFrame scanf;
    static JScrollPane jsp;
    static JPanel interframe;
    File dir;
    static JCheckBox applyall;
    JDialog j1;
    Date cd;
    String currentdate;
    String ifileLocation;
    File fl1, fl2, fl3;
    JFreeChart stat;
    ChartPanel cpnl;
    JTextArea jta;

    public Scan(File fd) {
        readdatabase rdb = new readdatabase();

        dir = fd;
        try {
            cd = new Date();
            scanner.systemlogwriter.append("Scan Started on :" + cd.toString() + "\nScanning :" + dir);
            // scanner.//systemlogwriter.close();
            currentdate = "" + cd.toString();
            System.out.println("Date is:" + currentdate);
            currentdate = currentdate.replaceAll(":", "");
            System.out.println("Date after change" + currentdate);
            fl1 = new File("" + settings.logs.getText() + "\\" + currentdate + "result.txt");
            fl2 = new File("" + settings.logs.getText() + "\\" + currentdate + "log.txt");
            fl3 = new File("" + settings.charts.getText() + "\\" + currentdate + "chart.avlg");
            FileOutputStream f1 = new FileOutputStream(fl1);
            FileOutputStream f2 = new FileOutputStream(fl2);
            FileOutputStream f3 = new FileOutputStream(fl3);

            result = new PrintStream(f1);
            log = new PrintStream(f2);
            dataset = new PrintStream(f3);
            // pi= new Image("progress.gif"); 
            pause = new JButton("Pause");
            resume = new JButton("Resume");
            stop = new JButton("Stop");
            viewresult = new JButton("View Result");
            close = new JButton("Close");
            empty1 = new JButton();
            empty2 = new JButton();
            empty3 = new JButton();
            bnl = new JPanel();
            pimage = new ImageIcon("progress.gif");
            jl1 = new JLabel("", pimage, 4);
            jl2 = new JLabel("         Status: Starting");
            jl3 = new JLabel("         Scanned:");
            jl4 = new JLabel("         Current File:");
            jl5 = new JLabel("         Infected Files:");
            jl6 = new JLabel();
            jp1 = new JProgressBar();
            jpnl = new JPanel();
            jp1.setStringPainted(true);
            jpnl.add(jl1);
            jpnl.add(jl2);
            jpnl.add(jl3);
            jpnl.add(jl4);
            jpnl.add(jl5);

            log.print("" + currentdate + "\n");
            log.print("Scan Log Of: " + dir + "\n");
            log.print("Infected File List:\n");

            bnl.add(empty1);
            bnl.add(pause);
            bnl.add(stop);
            bnl.add(empty2);

            bnl.add(empty3);
            pause.setBounds(10, 40, 100, 30);
            resume.setBounds(10, 40, 100, 30);
            stop.setBounds(10, 180, 100, 30);
            viewresult.setBounds(10, 40, 100, 30);
            close.setBounds(10, 180, 100, 30);
            resume.setVisible(false);
            empty1.setVisible(false);
            empty2.setVisible(false);
            empty3.setVisible(false);
            pause.setVisible(false);

            //stop.setEnabled(false);
            //resume.setVisible(true);
            bnl.setLayout(new GridLayout(0, 5));
            bnl.setSize(500, 100);

            System.out.println("1 ");
            jpnl.add(jp1);
            jpnl.add(bnl);
            jpnl.add(jl6);
            scanner.scanframestatus = false;
            jl2.setText("         Status: Calulating Files..");

            jp1.setMinimum(0);
            jp1.setMaximum(100);

            jp1.setPreferredSize(new Dimension(350, 20));
            interfcnt++;
            System.out.println("2 ");
            jpnl.setLayout(new GridLayout(8, 1));
            jpnl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            stop.addActionListener(this);
            viewresult.addActionListener(this);
            close.addActionListener(this);

            jpnl.setVisible(true);
            if (scanner.scanframestatus == false) {
                System.out.println("5 ");
                {

                    interframe = new JPanel();
                    System.out.println("3 ");
                    // interframe.setBackground(Color.red);
                    interframe.setLayout(new BorderLayout());
                    interframe.setLayout(new GridLayout(interfcnt, 0));
                    interframe.add(jpnl);
                    System.out.println("4 ");
                    interframe.setSize(400, 400 * interfcnt);
                    interframe.setVisible(true);
                    int v1 = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
                    int h1 = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
                    scanf = new JFrame("Scanning Tasks");
                    System.out.println("6 ");
                    jsp = new JScrollPane(interframe, v1, h1);
                    scanf.add(jsp);
                    jsp.setSize(400, 400);
                    System.out.println("7 ");
                    scanf.setSize(500, 210);
                    scanf.setPreferredSize(new Dimension(500, 240));
                    scanf.setVisible(true);
                    //scanner.scanf.setLayout(new FlowLayout());
                    scanf.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - scanf.getWidth()) / 2,
                            (Toolkit.getDefaultToolkit().getScreenSize().height - scanf.getHeight()) / 2);

                    scanf.addWindowListener(new WindowAdapter() {

                        public void windowClosing(WindowEvent we) {
                            scanf.setVisible(false);
                        }
                    });
                    scanner.scanframestatus = true;
                }
            } else {
                interframe.setLayout(new GridLayout(interfcnt, 0));
                interframe.add(jpnl);
                interframe.setSize(400, 400 * interfcnt);
                interframe.setVisible(true);

            }

            scanner.stats3 = "Scan Status: " + interfcnt + " Running";
            scanner.alert.setText("<HTML><font face=Georgia, Arial, Garamond><PRE>       " + scanner.stats1 + "</PRE><BR><PRE>       " + scanner.stats2 + "</PRE><BR><PRE>       " + scanner.stats3 + "</PRE><BR><PRE>       " + scanner.stats4 + "</PRE></HTML>");

        } catch (Exception guie) {
            System.out.println("Exception: " + guie);
        }

        System.out.println("Folder: ");

        Thread scan = new Thread(this);
        scan.start();
    }

    public void run() {
        gettotalfiles tf = (new gettotalfiles(dir));
        System.out.println("8 ");
        int totalf = tf.getTotalFile();
        System.out.println("13 ");
        jp1.setMaximum(totalf);
        totalfles = totalf;
        System.out.println("9 ");
        long fileSizeByte = getScan(dir);
        jl2.setText("         Status: Finished");
        System.out.println("9 ");
        jl1.setIcon(null);
        bnl.removeAll();
        bnl.add(empty1);
        bnl.add(viewresult);
        bnl.add(empty2);
        bnl.add(close);
        bnl.add(empty3);
        safe = totalFile - infected;
        skipped = totalf - totalFile;
        if (skipped < 0) {
            skipped = 0;
        }

        dataset.print("Infected &&@ " + infected + "\n");
        dataset.print("Skipped &&@ " + skipped + "\n");
        dataset.print("Safe &&@ " + safe + "\n");

        result.print("" + currentdate + "\n");
        result.print("Scan Log Of: " + dir + "\n");
        result.print("Total Files: " + totalf + "\n");
        result.print("Scanned Files: " + totalFile + "\n");
        result.print("Infected Files: " + infected + "\n");
        result.print("Skipped Files: " + skipped + "\n");
        try {
            scanner.systemlogwriter.append("Result saved to:" + fl1.getAbsolutePath() + "\n");
            scanner.systemlogwriter.append("Scan Log saved to:" + fl2.getAbsolutePath() + "\n");

            scanner.systemlogwriter.append("Scan Completed.\n\n\n\n\n");
            //scanner.//systemlogwriter.close();
        } catch (Exception fw) {
        }

    }

    public void actionPerformed(ActionEvent ae) {
        final Object o = ae.getSource();

        if (o == stop) {
            int result = javax.swing.JOptionPane.showConfirmDialog(null, "Do Yoy Really Want TO Stop Scanning?", "Scan", 0, 3);
            if (result == JOptionPane.YES_OPTION) {
                if (interfcnt == 1) {
                    interfcnt--;
                    scanner.scanframestatus = false;
                    scanf.dispose();
                } else {
                    interfcnt--;
                    interframe.remove(jpnl);
                    interframe.setLayout(new GridLayout(interfcnt, 0));

                    interframe.setSize(400, 400 * interfcnt);
                    interframe.setVisible(true);
                }
                Thread.currentThread().destroy();
            } else {
            }
        }

        if (o == close) {

            if (interfcnt == 1) {
                interfcnt--;
                scanner.scanframestatus = false;
                scanf.dispose();
            } else {
                interfcnt--;
                interframe.remove(jpnl);
                interframe.setLayout(new GridLayout(interfcnt, 0));

                interframe.setSize(400, 400 * interfcnt);
                interframe.setVisible(true);
            }
        }

        if (o == viewresult) {
            viewresults();

        }

    }

    public void setlog() {

        try {
            BufferedReader bReader = new BufferedReader(new FileReader(fl2));
            String s;
            while ((s = bReader.readLine()) != null) {
                String datavalue[] = s.split("\n");

                jta.append(datavalue[0].toString());
                jta.append("\n");
                jta.setEditable(false);
            }
            bReader.close();
        } catch (Exception fe) {
        }
    }

    public void viewresults() {
        try {
            String dataFileLocation = "" + fl3.getAbsolutePath();

            System.out.println("pie1 ");

            System.out.println("pie2 ");
            ifileLocation = "" + settings.charts.getText() + "" + currentdate + "chart.png";
            String title = "<HTML>Scan Results Of: " + dir + "<BR> Performed On:" + currentdate + "</HTML>";
            final chart mchart = new chart(title, ifileLocation, dataFileLocation);
            stat = mchart.addchart();
            cpnl = mchart.addcp();
            /*
  piechart chartCreator = new piechart(""+currentdate);

		String dataFileLocation=""+fl3.getAbsolutePath();
		PieDataset pieDataset = chartCreator.createPieDataSet(dataFileLocation);
                System.out.println("pie1 " );
		JFreeChart chart = chartCreator.create3DPieChart(pieDataset);
                System.out.println("pie2 " );
		 ifileLocation = ""+settings.charts.getText()+""+currentdate+"chart.jpg";

		chartCreator.saveChart(chart, ifileLocation);*/
            System.out.println("pie3 ");
            System.out.println("3D Pie Chart has been created successfully");
            System.out.println("Chart has been saved to: " + ifileLocation);

        } catch (Exception pie) {
        }

        final JFrame rf = new JFrame("Scan Result");
        JButton close2 = new JButton("Close");
        ImageIcon chrt = new ImageIcon(ifileLocation);
        JLabel imagelabl = new JLabel(chrt);
        jta = new JTextArea();
        JLabel summ = new JLabel("");
        JLabel total = new JLabel("Total Files:    " + totalfles);
        JLabel scanned = new JLabel("Scanned Files:    " + totalFile);
        JLabel Infected = new JLabel("Infected Files:    " + infected);
        JLabel skip = new JLabel("Skipped Files:    " + skipped);
        JPanel chartpnl = new JPanel();
        JPanel Top = new JPanel();
        JPanel Centre = new JPanel();
        JPanel top1 = new JPanel();
        JPanel top2 = new JPanel();
        JPanel centre1 = new JPanel();
        JPanel centre2 = new JPanel();

        Top.setLayout(new GridLayout(1, 1));
        Centre.setLayout(new GridLayout(2, 1));
        top1.setLayout(new GridLayout(5, 1));
        top2.setLayout(new GridLayout(2, 3));
        centre1.setLayout(new GridLayout(1, 1));
        centre2.setLayout(new GridLayout(1, 1));

        int v1 = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h1 = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        JScrollPane jsp2 = new JScrollPane(jta, v1, h1);
        Top.setPreferredSize(new Dimension(500, 180));
        top1.add(summ);
        top1.add(total);
        top1.add(scanned);
        top1.add(Infected);
        top1.add(skip);
        Top.add(top1);
        //Top.add(top2);
        centre1.add(jsp2);
        centre2.add(cpnl);
        cpnl.setMouseZoomable(true);
        cpnl.setBackground(Color.red);
        Centre.add(centre2);
        Centre.add(centre1);

        rf.add(Top);
        rf.add(Centre);
        Top.setBounds(30, 30, 800, 140);
        Centre.setBounds(10, 180, 800, 500);
        setlog();
        Top.setVisible(true);
        Centre.setVisible(true);
        close2.setBounds(650, 700, 100, 30);
        rf.add(close2);

        jsp2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Detected Worms:"), BorderFactory.createEmptyBorder(1, 1, 1, 1)));

        rf.setLayout(null);
        rf.setSize(840, 800);
        //scanf.setPreferredSize(new Dimension(500, 210));
        rf.setVisible(true);
        //scanner.scanf.setLayout(new FlowLayout());
        rf.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - rf.getWidth()) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - rf.getHeight()) / 2);

        rf.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                rf.setState(JFrame.EXIT_ON_CLOSE);
            }
        });

        close2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                rf.dispose();
            }
        });

    }

    public void deletevirus(String s3) {
        String fpth = s3;

        try {
            if (applyall.isSelected()) {
                Process del = Runtime.getRuntime().exec("del " + fpth);
                settings.jdiamode = 1;

                del.destroy();
            } else {
                Process del = Runtime.getRuntime().exec("del " + fpth);
                del.destroy();

            }
        } catch (Exception re) {
        }
    }

    public long getScan(File folder) {
        totalFolder++;
        System.out.println("1 ");
        jl2.setText("         Status: Scanning");
        System.out.println("Folder: " + folder.getName());
        long foldersize = 0;
        System.out.println("2 ");
        if (((folder.listFiles() == null) && (folder.isDirectory())) || !(folder.exists())) {
            System.out.println("3 ");
            System.out.println("Folder can't read");
            return foldersize;
        }
        System.out.println("4");
        File[] filelist = folder.listFiles();
        for (int i = 0; i < filelist.length; i++) {
            if (filelist[i].isDirectory()) {
                System.out.println("5 ");
                foldersize += getScan(filelist[i]);
            } else {
                totalFile++;
                jp1.setValue(totalFile);
                jl3.setText("         Scanned: " + totalFile);
                System.out.println("6 ");
                foldersize += filelist[i].length();
                System.out.println("- " + filelist[i].getAbsolutePath());
                jl4.setText("         Current File: " + filelist[i].getName());
                String name = "" + filelist[i].getName();
                System.out.println("7 ");
                if (filelist[i].canRead() && filelist[i].isFile()) {
                }

                {
                    for (int fn = 0; fn <= (readdatabase.data.size() - 1); fn++) {
                        String sig = "" + readdatabase.data.get(fn);
                        if (name.equals(sig)) {
                            System.out.println("File is virus");
                            infected++;
                            //filelist[i].delete();
                            log.print("" + filelist[i].getAbsolutePath() + " File is Infected\n");
                            jl5.setText("         Infected Files: " + infected);
                            if (settings.mode == 2) {

                                try {
                                    j1 = new JDialog(j1, "Virus Detected!");
                                    JLabel jl1 = new JLabel("" + filelist[i].getName() + " is a Virus");
                                    jl1.setBounds(20, 60, 300, 100);
                                    j1.add(jl1);
                                    j1.setLayout(null);
                                    j1.setSize(340, 200);
                                    j1.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - j1.getWidth() - 50),
                                            (Toolkit.getDefaultToolkit().getScreenSize().height - j1.getHeight()) - 30);
                                    j1.setVisible(true);
                                    j1.setIconImage(Toolkit.getDefaultToolkit().getImage("icons/min.png"));
                                    j1.show();

                                    Thread.currentThread().sleep(1000);

                                    Process del = Runtime.getRuntime().exec("del " + filelist[i].getAbsolutePath());
                                    del.destroy();

                                    jl1.setText("" + filelist[i].getName() + " is Deleted!");
                                    Thread.currentThread().sleep(1000);
                                    j1.dispose();

                                } catch (Exception re) {
                                }

                            } else if (settings.mode == 1) {
                                if (settings.jdiamode == 0) {

                                    ImageIcon sfc = new ImageIcon("icons/del.png");
                                    ImageIcon sfc2 = new ImageIcon("icons/del2.png");

                                    j1 = new JDialog(j1, "Virus Detected!");//("<HTML>Virus Detected <BR> File Name: <BR> "+filelist[i].getAbsolutePath()+" </HTML>");
                                    del = new JButton("<HTML>Delete<BR> <BR>This Option Will Delete File permanently from Computer</HTML>", sfc);
                                    Ignore = new JButton("Ignore");
                                    applyall = new JCheckBox("Apply This Action To All");
                                    //j1.setMessageType(1);

                                    del.setRolloverIcon(sfc2);
                                    del.setVerticalTextPosition(SwingConstants.CENTER);
                                    del.setHorizontalTextPosition(SwingConstants.RIGHT);

                                    j1.add(del);

                                    j1.add(Ignore);
                                    j1.add(applyall);
                                    final String fpth = "" + filelist[i].getAbsolutePath();

                                    del.setBounds(20, 60, 300, 100);
                                    Ignore.setBounds(20, 180, 300, 100);
                                    applyall.setBounds(10, 300, 300, 30);

                                    j1.setLayout(null);
                                    j1.setSize(340, 400);
                                    j1.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - j1.getWidth() - 30),
                                            (Toolkit.getDefaultToolkit().getScreenSize().height - j1.getHeight()) - 30);
                                    j1.setVisible(true);
                                    j1.setIconImage(Toolkit.getDefaultToolkit().getImage("icons/min.png"));
                                    //j1.show();
                                    settings.jdiamode = 1;
                                    del.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                if (applyall.isSelected()) {
                                                    deletevirus(fpth);

                                                    settings.jdiamode = 1;
                                                } else {
                                                    deletevirus(fpth);
                                                    settings.jdiamode = 0;
                                                }
                                            } catch (Exception re) {
                                            }
                                            j1.dispose();
                                        }
                                    });
                                    Ignore.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                if (applyall.isSelected()) {
                                                    settings.jdiamode = 2;
                                                } else {
                                                    settings.jdiamode = 0;
                                                }
                                            } catch (Exception re) {
                                            }
                                            j1.dispose();
                                        }
                                    });
                                    applyall.addItemListener(new ItemListener() {

                                        public void itemStateChanged(ItemEvent e) {

                                        }
                                    });

                                }

                            } else {

                            }
                            continue;
                        } else {
                        }

                    }
                }

            }
        }
        return foldersize;
    }

    public int getScannedFolder() {
        return totalFolder;
    }

    public int getScannedFile() {
        return totalFile;
    }

    public int getInfectedFile() {
        return infected;
    }

    public static void main(String args[]) {
        String folder = "C:/dx";
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            Scan size = new Scan(new File(folder));
            // long fileSizeByte=size.getFileSize(new File(folder));
            // System.out.println("Folder Size: "+fileSizeByte+" Bytes" );
            System.out.println("Total Number of Folders: " + size.getScannedFolder());
            System.out.println("Total Number of Files: " + size.getScannedFile());
        } catch (Exception e) {
        }
    }

}
