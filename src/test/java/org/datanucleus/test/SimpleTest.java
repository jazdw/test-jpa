package org.datanucleus.test;

import java.util.*;
import org.junit.*;
import javax.persistence.*;

import static org.junit.Assert.*;
import mydomain.model.*;
import org.datanucleus.util.NucleusLogger;

public class SimpleTest
{
    @Test
    public void testSimple()
    {
        NucleusLogger.GENERAL.info(">> test START");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyTest");

        Person jim = new Person(0, 1, "Jim");
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try
        {
            tx.begin();
            em.persist(jim);
            tx.commit();
        }
        catch (Throwable thr)
        {
            NucleusLogger.GENERAL.error(">> Exception in test", thr);
            fail("Failed test : " + thr.getMessage());
        }
        finally 
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            em.close();
        }
        
        // e.g. de-serialised
        Person jimBrown = new Person(1, 1, "Jim Brown");
        
        em = emf.createEntityManager();
        tx = em.getTransaction();
        try
        {
            tx.begin();
            
            em.merge(jimBrown);
            
            tx.commit();
        }
        catch (Throwable thr)
        {
            NucleusLogger.GENERAL.error(">> Exception in test", thr);
            fail("Failed test : " + thr.getMessage());
        }
        finally 
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            em.close();
        }

        emf.close();
        NucleusLogger.GENERAL.info(">> test END");
    }
}
