/*
 * This file was generated - do not edit it directly !!
 * Generated by AuDAO tool, a product of Spolecne s.r.o.
 * For more information please visit http://www.spoledge.com
 */
package com.ypg.db.dao.gae;


import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;


import com.spoledge.audao.db.dao.gae.GaeAbstractDaoImpl;
import com.spoledge.audao.db.dao.DBException;
import com.spoledge.audao.db.dao.DaoException;


import com.ypg.db.dao.BusinesDao;
import com.ypg.db.dto.Business;


/**
 * This is the DAO imlementation class.
 *
 * @author generated
 */
public class BusinesDaoImpl extends GaeAbstractDaoImpl<Business> implements BusinesDao {

    private static final String TABLE_NAME = "Busines";

    public BusinesDaoImpl( DatastoreService ds ) {
        super( ds );
    }

    /**
     * Finds a record identified by its primary key.
     * @return the record found or null
     */
    public Business findByPrimaryKey( long id ) {
        Entity _ent = entityGet( new KeyFactory.Builder( "Busines", id ).getKey());

        return _ent != null ? fetch( null, _ent ) : null;
    }

    /**
     * Finds a record.
     */
    public Business findById( long id ) {
        Query _query = getQuery();
        _query.addFilter( "id", Query.FilterOperator.EQUAL, id );

        return findOne( _query, "id = :1", 0, id);
    }

    /**
     * Finds records ordered by id.
     */
    public Business[] findAll( ) {
        Query _query = getQuery();

        multipleQueries = false;
        String _cond = "1 = 1";

        return findManyArray( _query, _cond, 0, -1 );
    }

    /**
     * Deletes a record identified by its primary key.
     * @return true iff the record was really deleted (existed)
     */
    public boolean deleteByPrimaryKey( long id ) throws DaoException {
        return entityDelete( new KeyFactory.Builder( "Busines", id ).getKey() );
    }

    /**
     * Inserts a new record.
     * @return the generated primary key - id
     */
    public long insert( Business dto ) throws DaoException {
        Entity _ent = new Entity( "Busines");

        {
            if ( dto.getName() == null ) {
                throw new DaoException("Value of column 'name' cannot be null");
            }
            checkMaxLength( "name", dto.getName(), 500 );
            _ent.setProperty( "name", dto.getName());
        }

        entityPut( _ent, dto, "insert" );

        dto.setId( _ent.getKey().getId());

        return dto.getId();
    }

    /**
     * Updates one record found by primary key.
     * @return true iff the record was really updated (=found and any change was really saved)
     */
    public boolean update( long id, Business dto ) throws DaoException {
        Entity _ent = entityGet( new KeyFactory.Builder( "Busines", id ).getKey());
        if (_ent == null) return false;

        boolean isUpdated = false;

        if ( dto.getId() != null ) {
            isUpdated = true;
        }

        if ( dto.getName() != null ) {
            checkMaxLength( "name", dto.getName(), 500 );
            _ent.setProperty( "name", dto.getName());
            isUpdated = true;
        }

        if (!isUpdated) {
            return false;
        }

        entityPut( _ent, dto, "update" );

        return true;
    }

    /**
     * Returns the table name.
     */
    public String getTableName() {
        return TABLE_NAME;
    }

    protected Business fetch( Business dto, Entity ent ) {
        if ( dto == null ) dto = new Business();

        dto.setId( ent.getKey().getId());
        dto.setName( getString( ent, "name" ));

        return dto;
    }

    protected Business[] toArray(ArrayList<Business> list ) {
        Business[] ret = new Business[ list.size() ];
        return list.toArray( ret );
    }

}
