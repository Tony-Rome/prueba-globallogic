package com.gl.test.utils;

public class ErrorMessageUtil {

    public static String BAD_CREDENTIALS_ERR_DESC = "Email/Password incorrecta";

    public final static String USER_NOT_FOUND = "Usuario con email entrante no encontrado";

    public final static String EMAIL_EXISTS_ERR_DESC = "Email ya registrado";

    public final static String EMAIL_ERR_DESC = "Email no válido, debe ser formato aaaaaaa@dominio.algo";
    public final static String PASSWORD_ERR_DESC = "Clave invalida, debe contener las siguientes reglas: " +
            "Una mayúscula, dos números, minúsculas y  largo total entre 8-12 caracteres";
    public static String NAME_ERR_DESC = "Nombre no valido";

    public final static String PHONE_ERR_DESC = "Número teléfono no válido";
    public final static String CITY_CODE_ERR_DESC = "Código ciudad no válido";
    public final static String COUNTRY_CODE_ERR_DESC = "Código país no válido";

    public static final String JWT_ERR_DESC = "Token no válido";
    public static final String EMPTY_JWT_ERR_DESC = "No hay token para validar";

    public static final String GLOBAL_ERR_DESC = "Oops! Ha ocurrido un error interno, por favor verificar datos y volver a intentarlo";
    public static final String NULL_VALUE_ERR_DESC = "Error dato nulo, por favor verificar datos de entrada";
}
