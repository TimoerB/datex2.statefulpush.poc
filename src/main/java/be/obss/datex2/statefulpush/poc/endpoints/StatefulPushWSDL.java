package be.obss.datex2.statefulpush.poc.endpoints;

import be.obss.datex2.statefulpush.poc.util.DataHolder;
import eu.datex2.schema._3.exchangeinformation.ExchangeContext;
import eu.datex2.schema._3.exchangeinformation.ExchangeInformation;
import eu.datex2.schema._3.messagecontainer.MessageContainer;
import eu.datex2.wsdl.statefulpush._2020.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.*;

import javax.xml.bind.JAXBElement;

@Endpoint
public class StatefulPushWSDL implements StatefulPushWSDLInterface {

    private static final String NAMESPACE_URI = "http://datex2.eu/wsdl/statefulPush/2020";

    private static final Logger log = LoggerFactory.getLogger(StatefulPushWSDL.class);

    private final DataHolder dataHolder;

    @Autowired
    public StatefulPushWSDL(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "putDataInput")
    public @ResponsePayload JAXBElement<ExchangeInformation> putData(@RequestPayload JAXBElement<MessageContainer> body) {
        log.info("Received putData from supplier");

        //TODO: this is a mock implementation, change this to an actual one

        dataHolder.getDatex2Publication().addAll(body.getValue().getPayloads());

        ExchangeInformation exchangeInformation = new ExchangeInformation();
        ExchangeContext exchangeContext = new ExchangeContext();
        exchangeContext.setSubscription(dataHolder.getSubscription());
        exchangeInformation.setExchangeContext(exchangeContext);
        return new ObjectFactory().createPutDataOutput(exchangeInformation);
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "putSnapshotDataInput")
    public @ResponsePayload JAXBElement<ExchangeInformation> putSnapshotData(@RequestPayload JAXBElement<MessageContainer> body) {
        log.info("Received putSnapshotData from supplier");

        //TODO: this is a mock implementation, change this to an actual one

        dataHolder.setDatex2Publication(body.getValue().getPayloads());

        ExchangeInformation exchangeInformation = new ExchangeInformation();
        ExchangeContext exchangeContext = new ExchangeContext();
        exchangeContext.setSubscription(dataHolder.getSubscription());
        exchangeInformation.setExchangeContext(exchangeContext);
        return new ObjectFactory().createPutSnapshotDataOutput(exchangeInformation);
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "openSessionInput")
    public @ResponsePayload JAXBElement<ExchangeInformation> openSession(@RequestPayload JAXBElement<ExchangeInformation> body) {
        log.info("Opening session!");

        //TODO: this is a mock implementation, change this to an actual one

        //echo the response back and keep the subscription object
        dataHolder.setSubscription(body.getValue().getExchangeContext().getSubscription());
        return new ObjectFactory().createOpenSessionOutput(body.getValue());
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "closeSessionInput")
    public @ResponsePayload JAXBElement<ExchangeInformation> closeSession(@RequestPayload JAXBElement<ExchangeInformation> body) {
        //TODO: this is a mock implementation, change this to an actual one
        return null;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "keepAliveInput")
    public @ResponsePayload JAXBElement<ExchangeInformation> keepAlive(@RequestPayload JAXBElement<ExchangeInformation> body) {
        log.info("Keeping session alive, echoing back");

        //TODO: this is a mock implementation, change this to an actual one

        return new ObjectFactory().createKeepAliveOutput(body.getValue());
    }
}
