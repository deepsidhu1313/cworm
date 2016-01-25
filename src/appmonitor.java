
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;

class appmonitor extends Thread {
//    new readdataba

    JDialog j1;
    JButton fullscan, cancel;
    JCheckBox applyall;
    int jdiamode = 0;
    String prc;
    Process killer;

    public void run() {
        new readdatabase();
        while (true) {

            try {

                String line;
                Process p = Runtime.getRuntime().exec("tasklist /svc /nh");
                try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                    while ((line = input.readLine()) != null) {
                        System.out.println(line); //<-- Parse data here.
                        System.out.println("Appmonitor is running");
                        for (int i = 0; i <= readdatabase.data.size() - 1; i++) {
                            String sig = "" + readdatabase.data.get(i);
                            if (line.contains(sig)) {
                                System.out.println("Virus Detected");
                                prc = line.substring(0, line.lastIndexOf(".exe") + 4);
                                System.out.printf("Virus is: " + prc);

                                if ((settings.mode == 2) && (scanner.fullscanstatus == false)) {

                                    try {

                                        fullscan fs = new fullscan();
                                        killer = Runtime.getRuntime().exec("taskkill /F /IM " + prc.trim());

                                    } catch (Exception re) {
                                    }

                                    killer.destroy();
                                } else if ((settings.mode == 1) && (scanner.fullscanstatus == false)) {
                                    if (jdiamode == 0) {
                                        ImageIcon sfc = new ImageIcon("icons/jsfc.png");
                                        ImageIcon sfc2 = new ImageIcon("icons/jsfc2.png");

                                        j1 = new JDialog(j1, "Application Monitor Live Detected Virus  !");//("<HTML>Virus Detected <BR> File Name: <BR> "+filelist[i].getAbsolutePath()+" </HTML>");
                                        fullscan = new JButton("<HTML>Full System Scan!<BR> <BR>This Option Will Kill Infected App & <BR>Start the Full Scan On This System.</HTML>", sfc2);
                                        cancel = new JButton("Cancel");
                                        applyall = new JCheckBox("Don't Show This Msg Again!");
                                        //j1.setMessageType(1);
                                        j1.add(fullscan);
                                        j1.add(cancel);
                                        j1.add(applyall);
                                        j1.setIconImage(Toolkit.getDefaultToolkit().getImage("icons/min.png"));
                                        fullscan.setRolloverIcon(sfc);
                                        fullscan.setVerticalTextPosition(SwingConstants.CENTER);
                                        fullscan.setHorizontalTextPosition(SwingConstants.RIGHT);

                                        fullscan.setBounds(20, 60, 300, 100);
                                        cancel.setBounds(20, 180, 300, 100);
                                        applyall.setBounds(20, 300, 300, 30);
                                        j1.setAlwaysOnTop(true);
                                        j1.setBackground(new Color(255, 48, 48));
                                        j1.setLayout(null);
                                        j1.setSize(340, 400);
                                        j1.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - j1.getWidth()),
                                                (Toolkit.getDefaultToolkit().getScreenSize().height - j1.getHeight()) - 50);
                                        j1.setVisible(true);
                                        j1.show();
                                        j1.addWindowListener(new WindowAdapter() {
                                            public void windowClosing() {
                                                jdiamode = 0;
                                                j1.dispose();
                                            }

                                        });
                                        jdiamode = 1;
                                        fullscan.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                try {
                                                    if (applyall.isSelected() == true) {

                                                        fullscan fs = new fullscan();
                                                        killer = Runtime.getRuntime().exec("taskkill /F /IM " + prc.trim());
                                                        sleep(500);
                                                        killer.destroy();
                                                        jdiamode = 1;
                                                    } else if (applyall.isSelected() == false) {
                                                        fullscan fs = new fullscan();
                                                        killer = Runtime.getRuntime().exec("taskkill /F /IM " + prc.trim());
                                                        sleep(10000);

                                                        jdiamode = 0;
                                                    }
                                                } catch (Exception re) {
                                                }
                                                j1.dispose();
                                            }
                                        });
                                        cancel.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                try {
                                                    if (applyall.isSelected()) {
                                                        jdiamode = 2;
                                                    } else {
                                                        jdiamode = 0;
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

                            try {
                                killer.destroy();
                            } catch (Exception killer) {
                            }
                            continue;
                        }
                    }

                    p.destroy();
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
    }
}
