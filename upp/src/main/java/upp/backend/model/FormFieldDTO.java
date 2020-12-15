package upp.backend.model;


public class FormFieldDTO {
    String fieldId;
    String fieldValue;

    public FormFieldDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FormFieldDTO(String fieldId, String fieldValue) {
        super();
        this.fieldId = fieldId;
        this.fieldValue = fieldValue;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

}
