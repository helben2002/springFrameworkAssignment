package se.yrgo.crm.services.calls;

import java.util.Collection;

import se.yrgo.crm.domain.Action;
import se.yrgo.crm.domain.Call;
import se.yrgo.crm.services.customers.CustomerNotFoundException;

public interface CallHandlingService {
	// DO NOT IMPLEMENT THIS INTERFACE UNTIL A LATER CHAPTER!

	/**
	 * Records a call with the customer management service, and also records
	 * any actions in the diary service
	 */
	public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException;
}
