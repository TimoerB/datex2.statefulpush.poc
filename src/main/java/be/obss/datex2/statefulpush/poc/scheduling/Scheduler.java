package be.obss.datex2.statefulpush.poc.scheduling;

import be.obss.datex2.statefulpush.poc.util.DataHolder;
import be.obss.datex2.statefulpush.poc.util.ObjectBuilder;
import eu.datex2.schema._3.common.ValidityStatusEnum;
import eu.datex2.schema._3.exchangeinformation.ExchangeInformation;
import eu.datex2.wsdl.statefulpush._2020.ObjectFactory;
import eu.datex2.wsdl.statefulpush._2020.StatefulPushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    private StatefulPushService statefulPushService = new StatefulPushService();

    private final DataHolder dataHolder;

    @Autowired
    public Scheduler(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    @Scheduled(fixedRateString = "${scheduler.update.milliseconds}")
    public void cron() {

        if (dataHolder.getSubscription() == null || dataHolder.getSubscription().getValidity() == null || dataHolder.getSubscription().getValidity().getValidityStatus() != ValidityStatusEnum.ACTIVE) {
            log.info("No subscription available, trying to subscribe.");
            ExchangeInformation exchangeInformation = statefulPushService.getStatefulPushSoapEndPoint().openSession(new ObjectFactory().createOpenSessionInput(ObjectBuilder.createSimpleExchangeInformationObject()).getValue());
            dataHolder.setSubscription(exchangeInformation.getExchangeContext().getSubscription());
            log.info("Subscription object saved");
        }
        else {
            log.info("Subscription is already active.");
        }
    }


}
