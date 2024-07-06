package pt.ipp.isep.dei.esoft.project.ui.console;

/**
 * The type Dev team ui.
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class DevTeamUI implements Runnable {

    /**
     * Instantiates a new Dev team ui.
     */
    public DevTeamUI() {

    }

    public void run() {
        System.out.println("\u001B[36m#=======Development Team=======#");
        System.out.println("\u001B[33m1. Diogo Pereira - 1221137@isep.ipp.pt");
        System.out.println("\u001B[33m2. Luís Morais - 1221148@isep.ipp.pt");
        System.out.println("\u001B[33m3. Ana Silva - 1221133@isep.ipp.pt");
        System.out.println("\u001B[33m4. Diogo Rio - 1211041@isep.ipp.pt");
        System.out.println("\u001B[33m5. Diogo Silva - 1211634@isep.ipp.pt");
    }
}
