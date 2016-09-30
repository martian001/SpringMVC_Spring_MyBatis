package com.et.shiro;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("memcachedSessionDAO")
public class MemcachedSessionDAO extends AbstractSessionDAO {
    private Logger logger = LoggerFactory.getLogger(MemcachedSessionDAO.class);

    public MemcachedSessionDAO() {

    }

    @Override
    public void delete(Session session) {
        try {

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug(e.getMessage());
            }
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {
        try {

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        try {

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug(e.getMessage());
            }
        }
    }

    @Override
    protected Serializable doCreate(Session session) {
        try {

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug(e.getMessage());
            }
        }
        return null;
    }

    @Override
    protected Session doReadSession(Serializable session) {
        try {

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug(e.getMessage());
            }
        }
        return null;
    }

}
