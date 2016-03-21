package ru.oz.mytutors.springbatch.model.sem;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "sphinx:document")
public class Category {
    private long id;
    private long categoryUri;
    private long categoryId;
    private long categoryLink;
    private String categoryName;
    private String smallPicture;
    private String picture;
    private List<String> unavailableBaseStores = new ArrayList();
    private String path;
    private float categoryWeight;
    private boolean ftSearchable; //child products(direct or indirect children) will be searchable via fulltext search with category restriction
}
