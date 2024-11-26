package se.yrgo.crm.dataaccess;

import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import se.yrgo.crm.domain.Action;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ActionDaoJpaImpl implements ActionDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Action newAction) {
        em.persist(newAction);
    }

    @Override
    public List<Action> getIncompleteActions(String userId) {
        return em.createQuery("select action from Action as action where " +
                "action.owningUser=:userId AND action.complete = false" ,
                Action.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public void update(Action actionToUpdate) throws RecordNotFoundException {
        Action action = em.find(Action.class, actionToUpdate.getActionId());

        if (action == null) {
            throw new RecordNotFoundException();
        }

        action.setDetails(actionToUpdate.getDetails());
        action.setOwningUser(actionToUpdate.getOwningUser());
        action.setRequiredBy(actionToUpdate.getRequiredBy());
    }

    @Override
    public void delete(Action oldAction) throws RecordNotFoundException {
        try {
            Action actionToDelete = em.find(Action.class, oldAction.getActionId());
            em.remove(actionToDelete);
        }catch (javax.persistence.NoResultException e) {
            throw new RecordNotFoundException();
        }
    }
}
