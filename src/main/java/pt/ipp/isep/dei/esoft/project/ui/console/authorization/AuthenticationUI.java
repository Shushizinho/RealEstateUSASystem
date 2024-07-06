package pt.ipp.isep.dei.esoft.project.ui.console.authorization;


import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
//import pt.ipp.isep.dei.esoft.project.ui.console.CreateSaleAnnouncementUI;
import pt.ipp.isep.dei.esoft.project.ui.console.CreateSaleAnnouncementUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * The type Authentication ui.
 *
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class AuthenticationUI implements Runnable {
    private final AuthenticationController ctrl;

    /**
     * Instantiates a new Authentication ui.
     */
    public AuthenticationUI() {
        ctrl = new AuthenticationController();
    }

    public void run() {
        boolean success = doLogin();

        if (success) {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                System.out.println("No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    this.redirectToRoleUI(rolesUI, role);
                } else {
                    System.out.println("No role selected.");
                }
            }
        }
        this.logout();
    }

    private List<MenuItem> getMenuItemForRoles() {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_ADMIN, new AdminUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_CLIENT, new ClientUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_STORE_NETWORK_MANAGER, new StoreNetworkManagerUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_AGENT,  new AgentUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_STORE_MANAGER,  new StoreManagerUI()));

        //TODO: Complete with other user roles and related RoleUI
        return rolesUI;
    }

    private boolean doLogin() {
        System.out.println("\u001B[36m#=======Login UI=======#");
        System.out.println("\u001B[33mEnter 0 as either email or password to cancel.");

        int maxAttempts = 3;
        boolean success = false;
        do {
            maxAttempts--;
            String id = Utils.readLineFromConsole("Enter UserId/Email: ");
            if (id.compareTo("0")==0) return false;
            String pwd = Utils.readLineFromConsole("Enter Password: ");
            if (pwd.compareTo("0")==0) return false;

            success = ctrl.doLogin(id, pwd);
            if (!success) {
                System.out.println("\u001B[31mInvalid UserId and/or Password.\u001B[0m\n\u001B[31mYou have  " + maxAttempts + " more attempt(s).\u001B[0m");

            }

        } while (!success && maxAttempts > 0);
        return success;
    }

    private void logout() {
        ctrl.doLogout();
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
        boolean found = false;
        Iterator<MenuItem> it = rolesUI.iterator();
        while (it.hasNext() && !found) {
            MenuItem item = it.next();
            found = item.hasDescription(role.getDescription());
            if (found) {
                item.run();
            }
        }
        if (!found) {
            System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
        }
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            return (UserRoleDTO) Utils.showAndSelectOneRole(roles, "Select the role you want to adopt in this session:");
        }
    }

}
