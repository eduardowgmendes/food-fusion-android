package br.com.edu.jet.foodfusion.client.converter;

import br.com.edu.jet.foodfusion.client.shared.dto.contact.info.EmailDTO;
import br.com.edu.jet.foodfusion.ui.model.restaurant.email.Email;

public class EmailConverter extends AttributeConverter<EmailDTO, Email> {

    @Override
    public EmailDTO to(Email target) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail(target.getEmail());
        return emailDTO;
    }

    @Override
    public Email from(EmailDTO source) {
        Email email = new Email();
        email.setEmail(source.getEmail());
        return email;
    }
}
