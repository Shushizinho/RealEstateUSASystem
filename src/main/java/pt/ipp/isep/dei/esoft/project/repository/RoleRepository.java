package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Role;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Role repository.
 */
public class RoleRepository {

    private final List<Role> roles = new ArrayList<>();

    /**
     * This method returns an exsiting Task Category by its description.
     *
     * @param roleDescription The description of the task category to be created.
     * @return The task category.
     * @throws IllegalArgumentException if the task category does not exist, which should never happen.
     */
    public Role getRoleByDescription(String roleDescription) {
        //Role newRole = new Role(roleDescription);
        Role role = null;
        for (Role roleCheck : List.copyOf(roles)) {
            if (roleCheck.compareDescription(roleDescription) == 0) {
                role = roleCheck;
            }
        }
        if (role == null) {
            throw new IllegalArgumentException(
                    "Role requested for [" + roleDescription + "] does not exist.");
        }
        return role;
    }

    /**
     * Add optional.
     *
     * @param role the role
     * @return the optional
     */
    public Optional<Role> add(Role role) {

        Optional<Role> newRole = Optional.empty();
        boolean operationSuccess = false;

        if (validateRole(role)) {
            newRole = Optional.of(role.clone());
            operationSuccess = roles.add(newRole.get());
        }

        if (!operationSuccess) {
            newRole = Optional.empty();
        }

        return newRole;
    }

    private boolean validateRole(Role role) {
        boolean isValid = !roles.contains(role);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<Role> getRoles() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(roles);
    }
}
