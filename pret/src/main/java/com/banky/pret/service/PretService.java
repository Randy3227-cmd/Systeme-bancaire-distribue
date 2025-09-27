package com.banky.pret.service;

import com.banky.pret.repository.PretRepository;
import com.banky.pret.model.Pret;

public class PretService {
        private PretRepository pretRepository;
    
        public PretService(PretRepository pretRepository) {
            this.pretRepository = pretRepository;
        }
    
        public Pret addPret(Pret pret) {
            return pretRepository.save(pret);
        }
        public List<Pret> getAllPret() {
            return pretRepository.findAll();
        }
    
        public Pret updatePret(Pret p) {
            return pretRepository.save(p);
        }
    
        public void deletePretById(Long id){
            pretRepository.deleteById(id);
        }
    
}
