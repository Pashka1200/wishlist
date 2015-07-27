package general;

import dao.InterfaseDao;
import dao.impl.ClassDao;


/**
 * Created by pavlo on 14.07.15.
 */
public class Factory {
    public static Factory instance = new Factory();

    private InterfaseDao interfaseDao;



    private Factory() {    }

    public static Factory getInstance() {
        return Factory.instance;
    }


    public InterfaseDao getInerfaseDao(Class class1) {
       if(interfaseDao == null)
       interfaseDao = new ClassDao(class1);
        return interfaseDao;
    }

}
