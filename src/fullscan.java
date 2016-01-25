
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

class fullscan {

    static File[] systemdrives = new File[10];
    static File afile[];

    fullscan() {
        try {
            scanner.fullscanstatus = true;
            ArrayList drivelist = new ArrayList();
            afile = File.listRoots();
            System.out.println("" + afile);
            System.out.println("" + afile.length);
            for (int i2 = 0; i2 < afile.length; i2++) {
                System.out.println("Drive name is" + afile[i2].getAbsolutePath());

                switch (i2) {
                    case 0:
                        systemdrives[0] = afile[0];
                        System.out.println("drive 1");
                        break;
                    case 1:
                        systemdrives[1] = afile[1];
                        System.out.println("drive 1");
                        break;
                    case 2:
                        systemdrives[2] = afile[2];
                        System.out.println("drive 1");
                        break;
                    case 3:
                        systemdrives[3] = afile[3];
                        System.out.println("drive 1");
                        break;
                    case 4:
                        systemdrives[4] = afile[4];
                        break;
                    case 5:
                        systemdrives[5] = afile[5];
                        break;
                    case 6:
                        systemdrives[6] = afile[6];
                        break;
                    case 7:
                        systemdrives[7] = afile[7];
                        break;
                    case 8:
                        systemdrives[8] = afile[8];
                        break;
                    case 9:
                        systemdrives[9] = afile[9];
                        break;
                    default:
                        break;

                }

                scanner.fullscanstatus = true;

                System.out.println("fullscan2 started");
            }
            scanner.stats3 = "Scan Status: " + Scan.interfcnt + " Running";
            scanner.alert.setText("<HTML><font face=Georgia, Arial, Garamond><PRE>       " + scanner.stats1 + "</PRE><BR><PRE>       " + scanner.stats2 + "</PRE><BR><PRE>       " + scanner.stats3 + "</PRE><BR><PRE>       " + scanner.stats4 + "</PRE></HTML>");

            new fullScan2();

        } catch (Exception exception5) {
        }

    }

}

class fullScan2 implements Runnable, ActionListener {

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

    gettotalfiles tf;

    int totalf;
    File dir;
    static JCheckBox applyall;
    JDialog j1;
    Date cd;
    String currentdate;
    String ifileLocation;
    File fl1, fl2, fl3;
    JFreeChart stat;
    ChartPanel cpnl;
    long getscanning;
    JTextArea jta;

