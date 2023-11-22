package modelo;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vistacontrolador.PantallaCrear;

public class EntradaSalida {

    private static java.sql.Connection con = null;
    private static Statement stm = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rs = null;
    private static String cad_conexion = "jdbc:mysql://localhost:3306/";
    private static String admin = "root";
    private static PantallaCrear pc = new PantallaCrear();

    public void crearBase() {
        try {
            iniciarConexion();
            stm.executeUpdate(BD.getCREARBD());
            stm.executeUpdate(BD.getUSARBD());
            stm.executeUpdate(BD.getCREARTABLA());
            stm.executeUpdate(BD.getDATOSTABLA());
        } catch (SQLException ex) {
            Logger.getLogger(EntradaSalida.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void cerrarConexion() {
        try {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntradaSalida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> listUsuarios() throws SQLException {
        iniciarConexion();
        ArrayList<String> usuA = new ArrayList<>();
        String consulta = "SELECT u.* FROM usuarios u; ";

        try {
            pstm = con.prepareStatement(consulta);
            ResultSet rs1 = pstm.executeQuery();
            while (rs1.next()) {
                String usu = rs1.getString("nombre");
                usuA.add(usu);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR SQL");
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return usuA;
    }

    public void iniciarConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cad_conexion, admin, "");
            stm = con.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver");
        } catch (SQLException ex) {
            Logger.getLogger(EntradaSalida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean verificarCredenciales(String usuario, String contraseña) {

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practica2_6", admin, "");

            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, usuario);
            pstm.setString(2, contraseña);

            rs = pstm.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void agregarUsuario(String usuario, String contraseña, String nombre, String apellido1, String apellido2, String fechanac, String correo) {
        try {
            con = DriverManager.getConnection(cad_conexion + "practica2_6", admin, "");
            String sql = "INSERT IGNORE INTO usuarios VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstm = con.prepareStatement(sql);

            pstm.setString(1, usuario);
            pstm.setString(2, contraseña);
            pstm.setString(3, nombre);
            pstm.setString(4, apellido1);
            pstm.setString(5, apellido2);
            pstm.setString(6, fechanac);
            pstm.setString(7, correo);

            pstm.executeUpdate();
            JOptionPane.showMessageDialog(pc, "Usuario agregado correctamente");
            System.out.println("Usuario agregado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al agregar el usuario a la base de datos.");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean consultar(String usuario_e, String contra_e) {
        try {
            con = DriverManager.getConnection(cad_conexion + "practica2_6", admin, "");
            stm = con.createStatement();
            rs = stm.executeQuery("SELECT usuario, contraseña FROM usuarios WHERE usuario = '" + usuario_e + "' and contraseña = '" + contra_e + "'");

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("edfsfsf");
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean consultar2(String usuario_e, String contra_e) {
        try {
            con = DriverManager.getConnection(cad_conexion + "practica2_6", admin, "");
            stm = con.createStatement();
            String consulta = "SELECT usuario, contraseña FROM usuarios WHERE usuario = ? AND contraseña = ?";
            pstm = con.prepareStatement(consulta);
            pstm.setString(1, usuario_e);
            pstm.setString(2, contra_e);
            rs = pstm.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean modificarContraseña(String usuario_e, String contra_e) {
        try {
            con = DriverManager.getConnection(cad_conexion + "practica2_6", admin, "");
            stm = con.createStatement();
            stm.executeUpdate("UPDATE usuarios SET contraseña = '" + contra_e + "' WHERE usuario = '" + usuario_e + "'");
            return true;

        } catch (SQLException ex) {
            return false;
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
