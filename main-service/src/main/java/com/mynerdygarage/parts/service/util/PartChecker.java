package com.mynerdygarage.parts.service.util;

import com.mynerdygarage.error.exception.ConflictOnRequestException;
import com.mynerdygarage.parts.model.Part;
import com.mynerdygarage.parts.model.PartStatus;
import com.mynerdygarage.parts.repository.PartRepository;

import java.time.LocalDate;
import java.util.Arrays;

public class PartChecker {

    private static void check(Part partToCheck) {

        if (partToCheck.getStatus() != null &&
                !Arrays.stream(PartStatus.values()).map(Enum::name).toList().contains(partToCheck.getStatus())) {

            throw new ConflictOnRequestException("- Incorrect part status");
        }

        LocalDate orderDate = partToCheck.getOrderDate();
        LocalDate deliveryDate = partToCheck.getDeliveryDate();
        boolean isPlanned;
        isPlanned = partToCheck.getStatus() != null && partToCheck.getStatus().equals(PartStatus.PLANNED.name());

        if (orderDate != null && deliveryDate != null && orderDate.isAfter(deliveryDate)) {
            throw new ConflictOnRequestException("- Order date cannot be after delivery date");
        }

        if (orderDate != null && orderDate.isBefore(LocalDate.now()) && isPlanned) {
            throw new ConflictOnRequestException("- If status='PLANNED' order date must be after current date");
        }

        if (deliveryDate != null
                && deliveryDate.isAfter(LocalDate.now())
                && !partToCheck.getStatus().equals(PartStatus.ON_DELIVERY.name())
                && !partToCheck.getStatus().equals(PartStatus.CANCELLED.name())) {

            throw new ConflictOnRequestException(
                    "- If status not equals 'ON_DELIVERY' or 'CANCELLED' delivery date must be before current date");
        }
    }


    public static void checkNewPart(PartRepository partRepository, Part newPartToCheck) {

        check(newPartToCheck);

        Long userId = newPartToCheck.getUser().getId();

        Part foundPart =
                partRepository.findByUserIdAndOrderDateAndPartNumberIgnoreCase(
                        userId, newPartToCheck.getOrderDate(), newPartToCheck.getPartNumber());

        if (foundPart != null) {
            throw new ConflictOnRequestException("- Part with this UserId and orderDate and partNumber already exists");
        }
    }

    public static void checkUpdatePart(PartRepository partRepository, Part updatePartToCheck) {

        check(updatePartToCheck);

        Long userId = updatePartToCheck.getUser().getId();

        Part foundPart =
                partRepository.findByUserIdAndOrderDateAndPartNumberIgnoreCase(
                        userId, updatePartToCheck.getOrderDate(), updatePartToCheck.getPartNumber());

        if (!foundPart.getId().equals(updatePartToCheck.getId())) {
            throw new ConflictOnRequestException(
                    "- Another part with this userId and orderDate and partNumber already exists");
        }
    }
}
