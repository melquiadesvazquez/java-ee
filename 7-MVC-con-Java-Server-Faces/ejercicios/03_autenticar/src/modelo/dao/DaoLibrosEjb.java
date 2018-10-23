/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entidades.Libro;
import entidades.Tema;

@Stateless
@LocalBean
 public class DaoLibrosEjb implements DaoLibrosEjbLocal {
	 
    
	@PersistenceContext(unitName="libreria_PU")
	EntityManager em;
    @Override
	public List<Libro> recuperarLibros(){
    	TypedQuery<Libro> tq=em.createNamedQuery("Libro.findAll",Libro.class);
        return tq.getResultList(); 
    }
    
    @Override
	public List<Libro> recuperarLibrosPaginas(int pagina){
    	int tam=10;
    	int primero=pagina*tam+1;
    	TypedQuery<Libro> tq=em.createNamedQuery("Libro.findAll",Libro.class);
    	tq.setFirstResult(primero);
    	tq.setMaxResults(tam);
    	return tq.getResultList();
    }
    
    
    @Override
	public List<Libro> recuperarLibros(int idTema){
    	Tema t=em.find(Tema.class, idTema);
    	return t.getLibros();
    }
   
    @Override
	public Libro recuperarLibroPorIsbn(int isbn) {
		return em.find(Libro.class, isbn);
	}
    
    
    
}
