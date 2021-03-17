package be.obss.datex2.statefulpush.poc.util;

import eu.datex2.schema._3.common.Validity;
import eu.datex2.schema._3.exchangeinformation.ExchangeContext;
import eu.datex2.schema._3.exchangeinformation.ExchangeInformation;
import eu.datex2.schema._3.exchangeinformation.Subscription;

public class ObjectBuilder {

    public static ExchangeInformation createSimpleExchangeInformationObject() {
        ExchangeInformation exchangeInformation = new ExchangeInformation();
        Subscription subscription = new Subscription();
        Validity validity = new Validity();
        subscription.setValidity(validity);
        ExchangeContext exchangeContext = new ExchangeContext();
        exchangeContext.setSubscription(subscription);
        exchangeInformation.setExchangeContext(exchangeContext);
        return exchangeInformation;
    }
}
