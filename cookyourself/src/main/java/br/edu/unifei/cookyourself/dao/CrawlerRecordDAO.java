/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unifei.cookyourself.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.edu.unifei.cookyourself.model.CrawlerRecord;
import org.hibernate.Session;

/**
 *
 * @author guilherme
 */
@Component
public class CrawlerRecordDAO extends GenericDAO<CrawlerRecord>{

    public CrawlerRecordDAO(Session session) {
        super(session);
    }
    
}
