package com.fung.fungdroid;

public class DownloadWithRunnable implements ButtonStrategy {

	 /**
     * Thread object that's used for the download.
     */
    private Thread mThread = null;

    /**
     * Creates and starts a new Thread to download an image in the
     * background via a Runnable. The downloaded image is then
     * diplayed in the UI Thread by posting another Runnable via the
     * Activity's runOnUiThread() method, which uses an internal
     * Handler.
     */
	public void downloadAndDisplayImage(DownloadContext downloadContext) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelDownload(DownloadContext downloadContext) {
		// TODO Auto-generated method stub
		
	}
    
    
}
