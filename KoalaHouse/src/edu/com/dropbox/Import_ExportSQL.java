/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.com.dropbox;

import java.io.InputStream;

/**
 *
 * @author nguyenvan
 */
public class Import_ExportSQL {

    public static boolean ImportSQL(String path) {
        String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysql -u root -pakatsuki projectkoala < " + path;
//        String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysql -u root -pakatsuki projectkoala -e " + path;
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            InputStream is = runtimeProcess.getInputStream();

// Do one OR the other, but not both ;)
// If you don't care about the output, but I think it's a bit of waste personally...

// I'd at least dump the output to the console...
            int byteRead = -1;
            while ((byteRead = is.read()) != -1) {
                System.out.print((char) byteRead);
            }
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                return true;
            } else {
                executeCmd = "C:\\Program Files (x86)\\MySQL\\MySQL Server 5.6\\bin\\mysql -u root -pakatsuki projectkoala < " + path;
                runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                is = runtimeProcess.getInputStream();
                while (is.read() != -1) {

                }
                processComplete = runtimeProcess.waitFor();
                return processComplete == 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean ExportSQL(String path) {
        String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump -u root -pakatsuki projectkoala -r " + path;
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                return true;
            } else {
                executeCmd = "C:\\Program Files (x86)\\MySQL\\MySQL Server 5.6\\bin\\mysqldump -u root -pakatsuki projectkoala -r " + path;
                runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                processComplete = runtimeProcess.waitFor();
                return processComplete == 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean tbBackup(String dbName, String tbName, String dbUserName, String dbPassword, String path) {

//        String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump -u root -pakatsuki projectkoala -r";
        String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump -u root -pakatsuki projectkoala -r ";

        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);

            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                System.out.println(
                        "Backup created successfully");
                return true;
            } else {
                System.out.println(
                        "Could not create the backup");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
