package com.jy.liuhairui.applayup.utils_dlu;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUtils {
    private static final String TAG = "HttpUtils";
    /**
     * HttpUrlConnection　实现文件上传
     * @param params       普通参数
     * @param fileFormName 文件在表单中的键
     * @param uploadFile   上传的文件
     * @param urlStr       url
     * @throws IOException
     */

    public static void uploadForm(Map<String, String> params, String fileFormName, File uploadFile, String urlStr) throws IOException {

        StringBuilder sb = new StringBuilder();
        if (params != null) {
            for (String key : params.keySet()) {
                sb.append("Content-Disposition: form-data; name=\"" + key + "\"" + "\r\n");
                sb.append("\r\n");
                sb.append(params.get(key) + "\r\n");
            }
        }

        sb.append("--" + BOUNDARY + "\r\n");
        sb.append("Content-Disposition: form-data; name=\"" + fileFormName + "\"; filename=\"" + uploadFile.getName() + "\""
                + "\r\n");
        sb.append("Content-Type: application/octet-stream" + "\r\n");// 如果服务器端有文件类型的校验，必须明确指定ContentType
        sb.append("\r\n");
        byte[] headerInfo = sb.toString().getBytes("UTF-8");
        byte[] endInfo = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        // 设置传输内容的格式，以及长度
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        conn.setRequestProperty("Content-Length",
                String.valueOf(headerInfo.length + uploadFile.length() + endInfo.length));
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();
        InputStream in = new FileInputStream(uploadFile);
        //写入的文件长度
        int count = 0;
        //文件的总长度
        int available = in.available();
        // 写入头部 （包含了普通的参数，以及文件的标示等）
        out.write(headerInfo);
        //--------------------------------------------headerInfo--------------------------------------------------



        // 写入文件
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) != -1) {
            out.write(buf, 0, len);
            count += len;
            int progress = count * 100 / available;
            Log.d(TAG, "上传进度: " + progress + " %");
        }
        // 写入尾部
        out.write(endInfo);
        in.close();
        out.close();
        if (conn.getResponseCode() == 200) {
            System.out.println("文件上传成功");
            String result = stream2String(conn.getInputStream());
            Log.d(TAG, "uploadForm: " + result);
        }
    }

    // 分割符,自己定义即可
    private static final String BOUNDARY = "----WebKitFormBoundaryT1HoybnYeFOGFlBR";

    public static String stream2String(InputStream is) {
        int len;
        byte[] bytes = new byte[1024];
        StringBuffer sb = new StringBuffer();
        try {
            while ((len = is.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
            is.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
