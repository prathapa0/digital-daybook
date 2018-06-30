package np.com.prathapa.digitaldaybook.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by susha on 6/30/2018.
 */

public class DaybookName implements Serializable {

    private String daybookName;
    private ArrayList<String> personNames;

    public DaybookName() {

    }

    public String getDaybookName() {
        return daybookName;
    }

    public void setDaybookName(String daybookName) {
        this.daybookName = daybookName;
    }

    public ArrayList<String> getPersonNames() {
        return personNames;
    }

    public void setPersonNames(ArrayList<String> personNames) {
        this.personNames = personNames;
    }
}
