package sk.epholl.hiringapp;

/**
 * Created by Tomáš Isteník on 28.08.2016.
 */
public final class Const {

    private Const() {
        throw new UnsupportedOperationException("Class " + getClass().getName() + " is not instantiable.");
    }

    public static final String DEFAULT_DATA_URL = "https://raw.githubusercontent.com/Epholl/hiring_app/master/data2.json";

}
