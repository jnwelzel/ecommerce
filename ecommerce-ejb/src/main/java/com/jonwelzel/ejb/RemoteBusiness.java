package com.jonwelzel.ejb;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.jonwelzel.persistence.dao.IDAO;
import com.jonwelzel.persistence.entities.Bean;

public interface RemoteBusiness<PK extends Serializable, T extends Bean<PK>> {

	public EntityManager getEntityManager();

	public IDAO<PK, T> getDAO();

}
