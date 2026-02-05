//
//  Protected to limit usage to only subclasses (Direct extend from this DAO)
//  - Guac
//

package dk.project.dao;

import jakarta.persistence.EntityManager;

public class EntityManagerDAO {

    // Attributes
    protected EntityManager em;

    // ________________________________________________________

    protected EntityManagerDAO(EntityManager em){
        this.em = em;
    }

}