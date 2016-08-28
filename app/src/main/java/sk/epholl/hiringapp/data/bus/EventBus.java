package sk.epholl.hiringapp.data.bus;

import com.squareup.otto.Bus;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public class EventBus {

    private static Bus sBus;

    public static void init() {
        sBus = new Bus();
    }

    public static Bus getInstance() {
        if (sBus == null) {
            throw new IllegalStateException("Event bus must be initialized first");
        }
        return sBus;
    }
}
