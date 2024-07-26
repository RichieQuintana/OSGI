package org.orga.osgi_rc;

import java.io.File;
import java.io.IOException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private static final long BYTES_IN_MB = 1024 * 1024;
    private static final long BYTES_IN_GB = 1024 * 1024 * 1024;

    public void start(BundleContext context) {
        System.out.println("Starting the bundle");

        File[] roots = File.listRoots();
        
        for (File root : roots) {
            try {
                String diskName = root.getCanonicalPath();
                long totalSpace = root.getTotalSpace();
                long usableSpace = root.getUsableSpace();
                long freeSpace = root.getFreeSpace();
                long usedSpace = totalSpace - freeSpace;
                
                System.out.println("Drive: " + diskName);
                System.out.println("  Total Space: " + formatBytesToGB(totalSpace) + " GB (" + formatBytesToMB(totalSpace) + " MB)");
                System.out.println("  Used Space: " + formatBytesToGB(usedSpace) + " GB (" + formatBytesToMB(usedSpace) + " MB)");
                System.out.println("  Usable Space: " + formatBytesToGB(usableSpace) + " GB (" + formatBytesToMB(usableSpace) + " MB)");
                System.out.println("  Free Space: " + formatBytesToGB(freeSpace) + " GB (" + formatBytesToMB(freeSpace) + " MB)");
            } catch (IOException e) {
                System.err.println("Error getting canonical path for drive: " + root.getAbsolutePath());
                e.printStackTrace();
            }
        }
    }

    public void stop(BundleContext context) {
        System.out.println("Stopping the bundle");
    }

    private String formatBytesToMB(long bytes) {
        return String.format("%.2f", bytes / (double) BYTES_IN_MB);
    }

    private String formatBytesToGB(long bytes) {
        return String.format("%.2f", bytes / (double) BYTES_IN_GB);
    }
}
