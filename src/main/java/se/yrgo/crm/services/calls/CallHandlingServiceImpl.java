package se.yrgo.crm.services.calls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.crm.domain.Action;
import se.yrgo.crm.domain.Call;
import se.yrgo.crm.services.customers.CustomerManagementService;
import se.yrgo.crm.services.customers.CustomerNotFoundException;
import se.yrgo.crm.services.diary.DiaryManagementService;

import java.util.Collection;

@Service
@Transactional
public class CallHandlingServiceImpl implements CallHandlingService {
    @Autowired
    CustomerManagementService customerManagementService;
    @Autowired
    DiaryManagementService diaryManagementService;

    @Override
    public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException {
        customerManagementService.recordCall(customerId, newCall);
        for (Action action : actions) {
            diaryManagementService.recordAction(action);
        }
    }
}
