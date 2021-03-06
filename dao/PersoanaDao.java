package dao;

import model.Persoana;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersoanaDao {


    private Connection connection;
    private PreparedStatement createStatement;
    private PreparedStatement findAllStatement;
    private PreparedStatement findByNumeStatement;
    private PreparedStatement deleteStatement;


    public PersoanaDao(Connection connection) {
        this.connection = connection;

        try {
            createStatement = connection.prepareStatement("INSERT INTO persoana VALUES (null, ?)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            findAllStatement = connection.prepareStatement("SELECT * FROM persoana");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            findByNumeStatement = connection.prepareStatement("SELECT * FROM persoana WHERE nume = ?");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            deleteStatement = connection.prepareStatement("DELETE FROM persoana WHERE id = ?");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public boolean create(Persoana p) {

        try {
            createStatement.setString(1, p.getNume());
            return createStatement.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    public List<Persoana> findAll() {
        List<Persoana> persoane = new ArrayList<>();

        try {
            ResultSet rs = findAllStatement.executeQuery();
            while (rs.next())//cursorul
            {
                Persoana p = new Persoana(
                        rs.getInt("id"),
                        rs.getString("nume"));

                persoane.add(p);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return persoane;
    }

    public Optional<Persoana> findByNume(String nume) {
        try {
            findByNumeStatement.setString(1, nume);

            ResultSet rs = findByNumeStatement.executeQuery();
            if (rs.next()) {
                return Optional.of(new Persoana(rs.getInt("id")
                        , rs.getString("nume")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean delete(int id)
    {
        try {
            deleteStatement.setInt(1,id);
            return deleteStatement.executeUpdate()!=0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

return false;
    }
}
