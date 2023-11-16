package modelo;

/**
 *
 * @author dam2
 */
public class BD {

    private static String CREARBD = "CREATE DATABASE IF NOT EXISTS Practica2_6;";
    private static String USARBD = "USE Practica2_6;";
    private static String CREARTABLA = "CREATE TABLE IF NOT EXISTS usuarios ( "
            + "usuario VARCHAR(25) NOT NULL, "
            + "contraseña VARCHAR(25) NOT NULL, "
            + "nombre VARCHAR(25), "
            + "apellido1 VARCHAR(25), "
            + "apellido2 VARCHAR(25), "
            + "fechanac VARCHAR(25), "
            + "correo VARCHAR(100));";
    private static String DATOSTABLA = "INSERT IGNORE INTO usuarios (usuario, contraseña, nombre, apellido1, apellido2, fechanac, correo) VALUES "
            + "('yago', '1234', '', '', '', '1990-01-01', ''),"
            + "('pedro', '123', '', '', '', '2000-02-15', ''),"
            + "('dragos', '123', '', '', '', '1995-07-20', ''),"
            + "('pisha', '123', '', '', '', '1980-05-10', ''),"
            + "('ronald', '123', '', '', '', '1998-11-30', '');";

    public BD() {
    }

    public static String getCREARBD() {
        return CREARBD;
    }

    public static void setCREARBD(String CREARBD) {
        BD.CREARBD = CREARBD;
    }

    public static String getUSARBD() {
        return USARBD;
    }

    public static void setUSARBD(String USARBD) {
        BD.USARBD = USARBD;
    }

    public static String getCREARTABLA() {
        return CREARTABLA;
    }

    public static void setCREARTABLA(String CREARTABLA) {
        BD.CREARTABLA = CREARTABLA;
    }

    public static String getDATOSTABLA() {
        return DATOSTABLA;
    }

    public static void setDATOSTABLA(String DATOSTABLA) {
        BD.DATOSTABLA = DATOSTABLA;
    }

}
