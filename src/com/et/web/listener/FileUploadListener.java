package com.et.web.listener;

import org.apache.commons.fileupload.ProgressListener;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2015-12-4上午11:43:01 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： 文件上传监听器<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
public class FileUploadListener implements ProgressListener {

    private long num100Ks = 0;
    private long theBytesRead = 0;
    private long theContentLength = -1;
    private int whichItem = 0;
    private int percentDone = 0;
    private boolean contentLengthKnown = false;

    /** 
     * 
     * @author:liangyanjun
     * @time:2015-12-4上午11:43:20
     * @param bytesRead:已上传长度
     * @param contentLength：总长度
     * @param items */
    @Override
    public void update(long bytesRead, long contentLength, int items) {

        if (contentLength > -1) {
            contentLengthKnown = true;
        }
        theBytesRead = bytesRead;
        theContentLength = contentLength;
        whichItem = items;

        long nowNum100Ks = bytesRead / 100000;
        if (nowNum100Ks > num100Ks) {
            num100Ks = nowNum100Ks;
            if (contentLengthKnown) {
                percentDone = (int) Math.round(100.00 * bytesRead / contentLength);
            }

        }
    }

    public int getPercentDone() {
        return percentDone;
    }
}
