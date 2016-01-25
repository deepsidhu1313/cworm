
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

class scanner extends JFrame implements ActionListener {

    String drive;
    int total, infected;
    FileWriter logFile;
    JLabel jl1, jb;
    Container container;
    Date dd;
    MenuBar mb;
    JButton jb1, jb3, jb4, jb5, b1, b2, b3, b4, b5, g1, g3, managetasks, scan, home, refresh;

    static ImageIcon stat1, stat2, stat3;
    static String stats1, stats2, stats3, stats4;
    static JButton alert;
    JToolBar jtb;
    static Boolean scanframestatus, fullscanstatus;
    static JFrame jf;
    appmonitor app;
    JComboBox main;
    ImageIcon sfc, sfc2, sff, sff2, sf, sf2, sfd, sfd2, rf, rf2, scan1, scan2, home1, home2, wtm, wtm2, tm1, tm2, mset1, mset2;
    JPanel jif, jif2, mainpanel;
    int files, vir, clean, vx, hx;
    File syslog = new File("system.log");
    static FileWriter systemlogwriter;
    String tdy;
    Date pd = new Date();

    scanner() {

        fullscanstatus = false;
        scanframestatus = false;
        //systemlogwriter= new FileWriter("system.log");
        tdy = "" + pd.getDate() + "" + pd.getMonth() + "" + pd.getHours() + "" + pd.getMinutes();

        // Container	contentpane;
        //   do
        {
            try {
                systemlogwriter = new FileWriter("logs/" + tdy + "system.log");

                systemlogwriter.append("Application Started ON:" + pd.toString() + "\n");
                //systemlogwriter.close();
            } catch (Exception exception1) {
            }

            {

                jf = new JFrame("Modeling and Detection of Camouflaging Worm");
                jif2 = new JPanel();
                jf.addWindowListener(new WindowAdapter() {
                    public void windowClosing() {
                        app.stop();
                        //app.destroy();
                        System.exit(-1);
                    }

                });

                main = new JComboBox();
                sfc = new ImageIcon("icons/sfc.png");
                sfc2 = new ImageIcon("icons/sfc2.png");
                sff = new ImageIcon("icons/sff.png");
                sff2 = new ImageIcon("icons/sff2.png");
                sf = new ImageIcon("icons/sf.png");
                sf2 = new ImageIcon("icons/sf2.png");
                sfd = new ImageIcon("icons/sfd.png");
                sfd2 = new ImageIcon("icons/sfd2.png");
                rf = new ImageIcon("icons/rf.png");
                rf2 = new ImageIcon("icons/rf2.png");
                scan1 = new ImageIcon("icons/scan1.png");
                scan2 = new ImageIcon("icons/scan2.png");
                home1 = new ImageIcon("icons/home1.png");
                home2 = new ImageIcon("icons/home2.png");
                wtm = new ImageIcon("icons/wtm.png");
                wtm2 = new ImageIcon("icons/wtm2.png");
                tm1 = new ImageIcon("icons/tm1.png");
                tm2 = new ImageIcon("icons/tm2.png");
                mset1 = new ImageIcon("icons/sett1.png");
                mset2 = new ImageIcon("icons/sett2.png");
                stat1 = new ImageIcon("icons/secure.png");
                stat2 = new ImageIcon("icons/msg.png");
                stat3 = new ImageIcon("icons/unsecure.png");

                stats1 = "Status: Secured";
                stats2 = "Protection Enable";
                stats3 = "Scan Status: 0 Running";
                stats4 = "Threats : NONE";
                mainpanel = new JPanel();
                jif = new JPanel();
                JPanel jpanel = new JPanel(null, true);
                jtb = new JToolBar();
                jb = new JLabel("Nika hero");
                JLabel jlabel = new JLabel("Database Version: 06.19.2011.9.16");
                jl1 = new JLabel("->Scanning Options");
                JLabel jlabel1 = new JLabel("Database By: CMC");
                JLabel jlabel2 = new JLabel("Update: Not Required");
                b1 = new JButton("View Log");
                b2 = new JButton("View File Log");
                b3 = new JButton("About Worm");
                g1 = new JButton("Task Manager", wtm2);
                g3 = new JButton("Setting", mset1);
                managetasks = new JButton("Manage Tasks", tm1);
                jb1 = new JButton("Scan Full Computer", sfc2);
                jb5 = new JButton("Scan File", sf2);
                jb4 = new JButton("Scan Folder", sff2);
                jb3 = new JButton("Scan User Drive", sfd2);
                scan = new JButton("Scan", scan1);
                alert = new JButton("<HTML><font face=Georgia, Arial, Garamond><PRE>       " + stats1 + "</PRE><BR><PRE>       " + stats2 + "</PRE><BR><PRE>       " + stats3 + "</PRE><BR><PRE>       " + stats4 + "</PRE></HTML>", stat1);
                alert.setFont(new Font("", Font.PLAIN, 14));

                home = new JButton("Home", home1);
                refresh = new JButton(rf);

                jb1.setRolloverIcon(sfc);
                jb3.setRolloverIcon(sfd);
                jb4.setRolloverIcon(sff);
                jb5.setRolloverIcon(sf);
                refresh.setRolloverIcon(rf2);
                scan.setRolloverIcon(scan2);
                managetasks.setRolloverIcon(tm2);

                g3.setRolloverIcon(mset2);

                g1.setRolloverIcon(wtm);
                //home.setRolloverIcon(home2);

                jb1.setVerticalTextPosition(SwingConstants.BOTTOM);
                jb1.setHorizontalTextPosition(SwingConstants.CENTER);
                jb3.setVerticalTextPosition(SwingConstants.BOTTOM);
                jb3.setHorizontalTextPosition(SwingConstants.CENTER);
                jb4.setVerticalTextPosition(SwingConstants.BOTTOM);
                jb4.setHorizontalTextPosition(SwingConstants.CENTER);
                jb5.setVerticalTextPosition(SwingConstants.BOTTOM);
                jb5.setHorizontalTextPosition(SwingConstants.CENTER);
                scan.setVerticalTextPosition(SwingConstants.BOTTOM);
                scan.setHorizontalTextPosition(SwingConstants.CENTER);
                home.setVerticalTextPosition(SwingConstants.CENTER);
                home.setHorizontalTextPosition(SwingConstants.LEFT);
                g1.setVerticalTextPosition(SwingConstants.BOTTOM);
                g1.setHorizontalTextPosition(SwingConstants.CENTER);
                managetasks.setVerticalTextPosition(SwingConstants.BOTTOM);
                managetasks.setHorizontalTextPosition(SwingConstants.CENTER);
                g3.setVerticalTextPosition(SwingConstants.BOTTOM);
                g3.setHorizontalTextPosition(SwingConstants.CENTER);
                alert.setVerticalTextPosition(SwingConstants.CENTER);
                alert.setHorizontalTextPosition(SwingConstants.RIGHT);

                jf.setVisible(true);
                jif2.setLayout(null);
                jif2.setBounds(10, 530, 500, 150);
                jif2.setVisible(true);
                jif.setLayout(null);
                jif.setBounds(10, 50, 500, 470);
                jif.setVisible(true);

                hx = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
                vx = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
                mainpanel.setBackground(new Color(244, 164, 96));
                b1.setForeground(new Color(225, 0, 0));

                b2.setForeground(new Color(225, 0, 0));
                b3.setForeground(new Color(225, 0, 0));
                mainpanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Information"), BorderFactory.createEmptyBorder(1, 1, 1, 1)));

                jl1.setFont(new Font("Serif", Font.BOLD, 15));

                main.addItem("Select Drive");
                try {
                    File afile[] = File.listRoots();
                    for (int i2 = 0; i2 < afile.length; i2++) {
                        main.addItem((new StringBuilder()).append(afile[i2]).append("").toString());
                    }

                } catch (Exception exception12) {
                }

                jtb.add(b1);
                jtb.addSeparator();
                jtb.add(b2);
                jtb.addSeparator();
                jtb.add(b3);
                jtb.addSeparator();

                mainpanel.add(jlabel1);
                mainpanel.add(jlabel2);
                mainpanel.add(jlabel);

                mainpanel.add(jpanel);
                jf.add(mainpanel);
                jf.add(jtb);
                jf.add(jif);
                jf.add(jif2);
                jf.add(alert);

                jif.add(jl1);
                jif.add(jb1);
                jif.add(home);
                jif.add(refresh);

                jif2.add(g1);
                jif2.add(g3);
                jif2.add(managetasks);
                jif2.add(scan);
                jif.add(jb3);
                jif.add(main);
                jif.add(jb4);
                jif.add(jb5);

                // jl5.setBounds(100, 150, 64, 40);
                jtb.setBounds(0, 1, 400, 30);
                jb.setBounds(15, 95, 120, 30);

                b1.setBounds(0, 1, 100, 30);
                b2.setBounds(0, 1, 100, 30);
                b3.setBounds(0, 1, 100, 30);
                jlabel.setBounds(20, 40, 400, 30);
                jlabel1.setBounds(20, 60, 400, 30);
                jlabel2.setBounds(20, 80, 150, 30);
                mainpanel.setBounds(10, 50, 400, 120);
                mainpanel.setLayout(null);
                mainpanel.setVisible(false);
                jl1.setBounds(180, 0, 190, 30);
                alert.setBounds(20, 200, 480, 250);
                alert.setVisible(false);
                jb1.setBounds(30, 55, 180, 190);
                scan.setBounds(10, 10, 120, 100);
                managetasks.setBounds(130, 10, 120, 100);
                g3.setBounds(250, 10, 120, 100);
                g1.setBounds(370, 10, 120, 100);
                jb3.setBounds(260, 90, 180, 155);
                main.setBounds(260, 55, 120, 30);
                jb4.setBounds(30, 265, 180, 190);
                jb5.setBounds(260, 265, 180, 190);
                refresh.setBounds(390, 55, 50, 30);
                home.setBounds(30, 0, 130, 50);
                jf.setIconImage(Toolkit.getDefaultToolkit().getImage("icons/worm.png"));
                jf.setLayout(null);
                jf.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - jf.getWidth()) / 2 - 400,
                        (Toolkit.getDefaultToolkit().getScreenSize().height - jf.getWidth()) / 2 - 400);
                jf.setSize(700, 700);
            }
            g1.addActionListener(this);
            b1.addActionListener(this);
            b2.addActionListener(this);
            b3.addActionListener(this);
            g1.addActionListener(this);
            g3.addActionListener(this);
            managetasks.addActionListener(this);
            jb1.addActionListener(this);
            scan.addActionListener(this);
            alert.addActionListener(this);
            jb3.addActionListener(this);
            jb4.addActionListener(this);
            jb5.addActionListener(this);
            home.addActionListener(this);
            refresh.addActionListener(this);
            settings set = new settings();
            //app = new appmonitor();
            // app.start();
            try {
                settings.settingframe.setVisible(false);
            } catch (Exception ew) {
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        final Object o = ae.getSource();

        if (o == managetasks) {
            try {
                Scan.scanf.setVisible(true);
            } catch (Exception managetasks) {
            }
        }

        if (o == home) {
            try {
                mainpanel.setVisible(true);
                alert.setVisible(true);
                jif.setVisible(false);
            } catch (Exception home) {
            }
        }
        if (o == scan) {
            try {
                mainpanel.setVisible(false);
                alert.setVisible(false);
                jif.setVisible(true);
            } catch (Exception scanb) {
            }
        }
        if (o == alert) {

            try {

            } catch (Exception alert) {
            }
        }

        if (o == refresh) {
            try {
                main.removeAllItems();
                main.addItem("Select Drive");
                try {
                    File afile[] = File.listRoots();
                    for (int i2 = 0; i2 < afile.length; i2++) {
                        main.addItem((new StringBuilder()).append(afile[i2]).append("").toString());
                    }

                } catch (Exception refresh) {
                }

            } catch (Exception refresh2) {
            }
        }

        if (o == g1) {
            try {
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("taskmgr.exe");

            } catch (Exception exception2) {
                System.err.println(""+exception2);
                try {
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("gnome-system-monitor");

            } catch (Exception exception) {
                System.err.println(""+exception);
            }
            }

        }

        if (o == b1) {
            try {
                Process logger = Runtime.getRuntime().exec("explorer " + syslog.getAbsolutePath());
            } catch (Exception exception3) {

            }

        }

        if (o == b2) {
            try {
                File f1 = new File(settings.logs.getText());
                Process logger = Runtime.getRuntime().exec("explorer " + f1.getAbsolutePath());
                //new view();
            } catch (Exception exception36) {
            }

        }

        if (o == b3) {
            try {
                JFrame jframe = new JFrame("About Worm Detection");
                jframe.setVisible(true);
                jframe.setLocation(100, 100);
                jframe.setSize(650, 350);
                jframe.setLayout(null);
                jframe.setBackground(new Color(100, 100, 100));

                /* ImageIcon imageicon = new ImageIcon("lvback.gif");
                            JLabel jlabel3 = new JLabel(imageicon);
                            jlabel3.setBounds(60, 20, 160, 140);
                            jframe.add(jlabel3); */
                ImageIcon imageicon1 = new ImageIcon("icons/worm.png");
                JLabel jlabel4 = new JLabel(imageicon1);
                jlabel4.setBounds(10, 40, 200, 200);
                jframe.add(jlabel4);

                JTextArea jtextarea = new JTextArea("About Worm");
                //jtextarea.setBackground(Color.black);
                //jtextarea.setForeground(Color.red);
                jtextarea.setBounds(220, 10, 400, 280);
                jtextarea.setEditable(false);
                JScrollPane jscrollpane1 = new JScrollPane(jtextarea, vx, hx);
                jframe.add(jtextarea);
                jframe.setIconImage(Toolkit.getDefaultToolkit().getImage("icons/abwm.png"));
                jtextarea.setText((new StringBuilder()).append(jtextarea.getText()).append("\n\rSoftware Name:Worm detector \n").toString());

                jtextarea.setText((new StringBuilder()).append(jtextarea.getText()).append("Database By: CMC \n").toString());
                jtextarea.setText((new StringBuilder()).append(jtextarea.getText()).append("It is a Simple worm detection system. In this\nsystem"
                        + "recursion Search & Comparison With worm- \nsignatures plays a major role in detection &\ndeletion of worms from computer. Most of The \nvulnerable computers generates unneccessary files\nduring worm attacks. The detection is commonly \nbased on the selfpropagating behavior of worms\nthat can be described as follows: \n A worm-infected computer identifies  and \ninfects a vulnerable computer on the Internet. \n").toString());

            } catch (Exception exception4) {
            }

        }

        if (o == jb1) {
            try {
                new fullscan();
            } catch (Exception exception5) {
            }

        }

        if (o == main) {
            try {
                String s39 = (String) main.getSelectedItem();
                System.out.println(s39);
            } catch (Exception exception8) {
                System.out.println(exception8);
            }

        }

        if (o == jb3) {
            try {
                String s39 = (String) main.getSelectedItem();
                drive = s39;
                if (drive.equals("Select Drive")) {
                    JOptionPane.showMessageDialog((Component) null, "Please Select drive from Drive list", "Drive Loading error", 0);
                } else {
                    drive = "" + main.getSelectedItem() + "\\";

                    Scan size = new Scan(new File(drive));
                }
            } catch (Exception exception9) {
                System.out.println(exception9);
            }

        }
        if (o == jb4) {

            new folderscan();

        }

        if (o == jb5) {

            new filescan();
        }
        if (o == g3) {
            try {
                settings.settingframe.setVisible(true);
            } catch (Exception exception11) {
                new settings();
            }

        }

    }

