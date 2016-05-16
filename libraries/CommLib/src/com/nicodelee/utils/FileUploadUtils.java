package com.nicodelee.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

//import com.puyuntech.photoprint.util.CustomMultipartEntity.ProgressListener;

/**
 * 文件上传工具类
 * 
 * @author Administrator
 * 
 */
public class FileUploadUtils
{
    public static void uploadFile()
        throws Exception
    {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, File> upfiles = new HashMap<String, File>();
        
        params.put("memId", "18");
        params.put("token", "9f0db934028e4ca2994556db9dd2be24");
        
        upfiles.put("a.png", new File("d:\\a.png"));
        
        String result = uploadMultiFile("http://172.21.1.243:8080/avatarUpload", params, upfiles);
    }
    
    /**
     * 多文件上传
     * 
     * @param actionUrl URL
     * @param params 参数
     * @param files 文件
     * @return 服务器端响应的数据，如果上传时出现异常会返回空字符串
     * @see [类、类#方法、类#成员]
     */
    
    public static String uploadMultiFile(String actionUrl, Map<String, String> params, Map<String, File> files)
    {
        
        String retVal = "";
        HttpClient httpclient = new DefaultHttpClient();
        try
        {
            HttpPost httpPost = new HttpPost(actionUrl);
            MultipartEntity reqEntity = new MultipartEntity();
            
            // 添加附件
            if (files != null && files.size() > 0)
            {
                for (Map.Entry<String, File> entry : files.entrySet())
                {
                    reqEntity.addPart(entry.getKey(), new FileBody(entry.getValue()));// file1为请求后台的File upload;属性
                }
            }
            
            // 添加参数
            if (params != null && params.size() > 0)
            {
                for (Map.Entry<String, String> entry : params.entrySet())
                {
                    reqEntity.addPart(entry.getKey(), new StringBody(entry.getValue(), Charset.forName("UTF-8")));// filename1为请求后台的普通参数;属性
                }
            }
            
            httpPost.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            
            // 请求成功
            if (statusCode == HttpStatus.SC_OK)
            {
                System.out.println("请求成功");
                HttpEntity resEntity = response.getEntity();
                retVal = EntityUtils.toString(resEntity);// httpclient自带的工具类读取返回数据
                // EntityUtils.consume(resEntity);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }
    
    
    public static String uploadMultiFile2(String actionUrl, Map<String, String> params, Map<String, File> files,CustomMultipartEntity multipartContent)
    {
        String serverResponse = null;
        
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext httpContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost(actionUrl);
        long totalSize;

        try
        {
//            CustomMultipartEntity multipartContent = new CustomMultipartEntity(new ProgressListener()
//            {
//                @Override
//                public void transferred(long num)
//                {
//                    publishProgress((int)((num / (float)totalSize) * 100));
//                }
//            });
            
            // We use FileBody to transfer an image
            // 添加附件
            if (files != null && files.size() > 0)
            {
                for (Map.Entry<String, File> entry : files.entrySet())
                {
                    multipartContent.addPart(entry.getKey(), new FileBody(entry.getValue()));// file1为请求后台的File
                                                                                             // upload;属性
                }
            }
            
            // 添加参数
            if (params != null && params.size() > 0)
            {
                for (Map.Entry<String, String> entry : params.entrySet())
                {
                    multipartContent.addPart(entry.getKey(), new StringBody(entry.getValue(), Charset.forName("UTF-8")));// filename1为请求后台的普通参数;属性
                }
            }
            totalSize = multipartContent.getContentLength();
            
            // Send it
            httpPost.setEntity(multipartContent);
            HttpResponse response = httpClient.execute(httpPost, httpContext);
            serverResponse = EntityUtils.toString(response.getEntity());
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return serverResponse;
    }
    
    public static void main(String[] args)
        throws Exception
    {
        // 参数
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("type", "2");
//
//
//        // 文件
//        Map<String, File> files = new HashMap<String, File>();
//        files.put("file1", new File("d:\\A01.xml"));
//
//        String data = uploadMultiFile("http://mail.puyuntech.com:7074/phone/APP/photoUpload.jhtml", params, files);
//
//        System.out.println("data=" + data);
    }
    
}
