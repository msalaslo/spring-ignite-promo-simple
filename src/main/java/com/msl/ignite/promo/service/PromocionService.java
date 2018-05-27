package com.msl.ignite.promo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msl.ignite.promo.SpringDataIgnitePromoApplication;
import com.msl.ignite.promo.entity.Promocion;
import com.msl.ignite.promo.repository.PromocionRepository;

@Service
public class PromocionService {

//	@Autowired
	private PromocionRepository repository = SpringDataIgnitePromoApplication.getPromocionrepository();
	
//    public Promocion findByid(String id){
//    	return repository.findOne(id);
//    }
    
    public Promocion findByCodpromoci(String codpromoci){
    	return repository.findByCodpromoci(codpromoci);
    }
    
    public Iterable<Promocion> findByCanlvnta(String canlvnta){
    	return repository.findByCanlvnta(canlvnta);
    }
    
	public Promocion save(Promocion promocion) {
		return repository.save(promocion);
	}
}
