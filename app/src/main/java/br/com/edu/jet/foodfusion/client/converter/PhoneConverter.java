package br.com.edu.jet.foodfusion.client.converter;

import br.com.edu.jet.foodfusion.client.shared.dto.contact.info.PhoneDTO;
import br.com.edu.jet.foodfusion.ui.model.restaurant.phone.Phone;

public class PhoneConverter extends AttributeConverter<PhoneDTO, Phone> {
    @Override
    public PhoneDTO to(Phone target) {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setPrefix(target.getPrefix());
        phoneDTO.setPhoneNumber(target.getPhoneNumber());
        return phoneDTO;
    }

    @Override
    public Phone from(PhoneDTO source) {
        Phone phone = new Phone();
        phone.setPrefix(source.getPrefix());
        phone.setPhoneNumber(source.getPhoneNumber());
        return phone;
    }
}
