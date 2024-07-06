package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Role test.
 */
class RoleTest {

    /**
     * Test equals.
     */
    @Test
    void testEquals() {
        Role  role  = new Role(2, AuthenticationController.ROLE_AGENT);
        assertEquals(true , role.equals(role) );
    }

    /**
     * Test not equals.
     */
    @Test
    void testNotEquals() {
        Role  role  = new Role(2, AuthenticationController.ROLE_AGENT);
        assertEquals(false , role.equals(""));
    }

    /**
     * Test not equals null.
     */
    @Test
    void testNotEqualsNull() {
        Role  role  = new Role(2, AuthenticationController.ROLE_AGENT);
        assertNotEquals(true, role.equals(null) );
    }

    /**
     * Test equals diferent id same role.
     */
    @Test
    void testEqualsDiferentIdSameRole() {
        Role  roleA  = new Role(2, AuthenticationController.ROLE_AGENT);
        Role  roleB  = new Role(5, AuthenticationController.ROLE_AGENT);
        assertEquals(true, roleA.equals(roleB) );
    }

    /**
     * Test not equals diferent role same id.
     */
    @Test
    void testNotEqualsDiferentRoleSameId() {
        Role  roleA  = new Role(2, AuthenticationController.ROLE_AGENT);
        Role  roleB  = new Role(2, AuthenticationController.ROLE_ADMIN);
        assertEquals(false, roleA.equals(roleB) );
    }

    /**
     * Test not equals diferent type.
     */
    @Test
    void testNotEqualsDiferentType() {
        Role  role  = new Role(2, AuthenticationController.ROLE_AGENT);
        Store  store  = new Store(10, "PPROG", "pprog@this.app", new Address("teste","teste","teste","teste"), 000000000);
        assertEquals(false, role.equals(store) );
    }
}