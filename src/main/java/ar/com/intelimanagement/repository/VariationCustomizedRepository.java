package ar.com.intelimanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.com.intelimanagement.domain.User;
import ar.com.intelimanagement.domain.Variation;
import ar.com.intelimanagement.service.dto.VariationDTO;

public interface VariationCustomizedRepository {
	
	public Page<Variation> getPending(Pageable pageable, User u);
	
}
