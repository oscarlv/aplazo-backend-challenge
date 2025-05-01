package com.aplazo.challenge.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServiceConstants {

    /*--API Version--*/
    public static final String API_VERSION_V1 = "/v1";

    /*--Paths--*/
    public static final String CUSTOMERS_PATH = API_VERSION_V1 + "/customers";
    public static final String LOANS_PATH = API_VERSION_V1 + "/loans";

    /*--Headers--*/
    public static final String AUTH_HEADER = "X-Auth-Token";

    /*--HTTP Verbs--*/
    public static final String POST = "POST";
}
