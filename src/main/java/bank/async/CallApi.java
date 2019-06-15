package bank.async;

import bank.Controller;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CallApi extends AsyncTask {
    private Controller myController;
    private static final String API_URL = "http://localhost:8080/submit";

    /**
     * set controller to change its property
     *
     * @param myController
     */
    public CallApi(Controller myController) {
        this.myController = myController;
    }

    /**
     * set loading before send request
     */
    @Override
    public void onPreExecute() {
        myController.resultLabel.setVisible(true);
        myController.resultLabel.setText("Loading...");
    }

    /**
     * send request to the server
     *
     * @param params I didn't pass anything
     *
     * @return Object
     */
    @Override
    public Object doInBackground(Object[] params) {
        try {
            HttpResponse<String> response = Unirest.post(API_URL)
                    .field("name", myController.name.getText())
                    .field("family", myController.family.getText())
                    .field("email", myController.email.getText())
                    .field("service", myController.service.getValue())
                    .asString();
            if (response.getStatus() == 200)
                return true;
        } catch (UnirestException e) {
            e.printStackTrace();
            return false;
        }
        return null;
    }

    /**
     * set label to show result of request
     *
     * @param params I set it into doInBackground method returns
     */
    @Override
    public void onPostExecute(Object params) {
        if ((boolean) params)
            myController.resultLabel.setText("Service submitted");
        else
            myController.resultLabel.setText("Server Error :(");
    }

    /**
     * i haven't done anything in callback yet.
     *
     * @param params
     */
    @Override
    public void progressCallback(Object[] params) {
     //
    }
}