    public static void main(String args[]) {
        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
        }
        scanner scanner = new scanner();

    }

}

class folderscan implements Runnable {

    folderscan() {

        Thread fs = new Thread(this);
        fs.start();

    }

    public void run() {

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Select target directory");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//FileChooserUI fd = new FileChooserUI(jf2, "Save", FileDialog.SAVE);
            int returnVal = chooser.showDialog(scanner.jf, "Select");//show();

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File path = chooser.getSelectedFile();
                new Scan(path);//AbsolutePath());
            }
            if (returnVal == JFileChooser.CANCEL_OPTION) {

            }
        } catch (Exception exception10) {
        }

    }

}

class filescan implements Runnable {

    filescan() {

        Thread fs = new Thread(this);
        fs.start();

    }

    public void run() {

        try {

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Select target directory");
//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//FileChooserUI fd = new FileChooserUI(jf2, "Save", FileDialog.SAVE);
            int returnVal = chooser.showDialog(scanner.jf, "Select");//show();

//int returnVal = chooser.showOpenDialog(parentFrame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File path = chooser.getSelectedFile();
                new Scan(path);//System.out.println(beanFile.getAbsolutePath() + beanFile.isDirectory());
            }
            if (returnVal == JFileChooser.CANCEL_OPTION) {

            }
        } catch (Exception exception10) {
        }

    }

}
