package com.ximenes.challangeranotaai.domain.category;

import lombok.*;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Category {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerID;

    public Category(CategoryDTO categoryDTO){
        this.title = categoryDTO.title();
        this.description = categoryDTO.description();
        this.ownerID = categoryDTO.ownerID();
    }

    @Override
    public String toString(){
        JSONObject json = new JSONObject();
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("id", this.id);
        json.put("ownerID", this.ownerID);
        json.put("type", "category");
        return json.toString();
    }
}
