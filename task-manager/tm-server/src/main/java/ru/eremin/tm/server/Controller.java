package ru.eremin.tm.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @autor av.eremin on 18.04.2019.
 */

@WebService
public class Controller {

    @WebMethod
    public int calc() {
        return 6;
    }
}
