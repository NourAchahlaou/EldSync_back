package tn.esprit.EldSync.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Attachment {
    @Id
    private String id;
    private String filename;
    private AttachmentType filetype;
    private long filesize;
    private String downloadUrl;
}
