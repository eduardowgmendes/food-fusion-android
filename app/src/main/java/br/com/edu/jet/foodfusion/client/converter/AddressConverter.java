package br.com.edu.jet.foodfusion.client.converter;

import br.com.edu.jet.foodfusion.client.shared.dto.contact.info.AddressDTO;
import br.com.edu.jet.foodfusion.ui.model.restaurant.address.Address;

public class AddressConverter extends AttributeConverter<AddressDTO, Address> {

    @Override
    public AddressDTO to(Address target) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(target.getStreet());
        addressDTO.setLocalNumber(target.getLocalNumber());
        addressDTO.setNeighborhood(target.getNeighborhood());
        addressDTO.setCity(target.getCity());
        addressDTO.setState(target.getState());
        addressDTO.setCountry(target.getCountry());
        addressDTO.setZipcode(target.getZipcode());
        return addressDTO;
    }

    @Override
    public Address from(AddressDTO source) {
        Address address = new Address();
        address.setStreet(source.getStreet());
        address.setLocalNumber(source.getLocalNumber());
        address.setNeighborhood(source.getNeighborhood());
        address.setCity(source.getCity());
        address.setState(source.getState());
        address.setCountry(source.getCountry());
        address.setZipcode(source.getZipcode());
        return address;
    }
}
