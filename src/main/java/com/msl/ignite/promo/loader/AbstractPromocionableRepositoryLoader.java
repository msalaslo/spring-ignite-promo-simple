package com.msl.ignite.promo.loader;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.msl.ignite.promo.SpringDataIgnitePromoApplication;
import com.msl.ignite.promo.entity.Promocion;
import com.msl.ignite.promo.entity.Promocionable;
import com.msl.ignite.promo.repository.PromocionRepository;

public abstract class AbstractPromocionableRepositoryLoader implements IPromocionableRepositoryLoader {

	private static final Logger logger = LoggerFactory.getLogger(AbstractPromocionableRepositoryLoader.class);	
	
//	@Autowired
	private PromocionRepository promocionRepo = SpringDataIgnitePromoApplication.getPromocionrepository();

	@Override
	public void loadPromociones(Iterable<Promocionable> promocionables) {
		for (Promocionable promocionable : promocionables) {	
			Promocion promocion = randomPromocion();
			logger.debug("Asociando la promocion " + promocion + " al promocionable " + promocionable );
			this.save(promocionable, promocion);		
		}			
	}
	
	public Promocion randomPromocion() {
		logger.debug("Recogiendo promocion aleatoria");
	    Long qty = promocionRepo.count();
	    int idx = (int)(Math.random() * qty);
//	    Page<Promocion> questionPage = promocionRepo.findAll(PageRequest.of(idx, 1));
//	    Page<Promocion> questionPage = promocionRepo.findAll(new PageRequest(idx, 1));
	    Iterable<Promocion> questionPage = promocionRepo.findAll();


	    Promocion q = questionPage.iterator().next();
		logger.debug("Promocion aleatoria recogida");
	    return q;
	}

	public void save(Promocionable promocionable, Promocion promocion) {
		List<Promocion> promociones = promocionable.getPromociones();
		if(promociones == null) {
			promociones = new ArrayList<Promocion>();
		}
		promociones.add(promocion);
		promocionable.setPromociones(promociones);
		this.saveCasting(promocionable);
	}

	public abstract void saveCasting(Promocionable promocionable);

}
