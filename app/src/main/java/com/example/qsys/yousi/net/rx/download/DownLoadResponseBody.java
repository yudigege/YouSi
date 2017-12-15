package com.example.qsys.yousi.net.rx.download;

import com.example.qsys.yousi.common.util.LogUtils;
import com.example.qsys.yousi.net.rx.listener.DownLoadProgressListener;

import java.io.IOException;

import javax.annotation.Nullable;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * @author hanshaokai
 * @date 2017/12/13 16:13
 */


public class DownLoadResponseBody extends ResponseBody {
    private ResponseBody responseBody;

    private DownLoadProgressListener progressListener;

    private BufferedSource bufferedSource;

    public DownLoadResponseBody(ResponseBody responseBody, DownLoadProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;

    }

    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                //read() return the number of bytes read ,or -1 if this source is exhausted;
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (progressListener != null) {

                    progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                    LogUtils.d("totalBytesRead" + totalBytesRead + "\n" + "responseBody.contentLength()" + responseBody.contentLength());
                }
                return bytesRead;
            }
        };

    }
}