    public fullScan2() {
        readdatabase rdb = new readdatabase();

        // dir=fd;
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
            //scanner.scanframestatus=false;
            jl2.setText("         Status: Calulating Files..");

            jp1.setMinimum(0);
            jp1.setMaximum(100);

            jp1.setPreferredSize(new Dimension(350, 20));
            Scan.interfcnt++;
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

                    Scan.interframe = new JPanel();
                    System.out.println("3 ");
                    // Scan.interframe.setBackground(Color.red);
                    Scan.interframe.setLayout(new BorderLayout());
                    Scan.interframe.setLayout(new GridLayout(Scan.interfcnt, 0));
                    Scan.interframe.add(jpnl);
                    System.out.println("4 ");
                    Scan.interframe.setSize(400, 400 * Scan.interfcnt);
                    Scan.interframe.setVisible(true);
                    int v1 = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
                    int h1 = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
                    Scan.scanf = new JFrame("Scanning Tasks");
                    System.out.println("6 ");
                    Scan.jsp = new JScrollPane(Scan.interframe, v1, h1);
                    Scan.scanf.add(Scan.jsp);
                    Scan.jsp.setSize(400, 400);
                    System.out.println("7 ");
                    Scan.scanf.setSize(500, 240);
                    Scan.scanf.setPreferredSize(new Dimension(500, 210));
                    Scan.scanf.setVisible(true);
                    //scanner.Scan.scanf.setLayout(new FlowLayout());
                    Scan.scanf.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - Scan.scanf.getWidth()) / 2,
                            (Toolkit.getDefaultToolkit().getScreenSize().height - Scan.scanf.getHeight()) / 2);

                    Scan.scanf.addWindowListener(new WindowAdapter() {

                        public void windowClosing(WindowEvent we) {
                            Scan.scanf.setVisible(false);
                        }
                    });
                    scanner.scanframestatus = true;
                }
            } else {
                Scan.interframe.setLayout(new GridLayout(Scan.interfcnt, 0));
                Scan.interframe.add(jpnl);
                Scan.interframe.setSize(400, 400 * Scan.interfcnt);
                Scan.interframe.setVisible(true);

            }

        } catch (Exception guie) {
            System.out.println("Exception: " + guie);
        }

        System.out.println("Folder: ");
        scanner.alert.setText(null);
        scanner.stats3 = "Scan Status: " + Scan.interfcnt + " Running";
        scanner.alert.setText("<HTML><font face=Georgia, Arial, Garamond><PRE>       " + scanner.stats1 + "</PRE><BR><PRE>       " + scanner.stats2 + "</PRE><BR><PRE>       " + scanner.stats3 + "</PRE><BR><PRE>       " + scanner.stats4 + "</PRE></HTML>");

        Thread scan = new Thread(this);
        scan.start();
    }

    public void run() {

        switch (fullscan.afile.length - 1) {

            case 0:
                tf = (new gettotalfiles(fullscan.systemdrives[0]));
                System.out.println("8 ");
                totalf = tf.getTotalFile();
                System.out.println("13 ");
                jp1.setMaximum(totalf);
                totalfles = totalf;
                System.out.println("9 ");
                getscanning = getfullScan(fullscan.systemdrives[0]);
                break;
            // fileSizeByte+=getfullScan(fullscan.systemdrives[0]);
            case 1:
                tf = (new gettotalfiles(fullscan.systemdrives[0], fullscan.systemdrives[1]));
                System.out.println("8 ");
                totalf = tf.getTotalFile();
                System.out.println("13 ");
                jp1.setMaximum(totalf);
                totalfles = totalf;
                System.out.println("9 ");
                getscanning = getfullScan(fullscan.systemdrives[0]);
                getscanning += getfullScan(fullscan.systemdrives[1]);
                break;
            case 2:
                tf = (new gettotalfiles(fullscan.systemdrives[0], fullscan.systemdrives[1], fullscan.systemdrives[2]));
                System.out.println("8 ");
                totalf = tf.getTotalFile();
                System.out.println("13 ");
                jp1.setMaximum(totalf);
                totalfles = totalf;
                System.out.println("9 ");
                getscanning = getfullScan(fullscan.systemdrives[0]);
                getscanning += getfullScan(fullscan.systemdrives[1]);
                getscanning += getfullScan(fullscan.systemdrives[2]);
                break;
            case 3:
                tf = (new gettotalfiles(fullscan.systemdrives[0], fullscan.systemdrives[1], fullscan.systemdrives[2], fullscan.systemdrives[3]));
                System.out.println("8 ");
                totalf = tf.getTotalFile();
                System.out.println("13 ");
                jp1.setMaximum(totalf);
                totalfles = totalf;
                System.out.println("9 ");
                getscanning = getfullScan(fullscan.systemdrives[0]);
                getscanning += getfullScan(fullscan.systemdrives[1]);
                getscanning += getfullScan(fullscan.systemdrives[2]);
                getscanning += getfullScan(fullscan.systemdrives[3]);
                break;
            case 4:
                tf = (new gettotalfiles(fullscan.systemdrives[0], fullscan.systemdrives[1], fullscan.systemdrives[2], fullscan.systemdrives[3], fullscan.systemdrives[4]));
                System.out.println("8 ");
                totalf = tf.getTotalFile();
                System.out.println("13 ");
                jp1.setMaximum(totalf);
                totalfles = totalf;
                System.out.println("9 ");
                getscanning = getfullScan(fullscan.systemdrives[0]);
                getscanning += getfullScan(fullscan.systemdrives[1]);
                getscanning += getfullScan(fullscan.systemdrives[2]);
                getscanning += getfullScan(fullscan.systemdrives[3]);
                getscanning += getfullScan(fullscan.systemdrives[4]);
                break;
            case 5:
                tf = (new gettotalfiles(fullscan.systemdrives[0], fullscan.systemdrives[1], fullscan.systemdrives[2], fullscan.systemdrives[3], fullscan.systemdrives[4], fullscan.systemdrives[5]));
                System.out.println("8 ");
                totalf = tf.getTotalFile();
                System.out.println("13 ");
                jp1.setMaximum(totalf);
                totalfles = totalf;
                System.out.println("9 ");
                getscanning = getfullScan(fullscan.systemdrives[0]);
                getscanning += getfullScan(fullscan.systemdrives[1]);
                getscanning += getfullScan(fullscan.systemdrives[2]);
                getscanning += getfullScan(fullscan.systemdrives[3]);
                getscanning += getfullScan(fullscan.systemdrives[4]);
                getscanning += getfullScan(fullscan.systemdrives[5]);
                break;
            case 6:
                tf = (new gettotalfiles(fullscan.systemdrives[0], fullscan.systemdrives[1], fullscan.systemdrives[2], fullscan.systemdrives[3], fullscan.systemdrives[4], fullscan.systemdrives[5], fullscan.systemdrives[6]));
                System.out.println("8 ");
                totalf = tf.getTotalFile();
                System.out.println("13 ");
                jp1.setMaximum(totalf);
                totalfles = totalf;
                System.out.println("9 ");
                getscanning = getfullScan(fullscan.systemdrives[0]);
                getscanning += getfullScan(fullscan.systemdrives[1]);
                getscanning += getfullScan(fullscan.systemdrives[2]);
                getscanning += getfullScan(fullscan.systemdrives[3]);
                getscanning += getfullScan(fullscan.systemdrives[4]);
                getscanning += getfullScan(fullscan.systemdrives[5]);
                getscanning += getfullScan(fullscan.systemdrives[6]);
                break;
            case 7:
                tf = (new gettotalfiles(fullscan.systemdrives[0], fullscan.systemdrives[1], fullscan.systemdrives[2], fullscan.systemdrives[3], fullscan.systemdrives[4], fullscan.systemdrives[5], fullscan.systemdrives[6], fullscan.systemdrives[7]));
                System.out.println("8 ");
                totalf = tf.getTotalFile();
                System.out.println("13 ");
                jp1.setMaximum(totalf);
                totalfles = totalf;
                System.out.println("9 ");
                getscanning = getfullScan(fullscan.systemdrives[0]);
                getscanning += getfullScan(fullscan.systemdrives[1]);
                getscanning += getfullScan(fullscan.systemdrives[2]);
                getscanning += getfullScan(fullscan.systemdrives[3]);
                getscanning += getfullScan(fullscan.systemdrives[4]);
                getscanning += getfullScan(fullscan.systemdrives[5]);
                getscanning += getfullScan(fullscan.systemdrives[6]);
                getscanning += getfullScan(fullscan.systemdrives[7]);
                break;
            case 8:
                tf = (new gettotalfiles(fullscan.systemdrives[0], fullscan.systemdrives[1], fullscan.systemdrives[2], fullscan.systemdrives[3], fullscan.systemdrives[4], fullscan.systemdrives[5], fullscan.systemdrives[6], fullscan.systemdrives[7], fullscan.systemdrives[8]));
                System.out.println("8 ");
                totalf = tf.getTotalFile();
                System.out.println("13 ");
                jp1.setMaximum(totalf);
                totalfles = totalf;
                System.out.println("9 ");
                getscanning = getfullScan(fullscan.systemdrives[0]);

                getscanning += getfullScan(fullscan.systemdrives[1]);
                getscanning += getfullScan(fullscan.systemdrives[2]);
                getscanning += getfullScan(fullscan.systemdrives[3]);
                getscanning += getfullScan(fullscan.systemdrives[4]);
                getscanning += getfullScan(fullscan.systemdrives[5]);
                getscanning += getfullScan(fullscan.systemdrives[6]);
                getscanning += getfullScan(fullscan.systemdrives[7]);
                getscanning += getfullScan(fullscan.systemdrives[8]);
                break;

            default:
                for (int i2 = 0; i2 < fullscan.afile.length; i2++) {
                    new Scan(fullscan.afile[i2]);
                    break;
                }
        }
        jl2.setText("         Status: Finished");
        System.out.println("9 ");
        scanner.fullscanstatus = false;
        jl1.setIcon(null);
        bnl.removeAll();
        bnl.add(empty1);
        bnl.add(viewresult);
        bnl.add(empty2);
        bnl.add(close);
        bnl.add(empty3);
        safe = totalFile - infected;
        skipped = totalfles - totalFile;
        if (skipped < 0) {
            skipped = 0;
        }
        dataset.print("Infected &&@ " + infected + "\n");
        dataset.print("Skipped &&@ " + skipped + "\n");
        dataset.print("Safe &&@ " + safe + "\n");

        result.print("" + currentdate + "\n");
        result.print("Scan Log Of: Full System Scan\n");
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
                if (Scan.interfcnt == 1) {
                    Scan.interfcnt--;
                    scanner.scanframestatus = false;
                    Scan.scanf.dispose();
                } else if (Scan.interfcnt >= 2) {
                    Scan.interfcnt--;
                    Scan.interframe.remove(jpnl);
                    Scan.interframe.setLayout(new GridLayout(Scan.interfcnt, 0));

                    Scan.interframe.setSize(400, 400 * Scan.interfcnt);
                    Scan.interframe.setVisible(true);
                }
                Thread.currentThread().stop();
            } else {
            }
        }

        if (o == close) {

            if (Scan.interfcnt == 1) {
                Scan.interfcnt--;
                scanner.scanframestatus = false;
                Scan.scanf.dispose();
            } else {
                Scan.interfcnt--;
                Scan.interframe.remove(jpnl);
                Scan.interframe.setLayout(new GridLayout(Scan.interfcnt, 0));

                Scan.interframe.setSize(400, 400 * Scan.interfcnt);
                Scan.interframe.setVisible(true);
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
        //Scan.scanf.setPreferredSize(new Dimension(500, 210));
        rf.setVisible(true);
        //scanner.Scan.scanf.setLayout(new FlowLayout());
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
            } else {
                Process del = Runtime.getRuntime().exec("del " + fpth);
            }
        } catch (Exception re) {
        }
    }

    public long getfullScan(File folder) {
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
                foldersize += getfullScan(filelist[i]);
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
                            log.print("" + filelist[i].getAbsolutePath() + " File is Infected");
                            jl5.setText("         Infected Files: " + infected);
                            if (settings.mode == 2) {

                                try {
                                    Process del = Runtime.getRuntime().exec("del " + filelist[i].getAbsolutePath());

                                } catch (Exception re) {
                                }

                            } else if (settings.mode == 1) {
                                if (settings.jdiamode == 0) {

                                    j1 = new JDialog();//("<HTML>Virus Detected <BR> File Name: <BR> "+filelist[i].getAbsolutePath()+" </HTML>");
                                    del = new JButton("<HTML>Delete<BR> <BR>This Option Will Delete File permanently from Computer</HTML>");
                                    Ignore = new JButton("Ignore");
                                    applyall = new JCheckBox("Apply This Action To All");
                                    //j1.setMessageType(1);
                                    j1.add(del);
                                    j1.add(Ignore);
                                    j1.add(applyall);
                                    final String fpth = "" + filelist[i].getAbsolutePath();

                                    del.setBounds(20, 60, 300, 100);
                                    Ignore.setBounds(20, 180, 300, 100);
                                    applyall.setBounds(10, 300, 300, 30);

                                    j1.setLayout(null);
                                    j1.setSize(340, 400);
                                    j1.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - j1.getWidth()),
                                            (Toolkit.getDefaultToolkit().getScreenSize().height - j1.getHeight()));
                                    j1.setVisible(true);
                                    //j1.show();
                                    del.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            try {
                                                if (applyall.isSelected()) {
                                                    deletevirus(fpth);

                                                    settings.jdiamode = 1;
                                                } else {
                                                    deletevirus(fpth);
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

}
