package ru.eremin.tm.endpoint;

import javax.jws.WebService;

/**
 * @autor av.eremin on 27.05.2019.
 */

@WebService
public interface ProjectEndpoint {

    String ping(int a);
}
