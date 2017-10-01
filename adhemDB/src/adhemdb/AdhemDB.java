/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adhemdb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saeed
 */
public class AdhemDB {

    private Connection getCon() {
        Connection con = null;
        try {
            String dbURL = "jdbc:sqlserver://SQL5018.myASP.NET\\DB_A1F51A_att:1433";
            String user = "DB_A1F51A_att_admin";
            String pass = "mean1994";
            con = DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return con;
    }

    private Connection getCon2() {
        Connection con = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String dbURL = "jdbc:jtds:sqlserver://SQL5018.myASP.NET;instance=SQLEXPRESS;DatabaseName=DB_A1F51A_att";

            String user = "DB_A1F51A_att_admin";
            String pass = "mean1994";
            con = DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdhemDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    private boolean getLogin(String usernametxt, String Passwordtxt) {
        Connection con = getCon2();
        int count = 0;
        try {

            if (con != null) {
                String sql = "SELECT username,pass FROM person where username = '" + usernametxt + "' and pass = '" + Passwordtxt + "'";
                Statement stmt = null;

                try {
                    stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    while (rs.next()) {
                        count++;
                    }

                } finally {
                    if (stmt != null) {
                        stmt.close();
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return (count > 0);
    }

    private void showImg() {

        Connection con = getCon2();

        try {
            if (con != null) {
                String sql = "select Img from person where username = '" + "shafy" + "' "
                        + "and pass = '" + "shafy" + "'";

                Statement stmt = null;
                String imgString = "";
                byte[] imgBytes;
                try {
                    stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        //img code
                        //imgString = rs.getString("Img");
                        imgBytes = rs.getBytes("Img");
                        OutputStream targetFile
                                = null;
                        try {
                            File picFile = new File("./pic.jpg");
                            picFile.createNewFile();
                            targetFile = new FileOutputStream(
                                    picFile);

                            targetFile.write(imgBytes);
                            targetFile.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                } finally {
                    if (stmt != null) {
                        stmt.close();
                    }
                    //set the pic
//                    if (imgString != "") {
//                        byte[] decodeString = Base64.decode(imgString, Base64.DEFAULT);
//                        Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
//                        iv.setImageBitmap(decodebitmap);
//                    }

                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        AdhemDB ad = new AdhemDB();
        System.out.println(ad.getLogin("fedy", "fedy"));
        ad.showImg();

    }

}
