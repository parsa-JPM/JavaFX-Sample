package bank.async;


import javafx.application.Platform;

/**
 * @author Victor Oliveira
 *
 * @see <a href=""https://medium.com/@victorlaertedoliveira/archive-javafx-asynctask-a-easy-way-to-handle-multithreading-in-javafx-26689f3cd8fa"">medium</a>
 */
public abstract class AsyncTask<T1, T2, T3> {

    private boolean daemon = true;

    private T1[] params;

    public abstract void onPreExecute();

    public abstract T3 doInBackground(T1... params);

    public abstract void onPostExecute(T3 params);

    public abstract void progressCallback(T2... params);

    public void publishProgress(final T2... progressParams) {
        Platform.runLater(() -> progressCallback(progressParams));
    }

    public final Thread backGroundThread = new Thread(new Runnable() {
        @Override
        public void run() {

            final T3 param = doInBackground(params);

            Platform.runLater(() -> onPostExecute(param));
        }
    });

    public void execute(final T1... params) {
        this.params = params;
        Platform.runLater(() -> {

            onPreExecute();

            backGroundThread.setDaemon(daemon);
            backGroundThread.start();
        });
    }

    public void setDaemon(boolean daemon) {
        this.daemon = daemon;
    }

    public final boolean isInterrupted() {
        return this.backGroundThread.isInterrupted();
    }

    public final boolean isAlive() {
        return this.backGroundThread.isAlive();
    }
}