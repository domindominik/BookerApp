package aitt.bookerapp.service;

import aitt.bookerapp.model.OfficeModel;
import aitt.bookerapp.repository.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {
    private final OfficeRepository officeRepository;


    @Override
    public OfficeModel getHours() {
        return officeRepository.findById(1L).orElse(null);
    }

    @Override
    public OfficeModel saveHours(OfficeModel officeModel) {
        officeModel.setId(1L);
        return officeRepository.save(officeModel);
    }
}
