package Validation.Condition;

public class Results {

    public String nameMetadata;
    public String status;
    public String message;
    public String user;

    public Results (String nameMetadata, String message, String user, Boolean status){
        this.nameMetadata = nameMetadata;
        this.message = message;
        this.user = user;
        if(status){
            this.status = "SUCCES";
        } else {
            this.status = "ERROR";
        }
    }


}
