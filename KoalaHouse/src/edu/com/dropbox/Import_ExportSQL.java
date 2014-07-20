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
        String info[] = new DataBase.DataTable().getUser();
        String[] executeCmd = new String[]{"C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysql", "projectkoala", "-u" + info[0], "-p" + info[1], "-e", " source " + path};
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            InputStream is = runtimeProcess.getInputStream();
            while (is.read() != -1) {
            }
            is = runtimeProcess.getErrorStream();
            while (is.read() != -1) {
            }
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                return true;
            } else {
//                executeCmd = "C:\\Program Files (x86)\\MySQL\\MySQL Server 5.6\\bin\\mysql -u " + info[0] + " -p" + info[1] + " projectkoala < " + path;
                executeCmd = new String[]{"C:\\Program Files (x86)\\MySQL\\MySQL Server 5.6\\bin\\mysql", "projectkoala", "-u" + info[0], "-p" + info[1], "-e", " source " + path};
                runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                is = runtimeProcess.getInputStream();
                while (is.read() != -1) {

                }
                processComplete = runtimeProcess.waitFor();
                return processComplete == 0;
            }
        } catch (Exception ex) {           
        }
        return false;
    }

    public static boolean ExportSQL(String path) {
        String info[] = new DataBase.DataTable().getUser();
        String executeCmd = "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump -u " + info[0] + " -p" + info[1] + " projectkoala -r " + path;
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            InputStream is = runtimeProcess.getInputStream();
            while (is.read() != -1) {
            }
            is = runtimeProcess.getErrorStream();
            while (is.read() != -1) {
            }
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                return true;
            } else {
                executeCmd = "C:\\Program Files (x86)\\MySQL\\MySQL Server 5.6\\bin\\mysqldump -u " + info[0] + " -p" + info[1] + " projectkoala -r " + path;
                runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                processComplete = runtimeProcess.waitFor();
                return processComplete == 0;
            }
        } catch (Exception ex) {         
        }
        return false;
    }
}
