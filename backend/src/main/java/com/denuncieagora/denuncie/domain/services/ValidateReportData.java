package com.denuncieagora.denuncie.domain.services;

import com.denuncieagora.denuncie.domain.exceptions.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ValidateReportData {

    public void validateId(UUID id) {
        if(id.toString().equals("")) {
            throw new ReportNotFoundException("Id is invalid!");
        }
    }

    public void validateIdentity(String identity) {
        if(identity.equals("")) {
            throw new IdentityInvalidException("Identity is invalid!");
        }
    }

    public void validateDate(LocalDate date) {
        if (date == null || date.isAfter(LocalDate.now())) {
            throw new DateInvalidException("The date is invalid, the date cannot be greater than the current date.");
        }
    }

    public void validateDescription(String description) {
        if (description.length() <= 200) {
            throw new DescriptionInvalidException("The description is short, the minimum is 200 characters.");
        }
    }

    public void validateState(String state) {
        if (state.equals("")) {
            throw new StateInvalidException("State is invalid.");
        }
    }

    public void validateCity(String city) {
        if (city.equals("")) {
            throw new CityInvalidException("City is invalid.");
        }
    }
}
