package np.com.prathapa.digitaldaybook.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by susha on 6/30/2018.
 */

@Table(name = "DaybookName")
public class DaybookNameModel extends Model{
    @Column(name = "daybook_name")
    public String daybookName = "";

    @Column(name = "password")
    public String password = "";

    @Column(name = "person_names")
    public String personNames = "";


    public List<DaybookNameModel> getListOfDaybookNames(){
        return new Select().from(DaybookNameModel.class).execute();
    }
}
