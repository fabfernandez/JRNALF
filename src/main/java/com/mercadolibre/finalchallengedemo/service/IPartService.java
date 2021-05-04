package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;

import java.util.List;

public interface IPartService {
    List<PartDTO> getAll();
    List<PartDTO> getPartsByFilter(PartFilterDTO filter);

}
