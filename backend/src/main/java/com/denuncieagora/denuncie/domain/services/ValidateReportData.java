package com.denuncieagora.denuncie.domain.services;

import com.denuncieagora.denuncie.domain.entities.Report;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class ValidateReportData {

    public void validateId(UUID id) {
        if(id.toString().equals("")) {
            throw new IllegalArgumentException("Id is invalid!");
        }
    }

    public void validateDate(LocalDate date) {
        if (date == null || date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date is invalid!");
        }
    }

    public void validateDescription(String description) {
        if (description.length() <= 200) {
            throw new IllegalArgumentException("Description is invalid!");
        }
    }

    public void validateState(String state) {
        if (state.equals("")) {
            throw new IllegalArgumentException("State is required!");
        }
    }

    public void validateCity(String city) {
        if (city.equals("")) {
            throw new IllegalArgumentException("City is required!");
        }
    }
}
