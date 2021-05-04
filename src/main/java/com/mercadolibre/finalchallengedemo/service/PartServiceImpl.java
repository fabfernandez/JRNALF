package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartServiceImpl implements IPartService {

    private PartRepository partRepository;

    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<PartDTO> getAll() {
        return this.partRepository.findAll();
    }

    @Override
    public List<PartDTO> getPartsByFilter(PartFilterDTO filter) {
        switch (filter.getQueryType()) {
            case 'P':
                return this.partRepository.findPartsModifiedSinceDate(filter.getDate());
            case 'V':
                return this.partRepository.findPartsPriceModifiedSinceDate(filter.getDate());
            case 'C':
            default:
                return getAll();
        }
    }



}
