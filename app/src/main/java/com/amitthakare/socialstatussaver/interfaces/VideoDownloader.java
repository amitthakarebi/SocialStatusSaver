package com.amitthakare.socialstatussaver.interfaces;

public interface VideoDownloader {

//    String createDirectory();

    String getVideoId(String link);

    void DownloadVideo();
}
