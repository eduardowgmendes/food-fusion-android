package br.com.edu.jet.foodfusion.client.converter;

import java.time.LocalTime;

import br.com.edu.jet.foodfusion.client.shared.dto.restaurant.ServiceTimeDTO;
import br.com.edu.jet.foodfusion.ui.model.restaurant.ServiceTime;

public class ServiceTimeConverter extends AttributeConverter<ServiceTimeDTO, ServiceTime> {

    @Override
    public ServiceTimeDTO to(ServiceTime target) {
        ServiceTimeDTO serviceTimeDTO = new ServiceTimeDTO();
        serviceTimeDTO.setName(target.getName());

        LocalTime opensAt = target.getOpensAt();
        serviceTimeDTO.setOpensAt(opensAt != null ? opensAt.toString() : null);

        LocalTime closesAt = target.getClosesAt();
        serviceTimeDTO.setClosesAt(closesAt != null ? closesAt.toString() : null);

        return serviceTimeDTO;
    }

    @Override
    public ServiceTime from(ServiceTimeDTO source) {
        ServiceTime serviceTime = new ServiceTime();
        serviceTime.setName(source.getName());
        serviceTime.setClosesAt(source.getClosesAt());
        serviceTime.setOpensAt(source.getOpensAt());
        return serviceTime;
    }
}
