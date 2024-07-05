package aitt.bookerapp.service;

import aitt.bookerapp.model.OfficeModel;

public interface OfficeService {
    OfficeModel getHours();
    OfficeModel saveHours(OfficeModel officeModel);
}
