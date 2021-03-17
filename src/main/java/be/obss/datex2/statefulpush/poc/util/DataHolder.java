package be.obss.datex2.statefulpush.poc.util;

import eu.datex2.schema._3.d2payload.Payload;
import eu.datex2.schema._3.exchangeinformation.Subscription;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    private Subscription subscription = new Subscription();

    private List<Payload> datex2Publication = new ArrayList<>();

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public List<Payload> getDatex2Publication() {
        return datex2Publication;
    }

    public void setDatex2Publication(List<Payload> datex2Publication) {
        this.datex2Publication = datex2Publication;
    }
}
