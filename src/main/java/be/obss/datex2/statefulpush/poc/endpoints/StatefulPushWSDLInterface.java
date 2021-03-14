package be.obss.datex2.statefulpush.poc.endpoints;

import eu.datex2.schema._3.exchangeinformation.ExchangeInformation;
import eu.datex2.schema._3.messagecontainer.MessageContainer;

import javax.xml.bind.JAXBElement;

public interface StatefulPushWSDLInterface {

    JAXBElement<ExchangeInformation> putData(JAXBElement<MessageContainer> body);

    JAXBElement<ExchangeInformation> putSnapshotData(JAXBElement<MessageContainer> body);

    JAXBElement<ExchangeInformation> openSession(JAXBElement<ExchangeInformation> body);

    JAXBElement<ExchangeInformation> closeSession(JAXBElement<ExchangeInformation> body);

    JAXBElement<ExchangeInformation> keepAlive(JAXBElement<ExchangeInformation> body);
}
