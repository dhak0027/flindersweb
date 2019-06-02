package com.flinders.poc.common.entity.listener;

/**
 * BasicAuditEntityListener for all entities
 * @author mswahithali
 */

import javax.persistence.Persistence;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.flinders.poc.common.constants.ActionMode;
import com.flinders.poc.common.entity.BasicEntity;

public class BasicAuditEntityListener extends AuditingEntityListener{
	
	@Autowired
    protected Persistence persistence;
	
	@PrePersist
    public void prePersist(BasicEntity target) {
        perform(target, ActionMode.CREATE);
    }
	
    @PreUpdate
    public void preUpdate(BasicEntity target) {
        perform(target, ActionMode.MODIFY);
    }
    
    @PreRemove
    public void preRemove(BasicEntity target) {
        perform(target, ActionMode.DELETE);
    }
    
    @Transactional
    private void perform(BasicEntity target, ActionMode actionMode) {
    	
    }
   
}
