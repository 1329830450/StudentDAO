package daoIMP;
import bean.Student;
import dao.StudentDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connection.DataBaseConnection;

public class StudentDAOIMP implements StudentDAO {
    @Override
    public void insert(Student s) {
        String sql = "INSERT INTO student (id, name) values (?,?)";
        PreparedStatement pstmt = null;
        DataBaseConnection conn = null;
        //针对数据库的具体操作
        try{
            conn = new DataBaseConnection();

            pstmt = conn.getConnection().prepareStatement(sql);
            pstmt.setLong(1,s.getID());
            //pstmt.setString(1,s.getID());
            pstmt.setString(2,s.getName());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        }
        catch(Exception e){  }
    }

    @Override
    public void update(Student s) {

    }


    @Override
    public void delete(String iD) {
        String sql = "DELETE FROM student Where id = ? ";
        PreparedStatement pstmt = null;
        DataBaseConnection conn = null;

        try{
            conn = new DataBaseConnection();
            pstmt = conn.getConnection().prepareStatement(sql) ;
            pstmt.setLong(1,Long.valueOf("iD") ) ;
            pstmt.executeUpdate() ;
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student findByID(long iD) {
        return null;
    }


    @Override
    public List<Student> findAll() {
        List<Student> stulist = new ArrayList<Student>() ;
        Student student = null;
        String sql = "SELECT id,name FROM student";
        PreparedStatement pstmt = null;
        DataBaseConnection conn = null;
        try{
            conn = new DataBaseConnection() ;
            pstmt = conn.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next() ){
                student = new Student();
                student.setID(rs.getLong(1)) ;
                student.setName(rs.getString(2)) ;
                rs.close() ;
                pstmt.close() ;
                conn.close() ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stulist;
    }
}
