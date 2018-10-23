/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entidades.Tema;

@Stateless
@LocalBean
 public class DaoTemasEjb implements DaoTemasEjbLocal {
	 
 
	@PersistenceContext(unitName="libreria_PU")
	EntityManager em;
    @Override
	public List<Tema> obtenerTemas(){
    	
    	TypedQuery<Tema> tq=em.createNamedQuery("Tema.findAll",Tema.class);
        return tq.getResultList();
           
        
    }
}
