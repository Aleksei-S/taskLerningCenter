package Validation.Condition;

public class Results {

    public String nameMetadata;
    public String status;
    public String message;

    public Results (String nameMetadata, boolean status, String message){
        this.nameMetadata = nameMetadata;
        this.nameMetadata = nameMetadata;
        if(status){
            this.status = "SUCCES";
        } else {
            this.status = "ERROR";
        }
    }


}
