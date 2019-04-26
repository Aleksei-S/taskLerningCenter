package Validation;

public class ValidationTaskMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start main thread.");
        System.out.println("Start main thread.");

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        GoogleHelper.callDocument();

        SalesforceHepler.checkUsersResults();
    }
}
