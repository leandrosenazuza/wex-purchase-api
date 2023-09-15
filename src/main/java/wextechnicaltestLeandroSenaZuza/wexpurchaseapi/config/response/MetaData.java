package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response;


import lombok.Data;

import java.util.Map;

@Data
public class MetaData {
    private int count;
    private Map<String, String> labels;
    private Map<String, String> dataTypes;
    private Map<String, String> dataFormats;
    private int total_count;
    private int total_pages;
}
