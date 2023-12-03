package uv.mx.fei.logic.domain;

import java.util.Date;

public class JuryInfo {
    private String students;
    private String workTitle;
    private String modality;
    private String presentationDate;
    private String result;

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getPresentationDate() {
        return presentationDate;
    }

    public void setPresentationDate(String  presentationDate) {
        this.presentationDate = presentationDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
