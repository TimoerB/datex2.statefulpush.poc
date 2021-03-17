package be.obss.datex2.statefulpush.poc;

import be.obss.datex2.statefulpush.poc.util.ObjectBuilder;
import eu.datex2.schema._3.common.ValidityStatusEnum;
import eu.datex2.wsdl.statefulpush._2020.ObjectFactory;
import eu.datex2.wsdl.statefulpush._2020.StatefulPushService;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulPushTest extends ApplicationTests {

    @Test
    public void openSession() {

        assertThat(new StatefulPushService().getStatefulPushSoapEndPoint().openSession(new ObjectFactory().createOpenSessionInput(ObjectBuilder.createSimpleExchangeInformationObject()).getValue())
                .getExchangeContext()
                .getSubscription()
                .getValidity()
                .getValidityStatus()).isEqualTo(ValidityStatusEnum.ACTIVE);

    }

}
