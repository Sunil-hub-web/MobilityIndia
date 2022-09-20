package com.example.mobilityindia.syn1.view.allresponse.downloadpdf;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DownloadPDF {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("PDF_URL")
    @Expose
    private String pdfUrl;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

}
