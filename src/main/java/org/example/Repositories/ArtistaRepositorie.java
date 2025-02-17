package org.example.Repositories;

import org.example.Entities.Artista;
import org.example.Log.Loggable;
import org.example.infrastructure.DataBaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistaRepositorie implements GenericRepositorie<Artista>, Loggable<Artista> {
    @Override
    public void add(Artista artista) {
        String sql = "INSERT INTO Artista(nome, generoMusical) Values (?, ?)";

        try(Connection conn = DataBaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, artista.getNome());
            stmt.setString(2, artista.getGeneroMusical());

            stmt.executeUpdate();
            logInfo("Artist successfuly registered!");
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error registering artist");
        }
    }

    @Override
    public ArrayList<Artista> display() {
        String sql = "SELECT * FROM Artista";
        ArrayList<Artista> artistas = new ArrayList<>();

        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()){

            while (rs.next()){
                Artista artista = new Artista();
                artista.setId(rs.getInt("id"));
                artista.setNome(rs.getString("nome"));
                artista.setGeneroMusical(rs.getString("generoMusical"));
                artistas.add(artista);
            }
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("error displaying");
        }
        return artistas;
    }

    @Override
    public void edit(Artista artista) {
        String sql = "UPDATE Artista SET nome = ?, generoMusical = ? WHERE id = ?";

        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, artista.getNome());
            stmt.setString(2, artista.getGeneroMusical());
            stmt.setInt(3, artista.getId());

            int rowsUpdated = stmt.executeUpdate();
            if(rowsUpdated > 0){
                logInfo("Artist successfully edited");
            }
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error editing artist");
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Artista WHERE id =?";

        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            int rowsDeleated = stmt.executeUpdate();
            if(rowsDeleated > 0){
                logInfo("Artisr deleated ");
            }
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error when deleting Artist");
        }

    }

    @Override
    public Artista findById(int id) {
        String sql = "SELECT * FROM Artista WHERE id = ?";
        Artista artista = null;

        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                artista = new Artista();
                artista.setId(rs.getInt("id"));
                artista.setNome(rs.getString("nome"));
                artista.setGeneroMusical(rs.getString("genroMusical"));
            }
            return artista;
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error when finding by id");
        }
        return artista;
    }

    public List<Artista> findByGener(String genero){
        List<Artista> artistas = new ArrayList<>();

        String sql = "SELECT * FROM Artista WHERE generoMusical = ?";

        try(Connection conn = DataBaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, genero);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int idArtista = rs.getInt("id");
                String nomeArtista = rs.getString("nome");

                Artista artista = new Artista(idArtista, nomeArtista);
                artistas.add(artista);
            }
            return artistas;
        }catch (SQLException e){
            e.printStackTrace();
            logInfo("Error when finding by gender");
        }
        return artistas;
    }
}
