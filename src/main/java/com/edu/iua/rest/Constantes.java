package com.edu.iua.rest;

public class Constantes {
	
	public static final String URL_API = "/api";
	public static final String URL_API_VERSION = "/v1";
	public static final String URL_BASE = URL_API + URL_API_VERSION;

	public static final String URL_CAMIONES = URL_BASE + "/camiones";
    public static final String URL_CHOFERES = URL_BASE + "/choferes";
	public static final String URL_CLIENTES = URL_BASE + "/clientes";
    public static final String URL_PRODUCTOS = URL_BASE + "/productos";
    public static final String URL_ORDENES = URL_BASE + "/ordenes";
    public static final String URL_CISTERNAS = URL_BASE + "/cisternas";
    
    public static final Integer PERIODO_ALMACENAMIENTO = 10;

    public static final String URL_AUTH_INFO = "/auth-info";

	public static final String URL_LOGOUT = "/logout-token";

	public static final String URL_AUTH = URL_BASE + "/auth";
	
	public static final String URL_USER = URL_BASE + "/users";

    public static final String TOPIC_SEND_WEBSOCKET_GRAPH="/iw3/data";
    public static final String URL_WEBSOCKET_ENPOINT=URL_BASE + "/ws";

}
