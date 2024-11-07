package se.yrgo.crm.services.diary;

import se.yrgo.crm.domain.Action;
import se.yrgo.crm.services.calls.CallHandlingService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiaryManagementServiceMockImpl implements DiaryManagementService {
    CallHandlingService callHandlingService;

    private final Set<Action> allActions = new HashSet<>();

    @Override
    public void recordAction(Action action) {
        allActions.add(action);
    }

    //Hint:
    //Create a list<Action>
    //In the for each loop going through the list use this condition: "if(action.getOwningUser().equals(requiredUser) && !action.isComplete())" to add a new action to the list.
    public List<Action> getAllIncompleteActions(String requiredUser) {
        List<Action> incompleteActions = new ArrayList<>();
        for (Action action : allActions) {
            if (action.getOwningUser().equals(requiredUser) && !action.isComplete()) {
                incompleteActions.add(action);
            }
        }
        return incompleteActions;
    }
}
