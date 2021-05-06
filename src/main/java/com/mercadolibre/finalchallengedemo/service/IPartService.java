package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.PartResponseDTO;

import java.util.List;

public interface IPartService {
    PartResponseDTO getAll();

    PartResponseDTO getPartsByFilter(PartFilterDTO filter);

    void deletePart(Integer id);

    void savePart(PartDTO part);

    PartDTO findPart(Integer id);
}
