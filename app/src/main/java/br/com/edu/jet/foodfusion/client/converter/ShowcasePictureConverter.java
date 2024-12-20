package br.com.edu.jet.foodfusion.client.converter;

import br.com.edu.jet.foodfusion.client.shared.dto.picture.ShowcasePictureDTO;
import br.com.edu.jet.foodfusion.ui.model.restaurant.menu.ShowcasePicture;

public class ShowcasePictureConverter extends AttributeConverter<ShowcasePictureDTO, ShowcasePicture> {
    @Override
    public ShowcasePictureDTO to(ShowcasePicture target) {
        ShowcasePictureDTO showcasePictureDTO = new ShowcasePictureDTO();
        showcasePictureDTO.setId(target.getId());
        showcasePictureDTO.setBase64(target.getBase64());
        return showcasePictureDTO;
    }

    @Override
    public ShowcasePicture from(ShowcasePictureDTO source) {
        ShowcasePicture showcasePicture = new ShowcasePicture();
        showcasePicture.setId(source.getId());
        showcasePicture.setBase64(showcasePicture.getBase64());
        return showcasePicture;
    }
}
